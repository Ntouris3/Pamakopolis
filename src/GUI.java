import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI extends JFrame{
	public Player tempPlayer;
	
	public JPanel panelbig = new JPanel();
	public JLayeredPane gameP = new JLayeredPane();
	public JPanel sidepanel = new JPanel();
	public JButton button = new JButton("Test");
	public JLayeredPane jl = new JLayeredPane();
	public JButton rollButton = new JButton("Roll Dice");
	private JPanel propertyOptionsPanel;
	private JButton rollDiceButton;
	private JButton readLocationInfoButton;
	private JButton readPropertyInfoButton;
	private JButton endTurnButton;
	private JButton buyButton;
	private JTextField ownedByField;
	private JButton buildButton;
	private JButton demolishButton;
	private JButton mortgageButton;
	private JButton tradeButton;
	
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
	
	
	public GUI(Player aPlayer){
		
		tempPlayer = aPlayer;
		
		jl.setBounds(6, 6, 700, 700);
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
		gameP.add(tempPlayer.piece, JLayeredPane.DRAG_LAYER);
		
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

		panelbig.setVisible(true);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1150,750);
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
	}
