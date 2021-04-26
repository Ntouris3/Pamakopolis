import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;



public class LoginScreenGUI extends JFrame {
	private JPanel panel = new JPanel();
	private JPanel namePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JTextField playerNameField;
	private JLabel playerName,choosePiece;
	private JList sel_piece;
	private DefaultListModel listModel = new DefaultListModel();
	private JButton addPlayerButton = new JButton("Προσθήκη παίκτη");
	private JButton startGameButton = new JButton("Έναρξη παιχνιδιού");
	
	
	
	@SuppressWarnings("unchecked")
	public LoginScreenGUI() {
		
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		playerName = new JLabel();
		
		
		playerNameField = new JTextField("Όνομα παίκτη ...");
		//playerNameField.setPreferredSize(new Dimension(130,18));
		namePanel.add(playerNameField);
		
		panel.add(playerName);
		panel.add(namePanel);
		
		choosePiece = new JLabel();
		choosePiece.setText("Επιλέξτε πιόνι:");
		choosePiece.setHorizontalAlignment(JLabel.CENTER);
		
		
		
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
		
		sel_piece = new JList(listModel);
		sel_piece.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		sel_piece.setVisibleRowCount(1);
		
		
		ButtonListener listener = new ButtonListener(); //Για το addPlayerButton
		addPlayerButton.addActionListener(listener);
		panel.add(choosePiece);
		
		ButtonListener2 listener2 = new ButtonListener2(); //Για το startGameButton
		startGameButton.addActionListener(listener2);
		panel.add(sel_piece);
		
		
		
		buttonPanel.add(addPlayerButton);
		buttonPanel.add(startGameButton);
		panel.add(buttonPanel);
		
		
		
		this.setContentPane(panel);
		this.setSize(700,400);
		this.setVisible(true);
		this.setTitle("Ξεκίνημα Παιχνιδιού");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(sel_piece.getSelectedValue()==null || playerNameField.getText().isEmpty() || playerNameField.getText().equals("Όνομα παίκτη ...")){
				
				JOptionPane.showMessageDialog(panel, "Πρέπει να επιλέξετε και όνομα και πιόνι!");
				
			}
			/*else if(Player.allPlayers.size()==6) {
				JOptionPane.showMessageDialog(panel, "Φτάσατε τον μέγιστο αριθμό παικτών");
			}
			else if(Player.playerExists(playerNameField.getText())) {
				JOptionPane.showMessageDialog(panel, "Ο παίκτης " + playerNameField.getText() + " υπάρχει ήδη! Παρακαλούμε διαλέξτε άλλο όνομα.");
			}*/
			else {
				//new Player(playerNameField.getText() , sel_piece.getSelectedValue());
				listModel.removeElement(sel_piece.getSelectedValue());
			}
		}
		
	}
	
	class ButtonListener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			/*if(Player.allPlayers.size()>2) {
				new Gui(Player.allPlayers.getIndex(0));
				
				
			}
			else{
				JOptionPane.showMessageDialog(panel, "Απαιτούνται τουλάχιστον 2 παίκτες για την έναρξη του παιχνιδιού!");
			}*/
			
		}
		
	}

	
}

