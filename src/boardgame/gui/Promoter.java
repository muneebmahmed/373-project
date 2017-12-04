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
	private PieceButton queen;
	private PieceButton rook;
	private PieceButton bishop;
	private PieceButton knight;
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
		queen.addActionListener(new PromotionListener());
		rook.addActionListener(new PromotionListener());
		bishop.addActionListener(new PromotionListener());
		knight.addActionListener(new PromotionListener());
		
		pieces = new JPanel();
		pieces.setLayout(new GridLayout(2, 2));
		pieces.add(queen);
		pieces.add(rook);
		pieces.add(bishop);
		pieces.add(knight);
		
		add(pieces);
		setMinimumSize(new Dimension (150, 150));
		promotion = PieceName.PAWN;
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
	
	private class PromotionListener implements ActionListener {

		@Override
		public synchronized void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			PieceButton source = (PieceButton)e.getSource();
			setPromotion(source.source.getPieceName());
		}
		
	}

}
