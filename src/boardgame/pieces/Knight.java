package boardgame.pieces;

import java.util.ArrayList;

import boardgame.data.Configuration.ConfigElement;

public class Knight extends Piece {

	public Knight() {
		super();
		symbol = 'N';
		name = "Knight";
		value = 3;
		pName = PieceName.KNIGHT;
	}

	public Knight(Square square) {
		this();
		this.square = square;
	}

	public Knight(Color c) {
		this();
		color = c;
	}
	
	public Knight(Color c, Square s) {
		this(c);
		square = s;
		s.setPiece(this);
	}
	
	public Knight(ConfigElement element, Board b) {
		this(element.getColor(), b.getSquares().get(element.getSquare()));
		this.moveCount = element.getMoveCount();
		this.board = b;
	}

	public Knight(Piece p) {
		super(p);
		symbol = 'N';
		name = "Knight";
		value = 3;
		pName = PieceName.KNIGHT;
	}

	@Override
	public ArrayList<Square> getAttacking() {
		// TODO Auto-generated method stub
		return getValidMoves();
	}

	@Override
	public ArrayList<Square> getValidMoves() {
		ArrayList<Square> moves = new ArrayList<Square>();
		int rank = square.getRank();
		char file = square.getFile(), tempFile;
		int fileIndex = Square.alphabet.indexOf(file);
		int delta1 = 1, delta2 = 2, temp = 0, tempRank, tempIndex;
		/*
		for (short i = 0; i < 4; i++) {
			tempRank = rank+delta1;
			tempIndex = fileIndex+delta2;
			if (tempRank <= 8 && tempRank >= 1 && tempIndex <= 7 && tempIndex >= 0) {
				tempFile = Square.alphabet.charAt(tempIndex);
				validMovesHelper(tempFile, tempRank, moves);
			}
			tempRank = rank+delta2;
			tempIndex = fileIndex + delta1;
			if (tempRank <= 8 && tempRank >= 1 && tempIndex <= 7 && tempIndex >= 0) {
				tempFile = Square.alphabet.charAt(tempIndex);
				validMovesHelper(tempFile, tempRank, moves);
			}
			temp = delta1;
			delta1 = delta2;
			delta2 = temp*-1;
		}*/
		//which algorithm is better? I like the one below @Brock @Jeremy
		for (short i = 0; i < 8; i++) {
			tempRank = rank+delta1;
			tempIndex = fileIndex+delta2;
			if (tempRank <= 8 && tempRank >= 1 && tempIndex <= 7 && tempIndex >= 0) {
				tempFile = Square.alphabet.charAt(tempIndex);
				validMovesHelper(tempFile, tempRank, moves);
			}
			//swap
			if (i%2 == 1) {
				temp = delta1;
				delta1 = delta2;
				delta2 = temp;
			}
			else {
				delta1 *= -1;
			}
		}
		return moves;
	}

}
