package boardgame.play;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import boardgame.data.Command;
import boardgame.pieces.*;

public class GUI extends JFrame implements UserInterface {

	//add JPanel and components here
	
	private volatile Piece moving;
	private volatile Square origin;
	private volatile Square destination;
	
	public GUI() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super("Chess");
		setSize(1000, 1000);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		moving = null;
		origin = null;
		destination = null;
		
		setVisible(true);
	}

	public GUI(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public GUI(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public GUI(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
	
	//In the JPanel class, use this code to always keep the board square:
	//Note: the parent component (JFrame) must use GridBagLayout
	
//	@Override
//	public Dimension getPreferredSize() {
//		Dimension d = super.getPreferredSize();
//		Container c = this.getParent();
//		if (c == null) {
//			return new Dimension(100, 100);
//		}
//		d = c.getSize();
//		int w = (int)d.getWidth();
//		int h = (int)d.getHeight();
//		int s = (w < h)? w : h;
//		return new Dimension(s, s);
//	}

	@Override
	public Command getCommand(Player player, Board b) {
		// TODO Auto-generated method stub
		destination = null;
		
		/*
		 * The following code waits until the player selects a move
		 * The player should be able to select a piece and destination with the GUI
		 * The action listeners associated with the pieces will execute on a different thread
		 * Therefore, the current thread should sleep to not waste resources
		 */
		while (destination == null) {
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				//do stuff?
			}
		}
		//System.out.println("Loop exited");
		return new Command(moving, origin, destination);
	}

}
