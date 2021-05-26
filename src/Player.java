import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class Player {
	public String name;
	public Piece piece;
	public int balance = 1500;
	public boolean isInJail = false;
    ArrayList<GetOutOfJailCard> jailCards = new ArrayList<GetOutOfJailCard>();
	public int position = 0;
	public ArrayList<Property> properties;
	public int lastDice;
	public ArrayList <Street> streets = new ArrayList<Street>();
	public ArrayList <Street> streetsToBuildIn = new ArrayList<Street>();
	public int jailTurns = 0;
	
	public Player(String name, Piece piece) {
	
		this.name = name;
		this.piece = piece;
		properties = new ArrayList<Property>();
		Main.allPlayers.add(this);
	}
	
	public void AddBalance (int amount) {
		balance += amount;
	}
	
	public void ReduceBalance (int amount) {
		balance = balance - amount;
	}
	
	public void Buy (Property prop) {
		
		Class c = prop.getClass();
		properties.add(prop);
		ReduceBalance(prop.price);
		prop.owner = this;
		if (prop instanceof Street)
		{
			streets.add((Street)prop);
		}
	}
	



	public void ChangePosition (int newPos) {
		int temp = newPos % 40;
		
		this.piece.MoveOnBoard(this, temp);
		if (temp == newPos) {         
				this.position = newPos;
			if (temp == 0 && this.isInJail==false) AddBalance(200);   //0 is the Start
		}
		else 
		{
			this.position = temp;
			AddBalance(200);
		}
		
	}
	
	public void Trade (Player otherPlayer, ArrayList<Property> currToOtherLocation, ArrayList<GetOutOfJailCard> currToOtherJailCards, int currToOtherMoney,
			ArrayList<Property> otherToCurrLocation, ArrayList<GetOutOfJailCard> otherToCurrJailCards, int otherToCurrMoney) {
		int noTrade = 0;
		if (!(currToOtherLocation.isEmpty())) {
			this.properties.removeAll(currToOtherLocation);
			otherPlayer.properties.addAll(currToOtherLocation);
		}else {
			noTrade++;
		}
		if (!(currToOtherJailCards.isEmpty())) {
			this.jailCards.removeAll(currToOtherJailCards);
			otherPlayer.jailCards.addAll(currToOtherJailCards);
		}else {
			noTrade++;
		}
		this.ReduceBalance(currToOtherMoney);
		otherPlayer.AddBalance(currToOtherMoney);
		if(currToOtherMoney == 0)
			noTrade++;
		
		if (!(otherToCurrLocation.isEmpty())) {
			otherPlayer.properties.removeAll(otherToCurrLocation);
			this.properties.addAll(otherToCurrLocation);
		}else {
			noTrade++;
		}
		if (!(otherToCurrJailCards.isEmpty())) {
			otherPlayer.jailCards.removeAll(otherToCurrJailCards);
			this.jailCards.addAll(otherToCurrJailCards);
		}else {
			noTrade++;
		}
		otherPlayer.ReduceBalance(otherToCurrMoney);
		this.AddBalance(otherToCurrMoney);
		if (otherToCurrMoney == 0)
			noTrade++;
		if (noTrade == 6)
			JOptionPane.showMessageDialog(null, "No trade was input");
		else
			JOptionPane.showMessageDialog(null, "Trade Succeed \n"+currToOtherLocation+"\n"+currToOtherJailCards+"\n"+currToOtherMoney+"€\n"+" were transfered to"+otherPlayer.name+
					otherToCurrLocation+"\n"+otherToCurrJailCards+"\n"+otherToCurrMoney+"€\n"+" were transfered to"+this.name);
		
	}
	
	public void AddToMortgage(Property prop) {
		if (!prop.isMortgaged)
			{
				AddBalance(prop.mortgage);	
				prop.isMortgaged = true;
			}	
	}
	
	public void Unmortgage (Property prop) {
		if (prop.isMortgaged) {
		  ReduceBalance((int) (1.1*prop.mortgage));
		  prop.isMortgaged = false;  
		}
	}
	public ArrayList<Street> PropertiesToBuildIn (){
		int sumRed=0, sumBlue=0, sumGreen=0, sumOrange=0, 
			sumBrown=0, sumYellow=0, sumPink=0, sumWhite=0;
		for(int i=0; i<streets.size(); i++)
		{
			if(streets.get(i).colour == "Red")
				sumRed++;
			else if (streets.get(i).colour == "Blue")
				sumBlue++;
			else if (streets.get(i).colour == "Green")
				sumGreen++;
			else if (streets.get(i).colour == "Orange")
				sumOrange++;
			else if (streets.get(i).colour == "Brown")
				sumBrown++;
			else if (streets.get(i).colour == "Yellow")
				sumYellow++;
			else if (streets.get(i).colour == "Pink")
				sumPink++;
			else if (streets.get(i).colour == "Cyan")
				sumWhite++;
		}
		if (sumRed==3) {
			for (Street s:streets)
				if (s.colour == "Red")
					streetsToBuildIn.add(s);}
	    if (sumBlue==2) {
			for (Street s:streets)
				if (s.colour == "Blue")
					streetsToBuildIn.add(s);}
		if (sumGreen==3) {
			for (Street s:streets)
				if (s.colour == "Green")
					streetsToBuildIn.add(s);}
		if (sumOrange==3) {
			for (Street s:streets)
				if (s.colour == "Orange")
					streetsToBuildIn.add(s);}
		if (sumBrown==2) {
			for (Street s:streets)
				if (s.colour == "Brown")
					streetsToBuildIn.add(s);}
		if (sumYellow==3) {
			for (Street s:streets)
				if (s.colour == "Yellow")
					streetsToBuildIn.add(s);}
		if (sumPink==3) {
			for (Street s:streets)
				if (s.colour == "Pink")
					streetsToBuildIn.add(s);}
		if (sumWhite==3) {
			for (Street s:streets)
				if (s.colour == "Cyan")
					streetsToBuildIn.add(s);}
		return streetsToBuildIn;
	}
	
	public boolean isBankrupt () {
		int sum1 = 0, sum2 = 0;
		
		for (int i=0; i<properties.size(); i++)
		{
			sum1 += properties.get(i).price; //mortgage
		}
		
		for (int i=0; i<streets.size();i++)
		{
			sum2 += (streets.get(i).hotelCost + streets.get(i).houseCost);
		}
		
		if ( balance<0 && sum1==0 && sum2==0 )
			return true;
		return false;
	}
	
	public void ShowJailFrame(JButton button) {
		
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		JLabel playerName = new JLabel("Name: "+this.name);
		JLabel playerBalance = new JLabel("Balance: "+this.balance+"$");
		JButton payButton = new JButton("Pay Jail Fee");
		JButton useCardButton = new JButton("Use Get Out Of Jail Card");
		JButton rollButton = new JButton("Roll Dice");
		JLayeredPane jl = new JLayeredPane();
		
		
		playerName.setFont(new Font("Serif", Font.PLAIN,30));
		playerBalance.setFont(new Font("Serif", Font.PLAIN, 30));
		
		if(this.jailCards.size()==0) {
			useCardButton.setVisible(false);
		}
		
		if(this.balance<50) {
			payButton.setVisible(false);
		}
		
		jl.setPreferredSize(new Dimension(120,41));
		
		Dice dice1 = new Dice(0,0, 40, 40);
		jl.add(dice1);
		
		Dice dice2 = new Dice(80, 0, 40, 40);
		jl.add(dice2);
		
		p.setLayout(new FlowLayout());
		p.add(playerName);
		p.add(playerBalance);
		p.add(useCardButton);
		p.add(payButton);
		p.add(rollButton);
		p.add(jl);
		
		
		
		
	
		
		this.jailTurns++;
		
		
		useCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jailCards.get(0).getCardImgName()=="Chance_GOOJF") {
					Main.allChances.add(jailCards.get(0));
				}
				else {
					Main.allCommunityChests.add(jailCards.get(0));
				}
				jailCards.remove(0);
				isInJail=false;
				jailTurns=0;
				button.setVisible(true);
				JOptionPane.showMessageDialog(null,"You succesfully used your card to get out of jail","Alert",JOptionPane.INFORMATION_MESSAGE);
				f.dispose();
			}
		});
		

		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReduceBalance(50);
				isInJail=false;
				jailTurns=0;
				button.setVisible(true);
				JOptionPane.showMessageDialog(null,"You succesfully payed the fee and got out of jail","Alert",JOptionPane.INFORMATION_MESSAGE);
				f.dispose();
			}
		});
		
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dice1.rollDice();
				dice2.rollDice();
				if(dice1.getFaceValue()==dice2.getFaceValue()) {
					isInJail = false;
					jailTurns = 0;
					JOptionPane.showMessageDialog(null,"You got out of jail thanks to your dice roll","Alert",JOptionPane.INFORMATION_MESSAGE);
					f.dispose();
					ChangePosition(dice1.getFaceValue()+dice2.getFaceValue()+10);
				}
				else {
					if(jailTurns==3) {
						if(balance<50) {
							isInJail = false;
							jailTurns = 0;
							JOptionPane.showMessageDialog(null,"You waited 3 rounds you can now leave jail","Alert",JOptionPane.INFORMATION_MESSAGE);
							f.dispose();
							ChangePosition(dice1.getFaceValue()+dice2.getFaceValue()+10);
						}else {
							isInJail = false;
							jailTurns = 0;
							ReduceBalance(50);
							JOptionPane.showMessageDialog(null,"You didn't throw doubles, 50$ have been removed from you balance\nYou can now leave jail","Alert",JOptionPane.INFORMATION_MESSAGE);
							f.dispose();
							ChangePosition(dice1.getFaceValue()+dice2.getFaceValue()+10);
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"You didn't throw doubles\nYou remain in jail","Alert",JOptionPane.INFORMATION_MESSAGE);
						f.dispose();
					}
					
				}
			}	
		});
		
		
		
		f.setVisible(true);
		f.setSize(420,400); 
		f.setResizable(false);
		f.setTitle("Jail");
		f.setContentPane(p);
	}
}

