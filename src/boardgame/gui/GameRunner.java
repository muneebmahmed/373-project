package boardgame.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import boardgame.pieces.*;
import boardgame.play.*;

public class GameRunner {

	public GameRunner() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

//		JFrame outputFrame = new JFrame();
//		Console c = new Console();
//		outputFrame.setLayout(new FlowLayout());
//		outputFrame.add(c);
//		outputFrame.setSize(300, 200);
//		outputFrame.setVisible(true);
//		System.out.println("Test");
		PrintStream systemOut = System.out;
		
		StartMenuGUI s = new StartMenuGUI();
		Runnable x = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				s.waitForStuff();
			}
			
		};
		Thread t2 = new Thread(x);
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//s.waitForStuff();
		//PieceName asdf = Promoter.getPromotionPiece(Color.WHITE);
		Board b = new Board();
		GUI cb = new GUI(b);
		cb.setMaximumSize(new Dimension(400, 400));
		int mode = 0, players = 0;
		players = JOptionPane.showConfirmDialog(null, "Is white a Human?");
		if (players == JOptionPane.YES_OPTION) {
			mode = 0;
		}
		else {
			mode = 2;
		}
		players = JOptionPane.showConfirmDialog(null, "Is black human?");
		if (players != JOptionPane.YES_OPTION) {
			mode++;
		}
		//MainFrame mf = new MainFrame(cb);
		ChessGame game = new ChessGame(mode);
		game.setUi(cb);
		game.setBoard(b);
		
		Thread t1 = new Thread(game);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Thread dead");
		System.out.println("Thread dead");
		System.exit(0);
		
				
		//EndMenuGUI e = new EndMenuGUI();
	}

}
