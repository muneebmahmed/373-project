package boardgame.pieces;

import java.util.*;
import boardgame.data.*;

public class Board {
	private Configuration currentState;
	private ArrayList<Configuration> history;
	private ArrayList<String> moves;
	private Square[][] board;
	ArrayList<Piece> pieces;
	private ArrayList<Piece> whitePieces, blackPieces;
	HashMap<String, Square> squares;	//easy access to squares
	
	private int mateFlag; //0 = game running, 1 = white wins, 2 = black wins, 3 = stalemate
	private ArrayList<Square> captured; //possible arrayLists to have squares with only captured pieces
	private ArrayList<Piece> capturedPieces;
	/*
	 * Rooks
	 * Bishops
	 * Knights
	 * Use ArrayList?
	 */
	private ArrayList<Pawn> pawns_white, pawns_black;
	
	//Constructor
	public Board() {
		whitePieces = new ArrayList<Piece>();
		blackPieces = new ArrayList<Piece>();
		squares = new HashMap<String, Square>();
		pieces = new ArrayList<Piece>();
		history = new ArrayList<Configuration>();
		moves = new ArrayList<String>();
		pawns_white = new ArrayList<Pawn>();
		pawns_black = new ArrayList<Pawn>();
		capturedPieces = new ArrayList<Piece>();
		mateFlag = 0;
		board = new Square[8][8];
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
		 * Pieces are usually set by squares, i.e.
		 * king = new King(); square.setPiece(king);
		 * EXCEPT in the piece constructor:
		 * king = new King(squares.get(e1);
		 * which implicitly calls square.setPiece() 
		 */
		King king_white = new King(Color.WHITE, squares.get("e1"));
		King king_black = new King(Color.BLACK, squares.get("e8"));
		Queen queen_white = new Queen(Color.WHITE, squares.get("d1"));
		Queen queen_black = new Queen(Color.BLACK, squares.get("d8"));
		whitePieces.addAll(Arrays.asList(king_white, queen_white));
		blackPieces.addAll(Arrays.asList(king_black, queen_black));
		/*
		 * Initializing each other piece is tedious, so use this code:
		 */
		String fchar = "";
		for (int f = 0; f < 2; f++) {
			fchar = "";
			fchar += Square.alphabet.charAt(f*7);
			Rook w_R = new Rook(Color.WHITE, squares.get(fchar + Integer.toString(1)));
			Rook b_R = new Rook(Color.BLACK, squares.get(fchar + Integer.toString(8)));
			fchar = "";
			fchar += Square.alphabet.charAt(1+f*5);
			Knight w_K = new Knight(Color.WHITE, squares.get(fchar + Integer.toString(1)));
			Knight b_K = new Knight(Color.BLACK, squares.get(fchar + Integer.toString(8)));
			fchar = "";
			fchar += Square.alphabet.charAt(2+f*3);
			Bishop w_B = new Bishop(Color.WHITE, squares.get(fchar + Integer.toString(1)));
			Bishop b_B = new Bishop(Color.BLACK, squares.get(fchar + Integer.toString(8)));
			whitePieces.addAll(Arrays.asList(w_R, w_K, w_B));
			blackPieces.addAll(Arrays.asList(b_R, b_K, b_B));
		}
		for (int f = 0; f < 8; f++) {
			fchar = "";
			fchar += Square.alphabet.charAt(f);
			Pawn w_pawn = new Pawn(Color.WHITE, squares.get(fchar + Integer.toString(2)));
			Pawn b_pawn = new Pawn(Color.BLACK, squares.get(fchar + Integer.toString(7)));
			pawns_white.add(w_pawn);
			pawns_black.add(b_pawn);
		}
		whitePieces.addAll(pawns_white);
		blackPieces.addAll(pawns_black);
		pieces.addAll(whitePieces);
		pieces.addAll(blackPieces);
		for (Piece piece : pieces) {
			piece.setBoard(this);
		}
	}
	
	/*
	 * Constructor for a new Board from a given configuration
	 * @param state configuration to be set
	 */
	public Board(Configuration state) {
		this();
		loadConfiguration(state);
		
	}
	
	//setters and getters
	public HashMap<String, Square> getSquares(){
		return squares;
	}
	//TODO add other setters and getters
	
	public Configuration getCurrentState() {
		return currentState;
	}

	public void setCurrentState(Configuration currentState) {
		this.currentState = currentState;
	}

	public ArrayList<Configuration> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<Configuration> history) {
		this.history = history;
	}

	public ArrayList<String> getMoves() {
		return moves;
	}

	public void setMoves(ArrayList<String> moves) {
		this.moves = moves;
	}

	public Square[][] getBoard() {
		return board;
	}

	public int getMateFlag() {
		return mateFlag;
	}

	public void setMateFlag(int mateFlag) {
		this.mateFlag = mateFlag;
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	/*
	 * Moves a single piece
	 * 
	 * @param move command detailing which piece to move where
	 */
	public void Move(Command move) {
		//TODO
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
		//TODO
		return;
	}
	
	/*
	 * Returns all squares in same rank as given square
	 * 
	 * @return ArrayList of all squares in same row as s, except s
	 */
	public ArrayList<Square> getRank(Square s){
		//TODO
		return new ArrayList<Square>();
	}
	/*
	 * Returns all squares in given rank
	 * @return all squares in rank
	 */
	public ArrayList<Square> getRank(int rank){
		ArrayList<Square> row = new ArrayList<Square>();
		String sqName = "a" + Integer.toString(rank);
		Square first = squares.get(sqName);
		row.add(first);
		row.addAll(getRank(first));
		return row;
	}
	
	/*
	 * Returns all squares in same rank as given square
	 * 
	 * @return ArrayList of all squares in same row as s, except s
	 */
	public ArrayList<Square> getFile(Square s){
		//TODO
		return new ArrayList<Square>();
	}
	
	/*
	 * Returns all squares in given file
	 * 
	 * @return all squares in file
	 */
	public ArrayList<Square> getFile(char file){
		//TODO
		return new ArrayList<Square>();
	}
	
}
