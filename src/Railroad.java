import java.util.ArrayList;

public class Railroad extends Property{

	private int rent;
	private static int totalRailroads=2;

	
	public Railroad(String name, String cardImg, Player owner, int price, int mortgage, boolean isMortgaged, int rent) {
		super(name, cardImg, owner, price, mortgage, isMortgaged);
		this.rent = rent;
	}


	public void CalcRent(Player player) {
		 
		ArrayList<Player> players= new ArrayList<Player>(Main.players);
		int sum=0;
		for (Player player1:players) {
			for(int i=0; i<=player1.properties.size(); i++) {
				if (player1.properties.get(i).price==rent) {
					sum++;
				}
			}
		}
		
		player.balance=player.balance-(sum*rent);

	}
					
		
	
	
}