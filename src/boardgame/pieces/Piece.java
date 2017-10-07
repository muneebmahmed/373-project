package boardgame.pieces;

import java.lang.*;
import java.util.*;

public abstract class Piece {
	private Color color;
	private Square square;
	private String name;
	//Board board; //<- We might need to have a reference to the board for getValidMoves() to work
	//other fields
	
	public Piece() {
		color = Color.WHITE;
		square = null;
		name = "unknown";
	}
	
	public Piece(Square square) {
		color = square.color;
		this.square = square;
		name = "unknown";
	}
	
	/*
	 * Returns ArrayList of all squares in piece's range, regardless
	 * of whether or not it can actually move there
	 * 
	 * @return ArrayList of squares in range
	 */
	public abstract ArrayList<Square> getRange();
	
	/*
	 * Returns ArrayList of squares that can be moved to
	 * 
	 * Calls getRange() to get squares in range
	 * Checks of piece can actually move there
	 * Does NOT check if King will be left in check
	 * 
	 * @return ArrayList of squares that can be moved to
	 */
	public abstract ArrayList<Square> getValidMoves();
}
