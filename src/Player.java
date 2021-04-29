import java.util.ArrayList;

public class Player {
	public String name;
	public Piece piece;
	public int balance = 1500;
	public boolean isInJail = false; 
	public boolean JailCard = false; 
	public int position = 0;
	public ArrayList<Property> properties;
	public int lastDice;
	
	public Player(String name, Piece piece) {
	
		this.name = name;
		this.piece = piece;
		properties = new ArrayList<Property>();
	}
	
	public void AddBalance (int amount) {
		balance = balance + amount;
	}
	
	public void ReduceBalance (int amount) {
		balance = balance - amount;
	}
	
	public void Buy (Property prop) {
		properties.add(prop);
		this.ReduceBalance(balance);
	}
	
	public void Sell (Property prop) {
		properties.remove(prop);
		this.ReduceBalance(balance);
	}
	public void ChangePosition (int newPosition) {
		
	}
	
	public void Trade (Player otherPlayer) {
		
	}
	
	public void AddToMortgage(Property prop) {
		
	}
	
	public void Unmortgage (Property prop) {
		
	}
	public ArrayList<Property> PropertiesToBuildIn (){
		// svhste to otan einai na grapsete to kwdika , to egrapsa gia na mhn vgazei errros
		ArrayList<Property> list = new ArrayList<>();
		return list;
	}
	
	public boolean isBankrupt () {
		// svhste to otan einai na grapsete to kwdika , to egrapsa gia na mhn vgazei errros
		return false;
	}
	public static boolean playerExists (String Name) {
		// svhste to otan einai na grapsete to kwdika , to egrapsa gia na mhn vgazei errros
		return false;
	}
}
