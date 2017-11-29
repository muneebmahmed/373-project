package boardgame.play;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import boardgame.pieces.*;
import boardgame.data.*;
import boardgame.play.*;


public class GUIRunner {
	
	

	public GUIRunner() {
		// TODO Auto-generated constructor stub
	}
	
	volatile static Command c;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		
		Board testBoard = new Board();
		//GUI cb = new GUI(testBoard);
		
		
		Runnable r = new Runnable() {

			@Override
			public void run() {
				//Board testBoard = new Board();
				GUI cb = new GUI(testBoard);
				cb.setVisible(true);
				cb.setMinimumSize(new Dimension(576, 576));
				cb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	
				//JFrame f = new JFrame("Chess");
				//f.add(cb.getGui());
				//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//f.setLocationByPlatform(true);

				// ensures the frame is the minimum size it needs to be
				// in order display the components within it
				//f.pack();
				// ensures the minimum size is enforced.
				//f.setMinimumSize(f.getSize());
				//f.setVisible(true);
				//String input = JOptionPane.showInputDialog("Hello");
				//Command move = new Command(Color.WHITE, "e4", testBoard);
				//testBoard.Move(move);
				//cb.resetSquareIcons();
				//cb.repaint();
//				Human white = new Human("White", Color.WHITE, cb), black = new Human("Black", Color.BLACK, cb);
//				int i = 0;
//				Player [] sides = { white, black };
//				Player current = sides[i];
//				while (testBoard.isGameOver(current.getColor()) == 0) {
//					System.out.println("In while loop: " + i);
//					cb.toMove = current.getColor();
//					c = null;
//					/*
//					Thread getCommandThread = new Thread() {
//					      public void run() {
//					        @SuppressWarnings("unused")
//							c = cb.getCommand(current, testBoard);
//					      }
//					    };
//					    getCommandThread.start();
//					while (c == null) {
//						try {
//							Thread.sleep(1000);
//						}catch (InterruptedException e) { }
//					}
//					*/
//					c = cb.getCommand(current, testBoard);
//					testBoard.Move(c);
//					testBoard.updateState(c);
//					cb.resetSquareIcons();
//					cb.repaint();
//					i++;
//					current = sides[i%2];
//				}
			}
			
		};
		SwingUtilities.invokeLater(r);
		
		
	}

}
