package de.dhbw.muehle_spiel;

import de.dhbw.muehle_api.EPositionIndex;
import de.dhbw.muehle_api.ESpielsteinFarbe;
import de.dhbw.muehle_api.Position;

public class Test {

	public static void main(String[] args) {
		
		Spieler Spieler1 = new Spieler(ESpielsteinFarbe.SCHWARZ);
		Spieler Spieler2 = new Spieler(ESpielsteinFarbe.WEISS);
		
		Spieler1.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Eins));
		Spieler1.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Zwei, EPositionIndex.Eins));
		Spieler1.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Eins));
		Spieler1.setzeSpielstein(new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Eins));
		Spieler1.setzeSpielstein(new Position(EPositionIndex.Zwei, EPositionIndex.Zwei, EPositionIndex.Eins));
		Spieler1.setzeSpielstein(new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Eins));
		Spieler1.setzeSpielstein(new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Eins));
		Spieler1.setzeSpielstein(new Position(EPositionIndex.Drei, EPositionIndex.Zwei, EPositionIndex.Eins));
		Spieler1.setzeSpielstein(new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Eins));
		
		Spieler1.entferneSpielstein(0);
		
		Spieler2.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Drei));
		Spieler2.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Zwei, EPositionIndex.Drei));
		Spieler2.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Drei));
		Spieler2.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Zwei));
		Spieler2.setzeSpielstein(new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Zwei));
		Spieler2.setzeSpielstein(new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Zwei));
		Spieler2.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Zwei));
		Spieler2.setzeSpielstein(new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Zwei));
		Spieler2.setzeSpielstein(new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Zwei));
		
//		System.out.println("Anzahl Steine: " + Spieler1.getAnzahlSteine());
//		System.out.println("Anzahl Züge: " + Spieler1.getAnzahlZuege());
//		System.out.println("Position Stein 0: " + Spieler1.Steine[0].getAktuellePosition().toString());
		
		Pruefung pruef = new Pruefung();
		
		Position pos =new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Zwei);
		Position pos1 =new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Drei);
		//System.out.println(pruef.checkSetzen(pos, Spieler1, Spieler2));
		
//		System.out.println(pruef.checkZug(new Bewegung(pos, pos1), Spieler1, Spieler2));
		System.out.println(pruef.checkInMuehle(1, Spieler1.Steine));
		
		
	}
}
