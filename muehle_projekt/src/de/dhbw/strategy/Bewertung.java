package de.dhbw.strategy;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.muehle_api.EPositionIndex;
import de.dhbw.muehle_api.ESpielsteinFarbe;
import de.dhbw.muehle_api.ISpielstein;
import de.dhbw.muehle_api.Position;
import de.dhbw.muehle_spiel.Bewegung;
import de.dhbw.muehle_spiel.Pruefung;
import de.dhbw.muehle_spiel.Spieler;
import de.dhbw.muehle_spiel.Spielstein;

public class Bewertung 
{
	//Array mit den aktuellen Steinen auf dem Feld
	List<ISpielstein> lSteine;
	Position von, nach;
	Pruefung pruef = new Pruefung();
	Spieler aktuellerSpieler;
	Spieler passiverSpieler;
	ISpielstein bewegterStein;
	
	//Variablen für die Bewertung 
	int bSpielBeendet = 10;
	
	//Phase Setzen
	double b1Muehle = 0.2;
	double b1Doppelangriff = 0;
	double b1StrategischePunkte = 0.3;
	double b1MuehleBewachen = 0;
	
	//Phase Bewegen
	double b2Muehle = 0;
	double b2Doppelangriff = 0;
	double b2StrategischePunkte = 0;
	double b2MuehleBewachen = 0;

	
	public double bewerteZug(List<ISpielstein> p_SpielFeld, Bewegung lBewegung, int index, int anzahlZuege)
	{
		double Score = 0;
		von = lBewegung.getVon();
		nach = lBewegung.getNach();
		lSteine = p_SpielFeld;
		bewegterStein = p_SpielFeld.get(index);
		aktuellerSpieler = new Spieler(bewegterStein.getFarbe(), "aktuellerSpielerB");
		
		//passiven Spieler festlegen und Arrays der Spieler füllen
		if(bewegterStein.getFarbe() == ESpielsteinFarbe.WEISS)
		{
			passiverSpieler = new Spieler(ESpielsteinFarbe.SCHWARZ, "passiverSpielerB");
			this.SteineAufteilen(p_SpielFeld, ESpielsteinFarbe.WEISS);
		}
		else
		{
			passiverSpieler = new Spieler(ESpielsteinFarbe.WEISS, "passiverSpielerB");
			this.SteineAufteilen(p_SpielFeld, ESpielsteinFarbe.SCHWARZ);
		}
			
		
		//Beginn der Bewertung
		
		//Wenn wir in der setzen Phase sind
		if(anzahlZuege <= 9)
		{
			//Wenn man eine Mühle erstellen kann
			if(this.checkInMuehle(lBewegung, aktuellerSpieler.Steine))
			{
				Score += b1Muehle;
				
				//wenn man duch die Mühle das Spiel gewonnen hat
				if(passiverSpieler.getAnzahlSteine() == 3 && anzahlZuege > 9 || 
						(anzahlZuege == 9 && aktuellerSpieler.getSpielerfarbe().equals(ESpielsteinFarbe.SCHWARZ) && passiverSpieler.getAnzahlZuege() == 3))
					Score += bSpielBeendet;
			}
			
			//Wenn man duch das Zustellen des Gegners gewinnen kann
			if(this.checkBewegungsunfaehig(passiverSpieler, aktuellerSpieler))
				Score += bSpielBeendet;
			
			if(this.isStrategischerPunkt(lBewegung))
				Score += b1StrategischePunkte;
		}
		else	//Wenn wir in der Bewegen Phase sind
		{
			//Wenn man eine Mühle erstellen kann
			if(this.checkInMuehle(lBewegung, aktuellerSpieler.Steine))
			{
				Score += b1Muehle;
				if(passiverSpieler.getAnzahlSteine() == 3 && anzahlZuege > 9 || 
						(anzahlZuege == 10 && aktuellerSpieler.getSpielerfarbe().equals(ESpielsteinFarbe.WEISS) && passiverSpieler.getAnzahlZuege() == 3))
					Score += bSpielBeendet;
			}
			
			
			if(this.checkBewegungsunfaehig(passiverSpieler, aktuellerSpieler))
				Score += bSpielBeendet;
			
		}
		
			
		
					
		
		return Score;
	}


	//aufteilen des Arrays aSteine in Arrays für jeweils einen Spieler
	public void SteineAufteilen(List<ISpielstein> p_SpielFeld, ESpielsteinFarbe farbe)
	{
		for(ISpielstein s: p_SpielFeld){
			if(s.getFarbe() == farbe)
			{
				aktuellerSpieler.setzeSpielstein(s.getPosition(), 0, 0);
			}
			else
			{
				passiverSpieler.setzeSpielstein(s.getPosition(),0,0);
			}
		}
	
	}
	
	//Überprüft, ob sich ein Stein auf dem Spielfeld in einer Mühle befindet
	// Gibt true zurück, wenn sich der abgefragte Stein(int IndexStein) in einer Mühle befindet
	public boolean checkInMuehle(Bewegung neueBewegung, ISpielstein[] Steine){ 

		Position nach = neueBewegung.getNach();
		boolean inMuehle = false;
		int aenderungEbene, aenderungX, aenderungY;
		int zaehlerEbene = 0;
		int zaehlerX = 0; 
		int zaehlerY = 0;
		
		int[][] Positionen = new int[9][3];
		
		// Ablegen der Positionen aller Steine eines Spielers in einem Array
		for (int i = 0; i < 9; i++){			
			if(Steine[i] != null){
				Positionen[i][0]= Steine[i].getPosition().getEbene().getValue();
				Positionen[i][1]= Steine[i].getPosition().getX().getValue();
				Positionen[i][2]= Steine[i].getPosition().getY().getValue();
				}
		}
		
		for (int j =0; j < 9; j++){
			
			aenderungEbene = 0;
			aenderungX = 0;
			aenderungY = 0;
			
			//Vergleicht die Position des betrachteten Steins(IndexStein) mit der des Steins an j-ter Stelle
			aenderungEbene = Math.abs(Positionen[j][0] - nach.getEbene().getValue());
			aenderungX = Math.abs(Positionen[j][1] - nach.getX().getValue());
			aenderungY = Math.abs(Positionen[j][2] - nach.getY().getValue());
					
			//Heraufsetzen des Zählers um 1, wenn sich nur die Ebene verändert hat
			if ((aenderungEbene == 1 && aenderungX == 0 && aenderungY == 0)||
				(aenderungEbene == 2 && aenderungX == 0 && aenderungY == 0))
			{
				zaehlerEbene ++;
				
				
				// Ausschließen der Eckpositionen
				//Sonderfälle: 1,1,3 - 2,1,3 - 3,1,3 ; 1,3,3 - 2,3,3 - 3,3,3 ; 1,3,1 - 2,3,1 - 3,3,1 ; 1,1,1 - 2,1,1 - 3,1,1
				if	((Positionen[j][0] != nach.getEbene().getValue())&&
								((Positionen[j][1] + Positionen[j][2] == 2)||
								(Positionen[j][1] + Positionen[j][2] == 4)||
								(Positionen[j][1] + Positionen[j][2] == 6)))
				{
					zaehlerEbene--;
				}
											
			}	
			
			//Heraufsetzen des Zählers um 1, wenn sich nur die X-Koordinate verändert hat
			if ((aenderungEbene == 0 && aenderungX == 1 && aenderungY == 0)||
				(aenderungEbene == 0 && aenderungX == 2 && aenderungY == 0))
				{
					zaehlerX ++;
				}
			
			//Heraufsetzen des Zählers um 1, wenn sich nur die Y-Koordinate verändert hat
			if ((aenderungEbene == 0 && aenderungX == 0 && aenderungY == 1)||
				(aenderungEbene == 0 && aenderungX == 0 && aenderungY == 2))
					{
						zaehlerY ++;
					}
			
		}

		if(zaehlerEbene == 2 || zaehlerX == 2 || zaehlerY == 2){
				inMuehle = true;
			}
					
		return inMuehle;
	}
	
	
	// Überprüft, ob das Spiel beendet ist, weil SpielerAktiv keine Möglichkeit mehr hat zu ziehen, oder weniger als 3 Steine hat
	// Gibt true zurück, wenn das Spiel beendet ist und false wenn das Spiel noch nicht beendet ist
	public boolean checkBewegungsunfaehig(Spieler SpielerAktiv, Spieler SpielerPassiv)
	{
		
		boolean ZugKorrekt = false;
		
		EPositionIndex ebene = null;
		EPositionIndex x = null;
		EPositionIndex y = null;
		
		
		// Wenn Anzahl der Steine > 3 wird überprüft, ob der Aktive Spieler noch die Möglichkeit hat zu ziehen
		if(SpielerAktiv.getAnzahlSteine() > 3 || (SpielerAktiv.getAnzahlSteine() == 3 && SpielerAktiv.getAnzahlZuege() <= 9))
		{
			for (int i = 0; i <= 8 ; i++)		//for (int i = 0; i < SpielerAktiv.Steine.length ; i++)
			{			
				for(int a = 1 ; a <= 3; a++)
				{
					if(a == 1){
						ebene = ebene.Eins;
						}
					if(a == 2){
						ebene = ebene.Zwei;
						}
					if(a == 3){
						ebene = ebene.Drei;
					}
					
					for(int b = 1; b <= 3; b++)
					{
						if(b == 1){
							x = x.Eins;
							}
						if(b == 2){
							x = x.Zwei;
							}
						if(b == 3){
							x = x.Drei;
						}
						for(int c = 1; c <= 3; c++)
						{
							if(c == 1){
								y = y.Eins;
								}
							if(c == 2){
								y = y.Zwei;
								}
							if(c == 3){
								y = y.Drei;
							}
							if((ebene == ebene.Eins && x == x.Zwei && y == y.Zwei) 
									|| (ebene == ebene.Zwei && x == x.Zwei && y == y.Zwei)
									|| (ebene == ebene.Drei && x == x.Zwei && y == y.Zwei))
								continue;
							
							if(SpielerAktiv.Steine[i] != null)	//TEST MARVIN
							ZugKorrekt = checkZug(new Bewegung(SpielerAktiv.Steine[i].getPosition(), new Position(ebene, x, y)), 
									SpielerAktiv, SpielerPassiv);
							
							if(ZugKorrekt == true)
								return false;
								
						}
					}
				}
			}	 	
		}
		else if(SpielerAktiv.getAnzahlSteine() == 3)
		{
			return false;
		}
		else if(SpielerAktiv.getAnzahlSteine() <= 3 && SpielerAktiv.getAnzahlZuege() <= 4)
			return false;

		
		return true;
	}

	
	
	
	public boolean checkZug (Bewegung bewegung, Spieler SpielerAktiv, Spieler SpielerPassiv )
	{
		
		
		boolean korrekt = false;			
		int aenderung = 0;
		int vonEbene, vonX, vonY, nachEbene, nachX, nachY;
		
		// Ablegen der Positionsindexe in einer int-Variablen
		vonEbene = bewegung.getVon().getEbene().getValue();
		vonX = bewegung.getVon().getX().getValue();
		vonY = bewegung.getVon().getY().getValue();
		nachEbene= bewegung.getNach().getEbene().getValue();
		nachX = bewegung.getNach().getX().getValue();
		nachY = bewegung.getNach().getY().getValue();
		
		// Wenn Anzahl Steine > 3 darf der Spieler ziehen, Wenn =3 darf er springen
		if (SpielerAktiv.getAnzahlSteine() > 3)
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
		else
			korrekt = true;
			
		
		//Überprüfung, ob die Nach-Position bereits belegt ist
			for (int i = 0; i<9; i++)
			{
				if(SpielerAktiv.Steine[i] != null){
					if(SpielerAktiv.Steine[i].getPosition().equals(bewegung.getNach()) == true ){
						return false;
					}
				
			}
				
				if(SpielerPassiv.Steine[i] != null){
					if(SpielerPassiv.Steine[i].getPosition().equals(bewegung.getNach()) == true){
						return false;
					}
					
				}
			
			}
				
				return korrekt;
			
		}
	
	public int getAnzahlSteine(ISpielstein[] Steine)
	{
		int counter = 0;
		for (int i = 0; i < Steine.length; i++)
		{
			if(Steine[i] != null)
				counter++;
		}
		return counter;
		
	}
	
	//Strategische Punkte sind 223, 221, 212, 232
	public boolean isStrategischerPunkt(Bewegung neueBewegung)
	{
		Position nach = neueBewegung.getNach();
		
		if(nach.getEbene().equals(EPositionIndex.Zwei) && (					//Ebene ist immer 2
				(nach.getX().equals(EPositionIndex.Zwei) &&					//Wenn x = 2 
						(nach.getY().equals(EPositionIndex.Drei) || nach.getY().equals(EPositionIndex.Eins)))
				||(nach.getY().equals(EPositionIndex.Zwei) &&				//Wenn y = 2 
						(nach.getX().equals(EPositionIndex.Drei) || nach.getX().equals(EPositionIndex.Eins)))))
			return true;
		else
			return false;
	}
	
	
}
