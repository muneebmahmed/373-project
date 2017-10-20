package boardgame.play;

import boardgame.data.*;
import boardgame.pieces.*;

/*
 * Interface for GUIs and CLIs to implement
 * 
 * Specifies functions that must be able to be done with any user interface
 * 
 */
public interface UserInterface {
	
	public Command getCommand(Player player, Board b);
	//Add more methods as they become necessary
	
}
