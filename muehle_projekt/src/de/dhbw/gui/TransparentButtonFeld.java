package de.dhbw.gui;
import de.dhbw.muehle_api.*;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class TransparentButtonFeld extends JButton 
{
	
	Position gPosition;
	
	public TransparentButtonFeld(String text, Position lPosition) 
	{ 
	    super(text);
	    setOpaque(false); 
	    gPosition = lPosition;
	} 
	    
	public void paint(Graphics g) { 
	    Graphics2D g2 = (Graphics2D) g.create(); 
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0)); // 0.5f hier lässt sich der Grad der Transparenz einstellen
	    super.paint(g2); 
	    g2.dispose(); 
	} 
	
	public Position getPosition()
	{
		return gPosition;
	}
	
	public boolean vergleichePosition(Position lPosition)
	{
		if(gPosition.equals(lPosition))
			return true;
		return false;
	}
	
	
}
