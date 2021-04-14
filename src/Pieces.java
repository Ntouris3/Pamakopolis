import java.awt.*;
import javax.swing.*;

import com.sun.beans.editors.ColorEditor;

public class Pieces extends JComponent {
	
	public Pieces () {
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
	
	public void movePiece (int x, int y) {
		this.setBounds(x,y,40,40); //� ������ �� ��� ����� ����������� �� ����� ��� ������  , �� �������������� �� ������ ���� � ������ ������ �� ����� � ����.
	}
}
