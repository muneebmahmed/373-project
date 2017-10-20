package boardgame.pieces;

import java.util.ArrayList;

import boardgame.data.Configuration.ConfigElement;

public class Pawn extends Piece {
	protected boolean enPassantFlag; //whether or not can capture en passant next turn
	protected Square enPassantCapture;
	
	public Pawn() {
		super();
		symbol = ' ';
		name = "pawn";
		value = 1;
		enPassantFlag = false;
		pName = PieceName.PAWN;
		enPassantCapture = null;
	}

	public Pawn(Square square) {
		this();
		this.square = square;
	}

	public Pawn(Color c) {
		this();
		color = c;
	}
	
	public Pawn(Color c, Square s) {
		this(c);
		square = s;
		s.setPiece(this);
	}
	
	public Pawn(ConfigElement element, Board b) {
		this(element.getColor(), b.getSquares().get(element.getSquare()));
		this.moveCount = element.getMoveCount();
		this.board = b;
		enPassantFlag = element.isFlags();
		if (element.getEnPassantSquare() != null) {
			enPassantCapture = board.getSquares().get(element.getEnPassantSquare());
		}
	}

	public Pawn(Piece p) {
		super(p);
		symbol = ' ';
		name = "pawn";
		value = 1;
		pName = PieceName.PAWN;
		enPassantFlag = false;
		enPassantCapture = null;
	}
	
	@Override
	public String getEnPassant() {
		if (enPassantCapture == null) {
			return null;
		}
		return enPassantCapture.toString();
	}
	
	@Override
	public void setEnPassant(String s) {
		if (s == null) {
			enPassantCapture = null;
			return;
		}
		enPassantCapture = board.getSquares().get(s);
	}

	@Override
	public boolean getSpecialFlags() {
		return enPassantFlag;
	}
	
	@Override
	public void setSpecialFlags(boolean flag) {
		enPassantFlag = flag;
	}

	@Override
	public ArrayList<Square> getRange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Square> getValidMoves() {
		ArrayList<Square> moves = new ArrayList<Square>();
		int rank = square.getRank();
		char file = square.getFile();
		int direction = (color == Color.WHITE)? 1 : -1;
		int fileIndex = Square.alphabet.indexOf(file), tempRank = rank+direction;
		boolean front = false;
		for (int i = fileIndex -1; i <= fileIndex + 1; i++) {
			if (i >= 0 && i <= 7 && tempRank <= 8 && tempRank >= 1) {
				if (validMovesHelper(Square.alphabet.charAt(i), tempRank, moves)) {
					front = true;
				}
			}
		}
		if (moveCount == 0 && front) {
			tempRank += direction;
			validMovesHelper(file, tempRank, moves);
		}
		
		tempRank = rank + direction;
		if (enPassantFlag && enPassantCapture != null) {
			Square s = board.getSquares().get(enPassantCapture.getFile() + Integer.toString(tempRank));
			moves.add(s);
		}
		return moves;
	}
	
	@Override
	protected boolean validMovesHelper(char file, int rank, ArrayList<Square> moves) {
		Square s = board.getSquares().get(file + Integer.toString(rank));
		if (!s.hasPiece() && file == square.getFile()) {
			moves.add(s);
			return true;
		}
		else if (s.hasPiece() && s.getPiece().color != color && file != square.getFile()) {
			moves.add(s);
		}
		return false;
	}

}
