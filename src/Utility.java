import java.util.ArrayList;

public class Utility extends Property{

	private int rent;
	private static int totalUtilities = 2;

	
	public Utility(String name, String cardImg, Player owner, int price, int mortgage, boolean isMortgaged, int rent) {
		super(name, cardImg, owner, price, mortgage, isMortgaged);
		this.rent = rent;
	}


	@Override
	public int CalcRent(Player player, int sumdice) {
			 
		ArrayList<Player> players= new ArrayList<Player>(Main.players);
		boolean flag=false;
		for (Player player1:players) {
			for(int i=0; i<=player1.properties.size(); i++) {
				if(player1.properties.get(i).name.equals("Electric Company")) {
					for(int j=0; j<=player1.properties.size(); j++) {
						if(player1.properties.get(i).name.equals("Water Works"))
							flag=true;
						break;
					}
				}
			
			}
		}
		
		if (flag) {
			return sumdice*10;
		}
		else
			return sumdice*4;
				
		
	}

}
