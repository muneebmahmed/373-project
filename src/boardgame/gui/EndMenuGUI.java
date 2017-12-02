package boardgame.gui;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.*;

public class EndMenuGUI {

	private JFrame endFrame;
	private JButton mainMenuButton;
	private JButton quitButton;
	private JTextArea gameOverMessage;
	
	public EndMenuGUI() {
		// TODO Auto-generated constructor stub
		super();
		
		this.endFrame = new JFrame("Game Over!");
		this.mainMenuButton = new JButton("Main Menu");
		this.quitButton = new JButton("Quit");
		this.gameOverMessage = new JTextArea("Game Over!");
		
		endFrame.setSize(150, 150);
		endFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		endFrame.setLayout(new FlowLayout());
		
		buildEndGUI();
		
		//startFrame.setVisible(true);
	}
	
	public void buildEndGUI() {
		
		mainMenuButton.addActionListener(new ButtonListener());
		quitButton.addActionListener(new ButtonListener());
		
		endFrame.add(gameOverMessage);
		endFrame.add(mainMenuButton);
		endFrame.add(quitButton);
		
		endFrame.setVisible(true);
		
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{

			JButton source = (JButton)(e.getSource());

			if(source.equals(mainMenuButton))
			{
				
				GameRunner.main(null);
				endFrame.dispose();
			
			}
			if(source.equals(quitButton)) {
				
				endFrame.dispose();
				
			}	
			
		}

	}

}
