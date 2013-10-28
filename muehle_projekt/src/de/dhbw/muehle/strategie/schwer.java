package de.dhbw.muehle.strategie;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.muehle.*;
import de.dhbw.muehle.strategy.*;
import de.dhbw.muehle_spiel.Bewegung;
import de.dhbw.muehle_spiel.Pruefung;
import de.dhbw.muehle_spiel.Spieler;
import de.dhbw.muehle_spiel.Spielstein;



public class schwer implements IStrategie{

	int anzahlZuege;
	ESpielsteinFarbe farbe;
	int tiefe = 3;
	Bewegung bewegung;
	List<ISpielstein> SpielFeld;
	
	@Override
	public void startePartie(ESpielsteinFarbe p_SpielerFarbe) throws StrategieException {
		
		farbe = p_SpielerFarbe;
		anzahlZuege = 0;
		
	}

	@Override
	public void partieBeendet() throws StrategieException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISpielzug bewegeStein(List<ISpielstein> p_SpielFeld) throws StrategieException {

		Spielzug zug = null;
		SpielFeld = p_SpielFeld;
		
		//Festlegen, ob KI anfängt oder der Gegner
		//Dementsprechend anzahlZuege bestimmen
		if(anzahlZuege == 0){
			if (p_SpielFeld.size() == 1)
				anzahlZuege=1;
		}
		
		//Spielstein setzen
		if(anzahlZuege < 18){
		//double wert = minmax(p_SpielFeld, tiefe, anzahlZuege );
		zug = new Spielzug(new Spielstein(farbe, bewegung.getNach(), 0, 0, 0));	
		
		anzahlZuege += 2;	
		}
		
		//Spielstein bewegen
		else{
		
		zug = new Spielzug(bewegung);
			
		anzahlZuege += 2;	
		}
		
		return zug;
	}
	
	//double minmax(){
		
	

	@Override
	public ISpielstein entferneStein(List<ISpielstein> p_SpielFeld,
			ISpielzug p_LetzterSpielzug) throws StrategieException {
		// TODO Auto-generated method stub
		return null;
	}

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

}
