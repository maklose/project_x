// Autor Marvin

package de.dhbw.muehle_spiel;
import de.dhbw.muehle_api.*;
import de.dhbw.muehle_api.strategy.IBewegung;
import de.dhbw.muehle_api.strategy.ISpielzug;

public class Spielstein implements ISpielstein, IBewegung
{
	//Variablen
	private ESpielsteinFarbe Spielsteinfarbe; 			//Die Farbe des Spielsteins
	private boolean gesperrt;							//zeigt an ob ein Stein gelöscht werden darf (wenn er z.b. in einer Mühle steht
	private Position aktuellePosition;					//hat die aktuelle Position des Steines
	private boolean ausgewählt; 						//true wenn der Stein ausgewählt wurde
	private int xPos, yPos;								//Die echte Position die ein Spielstein auf dem Feld hat
	
	//neuen Spieltein erzeugen
	public Spielstein(ESpielsteinFarbe lSpielsteinfarbe, Position laktuellePosition, int lxPos, int lyPos)
	{
		Spielsteinfarbe = lSpielsteinfarbe;
		aktuellePosition = laktuellePosition; 
		gesperrt = false;
		ausgewählt = false;
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
	
	public ESpielsteinFarbe getSpielsteinfarbe() 
	{
		return Spielsteinfarbe;
	}

	public void setSpielsteinfarbe(ESpielsteinFarbe spielsteinfarbe) 
	{
		Spielsteinfarbe = spielsteinfarbe;
	}

	public boolean isGesperrt() 
	{
		return gesperrt;
	}

	public void setGesperrt(boolean gesperrt) 
	{
		this.gesperrt = gesperrt;
	}

	public Position getAktuellePosition() 
	{
		return aktuellePosition;
	}

	public void setAktuellePosition(Position akutellePosition) 
	{
		this.aktuellePosition = akutellePosition;
	}

	public boolean isAusgewählt() 
	{
		return ausgewählt;
	}

	public void setAusgewählt(boolean ausgewählt) 
	{
		this.ausgewählt = ausgewählt;
	}

	@Override
	public Position altePosition() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position neuePosition() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ESpielsteinFarbe getFarbe() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getPosition() 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean FarbVergleich(ESpielsteinFarbe b)
	{
		if(this.getSpielsteinfarbe() == b)
			return true;
		else
			return false;
	}

	
}
