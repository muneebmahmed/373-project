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
	public static int count = 0;
	
	public Evaluator() {
		board = new Board();
		evaluations = new ArrayList<Evaluation>();
	}
	
	public Evaluator(Board b) {
		board = b.clone();
		evaluations = new ArrayList<Evaluation>();
	}
	
	public Evaluator(Configuration c) {
		board = new Board(c);
		evaluations = new ArrayList<Evaluation>();
	}
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board.clone();
	}

	public class Evaluation implements Comparable<Evaluation> {
		protected int value;
		protected Command command;
		
		public Evaluation() {
			value = 0;
			command = null;
		}
		
		public Evaluation(Board b, Command c) {
			Board testBoard = b.clone();
			Command formatted = testBoard.formatCommand(c);
			testBoard.Move(formatted);
			value = Evaluator.EvaluateBoard(testBoard, formatted.piece.getColor());
			formatted.piece = null;
			formatted.origin = null;
			formatted.destination = null;
			formatted.capturePiece = null;
			command = formatted;
			//what else?
		}

		@Override
		public int compareTo(Evaluation o) {
			// TODO Auto-generated method stub
			return value - o.value;		//should I reverse them?
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
		evaluations.clear();	 count = 0;
		ArrayList<Piece> pieces = board.getPieces();
		ArrayList<Command> commands = new ArrayList<Command>();
		ArrayList<Square> squares = new ArrayList<Square>();
		boolean flag = false; //count = 0;
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
		for (Command c : commands) {
			Evaluation e = new Evaluation(board, c);
			if (commands.size() > 1) {
				adjustEvaluation(e, sideToMove);
			}
			evaluations.add(e);
		}
		Collections.sort(evaluations);
		Collections.reverse(evaluations);
		Command c = commands.get((int)(Math.random()*commands.size()));
		int max = evaluations.get(0).value;
		for (int i = evaluations.size() - 1; i >= 0; i--) {
			if (evaluations.get(i).value < max) {
				evaluations.remove(i);
			}
		}
		c = evaluations.get((int)(Math.random()*evaluations.size())).command;
		System.out.println("Moves searched: " + count);
		return c;
	}
	
	public void adjustEvaluation(Evaluation e, Color sideToMove) {
		Board testBoard = board.clone();
		Command c = testBoard.formatCommand(e.command);
		testBoard.Move(c);
		Configuration state = new Configuration(testBoard);
		testBoard.setCurrentState(state);
		ArrayList<Piece> pieces = testBoard.getPieces();
		ArrayList<Command> commands = new ArrayList<Command>();
		ArrayList<Square> squares = new ArrayList<Square>();
		boolean flag = false;
		for (Piece p : pieces) {
			if (p.getColor() != sideToMove) { flag = p.getGoodLegalMoves(squares); }
			else { squares.clear(); }
			if (flag) {
				commands.clear();
				e.value = -1000;
				return;
			}
			for (Square s : squares) { commands.add(new Command(p, p.getSquare(), s)); }
			if (flag) { break; }
		}
		for (Command com : commands) {
			Command formatted = testBoard.formatCommand(com);
			try {
				testBoard.Move(formatted);
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			}
			
			int tempMin = EvaluateBoard(testBoard, sideToMove);
			if (tempMin < e.value) {
				e.value = tempMin;
			}
			testBoard.loadConfiguration(state);
		}
	}
	
	public static int EvaluateBoard(Board b, Color moved) {
		//TODO
		//assign pieces different values based on position for better evaluation?
		int sum = 0, currentValue = 0;
		for (Piece p : b.getPieces()) {
			currentValue = p.getValue();
			currentValue *= (p.getColor() == moved)? 1 : -1;
			sum += currentValue;
		}
		return sum;
	}
}
