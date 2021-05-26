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

	public String getCardImgName() {
		return cardImgName;
	}
}
