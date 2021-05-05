import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Player tempPlayer;
	
	public JPanel panelbig = new JPanel();
	public JLayeredPane gameP = new JLayeredPane();
	public JPanel sidepanel = new JPanel();
	//public Piece p = new Piece();
	public JButton button = new JButton("Test Button");
	public JLayeredPane jl = new JLayeredPane();
	public JButton rollButton = new JButton("Roll Dice");
	private JPanel propertyOptionsPanel;
	
	private JButton rollDiceButton;
	
	private JButton buyButton;
	private JButton seeLocationInfoButton = new JButton("See location info");
	private JButton buildButton;
	private JButton demolishButton;
	private JButton mortgageButton;
	private JButton tradeButton;
	private JButton seeCardsButton = new JButton("See Cards");
		
	private JButton endTurnButton;
	
	private JTextField ownedByField;
	
	String cardImgName;
	
	
	
	private JPanel playerInformationPanel;
	private JTextField balanceField;
	private JTextField nameField;	
	
	private JPanel boardPanel;
	private Board board = new Board();
	private Piece player1Piece;
	private Piece player2Piece;
	private Piece player3Piece;
	private Piece player4Piece;
	private Dice dice1 = new Dice(150, 180, 40, 40);
	private Dice dice2 = new Dice(210, 180, 40, 40);
	
	
	public GUI(){
		jl.setBounds(6, 6, 632, 630);
		jl.setPreferredSize(new Dimension(400, 400));

		Dice dice1 = new Dice(150, 180, 40, 40);
		jl.add(dice1);

		Dice dice2 = new Dice(210, 180, 40, 40);
		jl.add(dice2);

		gameP.setBounds(0, 0, 700, 700);
		
		
		gameP.setVisible(true);
		
		board.setOpaque(true);//���� ���� ��� ����� ImagePanel
		board.setBounds(0, 0, 700, 700);//���� ���� ��� ����� ImagePanel
		
		
		gameP.add(board , JLayeredPane.DEFAULT_LAYER);
		//gameP.add(p, JLayeredPane.DRAG_LAYER);
		
		board.repaint();
		//p.repaint();
		//gameP.repaint();
		
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
			}
		});

		tempPlayer = new Player("teo", null);
		tempPlayer.position = 2;
		tempPlayer.properties.add((Property) Main.locations.get(1));
		tempPlayer.properties.add((Property) Main.locations.get(5));
		tempPlayer.properties.add((Property) Main.locations.get(6));
		//see Location Info Button
		
		ShowLocationInfoButtonListener l2 = new ShowLocationInfoButtonListener();
		seeLocationInfoButton.addActionListener(l2);

		if (tempPlayer.position !=	4 && tempPlayer.position !=	38 && tempPlayer.position % 10 != 0) {
			sidepanel.add(seeLocationInfoButton);
		}

		//see cards button
		
		seeCardsButtonListener l3 = new seeCardsButtonListener();
		seeCardsButton.addActionListener(l3);
		sidepanel.add(seeCardsButton, BorderLayout.WEST);
		
		
		panelbig.setVisible(true);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(Toolkit.getDefaultToolkit().getScreenSize()); 
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(false);
		this.setVisible(true);
		this.setTitle("");
		this.setContentPane(panelbig);
	}
	class ButtonListener implements ActionListener {
		/*
		 * ��������� �� ����� ���� 50 + 50 (����� ������ ������� ��� �� ����� ��� �������� ��� ������� ,
		 * �� ������ �� ���������� ��� �� ��� ������ ��� ������) ��� ������
		 * ���������� ��� �� components ��� �� ����� � ����������.
		 * 
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		
			
		}
	}
	class seeCardsButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFrame f = new JFrame("Properties");
			
			JList<String> PropertiesJList = new JList<String>();
			DefaultListModel<String> model = new DefaultListModel<String>();
			for(Property thisProperty:tempPlayer.properties) {
				if(thisProperty instanceof Street) {
					model.addElement(thisProperty.name);					
				}else
					model.addElement(thisProperty.name);
			}	
			PropertiesJList.setModel(model);
			
			clickOnPropertiesJListListener listener = new clickOnPropertiesJListListener(PropertiesJList,f);
			PropertiesJList.addListSelectionListener(listener);
			f.add(PropertiesJList);
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
			    	
			    	for(Property thisProperty: tempPlayer.properties) {
			    		if (thisProperty.name.equals(PropertiesJList.getSelectedValue())) {
			    			if (thisProperty instanceof Street) {
				    			JTextField tf =new JTextField((thisProperty.name+" has "+((Street) thisProperty).hotel +" hotels and "+((Street) thisProperty).houses+" houses"));

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
			
			if (Main.locations.get(tempPlayer.position) instanceof ChanceAndCommunityChest) {
				Card thisCard = Main.allCommunityChests.peek();									//PREPEI NA KLEI8EI !!PRIN!! KLEI8EI H cardFunction!!!!
				cardImgName = thisCard.cardImgName;
				
			}else if(Main.locations.get(tempPlayer.position) instanceof Property){
				Property tempLocation =  (Property) Main.locations.get(tempPlayer.position);;
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
				f.setSize(360,239); 
				//f.setLayout(null);
				f.setVisible(true);
				
			}
			public void paint(Graphics g) {
				Toolkit t=Toolkit.getDefaultToolkit();
				Image i=t.getImage("src/Assets/CommunityChestsAndChances/"+cardImgName);

				g.drawImage(i, 0,0,this);	
			}
		}
	}
	
}
