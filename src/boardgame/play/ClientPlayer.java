package boardgame.play;

import boardgame.data.Command;
import boardgame.pieces.Board;
import boardgame.pieces.Color;
import java.io.*;
import java.net.*;

public class ClientPlayer extends Player {
	
	private Socket server;

	public ClientPlayer() {
		// TODO Auto-generated constructor stub
	}

	public ClientPlayer(String name, Color color) {
		super(name, color);
		// TODO Auto-generated constructor stub
	}
	
	public ClientPlayer(String name, Color color, String address) throws UnknownHostException, IOException {
		super(name, color);
		server = new Socket(address, 0);
	}
	
	public Socket getServer() {
		return server;
	}
	
	public void setServer(Socket server) {
		this.server = server;
	}
	
	@Override
	public int Move(Board b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Move(Board b, Command c) {
		// TODO Auto-generated method stub
		return 0;
	}

}
