import java.util.ArrayList;

public class Street extends Property{
	
	private String colour;
	private int propertiesInColour;
	private int rent[];
	private int houses;
	private int hotel ;
	private int houseCost;
	private int hotelCost;

	
	

	public Street(String name, String cardImg, Player owner, int price, int mortgage, boolean isMortgaged,
			String colour, int propertiesInColour, ArrayList<Integer> rent, int houses, int hotel, int houseCost,
			int hotelCost) {
		super(name, cardImg, owner, price, mortgage, isMortgaged);
		this.colour = colour;
		this.propertiesInColour = propertiesInColour;
		rent = new ArrayList<>();
		this.houses = houses;
		this.hotel = hotel;
		this.houseCost = houseCost;
		this.hotelCost = hotelCost;
	}

	public void Build(Player player, int number) {
		
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
