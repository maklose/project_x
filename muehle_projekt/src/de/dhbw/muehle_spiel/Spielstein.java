package de.dhbw.muehle_spiel;
import de.dhbw.muehle_api.*;
import de.dhbw.muehle_api.strategy.IBewegung;

public class Spielstein implements ISpielstein, IBewegung
{
	//Variablen
	private ESpielsteinFarbe Spielsteinfarbe;
	private boolean gesperrt;
	private Position akutellePosition;
	private boolean ausgewählt;
	
	
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

	public Spielstein()
	{
		
	}
	
	public void bewegen(Position nachPosition)
	{
		
	}
	
}
