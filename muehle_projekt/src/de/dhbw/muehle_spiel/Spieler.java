package de.dhbw.muehle_spiel;
import de.dhbw.muehle_api.*;
import de.dhbw.muehle_api.strategy.IBewegung;
import de.dhbw.muehle_api.strategy.ISpielzug;

public class Spieler implements ISpielzug  {

private int AnzahlZüge;  // Anzahl der Züge die ein Spieler gemacht hat
private int AnzahlSteine;  //Anzahl der Steine, die ein Spieler auf dem Spielfeld hat
ESpielsteinFarbe Spielerfarbe; //Farbe der Spielsteine eines Spielers
EPhase Phase; //Spielphase, in der sich der Spieler befindet

//Konstruktor, Erzeugung eines neuen Spielers unter Angabe der Spielerfarbe
public Spieler (ESpielsteinFarbe lSpielerfarbe)
{
	Spielerfarbe = lSpielerfarbe;
	AnzahlZüge = 0;
	AnzahlSteine =0;
}

@Override
public ISpielstein getNeuenSpielstein() {
	AnzahlZüge ++;
	AnzahlSteine ++;
}

@Override
public IBewegung bewegeSpielStein() {
	AnzahlZüge ++;
	AnzahlSteine ++;
}

	

	
}
