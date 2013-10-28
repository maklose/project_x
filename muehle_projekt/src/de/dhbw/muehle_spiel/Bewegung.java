package de.dhbw.muehle_spiel;

import de.dhbw.muehle.Position;
import de.dhbw.muehle.strategy.IBewegung;

public class Bewegung implements IBewegung{

	private Position von = null;
	private Position nach = null;
	public Position getVon() {
		return von;
	}
	public void setVon(Position von) {
		this.von = von;
	}
	public Position getNach() {
		return nach;
	}
	public void setNach(Position nach) {
		this.nach = nach;
	}
public Bewegung (Position von, Position nach)
{
	if(von == null)
	{
		this.nach = nach;
	}
	else
	{
		this.von = von;
		this.nach = nach;
	}
}
	
	/*
	 * diese Methode überprüft ob zwei bewegungen gleich sind 
	 * und berücksichtigt dabei auch, dass die von variable null sein kann
	 */
	public boolean equals(Bewegung bBewegung)
	{
		if(bBewegung.von == null && this.von == null)
		{
			if(bBewegung.nach.equals(this.nach))
				return true; 
			else	
				return false;
		}
		else if(bBewegung.von.equals(this.von) && bBewegung.nach.equals(this.nach))
			return true;
		else
			return false;
		 
	}
	
	public String toString()
	{
		if(this.von != null)
		return "Bewegung [([VON: [" + this.von.toString() + " NACH: " + this.nach.toString() + "])]";
		else
		return "Bewegung [([NACH: " + this.nach.toString() + "])]";
	}
	@Override
	public Position altePosition() {
		return von;
	}
	@Override
	public Position neuePosition() {
		return nach;
	}

}
