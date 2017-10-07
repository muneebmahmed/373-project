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

	/**
	 * @param square
	 */
	public Rook(Square square) {
		this();
		this.square = square;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param c
	 */
	public Rook(Color c) {
		super(c);
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
