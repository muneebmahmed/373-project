package boardgame.pieces;

import java.lang.*;
import java.util.*;
import boardgame.data.*;
import boardgame.data.Configuration.ConfigElement;
import boardgame.play.*;

/**
 * 
 * @author Muneeb Ahmed
 *
 */
public abstract class Piece {
	protected Color color;
	protected PieceName pName;
	protected Square square;
	protected char symbol;
	protected String name;
	protected int value;
	protected int moveCount;
	protected Board board; //We need to have a reference to the board for getValidMoves() to work
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
	
	//create a piece for a given board given a ConfigElement and Board
	public Piece(ConfigElement element, Board b) {
		this(element.getColor(), b.getSquares().get(element.getSquare()));
		this.moveCount = element.getMoveCount();
		this.board = b;
	}
	
	public Piece(Piece p) {
		color = p.color;
		square = p.square;
		moveCount = p.value;
		board = p.board;
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
	
	public PieceName getPieceName() {
		return pName;
	}

	public String getName() {
		return name;
	}

	public void setName(PieceName name) {
		this.pName = name;
		this.name = name.toString();
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
	
	public boolean getSpecialFlags() {
		return false;
	}
	/*
	 * Rooks, Kings, and pawns will override
	 * SpecialFlags is used for generating configurations
	 * Rooks, Kings, and pawns have boolean flags that other pieces don't
	 */
	public void setSpecialFlags(boolean flag) {
		return;	//don't do anything except if special piece
	}
	public String getEnPassant() {
		return null;
	}
	public void setEnPassant(String s) {
		return;
	}
	
	public void incrementMoveCount() {
		moveCount++;
		setSpecialFlags(false);	//calls overridden child method (tested in debug)
	}
	

	/**
	 * Returns ArrayList of all squares in piece's range, regardless
	 * of whether or not it can actually move there
	 * 
	 * @return ArrayList of squares in range
	 */
	public abstract ArrayList<Square> getRange();
	
	/**
	 * Creates ArrayList of squares that can be moved to
	 * <p>
	 * Checks of piece can actually move there<br>
	 * Does NOT check if King will be left in check
	 * 
	 * @return ArrayList of squares that can be moved to
	 */
	public abstract ArrayList<Square> getValidMoves();
	
	/**
	 * Helper function for getValidMoves()
	 * 
	 * @param file of the destination square
	 * @param rank of the destination square
	 * @param moves ArrayList of squares to add a move to
	 * @return true if move added, false if not
	 */
	protected boolean validMovesHelper(char file, int rank, ArrayList<Square> moves) {
		Square s = board.getSquares().get(file + Integer.toString(rank));
		if (!s.hasPiece()) {
			moves.add(s);
			return true;
		}
		else if (s.getPiece().color != color) {
			moves.add(s);
		}
		return false;
	}
	
	/**
	 * Creates ArrayList of Squares that can legally be moved to
	 * <p>
	 * Checks if King will be in check after each valid moves
	 * 
	 * @return ArrayList of Squares that legally can be moved to
	 */
	public ArrayList<Square> getLegalMoves(){
		ArrayList<Square> validMoves = getValidMoves();
		ArrayList<Square> legalMoves = new ArrayList<Square>();
		Configuration currentState = board.getCurrentState();
		Board testBoard = new Board();
		for (Square dest : validMoves) {
			testBoard.loadConfiguration(currentState);
			Command validMoveCommand = new Command(this, this.square, dest);
			validMoveCommand = testBoard.formatCommand(validMoveCommand);
			testBoard.Move(validMoveCommand);
			if (!testBoard.KingInCheck(this.color)) {
				legalMoves.add(dest);
			}
		}
		return legalMoves;
	}
	
	public String toString() {
		return color.toString() + ' ' + pName.toString();
	}
	
}
