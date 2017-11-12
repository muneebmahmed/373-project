package boardgame.pieces;

import java.util.ArrayList;

import boardgame.data.Configuration.ConfigElement;

public class Queen extends Piece {

	public Queen() {
		super();
		symbol = 'Q';
		name = "Queen";
		value = 9;
		pName = PieceName.QUEEN;
	}

	public Queen(Square square) {
		this();
		this.square = square;
	}
	
	public Queen(Color c) {
		this();
		color = c;
	}
	
	public Queen(Color c, Square s) {
		this(c);
		square = s;
		s.setPiece(this);
	}
	
	public Queen(ConfigElement element, Board b) {
		this(element.getColor(), b.getSquares().get(element.getSquare()));
		this.moveCount = element.getMoveCount();
		this.board = b;
	}

	public Queen(Piece p) {
		super(p);
		symbol = 'Q';
		name = "Queen";
		value = 9;
		pName = PieceName.QUEEN;
	}

	@Override
	public ArrayList<Square> getAttacking() {
		
		return getValidMoves();
	}

	@Override
	public ArrayList<Square> getValidMoves() {
		Rook r = new Rook(this);
		Bishop b = new Bishop(this);
		ArrayList<Square> moves = new ArrayList<Square>();
		moves.addAll(r.getValidMoves());
		moves.addAll(b.getValidMoves());
		
		return moves;
	}

}
