package boardgame.play;

import boardgame.pieces.*;
import boardgame.data.*;

public abstract class Player {
	protected String name;
	protected Color color;
	
	public Player() {
		// TODO Auto-generated constructor stub
	}
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public abstract int Move(Board b);
	
	public abstract int Move(Board b, Command c);
	
	@Override
	public String toString() {
		return name;
	}

}
