import java.util.ArrayList;

public class Player {


	public String name;
	private Piece piece;
	private int balance = 1500;
	private boolean isInJail = false; 
	private boolean JailCard = false; 
	private int position = 0;
	private ArrayList<Property> properties;
	private ArrayList <Street> streets = new ArrayList<Street>();

	
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
		if (!p.isMortgaged)
		{
		AddBalance(p.mortgage);	
		p.isMortgaged = true; 
	  	}	
	}
	
	public void Unmortgage (Property p) {
		if (p.isMortgaged) {
		  ReduceBalance((int)1.1*(p.mortgage));
		  p.isMortgaged = false;  
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
			sum1 += properties.get(i).price; //den jerw an prepei na kratw thn timh tou property 
		}                                        // h to sell price pou prepei na orisoume poso einai
		
		for (int i=0; i<streets.size();i++)
		{
			sum2 = sum2 + (streets.get(i).hotelCost + streets.get(i).houseCost);
		}
		
		if (balance<50 && sum1==0 && sum2==00) //to 50 kai ta 0 ta evala dokimastika 
			return true;
		return false;
	}
	
}
