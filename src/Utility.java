
public class Utility extends Property{

	private int rent;
	private static int totalUtilities = 2;

	
	public Utility(String name, String cardImg, Player owner, int price, int mortgage, boolean isMortgaged, int rent) {
		super(name, cardImg, owner, price, mortgage, isMortgaged);
		this.rent = rent;
	}


	public int CalcRent() {
		// svhste to otan einai na grapsete to kwdika , to egrapsa gia na mhn vgazei errros
		return 0;
	}
	
}
