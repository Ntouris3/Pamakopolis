import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI extends JFrame{
	private Player tempPlayer;
	
	public JPanel panelbig = new JPanel();
	public JLayeredPane gameP = new JLayeredPane();
	public JPanel sidepanel = new JPanel();
	//public Piece p = new Piece();
	public JButton button = new JButton("Test");
	public JLayeredPane jl = new JLayeredPane();
	public JButton rollButton = new JButton("Roll Dice");
	private JPanel propertyOptionsPanel;
	
	private JButton rollDiceButton;
	
	private JButton buyButton;
	private JButton seeLocationInfoButton;
	private JButton buildButton= new JButton("Build"); //δικο μου
	private JButton demolishButton= new JButton("Demolish"); //δικο μου
	private JButton mortgageButton;
	private JButton tradeButton;
	private JButton seeCardsButton;
		
	private JButton endTurnButton;
	
	private JTextField ownedByField;
	

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
	public int number;
	
	
	public GUI(){
		jl.setBounds(6, 6, 632, 630);
		jl.setPreferredSize(new Dimension(400, 400));

		Dice dice1 = new Dice(150, 180, 40, 40);
		jl.add(dice1);

		Dice dice2 = new Dice(210, 180, 40, 40);
		jl.add(dice2);

		gameP.setBounds(0, 0, 700, 700);
		
		
		gameP.setVisible(true);
		
		board.setOpaque(true);//οΏ½οΏ½οΏ½οΏ½ οΏ½οΏ½οΏ½οΏ½ οΏ½οΏ½οΏ½ οΏ½οΏ½οΏ½οΏ½οΏ½ ImagePanel
		board.setBounds(0, 0, 700, 700);//οΏ½οΏ½οΏ½οΏ½ οΏ½οΏ½οΏ½οΏ½ οΏ½οΏ½οΏ½ οΏ½οΏ½οΏ½οΏ½οΏ½ ImagePanel
		
		
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
		
		
		sidepanel.add(buildButton);
		sidepanel.add(demolishButton, BorderLayout.SOUTH);
		
		ButtonListener listener = new ButtonListener();
		buildButton.addActionListener(listener);
		
		ButtonListener2 listener2 = new ButtonListener2();
		demolishButton.addActionListener(listener2);
		
		
		
		
		//DEMO CODE
		tempPlayer=new Player("tasos", null);
		tempPlayer.properties.add((Property) Main.locations.get(1));
		tempPlayer.properties.add((Property) Main.locations.get(3));
		tempPlayer.properties.add((Property) Main.locations.get(5));
		
		
	
		
		
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dice1.rollDice();
				dice2.rollDice();
			}
		});

		panelbig.setVisible(true);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1100,750);
		this.setVisible(true);
		this.setTitle("");
		this.setContentPane(panelbig);
	}

	
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//BUILD
			//ask for number 1-4 houses, number 5 hotel
			
			
			JFrame f = new JFrame();
			JPanel panels=new JPanel();

			
			JList<String> propertiestobuild= new JList<String>();
			DefaultListModel<String> listprop = new DefaultListModel<>();
						
			//βαζουμε τα props σε μια λιστα που επιλεγει
			for (Property prop1:tempPlayer.properties) {
				if ((prop1.name.equals("Reading Railroad")) || (prop1.name.equals("Pennsylvania Railroad")) || (prop1.name.equals("B. & O. Railroad")) || (prop1.name.equals("Short Line")) || (prop1.name.equals("Electric Company")) || (prop1.name.equals("Water Works"))) {
					//listprop.addElement(prop1.name);
				}
				else {
					listprop.addElement(prop1.name);
				}
			}
			propertiestobuild.setModel(listprop);
			
			
			String[] choices = { "1 house", "2 houses", "3 houses", "4 houses", "Hotel" };
			JComboBox<String> choicelist = new JComboBox<String>(choices);
			
			
			panels.add(choicelist);
			panels.add(propertiestobuild);
			
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
			
			propertiestobuild.addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	for (int i=0; i<tempPlayer.properties.size(); i++) {
	                		if (propertiestobuild.getSelectedValue().toString().equals(tempPlayer.properties.get(i).name)) {
	                			((Street)tempPlayer.properties.get(i)).Build(tempPlayer, number);
	                			System.out.println(number);
	                			System.out.println(tempPlayer.properties.get(i).name);
	                		}
	                	}
	                }
	            }

	        });		
			
			f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			f.setSize(400,400);
			f.setContentPane(panels);
			f.setTitle("BUILD");
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
						
			//βαζουμε τα props σε μια λιστα που επιλεγει
			for (Property prop1:tempPlayer.properties) {
				if ((prop1.name.equals("Reading Railroad")) || (prop1.name.equals("Pennsylvania Railroad")) || (prop1.name.equals("B. & O. Railroad")) || (prop1.name.equals("Short Line")) || (prop1.name.equals("Electric Company")) || (prop1.name.equals("Water Works"))) {
					//listprop.addElement(prop1.name);
				}
				else {
					listprop.addElement(prop1.name);
				}
			}
			propertiestodemolish.setModel(listprop);
			
			ArrayList<String> choices=new ArrayList<String>();

			for (Property prop1:tempPlayer.properties) {
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

	    	String[] array = choices.toArray(new String[0]);
			JComboBox<String> choicelist = new JComboBox<String>(array);


			
			
			panels.add(choicelist);
			panels.add(propertiestodemolish);
			
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
	                	for (int i=0; i<tempPlayer.properties.size(); i++) {
	                		if (propertiestodemolish.getSelectedValue().toString().equals(tempPlayer.properties.get(i).name)) {
	                			((Street)tempPlayer.properties.get(i)).Build(tempPlayer, number);
	                			System.out.println(number);
	                			System.out.println(tempPlayer.properties.get(i).name);
	                		}
	                	}
	                }
	            }

	        });		
			
			f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			f.setSize(400,400);
			f.setContentPane(panels);
			f.setTitle("BUILD");
			f.setVisible(true);
			
						
				}
	}

	
	}
