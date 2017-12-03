package boardgame.gui;

<<<<<<< HEAD
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class Console extends JTextArea{
	
	private PrintStream out;
	
	public Console () {
		
		this.setEditable(false);
		out = new PrintStream( new CustomOutputStream(this));
		System.setOut(out);		//uncomment this?
		
	}
	
	public Console (String text) {
		super(text);
		this.setEditable(false);
		out = new PrintStream( new CustomOutputStream(this));
		System.setOut(out);
	}
	
	public PrintStream getPrintStream() {
		return out;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 100);
	}

	
	public class CustomOutputStream extends OutputStream {
	    private JTextArea textArea;
	     
	    public CustomOutputStream(JTextArea textArea) {
	        this.textArea = textArea;
	        
=======
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class Console extends JTextArea{
	
	private PrintStream out;
	
	public Console () {
		
		this.setEditable(false);
		
		out = new PrintStream( new CustomOutputStream(outputText));
		System.setOut(out);
		
	}
	
	public PrintStream getPrintStream() {
		return out;
	}
	

	
	public class CustomOutputStream extends OutputStream {
	    private JTextArea textArea;
	     
	    public CustomOutputStream(JTextArea textArea) {
	        this.textArea = textArea;
>>>>>>> refs/remotes/origin/NotJeremyBranch
	    }
	     
	    @Override
	    public void write(int b) throws IOException {
	        // redirects data to the text area
	        textArea.append(String.valueOf((char)b));
	        // scrolls the text area to the end of data
	        textArea.setCaretPosition(textArea.getDocument().getLength());
	    }
	}

}
