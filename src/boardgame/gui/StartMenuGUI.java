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
	private JButton exit;
	private JLabel welcomeMessage;
	private JLabel message2;
	private RulesGUI rules;
	
	private JFrame sideChooser;
	private JComboBox<String> whiteSide;
	private JComboBox<String> blackSide;
	private JTextField whiteName;
	private JTextField blackName;
	private JButton play;
	private JButton cancel;
	private JPanel buttonsPanel;
	private JPanel textsPanel1;
	private JPanel textsPanel2;
	private JLabel whiteLabel;
	private JLabel blackLabel;
	
	private volatile Board b;
	private volatile GUI cb;
	private volatile ChessGame game;
	public volatile boolean fire;
	private Thread t1;
	
	public StartMenuGUI() {
		// TODO Auto-generated constructor stub
		super();
		
		this.startFrame = new JFrame("Play Chess!");
		this.startGameButton = new JButton("Start New Game");
		this.rulesButton = new JButton("Rules");
		exit = new JButton("Close");

		welcomeMessage = new JLabel("Welcome to the Chess Launcher!");
		message2 = new JLabel("Click below to start a new game or learn the rules");
		
		startFrame.setSize(320, 200);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setLayout(new FlowLayout());
		//startFrame.setLocationRelativeTo(null);
		
		buildStartGUI();
	}
	
	public synchronized void waitForNewGame() {
		while (!fire) {
			try {
				wait();
			}catch (InterruptedException e) {
				
			}
			runGame();
			if (game.immediateStart)
				buildSideChooser();
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
		if (!game.immediateStart)
		startFrame.setVisible(true);
		fire = false;
	}
	
	public void buildStartGUI() {
		
		startGameButton.addActionListener(new StartListener());
		rulesButton.addActionListener(new StartListener());
		exit.addActionListener(new StartListener());
		
		startFrame.add(welcomeMessage);
		startFrame.add(message2);
		startFrame.add(startGameButton);
		startFrame.add(rulesButton);
		startFrame.add(exit);
		
		startFrame.setVisible(true);
		
	}
	
	public void buildSideChooser() {
		String[] option = {"Human", "Computer"};
		sideChooser = new JFrame("Determine Players and Names");
		whiteSide = new JComboBox<String>(option);
		whiteSide.setEditable(false);
		blackSide = new JComboBox<String>(option);
		blackSide.setEditable(false);
		whiteName = new JTextField(20);
		blackName = new JTextField(20);
		play = new JButton("Play!");
		cancel = new JButton("Cancel");
		play.addActionListener(new StartListener());
		cancel.addActionListener(new StartListener());
		
		whiteLabel = new JLabel("White: ");
		blackLabel = new JLabel("Black: ");
		buttonsPanel = new JPanel();
		textsPanel1 = new JPanel();
		textsPanel2 = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		textsPanel1.setLayout(new FlowLayout());
		textsPanel2.setLayout(new FlowLayout());
		buttonsPanel.add(cancel);
		buttonsPanel.add(play);
		textsPanel1.add(whiteLabel);
		textsPanel1.add(whiteSide);
		textsPanel1.add(whiteName);
		textsPanel2.add(blackLabel);
		textsPanel2.add(blackSide);
		textsPanel2.add(blackName);
		//JLabel instruction = new JLabel("<HTML><center>Enter the name of each player</center></HTML>");
		JLabel instruction = new JLabel("Enter the name of each player", SwingConstants.CENTER);
		sideChooser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sideChooser.addWindowListener(new SideListener());
		sideChooser.setLayout(new GridLayout(4, 1));
		sideChooser.setSize(500, 200);
		sideChooser.setLocationRelativeTo(null);
		
		sideChooser.add(instruction);
		sideChooser.add(textsPanel1);
		sideChooser.add(textsPanel2);
		sideChooser.add(buttonsPanel);
		
		sideChooser.setVisible(true);
		return;
	}
	
	private class StartListener implements ActionListener {
		
		//this is the method MenuListener must implement, as it comes from the ActionListener interface.
		public void actionPerformed(ActionEvent e) {

			JButton source = (JButton)(e.getSource());

			if(source.equals(startGameButton)) {
				//String[] arguments = {"not an Argument"};
				//GUIRunner.main(arguments);
				startFrame.setVisible(false);
				buildSideChooser();
				//startFrame.dispose();
				
				
//				int mode = 0, players = 0;
//				players = JOptionPane.showConfirmDialog(null, "Is a person (not a computer) playing white?");
//				if (players == JOptionPane.YES_OPTION) {
//					mode = 0;
//				}
//				else {
//					mode = 2;
//				}
//				players = JOptionPane.showConfirmDialog(null, "Is a person (not a computer) playing black?");
//				if (players != JOptionPane.YES_OPTION) {
//					mode++;
//				}
				
			}
			if(source.equals(rulesButton)) {
				rules = new RulesGUI();
			}
			if(source.equals(cancel)) {
				startFrame.setVisible(true);
				sideChooser.dispose();
			}
			if (source.equals(play)) {
				int mode = 0;

				mode = whiteSide.getSelectedItem().equals("Human")? 0 : 2;

				if (!blackSide.getSelectedItem().equals("Human")) {
					mode++;
				}
				String wName = whiteName.getText();
				String bName = blackName.getText();
				sideChooser.dispose();
				
				b = new Board();
				cb = new GUI(b);
				cb.setMaximumSize(new Dimension(400, 400));
				
				game = new ChessGame(mode);
				if (bName.equals("") && (mode == 1 || mode == 3)) {
					bName = "Black Computer";
				}
				if (wName.equals("") && (mode == 2 || mode == 3)) {
					wName = "White Computer";
				}
				if (wName.equals("") && (mode == 0 || mode == 1)) {
					game.hasNames = false;
				}
				else if (bName.equals("") && (mode == 0 || mode == 2)) {
					game.hasNames = false;
				}
				else {
					game.hasNames = true;
					game.whiteName = wName;
					game.blackName = bName;
				}
				if (mode > 3) {
					//get address
				}
				game.setUi(cb);
				game.setBoard(b);
				setFire(true);
			}
			if (source.equals(exit)) {
				System.exit(0);
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
	
//	private class ComboListener implements ItemListener {
//
//		@Override
//		public void itemStateChanged(ItemEvent e) {
//			// TODO Auto-generated method stub
//			@SuppressWarnings("unchecked")
//			JComboBox<String> source = (JComboBox<String>)e.getSource();
//			String message = (String)source.getSelectedItem();
//			
//		}
//		
//	}
	
	private class SideListener extends WindowAdapter {
		
		@Override
		public void windowClosing(WindowEvent e) {
			startFrame.setVisible(true);
		}
	}

}
