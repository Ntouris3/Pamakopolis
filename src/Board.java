import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel {
	private ArrayList<Location> locations;
	private ArrayList<Player> playerTurn;
	private BufferedImage boardimg;
	private Stack<Card> chance;
	private Stack<Card> communityChest;
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
	
	

