import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;



public class LoginScreenGUI extends JFrame {
	private JPanel panel = new JPanel();
	private JPanel namePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel piecePanel = new JPanel();
	private JTextField playerNameField;
	private JLabel choosePiece;
	private JList sel_piece;
	private DefaultListModel<ImageIcon> listModel = new DefaultListModel();
	private JButton addPlayerButton = new JButton("Add Player");
	private JButton startGameButton = new JButton("Start Game");
	private JButton darkModeButton = new JButton("Dark Mode");
	private TitledBorder title;

	
	
	
	@SuppressWarnings("unchecked")
	public LoginScreenGUI() {
		
	
		title = BorderFactory.createTitledBorder(new LineBorder(Color.LIGHT_GRAY) , "MONOPOLY by Φαντάστικ Τέν");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.gray);
		title.setTitleFont(new Font("SansSerif", Font.ITALIC,13));
		panel.setBorder(title);
		panel.setBackground(getBackground());
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		playerNameField = new JTextField("Enter name here...");
		playerNameField.setFont(new Font("SansSerif", Font.PLAIN,15));
	
		playerNameField.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                playerNameField.setText("");
            }
        });
		namePanel.add(playerNameField , BorderLayout.CENTER);
		
		
		panel.add(namePanel);
		
		choosePiece = new JLabel("Select Piece" , JLabel.CENTER);
		choosePiece.setFont(new Font("SansSerif", Font.BOLD,15));
		
		choosePiece.setAlignmentX(CENTER_ALIGNMENT);
		
		addPlayerButton.setFont(new Font("SansSerif", Font.BOLD,13));
		startGameButton.setFont(new Font("SansSerif", Font.BOLD,13));
		darkModeButton.setFont(new Font("SansSerif", Font.BOLD,13));
		
		try {
			listModel.addElement(new ImageIcon(ImageIO.read(getClass().getResource("Assets/Hat_adobespark.png"))));
			listModel.addElement(new ImageIcon(ImageIO.read(getClass().getResource("Assets/Car_adobespark.png"))));
			listModel.addElement(new ImageIcon(ImageIO.read(getClass().getResource("Assets/Shoe_adobespark.png"))));
			listModel.addElement(new ImageIcon(ImageIO.read(getClass().getResource("Assets/Dog_adobespark.png"))));
			listModel.addElement(new ImageIcon(ImageIO.read(getClass().getResource("Assets/Ship_adobespark.png"))));
			listModel.addElement(new ImageIcon(ImageIO.read(getClass().getResource("Assets/Thimble_adobespark.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		sel_piece = new JList<ImageIcon>(listModel);
		sel_piece.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		sel_piece.setVisibleRowCount(1);
		sel_piece.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		
		
		ButtonListener listener = new ButtonListener(); //For the addPlayerButton
		addPlayerButton.addActionListener(listener);
		panel.add(choosePiece);
		
		ButtonListener2 listener2 = new ButtonListener2(); //For the startGameButton
		startGameButton.addActionListener(listener2);
		
		ButtonListener3 listener3 = new ButtonListener3(); //For the darkModeButton
		darkModeButton.addActionListener(listener3);
		
		panel.add(sel_piece);
		
		buttonPanel.add(addPlayerButton);
		buttonPanel.add(startGameButton);
		buttonPanel.add(darkModeButton);
		
		panel.add(buttonPanel);
		
		panel.repaint();
		
		this.setContentPane(panel);
		this.setSize(700,300);
		this.setVisible(true);
		this.setTitle("Start Screen");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(sel_piece.getSelectedValue()==null || playerNameField.getText().isEmpty() || playerNameField.getText().equals("Enter name here...")){
				
				
				if(Main.allPlayers.size()==6) {
					JOptionPane.showMessageDialog(panel, "The maximum amount of players is reached!");
				}
				else {
					JOptionPane.showMessageDialog(panel, "You have to choose a name and a piece!");
				}
			}
			else if(playerExists(playerNameField.getText())) {
				JOptionPane.showMessageDialog(panel, "A player with the name " + playerNameField.getText() + " already exists!");
			}
			else {
				new Player(playerNameField.getText() , new Piece ((ImageIcon)sel_piece.getSelectedValue()));
				listModel.removeElement(sel_piece.getSelectedValue());
				
			}
			
			playerNameField.setText("Enter name here...");
			
		}
		
		public boolean playerExists(String aName) {
			for(int i=0; i<Main.allPlayers.size(); i++) {
				if(Main.allPlayers.get(i).name.equals(aName)) {
					return true;
				}
			}
		
			return false;
		}
	
	}
	
	class ButtonListener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(Main.allPlayers.size()>=2) {
				new GUI();
				dispose();
				
			}
			else{
				JOptionPane.showMessageDialog(panel, "There must be at least 2 players to start the game!");
			}
			
		}
		
	}
	class ButtonListener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(darkModeButton.getText() == "Dark Mode") {
				panel.setBackground(Color.DARK_GRAY);
				buttonPanel.setBackground(Color.DARK_GRAY);
				namePanel.setBackground(Color.DARK_GRAY);
				title = BorderFactory.createTitledBorder(new LineBorder(Color.LIGHT_GRAY) , "MONOPOLY by Φαντάστικ Τέν");
				choosePiece.setForeground(Color.white);
				sel_piece.setBackground(Color.black);
				
				
				GUI.panelbig.setBackground(Color.DARK_GRAY);
				
				
				
				darkModeButton.setText("Light Mode");
				panel.revalidate();
				panel.repaint();
			}
			else{
				darkModeButton.setText("Dark Mode");
				panel.setBackground(getBackground());
				buttonPanel.setBackground(getBackground());
				namePanel.setBackground(getBackground());
				title = BorderFactory.createTitledBorder(new LineBorder(Color.LIGHT_GRAY) , "MONOPOLY by Φαντάστικ Τέν");
				choosePiece.setForeground(Color.black);
				sel_piece.setBackground(Color.white);
		
				GUI.panelbig.setBackground(getBackground());
				
				revalidate();
				repaint();
			}
			
		}
		
	}
	
}
