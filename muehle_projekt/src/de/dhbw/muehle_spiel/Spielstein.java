package de.dhbw.muehle_spiel;
import de.dhbw.muehle_api.*;
import de.dhbw.muehle_api.strategy.IBewegung;

public class Spielstein implements ISpielstein, IBewegung
{
	//Variablen
	private ESpielsteinFarbe Spielsteinfarbe;
	private boolean gesperrt;
	private Position aktuellePosition;
	private boolean ausgewählt;
	
	public Spielstein(ESpielsteinFarbe lSpielsteinfarbe, Position laktuellePosition)
	{
		Spielsteinfarbe = lSpielsteinfarbe;
		aktuellePosition = laktuellePosition; 
		gesperrt = false;
		ausgewählt = false;
	}	
	
	public void bewegen(Position nachPosition)
	{
		aktuellePosition = nachPosition; 
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

	public Position getAkutellePosition() 
	{
		return aktuellePosition;
	}

	public void setAkutellePosition(Position akutellePosition) 
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

	
	
	
	
}
