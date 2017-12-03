package boardgame.play;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import boardgame.pieces.*;
import boardgame.data.*;

public class GUIRunner {

	public GUIRunner() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board testBoard = new Board();
		GUI cb = new GUI(testBoard);
		cb.setVisible(true);
		cb.setMinimumSize(new Dimension(576, 576));
		JFrame frame = new JFrame("Chess Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(cb);
		frame.pack();
		frame.setMinimumSize(frame.getSize());
		frame.setVisible(true);
		//cb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JTextArea text = new JTextArea(100,100);
//		cb.setLayout(new FlowLayout());
//		text.setVisible(true);
//		cb.add(text);
		Runnable x = new Runnable() {
			@Override
			public synchronized void run() {
				cb.resetSquareIcons();
				cb.repaint();
				System.out.println("Repainted in Thread: " + Thread.currentThread().getName());
				notifyAll();
			}
		};

		Runnable r = new Runnable() {

			@Override
			public void run() {
				//Board testBoard = new Board();
				//GUI cb = new GUI(testBoard);
				
            	
				//JFrame f = new JFrame("Chess");
				//f.add(cb.getGui());
				//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//f.setLocationByPlatform(true);

				// ensures the frame is the minimum size it needs to be
				// in order display the components within it
				//f.pack();
				// ensures the minimum size is enforced.
				//f.setMinimumSize(f.getSize());
				//f.setVisible(true);
				Color toMove = Color.WHITE;
				JFileChooser chooser;
				chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("pgn and txt files", "pgn", "txt");
				chooser.setFileFilter(filter);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				JDialog dialog;
				int code = JOptionPane.showConfirmDialog(null, "Do you want to open a file?");
				if (code == JOptionPane.YES_OPTION) {
					chooser.setVisible(true);
					dialog = new JDialog();
					code = chooser.showOpenDialog(dialog);
				
					if (code == JFileChooser.APPROVE_OPTION) {
						File gameFile = chooser.getSelectedFile();
						try {
							FileInputStream is = new FileInputStream(gameFile);
							toMove = testBoard.ReadFile(is);
							is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				}
				String input = "";
				int mode = 0;
				Player white, black, current;
				if (args.length != 2) {
					try { 
						mode = JOptionPane.showConfirmDialog(null, "Is white human?");
						if (mode == JOptionPane.YES_OPTION) { mode = 0; }
						else { mode = 1; }
					} catch (InputMismatchException e) { mode = 0; }
				}
				else {
					try { mode = Integer.parseInt(args[0]); } catch (NumberFormatException e) { mode = 1; }
				}
				if (mode == 1) {white = new Computer("Computer", Color.WHITE);}
				else {
					input = cb.getPlayerName(Color.WHITE);
					white = new Human(input, Color.WHITE, cb);}
				if (args.length != 2) {
					System.out.println("Is black human or computer? (0/1)");
					try {
						mode = JOptionPane.showConfirmDialog(null, "Is black human?");
						if (mode == JOptionPane.YES_OPTION) { mode = 0; }
						else { mode = 1; }
					} catch (InputMismatchException e) { mode = 0; }
				}
				else {
					try {mode = Integer.parseInt(args[1]); } catch (NumberFormatException e) { mode = 1; }
				}
				if (mode == 1) {black = new Computer("Computer", Color.BLACK);}
				else {
					input = cb.getPlayerName(Color.BLACK);
					black = new Human(input, Color.BLACK, cb);}
				//String input = JOptionPane.showInputDialog("Hello");
				//Command move = new Command(Color.WHITE, "e4", testBoard);
				//testBoard.Move(move);
				//cb.resetSquareIcons();
				//cb.repaint();
				int i = 0;
				Player [] sides = { white, black };
				current = (toMove == Color.WHITE)? white : sides[++i];
//				ChessGame chess = new ChessGame(white, black);
//				chess.setBoard(testBoard);
//				chess.setUi(cb);
//				(new Thread(chess)).start();

				cb.resetSquareIcons();
				cb.repaint();
				cb.setFocusable(true);
				while (testBoard.isGameOver(current.getColor()) == 0) {
					System.out.println("In while loop: " + i);
					cb.toMove = current.getColor();
					//Command c = null;
					cb.requestFocus();
					current.Move(testBoard);
					//c = cb.getCommand(current, testBoard);
					//testBoard.Move(c);
					//testBoard.updateState(c);
					//SwingUtilities.invokeLater(x);
					//try {
					//	wait();
					//} catch (InterruptedException e) { e.printStackTrace(); }
					cb.resetSquareIcons();
					cb.repaint();
					i++;
					current = sides[i%2];
				}
				//System.out.println("Game over");
				if (testBoard.isGameOver(current.getColor()) != 0) {
					if (testBoard.getMateFlag() == 1 || testBoard.getMateFlag() == 2) {
						input = current.getName() + " loses!";
					}
					else if (testBoard.getMateFlag() == 3) { input = "Stalemate!"; }
					else { input = "Draw"; }
				}
				JOptionPane.showMessageDialog(null, input);
				/*code = chooser.showSaveDialog(dialog);
				if (code == JFileChooser.APPROVE_OPTION) {
					File saveFile = chooser.getSelectedFile();
					try {
						FileOutputStream os = new FileOutputStream(saveFile);
						testBoard.SaveFile(os);
						System.out.println("File saved!");
						os.close();
					} catch (IOException f) {
						// TODO Auto-generated catch block
						f.printStackTrace();
					}
				}*/

			}
		};
		//SwingUtilities.invokeLater(r);
		(new Thread(r)).start();
		/*JFileChooser otherChooser = new JFileChooser();
		JDialog dia = new JDialog();
		JFrame newFrame = new JFrame();
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int fileCode = otherChooser.showSaveDialog(dia);
				if (fileCode == JFileChooser.APPROVE_OPTION) {
					File saveFile = otherChooser.getSelectedFile();
					try {
						FileOutputStream os = new FileOutputStream(saveFile);
						testBoard.SaveFile(os);
						System.out.println("File saved!");
						os.close();
					} catch (IOException f) {
						// TODO Auto-generated catch block
						f.printStackTrace();
					}
				}
				
			}

		});
		newFrame.add(saveButton);
		newFrame.setVisible(true);*/
		
	}

}
