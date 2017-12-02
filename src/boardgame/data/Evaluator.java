package boardgame.data;

import java.util.*;
import boardgame.pieces.*;

/**
 * Useful class for deciding computer moves<br>
 * Evaluates an individual move<br>
 * Creates boards using an input configuration<br>
 */
public class Evaluator {
	Board board;
	ArrayList<Evaluation> evaluations;	//should it be an ArrayList?
										//Maybe some object structured like a graph?
	public Evaluator() {
		board = new Board();
	}
	
	public Evaluator(Board b) {
		board = b.clone();
	}
	
	public Evaluator(Configuration c) {
		board = new Board(c);
	}
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board.clone();
	}

	public class Evaluation {
		protected int value;
		protected Command command;
		
		public Evaluation() {
			value = 0;
			command = null;
		}
		
		public Evaluation(Board b, Command c) {
			c = command;
			//what else?
		}
	}
	
	/**
	 * Evaluates the board and returns the best move for the computer
	 * <p>
	 * Will be improved later<br>
	 * Right now just returns a random move
	 * 
	 * @param sideToMove Color of the side to move
	 * @return Command unformatted version of a good move
	 */
	public Command getBestMove(Color sideToMove) {
		ArrayList<Piece> pieces = board.getPieces();
		ArrayList<Command> commands = new ArrayList<Command>();
		ArrayList<Square> squares = new ArrayList<Square>();
		boolean flag = false;
		for (Piece p : pieces) {
			if (p.getColor() == sideToMove) {
				flag = p.getGoodLegalMoves(squares);}
			else { squares.clear();}
			if (flag) { commands.clear(); }
			for (Square s : squares) {
				commands.add(new Command(p, p.getSquare(), s));
			}
			if (flag) { break; }
		}
		Command c = commands.get((int)(Math.random()*commands.size()));
		return c;
	}
	
	public static int EvaluateBoard(Board b, Color moved) {
		//TODO
		
		return 0;
	}
}
