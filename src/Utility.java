import java.util.ArrayList;

public class Utility extends Property{

	private int rent;
	private static int totalUtilities = 2;

	
	public Utility(String name, String cardImg, Player owner, int price, int mortgage, boolean isMortgaged, int rent) {
		super(name, cardImg, owner, price, mortgage, isMortgaged);
		this.rent = rent;
	}


	public void CalcRent(Player player) {
			 
		ArrayList<Player> players= new ArrayList<Player>(Main.players);
		int sum=0;
		for (Player player1:players) {
			for(int i=0; i<=player1.properties.size(); i++) {
				if (player1.properties.get(i).price==25) {
					sum++;
				}
			}
		}
					
		
	}
	
}
