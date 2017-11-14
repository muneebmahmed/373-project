package boardgame.gui;

import java.awt.*;

import javax.swing.*;

import boardgame.pieces.*;

@SuppressWarnings("serial")
public class PieceButton extends JButton {
	
	public Piece source;
	
	public PieceButton() {
		// TODO Auto-generated constructor stub
	}

	public PieceButton(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public PieceButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public PieceButton(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public PieceButton(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}
	
	public static PieceButton createPieceButton(Piece piece) {
		String color = piece.getColor().toString();
		String name = piece.getPieceName().toString();
		String filepath = "images/" + color + " "+ name + ".png";
		ImageIcon icon = new ImageIcon(filepath);
		Image unscaled = icon.getImage();
		Image scaled = unscaled.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaled);
		PieceButton newButton = new PieceButton(icon);
		newButton.source = piece;
		return newButton;
	}
	
	@Override
	public Dimension getPreferredSize() {
		Container c = this.getParent();
		if (c == null) {
			return new Dimension(50, 50);
		}
		Dimension d = c.getSize();
		int height = (int)d.getHeight();
		height /= 9;
		return new Dimension(height, height);
	}

}
