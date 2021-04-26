import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Piece extends JComponent{
	
	private BufferedImage pieceimg;
	
	public Piece() {
		this.setOpaque(true);
		this.setBounds(0, 0, 40, 40);
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(5)); //�� ���� �� ���������� , ��� ���� �� ������� ����� . �� ������ ��� ��������.
		
		
		
		g2D.setColor(Color.BLUE); //����� �� ����� ��� ������� , �� �������������� ��� ������.
		
		
		g2D.fillOval(0, 0, 40 , 40); //������ ��� ���������� �� ������� ��� ������, �� �������������� �� ������.
		
		
	}
	
	public void MoveOnBoard(int diceValue) {
		
	}
}
