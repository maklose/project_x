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
Bewegung Zug;


	@Override
	public void startePartie(ESpielsteinFarbe p_SpielerFarbe) throws StrategieException {
		// TODO Auto-generated method stub
		anzahlZuege=0;
		farbe = p_SpielerFarbe;
		
		
	}

	@Override
	public void partieBeendet() throws StrategieException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISpielzug bewegeStein(List<ISpielstein> p_SpielFeld) throws StrategieException {
		Spielzug zug;
		if(anzahlZuege < 9){
			
			
			
			
		}
		else{
			// schieben eines Steins
		}
		
		return zug;
	}

	//Liefert alle möglichen Bewegungnen eines Spielsteins
	public List<Bewegung> getMoeglicheBewegungen(List<ISpielstein> p_SpielFeld, Spielstein lStein){
	
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
		return moeglZuege;
	}
	
	// Liefert die Bewertung eines Spielzugs
	private double Bewertung(List<ISpielstein> p_SpielFeld, Bewegung bewegung){
		double bewertung = 0;
		
		return bewertung;
	}
	
	
	private double max (int ltiefe, List<ISpielstein> p_SpielFeld){	
		
	Spieler spieler = new Spieler(farbe, "");
		
	//Erzeugung der Spielsteine, die in p_Spielfeld vorhanden sind
	for(ISpielstein s: p_SpielFeld){
			if(s.getFarbe() == farbe)
			{
				spieler.setzeSpielstein(s.getPosition(), 0, 0);
			}
		}
	
	List<Bewegung> moeglBewegungen = new ArrayList<Bewegung>();
	
	if(tiefe == 0)
	{
	//Bewertung
	}
	
	double maxWert= 2;
	double wert;
	
	for(int i = 0; i < spieler.getAnzahlSteine(); i++){	
		
		moeglBewegungen = getMoeglicheBewegungen(p_SpielFeld, spieler.Steine[i]);
		for(int j = 0; j < moeglBewegungen.size();j++){
			
			// Bewegung ausführen
			spieler.Steine[i].bewegen(moeglBewegungen.get(j), 0, 0);
			
			//neues Spielfeld erzeugen
			int zaehler = 0;
			List<ISpielstein> neuesSpielFeld = p_SpielFeld;
			for(int k = 0; k < p_SpielFeld.size() ; k++){
				if(neuesSpielFeld.get(k).getFarbe()== farbe){	
				neuesSpielFeld.set(k, spieler.Steine[zaehler]);
				zaehler++;
				}
			}
			
			//Rekursion
			wert= min((ltiefe - 1) ,neuesSpielFeld);
			if( wert > maxWert){
			wert = min((ltiefe - 1) ,neuesSpielFeld);	
			 if(ltiefe == tiefe)
			Zug = moeglBewegungen.get(j); 	 
			}
			}	
		}
	
	
	}
		
	
	private double min (int ltiefe, List<ISpielstein> p_SpielFeld){
		
	}
	
	
	@Override
	public ISpielstein entferneStein(List<ISpielstein> p_SpielFeld, ISpielzug p_LetzterSpielzug) throws StrategieException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
