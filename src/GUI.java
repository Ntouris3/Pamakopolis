import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;


public class GUI extends JFrame{

	private Player currPlayer;
	private boolean drawCard = true;
	private int timesPressedRoll = 0;
	public static JPanel panelbig = new JPanel();
	public static JLayeredPane gameP = new JLayeredPane();
	public JPanel sidepanel = new JPanel();
	private JTextField propertyInfoField = new JTextField();
	public JLayeredPane jl = new JLayeredPane();
	public JButton rollButton = new JButton("Roll Dice");	

	private JButton buyButton;
	private JButton seeLocationInfoButton = new JButton("");
	private JButton buildButton= new JButton("Build");
	private JButton demolishButton= new JButton("Demolish");
	private JButton mortgageButton;
	private JButton seeCardsButton = new JButton("See Cards");
		
	private JButton endTurnButton = new JButton("End Turn");
	
	private JButton tradeButton = new JButton("Trade Properties");
		
	String cardImgName;
	JTextField tf = new JTextField();
	
	
	private JPanel playerInformationPanel = new JPanel();
	private JTextField balanceField = new JTextField();
	
	private Board board = new Board();

	public int number;
	public int currPlayerCounter=0;
	
	
	public GUI(){
		

		
		buyButton = new JButton("BUY");
		buyButton.setVisible(false);
		mortgageButton = new JButton("MORTGAGE");
		mortgageButton.setVisible(false);
		
		
		currPlayer = Main.allPlayers.get(currPlayerCounter);
		//jl.setBounds(0, 0, 100, 100);
		jl.setPreferredSize(new Dimension(100, 100));
		jl.setBorder(BorderFactory.createLineBorder(Color.black));
		
		Dice dice1 = new Dice(50, 180, 40, 40);
		jl.add(dice1);

		Dice dice2 = new Dice(110, 180, 40, 40);
		jl.add(dice2);
		jl.repaint();
		jl.revalidate();
		
		gameP.setVisible(true);
		
		board.setOpaque(true);
		board.setBounds(0, 0, 700, 700);	
		
		
		gameP.add(board , JLayeredPane.DEFAULT_LAYER);
		
		for(int i = 0 ; i<Main.allPlayers.size(); i++) {
			
			gameP.add(Main.allPlayers.get(i).piece , Integer.valueOf(i+1) );
		
		}
		board.repaint();

		balanceField.setText("Player "+currPlayer.name+" has "+currPlayer.balance+"€");
		balanceField.setEditable(false);
		propertyInfoField.setEditable(false);
		playerInformationPanel.add(balanceField);
		playerInformationPanel.add(propertyInfoField);
		
		panelbig.setLayout(new BorderLayout());
		board.setBorder(BorderFactory.createLineBorder(Color.red));
		panelbig.add(gameP, BorderLayout.CENTER);
		
		
		panelbig.add(sidepanel, BorderLayout.EAST);

		sidepanel.setLayout(new BoxLayout(sidepanel, BoxLayout.Y_AXIS));
		sidepanel.setBorder(BorderFactory.createLineBorder(Color.blue));

		
		sidepanel.add(rollButton);
		
		sidepanel.add(jl);
		sidepanel.add(playerInformationPanel);
		sidepanel.add(mortgageButton);
		sidepanel.add(buyButton);

		sidepanel.add(buildButton);
		sidepanel.add(demolishButton);
		sidepanel.add(tradeButton);
		//tradeButton.setVisible(true);
		
		
		
		ButtonListener listener3 = new ButtonListener();
		buildButton.addActionListener(listener3);
		
		ButtonListener2 listener2 = new ButtonListener2();
		demolishButton.addActionListener(listener2);
		
		endTurnButtonListener listener = new endTurnButtonListener();
		endTurnButton.addActionListener(listener);
		
		
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				timesPressedRoll++;
				balanceField.setText("Player "+currPlayer.name+" has "+currPlayer.balance+"€");
				sidepanel.revalidate();
				sidepanel.repaint();
				if(timesPressedRoll<3) {
//					dice1.rollDice();
//					dice2.rollDice();
					//demo
					dice1.setFaceValue(5);
					dice1.repaint();
					dice2.setFaceValue(2);
					dice2.repaint();
					
					if(dice1.getFaceValue() != dice2.getFaceValue()) {
						rollButton.setVisible(false);
					}else {
						
						rollButton.setVisible(true);
					}
					sidepanel.revalidate();
					sidepanel.repaint();
			

					
					dice1.paintImmediately(getX(), getY(), getWidth(), getHeight());
					dice2.paintImmediately(getX(), getY(), getWidth(), getHeight());
					int newPos = currPlayer.position + dice1.getFaceValue() + dice2.getFaceValue();
					currPlayer.lastDice = dice1.getFaceValue() + dice2.getFaceValue();
					currPlayer.ChangePosition(newPos);
					
					propertyInfoField.setText("");
					if (currPlayer.position != 4 && currPlayer.position != 38 && currPlayer.position % 10 != 0) {
						if (Main.locations.get(currPlayer.position) instanceof ChanceAndCommunityChest) {
							seeLocationInfoButton.setText("Draw the card");
						}else {
							seeLocationInfoButton.setText("See Location Info");
							
							if (((Property) Main.locations.get(currPlayer.position)).getOwner() == null) {
								propertyInfoField.setText("This property has no owner");
							}else {
								propertyInfoField.setText("This property is owned by "+((Property) Main.locations.get(currPlayer.position)).getOwner().name+
										" and costs "+((Property) Main.locations.get(currPlayer.position)).price+"€");
							}
						}
						seeLocationInfoButton.setVisible(true);
						sidepanel.revalidate();
					}else {
						seeLocationInfoButton.setVisible(false);
						sidepanel.revalidate();
					}
					
					//Disabling Buy Button And Paying off rent//
					if(Main.locations.get(currPlayer.position) instanceof Street || Main.locations.get(currPlayer.position) instanceof Utility 
							|| Main.locations.get(currPlayer.position) instanceof Railroad) {
						if(((Property)Main.locations.get(currPlayer.position)).getOwner() == null){
							buyButton.setVisible(true);
							sidepanel.revalidate();
							sidepanel.repaint();
						}
						else {
							buyButton.setVisible(false);
							sidepanel.revalidate();
							sidepanel.repaint();
							//Paying The Rent//
							if(((Property)Main.locations.get(currPlayer.position)).getOwner() != currPlayer) {
								if(Main.locations.get(currPlayer.position) instanceof Street) {
									((Street)Main.locations.get(currPlayer.position)).CalcRent(currPlayer);
									//System.out.println("Rent payed");
								}
								else if(Main.locations.get(currPlayer.position) instanceof Utility){
									((Utility)Main.locations.get(currPlayer.position)).CalcRent(currPlayer);
									//System.out.println("Rent payed");
								}
								else {
									((Railroad)Main.locations.get(currPlayer.position)).CalcRent(currPlayer);
									//System.out.println("Rent payed");
								}
							}
							
						}
					}	
					else {
							buyButton.setVisible(false);
							sidepanel.revalidate();
							sidepanel.repaint();
					}
					
					
					//DISABLING MORTGAGE BUTTON WHEN PLAYER HAS NO PROPERTIES
					if(currPlayer.properties.size()==0) {
						mortgageButton.setVisible(false);
						sidepanel.revalidate();
						sidepanel.repaint();
				}
				
				
				//DISABLING MORTGAGE BUTTON WHEN PLAYER HAS NO PROPERTIES
				if(currPlayer.properties.size()==0) {
					mortgageButton.setVisible(false);
					sidepanel.revalidate();
					sidepanel.repaint();
				}
				else {
					mortgageButton.setVisible(true);
					sidepanel.revalidate();
					sidepanel.repaint();
				}
				
				//PAYING TAX WHEN HITTING TAX BLOCK//
				if(Main.locations.get(currPlayer.position) instanceof Tax ){
					((Tax)Main.locations.get(currPlayer.position)).CalcTax(currPlayer);
					//System.out.println("Tax payed");
				}
				
				//Chance And Community Chest/
				drawCard = false;

//				if (currPlayer.position ==2 || currPlayer.position ==17 || currPlayer.position ==33) {
//					//TO-DO
//					JOptionPane.showMessageDialog(null,"You stepped on a Community Chest, draw a card.");
//					//Main.allCommunityChests.element().cardFunction(currPlayer);
//				}
//				else if(currPlayer.position ==7 || currPlayer.position ==22 || currPlayer.position ==36) {
//					JOptionPane.showMessageDialog(null,"You stepped on a Chance, draw a card.");
//					//Main.allChances.element().cardFunction(currPlayer);
//				}
				
				//Go To Prison Block//
				if(currPlayer.position == 30) {
					currPlayer.isInJail = true;
					currPlayer.ChangePosition(10);
				}
				
				
				if(currPlayer.position == 10) {
					if(currPlayer.isInJail==true) {
						buyButton.setVisible(false);
						rollButton.setVisible(false);
						sidepanel.revalidate();
						sidepanel.repaint();
					}
					
					//PAYING TAX WHEN HITTING TAX BLOCK//
					if(Main.locations.get(currPlayer.position) instanceof Tax ){
						((Tax)Main.locations.get(currPlayer.position)).CalcTax(currPlayer);
						//System.out.println("Tax payed");
					}
					
					//Chance And Community Chest/
					drawCard = false;

//					if (currPlayer.position ==2 || currPlayer.position ==17 || currPlayer.position ==33) {
//						//TO-DO
//						JOptionPane.showMessageDialog(null,"You stepped on a Community Chest, draw a card.");
//						//Main.allCommunityChests.element().cardFunction(currPlayer);
//					}
//					else if(currPlayer.position ==7 || currPlayer.position ==22 || currPlayer.position ==36) {
//						JOptionPane.showMessageDialog(null,"You stepped on a Chance, draw a card.");
//						//Main.allChances.element().cardFunction(currPlayer);
//					}
					
					//Go To Prison Block//
					if(currPlayer.position == 30) {
						currPlayer.isInJail = true;
						currPlayer.ChangePosition(10);
					}
					
					
					if(currPlayer.position == 10) {
						if(currPlayer.isInJail==true) {
							mortgageButton.setVisible(false);
							buyButton.setVisible(false);
							rollButton.setVisible(false);
							sidepanel.revalidate();
							sidepanel.repaint();
							JOptionPane.showMessageDialog(null,"You are now in jail","Alert",JOptionPane.INFORMATION_MESSAGE);
						}	
					}
				}
				
			}else {
					currPlayer.isInJail=true;
					currPlayer.position = 10;
					JOptionPane.showMessageDialog(null,"You rolled 3 times. You are now in jail");
				}
				balanceField.setText("Player "+currPlayer.name+" has "+currPlayer.balance+"€");
				sidepanel.revalidate();
				sidepanel.repaint();
		}});
	
		//BUY BUTTON
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((Property)Main.locations.get(currPlayer.position)).getPrice()>currPlayer.balance) {
					JOptionPane.showMessageDialog (null, "Not Enough Balance to aquire this property", "Low Balance", JOptionPane.ERROR_MESSAGE);
				}
				else {
					
					currPlayer.Buy(((Property)Main.locations.get(currPlayer.position)));
					buyButton.setVisible(false);
					mortgageButton.setVisible(true);
					balanceField.setText("Player "+currPlayer.name+" has "+currPlayer.balance+"€");		
					propertyInfoField.setText("This property is owned by "+((Property) Main.locations.get(currPlayer.position)).getOwner().name+
							" and costs "+((Property) Main.locations.get(currPlayer.position)).price+"€");
					sidepanel.revalidate();
					sidepanel.repaint();
				}
				
			}
		});
		
		//BUTTON THAT CREATES A FRAME FOR ALL THE MORTGAGE OPTIONS A PLAYER HAS
		mortgageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame();
				
				JLabel nameLabel = new JLabel("Name:"+currPlayer.name);
				nameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
				
				JLabel balanceLabel = new JLabel("Balance:"+String.valueOf(currPlayer.balance)+"€");
				balanceLabel.setFont(new Font("Serif", Font.PLAIN, 20));
				
				JPanel p = new JPanel();
				
				
				JButton b1 = new JButton("Mortgage");
				b1.setVisible(false);
				
				JButton b2 = new JButton("Unmortgage");
				b2.setVisible(false);
				
				JLabel label1 = new JLabel("");
	
				JList <Property> list = new JList(currPlayer.properties.toArray());
				DefaultListModel<Property> model;
				
				p.setLayout(new FlowLayout());
				p.add(nameLabel);
				p.add(balanceLabel);
				p.add(list);
				p.add(b1);
				p.add(b2);
				p.add(label1);
						
				model = new DefaultListModel<Property>();
				for(Property prop: currPlayer.properties) {
					model.addElement(prop);
				}
				list.setModel(model);
						
				//ENABLING AND DISABLING THE BUTTONS DEPENDING PLAYERS SELECTION
				list.addListSelectionListener( new ListSelectionListener() {
					   @Override
						public void valueChanged(ListSelectionEvent e) {
						   if(list.getSelectedValue().isMortgaged==true) {
				        		b2.setVisible(true);
				        		b1.setVisible(false);
				        		p.revalidate();
								p.repaint();
				        	}
				        	else {
				        		b1.setVisible(true);
				        		b2.setVisible(false);
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
						b2.setVisible(true);
		        		b1.setVisible(false);
		        		balanceLabel.setText("Balance:"+String.valueOf(currPlayer.balance)+"€");
		        		p.revalidate();
						p.repaint();
					}
				});
				
				//UnMortgage Button//
				b2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Property pro = list.getSelectedValue();
						if(currPlayer.balance>=pro.mortgage*1.1) {
							currPlayer.Unmortgage(pro);
							label1.setText(pro.name+" is no longer on Mortgage");
							b1.setVisible(true);
			        		b2.setVisible(false);
			        		balanceLabel.setText("Balance:"+String.valueOf(currPlayer.balance)+"€");
			        		p.revalidate();
							p.repaint();
						}
						else {
							JOptionPane.showMessageDialog (null, "Not Enough Balance to unmortgage this property", "Low Balance", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
							
						f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						f.setSize(350,500); 
						f.setContentPane(p);
						f.setTitle("Mortgage");
						f.setVisible(true);
						f.setResizable(false);
					}
		});
		
		//see Location Info Button
		
		ShowLocationInfoButtonListener l2 = new ShowLocationInfoButtonListener();
		seeLocationInfoButton.addActionListener(l2);
		sidepanel.add(seeLocationInfoButton);
		seeLocationInfoButton.setVisible(false);


		//see cards button
		
		seeCardsButtonListener l3 = new seeCardsButtonListener();
		seeCardsButton.addActionListener(l3);
		sidepanel.add(seeCardsButton);
		
		

		//trade button
		tradeButtonListener l4 = new tradeButtonListener();
		tradeButton.addActionListener(l4);
		

		panelbig.setVisible(true);
		sidepanel.setBackground(Color.DARK_GRAY);
		panelbig.setBackground(Color.DARK_GRAY);
		

		sidepanel.add(endTurnButton);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 700);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(false);
		this.setVisible(true);
		this.setTitle("");
		this.setContentPane(panelbig);
	
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
			tradeFrame = new JFrame("Trading Process");
			
			JTextArea messageArea = new JTextArea("Select a player:");
			messageArea.setEditable(false);
			tradeFrame.add(messageArea);
			
			JList<String> playersJList = new JList<String>();
			DefaultListModel<String> model = new DefaultListModel<String>();
			for(Player thisPlayer:Main.allPlayers) {
				if (!(thisPlayer.equals(currPlayer)))
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
							JPanel rightPanel = new JPanel();
							
							
							ArrayList<Property> currToOtherLocation = new ArrayList<Property>();
							
							ArrayList<GetOutOfJailCard> currToOtherJailCards = new ArrayList<GetOutOfJailCard>();
							
							ArrayList<Property> otherToCurrLocation = new ArrayList<Property>();
							
							ArrayList<GetOutOfJailCard> otherToCurrJailCards = new ArrayList<GetOutOfJailCard>();
							
							JTextArea mess2Area = new JTextArea("Player "+ currPlayer.name +"'s tangible assets");
							mess2Area.setEditable(false);
							leftPanel.add(mess2Area);
							
							JList<String> currPlayerPropertiesJList = new JList<String>();
							DefaultListModel<String> model1 = new DefaultListModel<String>();
							for(Property thisProperty:currPlayer.properties) {
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
									// TODO Auto-generated method stub
									if (!e.getValueIsAdjusting()) {
										
										for (String thisPropertyName : currPlayerPropertiesJList.getSelectedValuesList() ) {
											for (Property thisProperty : currPlayer.properties) {
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
							
							JTextField currPlayersBalanceField = new JTextField("Player "+currPlayer.name+" has "+ currPlayer.balance+"€");
							currPlayersBalanceField.setEditable(false);
							leftPanel.add(currPlayersBalanceField);
							
							JTextField currPlayerTradeMoneyFiled = new JTextField("Enter money...");
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
							for(GetOutOfJailCard thiscard:currPlayer.jailCards) {
								
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
									// TODO Auto-generated method stub
									if (!e.getValueIsAdjusting()) {
										
										
										for (String thisJailCardName : currPlayerJailCardsJList.getSelectedValuesList() ) {
											String s ;
											if (thisJailCardName.equals("Chance Get out of Jail Card")) {
												s = new String("Chance_GOOJF.png");
											}else {
												s = new String("Community_Chest_GOOJF.png");
											}
											for (GetOutOfJailCard thisJailCard : currPlayer.jailCards) {
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
									// TODO Auto-generated method stub
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
							otherPlayersBalanceField.setEditable(false);
							rightPanel.add(otherPlayersBalanceField);
							
							JTextField otherPlayerTradeMoneyFiled = new JTextField("Enter money...");
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
									// TODO Auto-generated method stub
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
							newJPanel.setVisible(true);
							newJPanel.setLayout(new FlowLayout());
							newJPanel.add(leftPanel);
							newJPanel.add(rightPanel);
							
							JButton rejectButton = new JButton("Reject");
							rejectButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {tradeFrame.dispose();}});
							newJPanel.add(rejectButton);
							JButton acceptTradeButton = new JButton("Trade");
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

									if (currToOtherMoney <= currPlayer.balance && otherToCurrMoney <= otherPlayer.balance) {
										currPlayer.Trade(otherPlayer, currToOtherLocation, currToOtherJailCards, currToOtherMoney, otherToCurrLocation, otherToCurrJailCards, otherToCurrMoney);
										tradeFrame.dispose();
									}else {
										JOptionPane.showMessageDialog(null, "Not valid input");
									}
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
	class endTurnButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(timesPressedRoll>0 && drawCard == true || currPlayer.isInJail) {
				timesPressedRoll = 0;
				drawCard = false;
				rollButton.setVisible(true);
				sidepanel.revalidate();
				currPlayerCounter++;
				
				if(currPlayerCounter == Main.allPlayers.size()) {
					currPlayerCounter = 0;
				}
				
				currPlayer = Main.allPlayers.get(currPlayerCounter);
			
				if(currPlayer.jailTurns==3) {
					JOptionPane.showMessageDialog(null,"You waited 3 rounds, you are now free","Alert",JOptionPane.INFORMATION_MESSAGE);
					currPlayer.isInJail=false;
				}
				
				if(currPlayer.isInJail==true) {
					rollButton.setVisible(false);
					currPlayer.ShowJailFrame(rollButton);
					//TO-DO
					panelbig.setVisible(false);
					panelbig.repaint();
					panelbig.revalidate();
				}
				else {
					rollButton.setVisible(true);
				}
				
				balanceField.setText("Player "+currPlayer.name+" has "+currPlayer.balance+"€");
				buyButton.setVisible(false);
				mortgageButton.setVisible(false);
			}else if (timesPressedRoll<=0 && !currPlayer.isInJail) {

				JOptionPane.showMessageDialog(null,"You must first roll dice.");
			}else if (drawCard == false) {
				JOptionPane.showMessageDialog(null,"You must first draw card.");
			}
			panelbig.setVisible(true);
			panelbig.repaint();
			panelbig.revalidate();
		}
	}
	class seeCardsButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFrame f = new JFrame("Properties");
			
			JList<String> PropertiesJList = new JList<String>();
			DefaultListModel<String> model = new DefaultListModel<String>();
			for(Property thisProperty:currPlayer.properties) {
				if(thisProperty instanceof Street) {
					model.addElement(thisProperty.name);					
				}else
					model.addElement(thisProperty.name);
			}	
			PropertiesJList.setModel(model);

			clickOnPropertiesJListListener listener = new clickOnPropertiesJListListener(PropertiesJList,f);
			PropertiesJList.addListSelectionListener(listener);
			JTextField hasInJailCards = new JTextField("Has "+currPlayer.jailCards.size()+" get out of jail cards");
			hasInJailCards.setEditable(false);
			f.add(hasInJailCards);
			f.setLayout(new FlowLayout());
			f.add(PropertiesJList);
			f.setSize(400,400); 
			f.setVisible(true);
		}
		class clickOnPropertiesJListListener implements  ListSelectionListener{

			JList<String> PropertiesJList;
			JFrame f;

			public clickOnPropertiesJListListener(JList<String> PropertiesJList, JFrame f) {
				// TODO Auto-generated method stub
				this.PropertiesJList = PropertiesJList;
				this.f = f;

			}

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				
			    if (!e.getValueIsAdjusting()) {//This line prevents double events
			    	
			    	for(Property thisProperty: currPlayer.properties) {
			    		if (thisProperty.name.equals(PropertiesJList.getSelectedValue())) {
			    			if (thisProperty instanceof Street) {

			    				JPanel p = new JPanel();
			    				tf.setText(thisProperty.name+" has "+((Street) thisProperty).hotel +" hotels and "+((Street) thisProperty).houses+" houses");;
				    			p.add(tf);
			    				f.add(p);
			    				tf.setEditable(false);
				    			f.add(tf);
				    			f.revalidate();
			    			}
			    			cardImgName = thisProperty.cardImg;
			    			new MyCanvas();
			    			break;
			    		}
			    	}
			    }

			}
			class MyCanvas extends Canvas{

				public MyCanvas() {

					super();
					JFrame f = new JFrame();
					f.add(this);
					f.setSize(319,390); 
					f.setVisible(true);
					
				}
				public void paint(Graphics g) {
					Toolkit t=Toolkit.getDefaultToolkit();
					Image i=t.getImage("src/Assets/CardImages/"+cardImgName);

					g.drawImage(i, 0,0,this);	
				}
			}
		}
	}
	class ShowLocationInfoButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (Main.locations.get(currPlayer.position) instanceof ChanceAndCommunityChest) {
				drawCard = true;
				Card thisCard = Main.allCommunityChests.poll();
				cardImgName = thisCard.cardImgName;
				
				
				seeLocationInfoButton.setVisible(false);
				if (currPlayer.position ==2 || currPlayer.position ==17 || currPlayer.position ==33) { 
					
					if (!(thisCard.cardImgName.equals("Community_Chest_GTJ.png"))){
						Main.allCommunityChests.add(thisCard);
					}
				}else if (currPlayer.position ==7 || currPlayer.position ==22 || currPlayer.position ==36) {
					if (!(thisCard.cardImgName.equals("Chance_GTJ.png"))){
						Main.allChances.add(thisCard);
					}
				}else {
					System.out.println("Error");
				}
				thisCard.cardFunction(currPlayer);
				
			}else if(Main.locations.get(currPlayer.position) instanceof Property){
				Property tempLocation =  (Property) Main.locations.get(currPlayer.position);;
				cardImgName = tempLocation.cardImg;
			}
			new MyCanvas();
			balanceField.setText("Player "+currPlayer.name+" has "+currPlayer.balance+"€");
			sidepanel.revalidate();
			sidepanel.repaint();	
		}
		class MyCanvas extends Canvas{

			public MyCanvas() {

				
				super();
				JFrame f = new JFrame();
				f.add(this);
				if (cardImgName.matches(".*\\d.*") && !cardImgName.equals("Chance_GB3S.png") ) {
					f.setSize(319,390);
				}else{
					f.setSize(360,239);
				}
				
				f.setVisible(true);
				f.revalidate();
				f.repaint();
				
				
			}
			public void paint(Graphics g) {
				Toolkit t=Toolkit.getDefaultToolkit();
				Image i;
				if (cardImgName.matches(".*\\d.*") && !cardImgName.equals("Chance_GB3S.png") ) {
					i=t.getImage("src/Assets/CardImages/"+cardImgName);
				}else {
					
					i=t.getImage("src/Assets/CommunityChestsAndChances/"+cardImgName);
				}
				g.drawImage(i, 0,0,this);	
			}
		}
	}
	
	
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//BUILD
			//ask for number 1-4 houses, number 5 hotel
			
			
			JFrame f = new JFrame();
			JPanel panels=new JPanel();

			JTextField messageF = new JTextField("You can only build if you have all the properties in the colour of the Street you want to build in");
			messageF.setEditable(false);
			messageF.setVisible(true);
			panels.add(messageF);
			JList<String> propertiestobuild= new JList<String>();
			DefaultListModel<String> listprop = new DefaultListModel<>();
						
			//Ξ²Ξ±Ξ¶ΞΏΟ…ΞΌΞµ Ο„Ξ± props ΟƒΞµ ΞΌΞΉΞ± Ξ»ΞΉΟƒΟ„Ξ± Ο€ΞΏΟ… ΞµΟ€ΞΉΞ»ΞµΞ³ΞµΞΉ
			for (Property prop1:currPlayer.PropertiesToBuildIn()) {
				if ((prop1.name.equals("Reading Railroad")) || (prop1.name.equals("Pennsylvania Railroad")) || (prop1.name.equals("B. & O. Railroad")) ||
						(prop1.name.equals("Short Line")) || (prop1.name.equals("Electric Company")) || (prop1.name.equals("Water Works"))) {
					//listprop.addElement(prop1.name);
				}
				else {
					listprop.addElement(prop1.name);
				}
			}
			propertiestobuild.setModel(listprop);
			
			//Ξ¦Ο„ΞΉΞ¬Ο‡Ξ½ΞΏΟ…ΞΌΞµ Ο„ΞΉΟ‚ ΞµΟ€ΞΉΞ»ΞΏΞ³Ξ­Ο‚
			String[] choices = { "1 house", "2 houses", "3 houses", "4 houses", "Hotel" };
			JComboBox<String> choicelist = new JComboBox<String>(choices);
			
			//Ξ’Ξ¬Ξ¶ΞΏΟ…ΞΌΞµ Ο„Ξ·Ξ½ Ξ»Ξ―ΟƒΟ„Ξ± props ΞΊΞ±ΞΉ Ο„ΞΉ ΞΈΞµΞ»ΞµΞΉ Ξ½Ξ± Ο‡Ο„Ξ―ΟƒΞµΞΉ
			//Ξ�Ξ± Ξ΄ΞΏΟ…ΞΌΞµ Ο„Ξ·Ξ½ Streets ΞΌΞµ Ο„ΞΉΟ‚ Ο‡Ο�ΞµΟ‰ΟƒΞµΞΉΟ‚ ΞΊΞ±ΞΉ Ξ½Ξ± Ο€ΞµΟ„Ξ±ΞµΞΉ ΞΌΞ·Ξ½Ο…ΞΌΞ± ΞΏΟ„Ξ±Ξ½ Ξ΄ΞµΞ½ ΞΌΟ€ΞΏΟ�ΞµΞΉ Ξ½Ξ± Ο‡Ο„ΞΉΟƒΞµΞΉ ΞΊΞ±Ο„ΞΉ
			panels.add(propertiestobuild);
			panels.add(choicelist);
			
			//Ξ�ΞΏΟ…ΞΌΟ€Ξ― build
			JButton BButton= new JButton("Build");
			panels.add(BButton);

			
			//Ξ•Ο€ΞΉΞ»ΞΏΞ³Ξ® Ξ±Ο�ΞΉΞΈΞΌΞΏΟ… Ο‡Ο„ΞΉΟƒΞΌΞ±Ο„ΞΏΟ‚
			choicelist.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent event) {
			    	String selected = (String) choicelist.getSelectedItem();
			    	if (selected=="1 house") {
			    		number=1;
			    	}
			    	else if(selected=="2 houses") {
			    		number=2;
			    	}
			    	else if(selected=="3 houses") {
			    		number=3;
			    	}
			    	else if(selected=="4 houses") {
			    		number=4;
			    	}
			    	else {
			    		number=5;
			    	}
			    }
			});	

			
			//List listener
			propertiestobuild.addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	for (int i=0; i<currPlayer.properties.size(); i++) {
	                		if (propertiestobuild.getSelectedValue().toString().equals(currPlayer.properties.get(i).name)) {
	                			int numb=i;
	            				BButton.addActionListener(new ActionListener() {
	            					public void actionPerformed(ActionEvent e) {
	    	                			((Street)currPlayer.properties.get(numb)).Build(currPlayer, number);
	    	                			System.out.println(currPlayer.properties.get(numb).name);
	    	                			System.out.println(number);
	            					}
	            				});
	                		}
	                	}
	                }
	            }

	        });	
			
			
			
			f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			f.setSize(600,200);
			f.setContentPane(panels);
			f.setTitle("Build");
			f.setVisible(true);
			
						
			
		}
	}
	
	class ButtonListener2 implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//demolish
			//ask for number 1-4 houses, number 5 hotel
			
			
			JFrame f = new JFrame();
			JPanel panels=new JPanel();

			
			JList<String> propertiestodemolish= new JList<String>();
			DefaultListModel<String> listprop = new DefaultListModel<>();
						
			//Ξ²Ξ±Ξ¶ΞΏΟ…ΞΌΞµ Ο„Ξ± props ΟƒΞµ ΞΌΞΉΞ± Ξ»ΞΉΟƒΟ„Ξ± Ο€ΞΏΟ… ΞµΟ€ΞΉΞ»ΞµΞ³ΞµΞΉ
			for (Property prop1:currPlayer.properties) {
				if ((prop1.name.equals("Reading Railroad")) || (prop1.name.equals("Pennsylvania Railroad")) || (prop1.name.equals("B. & O. Railroad")) || (prop1.name.equals("Short Line")) || (prop1.name.equals("Electric Company")) || (prop1.name.equals("Water Works"))) {
					//listprop.addElement(prop1.name);
				}
				else {
					listprop.addElement(prop1.name);
				}
			}
			propertiestodemolish.setModel(listprop);
			
			//Ξ¦Ο„ΞΉΞ¬Ο‡Ξ½ΞΏΟ…ΞΌΞµ Ο„ΞΉΟ‚ ΞµΟ€ΞΉΞ»ΞΏΞ³Ξ­Ο‚
			ArrayList<String> choices=new ArrayList<String>();
			for (Property prop1:currPlayer.properties) {
				if ((prop1.name.equals("Reading Railroad")) || (prop1.name.equals("Pennsylvania Railroad")) || (prop1.name.equals("B. & O. Railroad")) || (prop1.name.equals("Short Line")) || (prop1.name.equals("Electric Company")) || (prop1.name.equals("Water Works"))) {
					//listprop.addElement(prop1.name);
				}
				else {
		    		if (((Street)prop1).houses==1) {
		    			choices.add("1 house");
		    		}
		    		if (((Street)prop1).houses==2) {
		    			choices.add("2 houses");
		    		}
		    		if (((Street)prop1).houses==3) {
		    			choices.add("3 houses");
		    		}
		    		if (((Street)prop1).houses==4) {
		    			choices.add("4 houses");
		    		}
		    		if (((Street)prop1).hotel==1) {
		    			choices.add("Hotel");
		    		}
				}
				
			}
			//Ξ�ΞµΟ„Ξ±Ο„Ο�ΞΏΟ€Ξ® ΟƒΞµ string!
	    	String[] array = choices.toArray(new String[0]);
			JComboBox<String> choicelist = new JComboBox<String>(array);


			//Ξ’Ξ¬Ξ¶ΞΏΟ…ΞΌΞµ Ο„Ξ·Ξ½ Ξ»Ξ―ΟƒΟ„Ξ± props ΞΊΞ±ΞΉ Ο„ΞΉ ΞΌΟ€ΞΏΟ�ΞµΞ― Ξ½Ξ± ΞΊΞ±Ξ½ΞµΞΉ demolish
			panels.add(choicelist);
			panels.add(propertiestodemolish);
			
			//Ξ�ΞΏΟ…ΞΌΟ€Ξ― demolish
			JButton DButton= new JButton("Demolish");
			panels.add(DButton);

			
			choicelist.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent event) {
			    	String selected = (String) choicelist.getSelectedItem();
			    	if (selected=="1 house") {
			    		number=1;
			    	}
			    	else if(selected=="2 houses") {
			    		number=2;
			    	}
			    	else if(selected=="3 houses") {
			    		number=3;
			    	}
			    	else if(selected=="4 houses") {
			    		number=4;
			    	}
			    	else {
			    		number=5;
			    	}
			    }
			});			
			
			propertiestodemolish.addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	for (int i=0; i<currPlayer.properties.size(); i++) {
	                		if (propertiestodemolish.getSelectedValue().toString().equals(currPlayer.properties.get(i).name)) {
	                			int numb=i;
	            				DButton.addActionListener(new ActionListener() {
	            					public void actionPerformed(ActionEvent e) {
	    	                			((Street)currPlayer.properties.get(numb)).Demolish(currPlayer, number);
	    	                			System.out.println(currPlayer.properties.get(numb).name);
	    	                			System.out.println(number);
	            					}
	            				});
	                		}
	                	}
	                }
	            }

	        });		
			
			f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			f.setSize(400,200);
			f.setContentPane(panels);
			f.setTitle("Demolish");
			f.setVisible(true);
			
						
			}
	}	

}