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
	private int mode; //replay a game or playing a new one? Undo moves?
	public GUI ui;
	public MainFrame mf;
	public volatile boolean gameOver;
	
	public ChessGame() {
		board = new Board();
		white = null;
		black = null;
		toMove = Color.WHITE;
		mode = 0;
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

	public void setToMove(Color toMove) {
		this.toMove = toMove;
	}

	public GUI getUi() {
		return ui;
	}

	public void setUi(GUI ui) {
		this.ui = ui;
	}
	
	public synchronized boolean isGameOver() {
		return gameOver;
	}

	@Override
	public synchronized void run() {
		//TODO method stub
		//Player sides[] = { white, black };
		mf = new MainFrame(ui);
		mf.setVisible(true);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.pack();
		mf.setMinimumSize(mf.getSize());
		Player current = (toMove == Color.WHITE)? white : black;
		int i = 0, move;
		while (board.isGameOver(toMove) == 0) {
			//test if reset or undo
			move = current.Move(board);
			if (move == 1) {
				
			}
			ui.updateBoard(board);
			current = (toMove == Color.WHITE)? black : white;
			toMove = current.getColor();
		}
		gameOver = true;
		notifyAll();
	}

}
