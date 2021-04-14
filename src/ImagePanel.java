import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    /**
	 * 
	 */
	
	private BufferedImage image;

    public ImagePanel() {
                    
        
          try {
			image = ImageIO.read(getClass().getResource("Board.jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        
          
          this.setSize(image.getWidth(), image.getHeight());
  
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth() , getHeight() ,this); // see javadoc for more info on the parameters            
    }

}