package boardgame.pieces;

import java.util.ArrayList;

public class Pawn extends Piece {
	protected boolean enPassantFlag;
	
	public Pawn() {
		super();
		symbol = ' ';
		name = "pawn";
		value = 1;
		enPassantFlag = false;
	}

	public Pawn(Square square) {
		this();
		this.square = square;
		// TODO Auto-generated constructor stub
	}

	public Pawn(Color c) {
		this();
		color = c;
		// TODO Auto-generated constructor stub
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
