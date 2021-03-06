// Autor Marvin

package de.dhbw.muehle_spiel;
import de.dhbw.muehle_api.*;

public class Spielstein implements ISpielstein
{
	//Variablen
	private ESpielsteinFarbe Spielsteinfarbe; 			//Die Farbe des Spielsteins
	private Position aktuellePosition;					//hat die aktuelle Position des Steines
	private int xPos, yPos;								//Die echte Position die ein Spielstein auf dem Feld hat
	private int index;
	
	//neuen Spieltein erzeugen
	public Spielstein(ESpielsteinFarbe lSpielsteinfarbe, Position laktuellePosition, int lxPos, int lyPos, int lindex)
	{
		Spielsteinfarbe = lSpielsteinfarbe;
		aktuellePosition = laktuellePosition;
		index = lindex;
		xPos = lxPos;
		yPos = lyPos;

	}	
	
	public int getxPos() {
		return xPos;
	}
	
	
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}


	public void setyPos(int yPos) {
		this.yPos = yPos;
	}


	public int getyPos() {
		return yPos;
	}
		
	//hiermit kann man einen Spielstein bewegen 
	public void bewegen(Bewegung neueBewegung, int lxPos, int lyPos)
	{
		aktuellePosition = neueBewegung.getNach(); 
		xPos = lxPos;
		yPos = lyPos;

	}

	@Override
	public ESpielsteinFarbe getFarbe() 
	{
		return Spielsteinfarbe;
	}

	@Override
	public Position getPosition() 
	{
		return aktuellePosition;
	}
	
	
	public void setPosition(Position position) {
		aktuellePosition = position;
	}

	public boolean FarbVergleich(ESpielsteinFarbe b)
	{
		if(this.getFarbe() == b)
			return true;
		else
			return false;
	}

	public int getIndex() {
		return index;
	}
	
	public boolean vergleichen(Spielstein lSpielstein)
	{
		if(lSpielstein != null)
		{
			if(this.getIndex() == lSpielstein.getIndex())
				return true;
			else
				return false;
		}
		else
			return false;
	}

	@Override
	public String toString() {

		return "Spielstein [Spielsteinfarbe=" + Spielsteinfarbe
				+ ", aktuellePosition=" + aktuellePosition + ", index=" + index
				+ "]";

	}

	
}
