public class TransactionCard extends Card{

	private int moneyToTrasact;
	private boolean kindOfTransaction; // false = call reduce, true = call add
	
	public TransactionCard(String cardImgName, int moneyToTrasact, boolean kindOfTransaction) {
		super(cardImgName);
        this.moneyToTrasact = moneyToTrasact;
        this.kindOfTransaction = kindOfTransaction;
	}
	
    public TransactionCard(String cardImg, int moneyToTrasact){
        super(cardImg);
        this.moneyToTrasact = moneyToTrasact;
    }
	
    public void cardFunction(Player p){
    	
        if(!(this.cardImgName.equals("Chance_YHBAECOTB.png")) && !(this.cardImgName.equals("Community_Chest_GOO.png"))){ //direct transaction
            if (kindOfTransaction){
                p.AddBalance(moneyToTrasact);
            }else{
                p.ReduceBalance(moneyToTrasact);
            }
        }else if(this.cardImgName.equals("Chance_YHBAECOTB.png")){ // pay each player 50 card
                for(Player thisPlayer : Main.allPlayers){
                    if (thisPlayer.equals(p)){
                        p.ReduceBalance(50*Main.allPlayers.size());
                    }else{
                        thisPlayer.AddBalance(50);
                    }
                }
        }else{													//collect 50 from all players card
            for(Player thisPlayer : Main.allPlayers){
                if (thisPlayer.equals(p)){
                    p.AddBalance(50*Main.allPlayers.size());
                }else{
                    thisPlayer.ReduceBalance(50);
                }
            }
        }
    }
}
