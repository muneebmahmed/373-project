package boardgame.pieces;

import java.lang.*;

public enum Color {
	WHITE, BLACK;
	
	public String toString() {
		return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
	}
}
