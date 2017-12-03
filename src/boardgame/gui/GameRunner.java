package boardgame.gui;

import java.awt.FlowLayout;

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
		
		//StartMenuGUI s = new StartMenuGUI();
		Board b = new Board();
		GUI cb = new GUI(b);
		//MainFrame mf = new MainFrame(cb);
		Human white = new Human("Muneeb", Color.WHITE, cb);
		Human black = new Human("Not", Color.BLACK, cb);
		ChessGame game = new ChessGame(white, black);
		game.setUi(cb);
		game.setBoard(b);
		
//		mf.setVisible(true);
//		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		mf.pack();
//		mf.setMinimumSize(mf.getSize());
		Thread t1 = new Thread(game);
		t1.start();
		//while( /*game is still running*/ ) {
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Thread dead");
		System.out.println("Thread dead");
		
		
				
		//EndMenuGUI e = new EndMenuGUI();
	}

}
