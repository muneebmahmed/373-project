package boardgame.play;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.*;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.LineBorder;

import boardgame.data.Command;
import boardgame.pieces.*;
import boardgame.pieces.Color;
import boardgame.gui.*;


public class GUI extends JFrame implements UserInterface {

	//add JPanel and components here
	private JPanel gui;
	//private final JPanel gui = new JPanel(new BorderLayout(3,3));
	private JButton[][] chessBoardSquares= new JButton[8][8];
	private HashMap<String, SquareButton> squares;
	private JPanel chessBoard;
	private static final String Columns = "ABCDEFGH";
	private static final String ROWS = "12345678";
	
	public Board board;
	public Color toMove;
	
	//components in getting a move
	private volatile Piece moving;
	private volatile Square origin;
	private volatile Square destination;
	private volatile boolean undo, redo, quit;	//one for pawn promotion as well?
	//private volatile PieceName promotion;	//in case of pawn promotion
	private volatile Command c;	//messing around, should delete later?
	

	
	public GUI() throws HeadlessException {
		// TODO Auto-generated constructor stub
		//super("Chess");
		//setSize(1000, 1000);
		//setLayout(new BorderLayout());
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//moving = null;
		//origin = null;
		//destination = null;
		
		//setVisible(true);
		super();
		board = new Board();
		gui = new JPanel();
		squares = new HashMap<String, SquareButton>();
		makeGUI();
	}

	public GUI(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
		
		
	}

	public GUI(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		
		
	}

	public GUI(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
		
		
	}
	
	public GUI(Board b) {
		super();
		board = b;
		gui = new JPanel();
		toMove = Color.WHITE;		//let user decide?
		squares = new HashMap<String, SquareButton>();
		//initialize volatile elements?
		//undo = false; quit = false; redo = false;
		//moving = null; origin = null; destination = null;
		makeGUI();
	}
	
	//In the JPanel class, use this code to always keep the board square:
	//Note: the parent component (JFrame) must use GridBagLayout
	
//	@Override
//	public Dimension getPreferredSize() {
//		Dimension d = super.getPreferredSize();
//		Container c = this.getParent();
//		if (c == null) {
//			return new Dimension(100, 100);
//		}
//		d = c.getSize();
//		int w = (int)d.getWidth();
//		int h = (int)d.getHeight();
//		int s = (w < h)? w : h;
//		return new Dimension(s, s);
//	}

	
	public void makeGUI() {
		//make frame first
		makeChessFrame();
		resetSquareIcons();
		//makeBoard-add elements
	    

	}
	/*
	 * TODO
	 * What happens when changing the board?
	 * Will a new board get added to the frame?
	 * Perhaps split adding components and making buttons so that
	 * modifying boards between games will be easier
	 */
	public void makeChessFrame() {
		gui.setBorder(new EmptyBorder(5,5,5,5));
		
		chessBoard = new JPanel(new GridLayout(0,9));//provides the number of elements in board, including chess squares and indexes
		
		//gui.add(chessBoard); add(gui);
		add(chessBoard);
		
		Insets buttonMargin = new Insets(0,0,0,0);
		for (int i = 0; i < chessBoardSquares.length; i++) {
			for (int j = 0; j < chessBoardSquares[i].length; j++) {
				Square s = board.getBoard()[7-i][j];
				SquareButton b = new SquareButton(s);
				b.setMargin(buttonMargin);
				/*
				if (s.hasPiece()) {
					b = PieceButton.createPieceButton(s.getPiece());
				}
				else {
					b = new JButton();
					ImageIcon image = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));//64x64 px in size
					b.setIcon(image);
				}
				b.setMargin(buttonMargin);

				if (((j % 2 == 1) && (i % 2 == 1)) || (j % 2 == 0 && i % 2 == 0)) {//fills in every other tiles with white or black
					b.setBackground(java.awt.Color.WHITE);
				} else {
					b.setBackground(new java.awt.Color(0, 90, 45));
				}
				*/
				b.setOpaque(true);
				b.setBorderPainted(false);
				b.addActionListener(new SquareListener());
				chessBoardSquares[j][i] = b;
				squares.put(b.getSquare().getName(), b);	//Why errors?
			}
		}
		
		//fill the chess board
		//chessBoard.add(new JLabel(""));
		// fill the top row
//		for (int i = 0; i < 8; i++) {
//			chessBoard.add(new JLabel(Columns.substring(i, i + 1),SwingConstants.CENTER));
//		}
		// fill the black non-pawn piece row
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				switch (j) {
				case 0:
					chessBoard.add(new JLabel("" + (8 -i),SwingConstants.CENTER));
				default:
					chessBoard.add(chessBoardSquares[j][i]);
				}
			}
		}
		//File labels should be at the bottom
		chessBoard.add(new JLabel(""));
		for (int i = 0; i < 8; i++) {
			chessBoard.add(new JLabel(Character.toString(Character.toUpperCase(Square.alphabet.charAt(i))), SwingConstants.CENTER));
		}
		chessBoard.setVisible(true);
		setVisible(true);
		//gui.setVisible(true);
	}

	public JComponent getChessBoard() {
		return chessBoard;
	}

	public JComponent getGui() {
		return gui;
	}

    
	public void resetSquareIcons() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				SquareButton square = (SquareButton)chessBoardSquares[i][j];
				square.updateIcon();
				square.resetBackground();
			}
		}
		return;
	}
	
	public class SquareListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			SquareButton source = (SquareButton)(e.getSource());
			Square square = source.getSquare();
			if (destination != null) { return; }
			if (origin != null) {
				if (origin.equals(square)) {
					origin = null;
					moving = null;
					source.updateIcon();
					source.resetBackground();
					return;
				}
				else {
					ArrayList<Square> destSquares = moving.getLegalMoves();
					if (destSquares.contains(square)) {
						destination = square;
						//delete this later:
						Command c = new Command(moving, origin, destination);
						board.Move(c);
						board.updateState(c);
						resetSquareIcons();
						repaint();
						destination = null;
						origin = null;
						moving = null;
						toMove = (toMove == Color.WHITE)? Color.BLACK : Color.WHITE;
					}
					else {
						System.out.println("That's not a valid move");
					}
				}
			}
			else {
				if (square.hasPiece() && toMove == square.getPiece().getColor()) {
					origin = square;
					moving = square.getPiece();
					//check if piece has valid moves?
					source.setBackground(java.awt.Color.BLUE);
				}
				else if (square.hasPiece() && toMove != square.getPiece().getColor()) {
					System.out.println("That's not your piece!");
				}
				else {
					System.out.println("That square does not have a piece");
				}
			}
		}
		
	}

	@Override
	public Command getCommand(Player player, Board b) {
		// TODO Auto-generated method stub
		destination = null;
		
		//The following lines are just me messing around, I intend to delete them later
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				while (destination == null) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					
				}
				c = new Command(moving, origin, destination);
			}
		});
		
		/*
		 * The following code waits until the player selects a move
		 * The player should be able to select a piece and destination with the GUI
		 * The action listeners associated with the pieces will execute on a different thread
		 * Therefore, the current thread should sleep to not waste resources
		 */
		while (destination == null) {
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				//do stuff?
				System.out.println("Interrupted ");
			}
			//cases when not making a move
			if (undo) {
				undo = false;
				quit = false;
				redo = false;
				return new Command(toMove, "undo", board);
			}
			else if (quit) {
				quit = false;
				redo = false;
				return new Command(toMove, "quit", board);
			}
			else if (redo) {
				redo = false;
				//TODO implement redo
				//return new Command(toMove, "redo", board);
			}
			System.out.println("Waiting for destination ");
		}
		//System.out.println("Loop exited");
		return new Command(moving, origin, destination);
	}
	
	@Override
	public String getPlayerName(Color player) {
		//TODO
		String name = JOptionPane.showInputDialog("Enter the name for " + player);
		return name;
	}
	
	@Override
	public void updateBoard(Board b) {
		//TODO
		//should redraw the board after a move, if necessary
		return;
	}

}
