package boardgame.play;

import boardgame.pieces.*;
import boardgame.data.*;
import java.util.*;

/*
 * Run from command line
 * Also use for testing
 */
public class CLIRunner {

	public CLIRunner() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		//ChessGame game = new ChessGame();
		//do stuff
		Board testBoard = new Board();
		ArrayList<String> testSquareStrings = new ArrayList<String>(Arrays.asList("a1", "b1", "c1", "d1", "e1", "f1",
				"g1", "h1", "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"));
		for (int i = 0; i < 16; i++) {
			Square testSquare = testBoard.getSquares().get(testSquareStrings.get(i));
			System.out.println(testSquare + " has a " + testSquare.getPiece());
		}
		testBoard.printBoard();
	}

}
