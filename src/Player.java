import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;






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
	public Player curPlayer = this;
	
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
			ReduceBalance(amount);
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
	
	public void ShowJailFrame(JButton button, JButton viewCardButton, JButton buyButton) {
		
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
		
		//Button Listener for using Get Out Of Jail card
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
		
		//Button Listener for paying to get out of Jail
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
		
		//Button Listener for rolling the dice
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dice1.rollDice();
				dice2.rollDice();
			
				if(dice1.getFaceValue()==dice2.getFaceValue()) {
					isInJail = false;
					jailTurns = 0;
					GUI.setTimesPressedRoll(GUI.getTimesPressedRoll()+1);
					JOptionPane.showMessageDialog(null,"You got out of jail thanks to your dice roll","Alert",JOptionPane.INFORMATION_MESSAGE);
					f.dispose();
					ChangePosition(dice1.getFaceValue()+dice2.getFaceValue()+10);
					
					if (Main.locations.get(position) instanceof ChanceAndCommunityChest) {
						viewCardButton.setText("Draw the card");
						viewCardButton.setVisible(true);
						GUI.setDrawCard(false);
					}
					else if (Main.locations.get(position) instanceof Street || Main.locations.get(position) instanceof Utility 
					|| Main.locations.get(position) instanceof Railroad) {
						if(((Property)Main.locations.get(position)).getOwner() == null){
							buyButton.setVisible(true);
						}
						viewCardButton.setText("See Location Info");
						viewCardButton.setVisible(true);
					}

				}
				else {
					if(jailTurns==3) {
						if(balance<50) {
							isInJail = false;
							jailTurns = 0;
							GUI.setTimesPressedRoll(GUI.getTimesPressedRoll()+1);
							JOptionPane.showMessageDialog(null,"You waited 3 rounds you can now leave jail","Alert",JOptionPane.INFORMATION_MESSAGE);
							f.dispose();
							ChangePosition(dice1.getFaceValue()+dice2.getFaceValue()+10);
							if (Main.locations.get(position) instanceof ChanceAndCommunityChest) {
								viewCardButton.setText("Draw the card");
								viewCardButton.setVisible(true);
								GUI.setDrawCard(false);
							}
							else if (Main.locations.get(position) instanceof Street || Main.locations.get(position) instanceof Utility 
							|| Main.locations.get(position) instanceof Railroad) {
								if(((Property)Main.locations.get(position)).getOwner() == null){
									buyButton.setVisible(true);
								}
								viewCardButton.setText("See Location Info");
								viewCardButton.setVisible(true);
							}
						}else {
							isInJail = false;
							jailTurns = 0;
							ReduceBalance(50);
							GUI.setTimesPressedRoll(GUI.getTimesPressedRoll()+1);
							JOptionPane.showMessageDialog(null,"You didn't throw doubles, 50$ have been removed from you balance\nYou can now leave jail","Alert",JOptionPane.INFORMATION_MESSAGE);
							f.dispose();
							ChangePosition(dice1.getFaceValue()+dice2.getFaceValue()+10);
							if (Main.locations.get(position) instanceof ChanceAndCommunityChest) {
								viewCardButton.setText("Draw the card");
								viewCardButton.setVisible(true);
								GUI.setDrawCard(false);
							}
							else if (Main.locations.get(position) instanceof Street || Main.locations.get(position) instanceof Utility 
							|| Main.locations.get(position) instanceof Railroad) {
								if(((Property)Main.locations.get(position)).getOwner() == null){
									buyButton.setVisible(true);
								}
								viewCardButton.setText("See Location Info");
								viewCardButton.setVisible(true);
							}
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
		
		JLabel balanceLabel = new JLabel(this.balance + "€");
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
        		balanceLabel.setText(currPlayer.balance + "€");
        		GUI.balanceField.setText(balanceLabel.getText());
        		p.revalidate();
				p.repaint();
			}
		});
					
				
				o.setSize(500,350); 
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
			
			
			
			
			/*endTurnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});*/
			tradeButtonListener lis = new tradeButtonListener();
			tradeButton.addActionListener(lis);
			
			mortgageButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mortgageWhenBankrupt(aPlayer);
			}});
			
		
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
			f.setSize(700,500); 
			f.setResizable(false);
			f.setTitle("");
			f.setContentPane(p);
		}
		
		if(isBankrupt()){
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
			}
	}
		class tradeButtonListener implements ActionListener{
			Player otherPlayer = null;
			JFrame tradeFrame;
			JButton requestTrade = new JButton();
			
			JFrame f = new JFrame();
			JTextField t = new JTextField();
			JButton b = new JButton("Reject");
			JButton b1 = new JButton("Accept");
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				requestTrade.setFont(new Font("SansSerif", Font.BOLD, 12));
				t.setFont(new Font("SansSerif", Font.PLAIN, 15));
				tradeFrame = new JFrame("Trading Process");
				tradeFrame.getContentPane().setBackground(GUI.panelbig.getBackground());
				f.getContentPane().setBackground(GUI.panelbig.getBackground());
				tradeFrame.setFont(new Font("SansSerif", Font.PLAIN, 15));
				b.setFont(new Font("SansSerif", Font.BOLD, 12));
				b1.setFont(new Font("SansSerif", Font.BOLD, 12));
				
				JTextArea messageArea = new JTextArea("Select a player:");
				GUI.setColor(messageArea);
				messageArea.setFont(new Font("SansSerif", Font.BOLD, 12));
				messageArea.setEditable(false);
				tradeFrame.add(messageArea);
				
				JList<String> playersJList = new JList<String>();
				DefaultListModel<String> model = new DefaultListModel<String>();
				for(Player thisPlayer:Main.allPlayers) {
					if (!(thisPlayer.equals(curPlayer)))
						model.addElement(thisPlayer.name);
				}	
				playersJList.setModel(model);

				tradeFrame.add(playersJList);	
				
				playersJList.addListSelectionListener(new clickOnPlayerListener(playersJList));
				
				requestTrade.addActionListener(new ActionListener() {
					int currToOtherMoney = 0;
					int otherToCurrMoney = 0;
					
					@Override
					public void actionPerformed(ActionEvent e) {
							
						t.setText("Does player "+ otherPlayer.name+" accept the trading?");
						t.setEditable(false);
						f.add(t);
						
						b.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {f.dispose();}});
						f.add(b);
						
						
						b1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								f.dispose();
								tradeFrame.getContentPane().invalidate();
								tradeFrame.getContentPane().validate();
								tradeFrame.getContentPane().repaint();
								
								JPanel leftPanel = new JPanel();
								GUI.setColor(leftPanel);
								JPanel rightPanel = new JPanel();
								GUI.setColor(rightPanel);
								
								
								ArrayList<Property> currToOtherLocation = new ArrayList<Property>();
								
								ArrayList<GetOutOfJailCard> currToOtherJailCards = new ArrayList<GetOutOfJailCard>();
								
								ArrayList<Property> otherToCurrLocation = new ArrayList<Property>();
								
								ArrayList<GetOutOfJailCard> otherToCurrJailCards = new ArrayList<GetOutOfJailCard>();
								
								JTextArea mess2Area = new JTextArea("Player "+ curPlayer.name +"'s tangible assets");
								GUI.setColor(mess2Area);
								mess2Area.setFont(new Font("SansSerif", Font.PLAIN, 15));
								mess2Area.setEditable(false);
								leftPanel.add(mess2Area);
								
								JList<String> currPlayerPropertiesJList = new JList<String>();
								DefaultListModel<String> model1 = new DefaultListModel<String>();
								for(Property thisProperty:curPlayer.properties) {
									model1.addElement(thisProperty.name);
								}
								currPlayerPropertiesJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

								
								currPlayerPropertiesJList.setModel(model1);
								
								currPlayerPropertiesJList.setSelectionModel(new DefaultListSelectionModel() {
								    @Override
								    public void setSelectionInterval(int index0, int index1) {
								        if(super.isSelectedIndex(index0)) {
								            super.removeSelectionInterval(index0, index1);
								        }
								        else {
								            super.addSelectionInterval(index0, index1);
								        }
								    }});
								
								currPlayerPropertiesJList.addListSelectionListener(new ListSelectionListener() {
									
									@Override
									public void valueChanged(ListSelectionEvent e) {
										
										if (!e.getValueIsAdjusting()) {
											
											for (String thisPropertyName : currPlayerPropertiesJList.getSelectedValuesList() ) {
												for (Property thisProperty : curPlayer.properties) {
													if (thisPropertyName.equals(thisProperty.name) && !(currToOtherLocation.contains(thisProperty))) {
														currToOtherLocation.add(thisProperty);
														break;
													}
												}
											}
											
											;
										}
									}
								});
								
								leftPanel.add(currPlayerPropertiesJList);
								
								JTextField currPlayersBalanceField = new JTextField("Player "+curPlayer.name+" has "+ curPlayer.balance+"€");
								GUI.setColor(currPlayersBalanceField);
								currPlayersBalanceField.setFont(new Font("SansSerif", Font.PLAIN, 15));
								currPlayersBalanceField.setEditable(false);
								leftPanel.add(currPlayersBalanceField);
								
								JTextField currPlayerTradeMoneyFiled = new JTextField("Enter money...");
								GUI.setColor(currPlayerTradeMoneyFiled);
								currPlayerTradeMoneyFiled.setFont(new Font("SansSerif", Font.PLAIN, 15));
								currPlayerTradeMoneyFiled.addMouseListener(new MouseAdapter(){
						            public void mouseClicked(MouseEvent e){
						            	currPlayerTradeMoneyFiled.setText("");
						            }
						        });
								currPlayerTradeMoneyFiled.setColumns(10);
								
								
								((AbstractDocument)currPlayerTradeMoneyFiled.getDocument()).setDocumentFilter(new DocumentFilter(){
							        Pattern regEx = Pattern.compile("\\d*");

							        @Override
							        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {          
							            Matcher matcher = regEx.matcher(text);
							            if(!matcher.matches()){
							                return;
							            }
							            super.replace(fb, offset, length, text, attrs);
							        }
							    });
								
								leftPanel.add(currPlayerTradeMoneyFiled);
								
								JList<String> currPlayerJailCardsJList = new JList<String>();
								DefaultListModel<String> model3 = new DefaultListModel<String>();
								for(GetOutOfJailCard thiscard:curPlayer.jailCards) {
									
										if (thiscard.cardImgName.equals("Chance_GOOJF.png")) {
											model3.addElement("Chance Get out of Jail Card");
										}else {
											model3.addElement("Community Chest Get out of Jail Card");
										}
									
								}
								currPlayerJailCardsJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
								currPlayerJailCardsJList.setSelectionModel(new DefaultListSelectionModel() {
								    @Override
								    public void setSelectionInterval(int index0, int index1) {
								        if(super.isSelectedIndex(index0)) {
								            super.removeSelectionInterval(index0, index1);
								        }
								        else {
								            super.addSelectionInterval(index0, index1);
								        }
								    }});
								currPlayerJailCardsJList.addListSelectionListener(new ListSelectionListener() {
									
									@Override
									public void valueChanged(ListSelectionEvent e) {
										
										if (!e.getValueIsAdjusting()) {
											
											
											for (String thisJailCardName : currPlayerJailCardsJList.getSelectedValuesList() ) {
												String s ;
												if (thisJailCardName.equals("Chance Get out of Jail Card")) {
													s = new String("Chance_GOOJF.png");
												}else {
													s = new String("Community_Chest_GOOJF.png");
												}
												for (GetOutOfJailCard thisJailCard : curPlayer.jailCards) {
													if (s.equals(thisJailCard.cardImgName) && !(currToOtherJailCards.contains(thisJailCard))) {
														currToOtherJailCards.add(thisJailCard);
														break;
													}
												}
											}
										}
									}
								});
								currPlayerJailCardsJList.setModel(model3);

								leftPanel.add(currPlayerJailCardsJList);
								
								
								JTextArea mess3Area = new JTextArea("Player "+ otherPlayer.name +"'s tangible assets");
								GUI.setColor(mess3Area);
								mess3Area.setFont(new Font("SansSerif", Font.PLAIN, 15));
								mess3Area.setEditable(false);
								rightPanel.add(mess3Area);
								
								
								JList<String> otherPropertiesJList = new JList<String>();
								DefaultListModel<String> model2 = new DefaultListModel<String>();
								for(Property thisProperty:otherPlayer.properties) {
									model2.addElement(thisProperty.name);
								}
								otherPropertiesJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
								
								otherPropertiesJList.setSelectionModel(new DefaultListSelectionModel() {
								    @Override
								    public void setSelectionInterval(int index0, int index1) {
								        if(super.isSelectedIndex(index0)) {
								            super.removeSelectionInterval(index0, index1);
								        }
								        else {
								            super.addSelectionInterval(index0, index1);
								        }
								    }});
								
								otherPropertiesJList.addListSelectionListener(new ListSelectionListener() {
									
									@Override
									public void valueChanged(ListSelectionEvent e) {
										if (!e.getValueIsAdjusting()) {
											
											for (String thisPropertyName : otherPropertiesJList.getSelectedValuesList() ) {
												for (Property thisProperty : otherPlayer.properties) {
													if (thisPropertyName.equals(thisProperty.name) && !(otherToCurrLocation.contains(thisProperty))) {
														otherToCurrLocation.add(thisProperty);
														break;
													}
												}
											}
										}
									}
								});
								otherPropertiesJList.setModel(model2);

								rightPanel.add(otherPropertiesJList);
								JTextField otherPlayersBalanceField = new JTextField("Player "+otherPlayer.name+" has "+ otherPlayer.balance+"€");
								GUI.setColor(otherPlayersBalanceField);
								otherPlayersBalanceField.setFont(new Font("SansSerif", Font.PLAIN, 15));
								otherPlayersBalanceField.setEditable(false);
								rightPanel.add(otherPlayersBalanceField);
								
								JTextField otherPlayerTradeMoneyFiled = new JTextField("Enter money...");
								GUI.setColor(otherPlayerTradeMoneyFiled);
								otherPlayerTradeMoneyFiled.setFont(new Font("SansSerif", Font.PLAIN, 15));
								otherPlayerTradeMoneyFiled.addMouseListener(new MouseAdapter(){
						            public void mouseClicked(MouseEvent e){
						            	otherPlayerTradeMoneyFiled.setText("");
						            }
						        });
								otherPlayerTradeMoneyFiled.setColumns(10);
								
								((AbstractDocument)otherPlayerTradeMoneyFiled.getDocument()).setDocumentFilter(new DocumentFilter(){
							        Pattern regEx = Pattern.compile("\\d*");

							        @Override
							        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {          
							            Matcher matcher = regEx.matcher(text);
							            if(!matcher.matches()){
							                return;
							            }
							            super.replace(fb, offset, length, text, attrs);
							        }
							    });
								
								rightPanel.add(otherPlayerTradeMoneyFiled);
								
								JList<String> otherPlayerJailCardsJList = new JList<String>();
								DefaultListModel<String> model4 = new DefaultListModel<String>();
								for(GetOutOfJailCard thiscard: otherPlayer.jailCards) {
										if (thiscard.cardImgName.equals("Chance_GOOJF.png")) {
											model4.addElement("Chance Get out of Jail Card");
										}else {
											model4.addElement("Community Chest Get out of Jail Card");
										}
									
								}
								otherPlayerJailCardsJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
								otherPlayerJailCardsJList.setSelectionModel(new DefaultListSelectionModel() {
								    @Override
								    public void setSelectionInterval(int index0, int index1) {
								        if(super.isSelectedIndex(index0)) {
								            super.removeSelectionInterval(index0, index1);
								        }
								        else {
								            super.addSelectionInterval(index0, index1);
								        }
								    }});
								otherPlayerJailCardsJList.addListSelectionListener(new ListSelectionListener() {
									
									@Override
									public void valueChanged(ListSelectionEvent e) {
										
										if (!e.getValueIsAdjusting()) {
											
											for (String thisJailCardName : otherPlayerJailCardsJList.getSelectedValuesList() ) {
												String s ;
												if (thisJailCardName.equals("Chance Get out of Jail Card")) {
													s = new String("Chance_GOOJF.png");
												}else {
													s = new String("Community_Chest_GOOJF.png");
												}
												for (GetOutOfJailCard thisJailCard : otherPlayer.jailCards) {
													if (s.equals(thisJailCard.cardImgName) && !(otherToCurrJailCards.contains(thisJailCard))) {
														otherToCurrJailCards.add(thisJailCard);
														break;
													}
												}
											}
										}
									}
								});
								otherPlayerJailCardsJList.setModel(model4);

								rightPanel.add(otherPlayerJailCardsJList);
								
								
								leftPanel.setVisible(true);
								rightPanel.setVisible(true);
								leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
								rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
								
								JPanel newJPanel = new JPanel();
								GUI.setColor(newJPanel);
								newJPanel.setVisible(true);
								newJPanel.setLayout(new FlowLayout());
								newJPanel.add(leftPanel);
								newJPanel.add(rightPanel);
								
								JButton rejectButton = new JButton("Reject");
								rejectButton.setFont(new Font("SansSerif", Font.BOLD, 12));
								rejectButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {tradeFrame.dispose();}});
								newJPanel.add(rejectButton);
								JButton acceptTradeButton = new JButton("Trade");
								acceptTradeButton.setFont(new Font("SansSerif", Font.BOLD, 12));
								acceptTradeButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if (!isNumeric(currPlayerTradeMoneyFiled.getText())) {
											currPlayerTradeMoneyFiled.setText("0");
										}
										
										if (!isNumeric(otherPlayerTradeMoneyFiled.getText())) {
											otherPlayerTradeMoneyFiled.setText("0");
										}
										
										currToOtherMoney = Integer.parseInt(currPlayerTradeMoneyFiled.getText());
										otherToCurrMoney = Integer.parseInt(otherPlayerTradeMoneyFiled.getText());

											curPlayer.Trade(otherPlayer, currToOtherLocation, currToOtherJailCards, currToOtherMoney, otherToCurrLocation, otherToCurrJailCards, otherToCurrMoney);
											tradeFrame.dispose();

									}});
								newJPanel.add(acceptTradeButton);
								tradeFrame.add(newJPanel);
								tradeFrame.setContentPane(newJPanel);
								tradeFrame.revalidate();
							
							}});
						f.add(b1);
						
						f.setLayout(new FlowLayout());
						f.setSize(400,200); 
						f.setVisible(true);
						
					}
				});
				
				
				

				tradeFrame.setLayout(new FlowLayout());
				tradeFrame.setSize(350,400); 
				tradeFrame.setVisible(true);
			}
			
			public boolean isNumeric(String strNum) {
			    if (strNum.equals(null)) {
			        return false;
			    }
			    try {
			        @SuppressWarnings("unused")
					double d = Double.parseDouble(strNum);
			    } catch (NumberFormatException nfe) {
			        return false;
			    }
			    return true;
			}
			
			class clickOnPlayerListener implements  ListSelectionListener{
				
				JList<String> playersJList;
				
				
				public clickOnPlayerListener(JList<String> playersJList) {
					this.playersJList = playersJList;
				}
				@Override
				public void valueChanged(ListSelectionEvent e) {

					if (!e.getValueIsAdjusting()) {//This line prevents double events
						for (Player thisPlayer : Main.allPlayers) {
							if (thisPlayer.name.equals(playersJList.getSelectedValue())) {
								otherPlayer = thisPlayer;
								if (otherPlayer!= null) {
									requestTrade.setText("Request trade from "+otherPlayer.name);
									
									
									tradeFrame.add(requestTrade);
									tradeFrame.revalidate();			
								}
								break;
							}
						}
					}
				}
			}
			
		}
		
		
	

	
		
		
		
	
}

