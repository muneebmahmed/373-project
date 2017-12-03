package boardgame.gui;

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
