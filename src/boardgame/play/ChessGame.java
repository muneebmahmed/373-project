package boardgame.play;

import boardgame.pieces.*;
import boardgame.data.*;
import java.util.*;
import java.lang.*;

/**
 * 
 * @author Muneeb Ahmed
 * @author Brock Berube
 * @author Jeremy Sears
 *
 */
public class ChessGame {
	private Board board;
	private Player white;
	private Player black;
	private int mode; //replay a game or playing a new one? Undo moves?
	
	public ChessGame() {
		board = new Board();
		white = null;
		black = null;
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
	
	public void playGame() {
		//TODO method stub
	}

}
