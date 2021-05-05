import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		ButtonListener listener = new ButtonListener();
		endTurnButton.addActionListener(listener);
		
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
