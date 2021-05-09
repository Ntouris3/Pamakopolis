public class TransportationCard extends Card{

	 int n;

	public TransportationCard(String cardImgName, int n) {
		super(cardImgName);
		this.n = n;
	}
	
    public void cardFunction (Player p){
    	
        int newPosition;
        if(this.cardImgName.equals("Chance_GB3S.png")){ //go back 3 steps card: int this case n is the number of locations to go back
            newPosition = p.position-n;
        }else if (this.cardImgName.equals("Chance_GTJ.png") || this.cardImgName.equals("Community_Chest_GTJ.png")){ //go to jail cards
            p.isInJail = true;
            newPosition = n;
        }else{	//direct transportation, n is the final new position
            newPosition = n;
        }
        p.ChangePosition(newPosition);
    }
	
}
