package boardgame.play;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import boardgame.pieces.*;
import boardgame.data.*;

public class GUIRunner {

	public GUIRunner() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable r = new Runnable() {

			@Override
			public void run() {
				Board testBoard = new Board();
				GUI cb = new GUI(testBoard);
				cb.setVisible(false);
            	
				JFrame f = new JFrame("Chess");
				f.add(cb.getGui());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setLocationByPlatform(true);

				// ensures the frame is the minimum size it needs to be
				// in order display the components within it
				f.pack();
				// ensures the minimum size is enforced.
				f.setMinimumSize(f.getSize());
				f.setVisible(true);
			}
		};
		SwingUtilities.invokeLater(r);
	}

}
