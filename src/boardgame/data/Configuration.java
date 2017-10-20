package boardgame.data;

import java.util.*;
import boardgame.pieces.*;

/**
 * @author Muneeb Ahmed
 * <p>
 * Represents the state of the board at any given time<br>
 * ArrayLists for pieces detailing square, color, and other flags and data
 */
public class Configuration implements Cloneable {

	public ArrayList<ConfigElement> elements;
	
	/*
	 * New class will have data:
	 * PieceName pieceName (which piece)
	 * Color color (which side)
	 * String square (string representation of square);
	 * Other flags for castlers and pawns?
	 * Should Kings and Rooks implement a Castler interface?
	 */
	public class ConfigElement {
		private PieceName name;
		private Color color;
		private String square;
		private boolean flags;	//castling and en passant
		private int moveCount;
		private String enPassantSquare;
		
		public ConfigElement() {
			name = PieceName.PAWN;
			color = Color.WHITE;
			square = "a1";
			flags = false;
			moveCount = 0;
			enPassantSquare = null;
		}
		
		public ConfigElement(Piece p) {
			name = p.getPieceName();
			color = p.getColor();
			square = p.getSquare().toString();
			flags = p.getSpecialFlags();
			moveCount = p.getMoveCount();
			enPassantSquare = p.getEnPassant();
		}
		
		public ConfigElement(ConfigElement element) {
			name = element.getName();
			color = element.getColor();
			square = element.getSquare();
			flags = element.isFlags();
			moveCount = element.getMoveCount();
			enPassantSquare = element.getEnPassantSquare();
		}
		
		public ConfigElement(Color color, PieceName name, String square) {
			this();
			this.color = color;
			this.name = name;
			this.square = square;
		}

		public PieceName getName() {
			return name;
		}

		public void setName(PieceName name) {
			this.name = name;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		public String getSquare() {
			return square;
		}

		public void setSquare(String square) {
			this.square = square;
		}

		public boolean isFlags() {
			return flags;
		}

		public void setFlags(boolean flags) {
			this.flags = flags;
		}

		public int getMoveCount() {
			return moveCount;
		}

		public void setMoveCount(int moveCount) {
			this.moveCount = moveCount;
		}

		public String getEnPassantSquare() {
			return enPassantSquare;
		}

		public void setEnPassantSquare(String enPassantSquare) {
			this.enPassantSquare = enPassantSquare;
		}

	}

	
	public Configuration() {
		elements = new ArrayList<ConfigElement>();
	}
	
	/*
	 * Generates configuration based on board
	 */
	public Configuration(Board b) {
		this();
		ArrayList<Piece> pieces = b.getPieces();
		for (Piece p : pieces) {
			elements.add(new ConfigElement(p));
		}
	}
	
	public void setNewConfig(Board b) {
		elements.clear();
		ArrayList<Piece> pieces = b.getPieces();
		for (Piece p : pieces) {
			elements.add(new ConfigElement(p));
		}
	}
	
	public ArrayList<ConfigElement> getElements(){
		return elements;
	}
	
	@Override
	public Configuration clone() {
		Configuration config = new Configuration();
		for (ConfigElement e : elements) {
			config.elements.add(new ConfigElement(e));
		}
		return config;
	}
	
}
