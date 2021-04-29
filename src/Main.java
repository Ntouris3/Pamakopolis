import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Main {
	
	public static ArrayList<Player> allPlayers;
	
	public static void main(String[] args) {	
		ArrayList<Player> allPlayers=new ArrayList<Player>();
		ArrayList<Location> locations;
		
		new LoginScreenGUI();
		
		//new GUI();
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
