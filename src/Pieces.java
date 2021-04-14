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
		g2D.setStroke(new BasicStroke(5)); //Το είδα σε παράδειγμα , δεν ξέρω τι ακριβώς κάνει . Αν λείπει δεν δουλεύει.
		
		
		
		g2D.setColor(Color.BLUE); //Θέτει το χρώμα του πιονιού , θα αντικατασταθεί από εικόνα.
		
		
		g2D.fillOval(0, 0, 40 , 40); //Ορίζει και ζωγραφίζει το μέφεθος του κύκλου, θα αντικατασταθεί με εικόνα.
		
		
	}
	
	public void movePiece (int x, int y) {
		this.setBounds(x,y,40,40); //Ο τρόπος με τον οποίο μετακινήται το πιόνι στο ταμπλό  , θα αντικατασταθεί με εικόνα αλλά η βασική λογική θα είναι η ίδια.
	}
}
