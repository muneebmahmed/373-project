package boardgame.data;

import java.util.*;
import boardgame.pieces.*;
import boardgame.play.*;
/**
 * Represents a single move in the game
 * <br>
 * Data includes:
 * Piece to be moved, origin square, new square, if it's a capture<br>
 * Will have methods to parse a command from the command line<br>
 * Also will have methods to parse from graphical interface, once implemented
 * @author Muneeb Ahmed
 * @author Brock Berube
 * @author Jeremy Sears
 */
public class Command {
	public Piece piece;
	public Square origin;
	public Square destination;
	public boolean capture; //flag if capture takes place
	public boolean promotion;	//flag if pawn promotion
	public PieceName promotionPiece;
	public byte castleMode;	//0 if not castling, 1 if kingside, 2 if queenside, 25 if redo, 100 if undo, 50 if quit
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
	 * @deprecated
	 */
	public Command(String input) throws IllegalArgumentException {
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
	 * @throws IllegalArgumentException if the Command could not be parsed
	 */
	public Command(Color color, String input, Board b) throws IllegalArgumentException {
		this();
		char fileOrigin, currChar;
		int rankOrigin;
		String destSquare, originSquare;		//will be used by squares.get()
		String temp = "", pieceSyms = "KQRBNkqrn";
		PieceName pieceName;
		boolean fileNotrank = false, found = false;
		
		//We can start by removing spaces
		input = input.trim();
		input = input.replace(" ", "");
		input = input.replace(".", "");
		input = input.replace("ep", "");
		input = input.replace("+", "");
		input = input.replace("#", "");
		input = input.replace("?", "");

		//Only castling uses 'O' (capital letter not number)
		//Kingside will have 2 O, Queenside will have 3
		//check if undoing a move or quitting
		if (input.contains("u")) {
			castleMode = 100;		//undo
			if (input.contains("quit")) { castleMode = 50; }
		}
		else if (input.contains("redo")) {
			castleMode = 25;
		}
		else if (input.contains("O-O")) {				//castling
			for (Piece p : b.getPieces()) {
				if (p instanceof King && p.getColor() == color) {
					piece = p;
					origin = p.getSquare();
					break;
				}
			}
			int rookRank = (color == Color.WHITE)? 1 : 8;
			char rookFile = 'h';
			destSquare = 'g' + Integer.toString(rookRank);
			castleMode = 1;								//kingside
			if (input.contains("O-O-O")) {
				rookFile = 'a';
				destSquare = 'c' + Integer.toString(rookRank);
				castleMode = 2;							//queenside
			}
			destination = b.getSquares().get(destSquare);
			capturePiece = b.getSquares().get(rookFile + Integer.toString(rookRank)).getPiece();
			if (capturePiece == null) { throw new IllegalArgumentException(); }
		}
		else {											//not castling
			if (input.contains("=")) {					//promotion
				char promPiece = input.charAt(input.length()-1);
				switch (promPiece) {
				case 'R':
					promotionPiece = PieceName.ROOK;
					break;
				case 'B':
					promotionPiece = PieceName.BISHOP;
					break;
				case 'N':
					promotionPiece = PieceName.KNIGHT;
					break;
				case 'Q':
				default:
					promotionPiece = PieceName.QUEEN;
					break;
				}
				try {
					input = input.substring(0, input.length()-2); 
				} catch (IndexOutOfBoundsException e) {throw new IllegalArgumentException();}
				if (input.contains("=")) { throw new IllegalArgumentException(); }
				promotion = true;
			}
			if (input.contains("x") || input.contains("-")) {
				int captureIndex = input.indexOf('-');
				if (input.contains("x")) {
					capture = true;						//capture
					captureIndex = input.indexOf('x');
				}
				destSquare = input.substring(captureIndex+1);
				if (destSquare.length() != 2) { throw new IllegalArgumentException(); }
				destination = b.getSquares().get(destSquare);
				if (destination == null) { throw new IllegalArgumentException(); }
				input = input.substring(0, captureIndex);
				capturePiece = destination.getPiece();
			}
			else {
				try {
					destSquare = input.substring(input.length()-2);
				} catch (IndexOutOfBoundsException e) {throw new IllegalArgumentException();}
				destination = b.getSquares().get(destSquare);			//set destination
				if (destination == null) { throw new IllegalArgumentException(); }
				try {
					input = input.substring(0, input.length()-2);
				}catch (IndexOutOfBoundsException e) { throw new IllegalArgumentException(); }
			}
			
			//At this point input only has the piece symbol and an origin left
			//Could also be a pawn
			if (input.length() == 0) {					//nothing left, must be pawn
				for (Piece p : b.getPieces()) {
					if (p instanceof Pawn && p.getColor() == color) {
						if (p.getLegalMoves().contains(destination)) {
							piece = p;
							origin = p.getSquare();
							found = true;
							break;
						}
					}
				}
				if (!found) {
					throw new IllegalArgumentException();//error if not found
				}
			}
			else {
				if (pieceSyms.contains(Character.toString(input.charAt(0)))) {
					switch (input.charAt(0)) {			//find which piece it is
					case 'K':
					case 'k':
						pieceName = PieceName.KING;
						break;
					case 'Q':
					case 'q':
						pieceName = PieceName.QUEEN;
						break;
					case 'R':
					case 'r':
						pieceName = PieceName.ROOK;
						break;
					case 'B':
					case 'b':
						pieceName = PieceName.BISHOP;
						break;
					case 'N':
					case 'n':
						pieceName = PieceName.KNIGHT;
						break;
					default:
						pieceName = PieceName.PAWN;
							
					}
					input = input.substring(1);
				}
				else {
					pieceName = PieceName.PAWN;
				}
				if (input.length() != 0) {
					if (input.length() == 2) {		//origin square is in the string
						originSquare = input;
						origin = b.getSquares().get(originSquare);
						if (origin == null) { throw new IllegalArgumentException(); }
						piece = origin.getPiece();
						if (piece == null || piece.getPieceName() != pieceName) {
							throw new IllegalArgumentException();
						}
					}
					else {							//input is of length 1 (also could be greater than 2)
						currChar = input.charAt(0);	//possible bug?
						temp += currChar;
						try {
							fileOrigin = ' ';
							rankOrigin = Integer.parseInt(temp);
						}catch (NumberFormatException e) {
							fileOrigin = currChar;
							rankOrigin = 0;
							fileNotrank = true;
						}
						for (Piece p : b.getPieces()) {	//search pieces for a match
							if (p.getPieceName() == pieceName && p.getColor() == color) {
								if (p.getLegalMoves().contains(destination)) {
									if (fileNotrank && p.getSquare().getFile() == fileOrigin) {
										piece = p;
										origin = p.getSquare();
										found = true;
										break;
									}
									else if (!fileNotrank && p.getSquare().getRank() == rankOrigin) {
										piece = p;
										origin = p.getSquare();
										found = true;
										break;
									}
								}
							}
						}
						if (!found) {
							throw new IllegalArgumentException();
						}
					}
				}
				else {						//input is 0, so a major piece did not specify a starting square
					for (Piece p : b.getPieces()) {
						if (p.getPieceName() == pieceName && p.getColor() == color) {
							if (p.getLegalMoves().contains(destination)) {
								piece = p;
								origin = p.getSquare();
								found = true;	//found = !found; ?
								break;
							}
						}
					}
				}
				
			}
		}
		//Final checks for errors not caught before
		if (castleMode < 3) {
			if (origin == null || destination == null || piece == null) {
				throw new IllegalArgumentException();
			}
			if (destination.hasPiece() && capturePiece == null) {
				capture = true;
				capturePiece = destination.getPiece();
			}
			//pawn is capturing if it leaves the file
			if (piece instanceof Pawn) {
				capture = (destination.getFile() != piece.getSquare().getFile());
				
				//set capturePiece for en passant
				if (piece.getSpecialFlags() && capture && !destination.hasPiece()) {
					Pawn p = (Pawn)piece;
					capturePiece = b.getSquares().get(p.getEnPassant()).getPiece();
				}
				
				//pawns can only promote when they reach the end of the board
				if (promotion && (destination.getRank() != 8 && destination.getRank() != 1)) {
					throw new IllegalArgumentException();
				}
				
				//prevents pawn from reaching back rank without promoting
				if (!promotion && (destination.getRank() == 8 || destination.getRank() == 1)) {
					promotion = true;
					promotionPiece = PieceName.QUEEN;
				}
				
			}
			else if (promotion) { throw new IllegalArgumentException(); }
			
			//fixes fatal capturing error in issue #1
			if (capture && capturePiece == null) {
				capture = false;
			}
			
			//let King castle
			if (piece instanceof King && !piece.getAttacking().contains(destination)) {
				Command c = new Command(piece, origin, destination);
				capturePiece = c.capturePiece;
				castleMode = c.castleMode;
			}
			pieceSymbol = piece.getSymbol();
			originString = origin.getName();
			destString = destination.getName();
		}
		
		//Suggestion: Have multiple helper functions so this code doesn't become too long?
		
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
