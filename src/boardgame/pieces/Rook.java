/**
 * 
 */
package boardgame.pieces;

import java.util.ArrayList;

/**
 * @author muneeb
 *
 */
public class Rook extends Piece {
	protected boolean castleFlag;
	public Rook() {
		super();
		symbol = 'R';
		name = "Rook";
		value = 5;
		castleFlag = true;
	}

	public Rook(Square square) {
		this();
		this.square = square;
		square.setPiece(this);
	}

	public Rook(Color c) {
		this();
		color = c;
	}
	
	public Rook(Color c, Square s) {
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
