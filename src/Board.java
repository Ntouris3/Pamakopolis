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
		g2D.setStroke(new BasicStroke(5)); //Το είδα σε παράδειγμα , δεν ξέρω τι ακριβώς κάνει . Αν λείπει δεν δουλεύει.
		
		
		int startpointX=0; // Η συντεταγμένη του άξονα Χ όπου θα ξεκινήσει η αποτύπωση του σχήματος.
		int startpointY=0;	// Η συντεταγμένη του άξονα Υ όπου θα ξεκινήσει η αποτύπωση του σχήματος.
		//Η Java θεωρεί αρχή των αξόνων Χ και Υ την πάνω αριστερή γωνία του παραθύρου/frame/panel που αποτυπώνεται το σχήμα.
		
		/* Οι δύο εμφωλευμένες for σχεδιάζουν έναν 2D πίνακα με τετράπλευρο. Οι if υπάρχουν για να δημιουργηθεί το μεγάλο τετράγωνο και να μοιάζει ο πίνακας με 
		 * Μονόπολη. Επίσης μέσω των if θα μπορούμε να τραβήξουμε πληροφορίες από κάποιον άλλο πίνακα με όλα τα οικόπεδα, μαζί με μία παράμετρο που θα ορίζει 
		 * τον προσανατολισμό των κειμένων. 
		*/
		
		//col = στήλη , row σειρά
		for(int col=0; col<10; col++) { 
			for(int row=0; row<10; row++) {
					
					if((row==0 || row==9) && (col==0 || col==9)) {
						
						g2D.drawRect(startpointX,startpointY,95,95); //σχεδιάζει τετράγωνο διαστάσεων 90x90.
						g2D.setColor(Color.BLACK); //Ορίζει το χρώμα του περιγράμματος.
						startpointX=startpointX+95; //Μετακινεί το startpointX 90 μονάδες (pixels?) στα δεξιά για το επόμενο τεράπλευρο.
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
			
			/*Μόλις φτάσουμε στο τέλος της σειράς , πρέπει το Χ να γίνει 40 και το Υ να αυξηθεί κατά 90 ή 63 για να
			 * τυπωθεί το πρώτο τετράπλευρο της από κάτω σειράς.
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
