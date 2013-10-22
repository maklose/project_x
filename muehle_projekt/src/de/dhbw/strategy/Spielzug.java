package de.dhbw.strategy;

import de.dhbw.muehle_api.ISpielstein;
import de.dhbw.muehle_api.strategy.IBewegung;
import de.dhbw.muehle_api.strategy.ISpielzug;
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
