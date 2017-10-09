package boardgame.data;

import java.util.*;
import boardgame.pieces.*;

/*
 * Represents the state of the board at any given time
 * ArrayLists for pieces detailing square, color, and other flags and data
 */
public class Configuration {
	//TODO Delete all fields below and create a new class ConfigurationElement instead:
	
	/*
	 * New class will have data:
	 * PieceName pieceName (which piece)
	 * Color color (which side)
	 * String square (string representation of square);
	 * Other flags for castlers and pawns?
	 * Should Kings and Rooks implement a Castler interface?
	 */
	
	/*
	 * ArrayList for pieces that can castle:
	 * King, 2 x Rook for each side
	 * Contains strings of squares each piece is on
	 * Order is:
	 * White King, White Rook, White Rook, Black King, Black Rook x 2
	 * If piece is captured, set to default square
	 */
	ArrayList<String> castleSquares;
	ArrayList<Integer> castleFlags; //whether or not they can castle, same order as above
	
	/*
	 * ArrayList for other back rank pieces
	 * ArrayList only has squares
	 * Order is:
	 * White: Queen, Bishop x 2, Knight x 2; Black
	 */
	ArrayList<String> pieceSquares;
	
	/*
	 * ArrayList for pawns
	 * Order is white then black
	 * pawns can also have en passant flags
	 */
	ArrayList<String> pawnSquares;
	ArrayList<Integer> enPassantFlags;
	
	/*
	 * Pawns can also promote to other pieces
	 */
	
	public Configuration() {
		//initialize arraylists
	}
	
	/*
	 * Generates configuration based on board
	 */
	public Configuration(Board b) {
		this();
		//TODO create configuration
	}
}
