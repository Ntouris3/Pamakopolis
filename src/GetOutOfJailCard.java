public class GetOutOfJailCard extends Card{
	
	public GetOutOfJailCard(String cardImgName) {
		super(cardImgName);
	}

    public void cardFunction(Player p){
    	
       
        p.jailCards.add(this); //gives the get out of jail card to the player
		if (this.cardImgName.equals("Chance_GOOJF.png")){ //removing the card from the deck
			Main.allChances.remove(this);
		}else{
			Main.allCommunityChests.remove(this);
		}
    }
    
    public void restoreCard(Player p) {
    	p.jailCards.remove(this);
    	if (this.cardImgName.equals("Chance_GOOJF.png")){ 
			Main.allChances.add(this);
		}else{
			Main.allCommunityChests.add(this);
		}
    }

	
}
