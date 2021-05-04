import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.sun.tools.jdeps.JdepsConfiguration.Builder;

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
	private JButton buildButton; //äéêï ìïõ
	private JButton demolishButton; //äéêï ìïõ
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
	
	
	public GUI(){
		jl.setBounds(6, 6, 632, 630);
		jl.setPreferredSize(new Dimension(400, 400));

		Dice dice1 = new Dice(150, 180, 40, 40);
		jl.add(dice1);

		Dice dice2 = new Dice(210, 180, 40, 40);
		jl.add(dice2);

		gameP.setBounds(0, 0, 700, 700);
		
		
		gameP.setVisible(true);
		
		board.setOpaque(true);//ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ ImagePanel
		board.setBounds(0, 0, 700, 700);//ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ ImagePanel
		
		
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
		
		ButtonListener2 listener2 = new ButtonListener2();
		buildButton.addActionListener(listener2);
		
		ButtonListener3 listener3 = new ButtonListener3();
		demolishButton.addActionListener(listener3);
		
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dice1.rollDice();
				dice2.rollDice();
			}
		});

		panelbig.setVisible(true);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,1000);
		this.setVisible(true);
		this.setTitle("");
		this.setContentPane(panelbig);
	}
	class ButtonListener implements ActionListener {
		/*
		 * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ 50 + 50 (ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ,
		 * ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½) ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		 * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ components ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½.
		 * 
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		
			
		}
	}
	
	
	
	class ButtonListener2 implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//BUILD
			//ask for number 1-4 houses, number 5 hotel
			//ðùò èá îåñïõìå óå ðïéá éäéïêôçóéá èá åéíáé 
			
			//tempPlayer.position
			JList<String> sugglist= new JList<String>();
			DefaultListModel<String> listprop = new DefaultListModel<>();

			for (Property prop1:tempPlayer.properties) {
				listprop.addElement(prop1.name);
			}
			sugglist.setModel(listprop);
			
			JTextField number;
			int counter = (Integer.parseInt(number.getText()));
			this.Street.Build(tempPlayer, number);
			
		}
	}
	
	class ButtonListener3 implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//DEMOLISH
			
			
		}
	}

	
	}
