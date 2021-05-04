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
	public ArrayList <Street> streetsToBuildIn = new ArrayList<Street>();


	
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
		Class c = prop.getClass();
		properties.add(prop);
		ReduceBalance(prop.price);
		prop.owner = this;
		if (c.getName() == "Street")
		{
			streets.add((Street)prop);
		}
	}
	
	public void ChangePosition (int newPosition) {
		int temp = newPos % 40;
		if (temp == newPos) {         
			position = newPos;
			if (temp == 0) AddBalance(200);   //0 is the Start
		}
		else 
		{
			position = newPos % 40;
			AddBalance(200);
		}
	}
	
	public void Trade (Player otherPlayer) {
		
	}
	
	public void AddToMortgage(Property p) {
		if (!p.isMortgaged){
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
	public ArrayList<Street> PropertiesToBuildIn (){
		int sumRed=0, sumBlue=0, sumGreen=0, sumOrange=0, 
			sumBrown=0, sumYellow=0, sumPink=0, sumWhite=0;
		for(int i=0; i<streets.size(); i++)
		{
			if(streets.get(i).colour == "Red")
				sumRed++;
			else if (streets.get(i).colour == "Blue")
				sumBlue++;
			else if (streets.get(i).colour == "Green")
				sumGreen++;
			else if (streets.get(i).colour == "Orange")
				sumOrange++;
			else if (streets.get(i).colour == "Brown")
				sumBrown++;
			else if (streets.get(i).colour == "Yellow")
				sumYellow++;
			else if (streets.get(i).colour == "Pink")
				sumPink++;
			else if (streets.get(i).colour == "White")
				sumWhite++;
		}
		if (sumRed==3) {
			for (Street s:streets)
				if (s.colour == "Red")
					streetsToBuildIn.add(s);}
	    if (sumBlue==2) {
			for (Street s:streets)
				if (s.colour == "Blue")
					streetsToBuildIn.add(s);}
		if (sumGreen==3) {
			for (Street s:streets)
				if (s.colour == "Green")
					streetsToBuildIn.add(s);}
		if (sumOrange==3) {
			for (Street s:streets)
				if (s.colour == "Orange")
					streetsToBuildIn.add(s);}
		if (sumBrown==2) {
			for (Street s:streets)
				if (s.colour == "Brown")
					streetsToBuildIn.add(s);}
		if (sumYellow==3) {
			for (Street s:streets)
				if (s.colour == "Yellow")
					streetsToBuildIn.add(s);}
		if (sumPink==3) {
			for (Street s:streets)
				if (s.colour == "Pink")
					streetsToBuildIn.add(s);}
		if (sumWhite==3) {
			for (Street s:streets)
				if (s.colour == "White")
					streetsToBuildIn.add(s);}
		return streetsToBuildIn;
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
		
		if (balance<0 && sum1==0 && sum2==00) //to 50 kai ta 0 ta evala dokimastika 
			return true;
		return false;
	}
	
}
