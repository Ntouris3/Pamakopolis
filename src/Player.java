import java.util.ArrayList;

public class Player {


	public String name;

	private Piece piece;
	private int balance = 1500;
	private boolean isInJail = false; 
	private boolean JailCard = false; 
	private int position = 0;
	private ArrayList<Property> properties;
	
	public Player(String name, Piece piece) {
	
		this.name = name;
		this.piece = piece;
		properties = new ArrayList<Property>();
	}
	
	public void AddBalance (int amount) {
		balance +=  amount;
	}
	
	public void ReduceBalance (int amount) {
		balance -= amount;
	}
	
	public void Buy (Property prop) {
		properties.add(prop);
		this.ReduceBalance(prop.price);
		prop.owner = this.name;
	}
	
	public void Sell (Property p) {
		
	}
	public void ChangePosition (int newPosition) {
		
	}
	
	public void Trade (Player otherPlayer) {
		
	}
	
	public void AddToMortgage(Property p) {

			
	}
	
	public void Unmortgage (Property p) {
		if (p.isMortgage)
			ReduceBalance(1.1*(p.mortgage));
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
	
}
