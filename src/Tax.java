import javax.swing.JOptionPane;

public class Tax extends Location{
	
	public void CalcTax (Player player) {
		int tax =0;
		if (player.position == 4) {
			tax = 200;
		}
		
		if (player.position == 38) {
			tax =100;
		}
		
		if(player.balance>=tax) {
			player.ReduceBalance(tax);
		}
		else {
			JOptionPane.showMessageDialog(null,"You can't afford to pay the tax","Alert",JOptionPane.WARNING_MESSAGE);
		}
	}
}
