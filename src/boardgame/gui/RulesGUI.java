package boardgame.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import boardgame.pieces.*;
import boardgame.data.*;
import boardgame.data.Configuration.ConfigElement;
import boardgame.play.*;

import javax.swing.*;

public class RulesGUI {

	private StartMenuGUI startMenu;
	
	private JFrame rules;
	private JTextArea link;
	private JLabel rulesMessage;
	private JButton nextMainButton;
	private JButton backMainButton;
	private Board board;
	private GUI gui;
	private Console console;
	private ArrayList<Configuration> configurations;
	private ArrayList<String> descriptions;
	private Configuration current;
	int i;
	
	
	public RulesGUI() {
		
		rules = new JFrame();
		link = new JTextArea("Click next to learn more about chess");//FIXME ----- insert rules here
		rulesMessage = new JLabel("Welcome to the Rules!");
		backMainButton = new JButton("Back");
		nextMainButton = new JButton("Next");


		//rules.setLayout(new FlowLayout());
		rules.setLayout(new BorderLayout());
		rules.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		rules.setSize(600, 600);
		rules.setVisible(true);
		
		buildRulesGUI();
		
		
	}
	
	public void buildRulesGUI() {
		

		
		backMainButton.addActionListener(new RuleButtonListener());
		nextMainButton.addActionListener(new RuleButtonListener());
		
		//rules.add(rulesMessage);
		//rules.add(link);
		rules.add(backMainButton, BorderLayout.WEST);
		rules.add(nextMainButton, BorderLayout.EAST);
		board = new Board();
		gui = new GUI(board);
		gui.setMaximumSize(new Dimension(300, 300));
		rules.add(gui, BorderLayout.CENTER);
		i = 0;
		Configuration king = new Configuration();
		ConfigElement kingElement = king.new ConfigElement(Color.WHITE, PieceName.KING, "e1");
		king.elements.add(kingElement);
		
		Configuration queen = new Configuration();
		ConfigElement queenElement = queen.new ConfigElement(Color.WHITE, PieceName.QUEEN, "e4");
		queen.elements.add(queenElement);
		
		Configuration rook = new Configuration();
		ConfigElement rookElement = rook.new ConfigElement(Color.WHITE, PieceName.ROOK, "h1");
		rook.elements.add(rookElement);
		king.elements.add(rookElement);
		
		Configuration bishop = new Configuration();
		ConfigElement bishopElement = bishop.new ConfigElement(Color.WHITE, PieceName.BISHOP, "e4");
		bishop.elements.add(bishopElement);
		
		Configuration knight = new Configuration();
		ConfigElement knightElement = knight.new ConfigElement(Color.WHITE, PieceName.KNIGHT, "e4");
		knight.elements.add(knightElement);
		
		Configuration pawn = new Configuration();
		ConfigElement pawnElement = pawn.new ConfigElement(Color.WHITE, PieceName.PAWN, "e2");
		pawn.elements.add(pawnElement);
		configurations = new ArrayList<Configuration>();
		configurations.addAll(Arrays.asList(king, queen, rook, bishop, knight, pawn));
		current = configurations.get(i);
		board.loadConfiguration(current);
		board.setCurrentState(current);
		gui.updateBoard(board);
		descriptions = new ArrayList<String>();
		descriptions.add(new String("Rules for King\n"
				+ "The King can only move to the spaces adjacent to it\n"
				+ "Once the King is taken by your opponent, the game\n"
				+ " is over. Be sure you protect your King while trying\n"
				+ " to take your opponents King!"));
		descriptions.add(new String("Rules for Queen\n"
				+ "The Queen has the greatest movement amongst the game\n"
				+ "pieces. The Queen is allowed to move in any direction\n"
				+ " (forward, backwards, left,right,diagonal) while being\n"
				+ " able to move as far as she wants."));
		descriptions.add(new String("Rules for Rook\n"
				+"The Rook can only move forwards, backwards,\n"
				+"left, or right (not diagonally). The Rook \n"
				+"can travel as it can in one move."));
		descriptions.add(new String("Rules for Bishop\n"
				+ "The Bishop can only move diagonally, but can travel\n"
				+ "as far as it wants."));
		descriptions.add(new String("Rules for Knight\n"
				+ "The Knight has a strange movement pattern.\n"
				+ "The Knight has to move 2 tiles in any direction\n"
				+ "and then 1 tile next to that. The Knight is allowed to \n"
				+ "\"Jump Over\" other pieces when executing its move."));
		descriptions.add(new String("Rules for Pawn\n"
				+"The Pawn can only move forward one space\n"
				+"unless it has never been moved before. In this\n"
				+"case you can move the pawn two spaces forward.\n"
				+"In order to take another piece, the Pawn must move\n"
				+"diagonaly forward one step. It cannot take any pieces\n"
				+"dirctly in front of it."));
		console = new Console();
		rules.add(console, BorderLayout.NORTH);
		console.setVisible(true);
		console.setMinimumSize(new Dimension(100, 100));
	}
	
	
	private class RuleButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {

			JButton source = (JButton)(e.getSource());
			
			
			if(source.equals(nextMainButton)) {
				
				i++;
				if (i > 5) {
					StartMenuGUI s = new StartMenuGUI();
					rules.dispose();
					return;
				}
				current = configurations.get(i);
				board.loadConfiguration(current);
				board.setCurrentState(current);
				gui.updateBoard(board);
				console.setText("");
				System.out.println(descriptions.get(i));
				//makePawnFrame();
				//rules.setVisible(false);
				
			
			}
			if(source.equals(backMainButton)) {
				i--;
				if (i < 0) {
					StartMenuGUI s = new StartMenuGUI();
					rules.dispose();
					return;
				}
				current = configurations.get(i);
				board.loadConfiguration(current);
				board.setCurrentState(current);
				gui.updateBoard(board);
				console.setText("");
				System.out.println(descriptions.get(i));
			
			}
			
			
		}

	}
	
	
}
