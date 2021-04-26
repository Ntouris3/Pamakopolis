public class TransactionCard extends Card{
	private int moneyToTrasact;
	private boolean kindOfTransaction; // false = call reduce, true = call add
	
	
	public TransactionCard(String cardImg, int moneyToTrasact, boolean kindOfTransaction) {
		super(cardImg);
		this.moneyToTrasact = moneyToTrasact;
		this.kindOfTransaction = kindOfTransaction;
	}
	
	public void Transact(Player p) {
		
	}
	
}
