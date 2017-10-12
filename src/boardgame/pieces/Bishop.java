package boardgame.pieces;

import java.util.ArrayList;

import boardgame.data.Configuration.ConfigElement;

public class Bishop extends Piece {
	private Color diagonals;
	
	public Bishop() {
		super();
		symbol = 'B';
		name = "Bishop";
		value = 3;
		pName = PieceName.BISHOP;
	}

	public Bishop(Square square) {
		this();
		this.square = square;
		// TODO Auto-generated constructor stub
	}

	public Bishop(Color c) {
		this();
		color = c;
	}
	
	public Bishop(Color c, Square s) {
		this(c);
		square = s;
		s.setPiece(this);
	}
	
	public Bishop(ConfigElement element, Board b) {
		this(element.getColor(), b.getSquares().get(element.getSquare()));
		this.board = b;
	}

	public Bishop(Piece p) {
		super(p);
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
		char file = square.getFile(), tempFile;
		int fileIndex = Square.alphabet.indexOf(file), tempRank = rank, tempIndex = fileIndex;
		
		//top right diagonal
		tempRank++;
		tempIndex++;
		while (tempRank <= 8 && tempIndex < 8) {
			tempFile = Square.alphabet.charAt(tempIndex);
			if (!validMovesHelper(tempFile, tempRank, moves)) {
				break;
			}
			tempRank++;
			tempIndex++;
		}
		
		//top left diagonal
		tempRank = rank + 1;
		tempIndex = fileIndex - 1;
		while (tempRank <= 8 && tempIndex >= 0) {
			tempFile = Square.alphabet.charAt(tempIndex);
			if (!validMovesHelper(tempFile, tempRank, moves)) {
				break;
			}
			tempRank++;
			tempIndex--;
		}
		
		//bottom right diagonal
		tempRank = rank - 1;
		tempIndex = fileIndex + 1;
		while (tempRank >= 1 && tempIndex < 8) {
			tempFile = Square.alphabet.charAt(tempIndex);
			if (!validMovesHelper(tempFile, tempRank, moves)) {
				break;
			}
			tempRank--;
			tempIndex++;
		}
		
		//bottom left diagonal
		tempRank = rank - 1;
		tempIndex = fileIndex - 1;
		while (tempRank >= 1 && tempIndex >= 0) {
			tempFile = Square.alphabet.charAt(tempIndex);
			if (!validMovesHelper(tempFile, tempRank, moves)) {
				break;
			}
			tempRank--;
			tempIndex--;
		}
		return moves;
	}

}
