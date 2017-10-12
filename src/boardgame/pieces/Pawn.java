package boardgame.pieces;

import java.util.ArrayList;

import boardgame.data.Configuration.ConfigElement;

public class Pawn extends Piece {
	protected boolean enPassantFlag; //whether or not can capture en passant next turn
	protected Pawn enPassantCapture;
	
	public Pawn() {
		super();
		symbol = 'p';	//unnecessary since line is in superclass
		name = "pawn";
		value = 1;
		enPassantFlag = false;
		pName = PieceName.PAWN;
		enPassantCapture = null;
	}

	public Pawn(Square square) {
		this();
		this.square = square;
	}

	public Pawn(Color c) {
		this();
		color = c;
	}
	
	public Pawn(Color c, Square s) {
		this(c);
		square = s;
		s.setPiece(this);
	}
	
	public Pawn(ConfigElement element, Board b) {
		this(element.getColor(), b.getSquares().get(element.getSquare()));
		this.board = b;
		enPassantFlag = element.isFlags();
	}

	public Pawn(Piece p) {
		super(p);
		enPassantFlag = false;
	}

	@Override
	public ArrayList<Square> getRange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Square> getValidMoves() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean getSpecialFlags() {
		return enPassantFlag;
	}
	
	@Override
	public void setSpecialFlags(boolean flag) {
		enPassantFlag = flag;
	}

}
