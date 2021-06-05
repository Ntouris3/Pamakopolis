import java.awt.image.BufferedImage;

import java.awt.Graphics;

import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class Board extends JPanel {
	

	private BufferedImage image;

	public Board() {
	                    
	        
		try {
			image = ImageIO.read(getClass().getResource("Board.jpg"));
		} catch (IOException e) {
				
				e.printStackTrace();
		}
		
		
	     this.setSize(image.getWidth(), image.getHeight());
	  

		
			

	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, getWidth() , getHeight() ,this); // see javadoc for more info on the parameters            
	    }

	}
	
	

