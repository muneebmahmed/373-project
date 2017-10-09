package boardgame.pieces;

import java.util.ArrayList;

import boardgame.data.Configuration.ConfigElement;

public class Queen extends Piece {

	public Queen() {
		super();
		symbol = 'Q';
		name = "Queen";
		value = 9;
		pName = PieceName.QUEEN;
	}

	public Queen(Square square) {
		this();
		this.square = square;
	}
	
	public Queen(Color c) {
		this();
		color = c;
	}
	
	public Queen(Color c, Square s) {
		this(c);
		square = s;
		s.setPiece(this);
	}
	
	public Queen(ConfigElement element, Board b) {
		this(element.getColor(), b.getSquares().get(element.getSquare()));
		this.board = b;
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
