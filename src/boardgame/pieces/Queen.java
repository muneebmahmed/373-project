package boardgame.pieces;

import java.util.ArrayList;

public class Queen extends Piece {

	public Queen() {
		super();
		symbol = 'Q';
		name = "Queen";
		value = 9;
	}

	public Queen(Square square) {
		this();
		this.square = square;
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
