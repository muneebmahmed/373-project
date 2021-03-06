package boardgame.data;

import java.util.*;
import boardgame.pieces.*;

/**
 * Represents the state of the board at any given time
 * <p>
 * Has ArrayLists for pieces detailing square, color, and other flags and data
 * @author Muneeb Ahmed
 * 
 */
public class Configuration implements Cloneable {

	public ArrayList<ConfigElement> elements;
	public int plyCount;
	
//	public long whiteKing;
//	public long whiteQueen;
//	public long whiteRooks;
//	public long whiteBishops;
//	public long whiteKnights;
//	public long whitePawns;
//	public long blackKing;
//	public long blackQueen;
//	public long blackRooks;
//	public long blackBishops;
//	public long blackKnights;
//	public long blackPawns;
//	public long specialFlags;
	
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
			if ((name == PieceName.KING || name == PieceName.ROOK) && (square.equals("h1") || square.equals("e1"))) {
				flags = true;
			}
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
		plyCount = 0;
	}
	
	/*
	 * Generates configuration based on board
	 */
	public Configuration(Board b) {
		this();
		ArrayList<Piece> pieces = b.getPieces();
		for (Piece p : pieces) {
			elements.add(new ConfigElement(p));
			//addPiece(p);
		}
		plyCount = b.getMoveCount();
	}
	
	public static byte getOffset(Square s) {
		byte offset = 0;
		offset += s.getColumn();
		offset += s.getRow()*8;
		return offset;
	}
	
//	public void addPiece(Piece p) {
//		byte offset = getOffset(p.getSquare());
//		if (p.getColor() == Color.WHITE) {
//			switch (p.getPieceName()) {
//			case KING:
//				whiteKing |= (1 << offset);
//				break;
//			case QUEEN:
//				whiteQueen |= (1 << offset);
//				break;
//			case ROOK:
//				whiteRooks |= (1 << offset);
//				break;
//			case BISHOP:
//				whiteBishops |= (1 << offset);
//				break;
//			case KNIGHT:
//				whiteKnights |= (1 << offset);
//				break;
//			case PAWN:
//				whitePawns |= (1 << offset);
//				break;
//			default:
//				break;
//			}
//		}
//		else {
//			switch (p.getPieceName()) {
//			case KING:
//				blackKing |= (1 << offset);
//				break;
//			case QUEEN:
//				blackQueen |= (1 << offset);
//				break;
//			case ROOK:
//				blackRooks |= (1 << offset);
//				break;
//			case BISHOP:
//				blackBishops |= (1 << offset);
//				break;
//			case KNIGHT:
//				blackKnights |= (1 << offset);
//				break;
//			case PAWN:
//				blackPawns |= (1 << offset);
//				break;
//			default:
//				break;
//			}
//		}
//		if (p.getSpecialFlags()) { specialFlags |= (1 << offset); }
//	}
	
	public void setNewConfig(Board b) {
		elements.clear();
		ArrayList<Piece> pieces = b.getPieces();
		for (Piece p : pieces) {
			elements.add(new ConfigElement(p));
		}
		plyCount = b.getMoveCount();
	}
	
	public ArrayList<ConfigElement> getElements(){
		return elements;
	}
	
	public int getPlyCount() {
		return plyCount;
	}
	
	@Override
	public Configuration clone() {
		Configuration config = new Configuration();
		for (ConfigElement e : elements) {
			config.elements.add(new ConfigElement(e));
		}
//		config.plyCount = plyCount;
//		config.whiteKing = whiteKing;
//		config.whiteQueen = whiteQueen;
//		config.whiteRooks = whiteRooks;
//		config.whiteBishops = whiteBishops;
//		config.whiteKnights = whiteKnights;
//		config.whitePawns = whitePawns;
//		config.blackKing = blackKing;
//		config.blackQueen = blackQueen;
//		config.blackRooks = blackRooks;
//		config.blackBishops = blackBishops;
//		config.blackKnights = blackKnights;
//		config.blackPawns = blackPawns;
//		config.specialFlags = specialFlags;
		
		return config;
	}
	
}
