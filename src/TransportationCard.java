import java.awt.image.BufferedImage;

public class TransportationCard extends Card{
	private int numberOfRepositioning;

	public TransportationCard(BufferedImage cardImg, int numberOfRepositioning) {
		super(cardImg);
		this.numberOfRepositioning = numberOfRepositioning;
	}
	
	public void CallChangePosition (Player p) {
		
	}
	
}
