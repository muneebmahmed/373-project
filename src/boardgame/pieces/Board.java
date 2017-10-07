package boardgame.pieces;

import java.util.*;
import boardgame.data.*;

public class Board {
	Configuration currentState;
	ArrayList<Configuration> history;
	
	ArrayList<Piece> pieces;
	HashMap<String, Square> squares;
	private Square board[][];
	int mateFlag; //0 = game running, 1 = white wins, 2 = black wins, 3 = stalemate
	
	/*
	 * Kings
	 * Queens
	 * Rooks
	 * Bishops
	 * Knights
	 * pawns
	 */
	
	public Board() {
		squares = new HashMap<String, Square>();
		pieces = new ArrayList<Piece>();
		history = new ArrayList<Configuration>();
		mateFlag = 0;
		//create board
		Color colors[] = {Color.BLACK, Color.WHITE};
		int i = 0;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				i %= 2;
				board[row][col] = new Square(colors[i], row, col);
				squares.put(board[row][col].toString(), board[row][col]);
				i++;
			}
		}
		
		//create pieces
	}
	
	/*
	 * Returns all squares
	 */
	public ArrayList<Square> getRank(Square s){
		return new ArrayList<Square>();
	}
}
