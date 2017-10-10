package boardgame.data;

import java.util.*;
import boardgame.pieces.*;
import boardgame.play.*;
/*
 * Represents a single move in the game
 * Data includes:
 * Piece to be moved, origin square, new square, if it's a capture
 * Will have methods to parse a command from the command line
 * Also will have methods to parse from graphical interface, once implemented
 */
public class Command {
	public Piece piece;
	public Square origin;
	public Square destination;
	public boolean capture; //flag if capture takes place
	public Piece capturePiece;	//piece that's captured, if any
	public char pieceSymbol;
	//fields for piece color or piece name? If piece can't be found

	public Command() {
		piece = null;
		origin = null;
		destination = null;
		capturePiece = null;
	}
	
	public Command(Piece piece, Square origin, Square destination) {
		this.piece = piece;
		this.origin = origin;
		this.destination = destination;
	}
	
	/*
	 * Parses string to Command
	 */
	public Command(String input) {
		//TODO write constructor to parse input string
	}
	
	/*
	 * Parses string to command
	 * Board is used to obtain references to pieces
	 * 
	 * Input string can be formatted in any of the following ways:
	 * Qe1-f1	(Queen e1 to f1)						Qe1xf1	(Queen e1 captures on f1)
	 * Qf1		(Queen to f1)						Qxf1		(Queen captures on f1)
	 * Qef1		(Queen on e-file moves to f1)		Qexf1	(e-file Queen captures on f1)
	 * Q2f1		(Queen on 2nd rank moves to f1)		Q2xf1	(2nd rank Queen captures on f1)
	 * 
	 * A pawn has no symbol, e.g.:
	 * e4, e2-e4 both denoted a pawn moving to e4
	 * 
	 * Castling is denoted with:
	 * O-O	Kingside castling; King on e-file moves to g-file, Rook on h-file moves to f-file
	 * O-O-O		Queenside; King on e-file moves to c-file, Rook on a-file moves to d-file
	 * 
	 * En passant captures can be represented as:
	 * exd6 e.p. or exd6		
	 * both denote a white pawn on e5 capturing a black pawn on d5 en passant while moving to d6
	 *
	 * Moves can also end with a '+' denoting check, and '#' denoting checkmate
	 */
	public Command(Color color, String input, Board b) {
		//TODO write constructor to parse input string given board
	}
	
	public static Command parseToCommand(String input) {
		return new Command(input);
	}
	
	public static Command parseToCommand(Color color, String input, Board b) {
		return new Command(color, input, b);
	}
}
