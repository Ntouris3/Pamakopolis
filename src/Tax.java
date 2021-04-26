
public class Tax extends Location {
	
	private int prices[];
	
	public void CalcTax (Player player) {
		if (player.position == 2) {
			player.balance=player.balance-prices[0];
		}
		
		if (player.position == 37) {
			player.balance=player.balance-prices[1];
		}
	}
}
