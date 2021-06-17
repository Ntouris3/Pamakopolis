import javax.swing.JOptionPane;

public class Railroad extends Property{

	public int rent;


	
	public Railroad (String name, String cardImg, Player owner, int price, int mortgage, boolean isMortgaged, int rent) {
		super(name, cardImg, owner, price, mortgage, isMortgaged);
		this.rent = rent;
	}


	public void CalcRent(Player player) {
		int sum=0;
		
			for(int i=0; i<owner.properties.size(); i++) {
				if(owner.properties.get(i) instanceof Railroad) {
					sum++;
				}		
			}
			
			int currbalance = player.balance; // saving current balance in case the player bankrupts from this rent
			player.ReduceBalance(sum); 
			
			if(currbalance >= sum*rent) {
				owner.AddBalance(sum*rent);
			}
			else {
				owner.AddBalance(currbalance);
			}
		
			JOptionPane.showMessageDialog(null, "Rent: "+sum*rent+"€ payed.");
		
		
	}
					
		
	
	
}
