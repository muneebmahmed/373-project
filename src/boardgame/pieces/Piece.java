package boardgame.pieces;

import java.lang.*;
import java.util.*;

public abstract class Piece {
	protected Color color;
	protected Square square;
	protected char symbol;
	protected String name;
	protected int value;
	protected int moveCount;
	//protected Board board; //<- We might need to have a reference to the board for getValidMoves() to work
	//other fields
	
	public Piece() {
		color = Color.WHITE;
		square = null;
		symbol = ' ';
		value = 0;
		name = "unknown";
		moveCount = 0;
	}
	
	public Piece(Square square) {
		this();
		this.square = square;
	}
	
	public Piece(Color c) {
		this();
		color = c;
	}
	
	public Piece(Color c, Square s) {
		this();
		color = c;
		square = s;
		s.setPiece(this); //Only ok in constructor, otherwise square always sets piece
	}
	
	//setters and getters
	public void setSquare(Square s) {
		square = s;
	}
	public Square getSquare() {
		return square;
	}
	//other setters and getters
	
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
	
	/*
	 * The following are functions to identify each piece
	 * Each child class overrides their respective method to return true
	 * @return true if that piece, else false
	 */
	public boolean isKing() {
		return false;
	}
	public boolean isQueen() {
		return false;
	}
	public boolean isRook() {
		return false;
	}
	public boolean isBishop() {
		return false;
	}
	public boolean isKnight() {
		return false;
	}
	public boolean isPawn() {
		return false;
	}
	
	public String toString() {
		return name;
	}
}
