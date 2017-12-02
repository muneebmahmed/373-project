package boardgame.play;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class RulesGUI {
	
	private StartMenuGUI startMenu;
	
	private JFrame rules;
	private JTextArea link;
	private JLabel rulesMessage;
	private JButton nextMainButton;
	private JButton backMainButton;
	
	
	public RulesGUI() {
		
		rules = new JFrame();
		link = new JTextArea("Click next to learn more about chess");//FIXME ----- insert rules here
		rulesMessage = new JLabel("Welcome to the Rules!");
		backMainButton = new JButton("Back");
		nextMainButton = new JButton("Next");


		rules.setLayout(new FlowLayout());
		rules.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		rules.setSize(300, 200);
		rules.setVisible(true);
		
		buildRulesGUI();
		
		
	}
	
	public void buildRulesGUI() {
		

		
		backMainButton.addActionListener(new ButtonListener());
		nextMainButton.addActionListener(new ButtonListener());
		
		rules.add(rulesMessage);
		rules.add(link);
		rules.add(backMainButton);
		rules.add(nextMainButton);
		
		
	}
	
	private JFrame pawnFrame;
	private JButton nextPawnButton;
	private JButton backPawnButton;
	private JTextArea pawnRules;
	
	public void makePawnFrame() {
		
		this.pawnFrame = new JFrame("Pawn Rules");
		this.nextPawnButton = new JButton("Next");
		this.backPawnButton = new JButton("Back");
		this.pawnRules = new JTextArea("Rules for Pawn\n"
				+"The Pawn can only move forward one space\n"
				+"unless it has never been moved before. In this\n"
				+"case you can move the pawn two spaces forward.\n"
				+"In order to take another piece, the Pawn must move\n"
				+"diagonaly forward one step. It cannot take any pieces\n"
				+"dirctly in front of it."
				);
		
		nextPawnButton.addActionListener(new ButtonListener());
		backPawnButton.addActionListener(new ButtonListener());
		
		pawnFrame.add(pawnRules);
		pawnFrame.add(backPawnButton);
		pawnFrame.add(nextPawnButton);
		
		pawnFrame.setVisible(true);
		
		pawnFrame.setLayout(new FlowLayout());
		pawnFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pawnFrame.setSize(350, 200);
		pawnFrame.setVisible(true);
		
		
		
	}
	
	private JFrame rookFrame;
	private JButton nextRookButton;
	private JButton backRookButton;
	private JTextArea rookRules;
	
	public void makeRookFrame() {
		
		this.rookFrame = new JFrame("Rook Rules");
		this.nextRookButton = new JButton("Next");
		this.backRookButton = new JButton("Back");
		this.rookRules = new JTextArea("Rules for Rook\n"
				+"The Rook can only move forwards, backwards,\n"
				+"left, or right(Cannot move diagonally). The Rook \n"
				+"can travel as it can in one move."
				);
		
		nextRookButton.addActionListener(new ButtonListener());
		backRookButton.addActionListener(new ButtonListener());
		
		rookFrame.add(rookRules);
		rookFrame.add(backRookButton);
		rookFrame.add(nextRookButton);
		
		rookFrame.setVisible(true);
		
		rookFrame.setLayout(new FlowLayout());
		rookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		rookFrame.setSize(350, 200);
		rookFrame.setVisible(true);

	}
	
	private JFrame knightFrame;
	private JButton nextKnightButton;
	private JButton backKnightButton;
	private JTextArea knightRules;
	
	public void makeKnightFrame() {
		
		this.knightFrame = new JFrame("Knight Rules");
		this.nextKnightButton = new JButton("Next");
		this.backKnightButton = new JButton("Back");
		this.knightRules = new JTextArea("Rules for Knight\n"
				+ "The Knight has a strange movement pattern.\n"
				+ "The Knight has to move 2 tiles in any direction\n"
				+ "and then 1 tile next to that. The Knight is allowed to \n"
				+ "\"Jump Over\" other pieces when executing its move.");
		
		nextKnightButton.addActionListener(new ButtonListener());
		backKnightButton.addActionListener(new ButtonListener());
		
		knightFrame.add(knightRules);
		knightFrame.add(backKnightButton);
		knightFrame.add(nextKnightButton);
		
		knightFrame.setVisible(true);
		
		knightFrame.setLayout(new FlowLayout());
		knightFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		knightFrame.setSize(350, 200);
		knightFrame.setVisible(true);

	}
	
	
	private JFrame bishopFrame;
	private JButton nextBishopButton;
	private JButton backBishopButton;
	private JTextArea bishopRules;
	
	public void makeBishopFrame() {
		
		this.bishopFrame = new JFrame("Bishop Rules");
		this.nextBishopButton = new JButton("Next");
		this.backBishopButton = new JButton("Back");
		this.bishopRules = new JTextArea("Rules for Bishop\n"
				+ "The Bishop can only move diagonally, but can travel\n"
				+ "as far as it wants.");
		
		nextBishopButton.addActionListener(new ButtonListener());
		backBishopButton.addActionListener(new ButtonListener());
		
		bishopFrame.add(bishopRules);
		bishopFrame.add(backBishopButton);
		bishopFrame.add(nextBishopButton);
		
		bishopFrame.setLayout(new FlowLayout());
		bishopFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		bishopFrame.setSize(350, 200);
		bishopFrame.setVisible(true);

	}
	
	private JFrame queenFrame;
	private JButton nextQueenButton;
	private JButton backQueenButton;
	private JTextArea queenRules;
	
	public void makeQueenFrame() {
		
		this.queenFrame = new JFrame("Queen Rules");
		this.nextQueenButton = new JButton("Next");
		this.backQueenButton = new JButton("Back");
		this.queenRules = new JTextArea("Rules for Queen\n"
				+ "The Queen has the greatest movement amongst the game\n"
				+ "pieces. The Queen is allowed to move in any direction\n"
				+ " (forward, backwards, left,right,diagonal) while being\n"
				+ " able to move as far as she wants.");
		
		nextQueenButton.addActionListener(new ButtonListener());
		backQueenButton.addActionListener(new ButtonListener());
		
		queenFrame.add(queenRules);
		queenFrame.add(backQueenButton);
		queenFrame.add(nextQueenButton);
		
		queenFrame.setLayout(new FlowLayout());
		queenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		queenFrame.setSize(350, 200);
		queenFrame.setVisible(true);

	}
	
	
	private JFrame kingFrame;
	private JButton nextKingButton;
	private JButton backKingButton;
	private JTextArea kingRules;
	
	public void makeKingFrame() {
		
		this.kingFrame = new JFrame("King Rules");
		this.nextKingButton = new JButton("Main Menu");
		this.backKingButton = new JButton("Back");
		this.kingRules = new JTextArea("Rules for King\n"
				+ "The King can only move to the spaces adjacent to it\n"
				+ "Once the King is taken by your opponent, the game\n"
				+ " is over. Be sure you protect your King while trying\n"
				+ " to take your opponents King!");
		
		nextKingButton.addActionListener(new ButtonListener());
		backKingButton.addActionListener(new ButtonListener());
		
		kingFrame.add(kingRules);
		kingFrame.add(backKingButton);
		kingFrame.add(nextKingButton);
		
		kingFrame.setLayout(new FlowLayout());
		kingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		kingFrame.setSize(350, 200);
		kingFrame.setVisible(true);

	}
	
	
	
	
	
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{

			JButton source = (JButton)(e.getSource());
			
			
			
			
			
			if(source.equals(nextMainButton))
			{
				
				makePawnFrame();
				rules.setVisible(false);
				
			
			}
			if(source.equals(backMainButton))
			{
				
				StartMenuGUI s = new StartMenuGUI();
				rules.dispose();
				
			
			}
			if(source.equals(nextPawnButton))
			{
				
				makeRookFrame();
				pawnFrame.dispose();
				
			
			}
			if(source.equals(backPawnButton)) {
				
				
				rules.setVisible(true);
				pawnFrame.dispose();
				
			}
			if(source.equals(nextRookButton))
			{
				
				makeKnightFrame();
				rookFrame.dispose();
				
			
			}
			if(source.equals(backRookButton)) {
				
				makePawnFrame();
				rookFrame.dispose();
				
			}
			if(source.equals(nextKnightButton))
			{
				
				makeBishopFrame();
				knightFrame.dispose();
				
			
			}
			if(source.equals(backKnightButton)) {
				
				makeRookFrame();
				knightFrame.dispose();
				
			}
			if(source.equals(nextBishopButton))
			{
				
				makeQueenFrame();
				bishopFrame.dispose();
				
			
			}
			if(source.equals(backBishopButton)) {
				
				makeKnightFrame();
				bishopFrame.dispose();
				
			}
			if(source.equals(nextQueenButton))
			{
				
				makeKingFrame();
				queenFrame.dispose();
				
			
			}
			if(source.equals(backQueenButton)) {
				
				makeBishopFrame();
				queenFrame.dispose();
				
			}
			if(source.equals(nextKingButton))
			{
				
				StartMenuGUI s = new StartMenuGUI();
				kingFrame.dispose();
				rules.dispose();
				
			
			}
			if(source.equals(backKingButton)) {
				
				makeQueenFrame();
				kingFrame.dispose();
				
			}
			
			
		}

	}
	
	
	

}
