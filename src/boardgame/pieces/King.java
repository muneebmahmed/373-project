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
	
	
	public King(Piece p) {
		super(p);
	}

	@Override
	public ArrayList<Square> getRange() {
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
		//TODO add castling squares to range
		return range;
	}

	@Override
	public ArrayList<Square> getValidMoves() {
		// TODO Auto-generated method stub
		ArrayList<Square> range = getRange();
		ArrayList<Square> moves = new ArrayList<Square>();
		for (Square s : range) {
			if (!s.hasPiece() || s.getPiece().color != color) {
				moves.add(s);
			}
		}
		//TODO add castling
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
