package de.dhbw.muehle_spiel;
import de.dhbw.muehle_api.*;
import de.dhbw.muehle_api.strategy.IBewegung;
import de.dhbw.muehle_api.strategy.ISpielzug;

public class Spielstein implements ISpielstein, IBewegung
{
	//Variablen
	private ESpielsteinFarbe Spielsteinfarbe; 			//Die Farbe des Spielsteins
	private boolean gesperrt;							//zeigt an ob ein Stein gel�scht werden darf (wenn er z.b. in einer M�hle steht
	private Position aktuellePosition;					//hat die aktuelle Position des Steines
	private boolean ausgew�hlt; 						//true wenn der Stein ausgew�hlt wurde
	
	public Spielstein(ESpielsteinFarbe lSpielsteinfarbe, Position laktuellePosition)
	{
		Spielsteinfarbe = lSpielsteinfarbe;
		aktuellePosition = laktuellePosition; 
		gesperrt = false;
		ausgew�hlt = false;
	}	
	
	
	//hiermit kann man einen Spielstein bewegen 
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

	public Position getAktuellePosition() 
	{
		return aktuellePosition;
	}

	public void setAktuellePosition(Position akutellePosition) 
	{
		this.aktuellePosition = akutellePosition;
	}

	public boolean isAusgew�hlt() 
	{
		return ausgew�hlt;
	}

	public void setAusgew�hlt(boolean ausgew�hlt) 
	{
		this.ausgew�hlt = ausgew�hlt;
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
