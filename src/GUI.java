import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class GUI extends JFrame{
	public Player currPlayer;
	
	public static JPanel panelbig = new JPanel();
	public JPanel sidepanel = new JPanel();
	private JPanel propertyOptionsPanel;
	private JPanel playerInformationPanel;
	private JPanel boardPanel;
	
	public JLayeredPane jl = new JLayeredPane();
	public static JLayeredPane gameP = new JLayeredPane();
	
	public JButton rollButton = new JButton("Roll Dice");
	private JButton rollDiceButton;
	private JButton readLocationInfoButton;
	private JButton readPropertyInfoButton;
	private JButton endTurnButton = new JButton("End Turn");
	private JButton buyButton;
	private JButton buildButton;
	private JButton demolishButton;
	private JButton mortgageButton;
	private JButton tradeButton;
	
	private JTextField ownedByField;
	private JTextField balanceField;
	private JTextField nameField;	
	
	private Board board = new Board();
	
	public Dice dice1 = new Dice(150, 180, 40, 40);
	public Dice dice2 = new Dice(210, 180, 40, 40);
	
	int currPlayerCounter = 0;
	
	public GUI(){
		buyButton = new JButton("BUY");
		mortgageButton = new JButton("MORTGAGE");
		Main.allPlayers.add(new Player("Chris",new Piece(null)));
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
		sidepanel.add(endTurnButton,BorderLayout.SOUTH);
		sidepanel.add(rollButton, BorderLayout.NORTH);
		sidepanel.add(jl, BorderLayout.EAST);
		sidepanel.add(mortgageButton);
		sidepanel.add(buyButton,BorderLayout.WEST);
		
		ButtonListener listener = new ButtonListener();
		endTurnButton.addActionListener(listener);
		
		//Disabling buy button when player is in a position in which he cant buy a property//
		if(Main.locations.get(currPlayer.position).getClass().equals(Location.class) || Main.locations.get(currPlayer.position).getClass().equals(GoToJail.class) || Main.locations.get(currPlayer.position).getClass().equals(Tax.class)) {
			buyButton.setEnabled(false);
		}
		else { 
			if(((Property)Main.locations.get(currPlayer.position)).getOwner() != null){
				buyButton.setEnabled(false);
			}	
		}
		
		//TESTING MORTGAGE BUTTON BY GIVING RANDOM PROPERTIES TO PLAYER
		//currPlayer.properties.add((Property) Main.locations.get(1));
		//currPlayer.properties.add((Property) Main.locations.get(3));
		//currPlayer.properties.add((Property) Main.locations.get(5));
		//((Property) Main.locations.get(1)).isMortgaged=true;
		
		//DISABLING MORTGAGE BUTTON WHEN PLAYER HAS NO PROPERTIES
		if(currPlayer.properties.size()==0) {
			mortgageButton.setEnabled(false);
		}
		
		//ROLL BUTTON
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
				JButton b2 = new JButton("Unmortgage");
				JList <Property> list = new JList(currPlayer.properties.toArray());
				DefaultListModel<Property> model;
				
				p.add(list);
				p.add(b1);
				p.add(b2);
				model = new DefaultListModel<Property>();
				for(Property prop: currPlayer.properties) {
					model.addElement(prop);
				}
				list.setModel(model);
				
				//ENABLING AND DISABLING THE BUTTONS DEPENDING PLAYERS SELECTION
				MouseListener mouseListener = new MouseAdapter() {
				    public void mouseClicked(MouseEvent e) {
				        if (e.getClickCount() > 0) {
				        	if(list.getSelectedValue().isMortgaged==true) {
				        		b1.setEnabled(false);
				        		b2.setEnabled(true);
				        	}
				        	else {
				        		b2.setEnabled(false);
				        		b1.setEnabled(true);
				        	}
				         }
				    }
				};
				list.addMouseListener(mouseListener);
				
				//Mortgage Button//
				b1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Property pro = list.getSelectedValue();
						currPlayer.AddToMortgage(pro);
					}
				});
				
				//UnMortgage Button//
				b2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Property pro = list.getSelectedValue();
						currPlayer.Unmortgage(pro);
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
		
		panelbig.setVisible(true);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1150,750);
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
}