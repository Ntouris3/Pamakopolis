import java.util.ArrayList;

public class Utility extends Property{

	private int rent;
	private static int totalUtilities = 2;

	
	public Utility(String name, String cardImg, Player owner, int price, int mortgage, boolean isMortgaged, int rent) {
		super(name, cardImg, owner, price, mortgage, isMortgaged);
		this.rent = rent;
	}


	public int CalcRent(Player player) {
		
		 ArrayList<Player> players=new ArrayList<Player>(getPlayers);
		
		for (int i=0; i<=players.size(); i++) {
			
		}
					
		
		return player.balance;
	}
	
}
