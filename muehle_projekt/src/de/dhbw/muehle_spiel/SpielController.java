package de.dhbw.muehle_spiel;

import de.dhbw.muehle_api.ESpielsteinFarbe;

public class SpielController {
	
	public Spieler aktuellerSpieler;
	
	
	
	// Die zwei Spieler muessen erzeugt werden, damit das Spiel beginnen kann
	
	Spieler Spieler1 = new Spieler(ESpielsteinFarbe.SCHWARZ);
	
	Spieler Spieler2 = new Spieler(ESpielsteinFarbe.WEISS);
 
	
	
	
	
	//Phase1 : Setzen der Steine
	public void ablauf()
	{
		for (int i=1; i<=18; i++)
		{ 
			if ((i%2)==1) { aktuellerSpieler = Spieler1; }
			
			else { aktuellerSpieler=Spieler2;}
			
	}	
		
	}
	
	/* In dieser Klasse werden nicht die Spieler erzeugt, das passiert alles in der GUI
	 * Hier wird lediglich gesteuert welcher Spieler im Moment dran ist 
	 * und in welcher Phase sich die einzelnen Spieler befinden
	 * */
	
	
	
}
