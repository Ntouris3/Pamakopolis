import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.*;



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

	
	
	
	@SuppressWarnings("unchecked")
	public LoginScreenGUI() {
		
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		playerNameField = new JTextField("Enter name here...");
	
		playerNameField.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                playerNameField.setText("");
            }
        });
		namePanel.add(playerNameField , BorderLayout.CENTER);
		
		
		panel.add(namePanel);
		
		choosePiece = new JLabel("Select Piece" , JLabel.CENTER);
		
		choosePiece.setAlignmentX(CENTER_ALIGNMENT);
		
		
		
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
		
		
		
		ButtonListener listener = new ButtonListener(); //For the addPlayerButton
		addPlayerButton.addActionListener(listener);
		panel.add(choosePiece);
		
		ButtonListener2 listener2 = new ButtonListener2(); //For the startGameButton
		startGameButton.addActionListener(listener2);
		panel.add(sel_piece);
		
		buttonPanel.add(addPlayerButton);
		buttonPanel.add(startGameButton);
		panel.add(buttonPanel);
		
		this.setContentPane(panel);
		this.setSize(700,400);
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

	
}
