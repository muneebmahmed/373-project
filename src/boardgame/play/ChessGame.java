package boardgame.play;

import boardgame.pieces.*;
import boardgame.data.*;
import boardgame.gui.*;
import java.util.*;

import javax.swing.JFrame;

import java.lang.*;

/**
 * 
 * @author Muneeb Ahmed
 * @author Brock Berube
 * @author Jeremy Sears
 *
 */
public class ChessGame implements Runnable {
	private Board board;
	private volatile Player white;
	private volatile Player black;
	private volatile Color toMove;
	private volatile int mode; //0 = two humans, 1 = white human, 2 = black human, 3 = two computers
	public GUI ui;
	public MainFrame mf;
	public volatile boolean gameOver;
	public volatile boolean redo;
	public volatile boolean undo;
	public volatile boolean renamed;		//if the frame was renamed by editing the players
	
	public ChessGame() {
		board = new Board();
		white = null;
		black = null;
		toMove = Color.WHITE;
		mode = 0;
	}
	
	public ChessGame(int mode) {
		this();
		this.mode = mode;
	}
	
	public ChessGame(Player white, Player black) {
		this();
		this.white = white;
		this.black = black;
	}
	
	public ChessGame(Player white, Player black, int mode) {
		this(white, black);
		this.mode = mode;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getWhite() {
		return white;
	}

	public void setWhite(Player white) {
		this.white = white;
	}


	public Player getBlack() {
		return black;
	}


	public void setBlack(Player black) {
		this.black = black;
	}


	public int getMode() {
		return mode;
	}


	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public Color getToMove() {
		return toMove;
	}

	public synchronized void setToMove(Color toMove) {
		this.toMove = toMove;
	}

	public GUI getUi() {
		return ui;
	}

	public void setUi(GUI ui) {
		this.ui = ui;
	}
	
	public synchronized void setGameOver(boolean b) {
		gameOver = b;
		notifyAll();
	}
	
	public synchronized boolean isGameOver() {
		return gameOver;
	}
	
	public synchronized void setUndo(boolean b) {
		undo = b;
		setGameOver(false);
	}
	
	public synchronized void setRedo(boolean b) {
		redo = b;
		setGameOver(false);
	}

	@Override
	public synchronized void run() {
		//TODO method stub
		//Player sides[] = { white, black };
		mf = new MainFrame(ui);
		mf.setVisible(true);
		mf.pack();
		mf.setMinimumSize(mf.getSize());
		mf.setGame(this);
		String name;
		if (mode == 0 || mode == 1) {
			name = ui.getPlayerName(Color.WHITE);
			white = new Human(name, Color.WHITE, ui);
		}
		else {
			white = new Computer("White Computer", Color.WHITE);
		}
		if (mode == 0 || mode == 2) {
			name = ui.getPlayerName(Color.BLACK);
			black = new Human(name, Color.BLACK, ui);
		}
		else {
			black = new Computer("Black Computer", Color.BLACK);
		}
		Player current = (toMove == Color.WHITE)? white : black;
		mf.setTitle(white + "-" + black);
		int i = 0, move;
		while (board.isGameOver(toMove) == 0 && !gameOver) {
			ui.requestFocus();
			if (renamed) {
				mf.setTitle(white + "-" + black);
				renamed = false;
			}
			//test if reset or undo
			move = current.Move(board);
			if (move == 50) {
				board.setMateFlag((current.getColor() == Color.WHITE)? 2 : 1);
				break;
			}
			else if (move == 100) {
				if (white instanceof Human && black instanceof Human) {
					if (board.canUndo()) {
						board.undoMove();
					}
					else {
						toMove = (toMove == Color.WHITE)? Color.BLACK : Color.WHITE;
					}
				}
				else {
					if (board.getHistory().size() > 1) {
						board.undoMove(2);
						toMove = (toMove == Color.WHITE)? Color.BLACK : Color.WHITE;
					}
					else if (board.getHistory().size() > 0) {
						board.undoMove();
					}
					else {
						toMove = (toMove == Color.WHITE)? Color.BLACK : Color.WHITE;
					}
					
				}
			}
			else if (move == 25) {
				if (board.canRedo()) {
					board.redoMove(1);
				}
				else {
					toMove = (toMove == Color.WHITE)? Color.BLACK : Color.WHITE;
				}
			}
			else if (move == 10) {
				toMove = (board.getHistory().size()%2 == 1)? Color.WHITE : Color.BLACK;
			}
			ui.updateBoard(board);
			current = (toMove == Color.WHITE)? black : white;
			toMove = current.getColor();
			mf.printMovesHistory();
		}
		switch (board.getMateFlag()) {
		case 1:
			System.out.println(white + " wins!");
			break;
		case 2:
			System.out.println(black + " wins!");
			break;
		case 3:
			System.out.println("Stalemate!");
			break;
		case 4:
			System.out.println("Draw by 50 move rule");
			break;
		case 5:
			System.out.println("Draw by insufficient material");
			break;
		default:
			break;
		}
		System.out.println("Entering game over mode...");
		System.out.println("You can replay the game with the undo/redo buttons, but not change moves");
		while (!gameOver) {
			try {
				wait();
			} catch(InterruptedException e) {}
			if (undo && board.canUndo()) {
				board.undoMove();
				undo = false;
				ui.updateBoard(board);
				mf.printMovesHistory();
			}
			else if (redo && board.canRedo()) {
				board.redoMove(1);
				redo = false;
				ui.updateBoard(board);
				mf.printMovesHistory();
			}
		}
		mf.dispose();
		notifyAll();
	}

}
