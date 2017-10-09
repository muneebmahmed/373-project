package boardgame.pieces;

import java.util.ArrayList;

public class King extends Piece {
	protected boolean castleFlag;
	
	public King() {
		super();
		symbol = 'K';
		name = "King";
		value = 69;
		castleFlag = true;
		pName = PieceName.KING;
	}
	
	public King(Square s) {
		this();
		square = s;
		s.setPiece(this);
	}
	
	public King(Color c) {
		this();
		color = c;
	}
	
	public King(Color c, Square s) {
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
