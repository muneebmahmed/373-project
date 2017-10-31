package boardgame.data;

import java.util.*;
import boardgame.pieces.*;
import boardgame.play.*;
/**
 * @author Brock Berube
 * @author Jeremy Sears
 * <p>
 * Represents a single move in the game
 * <br>
 * Data includes:
 * Piece to be moved, origin square, new square, if it's a capture<br>
 * Will have methods to parse a command from the command line<br>
 * Also will have methods to parse from graphical interface, once implemented
 */
public class Command {
	public Piece piece;
	public Square origin;
	public Square destination;
	public boolean capture; //flag if capture takes place
	public boolean promotion;	//flag if pawn promotion
	public PieceName promotionPiece;
	public byte castleMode;	//0 if not castling, 1 if kingside, 2 if queenside
	public Piece capturePiece;	//piece that's captured, if any
	public char pieceSymbol;
	public String originString;
	public String destString;
	//fields for piece color or piece name? If piece can't be found

	public Command() {
		piece = null;
		origin = null;
		destination = null;
		capturePiece = null;
		promotion = false;
		capture = false;
		castleMode = 0;
		originString = null;
		destString = null;
	}
	
	/*
	 * Generates the Command to move a piece from an origin to a destination
	 */
	public Command(Piece piece, Square origin, Square destination) {
		this();
		this.piece = piece;
		this.origin = origin;
		this.destination = destination;
		pieceSymbol = piece.getSymbol();
		if (destination.hasPiece()) {
			capture = true;
			capturePiece = destination.getPiece();
		}
		else if (piece.getPieceName() == PieceName.PAWN && piece.getSpecialFlags() && (origin.getFile() != destination.getFile())) {
			capture = true;
			char file = destination.getFile();
			int rank = origin.getRank();
			capturePiece = piece.getBoard().getSquares().get(file + Integer.toString(rank)).getPiece();
			//check to make sure you're not capturing your own piece
			if (capturePiece == null || capturePiece.getColor() == piece.getColor()) {
				capture = false;
			}
		}
		if (piece.getPieceName() == PieceName.PAWN && (destination.getRank() == 8 || destination.getRank() == 1)) {
			promotion = true;
			promotionPiece = PieceName.QUEEN;
		}
		castleMode = 0;
		if (piece.getPieceName() == PieceName.KING && piece.getMoveCount() == 0) {
			char row;
			//replaced if else with conditional assignment
			row = (piece.getColor() == Color.WHITE)? '1' : '8';
			if (destination.getName().equals("g" + row)) {
				castleMode = 1;
				capturePiece = piece.getBoard().getSquares().get("h" + row).getPiece();
				//reuses capturePiece to store the rook instead of creating a new field
			}
			else if (destination.getName().equals("c" + row)) {
				castleMode = 2;
				capturePiece = piece.getBoard().getSquares().get("a" + row).getPiece();
			}
		}
		originString = origin.getName();
		destString = destination.getName();
	}
	
	public Command(Pawn pawn, Square destination, PieceName promote) {
		this(pawn, pawn.getSquare(), destination);
		promotionPiece = promote;
		promotion = true;
	}
	
	public Command(Command copy, Board b) {
		origin = b.getSquares().get(copy.origin.getName());
		piece = origin.getPiece();
		destination = b.getSquares().get(copy.destination.getName());
		if (copy.capturePiece == null) { capturePiece = null; }
		else {capturePiece = b.getSquares().get(copy.capturePiece.getSquare().getName()).getPiece();}
		promotion = copy.promotion;
		capture = copy.capture;
		castleMode = copy.castleMode;
		pieceSymbol = copy.pieceSymbol;
		promotionPiece = copy.promotionPiece;
	}
	
	/**
	 * Parses string to Command
	 * 
	 * @param input String to be parsed to Command
	 */
	public Command(String input) throws IllegalFormatException {
		//TODO write constructor to parse input string
	}
	
	/**
	 * Parses string to command
	 * <p>
	 * Board is used to obtain references to pieces
	 * <p>
	 * Input string can be formatted in any of the following ways:<br>
	 * Qe1-f1	(Queen e1 to f1)<br>						Qe1xf1	(Queen e1 captures on f1)<br>
	 * Qf1		(Queen to f1)<br>						Qxf1		(Queen captures on f1)<br>
	 * Qef1		(Queen on e-file moves to f1)<br>		Qexf1	(e-file Queen captures on f1)<br>
	 * Q2f1		(Queen on 2nd rank moves to f1)<br>		Q2xf1	(2nd rank Queen captures on f1)<br>
	 * <p>
	 * A pawn has no symbol, e.g.:<br>
	 * e4, e2-e4 both denoted a pawn moving to e4
	 * <p>
	 * Castling is denoted with:<br>
	 * O-O	Kingside castling; King on e-file moves to g-file, Rook on h-file moves to f-file<br>
	 * O-O-O		Queenside; King on e-file moves to c-file, Rook on a-file moves to d-file<br>
	 * <p>
	 * En passant captures can be represented as:<br>
	 * exd6 e.p. or exd6		<br>
	 * both denote a white pawn on e5 capturing a black pawn on d5 en passant while moving to d6<br>
	 * <p>
	 * Promotion is represented as:<br>
	 * e8=Q or exf1=B+<br>
	 *
	 * Moves can also end with a '+' denoting check, and '#' denoting checkmate<br>
	 * 
	 * You can assume the input string has already been split so only
	 * contains commands for one side rather than both
	 * 
	 * @param color Color of piece to be moved
	 * @param input String formatted as above
	 * @param b Board on which to move
	 */
	public Command(Color color, String input, Board b) throws IllegalFormatException {
		//TODO write constructor to parse input string given board
		
		/*char fileDest, fileOrigin;
		int rankDest, rankOrigin;
		String destSquare, originSquare;		//will be used by squares.get()
		char currChar;
		String buffer = input;
		String temp = "";
		boolean done = false;
		
		//We can start by checking for castling
		//Only castling uses 'O' (capital letter not number)
		//Kingside will have 2 O, Queenside will have 3
		
		//We can continue by searching for 'x' and '='
		
		//Let's handle the 'x' and '=' cases here so we don't
		//have to worry about them later
		
		//The rest of this code should probably be in an else branch
		//since we don't want it to keep running
		
		//Suggestion: Have multiple helper functions so this code doesn't
		//become too long
		
		//We can start parsing from the end:
		currChar = input.charAt(input.length()-1);
		buffer = input.substring(0, input.length()-1);
		//the following code may help determine once an int is reached
		while (!done) {
			try {
				temp += currChar;
				rankDest = Integer.parseInt(temp);
				done = true;
			}catch (NumberFormatException e) {
				//what if it's a pawn promotion?
				//check for that as well
				currChar = buffer.charAt(buffer.length()-1);
				buffer = buffer.substring(0, buffer.length()-1);
				temp = "";
				done = false;
			}
		}
		*/
		//Now pull off a char from the string to get the destination file
		
		//Assign destination square using fileDest and rankDest
		
		//check the size of the buffer to see if 0 (pawns don't have symbols)
		//if it's a pawn that doesn't capture, then fileDest = fileOrigin
		
		//Next char you pull off can be an x, a file, a rank, or a piece
		
		/*if (currChar == 'x'){
			capture = true;
			currChar = buffer.charAt(buffer.length()-1);
			buffer = buffer.substring(0, buffer.length()-1);
		}
		else {
			capture = false;
		}*/
		
		//Now the char will be a rank, a file, or a piece
		
	}
	
	public static Command parseToCommand(String input) {
		return new Command(input);
	}
	
	public static Command parseToCommand(Color color, String input, Board b) {
		return new Command(color, input, b);
	}
	
	@Override
	public boolean equals(Object o) {
		//TODO
		if (o == null || !(o instanceof Command)) {
			return false;
		}
		Command command = (Command)o;
		if (command.piece.getPieceName() == piece.getPieceName() && command.origin.equals(origin) && command.destination.equals(destination)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		//TODO write hashCode()
		int c = 0, result = 0;
		c = piece.getPieceName().hashCode();
		result = 31*result + c;
		c = origin.hashCode();
		result = 31*result + c;
		c = destination.hashCode();
		return 31*result + c;
	}
	
	@Override
	public String toString() {
		//TODO write toString() method
		StringBuilder builder = new StringBuilder();
		if (castleMode == 1) {
			return "O-O";
		}
		if (castleMode == 2) {
			return "O-O-O";
		}
		builder.append(pieceSymbol);
		builder.append(origin.getName());
		if (capture) {
			builder.append('x');
		}
		else {
			builder.append('-');
		}
		builder.append(destination.getName());
		if (promotion) {
			builder.append('=');
			switch (promotionPiece) {
			case ROOK:
				builder.append('R');
				break;
			case BISHOP:
				builder.append('B');
				break;
			case KNIGHT:
				builder.append('N');
				break;
			default:
				builder.append('Q');
				break;
			}
		}
		return builder.toString();
	}
}
