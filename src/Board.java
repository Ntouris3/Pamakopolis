import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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
	public static Stack<Card> allChances = new Stack<Card>();
	public static Stack<Card> allCommunityChests = new Stack<Card>();
	private BufferedImage image;

	public Board() {
	                    
	        
		try {
			image = ImageIO.read(getClass().getResource("Board.jpg"));
		} catch (IOException e) {
				
				e.printStackTrace();
		}
		
		
	     this.setSize(image.getWidth(), image.getHeight());
	  

		//Loading the card files
		File file = new File("AllChances.ser");
		
		try {
			FileInputStream fIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fIn);
			
			allChances = (Stack<Card>) in.readObject();
			in.close();
			fIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file2 = new File("allCommunityChests.ser");
		
		try {
			FileInputStream fIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fIn);
			
			allCommunityChests = (Stack<Card>) in.readObject();
			in.close();
			fIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, getWidth() , getHeight() ,this); // see javadoc for more info on the parameters            
	    }

	}
	
	

