package de.dhbw.muehle_spiel;
import de.dhbw.muehle_api.*;


public class Spieler {

private int AnzahlZuege;  // Anzahl der Züge die ein Spieler gemacht hat
private int AnzahlSteine;  //Anzahl der Steine, die ein Spieler auf dem Spielfeld hat

private ESpielsteinFarbe Spielerfarbe; //Farbe der Spielsteine eines Spielers
private EPhase Phase; //Spielphase, in der sich der Spieler befindet

Spielstein[] Steine = new Spielstein[9]; //Array, in dem alle Spielsteine eines Spielers abgelegt werden

private boolean amZug = false;

//Konstruktor, Erzeugung eines neuen Spielers unter Angabe der Spielerfarbe
public Spieler (ESpielsteinFarbe lSpielerfarbe)
{
	Spielerfarbe = lSpielerfarbe;
	AnzahlZuege = 0;
	AnzahlSteine =0;
}

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
	if (AnzahlZuege <= 9){
	Steine[AnzahlSteine]= new Spielstein(Spielerfarbe, lPosition );
	AnzahlZuege ++;
	AnzahlSteine ++;
	}
}


public void bewegeSpielstein(Bewegung bewegung, int IndexStein) {
    if (AnzahlZuege > 9){
	Steine[IndexStein].bewegen(bewegung);
	AnzahlZuege ++;
    }
}


public int getAnzahlZuege() {
	return AnzahlZuege;
}

public int getAnzahlSteine() {
	return AnzahlSteine;
}

	
public ESpielsteinFarbe getSpielerfarbe() {
	return Spielerfarbe;
}

public void setSpielerfarbe(ESpielsteinFarbe spielerfarbe) {
	Spielerfarbe = spielerfarbe;
}

public boolean isAmZug() {
	return amZug;
}

public void setAmZug(boolean amZug) {
	this.amZug = amZug;
}


}
