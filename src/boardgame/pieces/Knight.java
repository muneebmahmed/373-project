package boardgame.pieces;

import java.util.ArrayList;

import boardgame.data.Configuration.ConfigElement;

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
	
	public Knight(ConfigElement element, Board b) {
		this(element.getColor(), b.getSquares().get(element.getSquare()));
		this.board = b;
	}

	public Knight(Piece p) {
		super(p);
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
