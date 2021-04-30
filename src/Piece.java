import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Piece extends JComponent{
	
	public ImageIcon pieceimg;
	
	public Piece(ImageIcon aImage) {

		this.pieceimg = resizeImage(aImage);
		this.setOpaque(true);
		this.setBounds(650, 650, 80, 80);
		
	}
	
	
	//Kanei pio mikro to pioni gia na xwraei stis perioxes toy epitrapeziou
	private ImageIcon resizeImage(ImageIcon aImage) {
		 Image img = aImage.getImage();
		 BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);
		 Graphics g = bi.createGraphics();
		 g.drawImage(img, 0, 0, 55, 45, null);
		 ImageIcon newImg = new ImageIcon(bi);
		 
		 return newImg;
		 
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    //g.drawImage(pieceimg, 0, 0, getWidth() , getHeight() ,this); // see javadoc for more info on the parameters     
	    pieceimg.paintIcon(this, g, 0, 0);
	}
	
	public void MoveOnBoard(Player p ,int newPosition) {
		int currPiecePos = p.position;
		
		
		
		while ( currPiecePos != newPosition ) {
			
			if(currPiecePos == 0) {
				this.setBounds(this.getX()-96, this.getY(), 55, 45);
				GUI.gameP.paintImmediately(getX()+96, getY(), getWidth(), getHeight());
			}
			else if(currPiecePos < 9) {
				this.setBounds(this.getX()-58, this.getY(), 55, 45);
				GUI.gameP.paintImmediately(getX()+58, getY(), getWidth(), getHeight());
			}
			else if(currPiecePos == 9) {
				this.setBounds(this.getX()-90, this.getY(), 55, 45);
				GUI.gameP.paintImmediately(getX()+90, getY(), getWidth(), getHeight());
			}
			else if(currPiecePos == 10) {
				this.setBounds(this.getX(), this.getY()-96, 55, 45);
				GUI.gameP.paintImmediately(getX(), getY()+96, getWidth(), getHeight());
			}
			else if(currPiecePos < 19) {
				this.setBounds(this.getX(), this.getY()-58, 55, 45);
				GUI.gameP.paintImmediately(getX(), getY()+58, getWidth(), getHeight());
			}
			else if(currPiecePos == 19) {
				this.setBounds(this.getX(), this.getY()-90, 55, 45);
				GUI.gameP.paintImmediately(getX(), getY()+90, getWidth(), getHeight());
			}
			else if(currPiecePos == 20) {
				this.setBounds(this.getX()+90, this.getY(), 55, 45);
				GUI.gameP.paintImmediately(getX()-90, getY(), getWidth(), getHeight());
			}
			else if(currPiecePos < 29) {
				this.setBounds(this.getX()+58, this.getY(), 55, 45);
				GUI.gameP.paintImmediately(getX()-58, getY(), getWidth(), getHeight());
			}
			else if(currPiecePos == 29) {
				this.setBounds(this.getX()+96, this.getY(), 55, 45);
				GUI.gameP.paintImmediately(getX()-96, getY(), getWidth(), getHeight());
			}
			else if(currPiecePos == 30) {
				this.setBounds(this.getX(), this.getY()+90, 55, 45);
				GUI.gameP.paintImmediately(getX(), getY()-90, getWidth(), getHeight());
			}
			else if(currPiecePos < 39) {
				this.setBounds(this.getX(), this.getY()+58, 55, 45);
				GUI.gameP.paintImmediately(getX(), getY()-58, getWidth(), getHeight());
			}
			else if(currPiecePos == 39) {
				this.setBounds(this.getX(), this.getY()+96, 55, 45);
				GUI.gameP.paintImmediately(getX(), getY()-96, getWidth(), getHeight());
			}
			
			currPiecePos= (currPiecePos + 1)%40;
			
			
			GUI.gameP.paintImmediately(getX(), getY(), getWidth(), getHeight());
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
		}
		
		p.position = currPiecePos;
		
		
	}
}
