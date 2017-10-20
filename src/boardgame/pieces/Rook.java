/**
 * 
 */
package boardgame.pieces;

import java.util.ArrayList;

import boardgame.data.Configuration.ConfigElement;

/**
 * @author Muneeb Ahmed
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
		pName = PieceName.ROOK;
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
	
	public Rook(ConfigElement element, Board b) {
		this(element.getColor(), b.getSquares().get(element.getSquare()));
		this.moveCount = element.getMoveCount();
		this.board = b;
		castleFlag = element.isFlags();
	}

	public Rook(Piece p) {
		super(p);
		pName = PieceName.ROOK;
		symbol = 'R';
		name = "Rook";
		value = 5;
		castleFlag = false;
	}

	@Override
	public ArrayList<Square> getRange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Square> getValidMoves() {
		ArrayList<Square> moves = new ArrayList<Square>();
		//test file
		int rank = square.getRank();
		char file = square.getFile();
		//squares above rook
		for (int i = rank + 1; i <= 8; i++) {
			if (!validMovesHelper(file, i, moves)) {
				break;
			}
		}
		//squares below rook
		for (int i = rank -1; i >= 1; i--) {
			if (!validMovesHelper(file, i, moves)) {
				break;
			}
		}
		//test rank
		int index = Square.alphabet.indexOf(file);
		//squares right of rook
		for (int j = index + 1; j < 8; j++) {
			if (!validMovesHelper(Square.alphabet.charAt(j), rank, moves)) {
				break;
			}
		}
		//squares left of rook
		for (int j = index -1; j >= 0; j--) {
			if (!validMovesHelper(Square.alphabet.charAt(j), rank, moves)) {
				break;
			}
		}
		return moves;
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
