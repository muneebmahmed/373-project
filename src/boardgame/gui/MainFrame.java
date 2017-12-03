package boardgame.gui;

import boardgame.play.*;
import boardgame.pieces.*;
import boardgame.data.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame {
	
	public GUI gui;
	
	private JMenuBar menu;
	private JMenu file;
	private JMenuItem save;
	private JMenuItem exit;
	private JMenuItem open;
	private JMenu edit;
	private JMenuItem setWhite;
	private JMenuItem setBlack;
	
	private JFileChooser chooser;
	private JDialog dialog;
	private JTextArea text;

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
		setWhite = new JMenuItem("Set White");
		setBlack = new JMenuItem("Set Black");
		save.addActionListener(new MenuListener());
		open.addActionListener(new MenuListener());
		exit.addActionListener(new MenuListener());
		setWhite.addActionListener(new MenuListener());
		setBlack.addActionListener(new MenuListener());
		file.add(open);
		file.add(save);
		file.add(exit);
		edit.add(setWhite);
		edit.add(setBlack);
		
		menu.add(file);
		menu.add(edit);
		this.setJMenuBar(menu);
		
		this.setLayout(new BorderLayout());
		add(this.gui, BorderLayout.CENTER);
		
		text = new Console();
		text.setMaximumSize(new Dimension (500, 100));
		this.add(text, BorderLayout.SOUTH);
		
		chooser = new JFileChooser();
		dialog = new JDialog();
		
	}
	
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JMenuItem source = (JMenuItem)e.getSource();
			
		}
		
	}

}
