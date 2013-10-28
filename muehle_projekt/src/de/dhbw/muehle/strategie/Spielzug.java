package de.dhbw.muehle.strategie;

import de.dhbw.muehle.ISpielstein;
import de.dhbw.muehle.strategy.IBewegung;
import de.dhbw.muehle.strategy.ISpielzug;
import de.dhbw.muehle_spiel.Bewegung;
import de.dhbw.muehle_spiel.Spielstein;

public class Spielzug implements ISpielzug{

Bewegung bewegung;	
Spielstein stein;
	
	public Spielzug(Bewegung lBewegung){
		bewegung = lBewegung;
	}
	
	public Spielzug(Spielstein lStein){
		stein = lStein;
	}
	@Override
	public  ISpielstein getNeuenSpielstein() {
		return stein;
	}

	@Override
	public IBewegung bewegeSpielStein() {
		return bewegung;
	}

}
