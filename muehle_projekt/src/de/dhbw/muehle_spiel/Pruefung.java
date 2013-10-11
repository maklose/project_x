package de.dhbw.muehle_spiel;

import de.dhbw.muehle_api.EPositionIndex;
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
	
	//Überprüft, ob sich ein Stein auf dem Spielfeld in einer Mühle befindet
	public boolean checkInMuehle(int IndexStein, Spielstein[] Steine){
		
		boolean inMuehle = false;
		int aenderung;
		int zaehler = 0;
		
		int[][] Positionen = new int[9][3];
		
		// Ablegen der Positionen aller Steine eines Spielers in einem Array
		for (int i = 0; i < 9; i++){
			Positionen[i][0]= Steine[i].getPosition().getEbene().getValue();
			Positionen[i][1]= Steine[i].getPosition().getX().getValue();
			Positionen[i][2]= Steine[i].getPosition().getY().getValue();		
			}
		
		for (int i =0; i <9; i++){
			
			aenderung = 0;
			
			//Verändert sich ein Positionsindex eines Steins um 1 im Vergleich mit dem betrachteten Stein(IndexStein) wird aenderung um 1 erhöht
			//Ist aenderung == 1 wird zaehler um 1 erhöht
			//Ist zaehler == 2 befindet sich der betrachtete Stein in einer Mühle
			
//			if(Positionen[i][0] - Positionen[IndexStein][0] == 1 || Positionen[i][0] - Positionen[IndexStein][0] == -1 )
//				aenderung ++;
//			if(Positionen[i][1] - Positionen[IndexStein][1] == 1 || Positionen[i][1] - Positionen[IndexStein][1] == -1)
//				aenderung++;
//			if(Positionen[i][2] - Positionen[IndexStein][2] == 1 || Positionen[i][2] - Positionen[IndexStein][2] == -1)
//				aenderung++;
			
			aenderung += Math.abs(Positionen[i][0] - Positionen[IndexStein][0]);
			aenderung += Math.abs(Positionen[i][1] - Positionen[IndexStein][1]);
			aenderung += Math.abs(Positionen[i][2] - Positionen[IndexStein][2]);				
			
			if(aenderung == 1){
			zaehler ++;
			}
			
		}	
		
		// Ausschliesen der 4 Sonderfälle, bei denen sich der Index um 1 verändert, der Stein jedoch nicht in einer Mühle steht
		//Sonderfälle: 1,1,3 - 2,1,3 - 3,1,3 ; 1,3,3 - 2,3,3 - 3,3,3 ; 1,3,1 - 2,3,1 - 3,3,1 ; 1,1,1 - 2,1,1 - 3,1,1		
		
		Position[] pos = new Position[12];
		pos[0] = new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Drei);
		pos[1] = new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Drei);
		pos[2] = new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Drei);
		pos[3] = new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Drei);
		pos[4] = new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Drei);
		pos[5] = new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Drei);
		pos[6] = new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Eins);
		pos[7] = new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Eins);
		pos[8] = new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Eins);
		pos[9] = new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Eins);
		pos[10] = new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Eins);
		pos[11] = new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Eins);
		
		int anzahl1 = 0;
		int anzahl2 = 0;
		int anzahl3 = 0;
		int anzahl4 = 0;
		
		for(int j = 0; j <9 ; j++){
			if(Steine[j].equals(pos[0]))
				anzahl1++;
			if(Steine[j].equals(pos[1]))
				anzahl1++;
			if(Steine[j].equals(pos[2]))
				anzahl1++;
			if(Steine[j].equals(pos[3]))
				anzahl2++;
			if(Steine[j].equals(pos[4]))
				anzahl2++;
			if(Steine[j].equals(pos[5]))
				anzahl2++;
			if(Steine[j].equals(pos[6]))
				anzahl3++;
			if(Steine[j].equals(pos[7]))
				anzahl3++;
			if(Steine[j].equals(pos[8]))
				anzahl3++;
			if(Steine[j].equals(pos[9]))
				anzahl3++;
			if(Steine[j].equals(pos[10]))
				anzahl4++;
			if(Steine[j].equals(pos[11]))
				anzahl4++;
		}
			
		//Wenn anzahl1/2/3/4 == 3 ist Fall vorhanden und zaehler wird um 2 vermindert
		if(anzahl1 == 3)
			zaehler = zaehler - 2;
		if(anzahl2 == 3)
			zaehler = zaehler - 2;
		if(anzahl3 == 3)
			zaehler = zaehler - 2;
		if(anzahl4 == 3)
			zaehler = zaehler - 2;
		
		if(zaehler == 2 || zaehler == 4){
				inMuehle = true;
			}
			
			// Ausschliesen der 4 Sonderfälle, bei denen sich der Index um 1 verändert, der Stein jedoch nicht in einer Mühle steht
			//Sonderfälle: 1,1,3 - 2,1,3 - 3,1,3 ; 1,3,3 - 2,3,3 - 3,3,3 ; 1,3,1 - 2,3,1 - 3,3,1 ; 1,1,1 - 2,1,1 - 3,1,1		
			
			
		
		return inMuehle;
	}
}
