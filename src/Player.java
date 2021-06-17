import java.awt.BorderLayout;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Player {
	public String name;
	public Piece piece;
	public int balance = 300;
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
		
		this.balance = this.balance - amount;
		if(this.balance < 0) {
			bankruptGUI(this);
		}
		
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
			JOptionPane.showMessageDialog(null, "Trade Succeed \n"+currToOtherLocation+"\n"+currToOtherJailCards+"\n"+currToOtherMoney+"€\n"+" were transfered to "+otherPlayer.name+
					"\n"+otherToCurrLocation+"\n"+otherToCurrJailCards+"\n"+otherToCurrMoney+"€\n"+" were transfered to"+this.name);
		
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
		if(this.balance < 0 && allMortgaged() && this.jailCards.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
	public void ShowJailFrame(JButton button) {
		
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		GUI.setColor(p);
		JLabel playerName = new JLabel("Name: "+this.name);
		JLabel playerBalance = new JLabel("Balance: "+this.balance+"$");
		JButton payButton = new JButton("Pay Jail Fee");
		JButton useCardButton = new JButton("Use Get Out Of Jail Card");
		JButton rollButton = new JButton("Roll Dice");
		JLayeredPane jl = new JLayeredPane();
		
		
		playerName.setFont(new Font("Serif", Font.PLAIN,20));
		playerBalance.setFont(new Font("Serif", Font.PLAIN, 20));
		payButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		useCardButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		rollButton.setFont(new Font("SansSerif", Font.BOLD, 12));
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
		
	
	private void mortgageWhenBankrupt(Player currPlayer) {
		JFrame o = new JFrame();
		
		JLabel nameLabel = new JLabel("Name:"+this.name);
		nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		JLabel balanceLabel = new JLabel("Balance:"+String.valueOf(this.balance)+"€");
		balanceLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		JPanel p = new JPanel();
		GUI.setColor(p);
		
		
		JButton b1 = new JButton("Mortgage");
		b1.setFont(new Font("SansSerif", Font.BOLD, 12));
		b1.setVisible(false);
		
		
		JLabel label1 = new JLabel("");
		label1.setFont(new Font("SansSerif", Font.PLAIN, 20));

		JList <Property> list = new JList(this.properties.toArray());
		DefaultListModel<Property> model;
		
		p.setLayout(new FlowLayout());
		p.add(nameLabel);
		p.add(balanceLabel);
		p.add(list);
		p.add(b1);
		
		p.add(label1);
				
		model = new DefaultListModel<Property>();
		for(Property prop: this.properties) {
			model.addElement(prop);
		}
		list.setModel(model);
				
		//ENABLING AND DISABLING THE BUTTONS DEPENDING PLAYERS SELECTION
		list.addListSelectionListener( new ListSelectionListener() {
			   @Override
				public void valueChanged(ListSelectionEvent e) {
				   if(list.getSelectedValue().isMortgaged==true) {
		        		b1.setVisible(false);
		        		p.revalidate();
						p.repaint();
		        	}
		        	else {
		        		b1.setVisible(true);
		        		p.revalidate();
						p.repaint();
		        	}	
				}
		});
		
		
		//Mortgage Button//
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Property pro = list.getSelectedValue();
				currPlayer.AddToMortgage(pro);
				label1.setText(pro.name+" is now on Mortgage");
        		b1.setVisible(false);
        		balanceLabel.setText("Balance:"+String.valueOf(currPlayer.balance)+"€");
        		p.revalidate();
				p.repaint();
			}
		});
					
				
				o.setSize(350,500); 
				o.setContentPane(p);
				o.setTitle("Mortgage");
				o.setVisible(true);
				o.setResizable(false);
			
	
	}	
	
	private boolean allMortgaged() {
		
		for(int i=0; i<this.properties.size(); i++) {
			if(!this.properties.get(i).isMortgaged) {
				return false;
			}
		}
		return true;
	}
	
	public void bankruptGUI(Player aPlayer) {
		if(!isBankrupt()) {
			JFrame f = new JFrame();
			JPanel p = new JPanel();
			GUI.setColor(p);
			JTextArea text= new JTextArea("Oh no " + this.name + ", you are almost bankrupt! Mortgage or Trade your belogings to avoid loosing the game!");
			text.getLineWrap();
			JLabel playerBalance = new JLabel("Your balance: "+this.balance+"$");
			JButton mortgageButton = new JButton("Mortgage");
			JButton tradeButton = new JButton("Trade");
		
			f.add(p);
			
			p.add(text);
			text.setFont(new Font("SansSerif", Font.PLAIN,15));
			p.add(playerBalance);
			playerBalance.setFont(new Font("SansSerif", Font.BOLD,15));
			p.add(mortgageButton);
			mortgageButton.setFont(new Font("SansSerif", Font.BOLD,12));
			p.add(tradeButton);
			tradeButton.setFont(new Font("SansSerif", Font.BOLD,12));
			
			
			
			
			mortgageButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			
			tradeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			
			mortgageButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mortgageWhenBankrupt(aPlayer);
			}});
			
		
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
			f.setSize(420,400); 
			f.setResizable(false);
			f.setTitle("");
			f.setContentPane(p);
		}
		else {
			JOptionPane.showMessageDialog(null,"Player " + this.name + " is bankrupt , R.I.P. ");
			for(int i=0; i<this.properties.size(); i++) {
				this.properties.get(i).owner = null;
				this.properties.get(i).isMortgaged = false;
			}
			for(int i=0; i<this.jailCards.size(); i++) {
				this.jailCards.get(i).restoreCard(aPlayer);
			}
			for(int i=0; i<Main.allPlayers.size(); i++) {
				if(Main.allPlayers.get(i).name == this.name) {
					GUI.gameP.remove(Main.allPlayers.get(i).piece);
					Main.allPlayers.remove(i);
					
					if(GUI.currPlayerCounter >= Main.allPlayers.size()) {
						GUI.currPlayerCounter = 0;
					}
					
					if(Main.allPlayers.size() > 1) {
						GUI.currPlayer = Main.allPlayers.get(GUI.currPlayerCounter);
					}
					else {
						JOptionPane.showMessageDialog(null,"Player " + Main.allPlayers.get(0).name + " won !");
						System.exit(0);
						
					}
					
				}
			}
			
			GUI.sidepanel.revalidate();
			GUI.sidepanel.repaint();
			GUI.panelbig.revalidate();
			GUI.panelbig.repaint();
			
			
			/*class endTurnButtonListener implements ActionListener {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
						
						if(!GUI.currPlayer.isBankrupt()) {
							GUI.currPlayerCounter++;
							
						}
						else {
							JOptionPane.showMessageDialog(null,"Success!");
							//panelbig.remove(bankruptPanel);
							//panelbig.add(sidepanel, BorderLayout.EAST);
						}
						
						
						if(currPlayerCounter >= Main.allPlayers.size()) {
							currPlayerCounter = 0;
						}
						
						currPlayer = Main.allPlayers.get(currPlayerCounter);
						
				}
			}*/
			
		}
		
		
		}
}

