package boardgame.pieces;

import java.util.ArrayList;

import boardgame.data.*;
import boardgame.data.Configuration.ConfigElement;

/**
 * 
 * @author Muneeb Ahmed
 *
 */
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
		this.moveCount = element.getMoveCount();
		this.board = b;
		castleFlag = element.isFlags();
	}
	
	
	public King(Piece p) {
		super(p);
		symbol = 'K';
		name = "King";
		value = 69;
		castleFlag = true;
		pName = PieceName.KING;
	}

	@Override
	public ArrayList<Square> getAttacking() {
		char file = square.getFile();
		int rank = square.getRank();
		int fileIndex = Square.alphabet.indexOf(file);
		ArrayList<Square> range = new ArrayList<Square>();
		Square sq;
		String accesser = "";
		for (int row = rank-1; row <= rank+1; row++) {
			for (int col = fileIndex-1; col <= fileIndex + 1; col++) {
				if (row >= 1 && row <= 8 && col >= 0 && col <= 7) {
					accesser = Square.alphabet.charAt(col) + Integer.toString(row);
					if (row != rank || col != fileIndex) {
						sq = board.getSquares().get(accesser);
						range.add(sq);
					}
				}
			}
		}
		return range;
	}

	@Override
	public ArrayList<Square> getValidMoves() {
		ArrayList<Square> range = getAttacking();
		ArrayList<Square> moves = new ArrayList<Square>();
		for (Square s : range) {
			if (!s.hasPiece() || s.getPiece().color != color) {
				moves.add(s);
			}
		}
		//TODO castling is done, but could it be better? @Brock @Jeremy
		//parenthesis around or prevents castling out of check
		if ((this.moveCount == 0 || castleFlag) && !board.squareUnderAttack(color, square)) {
			Piece leftRook, rightRook;
			int rank;
			if (color == Color.WHITE) {
				rank = 1;
			}
			else {
				rank = 8;
			}
			leftRook = board.getSquares().get('a' + Integer.toString(rank)).getPiece();
			rightRook = board.getSquares().get('h' + Integer.toString(rank)).getPiece();
			Square s1, s2, s3;
			if (leftRook != null && (leftRook instanceof Rook) && ((Rook)leftRook).castleFlag) {
				s1 = board.getSquares().get('b' + Integer.toString(rank));
				s2 = board.getSquares().get('c' + Integer.toString(rank));
				s3 = board.getSquares().get('d' + Integer.toString(rank));
				if (!s1.hasPiece() && !s2.hasPiece() && !s3.hasPiece() && !board.squareUnderAttack(color, s1) && !board.squareUnderAttack(color, s2) && !board.squareUnderAttack(color,  s3)) {
					moves.add(s2);
				}
			}
			if (rightRook != null && (rightRook instanceof Rook) && ((Rook)rightRook).castleFlag) {
				s1 = board.getSquares().get('f' + Integer.toString(rank));
				s2 = board.getSquares().get('g' + Integer.toString(rank));
				if (!s1.hasPiece() && !s2.hasPiece() && !board.squareUnderAttack(color, s1) && !board.squareUnderAttack(color,  s2)) {
					moves.add(s2);
				}
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
