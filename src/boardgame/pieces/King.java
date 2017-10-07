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
	}
	
	public King(Square s) {
		this();
		square = s;
	}
	//other constructor with Color as argument
	
	
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
