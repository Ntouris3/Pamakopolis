import java.util.ArrayList;

public class Player {
	public String name;
	public Piece piece;
	public int balance = 1500;
	public boolean isInJail = false;
    ArrayList<GetOutOfJailCard> jailCards = new ArrayList<GetOutOfJailCard>();
	public int position = 0;
	public ArrayList<Property> properties;
	public int lastDice;
	
	public Player(String name, Piece piece) {
	
		this.name = name;
		this.piece = piece;
		properties = new ArrayList<Property>();
		Main.allPlayers.add(this);
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
		prop.owner = this;
	}
	



	public void ChangePosition (int newPos) {
		int temp = newPos % 40;
		
		this.piece.MoveOnBoard(this, newPos % 40);
		if (temp == newPos) {         
				this.position = newPos;
			if (temp == 0) AddBalance(200);   //0 is the Start
		}
		else 
		{
			this.position = newPos % 40;
			AddBalance(200);
		}
		
	}
	
	public void Trade (Player otherPlayer) {
		
	}
	
	public void AddToMortgage(Property prop) {
	if (!prop.isMortgaged)
		{
			AddBalance(prop.mortgage);	
			prop.isMortgaged = true;
		}	
	}
	
	public void Unmortgage (Property prop) {
		if (prop.isMortgaged) {
		  ReduceBalance((int)1.1*(prop.mortgage));
		  prop.isMortgaged = false;  
		}
	}
	public ArrayList<Property> PropertiesToBuildIn (){
		// svhste to otan einai na grapsete to kwdika , to egrapsa gia na mhn vgazei errros
		ArrayList<Property> list = new ArrayList<>();
		return list;
	}
	
	public boolean isBankrupt () {
		int sum1 = 0, sum2 = 0;
		
		for (int i=0; i<properties.size(); i++)
		{
			sum1 += properties.get(i).price;//mortgage
		}
		
		for (int i=0; i<streets.size();i++)
		{
			sum2 += (streets.get(i).hotelCost + streets.get(i).houseCost);
		}
		
		if (balance<0 && sum1==0 && sum2==0)
			return true;
		return false;
	}
}
