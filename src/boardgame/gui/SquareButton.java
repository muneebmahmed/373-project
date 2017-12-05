package boardgame.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.*;


import boardgame.pieces.*;
import boardgame.data.*;

/*
 * This class should have buttons for squares
 * These buttons will not be moving, but will need to be redrawn
 */
public class SquareButton extends JButton {

	public Square source;		//The Square the button points to
	public int paintCount;		//when to repaint
	
	public static final java.awt.Color GREEN = new java.awt.Color(0, 90, 45);
	public static final java.awt.Color LIGHTER_GREEN = new java.awt.Color(0, 150, 75);
	public static final java.awt.Color GREY = new java.awt.Color(200, 200, 200);
	public static final java.awt.Color BLUE = new java.awt.Color(124, 215, 250);
	public static final java.awt.Color DARKER_BLUE = new java.awt.Color(100, 170, 200);
	
	public SquareButton() {
		// TODO Auto-generated constructor stub
	}

	public SquareButton(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public SquareButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public SquareButton(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public SquareButton(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}
	
	public SquareButton(Square square) {
		super();
		source = square;
		java.awt.Color bg = (square.getColor() == Color.WHITE)? java.awt.Color.WHITE : GREEN;
		this.setBackground(bg);
		paintCount = 0;
		this.setIcon(getIconFromPiece(square.getPiece()));
	}
	
	public Square getSquare() {
		return source;
	}
	
	public void setSquare(Square square) {
		source = square;
	}
	
	public Piece getPiece() {
		return source.getPiece();
	}
	
	public void updateIcon() {
		this.setIcon(getIconFromPiece(source.getPiece()));
	}
	
	public void resetBackground() {
		java.awt.Color bg = (source.getColor() == Color.WHITE)? java.awt.Color.WHITE : GREEN;
		this.setBackground(bg);
	}
	
	public void emphasizeBackground() {
		java.awt.Color bg = (source.getColor() == Color.WHITE)? GREY : LIGHTER_GREEN;
		this.setBackground(bg);
	}
	
	public void setBlue() {
		switch (source.getColor()) {
		case WHITE:
			this.setBackground(BLUE);
			break;
		case BLACK:
		default:
			this.setBackground(DARKER_BLUE);
			break;
		}
	}
	
	public static ImageIcon getIconFromPiece(Piece piece) {
		if (piece == null) {
			ImageIcon image = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
			return image;
		}
		String color = piece.getColor().toString();
		String name = piece.getPieceName().toString();
		String filepath = "images/" + color + " "+ name + ".png";
		ImageIcon icon = new ImageIcon(filepath);
		Image unscaled = icon.getImage();
		Image scaled = unscaled.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaled);
		return icon;
	}
	
	public static SquareButton createSquareButton(Square square) {
		return new SquareButton(square);
	}

}
