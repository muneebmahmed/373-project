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
public class Computer extends Player {
	
	public Evaluator evaluator;

	public Computer() {
		// TODO Auto-generated constructor stub
		evaluator = new Evaluator();
	}

	public Computer(String name, Color color) {
		super(name, color);
		evaluator = new Evaluator();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int Move(Board b) {
		// TODO Auto-generated method stub
		evaluator.setBoard(b);
		Command c = evaluator.getBestMove(color);
		c = b.formatCommand(c);
		b.Move(c);
		b.updateState(c);
		System.out.println("I moved " + c);
		return c.castleMode;
	}

	@Override
	public int Move(Board b, Command c) {
		// TODO Auto-generated method stub
		b.Move(c);
		b.updateState(c);
		return c.castleMode;
	}

}
