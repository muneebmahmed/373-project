package boardgame.play;

import boardgame.pieces.*;
import boardgame.data.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/*
 * Run from command line
 * 
 * Is NOT the CLI class
 * The CLIRunner just runs the CLI
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
				if (j%2 <= 1) {	//change the '==' to  '<=' if not using Eclipse on Mac
					builder.append(' '); //for spacing in Eclipse on Mac
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
		Scanner scanner = new Scanner(System.in);
		String input = "";
		UserInterface commandLine = new CLI();
		int mode = 0;
		Player white, black, current;
		if (args.length != 2) {
			System.out.println("Is white human or computer? (0/1)");
			try { mode = scanner.nextInt(); } catch (InputMismatchException e) { mode = 0; }
		}
		else {
			try { mode = Integer.parseInt(args[0]); } catch (NumberFormatException e) { mode = 1; }
		}
		if (mode == 1) {white = new Computer("Computer", Color.WHITE);}
		else {
			input = commandLine.getPlayerName(Color.WHITE);
			white = new Human(input, Color.WHITE, commandLine);}
		if (args.length != 2) {
			System.out.println("Is black human or computer? (0/1)");
			try { mode = scanner.nextInt(); } catch (InputMismatchException e) { mode = 0; }
		}
		else {
			try {mode = Integer.parseInt(args[1]); } catch (NumberFormatException e) { mode = 1; }
		}
		if (mode == 1) {black = new Computer("Computer", Color.BLACK);}
		else {
			input = commandLine.getPlayerName(Color.BLACK);
			black = new Human(input, Color.BLACK, commandLine);}
		current = white;
		Player players[] = {white, black};
		int i = 0;
		long startTime = 0, endTime = 0;

		Color toMove = Color.WHITE;
		System.out.println("Would you like to open a file?");
		scanner.reset();
		input = scanner.nextLine();
		input = scanner.nextLine();
		if (input.equals("yes") || input.equals("y") || input.equals("Yes")) {
			System.out.println("Enter filepath: ");
			input = scanner.nextLine();
			try {
				FileInputStream file = new FileInputStream(input);
				toMove = testBoard.ReadFile(file);
				file.close();
			} catch (IOException e) {
				System.out.println("Error opening file!");
			}
		}
		//This is for general testing purposes
		if (white instanceof Computer && black instanceof Computer) {
			startTime = System.currentTimeMillis();
		}
		current = (toMove == Color.WHITE)? white : black;
		if (toMove == Color.BLACK) {
			i++;
		}
		while(testBoard.isGameOver(current.getColor()) == 0) {
			System.out.println(printBoard(testBoard));
			Command c;
			if (current instanceof Human) {
				c = commandLine.getCommand(current, testBoard);}
			else {
				Evaluator e = ((Computer)current).evaluator;
				e.setBoard(testBoard);
				c = e.getBestMove(current.getColor());
				c = testBoard.formatCommand(c);
			}
			while (c.castleMode == 100 || c.castleMode == 25) {
				if (testBoard.getHistory().size() != 0 && c.castleMode == 100) {
					testBoard.undoMove();
					System.out.print(printBoard(testBoard));
					//i--;
					current = players[++i%2];
				}
				else if (c.castleMode == 25 && testBoard.canRedo()) {
					testBoard.redoMove(1);
					System.out.println(printBoard(testBoard));
					current = players[++i%2];
				}
				c = commandLine.getCommand(current, testBoard);
			}
			if (c.castleMode == 50) {
				break;
			}
			testBoard.Move(c);
			testBoard.updateState(c);
			//System.out.print(printBoard(testBoard));
			i++;
			if (current instanceof Computer) { System.out.println("I moved " + c); }
			current = players[i%2];
		}
		if (testBoard.isGameOver(current.getColor()) != 0) {
			if (testBoard.getMateFlag() == 1 || testBoard.getMateFlag() == 2) {
				System.out.println(current.getName() + " loses!");
				int checkmateIndex = testBoard.getMoves().size()-1;
				String checkmateString = testBoard.getMoves().get(checkmateIndex);
				checkmateString += '#';
				testBoard.getMoves().remove(checkmateIndex);
				testBoard.getMoves().add(checkmateString);
			}
			else if (testBoard.getMateFlag() == 3) { System.out.println("Stalemate"); }
			else { System.out.println("Draw"); }
		}
		System.out.println(printBoard(testBoard));
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
		if (testBoard.getMateFlag() == 1 || testBoard.getMateFlag() == 2) {
			System.out.println("Checkmate\n");
			System.out.println("Would you like to save the history to a file?");
        		String yesno = scanner.nextLine();
			if (yesno.equals("yes") || yesno.equals("y")) {
				System.out.println("Enter fileName: ");
				input = scanner.nextLine();
				if (!input.contains(".pgn")) {
					input = input + ".pgn";
				}
				try {
					FileOutputStream os = new FileOutputStream(input);
					testBoard.SaveFile(os);
					System.out.println("File saved");
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
		}
        else if (testBoard.getMateFlag() == 3) { System.out.println("Stalemate\n"); }
        else if (testBoard.getMateFlag() == 0) {
        		System.out.println("Would you like to save the history to a file?");
        		String yesno = scanner.nextLine();
        		if (yesno.equals("yes") || yesno.equals("y")) {
        			System.out.println("Enter fileName: ");
        			input = scanner.nextLine();
        			if (!input.contains(".pgn")) {
        				input = input + ".pgn";
        			}
        			try {
						FileOutputStream os = new FileOutputStream(input);
						testBoard.SaveFile(os);
						System.out.println("File saved");
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		
        }
        else { System.out.println("Draw\n"); }
		if (white instanceof Computer && black instanceof Computer) {
			endTime = System.currentTimeMillis();
			System.out.println("Execution time: " + ((double)endTime-startTime)/1000.0 + " seconds");
		}else {
			System.out.println("Goodbye, " + players[0].getName() + " and " + players[1].getName());}
		scanner.close();
		
		return;
	}

}
