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
			// setzen eines Steins
			
			
		}
		else{
			// schieben eines Steins
		}
		
		return zug;
	}

	//Liefert alle möglichen Bewegungnen eines Spielsteins
	public List<Bewegung> getMöglicheBewegungen(List<ISpielstein> p_SpielFeld, Spielstein lStein){
	
	List<Bewegung> moeglZuege = new ArrayList<Bewegung>();
	
	Spieler Spieler1 = new Spieler(ESpielsteinFarbe.WEISS);
	Spieler Spieler2 = new Spieler(ESpielsteinFarbe.SCHWARZ);
	
	//Erzeugung der Spielsteine, die in p_Spielfeld vorhanden sind
	for(ISpielstein s: p_SpielFeld){
		if(s.getFarbe() == farbe)
		{
			Spieler1.setzeSpielstein(s.getPosition(), 0, 0);
		}
		else
		{
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
						
						if(pruef.checkZug(new Bewegung(lStein.getPosition(), new Position(ebene, x, y)), Spieler1, Spieler2) == true)
						{
							moeglZuege.add(new Bewegung(lStein.getPosition(), new Position(ebene, x, y)));
						}
				}
			}	
		}		
		return moeglZuege;
	}
	
	public int Bewertung(List<ISpielstein> p_SpielFeld, Bewegung bewegung){
		int bewertung = 0;
		
		return bewertung;
	}
	
	@Override
	public ISpielstein entferneStein(List<ISpielstein> p_SpielFeld, ISpielzug p_LetzterSpielzug) throws StrategieException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
