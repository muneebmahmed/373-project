package boardgame.play;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import boardgame.play.*;

public class StartMenuGUI {
	
	private JFrame startFrame;
	private JButton startGameButton;
	private JButton rulesButton;
	private JTextArea welcomeMessage;
	private GUIRunner guiRunner;
	private RulesGUI rules;
	
	public StartMenuGUI() {
		super();
		
		this.startFrame = new JFrame("Play Chess!");
		this.startGameButton = new JButton("Start Game!");
		this.rulesButton = new JButton("Rules");
		this.welcomeMessage = new JTextArea("Welcome to Chess");
		
		startFrame.setSize(200, 200);
		startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		startFrame.setLayout(new FlowLayout());
		
		buildStartGUI();
		
		//startFrame.setVisible(true);

	}
	
	public GUIRunner getGUIRunner() {
		return this.guiRunner;
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
				
				guiRunner.main(null);
				startFrame.dispose();
			
			}
			if(source.equals(rulesButton)) {
				
				rules = new RulesGUI();
				startFrame.dispose();
				
			}	
			
		}

	}
	

	
	

}
