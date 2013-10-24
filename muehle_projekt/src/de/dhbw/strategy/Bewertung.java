package de.dhbw.strategy;

import java.util.ArrayList;
import java.util.List;

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
//	Spieler aktiverSpielerb;
//	Spieler passiverSpielerb;
	
	public Bewertung()
	{
//		aktiverSpielerb = new Spieler(ESpielsteinFarbe.WEISS, "BewertungsSpiler1");
//		passiverSpielerb = new Spieler(ESpielsteinFarbe.SCHWARZ, "BewertungsSpieler2");
		
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
	
	//aufteilen des Arrays aSteine in Arrays für jeweils einen Spieler
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

	
	
}
