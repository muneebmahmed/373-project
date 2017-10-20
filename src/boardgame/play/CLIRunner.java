package boardgame.play;

import boardgame.pieces.*;
import boardgame.data.*;
import java.util.*;

/*
 * Run from command line
 * 
 * Is NOT the CLI class. That will be added later
 * The CLIRunner just runs the CLI, which will be added later
 * Also used for testing
 */
public class CLIRunner {

	public CLIRunner() {
		// TODO Auto-generated constructor stub
	}
	
	public static StringBuilder printBoard(Board b) {
		StringBuilder builder = new StringBuilder(450);
	    String top =  "\t _____ _____ _____ _____ _____ _____ _____ _____";
		String row =  "\t|     |     |     |     |     |     |     |     |";
		String both =  "\t|_____|_____|_____|_____|_____|_____|_____|_____|";
		builder.append(top + "\n");
		for (int i = 7; i >= 0; i--) {
			builder.append(row);
			builder.append("\n\t" + (i+1));
			for (int j = 0; j < 8; j++) {
				builder.append("  ");
				if (b.getBoard()[i][j].hasPiece()) {
					Piece p = b.getBoard()[i][j].getPiece();
					switch (p.getSymbol()) {
					case 'K':
						if (p.getColor() == Color.WHITE) {
							builder.append('\u2654');
						}
						else {
							builder.append("♚");
						}
						break;
					case 'Q':
						if (p.getColor() == Color.WHITE) {
							builder.append('\u2655');
						}
						else {
							builder.append("♛");
						}
						break;
					case 'R':
						if (p.getColor() == Color.WHITE) {
							builder.append('\u2656');
						}
						else {
							builder.append('\u265C');
						}
						break;
					case 'B':
						if (p.getColor() == Color.WHITE) {
							builder.append('\u2657');
						}
						else {
							builder.append('\u265D');
						}
						break;
					case 'N':
						if (p.getColor() == Color.WHITE) {
							builder.append('\u2658');
						}
						else {
							builder.append('\u265E');
						}
						break;
					default:
						if (p.getColor() == Color.WHITE) {
							builder.append('\u2659');
						}
						else {
							builder.append('\u265F');
						}
					}
				}
				else {
					builder.append(" ");
				}
				builder.append("  "); //removed space
				if (j%2 == 1) {	//for spacing in the terminal
					builder.append(' ');
				}
			}
			builder.append('\n');
			builder.append(both);
			builder.append('\n');
		}
		builder.append("\t   a     b     c     d     e     f     g     h  \n");
		return builder;
	}

	public static void main(String[] args) {
		//ChessGame game = new ChessGame();
		//do stuff
		Board testBoard = new Board();
		System.out.print(printBoard(testBoard));
		Square origin, destination;
		Scanner scanner = new Scanner(System.in);
		String input = "";
		Piece moving;
		System.out.println("Enter the name for white: ");
		input = scanner.nextLine();
		Player white = new Human(input, Color.WHITE);
		System.out.println("Enter the name for black: ");
		input = scanner.nextLine();
		Player black = new Human(input, Color.BLACK), current;
		Player players[] = {white, black};
		ArrayList<Square> validMoves = new ArrayList<Square>();
		int i = 0;
		
		//This is for general testing purposes
		while(!input.equals("quit")) {
			current = players[i%2];
			System.out.println(current.getName() + ", choose an origin square, 'u' to undo, or 'quit' to quit: ");
			input = scanner.nextLine();
			while (input.equals("u")) {
				if (testBoard.getHistory().size() != 0) {
					testBoard.undoMove();
					System.out.print(printBoard(testBoard));
					i--;
					current = players[i%2];
				}
				System.out.println("Choose an origin square, 'u' to undo, or 'quit' to quit:");
				input = scanner.nextLine();
			}
			if (input.equals("quit") || input.equals("checkmate")) {
				break;
			}
			origin = testBoard.getSquares().get(input);
			while (!origin.hasPiece() || origin.getPiece().getColor() != current.getColor() || origin.getPiece().getValidMoves().size() == 0) {
				System.out.println("Choose a square with one of your pieces: ");
				input = scanner.nextLine();
				origin = testBoard.getSquares().get(input);
			}
			moving = origin.getPiece();
			validMoves = moving.getValidMoves();
			System.out.println(moving + "'s valid moves are "+ validMoves);
			System.out.println("Choose a destination square: ");
			input = scanner.nextLine();
			if (input.equals("quit")) {
				break;
			}
			destination = testBoard.getSquares().get(input);
			while (!validMoves.contains(destination)) {
				System.out.println("Choose a valid move: " + validMoves);
				input = scanner.nextLine();
				destination = testBoard.getSquares().get(input);
			}
			//testBoard.Move(new Command(moving, origin, destination));
			if (moving.getPieceName() == PieceName.PAWN && (destination.getRank() == 8 || destination.getRank() == 1)) {
				PieceName promotionPiece;
				char pSymbol;
				System.out.println("Choose a promotion piece: ");
				input = scanner.nextLine();
				pSymbol = input.charAt(0);
				switch (pSymbol) {
				case 'R':
					promotionPiece = PieceName.ROOK;
					break;
				case 'B':
					promotionPiece = PieceName.BISHOP;
					break;
				case 'K':
				case 'N':
					promotionPiece = PieceName.KNIGHT;
					break;
				case 'Q':
				default:
					promotionPiece = PieceName.QUEEN;
					break;
				}
				current.Move(testBoard, new Command((Pawn)moving, destination, promotionPiece));
			}
			else {
			current.Move(testBoard, new Command(moving, origin, destination));}
			if (testBoard.KingInCheck(current.getColor())) {
				System.out.println("Error! King cannot be in check! ");
				testBoard.undoMove();
				i--;
			}
			else {
				System.out.print(printBoard(testBoard));
			}
			i++;
		}
		System.out.println("History:");
		for (i = 0; i < testBoard.getMoves().size(); i++) {
			if (i%2 == 0) {
				System.out.print(Integer.toString(i/2 + 1) + ". " + testBoard.getMoves().get(i));
			}
			else {
				System.out.println("\t" + testBoard.getMoves().get(i));
			}
		}
		System.out.println("");
		System.out.println("Goodbye, " + players[0].getName() + " and " + players[1].getName());
		scanner.close();
		
		return;
		/*
		while(!input.equals("quit")) {
			System.out.println("Choose an origin square, 'u' to undo, or 'quit' to quit: ");
			input = scanner.nextLine();
			while (input.equals("u")) {
				testBoard.undoMove();
				System.out.print(printBoard(testBoard));
				System.out.println("Choose an origin square, 'u' to undo, or 'quit' to quit:");
				input = scanner.nextLine();
			}
			if (input.equals("quit")) {
				break;
			}
			origin = testBoard.getSquares().get(input);
			moving = origin.getPiece();
			System.out.println(moving + "'s valid moves are "+ moving.getValidMoves());
			System.out.println("Choose a destination square: ");
			input = scanner.nextLine();
			if (input.equals("quit")) {
				break;
			}
			destination = testBoard.getSquares().get(input);
			testBoard.Move(new Command(moving, origin, destination));
			System.out.print(printBoard(testBoard));
		}
		System.out.println("Goodbye");
		scanner.close();
		*/
	}

}
