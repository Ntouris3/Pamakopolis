
public class Tax extends Location {
	
	private int prices[];
	
	public int CalcTax (Player player) {
		if (player.position == 2) {
			return prices[0];
		}
		
		if (player.position == 37) {
			return prices[1];
		}
		return 0;
	}
}
