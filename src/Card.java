import java.io.Serializable;

public abstract class Card implements Serializable{ 
	/*
	 * abstract superclass for all cards
	*/
	 String cardImgName;

	public Card(String cardImg) {
		this.cardImgName = cardImg;
	}
	
	abstract public  void cardFunction(Player p); //called in any case if a card has to do something
	
	public void manageCardInQueue(Player p) {
    	int locationPosition = p.position;
    	if (locationPosition ==2 || locationPosition ==17 || locationPosition ==33) { 
			Main.allCommunityChests.remove();
			if (!(this.cardImgName.equals("Community_Chest_GTJ.png"))){
				Main.allCommunityChests.add(this);
			}
		}else if (locationPosition ==7 || locationPosition ==22 || locationPosition ==36) {
			Main.allChances.remove();
			if (!(this.cardImgName.equals("Chance_GTJ.png"))){
				Main.allChances.add(this);
			}
		}else {
			System.out.println("Error");
		}
	}
}
