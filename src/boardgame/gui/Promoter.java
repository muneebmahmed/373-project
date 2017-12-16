package boardgame.gui;

import boardgame.pieces.*;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Promoter extends JFrame {

	private JPanel pieces;
	public PieceButton queen;
	public PieceButton rook;
	public PieceButton bishop;
	public PieceButton knight;
	private volatile PieceName promotion;
	
	public Promoter() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}
	
	public Promoter(Color color) {
		Queen q = new Queen(color);
		Rook r = new Rook(color);
		Bishop b = new Bishop(color);
		Knight k = new Knight(color);
		queen = PieceButton.createPieceButton(q);
		rook = PieceButton.createPieceButton(r);
		bishop = PieceButton.createPieceButton(b);
		knight = PieceButton.createPieceButton(k);
		
		pieces = new JPanel();
		pieces.setLayout(new GridLayout(2, 2));
		pieces.add(queen);
		pieces.add(rook);
		pieces.add(bishop);
		pieces.add(knight);
		
		add(pieces);
		setMinimumSize(new Dimension (150, 170));
		promotion = PieceName.PAWN;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocation(200, 200);
		
	}
	
	public synchronized PieceName promote() {
		while (promotion == PieceName.PAWN) {
			try {
				System.out.println("Waiting in Thread: " + Thread.currentThread().getName());
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return promotion;
	}
	
	public synchronized void setPromotion(PieceName pName) {
		promotion = pName;
		notifyAll();
	}
	
	public static PieceName getPromotionPiece(Color color) {
		Promoter p = new Promoter(color);
		p.setVisible(true);
		PieceName prom = p.promote();
		p.dispose();
		return prom;
	}

}
