package boardgame.pieces;

import java.lang.*;
import java.util.*;

import boardgame.pieces.Color;

public class Square {
	Color color;
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
	
	//more setters and getters
	
	public boolean hasPiece() {
		return false;
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
