package de.dhbw.strategy;

import java.util.List;

import de.dhbw.muehle_api.ESpielsteinFarbe;
import de.dhbw.muehle_api.ISpielstein;
import de.dhbw.muehle_api.Position;
import de.dhbw.muehle_api.strategy.*;



public class schwer implements IStrategie, ISpielzug, IBewegung, IStrategieFactory{

	@Override
	public void startePartie(ESpielsteinFarbe p_SpielerFarbe)
			throws StrategieException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partieBeendet() throws StrategieException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISpielzug bewegeStein(List<ISpielstein> p_SpielFeld)
			throws StrategieException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISpielstein entferneStein(List<ISpielstein> p_SpielFeld,
			ISpielzug p_LetzterSpielzug) throws StrategieException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStrategie getStrategie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position altePosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position neuePosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISpielstein getNeuenSpielstein() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBewegung bewegeSpielStein() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
