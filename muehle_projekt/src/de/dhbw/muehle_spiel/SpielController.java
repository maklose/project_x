package de.dhbw.muehle_spiel;

import de.dhbw.muehle_api.ESpielsteinFarbe;
import de.dhbw.muehle_api.Position;
import de.dhbw.muehle_spiel.Spieler;
import de.dhbw.muehle_spiel.EPhase;

public class SpielController {
	
	Spieler Spieler1;
	Spieler Spieler2;
	
	
	
	if ((Spieler1.getAnzahlZuege()+Spieler2.getAnzahlZuege())%2==0){
		
		SpielerAktiv=Spieler1;
		SpielerPassiv=Spieler2;}
	
	else {
		SpielerAktiv=Spieler2;
	    SpielerPassiv=Spieler1;}
	
	 // if (checkSpielBeendet(SpielerAktiv, SpielerPassiv)==false)  ueberprueft ob das Spiel nicht beendet ist!
	 // und dann kann der Aktive Spieler weitermachen
	
	SpielerAktiv.getPhase();
	// je nach aktuelle Phase, die entsprechende Methode aufrufen
	
	//  else { Spiel ist beendet, der aktive Spieler hat verloren!  }
	
	
	

	
	
	}
	
	
	
	