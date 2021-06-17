import javax.swing.JOptionPane;

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
        		int numOfPlayersToPay = Main.allPlayers.size() - 1;
        		
        		int currbalance = p.balance; // saving current balance in case the player bankrupts from this reduction of money
    			p.ReduceBalance(numOfPlayersToPay*50); 
    			
    			if(currbalance >= numOfPlayersToPay*50) {
    				for(Player thisPlayer : Main.allPlayers){
                    if (!thisPlayer.equals(p)){
                       thisPlayer.AddBalance(50);
                    	}
    				}
    			}
    			else {
    				for(Player thisPlayer : Main.allPlayers){
                        if (!thisPlayer.equals(p)){
                           thisPlayer.AddBalance(currbalance/numOfPlayersToPay);
                        }
    			}
                
                    
                }
                p.ReduceBalance(50*Main.allPlayers.size());
        }else{													//collect 50 from all players card
            for(Player thisPlayer : Main.allPlayers){
                if (thisPlayer.equals(p)){
                    p.AddBalance(50*(Main.allPlayers.size() - 1));
                }else{
                	thisPlayer.ReduceBalance(50);
                }
            }
        }
    }
}
