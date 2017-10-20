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

	public Human() {
		// TODO Auto-generated constructor stub
	}

	public Human(String name, Color color) {
		super(name, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int Move(Board b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Move(Board b, Command c) {
		// TODO Auto-generated method stub
		b.Move(c);
		return 0;
	}

}
