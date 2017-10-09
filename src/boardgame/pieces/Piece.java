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
	protected Board board; //We need to have a reference to the board for getValidMoves() to work
	protected PieceName pName;
	//other fields
	
	public Piece() {
		color = Color.WHITE;
		square = null;
		symbol = ' ';
		value = 0;
		name = "unknown";
		moveCount = 0;
		board = null;
		pName = PieceName.KING;
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
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getMoveCount() {
		return moveCount;
	}

	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
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
