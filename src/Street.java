import javax.swing.JOptionPane;

public class Street extends Property{
	
	public String colour;
	private int propertiesInColour;
	private int rent[];
	public int houses;
	public int hotel ;
	public int houseCost;
	public int hotelCost;

	
	

	public Street(String name, String cardImg, Player owner, int price, int mortgage, boolean isMortgaged,
			String colour, int propertiesInColour,  int[] rent, int houses, int hotel, int houseCost,
			int hotelCost) {
		super(name, cardImg, owner, price, mortgage, isMortgaged);
		this.colour = colour;
		this.propertiesInColour = propertiesInColour;
		this.rent =  rent;
		this.houses = houses;
		this.hotel = hotel;
		this.houseCost = houseCost;
		this.hotelCost = hotelCost;
	}
	
	public void Build(Player player, int number) {
		//GUI input for number of houses #1-4 and hotel #5
		//áí èåëåé íá ÷ôéóåé åíá hotel êáôåõèåéáí, ôé ãéíåôáé??
		//hotelcost ðñåðåé íá åéíáé housecost*5
		//èåëåé pop up window åðéâåâáéùóçò
		if (number==5) {
			if(hotel==0) {
				if(player.balance<hotelCost) {
					JOptionPane.showMessageDialog(null, "Not enough money to build a hotel!");
				}
				else {
					player.balance=player.balance-hotelCost;
					hotel++;
					houses=0;
				}	
			}
			else {
				JOptionPane.showMessageDialog(null, "Already have hotels");
			}
		}else {
			if(number+houses<=4 && hotel==0) {
				if(player.balance<(houseCost*number)) {
					JOptionPane.showMessageDialog(null, "Not enough money to build "+number+" houses!");
				}
				else {
					player.balance=player.balance-(houseCost*number);
					houses=houses+number;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Can't build more houses!!");
			}
		}
				
	}
	
	public void Demolish(Player player, int number) {
		if (number==5) {
			if(hotel==0) {
				JOptionPane.showMessageDialog(null, "You have no hotels to demolish");
			}
			else {
				hotel--;
				houses= 4;
				player.balance=player.balance+(hotelCost/2);
			}	
		}else {
			if(number>houses) {
				if(hotel==1) {
					JOptionPane.showMessageDialog(null, "You have a hotel, so you can't demolish houses!!");
				}
				else {
					JOptionPane.showMessageDialog(null, "You can't demolish so many houses");
				}
			}
			else {
				houses=houses-number;
				player.balance=player.balance+(houseCost/2)*number;
			}	
		}
	}


	public void CalcRent(Player player) {
		int sum=0;
		
		if (hotel==1) {
			sum=rent[5];
		}
		else if(hotel==0) {
			sum=rent[houses];			
		}
		
		
		int currbalance = player.balance; // saving current balance in case the player bankrupts from this rent
		player.ReduceBalance(sum); 
		
		if(currbalance >= sum) {
			owner.AddBalance(sum);
		}
		else {
			owner.AddBalance(currbalance);
		}
		JOptionPane.showMessageDialog(null, "Rent: "+sum+"€ payed.");
		
	}
	

}
