package de.dhbw.muehle_spiel;
import de.dhbw.muehle_api.*;


public class Spieler {

private int AnzahlZuege;  // Anzahl der Züge die ein Spieler gemacht hat
private int AnzahlSteine;  //Anzahl der Steine, die ein Spieler auf dem Spielfeld hat

private ESpielsteinFarbe Spielerfarbe; //Farbe der Spielsteine eines Spielers
private EPhase Phase; //Spielphase, in der sich der Spieler befindet

Spielstein[] Steine = new Spielstein[9]; //Array, in dem alle Spielsteine eines Spielers abgelegt werden

//Konstruktor, Erzeugung eines neuen Spielers unter Angabe der Spielerfarbe
public Spieler (ESpielsteinFarbe lSpielerfarbe)
{
	Spielerfarbe = lSpielerfarbe;
	AnzahlZuege = 0;
	AnzahlSteine =0;
}

//Liefert die Phase, in der sich ein Spieler befindet
public EPhase getPhase(){
	if (AnzahlZuege < 9)
		Phase = EPhase.Setzen;
	else if (AnzahlSteine == 3)
		Phase = EPhase.Springen;
	else
		Phase = EPhase.Schieben;
	
	return Phase;	
}

// Erzeugt einen neuen Spielstein, solange AnzahlZuege <9
public void setzeSpielstein(Position lPosition, int lxPos, int lyPos) {
	if (AnzahlZuege <= 9){
	Steine[AnzahlSteine]= new Spielstein(Spielerfarbe, lPosition, lxPos, lyPos);
	AnzahlZuege ++;
	AnzahlSteine ++;
	}
}

//Ändert die Position eines Spielsteins
public void bewegeSpielstein(Bewegung bewegung, int IndexStein, int lxPos, int lyPos) {
	
		if (AnzahlZuege > 9){
				Steine[IndexStein].bewegen(bewegung, lxPos, lyPos);
				AnzahlZuege ++;
		}
   }


public void entferneSpielstein(int IndexStein){
	
		Steine[IndexStein].setPosition(null);
		AnzahlSteine --;
	
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


public String SpielsteinFarbeAsString()
{
	if(this.Spielerfarbe == ESpielsteinFarbe.SCHWARZ)
		return "Schwarz";
	else
	return "Weiss";
}

public Spielstein getSpielstein(int index)
{
	return Steine[index];
}


}
