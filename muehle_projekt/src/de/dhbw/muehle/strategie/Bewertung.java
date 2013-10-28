package de.dhbw.muehle.strategie;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.muehle.*;
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
	ISpielstein Spielfeld[][][];
	
	
	//Variablen für die Bewertung 
	int bSpielBeendet = 10;
	
	//Phase Setzen
	double b1Muehle = 0.2;
	double b1Doppelangriff = 0.35;
	double b1DoppelangriffIn2 = 0.25;
	double b1StrategischePunkte = 0.01;
	double b1MuehleBewachen = 0;
	
	//Phase Bewegen
	double b2Muehle = 0;
	double b2Doppelangriff = 0;
	double b2DoppelangriffIn2 = 0;
	double b2StrategischePunkte = 0;
	double b2MuehleBewachen = 0;

	
	
	public double bewerteZug(List<ISpielstein> p_SpielFeld, Bewegung lBewegung, int anzahlZuege)
	{
		
		Spielfeld = new ISpielstein[3][3][3];
		this.ListeZuArray(p_SpielFeld);
		double Score = 0;
		
		von = lBewegung.getVon();
		nach = lBewegung.getNach();
		lSteine = p_SpielFeld;
//		for(ISpielstein Stein : p_SpielFeld)
//		{
//			if(Stein.getPosition().equals(lBewegung.getNach()))
//					bewegterStein = Stein;
//		}
		ESpielsteinFarbe farbe;
		if(p_SpielFeld.get(p_SpielFeld.size()-1).getFarbe().equals(ESpielsteinFarbe.WEISS))
			farbe = ESpielsteinFarbe.SCHWARZ;
		else
			farbe = ESpielsteinFarbe.WEISS;
		
		Spielstein bewegterStein = new Spielstein(farbe, lBewegung.getNach(), 0, 0, 0);
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
		if(anzahlZuege <= 18)
		{
			//Wenn man eine Mühle erstellen kann
			if(this.checkInMuehle(lBewegung, aktuellerSpieler.Steine))
			{
				Score = Score + b1Muehle;
				
				//wenn man duch die Mühle das Spiel gewonnen hat
				if(passiverSpieler.getAnzahlSteine() == 3 && anzahlZuege > 18 || 
						(anzahlZuege == 18 && aktuellerSpieler.getSpielerfarbe().equals(ESpielsteinFarbe.SCHWARZ) && passiverSpieler.getAnzahlZuege() == 3))
					Score = Score + bSpielBeendet;
			}
			
			
			//Wenn man duch das Zustellen des Gegners gewinnen kann
			if(this.checkBewegungsunfaehig(passiverSpieler, aktuellerSpieler))
				Score = Score + bSpielBeendet;
			
			if(this.isStrategischerPunkt(lBewegung))
				Score = Score + b1StrategischePunkte;
			
			
			Score = Score + this.bewerteDoppelangriff();
		}
		else	//Wenn wir in der Bewegen Phase sind
		{
			//Wenn man eine Mühle erstellen kann
			if(this.checkInMuehle(lBewegung, aktuellerSpieler.Steine))
			{
				Score = b1Muehle;
				if(passiverSpieler.getAnzahlSteine() == 3 && anzahlZuege > 18 || 
						(anzahlZuege == 18 && aktuellerSpieler.getSpielerfarbe().equals(ESpielsteinFarbe.WEISS) && passiverSpieler.getAnzahlZuege() == 3))
					Score = bSpielBeendet;
			}
			
			
			if(this.checkBewegungsunfaehig(passiverSpieler, aktuellerSpieler))
				Score = bSpielBeendet;
			
		}
		
			
		
					
		
		return Score;
	}
	
	public void ListeZuArray(List<ISpielstein> p_SpielFeld)
	{
		
			for (ISpielstein Stein : p_SpielFeld)		
			{		
				if(Stein != null)
				{
					if(Stein.getPosition()!= null)
					{
						int ebene = this.posIndexUmrechnen(Stein.getPosition().getEbene());
						int x = this.posIndexUmrechnen(Stein.getPosition().getX());
						int y = this.posIndexUmrechnen(Stein.getPosition().getY());
						Spielfeld[ebene][x][y] = Stein;
					}
				}
			}
		
			
			
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
	
	
	//Strategische Punkte sind 223, 221, 212, 232
	public boolean isStrategischerPunkt(Bewegung neueBewegung)
	{
		Position nach = neueBewegung.getNach();
		
		if(nach.getEbene().equals(EPositionIndex.Zwei) &&	(
				(nach.getX().equals(EPositionIndex.Eins) && nach.getY().equals(EPositionIndex.Zwei)) ||
				(nach.getX().equals(EPositionIndex.Zwei) && nach.getY().equals(EPositionIndex.Eins)) ||
				(nach.getX().equals(EPositionIndex.Zwei) && nach.getY().equals(EPositionIndex.Drei)) ||
				(nach.getX().equals(EPositionIndex.Drei) && nach.getY().equals(EPositionIndex.Zwei))))
			return true;
		else
			return false;
	}
	
	public double bewerteDoppelangriff()
	{
		int ebene = this.posIndexUmrechnen(nach.getEbene());
		int x = this.posIndexUmrechnen(nach.getX());
		int y = this.posIndexUmrechnen(nach.getY());
		
		/*
		 * möglichkeit 1 ---------------- diagonaler doppelangriff -----------------------------
		 */
		
		if((x == 0 && y == 0) || (x == 2 && y == 0) || (x == 0 && y == 2) || (x == 2 && y == 2))
		{
			//nacheinander für meine Steine auf dem Feld überprüfen
			for(int i = 0; i <= 2; i++)
			{
				for(int j = 0; j <= 2; j++)
				{
					for(int k = 0; k <= 2; k++)
					{
						//bewegter Stein darf nicht einbezogen werden
						if(i == this.posIndexUmrechnen(nach.getEbene()) && j == this.posIndexUmrechnen(nach.getX()) && k == this.posIndexUmrechnen(nach.getY()))
							continue;
						
						//gucken ob man eine vorbereitung erstellen kann, also eine der 4 arten möglich ist
						
						if(Spielfeld[ebene][Math.abs(x-2)][Math.abs(y-2)] != null)
						{
							if(Spielfeld[ebene][Math.abs(x-2)][Math.abs(y-2)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) && (	//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									(Spielfeld[ebene][Math.abs(x-1)][y] == null && Spielfeld[ebene][Math.abs(x-2)][Math.abs(y-1)] == null)	//und wenn alle anderen felder die benötigt werden freu sind
									|| (Spielfeld[ebene][x][Math.abs(y-1)] == null && Spielfeld[ebene][Math.abs(x-1)][Math.abs(y-2)] == null)
									&& (Spielfeld[ebene][x][Math.abs(y-2)] == null|| Spielfeld[ebene][Math.abs(x-2)][y] == null)))				
							{	//Wenn ich schon vollenden kann
								if(Spielfeld[ebene][x][Math.abs(y-2)] != null && Spielfeld[ebene][Math.abs(x-2)][y] != null)	//wenn das gegenüberliegende feld nicht null ist	
								{
									
									if(Spielfeld[ebene][Math.abs(x-2)][y].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
											Spielfeld[ebene][x][Math.abs(y-2)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&
											Spielfeld[ebene][x][Math.abs(y-1)] == null && Spielfeld[ebene][Math.abs(x-1)][y] == null)			
									{
										return b1Doppelangriff;
									}
								}
								else
									return b1DoppelangriffIn2;
							}
						}
						
					}
				}
			}
				
			for(int a = 0; a <= 2; a++)
			{
				for(int b = 0; b <= 2; b++)
				{
					for(int c = 0; c <= 2; c++)
					{
						//beweter Stein darf nicht einbezogen werden
						if(a == this.posIndexUmrechnen(nach.getEbene()) && b == this.posIndexUmrechnen(nach.getX()) && c == this.posIndexUmrechnen(nach.getY()))
							continue;
						
						//gucken ob man eine vorbereitung erstellen kann, also eine der 4 arten möglich ist
						//1. Möglichkeit: Steine stehen sich in einer Ebene diagonal gegenüber
						if(Spielfeld[ebene][x][Math.abs(y-2)] != null && Spielfeld[ebene][Math.abs(x-2)][y] != null)	//wenn das gegenüberliegende feld nicht null ist	
						{
							
							if(Spielfeld[ebene][Math.abs(x-2)][y].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][x][Math.abs(y-2)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&
									Spielfeld[ebene][x][Math.abs(y-1)] == null && Spielfeld[ebene][Math.abs(x-1)][y] == null)			
							{
								return b1Doppelangriff;
							}
						}
					}	
				}
			}
		}
		
		/*
		 * möglichkeit 2 ---------------- rechtwinkliger doppelangriff mitte -----------------------------
		 */
		
		if((x == 1 && y == 0) || (x == 0 && y == 1) || (x == 1 && y == 2) || (x == 2 && y == 1))
		{
			//nacheinander für meine Steine auf dem Feld überprüfen
			for(int i = 0; i <= 2; i++)
			{
				for(int j = 0; j <= 2; j++)
				{
					for(int k = 0; k <= 2; k++)
					{
						//beweter Stein darf nicht einbezogen werden
						if(i == this.posIndexUmrechnen(nach.getEbene()) && j == this.posIndexUmrechnen(nach.getX()) && k == this.posIndexUmrechnen(nach.getY()))
							continue;
						
						//gucken ob man eine vorbereitung erstellen kann, also eine der 4 arten möglich ist
						if(y != 1)
						{
							if(Spielfeld[ebene][Math.abs(x-1)][Math.abs(y-1)] != null)
							{
								if(Spielfeld[ebene][Math.abs(x-1)][Math.abs(y-1)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
										Spielfeld[ebene][Math.abs(x-1)][y] == null && Spielfeld[ebene][Math.abs(x+1)][y] == null	
										&& Spielfeld[ebene][Math.abs(x-1)][Math.abs(y-2)] == null)				
								{
									return b1DoppelangriffIn2;
								}
							}
							if(Spielfeld[ebene][Math.abs(x+1)][Math.abs(y-1)] != null)
							{
								if(Spielfeld[ebene][Math.abs(x+1)][Math.abs(y-1)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
										Spielfeld[ebene][Math.abs(x+1)][y] == null && Spielfeld[ebene][Math.abs(x-1)][y] == null	
										&& Spielfeld[ebene][Math.abs(x+1)][Math.abs(y-2)] == null)					
								{
									return b1DoppelangriffIn2;
								}
							}
						}
						else
						{
							if(Spielfeld[ebene][Math.abs(x-1)][Math.abs(y-1)] != null)
							{
								if(Spielfeld[ebene][Math.abs(x-1)][Math.abs(y-1)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
										Spielfeld[ebene][x][Math.abs(y-1)] == null && Spielfeld[ebene][x][Math.abs(y+1)] == null	
										&& Spielfeld[ebene][Math.abs(x-2)][Math.abs(y-1)] == null)				
								{
									return b1DoppelangriffIn2;
								}
							}
							if(Spielfeld[ebene][Math.abs(x-1)][Math.abs(y+1)] != null)
							{
								if(Spielfeld[ebene][Math.abs(x-1)][Math.abs(y+1)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
										Spielfeld[ebene][x][Math.abs(y-1)] == null && Spielfeld[ebene][x][Math.abs(y+1)] == null	
										&& Spielfeld[ebene][Math.abs(x-2)][Math.abs(y+1)] == null)					
								{
									return b1DoppelangriffIn2;
								}
							}
						}
						
					}
				}
			}
		}
		if((x == 0 && y == 0) || (x == 0 && y == 2) || (x == 2 && y == 0) || (x == 2 && y == 2))
		{
			//gucken ob mein einen doppelangriff vollenden kann
			for(int a = 0; a <= 2; a++)
			{
				for(int b = 0; b <= 2; b++)
				{
					for(int c = 0; c <= 2; c++)
					{
						//beweter Stein darf nicht einbezogen werden
						if(a == this.posIndexUmrechnen(nach.getEbene()) && b == this.posIndexUmrechnen(nach.getX()) && c == this.posIndexUmrechnen(nach.getY()))
							continue;
						
						//gucken ob man eine vorbereitung erstellen kann, also eine der 4 arten möglich ist
						//1. Möglichkeit: Steine stehen sich in einer Ebene diagonal gegenüber
						
						if(Spielfeld[ebene][Math.abs(x-1)][y] != null && Spielfeld[ebene][x][Math.abs(y-1)] != null)
						{
							if(Spielfeld[ebene][Math.abs(x-1)][y].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][x][Math.abs(y-1)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&
									Spielfeld[ebene][Math.abs(x-2)][y] == null	&& Spielfeld[ebene][x][Math.abs(y-2)] == null)				
							{
								return b1Doppelangriff;
							}
						}
						
					}	
				}
			}
		}
		
		
		
		/*
		 * möglichkeit 3 ---------------- doppelangriff in einer ebene mit horizontal beiden aussenpositionen und vertikal mit zwei nebeneinanderliegenden Positionen gefüllt oder anders herum -----------------------------
		 */
		
		
		//nacheinander für meine Steine auf dem Feld überprüfen
		for(int i = 0; i <= 2; i++)
		{
			for(int j = 0; j <= 2; j++)
			{
				for(int k = 0; k <= 2; k++)
				{
					//beweter Stein darf nicht einbezogen werden
					if(i == this.posIndexUmrechnen(nach.getEbene()) && j == this.posIndexUmrechnen(nach.getX()) && k == this.posIndexUmrechnen(nach.getY()))
						continue;
					
					//gucken ob man eine vorbereitung erstellen kann, also eine der 4 arten möglich ist
					if(x != 1)
					{
						if(y != 1 && Spielfeld[ebene][Math.abs(x-2)][Math.abs(y-1)] != null)
						{
							if(Spielfeld[ebene][Math.abs(x-2)][Math.abs(y-1)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][Math.abs(x-1)][y] == null && Spielfeld[ebene][Math.abs(x-2)][y] == null	
									&& Spielfeld[ebene][Math.abs(x-2)][Math.abs(y-2)] == null)				
							{
								return b1DoppelangriffIn2;
							}
						}
						if(y != 1 && Spielfeld[ebene][Math.abs(x-1)][Math.abs(y-2)] != null)
						{
							if(Spielfeld[ebene][Math.abs(x-1)][Math.abs(y-2)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][Math.abs(x-2)][Math.abs(y-2)] == null && Spielfeld[ebene][x][Math.abs(y-1)] == null	
									&& Spielfeld[ebene][x][Math.abs(y-2)] == null)				
							{
								return b1DoppelangriffIn2;
							}
						}
						if(y == 1 && Spielfeld[ebene][Math.abs(x-2)][Math.abs(y-1)] != null)
						{
							if(Spielfeld[ebene][Math.abs(x-2)][Math.abs(y-1)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][Math.abs(x-1)][Math.abs(y-1)] == null && Spielfeld[ebene][x][Math.abs(y-1)] == null	
									&& Spielfeld[ebene][x][Math.abs(y+1)] == null)					
							{
								return b1DoppelangriffIn2;
							}
						}
						if(y == 1 && Spielfeld[ebene][Math.abs(x-2)][Math.abs(y+1)] != null)
						{
							if(Spielfeld[ebene][Math.abs(x-2)][Math.abs(y+1)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][Math.abs(x-1)][Math.abs(y+1)] == null && Spielfeld[ebene][x][Math.abs(y-1)] == null	
									&& Spielfeld[ebene][x][Math.abs(y+1)] == null)					
							{
								return b1DoppelangriffIn2;
							}
						}
					}
					else
					{
						if(Spielfeld[ebene][Math.abs(x-1)][Math.abs(y-2)] != null)
						{
							if(Spielfeld[ebene][Math.abs(x-1)][Math.abs(y-2)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][Math.abs(x-1)][y] == null && Spielfeld[ebene][Math.abs(x+1)][y] == null	
									&& Spielfeld[ebene][Math.abs(x-1)][Math.abs(y-1)] == null)					
							{
								return b1DoppelangriffIn2;
							}
						}
						if(Spielfeld[ebene][Math.abs(x+1)][Math.abs(y-2)] != null)
						{
							if(Spielfeld[ebene][Math.abs(x+1)][Math.abs(y-2)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][Math.abs(x-1)][y] == null && Spielfeld[ebene][Math.abs(x+1)][y] == null	
									&& Spielfeld[ebene][Math.abs(x+1)][Math.abs(y-1)] == null)					
							{
								return b1DoppelangriffIn2;
							}
						}
					}
					
				}
			}
		}
		
		//gucken ob mein einen doppelangriff vollenden kann
		if((x == 0 && y == 0) || (x == 2 && y == 0) || (x == 0 && y == 2) || (x == 2 && y == 2))
		{
			for(int a = 0; a <= 2; a++)
			{
				for(int b = 0; b <= 2; b++)
				{
					for(int c = 0; c <= 2; c++)
					{
						//beweter Stein darf nicht einbezogen werden
						if(a == this.posIndexUmrechnen(nach.getEbene()) && b == this.posIndexUmrechnen(nach.getX()) && c == this.posIndexUmrechnen(nach.getY()))
							continue;
						
						//gucken ob man eine vorbereitung erstellen kann, also eine der 4 arten möglich ist
						//1. Möglichkeit: Steine stehen sich in einer Ebene diagonal gegenüber
						
						if(Spielfeld[ebene][Math.abs(x-2)][y] != null && Spielfeld[ebene][x][Math.abs(y-1)] != null)
						{
							if(Spielfeld[ebene][Math.abs(x-2)][y].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][x][Math.abs(y-1)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&
									Spielfeld[ebene][Math.abs(x-1)][y] == null	&& Spielfeld[ebene][x][Math.abs(y-2)] == null)				
							{
								return b1Doppelangriff;
							}
						}
						if(Spielfeld[ebene][Math.abs(x-1)][y] != null && Spielfeld[ebene][x][Math.abs(y-2)] != null)
						{
							if(Spielfeld[ebene][Math.abs(x-1)][y].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][x][Math.abs(y-2)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&
									Spielfeld[ebene][Math.abs(x-2)][y] == null	&& Spielfeld[ebene][x][Math.abs(y-1)] == null)				
							{
								return b1Doppelangriff;
							}
						}
						
					}	
				}
			}
		}
		
		
		/*
		 * möglichkeit 4 ---------------- doppelangriff über zwei ebenen mit jeweils einen Stein am rand und einen in der mitte -----------------------------
		 */
		
		/*
		//nacheinander für meine Steine auf dem Feld überprüfen
		for(int i = 0; i <= 2; i++)
		{
			for(int j = 0; j <= 2; j++)
			{
				for(int k = 0; k <= 2; k++)
				{
					//beweter Stein darf nicht einbezogen werden
					if(i == this.posIndexUmrechnen(nach.getEbene()) && j == this.posIndexUmrechnen(nach.getX()) && k == this.posIndexUmrechnen(nach.getY()))
						continue;
					
					//gucken ob man eine vorbereitung erstellen kann, also eine der 4 arten möglich ist
					if(x != 1 || y != 1)
					{
						//abfragen 2. Stein jeweils zur y =1
						if(ebene != 1 && Spielfeld[Math.abs(ebene-1)][x][Math.abs(y-1)] != null)
						{
							if(Spielfeld[Math.abs(ebene-1)][x][Math.abs(y-1)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][x][Math.abs(y-1)] == null && Spielfeld[ebene][x][Math.abs(y-2)] == null	
									&& Spielfeld[Math.abs(ebene-2)][x][Math.abs(y-1)] == null)				
							{
								return 44;
							}
						}
						if(ebene == 1 && Spielfeld[ebene-1][x][Math.abs(y-1)] != null) //nach aussen
						{
							if(Spielfeld[Math.abs(ebene-1)][x][Math.abs(y-1)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][x][Math.abs(y-1)] == null && Spielfeld[ebene][x][Math.abs(y-2)] == null	
									&& Spielfeld[ebene+1][x][Math.abs(y-1)] == null)				
							{
								return 44;
							}
						}
						if(ebene == 1 && Spielfeld[ebene+1][x][Math.abs(y-1)] != null)	//nach innen
						{
							if(Spielfeld[Math.abs(ebene+1)][x][Math.abs(y-1)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][x][Math.abs(y-1)] == null && Spielfeld[ebene][x][Math.abs(y-2)] == null	
									&& Spielfeld[ebene-1][x][Math.abs(y-1)] == null)				
							{
								return 44;
							}
						}
						
						//abfragen jeweils zu x = 1
						if(ebene != 1 && Spielfeld[Math.abs(ebene-1)][Math.abs(x-1)][y] != null)
						{
							if(Spielfeld[Math.abs(ebene-1)][Math.abs(x-1)][y].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][Math.abs(x-1)][y] == null && Spielfeld[ebene][Math.abs(x-2)][y] == null	
									&& Spielfeld[Math.abs(ebene-2)][Math.abs(x-1)][y] == null)				
							{
								return 44;
							}
						}
						if(ebene == 1 && Spielfeld[ebene-1][Math.abs(x-1)][y] != null) //nach aussen
						{
							if(Spielfeld[Math.abs(ebene-1)][Math.abs(x-1)][y].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][Math.abs(x-1)][y] == null && Spielfeld[ebene][Math.abs(x-2)][y] == null	
									&& Spielfeld[ebene+1][Math.abs(x-1)][y] == null)				
							{
								return 44;
							}
						}
						if(ebene == 1 && Spielfeld[ebene+1][x][Math.abs(y-1)] != null)	//nach innen
						{
							if(Spielfeld[Math.abs(ebene+1)][Math.abs(x-1)][y].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][Math.abs(x-1)][y] == null && Spielfeld[ebene][Math.abs(x-2)][y] == null	
									&& Spielfeld[ebene-1][Math.abs(x-1)][y] == null)				
							{
								return 44;
							}
						}
						
					}
					else
					{
						if(ebene != 1)
						{
							// nach rechts
						}
						else
						{
							
						}
					}
					
				}
			}
		}
		
		//gucken ob mein einen doppelangriff vollenden kann
		if((x == 0 && y == 0) || (x == 2 && y == 0) || (x == 0 && y == 2) || (x == 2 && y == 2))
		{
			for(int a = 0; a <= 2; a++)
			{
				for(int b = 0; b <= 2; b++)
				{
					for(int c = 0; c <= 2; c++)
					{
						//beweter Stein darf nicht einbezogen werden
						if(a == this.posIndexUmrechnen(nach.getEbene()) && b == this.posIndexUmrechnen(nach.getX()) && c == this.posIndexUmrechnen(nach.getY()))
							continue;
						
						//gucken ob man eine vorbereitung erstellen kann, also eine der 4 arten möglich ist
						//1. Möglichkeit: Steine stehen sich in einer Ebene diagonal gegenüber
						
						if(Spielfeld[ebene][Math.abs(x-2)][y] != null && Spielfeld[ebene][x][Math.abs(y-1)] != null)
						{
							if(Spielfeld[ebene][Math.abs(x-2)][y].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][x][Math.abs(y-1)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&
									Spielfeld[ebene][Math.abs(x-1)][y] == null	&& Spielfeld[ebene][x][Math.abs(y-2)] == null)				
							{
								return 3333333;
							}
						}
						if(Spielfeld[ebene][Math.abs(x-1)][y] != null && Spielfeld[ebene][x][Math.abs(y-2)] != null)
						{
							if(Spielfeld[ebene][Math.abs(x-1)][y].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&		//Wenn auf der gegenüberligendenseite einer von meinen Steinen steht
									Spielfeld[ebene][x][Math.abs(y-2)].getFarbe().equals(aktuellerSpieler.getSpielerfarbe()) &&
									Spielfeld[ebene][Math.abs(x-2)][y] == null	&& Spielfeld[ebene][x][Math.abs(y-1)] == null)				
							{
								return 3333333;
							}
						}
						
					}	
				}
			}
		}
		
		
		
		
		*/
		return 0.0;
	}
	
	public int posIndexUmrechnen(EPositionIndex lPosition)
	{
		if(lPosition.equals(EPositionIndex.Eins))
			return 0;
		else if(lPosition.equals(EPositionIndex.Zwei))
			return 1;
		else if(lPosition.equals(EPositionIndex.Drei))
			return 2;
		else 
			return 99;
	}
	
	
	
}
