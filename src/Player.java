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
	public ArrayList <Street> streets = new ArrayList<Street>();
	public ArrayList <Street> streetsToBuildIn = new ArrayList<Street>();
	
	public Player(String name, Piece piece) {
	
		this.name = name;
		this.piece = piece;
		properties = new ArrayList<Property>();
		Main.allPlayers.add(this);
	}
	
	public void AddBalance (int amount) {
		balance += amount;
	}
	
	public void ReduceBalance (int amount) {
		balance = balance - amount;
	}
	
	public void Buy (Property prop) {
		
		Class c = prop.getClass();
		properties.add(prop);
		ReduceBalance(prop.price);
		prop.owner = this;
		if (prop instanceof Street)
		{
			streets.add((Street)prop);
		}
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
				if (s.colour == "Cyan")
					streetsToBuildIn.add(s);}
		return streetsToBuildIn;
	}
	
	public boolean isBankrupt () {
		int sum1 = 0, sum2 = 0;
		
		for (int i=0; i<properties.size(); i++)
		{
			sum1 += properties.get(i).price; //mortgage
		}
		
		for (int i=0; i<streets.size();i++)
		{
			sum2 += (streets.get(i).hotelCost + streets.get(i).houseCost);
		}
		
		if ( balance<0 && sum1==0 && sum2==0 )
			return true;
		return false;
	}
}
