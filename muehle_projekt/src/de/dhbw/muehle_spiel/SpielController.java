package de.dhbw.muehle_spiel;

import de.dhbw.muehle_api.ESpielsteinFarbe;
import de.dhbw.muehle_api.Position;
import de.dhbw.muehle_spiel.Spieler;
import de.dhbw.muehle_spiel.EPhase;

public class SpielController {
	
	Spieler aktuellerSpieler;
	
	
	//Konstruktor
	public SpielController(Spieler s1, Spieler s2) 
	{
		Spieler Spieler1=s1;
		Spieler Spieler2=s2; }
	
	
	
	
	
	public boolean Spiel_beendet(Spieler aktuellerSpieler){
		if (aktuellerSpieler.getAnzahlSteine()<3) {return true; } //or if alle Steine gesperrt
		
		else {return false;}
	}
	
	
	
	
	
	
	public void Ablauf(){
		
		
		
	for (int i=1;;i++){
	
	if ((i%2)==1) {aktuellerSpieler=Spieler1; }
	else {aktuellerSpieler=Spieler2; }
		
		  if (Spiel_beendet(aktuellerSpieler)==false) {
	
		 
		EPhase aktuellePhase= aktuellerSpieler.getPhase();
		
		/* 
		 * if (aktuellePhase==EPhase.Setzen){ aktuellerSpieler.setzeSpielstein(lPos);}
		*else if(aktuellePhase==EPhase.Schieben){}
		*else if (aktuellePhase==EPhase.Springen){}
		**/
		
		//TO DO: Pr�e ob eine M�hle entstanden ist, wenn ja dann nimm einen Stein weg
	 
		  }
		  
		  else if(Spiel_beendet(aktuellerSpieler)==true){
			  //Spiel muss beendet werden, der akuelle Spiler ist der Verlierer!
			
		  }
		
	}	
	}
		
			
			
}
			
