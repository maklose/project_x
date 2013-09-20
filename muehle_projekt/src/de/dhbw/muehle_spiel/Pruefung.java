package de.dhbw.muehle_spiel;

import de.dhbw.muehle_api.Position;


public class Pruefung {

	public boolean checkSpielzug (Bewegung bewegung){
	
	boolean korrekt = false;
	int aenderung = 0;
	int vonEbene, vonX, vonY, nachEbene, nachX, nachY;
	
	
	// Ablegen der Positionsindexe in einer int-Variablen
	vonEbene = bewegung.getVon().getEbene().getValue();
	vonX = bewegung.getVon().getX().getValue();
	vonY = bewegung.getVon().getX().getValue();
	nachEbene= bewegung.getNach().getEbene().getValue();
	nachX = bewegung.getNach().getX().getValue();
	nachY = bewegung.getNach().getY().getValue();
	
	
	// Überprüfung, ob sich der PositionsIndex um 1 oder 2 verändert hat
	if (vonEbene- nachEbene == 1 || vonEbene- nachEbene == -1)
		aenderung ++;
	if (vonEbene- nachEbene == 2 || vonEbene- nachEbene == -2)
		korrekt = false;
	if (vonX - nachX == 1 || vonX - nachX == -1) 
		aenderung++;
	if (vonX - nachX == 2 || vonX - nachX == -2) 
		korrekt = false;
	if (vonY - nachY == 1 || vonY - nachY == -1) 
		aenderung++;
	if (vonY - nachY == 2 || vonY - nachY == -2) 
		korrekt = false;
	
	//Überprüfung, ob sich die Ebene ungültiger Weise verändert hat
	if (vonX == 1 && vonY ==3 && vonEbene != nachEbene)
		korrekt = false;
	if (vonX == 3 && vonY ==3 && vonEbene != nachEbene)
		korrekt = false;
	if (vonX == 3 && vonY ==1 && vonEbene != nachEbene)
		korrekt = false;
	if (vonX == 1 && vonY ==1 && vonEbene != nachEbene)
		korrekt = false;
	
	//Wenn aenderung 1 ist, dann ist der Zug gültig
	if (aenderung == 1)
		korrekt = true;
			
			return korrekt;
		
	}
	
	
}
