package de.dhbw.muehle_spiel;
import de.dhbw.muehle_api.*;
import de.dhbw.muehle_api.strategy.IBewegung;
import de.dhbw.muehle_api.strategy.ISpielzug;

public class Spieler implements ISpielzug  {

private int AnzahlZ�ge;  // Anzahl der Z�ge die ein Spieler gemacht hat
private int AnzahlSteine;  //Anzahl der Steine, die ein Spieler auf dem Spielfeld hat
ESpielsteinFarbe Spielerfarbe; //Farbe der Spielsteine eines Spielers
EPhase Phase; //Spielphase, in der sich der Spieler befindet

//Konstruktor, Erzeugung eines neuen Spielers unter Angabe der Spielerfarbe
public Spieler (ESpielsteinFarbe lSpielerfarbe)
{
	Spielerfarbe = lSpielerfarbe;
	AnzahlZ�ge = 0;
}

	public void ISpielstein getNeuenSpielstein() {
		AnzahlZ�ge = AnzahlZ�ge + 1;
		AnzahlSpielsteine = AnzahlSpielsteine + 1;
		
	}

	@Override
	public void IBewegung bewegeSpielStein() {
		AnzahlZ�ge = AnzahlZ�ge + 1;
		
	}

	
}
