package de.dhbw.muehle_spiel;
import de.dhbw.muehle_api.*;
import de.dhbw.muehle_api.strategy.IBewegung;
import de.dhbw.muehle_api.strategy.ISpielzug;

public class Spieler {

private int AnzahlZuege;  // Anzahl der Züge die ein Spieler gemacht hat
private int AnzahlSteine;  //Anzahl der Steine, die ein Spieler auf dem Spielfeld hat
private ESpielsteinFarbe Spielerfarbe; //Farbe der Spielsteine eines Spielers
private EPhase Phase; //Spielphase, in der sich der Spieler befindet

//Konstruktor, Erzeugung eines neuen Spielers unter Angabe der Spielerfarbe
public Spieler (ESpielsteinFarbe lSpielerfarbe)
{
	Spielerfarbe = lSpielerfarbe;
	AnzahlZuege = 0;
	AnzahlSteine =0;
}



Spielstein[] Steine;


public EPhase getPhase(){
	if (AnzahlZuege < 9)
		Phase = Phase.Setzen;
	else if (AnzahlSteine == 3)
		Phase = Phase.Springen;
	else
		Phase = Phase.Schieben;
	
	return Phase;	
}

public void setzeSpielstein(Position lPosition) {
	Steine[AnzahlZuege]= new Spielstein(Spielerfarbe, lPosition );
	AnzahlZuege ++;
	AnzahlSteine ++;
}


public void bewegeSpielstein(Bewegung bewegung, int IndexStein) {
    Steine[IndexStein].bewegen(bewegung);
	AnzahlZuege ++;
}


public int getAnzahlZuege() {
	return AnzahlZuege;
}


public void setAnzahlZuege(int anzahlZuege) {
	AnzahlZuege = anzahlZuege;
}


public int getAnzahlSteine() {
	return AnzahlSteine;
}


public void setAnzahlSteine(int anzahlSteine) {
	AnzahlSteine = anzahlSteine;
}
	
public ESpielsteinFarbe getSpielerfarbe() {
	return Spielerfarbe;
}

public void setSpielerfarbe(ESpielsteinFarbe spielerfarbe) {
	Spielerfarbe = spielerfarbe;
}

}
