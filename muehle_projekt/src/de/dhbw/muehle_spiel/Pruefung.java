package de.dhbw.muehle_spiel;

import de.dhbw.muehle_api.Position;

public class Pruefung {

	// Überprüft, ob ein Spielzug regelkonform ist
	public boolean checkZug (Bewegung bewegung, Spieler SpielerAktiv, Spieler SpielerPassiv ){
	
	Spielstein[] SteineSpieler1 = SpielerAktiv.Steine;
	Spielstein[] SteineSpieler2 = SpielerPassiv.Steine;
	
	
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
		
		
	int AnzahlSteine = SpielerAktiv.getAnzahlSteine();
	
	// Wenn Anzahl Steine > 3 darf der Spieler ziehen, Wenn =3 darf er springen
	if (AnzahlSteine > 3)
	{	
		// Überprüfung, ob sich der PositionsIndex um 1 verändert hat
		// Zug ist gültig, wenn aenderung = 1
		if (vonEbene- nachEbene == 1 || vonEbene- nachEbene == -1)
			aenderung ++;
		if (vonEbene- nachEbene == 2 || vonEbene- nachEbene == -2)
			return false;
		if (vonX - nachX == 1 || vonX - nachX == -1) 
			aenderung++;
		if (vonX - nachX == 2 || vonX - nachX == -2) 
			return false;
		if (vonY - nachY == 1 || vonY - nachY == -1) 
			aenderung++;
		if (vonY - nachY == 2 || vonY - nachY == -2) 
			return false;
		if (aenderung == 1)
			korrekt = true;
		
		//Überprüfung, ob sich die Ebene ungültiger Weise verändert hat
		if (vonX == 1 && vonY ==3 && vonEbene != nachEbene)
			return false;
		if (vonX == 3 && vonY ==3 && vonEbene != nachEbene)
			return false;
		if (vonX == 3 && vonY ==1 && vonEbene != nachEbene)
			return false;
		if (vonX == 1 && vonY ==1 && vonEbene != nachEbene)
			return false;
		
	}
		
		//Überprüfung, ob die Nach-Position bereits belegt ist
		for (int i = 0; i<9; i++)
		{
			if(SteineSpieler1[i].getAktuellePosition() != bewegung.getNach() ){
			korrekt = true;
			}
			
			else{
			return false;
			}
			
			if(SteineSpieler2[i].getAktuellePosition() != bewegung.getNach() ){
			korrekt = true;
			}
			else{
			return false;
			}
		
		}
			
			return korrekt;
		
	}
	
	//Überprüft, ob ein Stein auf dem Spielfeld weggenommen werden darf
	public boolean checkInMuehle(int IndexStein, Spielstein[] Steine){
		
		boolean korrekt = false;
		
		int[][] Positionen = new int[8][2];
		
		// Ablegen der Positionen aller Steine eines Spielers in einem Array
		for (int i = 0; i < 9; i++){
			Positionen[i][0]= Steine[i].getPosition().getEbene().getValue();
			Positionen[i][1]= Steine[i].getPosition().getX().getValue();
			Positionen[i][2]= Steine[i].getPosition().getY().getValue();		
			}
		return korrekt;
	}
}
