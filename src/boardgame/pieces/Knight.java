package boardgame.pieces;

import java.util.ArrayList;

public class Knight extends Piece {

	public Knight() {
		super();
		symbol = 'N';
		name = "Knight";
		value = 3;
		// TODO Auto-generated constructor stub
	}

	public Knight(Square square) {
		this();
		this.square = square;
		// TODO Auto-generated constructor stub
	}

	public Knight(Color c) {
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
