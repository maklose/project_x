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
	ISpielstein[] aSteine, aSteineWeiss, aSteineSchwarz;
	Position von, nach;
	Pruefung pruef = new Pruefung();
	Spieler weisserSpielerb;
	Spieler schwarzerSpielerb;
	
	public Bewertung()
	{
		weisserSpielerb = new Spieler(ESpielsteinFarbe.WEISS, "BewertungsSpiler1");
		schwarzerSpielerb = new Spieler(ESpielsteinFarbe.SCHWARZ, "BewertungsSpieler2");
		
	}
	
	public double bewerteZug(List<ISpielstein> p_SpielFeld, Bewegung lbewegung, Spieler laktuellerSpieler)
	{
		double Score = 0;
		von = lbewegung.getVon();
		nach = lbewegung.getNach();
		lSteine = p_SpielFeld;
		Spieler aktuellerSpieler = laktuellerSpieler;
		aSteine = this.ListeUmrechnen(lSteine);
		this.SteineAufteilen(aSteine);
		
		if(aktuellerSpieler.getSpielerfarbe().equals(ESpielsteinFarbe.WEISS) && this.checkInMuehle(lbewegung, aSteineWeiss))
			Score += 0.8;
		if(aktuellerSpieler.getSpielerfarbe().equals(ESpielsteinFarbe.SCHWARZ) && this.checkInMuehle(lbewegung, aSteineSchwarz))
			Score += 0.8;
		if(aktuellerSpieler.getSpielerfarbe().equals(ESpielsteinFarbe.WEISS) && this.checkSpielBeendet(weisserSpielerb, schwarzerSpielerb))
			Score += 1;
		if(aktuellerSpieler.getSpielerfarbe().equals(ESpielsteinFarbe.SCHWARZ) && this.checkSpielBeendet(schwarzerSpielerb, weisserSpielerb))
			Score += 1;

					
		
		return Score;
	}

	
	
	public ISpielstein[] ListeUmrechnen(List<ISpielstein> p_SpielFeld)
	{
		
		if(!p_SpielFeld.isEmpty())
		{
			Spielstein[] aSteine = new Spielstein[p_SpielFeld.size()];
			for(int i = 0; i <= p_SpielFeld.size()-1; i++)
			{
				aSteine[i] = (Spielstein)p_SpielFeld.get(i);
			}
				
			return aSteine;
		}
		else
			return null;
		
	}
	
	//aufteilen des Arrays aSteine in Arrays f�r jeweils einen Spieler
	public void SteineAufteilen(ISpielstein[] Steine)
	{
		aSteineWeiss = new ISpielstein[9];
		aSteineSchwarz = new ISpielstein[9];

		int a = 0, b = 0;
		for(int i = 0; i <= Steine.length-1; i++)
		{
			if(Steine[i].getFarbe() == ESpielsteinFarbe.WEISS)
			{
				aSteineWeiss[a]	= Steine[i];
				a++;
			}
			else
			{
				aSteineSchwarz[b] = Steine[i];
				b++;
			}
		}
	}
	
	//�berpr�ft, ob sich ein Stein auf dem Spielfeld in einer M�hle befindet
	// Gibt true zur�ck, wenn sich der abgefragte Stein(int IndexStein) in einer M�hle befindet
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
					
			//Heraufsetzen des Z�hlers um 1, wenn sich nur die Ebene ver�ndert hat
			if ((aenderungEbene == 1 && aenderungX == 0 && aenderungY == 0)||
				(aenderungEbene == 2 && aenderungX == 0 && aenderungY == 0))
			{
				zaehlerEbene ++;
				
				
				// Ausschlie�en der Eckpositionen
				//Sonderf�lle: 1,1,3 - 2,1,3 - 3,1,3 ; 1,3,3 - 2,3,3 - 3,3,3 ; 1,3,1 - 2,3,1 - 3,3,1 ; 1,1,1 - 2,1,1 - 3,1,1
				if	((Positionen[j][0] != nach.getEbene().getValue())&&
								((Positionen[j][1] + Positionen[j][2] == 2)||
								(Positionen[j][1] + Positionen[j][2] == 4)||
								(Positionen[j][1] + Positionen[j][2] == 6)))
				{
					zaehlerEbene--;
				}
											
			}	
			
			//Heraufsetzen des Z�hlers um 1, wenn sich nur die X-Koordinate ver�ndert hat
			if ((aenderungEbene == 0 && aenderungX == 1 && aenderungY == 0)||
				(aenderungEbene == 0 && aenderungX == 2 && aenderungY == 0))
				{
					zaehlerX ++;
				}
			
			//Heraufsetzen des Z�hlers um 1, wenn sich nur die Y-Koordinate ver�ndert hat
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
	
	
	public boolean checkSpielBeendet(Spieler SpielerAktiv, Spieler SpielerPassiv)
	{
		
		boolean ZugKorrekt = false;
		
		EPositionIndex ebene = null;
		EPositionIndex x = null;
		EPositionIndex y = null;
		
		
		// Wenn Anzahl der Steine > 3 wird �berpr�ft, ob der Aktive Spieler noch die M�glichkeit hat zu ziehen
		if(SpielerAktiv.getAnzahlSteine() > 3)
		{
			for (int i = 0; i < SpielerAktiv.Steine.length ; i++)
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
							
							ZugKorrekt = this.checkZug(new Bewegung(SpielerAktiv.Steine[i].getPosition(), new Position(ebene, x, y)), SpielerAktiv, SpielerPassiv); 
							
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
		else if(SpielerAktiv.getAnzahlSteine() < 3 && SpielerAktiv.getAnzahlZuege() < 9)
			return false;
		else
		{
			return true;
		}
		return false;
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
			// �berpr�fung, ob sich der PositionsIndex um 1 ver�ndert hat
			// Zug ist g�ltig, wenn aenderung = 1
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
			
			//�berpr�fung, ob sich die Ebene ung�ltiger Weise ver�ndert hat
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
			
		
		//�berpr�fung, ob die Nach-Position bereits belegt ist
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
	
	
}
