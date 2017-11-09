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
	//public String getPlayerName(Color player);	//method for users to enter names
	
	//public int getGameMode();	//method for users to determine which game mode to play
	
}
