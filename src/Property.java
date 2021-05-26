
public abstract class Property extends Location{
	protected String name;
	protected String cardImg;
	protected Player owner ;
	protected int price;
	protected int mortgage;
	protected boolean isMortgaged;
	
	
	public Property(String name, String cardImg, Player owner, int price, int mortgage, boolean isMortgaged) {
		this.name = name;
		this.cardImg = cardImg;
		this.owner = owner;
		this.price = price;
		this.mortgage = mortgage;
		this.isMortgaged = isMortgaged;
	}


	public abstract void CalcRent (Player player);


	public Player getOwner() {
		return owner;
	}
	
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}


	public String toString() {
		return name;
		
	}

	public int getPrice() {
		return price;
	}





	


	
	
	
}
