package boardgame.play;

import boardgame.data.*;
import boardgame.pieces.*;
import java.io.*;
import java.util.*;

public class CLI implements UserInterface {

	private Scanner scanner;

	public CLI() {
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
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
				builder.append("   ");
				
				//if (j%2 == 1) {	builder.append(' '); } //for spacing in the terminal
			}
			builder.append('\n');
			builder.append(both);
			builder.append('\n');
		}
		builder.append("\t   a     b     c     d     e     f     g     h  \n");
		return builder;
	}

	@Override
	public Command getCommand(Player player, Board b) {
		String input;
		Command c = new Command();
		Color side = player.getColor();
		boolean loopPassed = false;
		do {
			if (loopPassed) {
				System.out.println("That is not a legal move");
			}
			System.out.println(player + ", enter a move: ");
			input = scanner.nextLine();
			/*
			//TODO 
			//comment out
			//next fifty
			//lines later
			while (input.equals("u") && b.getHistory().size() == 0) {
				System.out.println(player + ", choose an origin square, 'u' to undo, or 'quit' to quit:");
				input = scanner.nextLine();
			}
			if (input.equals("u")) {
				c = new Command();
				c.castleMode = 100;
				break;
			}
			if (input.equals("quit")) {
				c = new Command();
				c.castleMode = 50;
				break;
			}
			Square origin, destination;
			origin = b.getSquares().get(input);
			while (origin == null || !origin.hasPiece() || origin.getPiece().getColor() != player.getColor() || origin.getPiece().getLegalMoves().size() == 0) {
				System.out.println(player + ", choose a square with one of your pieces: ");
				input = scanner.nextLine();
				origin = b.getSquares().get(input);
			}
			Piece moving = origin.getPiece();
			ArrayList<Square> validMoves = moving.getLegalMoves();
			System.out.println(moving + "'s valid moves are "+ validMoves);
			System.out.println(player + ", choose a destination square: ");
			input = scanner.nextLine();
			destination = b.getSquares().get(input);
			while (!validMoves.contains(destination)) {
				System.out.println(player + ", choose a valid move: " + validMoves);
				input = scanner.nextLine();
				destination = b.getSquares().get(input);
				if (input.equals("quit")) { c.castleMode = 50; break; }
			}
			if (input.equals("quit")) { c.castleMode = 50; break;}
			if (moving.getPieceName() == PieceName.PAWN && (destination.getRank() == 8 || destination.getRank() == 1)) {
				PieceName promotionPiece;
				char pSymbol;
				System.out.println(player + ", choose a promotion piece: ");
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
				c = new Command((Pawn)moving, destination, promotionPiece);
			}
			else {
				c = new Command(moving, origin, destination);
			}
			//TODO 
			//end
			//uncomment
			*/
			
			try {
				c = new Command(side, input, b);
			} catch (IllegalArgumentException e) {
				//e.printStackTrace();
				System.out.println("Could not parse move!");
				c = new Command();
			}
			loopPassed = true;
		} while (!b.isLegalCommand(c));
		
		return c;
	}
	
	@Override
	public String getPlayerName(Color player) {
		System.out.println("Enter the player name for " + player + ":");
		return scanner.nextLine();
	}
	
	@Override
	public void updateBoard(Board b) {
		//TODO
		System.out.print(printBoard(b));
		return;
	}
	
	public int getGameMode() {
		//TODO
		return 0;
	}

}
