public class Utility extends Property{

	public int rent;

	
	public Utility(String name, String cardImg, Player owner, int price, int mortgage, boolean isMortgaged, int rent) {
		super(name, cardImg, owner, price, mortgage, isMortgaged);
		this.rent = rent;
	}

	public void CalcRent(Player player) {
		boolean flag=false;
			for(int i=0; i<=owner.properties.size(); i++) {
				if(owner.properties.get(i).name.equals("Electric Company")) {
					for(int j=0; j<owner.properties.size(); j++) {
						if(owner.properties.get(j).name.equals("Water Works")) {
							flag=true;
							break;
						}
					}
				}
			}
		if (flag) {
			player.ReduceBalance(player.lastDice*10);
			owner.AddBalance(player.lastDice*10);
		}
		else {
			player.ReduceBalance(player.lastDice*4);
			owner.AddBalance(player.lastDice*4);
		}
	}

}