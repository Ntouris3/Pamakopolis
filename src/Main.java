import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
	
	public static ArrayList<Player> allPlayers = new ArrayList<Player>();
	public static Stack<Card> allChances = new Stack<Card>();
	public static Stack<Card> allCommunityChests = new Stack<Card>();
	public static ArrayList<Location> locations = new ArrayList<Location>();
   
	
	public static void main(String[] args) {	

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


		//Loading the card files
				File file1 = new File("AllChances.ser");
				
				try {
					FileInputStream fIn = new FileInputStream(file1);
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
					FileInputStream fIn = new FileInputStream(file2);
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
		
		new LoginScreenGUI();
		
		//new GUI();

	}
	
	 
}
