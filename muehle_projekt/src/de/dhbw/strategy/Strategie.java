package de.dhbw.strategy;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.muehle_api.EPositionIndex;
import de.dhbw.muehle_api.ESpielsteinFarbe;
import de.dhbw.muehle_api.ISpielstein;
import de.dhbw.muehle_api.Position;
import de.dhbw.muehle_api.strategy.ISpielzug;
import de.dhbw.muehle_api.strategy.IStrategie;
import de.dhbw.muehle_api.strategy.StrategieException;
import de.dhbw.muehle_spiel.Bewegung;
import de.dhbw.muehle_spiel.Pruefung;
import de.dhbw.muehle_spiel.Spieler;
import de.dhbw.muehle_spiel.Spielstein;

public class Strategie implements IStrategie{

int anzahlZuege;
ESpielsteinFarbe farbe;
int tiefe = 3;

private Bewegung bewegung;

List<ISpielstein> SpielFeld;


	public Strategie(int ltiefe){
		tiefe = ltiefe;
	}

	@Override
	public void startePartie(ESpielsteinFarbe p_SpielerFarbe) throws StrategieException {
		// TODO Auto-generated method stub
		farbe = p_SpielerFarbe;
		anzahlZuege = 0;	
	}

	@Override
	public void partieBeendet() throws StrategieException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISpielzug bewegeStein(List<ISpielstein> p_SpielFeld) throws StrategieException {
     
		SpielFeld = p_SpielFeld;
		//Wenn Spielfeld leer ist, fängt KI-Spieler an und anzahlZuege = 0
		//Sonst fängt Gegner an und anzahlZuege = 1
		if(p_SpielFeld.size() == 0){
			anzahlZuege = 0;
		}
		else if (p_SpielFeld.size() == 1){
			anzahlZuege = 1;
		}
		
		double bewertung;
		Spielzug zug = null;
		
		if(anzahlZuege <= 18){
		bewertung = max(tiefe, null, anzahlZuege);
		anzahlZuege +=2;
//		System.out.println(bewegung.toString());
		System.out.println(bewegung + " "  + bewertung);
		zug = new Spielzug(new Spielstein(farbe, bewegung.getNach(),0,0,0));
		}
		else{
		bewertung = max(tiefe, null, anzahlZuege );
		anzahlZuege += 2;	
		zug = new Spielzug(bewegung);
		}
		return zug;
	}

	//Liefert alle möglichen Bewegungnen eines Spielsteins
	public List<Bewegung> getMoeglicheBewegungen(List<ISpielstein> p_SpielFeld, Spielstein lStein, int lanzahlZuege){
	
		List<Bewegung> moeglZuege = new ArrayList<Bewegung>();
		
		ESpielsteinFarbe farbeSpieler2;
		
		if( lStein.getFarbe() == ESpielsteinFarbe.SCHWARZ){
			farbeSpieler2 = ESpielsteinFarbe.WEISS;
		}
		else{
			farbeSpieler2 = ESpielsteinFarbe.SCHWARZ;
		}
			
		Spieler Spieler1 = new Spieler(lStein.getFarbe(), "");
		Spieler Spieler2 = new Spieler(farbeSpieler2, "");
		
		//Erzeugung der Spielsteine, die in p_Spielfeld vorhanden sind
		for(ISpielstein s: p_SpielFeld){
			if(s.getFarbe() == lStein.getFarbe())
			{
				Spieler1.setzeSpielstein(s.getPosition(), 0, 0);
			}
			else{
				Spieler2.setzeSpielstein(s.getPosition(), 0, 0);
			}
		}
			
		EPositionIndex ebene = null;
		EPositionIndex x = null;
		EPositionIndex y = null;
		
		Pruefung pruef = new Pruefung();
		
		// alle Mögliche Positionen, auf die ein Stein gesetzt werden darf
		if(lanzahlZuege <= 18){
			for( int i = 0; i< 3; i++){
				if(i == 0)
					ebene = ebene.Eins;
				if(i == 1)
					ebene = ebene.Zwei;
				if(i == 2)
					ebene = ebene.Drei;
			for(int j = 0; j < 3; j++){
					if(j == 0)
						x = x.Eins;
					if(j == 1)
						x = x.Zwei;
					if(j == 2)
						x = x.Drei;
				for(int k = 0; k < 3; k++){
						if(k == 0)
							y = y.Eins;
						if(k == 1)
							y = y.Zwei;
						if(k == 2)
							y = y.Drei;
						
						if((ebene == ebene.Eins && x == x.Zwei && y == y.Zwei) 
								|| (ebene == ebene.Zwei && x == x.Zwei && y == y.Zwei)
								|| (ebene == ebene.Drei && x == x.Zwei && y == y.Zwei))
							continue;
						
						if(pruef.checkSetzen(new Position(ebene, x, y), Spieler1, Spieler2) == true)
						{
							moeglZuege.add(new Bewegung(null, new Position(ebene, x, y)));
						}
					}
				}	
			}		
		}
			
		else{	
		//Überprüfung für jede mögl. Position auf dem Brett, ob der Stein dahin/springen ziehen darf	
			for( int i = 0; i< 3; i++){
					if(i == 0)
						ebene = ebene.Eins;
					if(i == 1)
						ebene = ebene.Zwei;
					if(i == 2)
						ebene = ebene.Drei;
				for(int j = 0; j < 3; j++){
						if(j == 0)
							x = x.Eins;
						if(j == 1)
							x = x.Zwei;
						if(j == 2)
							x = x.Drei;
					for(int k = 0; k < 3; k++){
							if(k == 0)
								y = y.Eins;
							if(k == 1)
								y = y.Zwei;
							if(k == 2)
								y = y.Drei;
							
							if((ebene == ebene.Eins && x == x.Zwei && y == y.Zwei) 
									|| (ebene == ebene.Zwei && x == x.Zwei && y == y.Zwei)
									|| (ebene == ebene.Drei && x == x.Zwei && y == y.Zwei))
								continue;
							
							if(pruef.checkZug(new Bewegung(lStein.getPosition(), new Position(ebene, x, y)), Spieler1, Spieler2) == true)
							{
								moeglZuege.add(new Bewegung(lStein.getPosition(), new Position(ebene, x, y)));
							}
					}
				}	
			}		
		}	
			return moeglZuege;
		}
	
	
	private double max (int ltiefe, Bewegung bewegung, int lanzahlZuege){	
			
	ESpielsteinFarbe farbeSpieler2;
	
	//Festlegen, welche Farbe der Gegner hat
	if( farbe == ESpielsteinFarbe.SCHWARZ)
		farbeSpieler2 = ESpielsteinFarbe.WEISS;
	else
		farbeSpieler2 = ESpielsteinFarbe.SCHWARZ;
	
	//Spieler erzeugen
	Spieler spieler1 = new Spieler(farbe, "");
	Spieler spieler2 = new Spieler(farbeSpieler2, "");
	
	if(ltiefe != 0){
		if((lanzahlZuege - anzahlZuege) > 0 ){
			//ISpielstein an  Feld anfügen, wenn Stein neu auf das Feld gesetzt wurde
			if(lanzahlZuege <= 18){
			SpielFeld.add(new Spielstein(spieler2.getSpielerfarbe(), bewegung.getNach(), 0,0,0));	
			}
		
		else{
			//Position ändern, wenn Spielstein geschoben wurde/gesprungen ist
			for(int i= 0; i < SpielFeld.size(); i++){
				if(SpielFeld.get(i).getPosition() == bewegung.getVon()){
				SpielFeld.set(i, new Spielstein(spieler1.getSpielerfarbe(), bewegung.getNach(), 0,0,0));
				}
			}
		}
		//Erzeugung der Spielsteine des KI Spielers, die in p_Spielfeld vorhanden sind
		for(ISpielstein s: SpielFeld){
				if(s.getFarbe() == farbe)
				{
					spieler1.setzeSpielstein(s.getPosition(), 0, 0);
				}
				else
				{
					spieler2.setzeSpielstein(s.getPosition(),0,0);
				}
			}
		}
	}	
	
	Pruefung pruef = new Pruefung();
	
	// Wenn die tiefe 0 ist oder das Spielbeendet ist, wird die Bewertung des Spielzugs geholt
	if(ltiefe == 0 || pruef.checkSpielBeendet(spieler1, spieler2) == true)
	{
	Bewertung bewertung = new Bewertung();	
//	for(ISpielstein s: SpielFeld){
//		System.out.print(s.getPosition().toString() + " ");
//	}
//	System.out.println();
	return (bewertung.bewerteZug(SpielFeld, bewegung, lanzahlZuege) *-1);
	}	
		
	List<Bewegung> moeglBewegungen = new ArrayList<Bewegung>();
	
	double maxWert= -2;
	double wert;
	
	if(lanzahlZuege > 18){ // ziehen und springen Phase
	
	for(int i = 0; i < spieler1.getAnzahlSteine(); i++){	
	
		moeglBewegungen = getMoeglicheBewegungen(SpielFeld, spieler1.Steine[i], lanzahlZuege);
		for(int j = 0; j < moeglBewegungen.size();j++){	
			//Rekursion
			wert= min((ltiefe - 1), moeglBewegungen.get(j), (lanzahlZuege + 1));
			//test
			//Bewegung rückgängig machen
			
			for(int k = 0; k < SpielFeld.size(); k++){
				if(SpielFeld.get(k).getPosition() == moeglBewegungen.get(j).getVon())
					SpielFeld.set(k, new Spielstein(spieler2.getSpielerfarbe(), bewegung.getVon(), 0,0,0));
			}
			
			if( wert > maxWert){
				maxWert = wert;	
				if(ltiefe == tiefe)
				bewegung = moeglBewegungen.get(j);
			}
		}	
	}
	}	
	else  //setzen Phase
	{
		moeglBewegungen = getMoeglicheBewegungen(SpielFeld, new Spielstein(farbe, null, 0,0,0), lanzahlZuege);
		for(int j = 0; j < moeglBewegungen.size();j++){	
			
			//Rekursion
			wert= min((ltiefe - 1) , moeglBewegungen.get(j), (lanzahlZuege + 1));
			
			// Bewegung rückgängig machen
			if((SpielFeld.size() > 0) && (ltiefe > 1))
			SpielFeld.remove(SpielFeld.size() - 1 );	
					
			if( wert > maxWert){
				maxWert = wert;
//				System.out.println(maxWert);
//				System.out.println(moeglBewegungen.get(j).toString());
				if(ltiefe == tiefe){
				System.out.println(wert + "Anzahl Elemente in SpielFeld: " + SpielFeld.size());
				ergebnis(moeglBewegungen.get(j));
				}
			}
		}
	}	
	System.out.println(anzahlZuege + 1);
	return (maxWert * -1);
	}
		
	
	private double min (int ltiefe, Bewegung bewegung, int lanzahlZuege){
		
	ESpielsteinFarbe farbeSpieler2;
		
	//Festlegen, welche Farbe der Gegner hat
	if( farbe == ESpielsteinFarbe.SCHWARZ)
		farbeSpieler2 = ESpielsteinFarbe.WEISS;
	else
		farbeSpieler2 = ESpielsteinFarbe.SCHWARZ;
		
	//Spieler erzeugen
	Spieler spieler1 = new Spieler(farbe, "");
	Spieler spieler2 = new Spieler(farbeSpieler2, "");

//	//neues Spielfeld erzeugen
//	List<ISpielstein> neuesSpielFeld = SpielFeld;
	
	if(ltiefe != 0){
		//ISpielstein an  Feld anfügen, wenn Stein neu gesetzt wurde
		if(lanzahlZuege <= 18){
			SpielFeld.add(new Spielstein(spieler1.getSpielerfarbe(), bewegung.getNach(), 0,0,0));	
			}
	
		else{
		//Position ändern, wenn Spielstein geschoben wurde/gesprungen ist
			for(int i= 0; i < SpielFeld.size(); i++){
				if(SpielFeld.get(i).getPosition() == bewegung.getVon()){
				SpielFeld.set(i, new Spielstein(spieler2.getSpielerfarbe(), bewegung.getNach(), 0,0,0));
				}			
			}
		}
		//Erzeugung der Spielsteine des KI Gegners, die in SpielFeld vorhanden sind
		for(ISpielstein s: SpielFeld){
				if(s.getFarbe() == farbe)
				{
					spieler1.setzeSpielstein(s.getPosition(), 0, 0);
				}
				else
				{
					spieler2.setzeSpielstein(s.getPosition(),0,0);
				}
			}
	}	
	
	Pruefung pruef = new Pruefung();
		
	// Wenn die tiefe 0 ist oder das Spielbeendet ist, wird die Beweertung des Spielzugs geholt
	if(ltiefe == 0 || pruef.checkSpielBeendet(spieler2, spieler1) == true)
	{
	Bewertung bewertung = new Bewertung();	
	
//	for(ISpielstein s: SpielFeld){
////		System.out.print(s.getPosition().toString() + " ");
//	}
////	System.out.println();
	return bewertung.bewerteZug(SpielFeld, bewegung, lanzahlZuege);
	}	
			
	List<Bewegung> moeglBewegungen = new ArrayList<Bewegung>();
		
	double minWert= 2;
	double wert;
		
	if(lanzahlZuege > 18){  // Ziehen und Springen Phase
	for(int i = 0; i < spieler2.getAnzahlSteine(); i++){	
		
		moeglBewegungen = getMoeglicheBewegungen(SpielFeld, spieler2.Steine[i], lanzahlZuege);
		for(int j = 0; j < moeglBewegungen.size();j++){	
			//Rekursion
			wert= max((ltiefe - 1) , moeglBewegungen.get(j), (lanzahlZuege + 1));
			
			// Bewegung rückgängig machen			
			for(int k = 0; k < SpielFeld.size(); k++){
				if(SpielFeld.get(k).getPosition() == moeglBewegungen.get(j).getVon()){
					SpielFeld.set(k, new Spielstein(spieler1.getSpielerfarbe(), bewegung.getVon(), 0,0,0));
				}
			}

			if( wert < minWert){
				minWert = wert;	
				System.out.println(wert);
				System.out.println(moeglBewegungen.get(j).toString());
				if(ltiefe == tiefe)
				bewegung = moeglBewegungen.get(j); 	 
			}
		}	
	}
	}
		
	else{ // Setzen Phase
		moeglBewegungen = getMoeglicheBewegungen(SpielFeld, new Spielstein(spieler2.getSpielerfarbe(), null, 0,0,0), lanzahlZuege);
		for(int j = 0; j < moeglBewegungen.size();j++){	
			//Rekursion
			wert= max((ltiefe - 1) , moeglBewegungen.get(j), (lanzahlZuege + 1));
			
			//Bewegung rückgängig machen
//			if(lanzahlZuege < 18){
				if(ltiefe > 1)
				SpielFeld.remove(SpielFeld.size() - 1 );	
//			}
			
			if( wert < minWert){
				minWert = wert;	
				if(ltiefe == tiefe)
				ergebnis(moeglBewegungen.get(j));
				
			}
		}			
	}
		
		return minWert;
	}
	
	//wird aufgerufen, um die gewählte Bewegung festzulegen
	void ergebnis (Bewegung lbewegung){
		bewegung = lbewegung;
		System.out.println(lbewegung);
		
	}
	
	@Override
	public ISpielstein entferneStein(List<ISpielstein> p_SpielFeld, ISpielzug p_LetzterSpielzug) throws StrategieException {
	
	ISpielstein SteinEntfernen;	
		
	ESpielsteinFarbe farbeSpieler2;
	boolean aufLinie;
	List<Spielstein> SteineAufLinie = new ArrayList();
	Pruefung pruef = new Pruefung();
		
	//Festlegen der Spielerfarben
	if( farbe == ESpielsteinFarbe.SCHWARZ)
		farbeSpieler2 = ESpielsteinFarbe.WEISS;
	else
		farbeSpieler2 = ESpielsteinFarbe.SCHWARZ;
			
	Spieler Spieler1  = new Spieler(farbe, "");
	Spieler Spieler2  = new Spieler(farbeSpieler2, "");
	
	//Ablegen der Steine aus P_SpielFeld in den Spielern
	for(ISpielstein s: p_SpielFeld){
		if(s.getFarbe() == farbe)
		{
			Spieler1.setzeSpielstein(s.getPosition(), 0, 0);
		}
		else
		{
			Spieler2.setzeSpielstein(s.getPosition(),0,0);
		}
	}
	
	//Überprüfen, ob zwei Steine der selben Farbe auf einer Linie stehen
	EPositionIndex ebene = null;
	EPositionIndex x = null;
	EPositionIndex y = null;
	
	int aenderungEbene, aenderungX, aenderungY;
	int zaehlerEbene = 0;
	int zaehlerX = 0; 
	int zaehlerY = 0;
	
	Spielstein[] Steine = new Spielstein[Spieler2.getAnzahlSteine()];
	
	for (int index = 0; index < Spieler2.getAnzahlSteine(); index++)
	{
		Steine[index] = Spieler2.Steine[index];
	}
	
	int[][] Positionen = new int[9][3];
	
	// Ablegen der Positionen aller Steine eines Spielers in einem Array
	for (int i = 0; i < 9; i++){
		if(Steine[i] != null){
			Positionen[i][0]= Steine[i].getPosition().getEbene().getValue();
			Positionen[i][1]= Steine[i].getPosition().getX().getValue();
			Positionen[i][2]= Steine[i].getPosition().getY().getValue();
			}
	}
	
	for(int IndexStein = 0; IndexStein < Spieler2.getAnzahlSteine(); IndexStein++){
		for (int j =0; j <9; j++){
			
			aenderungEbene = 0;
			aenderungX = 0;
			aenderungY = 0;
			
			//Vergleicht die Position des betrachteten Steins(IndexStein) mit der des Steins an j-ter Stelle
			aenderungEbene = Math.abs(Positionen[j][0] - Positionen[IndexStein][0]);
			aenderungX = Math.abs(Positionen[j][1] - Positionen[IndexStein][1]);
			aenderungY = Math.abs(Positionen[j][2] - Positionen[IndexStein][2]);
					
			//Heraufsetzen des Zählers um 1, wenn sich nur die Ebene verändert hat
			if ((aenderungEbene == 1 && aenderungX == 0 && aenderungY == 0)||
				(aenderungEbene == 2 && aenderungX == 0 && aenderungY == 0))
			{
				zaehlerEbene ++;
				
				
				// Ausschließen der Eckpositionen
				//Sonderfälle: 1,1,3 - 2,1,3 - 3,1,3 ; 1,3,3 - 2,3,3 - 3,3,3 ; 1,3,1 - 2,3,1 - 3,3,1 ; 1,1,1 - 2,1,1 - 3,1,1
				if	((Positionen[j][0] != Positionen[IndexStein][0])&&
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
	
		if(zaehlerEbene == 1 || zaehlerX == 1 || zaehlerY == 1){
				aufLinie = true;
				
				//Abrfagen, ob der Stein[IndexStein in einer Mühle steht]
				// wenn nicht hinzufügen zu SteineAufLinie
				if(pruef.checkInMuehle(IndexStein, Spieler2.Steine) == false)
				SteineAufLinie.add(Spieler2.Steine[IndexStein]);		
			}
		
		}
	
	
	//Stein auswählen, der entfernt werden soll
	if(SteineAufLinie.size() >0){
		SteinEntfernen = SteineAufLinie.get(0);
	}
	else{
//		for(int i = 0; i < )
	}
	
	return SteinEntfernen;
	}	
			
}

