package boardgame.gui;

import boardgame.data.*;
import boardgame.play.*;
import boardgame.pieces.*;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class StartMenuGUI {
	
	private JFrame startFrame;
	private JButton startGameButton;
	private JButton rulesButton;
	private JTextArea welcomeMessage;
	
	public StartMenuGUI() {
		// TODO Auto-generated constructor stub
		super();
		
		this.startFrame = new JFrame("Play Chess!");
		this.startGameButton = new JButton("Start Game!");
		this.rulesButton = new JButton("Rules");
		this.welcomeMessage = new JTextArea("Welcome to Chess");
		
		startFrame.setSize(200, 200);
		startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		startFrame.setLayout(new FlowLayout());
		
		buildStartGUI();
	}
	
	public void buildStartGUI() {
		
		startGameButton.addActionListener(new ButtonListener());
		rulesButton.addActionListener(new ButtonListener());
		
		startFrame.add(welcomeMessage);
		startFrame.add(startGameButton);
		startFrame.add(rulesButton);
		
		startFrame.setVisible(true);
		
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{

			JButton source = (JButton)(e.getSource());

			if(source.equals(startGameButton))
			{
				String[] arguments = {"not an Argument"};
				GUIRunner.main(arguments);
				startFrame.setVisible(false);
			
			}
			if(source.equals(rulesButton)) {
				
				makeRuleFrame();
				
			}	
			
		}

	}
	
	public void makeRuleFrame() {
		
		JFrame rules = new JFrame();
		JTextArea link = new JTextArea("https://en.wikipedia.org/wiki/Rules_of_chess");//FIXME ----- insert rules here
		JLabel rulesMessage = new JLabel("Follow the Link for the Rules!");


		rules.add(rulesMessage);
		rules.add(link);
		
		rules.setLayout(new FlowLayout());
		rules.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		rules.setSize(300, 200);
		rules.setVisible(true);
		
		
	}

}
