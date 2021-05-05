import java.awt.BorderLayout;
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
	private DefaultListModel<ImageIcon> listModel = new DefaultListModel();
	private JButton addPlayerButton = new JButton("ÐñïóèÞêç ðáßêôç");
	private JButton startGameButton = new JButton("¸íáñîç ðáé÷íéäéïý");
	private Piece piece1,piece2,piece3,piece4,piece5,piece6;
	
	
	
	@SuppressWarnings("unchecked")
	public LoginScreenGUI() {
		
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		playerName = new JLabel();
		
		
		playerNameField = new JTextField("¼íïìá ðáßêôç ...");
	
		namePanel.add(playerNameField , BorderLayout.CENTER);
		
		//panel.add();
		panel.add(namePanel);
		
		choosePiece = new JLabel();
		choosePiece.setText("ÅðéëÝîôå ðéüíé:");
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
		
		/*piece1 = new Piece("Car_adobespark.png");
		piece2 = new Piece("Dog_adobespark.png");
		piece3 = new Piece("Hat_adobespark.png");
		piece4 = new Piece("Ship_adobespark.png");
		piece5 = new Piece("Shoe_adobespark.png");
		piece6 = new Piece("Thimble_adobespark.png");*/
		/*
		listModel.addElement(piece1.pieceimg);
		listModel.addElement(piece2.pieceimg);
		listModel.addElement(piece3.pieceimg);
		listModel.addElement(piece4.pieceimg);
		listModel.addElement(piece5.pieceimg);
		listModel.addElement(piece6.pieceimg);
		*/
		sel_piece = new JList<ImageIcon>(listModel);
		sel_piece.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		sel_piece.setVisibleRowCount(1);
		
		
		ButtonListener listener = new ButtonListener(); //Ãéá ôï addPlayerButton
		addPlayerButton.addActionListener(listener);
		panel.add(choosePiece);
		
		ButtonListener2 listener2 = new ButtonListener2(); //Ãéá ôï startGameButton
		startGameButton.addActionListener(listener2);
		panel.add(sel_piece);
		
		
		
		buttonPanel.add(addPlayerButton);
		buttonPanel.add(startGameButton);
		panel.add(buttonPanel);
		
		
		
		this.setContentPane(panel);
		this.setSize(700,400);
		this.setVisible(true);
		this.setTitle("Îåêßíçìá Ðáé÷íéäéïý");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(sel_piece.getSelectedValue()==null || playerNameField.getText().isEmpty() || playerNameField.getText().equals("¼íïìá ðáßêôç ...")){
				
				
				if(Main.allPlayers.size()==6) {
					JOptionPane.showMessageDialog(panel, "ÖôÜóáôå ôïí ìÝãéóôï áñéèìü ðáéêôþí");
				}
				else {
					JOptionPane.showMessageDialog(panel, "ÐñÝðåé íá åðéëÝîåôå êáé üíïìá êáé ðéüíé!");
				}
			}
			else if(playerExists(playerNameField.getText())) {
				JOptionPane.showMessageDialog(panel, "Ï ðáßêôçò " + playerNameField.getText() + " õðÜñ÷åé Þäç! Ðáñáêáëïýìå äéáëÝîôå Üëëï üíïìá.");
			}
			else {
				new Player(playerNameField.getText() , new Piece ((ImageIcon)sel_piece.getSelectedValue()));
				listModel.removeElement(sel_piece.getSelectedValue());
			}
			
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
			
			/*if(Player.allPlayers.size()>2) {
				new Gui(Player.allPlayers.getIndex(0));
				
				
			}
			else{
				JOptionPane.showMessageDialog(panel, "Áðáéôïýíôáé ôïõëÜ÷éóôïí 2 ðáßêôåò ãéá ôçí Ýíáñîç ôïõ ðáé÷íéäéïý!");
			}*/
			
		}
		
	}

	
}
