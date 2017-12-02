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
	private volatile PieceName promotion;	//in case of pawn promotion
	

	
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
		toMove = Color.WHITE;
		promotion = PieceName.PAWN;
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
		promotion = PieceName.PAWN;
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

    
	public synchronized void resetSquareIcons() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				SquareButton square = (SquareButton)chessBoardSquares[i][j];
				square.updateIcon();
				if (square.paintCount != 0) {
					square.resetBackground();
				}
				square.paintCount++;
			}
		}
		return;
	}
	
	public class SquareListener implements ActionListener {

		@Override
		public synchronized void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			SquareButton source = (SquareButton)(e.getSource());
			Square square = source.getSquare();
			if (destination != null) { return; }
			if (origin != null) {
				if (origin.equals(square)) {
					if (moving != null) {
						for (Square s : moving.getLegalMoves()) {
							squares.get(s.getName()).resetBackground();
						}
					}
					origin = null;
					moving = null;
					source.resetBackground();
					return;
				}
				else {
					ArrayList<Square> destSquares = moving.getLegalMoves();
					if (destSquares.contains(square)) {
						//destination = square;
						setDestination(square);
						squares.get(origin.getName()).resetBackground();
						//resetSquareIcons();
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
					source.emphasizeBackground();
					for (Square s : moving.getLegalMoves()) {
						squares.get(s.getName()).setBlue();
					}
					//source.setBackground(java.awt.Color.BLUE);
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
	public synchronized Command getCommand(Player player, Board b) {
		// TODO Auto-generated method stub
		destination = null;
		toMove = player.getColor();
		
		/*
		 * The following code waits until the player selects a move
		 * The player should be able to select a piece and destination with the GUI
		 * The action listeners associated with the pieces will execute on a different thread
		 * Therefore, the current thread should wait to not waste resources
		 */
		while (destination == null) {
			try {
				//System.out.println("Waiting in Thread: " + Thread.currentThread().getName());
				wait();
			}catch (InterruptedException e) {
				//do stuff?
				System.out.println("Interrupted ");
			}
			//cases when not making a move
			if (undo) {
				undo = quit = redo = false;
				return new Command(toMove, "undo", board);
			}
			else if (quit) {
				quit = redo = false;
				return new Command(toMove, "quit", board);
			}
			else if (redo) {
				redo = false;
				//TODO implement redo
				return new Command(toMove, "redo", board);
			}
		}
		//System.out.println("Loop exited");
		Command move = new Command(moving, origin, destination);
		if (promotion != PieceName.PAWN && moving instanceof Pawn) {
			move = new Command((Pawn)moving, destination, promotion);
		}
		SquareButton ori = squares.get(origin.getName()), dest = squares.get(destination.getName());
		ori.emphasizeBackground();
		ori.paintCount = 0; dest.paintCount = 0;
		dest.emphasizeBackground();
		origin = destination = null;
		moving = null;
		promotion = PieceName.PAWN;
		return move;
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
		resetSquareIcons();
		repaint();
		return;
	}

	public synchronized Piece getMoving() {
		return moving;
	}

	public synchronized void setMoving(Piece moving) {
		this.moving = moving;
	}

	public synchronized Square getOrigin() {
		return origin;
	}

	public synchronized void setOrigin(Square origin) {
		this.origin = origin;
	}

	public synchronized Square getDestination() {
		return destination;
	}

	public synchronized void setDestination(Square destination) {
		this.destination = destination;
		notifyAll();
	}

}
