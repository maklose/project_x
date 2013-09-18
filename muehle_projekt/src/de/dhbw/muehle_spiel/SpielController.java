package de.dhbw.muehle_spiel;

import de.dhbw.muehle_api.ESpielsteinFarbe;

public class SpielController {

	public Spieler aktuellerSpieler;
	
	
	// Die zwei Spieler muessen erzeugt werden, damit das Spiel beginnen kann
	

	public void erzeugeSpieler(Spieler lSpieler)
	{
		Spieler Spieler1 = new Spieler(ESpielsteinFarbe.SCHWARZ);
		
		Spieler Spieler2 = new Spieler(ESpielsteinFarbe.WEISS);
		
	}
	
	
	
	public boolean Spielbeendet()
	{
		if (   )   { return true;}
		
		else { return false;}
		
	}
	
	
	
	
}
