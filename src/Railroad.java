
public class Railroad extends Property{

	private int rent=25;
	private static int totalRailroads;

	
	public Railroad(String name, String cardImg, Player owner, int price, int mortgage, boolean isMortgaged, int rent) {
		super(name, cardImg, owner, price, mortgage, isMortgaged);
		this.rent = rent;
		totalRailroads++;
	}


	public int CalcRent(Player player) {
		
		player.balance =player.balance-(rent*Railroad.totalRailroads);
		
		return player.balance;
	}
	
	
}
