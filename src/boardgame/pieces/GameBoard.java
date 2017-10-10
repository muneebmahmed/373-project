package boardgame.pieces;

import java.util.*;
import boardgame.data.*;


/*
 * Can be superclass for Board
 * 
 */
public class GameBoard {
	ArrayList<Piece> pieces;
	protected Square[][] board;
	HashMap<String, Square> squares;
	
	public GameBoard() {
		pieces = new ArrayList<Piece>();
		squares = new HashMap<String, Square>();
	}

}
