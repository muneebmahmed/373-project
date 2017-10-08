package boardgame.pieces;

import java.util.ArrayList;

public class Bishop extends Piece {
	private Color diagonals;
	
	public Bishop() {
		super();
		symbol = 'B';
		name = "Bishop";
		value = 3;
	}

	public Bishop(Square square) {
		this();
		this.square = square;
		// TODO Auto-generated constructor stub
	}

	public Bishop(Color c) {
		this();
		color = c;
	}
	
	public Bishop(Color c, Square s) {
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
	
	@Override
	public boolean isBishop() {
		return true;
	}

}
