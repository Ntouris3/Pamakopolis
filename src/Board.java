import java.awt.*;
import javax.swing.*;


public class Board extends JComponent{
	
	
	
	/*public Board () {
		//this.setDefaultCloseOperation(JPanel.EXIT_ON_CLOSE);
		//this.setSize(1000,1000);
		this.add(paintComponent);
		this.setVisible(true);
		//this.setTitle("");

		
	}*/
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(5)); //�� ���� �� ���������� , ��� ���� �� ������� ����� . �� ������ ��� ��������.
		
		
		int startpointX=0; // � ������������ ��� ����� � ���� �� ��������� � ��������� ��� ��������.
		int startpointY=0;	// � ������������ ��� ����� � ���� �� ��������� � ��������� ��� ��������.
		//� Java ������ ���� ��� ������ � ��� � ��� ���� �������� ����� ��� ���������/frame/panel ��� ������������ �� �����.
		
		/* �� ��� ������������ for ���������� ���� 2D ������ �� �����������. �� if �������� ��� �� ������������ �� ������ ��������� ��� �� ������� � ������� �� 
		 * ��������. ������ ���� ��� if �� �������� �� ���������� ����������� ��� ������� ���� ������ �� ��� �� ��������, ���� �� ��� ��������� ��� �� ������ 
		 * ��� �������������� ��� ��������. 
		*/
		
		//col = ����� , row �����
		for(int col=0; col<10; col++) { 
			for(int row=0; row<10; row++) {
					
					if((row==0 || row==9) && (col==0 || col==9)) {
						
						g2D.drawRect(startpointX,startpointY,95,95); //��������� ��������� ���������� 90x90.
						g2D.setColor(Color.BLACK); //������ �� ����� ��� �������������.
						startpointX=startpointX+95; //��������� �� startpointX 90 ������� (pixels?) ��� ����� ��� �� ������� ����������.
					}
					else if(col==0 || col==9){
						
						g2D.drawRect(startpointX,startpointY,67,95);
						g2D.setColor(Color.BLACK);
						startpointX=startpointX+67;
					
					}
					else if((col!=0 && col!=9) && (row==0 || row==9)){
						g2D.drawRect(startpointX,startpointY,95,67);
						g2D.setColor(Color.BLACK);
						startpointX=startpointX+95;
					}
					else {
						startpointX=startpointX+67;
					}
			}
			
			/*����� �������� ��� ����� ��� ������ , ������ �� � �� ����� 40 ��� �� � �� ������� ���� 90 � 63 ��� ��
			 * ������� �� ����� ����������� ��� ��� ���� ������.
			 * */
			startpointX=0;  
			
			if(col==0 || col==9) {
				startpointY=startpointY+95;
			}
			else {
				startpointY=startpointY+67;
			}
			
		}
	}
	
	
}
