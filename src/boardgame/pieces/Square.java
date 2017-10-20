package boardgame.pieces;

import java.lang.*;
import java.util.*;

import boardgame.pieces.Color;

/**
 * 
 * @author Muneeb Ahmed
 *
 */
public class Square {
	private Color color;
	private char file;
	private int rank;
	private int row;
	private int column;
	private Piece piece;
	private String name;
	
	public int isProtected; //set and reset each turn, useful for computer moves
	public static final String alphabet = "abcdefgh";
	
	public Square() {
		color = Color.WHITE;
		rank = 0;
		file = 'z';
		row = 0;
		column = 0;
		name = "z0";
		piece = null;
	}
	
	public Square(Color colour, char file, int rank) {
		color = colour;
		this.file = file;
		this.rank = rank;
		row = rank - 1;
		column = alphabet.indexOf(file);
		name = file + Integer.toString(rank);
		piece = null;
	}
	
	public Square(Color colour, int row, int column) {
		color = colour;
		this.row = row;
		this.column = column;
		rank = row + 1;
		file = alphabet.charAt(column);
		name = file + Integer.toString(rank);
		piece = null;
	}
	
	public void setPiece(Piece piece) {
		if (this.piece != null) {
			this.piece.setSquare(null); //haven't implemented in Piece yet
		}
		this.piece = piece;
		if (piece != null && !(this.equals(piece.getSquare()))){
			this.piece.setSquare(this);
		}
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public char getFile() {
		return file;
	}

	public void setFile(char file) {
		this.file = file;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean hasPiece() {
		return (piece != null);
	}
	
	public boolean isEdge() {
		return (file == 'a' || file == 'h' || rank == 1 || rank == 8);
	}
	
	
	@Override
	public boolean equals(Object s) {
		if (s == null || !(s instanceof Square)) {
			return false;
		}
		Square square = (Square)s;
		if (name.equals(square.name)){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
