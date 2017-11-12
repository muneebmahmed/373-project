package boardgame.play;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.*;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.LineBorder;

import boardgame.data.Command;
import boardgame.pieces.*;
import boardgame.pieces.Color;


public class GUI extends JFrame implements UserInterface {

	//add JPanel and components here
	private final JPanel gui = new JPanel(new BorderLayout(3,3));
	private JButton[][] chessBoardSquares= new JButton[8][8];
	private JPanel chessBoard;
	private static final String Columns = "ABCDEFGH";
	private static final String ROWS = "12345678";
	
	//components in getting a move
	private volatile Piece moving;
	private volatile Square origin;
	private volatile Square destination;
	

	
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
		addChessPieces();
		//makeBoard-add elements
	    

	}
	
	public void makeChessFrame() {
		gui.setBorder(new EmptyBorder(5,5,5,5));
		
		chessBoard = new JPanel(new GridLayout(0,9));//provides the number of elements in board, including chess squares and indexes
		
		gui.add(chessBoard);
		
		Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon image = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));//64x64 px in size
                b.setIcon(image);
                if (((j % 2 == 1) && (i % 2 == 1)) || (j % 2 == 0 && i % 2 == 0)) {//fills in every other tiles with white or black
                    b.setBackground(java.awt.Color.WHITE);
                } else {
                	b.setBackground(java.awt.Color.BLACK);
                }
                chessBoardSquares[j][i] = b;
            }
        }
		
      //fill the chess board
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int i = 0; i < 8; i++) {
            chessBoard.add(new JLabel(Columns.substring(i, i + 1),SwingConstants.CENTER));
        }
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
    }

    public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JComponent getGui() {
        return gui;
    }
    
    
    public void addChessPieces() {
    	/*
    	 * FIXME
    	 * In order to get the pieces to show up, we have to change the setBackground to setForeground. 
    	 * We also need to implement the icon of the piece instead of the
    	 * different colors
    	 * 
    	 */
    	
    	//black pieces first
    		for(int i = 0; i < 8; i++) {
    			chessBoardSquares[i][1].setBackground(java.awt.Color.BLUE);//add the black pawns here
    		}
    		chessBoardSquares[0][0].setBackground(java.awt.Color.BLUE);//black rooks add here
    		chessBoardSquares[7][0].setBackground(java.awt.Color.BLUE);
    	
    		chessBoardSquares[1][0].setBackground(java.awt.Color.BLUE);//black knights add here
    		chessBoardSquares[6][0].setBackground(java.awt.Color.BLUE);
    	
    		chessBoardSquares[2][0].setBackground(java.awt.Color.BLUE);//black bishop add here
    		chessBoardSquares[5][0].setBackground(java.awt.Color.BLUE);
    	
    		chessBoardSquares[3][0].setBackground(java.awt.Color.BLUE);//black queen
    		chessBoardSquares[4][0].setBackground(java.awt.Color.RED);//black king

    	
    		for(int i = 0; i < 8; i++) {
    			chessBoardSquares[i][6].setBackground(java.awt.Color.GREEN);//add the white pawns here
    		}
    	
    		chessBoardSquares[0][7].setBackground(java.awt.Color.GREEN);//black rooks add here
    		chessBoardSquares[7][7].setBackground(java.awt.Color.GREEN);
    	
    		chessBoardSquares[1][7].setBackground(java.awt.Color.GREEN);//black knights add here
    		chessBoardSquares[6][7].setBackground(java.awt.Color.GREEN);
    	
    		chessBoardSquares[2][7].setBackground(java.awt.Color.GREEN);//black bishop add here
    		chessBoardSquares[5][7].setBackground(java.awt.Color.GREEN);
    		
    		chessBoardSquares[4][7].setBackground(java.awt.Color.GREEN);//black queen
    		chessBoardSquares[3][7].setBackground(java.awt.Color.RED);//black king
    	
    	
    		//next step is to add action listeners for each of these pieces
    	
    }
/*
    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
            	GUI cb = new GUI();
            	cb.setVisible(true);
            	
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
	
	}
  */  

	@Override
	public Command getCommand(Player player, Board b) {
		// TODO Auto-generated method stub
		destination = null;
		
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
			}
		}
		//System.out.println("Loop exited");
		return new Command(moving, origin, destination);
	}
	
	@Override
	public String getPlayerName(Color player) {
		//TODO
		return "";
	}
	
	@Override
	public void updateBoard(Board b) {
		//TODO
		//should redraw the board after a move, if necessary
		return;
	}

}
