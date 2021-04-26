import java.util.ArrayList;

public class Street extends Property{
	
	private String colour;
	private int propertiesInColour;
	private int[] rent = new int[6];
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

	public void Build() {
		
	}
	
	public void Demolish() {
		
	}


	public int CalcRent() {
		// svhste to otan einai na grapsete to kwdika , to egrapsa gia na mhn vgazei errros
		return 0;
	}

}
