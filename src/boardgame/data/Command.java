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
	//fields for piece color or piece name? If piece can't be found

	public Command() {
		piece = null;
		origin = null;
		destination = null;
	}
	
	/*
	 * Parses string to Command
	 */
	public Command(String input) {
		//TODO write constructor to parse input string
	}
	
	/*
	 * Parses string to command
	 * 
	 * Board is used to obtain references to pieces
	 */
	public Command(Color color, String input, Board b) {
		//TODO write constructor to parse input string given board
	}
	
	public static Command parseToCommand(String input) {
		return new Command(input);
	}
}
