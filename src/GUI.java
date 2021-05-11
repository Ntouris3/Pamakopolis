import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class GUI extends JFrame{

	private Player currPlayer;
	
	public static JPanel panelbig = new JPanel();
	public static JLayeredPane gameP = new JLayeredPane();
	public JPanel sidepanel = new JPanel();
	private JPanel propertyOptionsPanel;
	public JLayeredPane jl = new JLayeredPane();
	public JButton rollButton = new JButton("Roll Dice");	

	private JButton buyButton;
	private JButton seeLocationInfoButton = new JButton("");
	private JButton buildButton= new JButton("Build");
	private JButton demolishButton= new JButton("Demolish");
	private JButton mortgageButton;
	private JButton tradeButton;
	private JButton seeCardsButton = new JButton("See Cards");
		
	private JButton endTurnButton = new JButton("End Turn");
	
	private JTextField ownedByField;
		
	String cardImgName;
	JTextField tf = new JTextField();
	
	
	private JPanel playerInformationPanel;
	private JTextField balanceField;
	private JTextField nameField;	
	
	private JPanel boardPanel;
	private Board board = new Board();

	private Dice dice1 = new Dice(150, 180, 40, 40);
	private Dice dice2 = new Dice(210, 180, 40, 40);
	public int number;
	public int currPlayerCounter=0;
	
	
	public GUI(){
		buyButton = new JButton("BUY");
		mortgageButton = new JButton("MORTGAGE");
		
		currPlayer = Main.allPlayers.get(currPlayerCounter);
		jl.setBounds(6, 6, 700, 700);
		jl.setPreferredSize(new Dimension(400, 400));

		Dice dice1 = new Dice(150, 180, 40, 40);
		jl.add(dice1);

		Dice dice2 = new Dice(210, 180, 40, 40);
		jl.add(dice2);

		gameP.setBounds(0, 0, 700, 700);
		gameP.setVisible(true);
		
		board.setOpaque(true);
		board.setBounds(0, 0, 700, 700);		
		
		gameP.add(board , JLayeredPane.DEFAULT_LAYER);
		
		for(int i = 0 ; i<Main.allPlayers.size(); i++) {
			
			gameP.add(Main.allPlayers.get(i).piece , Integer.valueOf(i+1) );
		
		}
		board.repaint();

		
		panelbig.setLayout(new BorderLayout());
		panelbig.add(gameP , BorderLayout.CENTER);
		
		
		panelbig.add(sidepanel , BorderLayout.EAST);

		sidepanel.setLayout(new BorderLayout());
		//sidepanel.add(endTurnButton,BorderLayout.SOUTH);
		sidepanel.add(rollButton, BorderLayout.NORTH);
		sidepanel.add(jl, BorderLayout.EAST);
		
		sidepanel.add(mortgageButton);
		sidepanel.add(buyButton,BorderLayout.WEST);

		//sidepanel.add(buildButton);
		//sidepanel.add(demolishButton);

		
		ButtonListener listener3 = new ButtonListener();
		buildButton.addActionListener(listener3);
		
		ButtonListener2 listener2 = new ButtonListener2();
		demolishButton.addActionListener(listener2);
		
		endTurnButtonListener listener = new endTurnButtonListener();
		endTurnButton.addActionListener(listener);
		
		//PAYING TAX WHEN HITTING TAX BLOCK//
		if(Main.locations.get(currPlayer.position).getClass().equals((Tax.class))){
				((Tax)Main.locations.get(currPlayer.position)).CalcTax(currPlayer);
		  }
		
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dice1.rollDice();
				dice2.rollDice();
				dice1.paintImmediately(getX(), getY(), getWidth(), getHeight());
				dice2.paintImmediately(getX(), getY(), getWidth(), getHeight());
				int newPos = currPlayer.position + dice1.getFaceValue() + dice2.getFaceValue();
				currPlayer.ChangePosition(newPos);
				sidepanel.add(endTurnButton,BorderLayout.SOUTH);
			}
		});
		
		//BUY BUTTON
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currPlayer.Buy(((Property)Main.locations.get(currPlayer.position)));
			}
		});
		
		//BUTTON THAT CREATES A FRAME FOR ALL THE MORTGAGE OPTIONS A PLAYER HAS
		mortgageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame();
				JPanel p = new JPanel();
				JButton b1 = new JButton("Mortgage");
				b1.setVisible(false);
				JButton b2 = new JButton("Unmortgage");
				b2.setVisible(false);
				JLabel label1 = new JLabel("                                    ");
				JList <Property> list = new JList(currPlayer.properties.toArray());
				DefaultListModel<Property> model;
					
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
				MouseListener mouseListener = new MouseAdapter() {
					  public void mouseClicked(MouseEvent e) {
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
						};
						list.addMouseListener(mouseListener);
						
						//Mortgage Button//
						b1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Property pro = list.getSelectedValue();
								currPlayer.AddToMortgage(pro);
								label1.setText(pro.name+" is now on Mortgage");
							}
						});
						
						//UnMortgage Button//
						b2.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Property pro = list.getSelectedValue();
								currPlayer.Unmortgage(pro);
								label1.setText(pro.name+" is no longer on Mortgage");
							}
						});
							
						f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						f.pack();
						f.setSize(400,400);
						f.setContentPane(p);
						f.setTitle("Mortgage");
						f.setVisible(true);
					}
		});
		
		//see Location Info Button
		
		ShowLocationInfoButtonListener l2 = new ShowLocationInfoButtonListener();
		seeLocationInfoButton.addActionListener(l2);

		if (currPlayer.position != 4 && currPlayer.position != 38 && currPlayer.position % 10 != 0) {
			if (Main.locations.get(currPlayer.position) instanceof ChanceAndCommunityChest) {
				seeLocationInfoButton.setText("Draw the card");
			}else {
				seeLocationInfoButton.setText("See Location Info");
			}
			sidepanel.add(seeLocationInfoButton);
		}

		//see cards button
		
		seeCardsButtonListener l3 = new seeCardsButtonListener();
		seeCardsButton.addActionListener(l3);
		//sidepanel.add(seeCardsButton, BorderLayout.WEST);
		
		
		panelbig.setVisible(true);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(false);
		this.setVisible(true);
		this.setTitle("");
		this.setContentPane(panelbig);
	}
	class endTurnButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			currPlayerCounter++;
			
			if(currPlayerCounter == Main.allPlayers.size()) {
				currPlayerCounter = 0;
			}
			
			currPlayer = Main.allPlayers.get(currPlayerCounter);
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
				Card thisCard = Main.allCommunityChests.peek();									//PREPEI NA KLEI8EI !!PRIN!! KLEI8EI H cardFunction!!!!
				cardImgName = thisCard.cardImgName;
			}else if(Main.locations.get(currPlayer.position) instanceof Property){
				Property tempLocation =  (Property) Main.locations.get(currPlayer.position);;
				cardImgName = tempLocation.cardImg;
			}
			new MyCanvas();
			
		}
		class MyCanvas extends Canvas{

			public MyCanvas() {

				//MyCanvas m = new MyCanvas();
				super();
				JFrame f = new JFrame();
				f.add(this);
				if (cardImgName.matches(".*\\d.*") && !cardImgName.equals("Chance_GB3S.png") ) {
					f.setSize(319,390);
				}else{
					f.setSize(360,239);
				}
				//f.setLayout(null);
				f.setVisible(true);
				
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

			
			JList<String> propertiestobuild= new JList<String>();
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
			f.setSize(400,200);
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
