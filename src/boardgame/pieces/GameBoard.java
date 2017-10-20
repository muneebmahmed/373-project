package boardgame.pieces;

import java.util.*;
import boardgame.data.*;


/*
 * Can be superclass for Board
 * 
 */
public class GameBoard {
	ArrayList<Piece> pieces;
	protected Square[][] board;
	HashMap<String, Square> squares;
	
	public GameBoard() {
		pieces = new ArrayList<Piece>();
		squares = new HashMap<String, Square>();
	}
	
	/*
	 * Returns all squares in same rank as given square
	 * 
	 * @return ArrayList of all squares in same row as s, except s
	 */
	public ArrayList<Square> getRank(Square s){
		ArrayList<Square> rank = new ArrayList<Square>();
		int row = s.getRow();
		for (Square square : board[row]) {
			if (!s.equals(square)) {
				rank.add(square);
			}
		}
		return rank;
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
		ArrayList<Square> file = new ArrayList<Square>();
		int col = s.getColumn();
		for (int i = 0; i < board.length; i++) {
			Square square = board[i][col];
			if (!s.equals(square)) {
				file.add(square);
			}
		}
		return file;
	}
	
	/*
	 * Returns all squares in given file
	 * 
	 * @return all squares in file
	 */
	public ArrayList<Square> getFile(char file){
		ArrayList<Square> column = new ArrayList<Square>();
		String sqName = file + Integer.toString(1);
		Square first = squares.get(sqName);
		column.add(first);
		column.addAll(getFile(first));
		return column;
	}

}
