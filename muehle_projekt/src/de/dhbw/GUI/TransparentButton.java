package de.dhbw.GUI;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class TransparentButton extends JButton {
	public TransparentButton(String text) { 
	    super(text);
	    setOpaque(false); 
	} 
	    
	public void paint(Graphics g) { 
	    Graphics2D g2 = (Graphics2D) g.create(); 
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); //hier lässt sich der Grad der Transparenz einstellen
	    super.paint(g2); 
	    g2.dispose(); 
	} 
}

