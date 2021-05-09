
public class Tax extends Location{
	
	public void CalcTax (Player player) {
		if (player.position == 2) {
			player.ReduceBalance(200);
		}
		
		if (player.position == 37) {
			player.ReduceBalance(100);
		}
	}
}
