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
	private RulesGUI rules;
	
	private volatile Board b;
	private volatile GUI cb;
	private volatile ChessGame game;
	public volatile boolean fire;
	private Thread t1;
	int mode = 0, players = 0;
	
	public StartMenuGUI() {
		// TODO Auto-generated constructor stub
		super();
		
		this.startFrame = new JFrame("Play Chess!");
		this.startGameButton = new JButton("Start Game!");
		this.rulesButton = new JButton("Rules");
		this.welcomeMessage = new JTextArea("Welcome to Chess");
		
		startFrame.setSize(200, 200);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setLayout(new FlowLayout());
		
		buildStartGUI();
	}
	
	public synchronized void waitForStuff() {
		while (!fire) {
			try {
				wait();
			}catch (InterruptedException e) {
				
			}
			runGame();
		}
	}
	
	public synchronized void setFire(boolean f) {
		fire = f;
		notifyAll();
	}
	
	public void runGame() {
		t1 = new Thread(game);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		startFrame.setVisible(true);
		fire = false;
	}
	
	public void buildStartGUI() {
		
		startGameButton.addActionListener(new StartListener());
		rulesButton.addActionListener(new StartListener());
		
		startFrame.add(welcomeMessage);
		startFrame.add(startGameButton);
		startFrame.add(rulesButton);
		
		startFrame.setVisible(true);
		
	}
	
	private class StartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{

			JButton source = (JButton)(e.getSource());

			if(source.equals(startGameButton))
			{
				//String[] arguments = {"not an Argument"};
				//GUIRunner.main(arguments);
				startFrame.setVisible(false);
				//startFrame.dispose();
				
				b = new Board();
				cb = new GUI(b);
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
				game = new ChessGame(mode);
				game.setUi(cb);
				game.setBoard(b);
				setFire(true);
			}
			if(source.equals(rulesButton)) {
				
				//makeRuleFrame();
				rules = new RulesGUI();
				
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
