package boardgame.pieces;

import java.util.ArrayList;

import boardgame.data.*;
import boardgame.data.Configuration.ConfigElement;

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
	
	public King(ConfigElement element, Board b) {
		this(element.getColor(), b.getSquares().get(element.getSquare()));
		this.board = b;
		castleFlag = element.isFlags();
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
		return castleFlag;
	}
	
	@Override
	public void setSpecialFlags(boolean flag) {
		castleFlag = flag;
	}

}
