package boardgame.pieces;

public enum PieceName {
	KING, QUEEN, ROOK, KNIGHT, BISHOP, PAWN;
	
	public String toString() {
		return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
	}
}
