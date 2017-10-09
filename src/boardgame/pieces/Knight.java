package boardgame.pieces;

import java.util.ArrayList;

public class Knight extends Piece {

	public Knight() {
		super();
		symbol = 'N';
		name = "Knight";
		value = 3;
		pName = PieceName.KNIGHT;
	}

	public Knight(Square square) {
		this();
		this.square = square;
	}

	public Knight(Color c) {
		this();
		color = c;
	}
	
	public Knight(Color c, Square s) {
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
