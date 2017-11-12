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
	
	/**
	 * Gets a player's command 
	 * 
	 * @param player Player to move
	 * @param b Board to move on
	 * @return player's command
	 */
	public Command getCommand(Player player, Board b);
	//Add more methods as they become necessary
	/**
	 * Gets a player's name from the user interface
	 * @param player color of the Player to be named
	 * @return name of the Player
	 */
	public String getPlayerName(Color player);	//method for users to enter names
	
	/**
	 * Updates the board after a move
	 */
	public void updateBoard(Board b);
	
	//public int getGameMode();	//method for users to determine which game mode to play
	
}
