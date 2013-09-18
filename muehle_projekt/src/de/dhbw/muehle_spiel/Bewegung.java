package de.dhbw.muehle_spiel;

import de.dhbw.muehle_api.Position;
import de.dhbw.muehle_api.strategy.IBewegung;

public class Bewegung implements IBewegung {

	private Position von;
	private Position nach;
public Position getVon() {
		return von;
	}
	public void setVon(Position von) {
		this.von = von;
	}
	public Position getNach() {
		return nach;
	}
	public void setNach(Position nach) {
		this.nach = nach;
	}
public Bewegung (Position von, Position nach)
{
	this.von = von;
	this.nach = nach;
}
	@Override
	public Position altePosition() {
		// TODO Auto-generated method stub
		return von;
	}

	@Override
	public Position neuePosition() {
		
		return nach;
	}

}
