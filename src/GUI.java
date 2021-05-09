import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI extends JFrame{

	private static final long serialVersionUID = 1L;

	private Player currPlayer;
	
	public static JPanel panelbig = new JPanel();
	public static JLayeredPane gameP = new JLayeredPane();
	public JPanel sidepanel = new JPanel();
	private JPanel propertyOptionsPanel;
	public JLayeredPane jl = new JLayeredPane();
	public JButton rollButton = new JButton("Roll Dice");	

	private JButton buyButton;
	private JButton seeLocationInfoButton = new JButton("");
	private JButton buildButton;
	private JButton demolishButton;
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
	
	
	public GUI(){
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
		sidepanel.add(button,BorderLayout.SOUTH);
		sidepanel.add(rollButton, BorderLayout.NORTH);
		sidepanel.add(jl, BorderLayout.EAST);

		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dice1.rollDice();
				dice2.rollDice();
				dice1.paintImmediately(getX(), getY(), getWidth(), getHeight());
				dice2.paintImmediately(getX(), getY(), getWidth(), getHeight());
				int newPos = currPlayer.position + dice1.getFaceValue() + dice2.getFaceValue();
				currPlayer.ChangePosition(newPos);
			}
		});

		
		for (Card thisc:Main.allChances) {
			if (thisc instanceof GetOutOfJailCard) {
				currPlayer.jailCards.add((GetOutOfJailCard) thisc);
			}
		}
		

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
		sidepanel.add(seeCardsButton, BorderLayout.WEST);
		
		
		panelbig.setVisible(true);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(false);
		this.setVisible(true);
		this.setTitle("");
		this.setContentPane(panelbig);
	}
	class ButtonListener implements ActionListener {
		
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
}
