import java.util.ArrayList;

public class Street extends Property{
	
	private String colour;
	private int propertiesInColour;
	private int rent[];
	public int houses;
	public int hotel ;
	private int houseCost;
	private int hotelCost;

	
	

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
		if (number==5) {
			player.balance=player.balance-hotelCost;
			hotel++;
		}else {
			player.balance=player.balance-(houseCost*number);
			houses=houses+number;
		}
				
	}
	
	public void Demolish(Player player, int number) {
		//XARA DES NAOYME
		if (number==5) {
			hotel--;
		}else {
			houses=houses-number;
		}
	}


	public int CalcRent(Player player) {
		int sum=0;
		sum=sum+rent[houses];
		
		if (hotel>=1) {
			sum=sum+(rent[5]*hotel);
		}
		
		return sum;
	}
	

}
