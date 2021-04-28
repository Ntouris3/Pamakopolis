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
		this.setBounds(0, 0, 80, 80);
		
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
	
	public void MoveOnBoard(int diceValue) {
		
	}
}
