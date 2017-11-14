package boardgame.gui;

import javax.swing.*;


import boardgame.pieces.*;
import boardgame.data.*;

/*
 * This class should have buttons for destination squares
 * They should show up as highlights over the original squares
 * Ideally, they will be in a new layer over the board
 */
public class SquareButton extends JButton {

	public Square source;		//The Square the button points to
	
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

}
