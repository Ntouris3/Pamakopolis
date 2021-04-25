import java.awt.image.BufferedImage;

public class TransactionCard extends Card{
	private int moneyToTrasact;
	private boolean kindOfTransaction; // false = call reduce, true = call add
	
	
	public TransactionCard(BufferedImage cardImg, int moneyToTrasact, boolean kindOfTransaction) {
		super(cardImg);
		this.moneyToTrasact = moneyToTrasact;
		this.kindOfTransaction = kindOfTransaction;
	}
	
	public void Transact(Player p) {
		
	}
	
}
