package boardgame.gui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;

public class CustomScroller extends JScrollPane {

	public CustomScroller() {
		// TODO Auto-generated constructor stub
	}

	public CustomScroller(Component view) {
		super(view);
		// TODO Auto-generated constructor stub
	}

	public CustomScroller(int vsbPolicy, int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
		// TODO Auto-generated constructor stub
	}

	public CustomScroller(Component view, int vsbPolicy, int hsbPolicy) {
		super(view, vsbPolicy, hsbPolicy);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 100);
	}

}
