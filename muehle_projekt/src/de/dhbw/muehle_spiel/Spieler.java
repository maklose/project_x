package de.dhbw.muehle_spiel;
import de.dhbw.muehle_api.*;
import de.dhbw.muehle_api.strategy.IBewegung;
import de.dhbw.muehle_api.strategy.ISpielzug;

public class Spieler implements ISpielzug, IBewegung {

private int AnzahlZuege;  // Anzahl der Züge die ein Spieler gemacht hat
private int AnzahlSteine;  //Anzahl der Steine, die ein Spieler auf dem Spielfeld hat
ESpielsteinFarbe Spielerfarbe; //Farbe der Spielsteine eines Spielers
EPhase Phase; //Spielphase, in der sich der Spieler befindet

//Konstruktor, Erzeugung eines neuen Spielers unter Angabe der Spielerfarbe
public Spieler (ESpielsteinFarbe lSpielerfarbe)
{
	Spielerfarbe = lSpielerfarbe;
	AnzahlZuege = 0;
	AnzahlSteine =0;
}

Spielstein[] Steine;


public void bewegeSpielstein() {
	AnzahlZuege ++;
	AnzahlSteine ++;
}



public ISpielstein getNeuenSpielstein(Position lPosition) {
	Steine[AnzahlZuege]= new Spielstein(Spielerfarbe, lPosition );
	AnzahlZuege ++;
	AnzahlSteine ++;
	return Steine[AnzahlZuege];
}



public IBewegung bewegeSpielStein(Bewegung bewegung) {
    
	AnzahlZuege ++;
	AnzahlSteine ++;
	return bewegung;
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

	

	
}
