package boardgame.play;

import boardgame.data.Command;
import boardgame.pieces.*;
import java.io.*;
import java.util.*;

public class CLI implements UserInterface {

	private Scanner scanner;

	public CLI() {
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
	}

	@Override
	public Command getCommand(Player player, Board b) {
		String input;
		Command c = new Command();
		Color side = player.getColor();
		do {
			System.out.println(player + ", enter a move: ");
			input = scanner.nextLine();
			
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
			
			/*
			try {
				c = new Command(side, input, b);
			} catch (IllegalFormatException e) {
				e.printStackTrace();
				c = null;
			}*/
		} while (!b.isLegalCommand(c));
		
		return c;
	}
	
	//@Override
	public String getPlayerName(Color player) {
		System.out.println("Enter the player name for " + player + ":");
		return scanner.nextLine();
	}
	
	public int getGameMode() {
		
		return 0;
	}

}
