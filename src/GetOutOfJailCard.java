public class GetOutOfJailCard extends Card{
	
	public GetOutOfJailCard(String cardImgName) {
		super(cardImgName);
	}

    public void cardFunction(Player p){
    	
        p.hasJailCard = true; //gives the get out of jail card to the player
        p.jailCards.add(this);
		if (this.cardImgName.equals("Chance_GOOJF.png")){ //removing the card from the deck
			Main.allChances.remove(this);
		}else{
			Board.allCommunityChests.remove(this);
		}
    }

	
}
