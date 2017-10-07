package boardgame.pieces;

import java.util.*;
import boardgame.data.*;

public class Board {
	private Configuration currentState;
	private ArrayList<Configuration> history;
	
	ArrayList<Piece> pieces;
	HashMap<String, Square> squares;	//easy access to squares
	private Square board[][];
	int mateFlag; //0 = game running, 1 = white wins, 2 = black wins, 3 = stalemate
	ArrayList<Square> captured; //possible arrayLists to have squares with only captured pieces
	/*
	 * Kings
	 * Queens
	 * Rooks
	 * Bishops
	 * Knights
	 * pawns
	 */
	
	//Constructor
	public Board() {
		squares = new HashMap<String, Square>();
		pieces = new ArrayList<Piece>();
		history = new ArrayList<Configuration>();
		mateFlag = 0;
		//create board
		Color colors[] = {Color.BLACK, Color.WHITE};
		int i = 0;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				i %= 2;
				board[row][col] = new Square(colors[i], row, col);
				squares.put(board[row][col].toString(), board[row][col]);
				i++;
			}
		}
		
		/*
		 * Write code to create each piece
		 */
	}
	
	/*
	 * Constructor for a new Board from a given configuration
	 * @param state configuration to be set
	 */
	public Board(Configuration state) {
		this();
		loadConfiguration(state);
		
	}
	
	/*
	 * Moves a single piece
	 * 
	 * @param move command detailing which piece to move where
	 */
	public void Move(Command move) {
		//do stuff
		return;
	}
	/*
	 * Loads a new configuration to the board (i.e. moves)
	 * 
	 * Moves by loading a new configuration
	 * Clears current configuration and sets it to state
	 * Empties all squares and then sets one by one
	 * Used mainly for taking back moves. To move only one piece,
	 * it's more efficient to use move()
	 * 
	 * @param state new configuration to be loaded
	 */
	public void loadConfiguration(Configuration state) {
		//do stuff
		return;
	}
	
	/*
	 * Returns all squares in same rank as given square
	 * @return ArrayList of all squares in same row as s, except s
	 */
	public ArrayList<Square> getRank(Square s){
		return new ArrayList<Square>();
	}
	/*
	 * Returns all squares in given rank
	 * @return all squares in rank
	 */
	public ArrayList<Square> getRank(int rank){
		ArrayList<Square> row = new ArrayList<Square>();
		//code
		return row;
	}
}
