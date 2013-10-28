package de.dhbw.muehle.strategie;

import de.dhbw.muehle.strategy.IStrategie;
import de.dhbw.muehle.strategy.IStrategieFactory;

public class StrategieFactory implements IStrategieFactory{

	@Override
	public String getName() {
		
		return "StrategieProjektX";
		
	}

	@Override
	public IStrategie getStrategie() {
		
		return new Strategie(1);
	}

}
