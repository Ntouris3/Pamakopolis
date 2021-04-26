import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Stack;

public class Board {
	private ArrayList<Location> locations;
	private ArrayList<Player> playerTurn;
	private BufferedImage boardimg;
	private Stack<Card> chance;
	private Stack<Card> communityChest;

	public Board() {
		File file = new File("Locations.ser");
		
		try {
			FileInputStream fIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fIn);
			
			locations = (ArrayList<Location>) in.readObject();
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
	
	
}
