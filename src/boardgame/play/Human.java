package boardgame.play;

import boardgame.data.*;
import boardgame.pieces.*;

/**
 * 
 * @author Muneeb Ahmed
 * @author Brock Berube
 * @author Jeremy Sears
 *
 */
public class Human extends Player {
	
	UserInterface ui;
	
	public Human() {
		super();
		ui = null;
	}

	public Human(String name, Color color) {
		super(name, color);
		ui = null;
	}
	
	public Human(String name, Color color, UserInterface ui) {
		super (name, color);
		this.ui = ui;
	}

	public UserInterface getUi() {
		return ui;
	}

	public void setUi(UserInterface ui) {
		this.ui = ui;
	}

	@Override
	public int Move(Board b) {
		Command c = ui.getCommand(this, b);		//getCommand() should check if move is legal
		b.Move(c);
		b.updateState(c);
		return b.getMateFlag();
	}

	@Override
	public int Move(Board b, Command c) {
		b.Move(c);
		b.updateState(c);
		return b.getMateFlag();
	}

}
