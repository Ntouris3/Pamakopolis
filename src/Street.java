import java.util.ArrayList;

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
		rent =  new int[6];
		this.houses = houses;
		this.hotel = hotel;
		this.houseCost = houseCost;
		this.hotelCost = hotelCost;
	}

	public void Build(Player player, int number) {
		//GUI input for number of houses #1-4 and hotel #5
		//αν θελει να χτισει ενα hotel κατευθειαν, τι γινεται??
		//hotelcost πρεπει να ειναι housecost*5
		//θελει pop up window επιβεβαιωσης
		if (number==5) {
			player.balance=player.balance-hotelCost;
			hotel++;
		}else {
			player.balance=player.balance-(houseCost*number);
			houses=houses+number;
		}
				
	}
	
	public void Demolish(Player player, int number) {
		if (number==5) {
			hotel--;
			player.balance=player.balance+(hotelCost/2);
		}else {
			houses=houses-number;
			player.balance=player.balance+(houseCost/2)*number;
		}
	}


	public int CalcRent(Player player) {
		int sum=0;
		
		if (hotel==1) {
			sum=sum+rent[5];
		}
		else if(hotel==0) {
			sum=sum+rent[houses];			
		}
		
		return sum;
	}
	

}
