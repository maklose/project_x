package de.dhbw.muehle_spiel;

import de.dhbw.muehle_api.ESpielsteinFarbe;
import de.dhbw.muehle_api.Position;
import de.dhbw.muehle_spiel.Spieler;
import de.dhbw.muehle_spiel.EPhase;

public class SpielController {
	
	Spieler aktuellerSpieler;
	Spieler Spieler1;
	Spieler Spieler2;
	
	//TO DO  Brauch ich einen Konstuktor? 
	
	
	//TO DO: hol die 2 Objekte Spieler 1 und 2.. Aber wie?
	
	for (int i=1;;i++){
	
	if ((i%2)==1) {aktuellerSpieler=Spieler1; }
	else {aktuellerSpieler=Spieler2; }
		
		
	
		aktuellerSpieler.setAmZug(true); 
		EPhase aktuellePhase= aktuellerSpieler.getPhase();
		
		/* Brauch ich diesen Code?
		 * if (aktuellePhase==EPhase.Setzen){ aktuellerSpieler.setzeSpielstein(lPos);}
		*else if(aktuellePhase==EPhase.Schieben){}
		*else if (aktuellePhase==EPhase.Springen){}
		**/
		
		//TO DO: Prüfe ob eine Mühle entstanden ist, wenn ja dann nimm einen Stein weg
	 // TO DO:Prüfe ob das Spiel beendet ist.
		
		aktuellerSpieler.setAmZug(false);
	}	
	  
		
			
			
}
			
