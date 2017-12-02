package boardgame.play;

import java.awt.FlowLayout;

import javax.swing.JFrame;

import boardgame.gui.Console;
import boardgame.pieces.Board;
import boardgame.play.*;


public class GameRunner {
	
	
	public static void main(String[] args) {
		
		JFrame outputFrame = new JFrame();
		
		Console c = new Console();
		
		outputFrame.setLayout(new FlowLayout());
		outputFrame.add(c.getTextArea());
		outputFrame.setSize(300, 200);
		outputFrame.setVisible(true);
		System.out.println("Test");
		
		StartMenuGUI s = new StartMenuGUI();
		
		
		//EndMenuGUI e = new EndMenuGUI();
		
	}

}
