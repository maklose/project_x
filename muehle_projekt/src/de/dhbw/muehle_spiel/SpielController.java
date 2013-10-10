package de.dhbw.muehle_spiel;

import de.dhbw.muehle_api.ESpielsteinFarbe;

public class SpielController {
	
	public Spieler aktuellerSpieler;
	
	int i=1;
	
	
	
	/*  Dieser Code in der GUI, um die Spieler zu erzeugen
	*   Spieler Spieler1 = new Spieler(ESpielsteinFarbe.SCHWARZ);
	*   Spieler Spieler2 = new Spieler(ESpielsteinFarbe.WEISS);
    **/
	
	//TO DO: hol die 2 variablen Spieler 1 und 2
	
	
	do {
		if ((i%2)==1) { 
			aktuellerSpieler = Spieler1; 
			Ephase phase= new Ephase.getPhase();
			//Spiel_beendet();
		}
		
		else { 
			aktuellerSpieler=Spieler2;
		    Spieler2.getPhase(); 
		  //Spiel_beendet();
		    }
		
		i++;
	   }
	while (Spiel_beendet==false());
		
			
			
			
			
			
		
	
	
	
	//Phase3: Einer oder beide Spieler haben 3 Steine(Unterscheidung?)
	
	
	
	/* In dieser Klasse werden nicht die Spieler erzeugt, das passiert alles in der GUI
	 * Hier wird lediglich gesteuert welcher Spieler im Moment dran ist 
	 * und in welcher Phase sich die einzelnen Spieler befinden...
	 * */
	
	
	
}
