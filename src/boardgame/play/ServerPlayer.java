package boardgame.play;

import boardgame.data.Command;
import boardgame.pieces.Board;
import boardgame.pieces.Color;
import java.io.*;
import java.net.*;

public class ServerPlayer extends Player {
	
	private ServerSocket serverSock;
	private Socket client;

	public ServerPlayer() {
		// TODO Auto-generated constructor stub
	}

	public ServerPlayer(String name, Color color) {
		super(name, color);
		// TODO Auto-generated constructor stub
	}
	
	public ServerPlayer(String name, Color color, int address) throws IOException {
		super(name, color);
		serverSock = new ServerSocket(0);
		client = serverSock.accept();
		//TODO
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
