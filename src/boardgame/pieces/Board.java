package boardgame.pieces;

import java.util.*;
import boardgame.data.*;
import boardgame.data.Configuration.ConfigElement;

/**
 * 
 * @author Muneeb Ahmed
 * @author Brock Berube
 * @author Jeremy Sears
 *
 */
public class Board implements Cloneable {
	private Configuration currentState;
	private ArrayList<Configuration> history;	//use stack instead?
	private ArrayList<String> moves;		//maybe should be of Commands instead of String
	private Stack<Configuration> future;	//change access to private
	private Stack<String> undoneMoves;
	private Square[][] board;
	ArrayList<Piece> pieces;
	private ArrayList<Piece> whitePieces, blackPieces;
	HashMap<String, Square> squares;	//easy access to squares
	private int moveCount; //used for 50 move rule
	
	private int mateFlag; //0 = game running, 1 = white wins, 2 = black wins, 3 = stalemate, 4,5 = draw
	private ArrayList<Piece> capturedPieces;
	//Are separate lists for pawns necessary?
	private ArrayList<Pawn> pawns_white, pawns_black;
	
	//Constructor
	public Board() {
		whitePieces = new ArrayList<Piece>();
		blackPieces = new ArrayList<Piece>();
		squares = new HashMap<String, Square>();
		pieces = new ArrayList<Piece>();
		history = new ArrayList<Configuration>();
		moves = new ArrayList<String>();
		future = new Stack<Configuration>();
		undoneMoves = new Stack<String>();
		pawns_white = new ArrayList<Pawn>();
		pawns_black = new ArrayList<Pawn>();
		capturedPieces = new ArrayList<Piece>();
		moveCount = 0;
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
			i++;
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
		
		currentState = new Configuration(this);
	}
	
	/**
	 * Constructor for a new Board from a given configuration
	 * 
	 * @param state configuration to be set
	 */
	public Board(Configuration state) {
		//just make a new board here instead of default board with this()
		whitePieces = new ArrayList<Piece>();
		blackPieces = new ArrayList<Piece>();
		squares = new HashMap<String, Square>();
		pieces = new ArrayList<Piece>();
		history = new ArrayList<Configuration>();
		moves = new ArrayList<String>();
		future = new Stack<Configuration>();
		undoneMoves = new Stack<String>();
		pawns_white = new ArrayList<Pawn>();
		pawns_black = new ArrayList<Pawn>();
		capturedPieces = new ArrayList<Piece>();
		moveCount = 0;
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
			i++;
		}
		loadConfiguration(state);
		currentState = state.clone();
	}
	
	//setters and getters
	public HashMap<String, Square> getSquares(){
		return squares;
	}
	
	public Configuration getCurrentState() {
		return currentState;
	}

	public void setCurrentState(Configuration currentState) {
		//loadConfiguration()?
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
	
	public int getMoveCount() {
		return moveCount;
	}

	
	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}
	

	/**
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
		clearSquares();
		ArrayList<ConfigElement> elements = state.getElements();
		for (ConfigElement element : elements) {
			switch (element.getName()) {
			case KING:
				King k = new King(element, this);
				pieces.add(k);
				break;
			case QUEEN:
				Queen q = new Queen(element, this);
				pieces.add(q);
				break;
			case ROOK:
				Rook r = new Rook(element, this);
				pieces.add(r);
				break;
			case KNIGHT:
				Knight n = new Knight(element, this);
				pieces.add(n);
				break;
			case BISHOP:
				Bishop b = new Bishop(element, this);
				pieces.add(b);
				break;
			case PAWN:
				Pawn p = new Pawn(element, this);
				pieces.add(p);
				break;
			default:
				break;
			}
		}
		for (Piece p : pieces) {
			if (p.getColor() == Color.WHITE) {
				whitePieces.add(p);
				if (p.getPieceName() == PieceName.PAWN) {
					pawns_white.add((Pawn)p);
				}
			}
			else {
				blackPieces.add(p);
				if (p.getPieceName() == PieceName.PAWN) {
					pawns_black.add((Pawn)p);
				}
			}
		}
		moveCount = state.getPlyCount();
		return;
	}
	
	/*
	 * Removes all pieces from board
	 */
	public void clearSquares() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				board[row][col].setPiece(null);
			}
		}
		//setPiece(null) already calls Piece.setSquare(null);
		pieces.clear();
		whitePieces.clear();
		blackPieces.clear();
		pawns_white.clear();
		pawns_black.clear();
		capturedPieces.clear();
		return;
	}

	/**
	 * Moves a single piece
	 * <p>
	 * Command should either have references to squares or string names <br>
	 * Does not update the configuration, so that the computer can try many moves
	 * more efficiently
	 * 
	 * @param move command detailing which piece to move where
	 */
	public void Move(Command move) {
		//TODO
		
		//if castleMode = 100 then signal to undo a move
		if (move.castleMode == 100) {
			if (history.size() != 0) {
				undoMove();
			}
			return;
		}
		moveCount++;
		int rank;
		char file;
		if (move.piece.getPieceName() == PieceName.KING && move.castleMode > 0) {
			rank = (move.piece.getColor() == Color.WHITE)? 1 : 8;
			file = (move.castleMode == 1)? 'f' : 'd';
			Square rookOrigin = move.capturePiece.getSquare();
			Square rookDestination = squares.get(file + Integer.toString(rank));
			rookOrigin.setPiece(null);
			rookDestination.setPiece(move.capturePiece);
			move.capturePiece.incrementMoveCount();
		}
		
		//capture, including en passant
		if (move.capture) {
			if (move.capturePiece.getColor() == Color.WHITE) {
				whitePieces.remove(move.capturePiece);
			}
			else {
				blackPieces.remove(move.capturePiece);
			}
			pieces.remove(move.capturePiece);
			move.capturePiece.getSquare().setPiece(null);
			moveCount = 0;
		}
		
		//the following was simplified code for testing purposes only
		//it may not yet cover all the possibilities
		Piece moving = move.piece;
		Square origin = move.origin;
		Square destination = move.destination;
		origin.setPiece(null);
		destination.setPiece(moving);
		moving.incrementMoveCount();
		
		//test if promotion
		if (move.promotion) {
			Piece promotion;
			switch (move.promotionPiece) {
			case ROOK:
				promotion = new Rook(moving);
				break;
			case BISHOP:
				promotion = new Bishop(moving);
				break;
			case KNIGHT:
				promotion = new Knight(moving);
				break;
			case QUEEN:
			default:
				promotion = new Queen(moving);
				break;
			}
			destination.setPiece(promotion);
			if (promotion.getColor() == Color.WHITE) {
				whitePieces.remove(moving);
				whitePieces.add(promotion);
			}
			else {
				blackPieces.remove(moving);
				blackPieces.add(promotion);
			}
			pieces.remove(moving);
			pieces.add(promotion);
			
		}
		//reset en passant flags
		for (Piece p : pieces) {
			if (p instanceof Pawn) {
				p.setSpecialFlags(false);
				p.setEnPassant(null);
			}
		}
		if (moving.getPieceName() == PieceName.PAWN && moving.getMoveCount() == 1) {
			if (destination.getRank() == 4 || destination.getRank() == 5) {
				rank = destination.getRank();
				file = destination.getFile();
				int fileIndex = Square.alphabet.indexOf(file);
				Square temp;
				if (fileIndex - 1 >= 0) {
					temp = squares.get(Square.alphabet.charAt(fileIndex -1) + Integer.toString(rank));
					if (temp.hasPiece() && temp.getPiece().getColor() != moving.getColor()) {
						temp.getPiece().setSpecialFlags(true);
						temp.getPiece().setEnPassant(moving.getSquare().getName());
					}
				}
				if (fileIndex + 1 <= 7) {
					temp = squares.get(Square.alphabet.charAt(fileIndex +1) + Integer.toString(rank));
					if (temp.hasPiece() && temp.getPiece().getColor() != moving.getColor()) {
						temp.getPiece().setSpecialFlags(true);
						temp.getPiece().setEnPassant(moving.getSquare().getName());
					}
				}
			}
		}
		if (moving.getPieceName() == PieceName.PAWN) {
			moveCount = 0;
		}
		//state updates moved to updateState()
		return;
	}
	
	/**
	 * Updates the history and current state
	 * <p>
	 * Only called when making a legitimate move <br>
	 * Not calling it allows the computer to more efficiently test multiple moves
	 * 
	 * @param move the Command to be added to move history
	 */
	public void updateState(Command move) {
		history.add(currentState);
		currentState = new Configuration(this);
		moves.add(move.toString());
		return;
	}
	
	/*
	 * Undoes a move
	 */
	public void undoMove() {
		Configuration last = history.get(history.size()-1);
		String lastMove = moves.get(moves.size()-1);
		future.push(currentState);
		undoneMoves.push(lastMove);
		moves.remove(lastMove);
		currentState = last;
		loadConfiguration(last);
		history.remove(last);
		return;
	}
	
	/**
	 * Undoes n moves
	 * 
	 * Is more efficient than calling undoMove() n times
	 * 
	 * @param n number of moves to undo
	 */
	public void undoMove(int n) {
		for (int i = 0; i < n; i++) {
			future.push(currentState);
			currentState = history.get(history.size()-1);
			history.remove(history.size()-1);
			undoneMoves.push(moves.get(moves.size()-1));
			moves.remove(moves.size()-1);
		}
		loadConfiguration(currentState);
		return;
	}
	
	public void redoMove() {
		Command move = new Command(undoneMoves.pop());
		Move(move);
		history.add(currentState);
		currentState = future.pop();
		moves.add(move.toString());
		
		//Use code above, since it should be more efficient than below for one move
		/*
		history.add(currentState);
		currentState = future.pop();
		loadConfiguration(currentState);
		moves.add(undoneMoves.pop());
		*/
		return;
	}
	
	/**
	 * Redoes n moves
	 * 
	 * Should be more efficient than calling redoMove n times
	 * 
	 * @param n, number of moves to redo
	 */
	public void redoMove(int n) {
		//TODO
		for (int i = 0; i < n; i++) {
			history.add(currentState);
			currentState = future.pop();
			moves.add(undoneMoves.pop());
		}
		loadConfiguration(currentState);
		return;
	}
	
	/**
	 * Returns an ArrayList of Commands given Square strings
	 * 
	 * @param piece Piece to move
	 * @param destSquares ArrayList of destination squares
	 * @return ArrayList of Commands
	 */
	public ArrayList<Command> GenerateMoves(Piece piece, ArrayList<Square> destSquares){
		ArrayList<Command> commands = new ArrayList<Command>();
		for (Square s : destSquares) {
			commands.add(new Command(piece, piece.getSquare(), s));
		}
		return commands;
	}
	
	/**
	 * Overloads above method without requiring references
	 * 
	 * Used for copying a set of commands from one board to a new copy
	 * 
	 * @param originSquare String representation of origin
	 * @param destSquares ArrayList of destination squares
	 * @return ArrayList of Commands
	 */
	public ArrayList<Command> GenerateMoves(String originSquare, ArrayList<String> destSquares){
		ArrayList<Command> commands = new ArrayList<Command>();
		Square origin = squares.get(originSquare);
		Square destination;
		for (String s : destSquares) {
			destination = squares.get(s);
			commands.add(new Command(origin.getPiece(), origin, destination));
		}
		return commands;
	}
	
	/**
	 * Formats commands that may have been written for different Board
	 * 
	 * Takes the Command fields that aren't explicit Square or Piece references
	 * (String) and changes the fields that are to match the current board
	 * Also used after the Board loads a new configuration with new pieces
	 * 
	 * @param unformatted Command
	 * @return formatted Command
	 */
	public Command formatCommand(Command unformatted) {

		return new Command(unformatted, this);
	}
	
	/**
	 * Checks if player's king is in check
	 * 
	 * Ignores opponent's King
	 * 
	 * @param color Color of player to move
	 * 
	 * @return true if player's King is in check
	 */
	public boolean KingInCheck(Color color) {
		King k = new King();
		ArrayList<Piece> kingsPieces;
		kingsPieces = (color == Color.WHITE)? whitePieces : blackPieces;
		for (Piece p : kingsPieces) {
			if (p.getPieceName() == PieceName.KING) {
				k = (King)p;
				break;
			}
		}
		
		return squareUnderAttack(color, k.getSquare());
	}
	
	/**
	 * Checks if square is under attack by the other side
	 * 
	 * @param color of Player who's being attacked
	 * @param s Square to check
	 * 
	 * @return boolean true if under attack, else false
	 */
	public boolean squareUnderAttack(Color color, Square s) {
		ArrayList<Piece> opponents;
		ArrayList<Square> moves;
		opponents = (color == Color.WHITE)? this.blackPieces : this.whitePieces;
		for (Piece p : opponents) {
			moves = p.getAttacking();	//fix fatal castling errors
			for (Square square : moves) {
				if (square.equals(s)) { 
					return true;			//slightly faster code
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if specified command is a legal move
	 * 
	 * @param command to be checked
	 * @return true if command is legal, else false
	 */
	public boolean isLegalCommand(Command command) {
		if (command == null || command.piece == null || command.destination == null) {
			return false;
		}
		return command.piece.getLegalMoves().contains(command.destination);
	}
	
	/**
	 * Evaluates if the Game is over and updates mateFlag
	 * <p>
	 * Updates the value of the mateFlag. mateFlag is: <br>
	 * 0 if the game is running<br>1 if white won<br>2 if black won<br>
	 * 3 if there is a stalemate<br> 4 if a draw by 50 move rule<br>5 if a draw due to insufficient
	 * mating material
	 * 
	 * @param sideToMove Color of the player to move
	 * @return mateFlag detailing status
	 */
	public int isGameOver(Color sideToMove) {
		if (moveCount >= 100) {	//100 plies = 50 moves
			mateFlag = 4;
			return 4;
		}
		int sum = 0, pawnCount = 0;
		for (Piece p : pieces) {
			sum += p.getValue();
			if (p instanceof Pawn) {pawnCount++;}
		}
		if (sum == 138 || (sum == 141 && pawnCount == 0)) {mateFlag = 5; return 5;}
		ArrayList<Piece> moving = (sideToMove == Color.WHITE)? whitePieces : blackPieces;
		ArrayList<Square> legalMoves = new ArrayList<Square>();
		for (Piece p : moving) {
			legalMoves.addAll(p.getLegalMoves());
			if (legalMoves.size() > 0) {
				mateFlag = 0;
				return 0;
			}
		}
		//only reaches this point if no legal moves
		if (KingInCheck(sideToMove)) {
			mateFlag = (sideToMove == Color.WHITE)? 2 : 1;	//2 if black wins (white in checkmate)
		}													//1 if white wins (black in checkmate)
		else {
			mateFlag = 3;	//stalemate
		}
		
		return mateFlag;
	}
	
	@Override
	public Board clone() {
		Board newBoard = new Board(this.currentState);
		for (Configuration c : history) {
			newBoard.history.add(c.clone());
		}
		return newBoard;
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
