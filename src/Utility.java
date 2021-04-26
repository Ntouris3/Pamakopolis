
public class Utility extends Property{

	private int rent;
	private static int totalUtilities = 2;

	
	public Utility(String name, String cardImg, Player owner, int price, int mortgage, boolean isMortgaged, int rent) {
		super(name, cardImg, owner, price, mortgage, isMortgaged);
		this.rent = rent;
	}


	public int CalcRent(Player player) {
		
		for (int i=0; i<=Main.players.size(); i++) {
			if 
		}
		
		player.balance =player.balance-rent[houses];
		
		if (hotel>=1) {
			player.balance=player.balance-(rent[5]*hotel);
		}
		
		
		return player.balance;
	}
	
}
