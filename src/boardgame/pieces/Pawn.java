package boardgame.pieces;

import java.util.ArrayList;

public class Pawn extends Piece {
	protected boolean enPassantFlag; //whether or not can capture en passant next turn
	
	public Pawn() {
		super();
		symbol = ' ';	//unnecessary since line is in superclass
		name = "pawn";
		value = 1;
		enPassantFlag = false;
		pName = PieceName.PAWN;
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

}
