// Autor Marvin


package de.dhbw.muehle_spiel;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*; //definiert Listener-Interfaces
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class test_gui extends JPanel//implements ActionListener 
{
	
	static BufferedImage image;

	public static void main(String[] args)
	{   
		//Die file f�r das Bild wird erstellt
		File imageFile = new File("C:/test.jpg");
		
		//das hauptframe wird erstellt
		JFrame frame = new JFrame("M�hle-Spiel");      
		frame.setSize(1600,1000);
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Erzeugung zweier JPanel-Objekte
        JPanel panelBild = new JPanel();
        
        //hier wird das Bild geladen und in das JPanel panelRot eingef�gt
        
        try {
            image = ImageIO.read(imageFile);
        
        }
        catch(IOException ioex) {
            //abort
            System.exit(1);
        }
        panelBild.add(new JLabel(new ImageIcon(image)));
        
	    /*//rechtes panel wird erstellt
	    JPanel panelRechts = new JPanel();
	    
	    // Hintergrundfarben der JPanels werden gesetzt
	    panelRechts.setBackground(Color.GRAY);*/
        
        //Beschriftungen f�r die beiden Seiten werden erstellt (�ber Labels)
        JLabel labelLinks = new JLabel("hier k�nnte das Spielfeld stehen");
       
        //JLabel labelRechts = new JLabel("hier k�nnte das Men� stehen");
        
        //Labels werden unseren Panels hinzugef�gt
        panelBild.add(labelLinks);
        //panelRechts.add(labelRechts);
 
        // Erzeugung eines JSplitPane-Objektes mit horizontaler Trennung
        JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        // Hier setzen wir links unser linkes JPanel und rechts das rechte
        splitpane.setLeftComponent(panelBild);
        //splitpane.setRightComponent(panelRechts);
        //splitpane.setDividerLocation(1400);
 
        // Hier f�gen wir unserem Dialog unser JSplitPane hinzu
        frame.add(splitpane);

		
		//Men�leiste
        Border bo = new LineBorder(Color.yellow);
        JMenuBar bar = new JMenuBar();
        bar.setBorder(bo);
        JMenu menu = new JMenu("Ich bin ein JMenu");
        JMenuItem item = new JMenuItem("Ich bin das JMenuItem");
        menu.add(item);
        bar.add(menu);
        frame.setJMenuBar(bar);
        
        //hier wird dynamisch die ver�nderung angezeigt wenn man das Fesnter ver�ndert
        Toolkit.getDefaultToolkit().getDesktopProperty("awt.dynamicLayoutSupported");
        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        
        
        
  
        
		frame.setVisible(true);
		
		
		
	}
	
	 public void paintComponent(Graphics g) {
		 
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(),null);
	    }
	
}
