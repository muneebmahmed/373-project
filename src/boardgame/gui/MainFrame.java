package boardgame.gui;

import boardgame.play.*;
import boardgame.pieces.*;
import boardgame.data.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame {
	
	public GUI gui;
	public ChessGame game;
	
	private JMenuBar menu;
	private JMenu file;
	private JMenuItem save;
	private JMenuItem exit;
	private JMenuItem open;
	private JMenu edit;
	private JMenu white;
	private JMenu black;
	private JMenuItem setWhite;
	private JMenuItem setBlack;
	private JMenuItem renameWhite;
	private JMenuItem renameBlack;
	private JMenu help;
	private JMenuItem rules;
	
	private JFileChooser chooser;
	private Console console;
	
	private JPanel buttons;
	private JButton redo;
	private JButton undo;
	private JButton resign;
	
	public Color toMove;
	private JScrollPane boardScroller;
	private JScrollPane textScroller;
	
	private JTextArea movesHistory;
	private JScrollPane movesScroller;
	
	private JColorChooser colorChooser;
	private JMenuItem colorWhite;
	private JMenuItem colorBlack;
	
	private JMenuItem newGame;

	public MainFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	public MainFrame(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public MainFrame(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public MainFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
	
	public MainFrame(GUI gui) {
		super();
		this.gui = gui;
		menu = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		save = new JMenuItem("Save");
		open = new JMenuItem("Open");
		exit = new JMenuItem("Exit");
		white = new JMenu("White");
		black = new JMenu("Black");
		setWhite = new JMenuItem("Set White");
		setBlack = new JMenuItem("Set Black");
		renameWhite = new JMenuItem("Rename");
		renameBlack = new JMenuItem("Rename");
		save.addActionListener(new MenuListener());
		open.addActionListener(new MenuListener());
		exit.addActionListener(new MenuListener());
		setWhite.addActionListener(new MenuListener());
		setBlack.addActionListener(new MenuListener());
		renameWhite.addActionListener(new MenuListener());
		renameBlack.addActionListener(new MenuListener());
		colorWhite = new JMenuItem("Recolor Light Squares...");
		colorBlack = new JMenuItem("Recolor Dark Squares...");
		colorWhite.addActionListener(new MenuListener());
		colorBlack.addActionListener(new MenuListener());
		newGame = new JMenuItem("New");
		newGame.addActionListener(new MenuListener());
		file.add(newGame);
		file.add(open);
		file.add(save);
		file.add(exit);
		white.add(renameWhite);
		white.add(setWhite);
		black.add(renameBlack);
		black.add(setBlack);
		edit.add(white);
		edit.add(black);
		edit.add(colorWhite);
		edit.add(colorBlack);
		help = new JMenu("Help");
		rules = new JMenuItem("Rules");
		rules.addActionListener(new MenuListener());
		help.add(rules);
		
		menu.add(file);
		menu.add(edit);
		menu.add(help);
		this.setJMenuBar(menu);
		
		this.setLayout(new BorderLayout());
		boardScroller = new JScrollPane(this.gui);
		boardScroller.setAutoscrolls(true);
		
		add(boardScroller, BorderLayout.CENTER);
		//add(this.gui, BorderLayout.CENTER);
		
		console = new Console();
		System.setOut(console.getPrintStream());
		textScroller = new CustomScroller(this.console);
		//textScroller.setMaximumSize(new Dimension(500, 100));

		//textScroller.setAutoscrolls(true);
		textScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//text.setMaximumSize(new Dimension (500, 100));
		this.add(textScroller, BorderLayout.SOUTH);
		
		movesHistory = new Console();
		movesScroller = new CustomScroller(this.movesHistory);
		movesScroller.setAutoscrolls(true);
		this.add(movesScroller, BorderLayout.EAST);
		
		chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.pgn, *.txt", "pgn", "txt");
		chooser.setFileFilter(filter);
		colorChooser = new JColorChooser();
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new ExitListener());
		
		redo = new JButton("Redo");
		redo.addActionListener(new ButtonListener());
		undo = new JButton("Undo");
		undo.addActionListener(new ButtonListener());
		resign = new JButton("Resign");
		resign.addActionListener(new ButtonListener());
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(3, 1));
		buttons.add(undo);
		buttons.add(redo);
		buttons.add(resign);
		this.add(buttons, BorderLayout.WEST);
	}
	
	public void setGame(ChessGame game) {
		this.game = game;
		return;
	}
	
	public void printMovesHistory() {
		StringBuilder text = new StringBuilder();
		ArrayList<String> moveList = game.getBoard().getMoves();
		for (int i = 0; i < moveList.size(); i++) {
			if (i%2 == 0) {
				text.append(Integer.toString(i/2 + 1) + ". " + moveList.get(i));
			}
			else {
				text.append("\t" + moveList.get(i) + "\n");
			}
		}
		movesHistory.setText(text.toString());
	}
	
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JMenuItem source = (JMenuItem)e.getSource();
			if (source.equals(open)) {
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setVisible(true);
				int code = chooser.showOpenDialog(menu.getParent().getParent());
				if (code == JFileChooser.APPROVE_OPTION) {
					File gameFile = chooser.getSelectedFile();
					try {
						FileInputStream is = new FileInputStream(gameFile);
						toMove = gui.getBoard().ReadFile(is);
						is.close();
						gui.updateBoard(gui.getBoard());
						//game.setToMove(toMove);	//this breaks the game for some reason
						gui.setError(true);			//this works around it by making game figure it out
						return;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			else if (source.equals(save)) {
				chooser.setVisible(true);
				
				int code = chooser.showSaveDialog(menu.getParent().getParent());
				if (code == JFileChooser.APPROVE_OPTION) {
					File saveFile = chooser.getSelectedFile();
					if (!saveFile.getAbsolutePath().contains(".pgn")) {
						saveFile = new File(saveFile.getAbsolutePath() + ".pgn");
					}
					try {
						FileOutputStream os = new FileOutputStream(saveFile);
						Board b = gui.getBoard();
						b.SaveFile(os);
						System.out.println("File saved!");
						os.close();
						gui.updateBoard(b);
					} catch (IOException f) {
						// TODO Auto-generated catch block
						f.printStackTrace();
					}
				}
			}
			else if (source.equals(exit)) {
				if (game.getMode() != 3) {
					gui.setQuit(true);
					game.setGameOver(true);
				}
				else {
					game.gameOver = true;
					if (game.getBoard().getMateFlag() != 0) {
						game.setGameOver(true);		//Fixes issue when game over window won't close
					}
				}
			}
			else if (source.equals(renameWhite)) {
				String name = JOptionPane.showInputDialog(menu.getParent(), "Enter the name for White: ");
				game.getWhite().setName(name);
				game.renamed = true;
				return;
			}
			else if (source.equals(renameBlack)) {
				String name = JOptionPane.showInputDialog(menu.getParent(), "Enter the name for Black: ");
				game.getBlack().setName(name);
				game.renamed = true;
				return;
			}
			else if (source.equals(setWhite)) {
				int prevMode = game.getMode();
				if (prevMode == 3) {
					game.pause = true;
				}
				int confirm = JOptionPane.showConfirmDialog(menu.getParent(), "Is a person playing white?");
				String name;
				Player whitePlayer;
				if (confirm == JOptionPane.CANCEL_OPTION) {
					return;
				}
				if (confirm == JOptionPane.YES_OPTION) {
					name = JOptionPane.showInputDialog(menu.getParent(), "Enter the name for White: ");
					whitePlayer = new Human(name, Color.WHITE, gui);
					if (game.getMode() == 2 || game.getMode() == 3) {
						game.setMode(game.getMode() -2);
					}
				}
				else {
					whitePlayer = new Computer("White Computer", Color.WHITE);
					if (game.getMode() == 0 || game.getMode() == 1) {
						game.setMode(game.getMode() + 2);
					}
				}
				game.setWhite(whitePlayer);
				game.renamed = true;
				if (prevMode == 3) {
					game.setPause(false);
				}
			}
			else if (source.equals(setBlack)) {
				int prevMode = game.getMode();
				if (prevMode == 3) {
					game.pause = true;
				}
				int confirm = JOptionPane.showConfirmDialog(menu.getParent(), "Is a person playing black?");
				String name;
				Player blackPlayer;
				if (confirm == JOptionPane.CANCEL_OPTION) {
					return;
				}
				if (confirm == JOptionPane.YES_OPTION) {
					name = JOptionPane.showInputDialog(menu.getParent(), "Enter the name for Black: ");
					blackPlayer = new Human(name, Color.BLACK, gui);
					if (game.getMode() == 1 || game.getMode() == 3) {
						game.setMode(game.getMode() -1);
					}
				}
				else {
					blackPlayer = new Computer("Black Computer", Color.BLACK);
					if (game.getMode() == 0 || game.getMode() == 2) {
						game.setMode(game.getMode() + 1);
					}
				}
				game.setBlack(blackPlayer);
				game.renamed = true;
				if (prevMode == 3) {
					game.setPause(false);
				}
			}
			else if (source.equals(rules)) {
				RulesGUI r = new RulesGUI();
			}
			else if (source.equals(colorWhite)) {
				java.awt.Color selected = JColorChooser.showDialog(getParent(), "Choose a color", SquareButton.WHITE);
				if (selected == null) { return; }
				SquareButton.adjustWhite(selected);
				gui.updateBoard(gui.board);
			}
			else if (source.equals(colorBlack)) {
				java.awt.Color selected = JColorChooser.showDialog(getParent(), "Choose a color", SquareButton.GREEN);
				if (selected == null) { return; }
				SquareButton.adjustGreen(selected);
				gui.updateBoard(gui.board);
			}
			else if (source.equals(newGame)) {
				game.immediateStart = true;
				if (game.getMode() != 3) {
					gui.setQuit(true);
					game.setGameOver(true);
				}
				else {
					game.gameOver = true;
					if (game.getBoard().getMateFlag() != 0) {
						game.setGameOver(true);		//Fixes issue when game over window won't close
					}
				}
			}
			else {
				
			}
		}
		
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton source = (JButton)e.getSource();
			
			if (source.equals(undo)) {
				if (game.getMode() != 3 || game.renamed) {
					gui.setUndo(true);
				}
				if (gui.getBoard().getMateFlag() != 0) {
					game.setUndo(true);
				}
				//game.set
			}
			else if (source.equals(redo)) {
				if (game.getMode() != 3) {
					gui.setRedo(true);
				}
				if (gui.getBoard().getMateFlag() != 0) {
					game.setRedo(true);
				}
				//something with game?
			}
			else if (source.equals(resign)) {
				if (game.getMode() != 3) {
					gui.setQuit(true);
				}
				//game ?
			}
		}
		
	}
	
	private class ExitListener extends WindowAdapter {
		
		@Override
		public void windowClosing(WindowEvent e) {
			if (game.getMode() != 3) {
				gui.setQuit(true);
				game.setGameOver(true);
			}
			else {
				game.gameOver = true;
				if (game.getBoard().getMateFlag() != 0) {
					game.setGameOver(true);		//Fixes issue when game over window won't close
				}
			}
		}
		
	}

}
