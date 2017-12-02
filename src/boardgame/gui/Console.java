package boardgame.gui;

import java.awt.FlowLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class Console {
	
	private JFrame outputFrame;
	private JTextArea outputText;
	
	public Console() {
		
		outputFrame = new JFrame("Console Output");
		outputText = new JTextArea("Console Output\n");
		
		outputFrame.add(outputText);
		
		outputFrame.setLayout(new FlowLayout());
		outputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		outputFrame.setSize(400, 300);
		outputFrame.setVisible(true);
		
		PrintStream out = new PrintStream( new CustomOutputStream(outputText));
		System.setOut(out);
		
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
