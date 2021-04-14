import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Gui extends JFrame{
	public JPanel panelbig = new JPanel();
	public JLayeredPane gameP = new JLayeredPane();
	public JPanel sidepanel = new JPanel();
	public Pieces p = new Pieces();
	public JButton button = new JButton("Test");
	public ImagePanel board = new ImagePanel();
	

	public Gui(){
		
		gameP.setBounds(0, 0, 700, 700);
		
		
		gameP.setVisible(true);
		
		board.setOpaque(true);//���� ���� ��� ����� ImagePanel
		board.setBounds(0, 0, 700, 700);//���� ���� ��� ����� ImagePanel
		
		
		gameP.add(board , JLayeredPane.DEFAULT_LAYER);
		gameP.add(p , JLayeredPane.DRAG_LAYER);
		
		board.repaint();
		p.repaint();
		//gameP.repaint();
		
		panelbig.setLayout(new BorderLayout());
		panelbig.add(gameP , BorderLayout.CENTER);
		
		
		panelbig.add(sidepanel , BorderLayout.EAST);
		sidepanel.add(button);
		
		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		
		panelbig.setVisible(true);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,1000);
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
			
			p.movePiece(p.getX()+50, p.getY()+50);
			p.repaint();
			gameP.repaint();
			panelbig.repaint();
			
		}
	}
}

