package de.dhbw.muehle_spiel;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.muehle_api.EPositionIndex;
import de.dhbw.muehle_api.ESpielsteinFarbe;
import de.dhbw.muehle_api.ISpielstein;
import de.dhbw.muehle_api.Position;
import de.dhbw.muehle_api.strategy.ISpielzug;
import de.dhbw.muehle_api.strategy.StrategieException;
import de.dhbw.strategy.Spielzug;
import de.dhbw.strategy.Strategie;

public class Test {

	public static void main(String[] args) throws StrategieException {
		
		Spieler Spieler1 = new Spieler(ESpielsteinFarbe.SCHWARZ);
		Spieler Spieler2 = new Spieler(ESpielsteinFarbe.WEISS);
//		
		Spieler1.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Eins),0,0);
		Spieler1.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Zwei, EPositionIndex.Eins),0,0);
		Spieler1.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Eins),0,0);
//		Spieler1.setzeSpielstein(new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Eins));
//		Spieler1.setzeSpielstein(new Position(EPositionIndex.Zwei, EPositionIndex.Zwei, EPositionIndex.Eins));
//		Spieler1.setzeSpielstein(new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Eins));
//		Spieler1.setzeSpielstein(new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Eins));
//		Spieler1.setzeSpielstein(new Position(EPositionIndex.Drei, EPositionIndex.Zwei, EPositionIndex.Eins));
//		Spieler1.setzeSpielstein(new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Eins));
//		
//		Spieler1.entferneSpielstein(0);
//		
		Spieler2.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Drei),0,0);
		Spieler2.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Zwei, EPositionIndex.Drei),0,0);
		Spieler2.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Drei),0,0);
//		Spieler2.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Zwei));
//		Spieler2.setzeSpielstein(new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Zwei));
//		Spieler2.setzeSpielstein(new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Zwei));
//		Spieler2.setzeSpielstein(new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Zwei));
//		Spieler2.setzeSpielstein(new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Zwei));
//		Spieler2.setzeSpielstein(new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Zwei));
		
//		System.out.println("Anzahl Steine: " + Spieler1.getAnzahlSteine());
//		System.out.println("Anzahl Züge: " + Spieler1.getAnzahlZuege());
//		System.out.println("Position Stein 0: " + Spieler1.Steine[0].getAktuellePosition().toString());
		
//		Pruefung pruef = new Pruefung();
//		
//		Position pos =new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Zwei);
//		Position pos1 =new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Drei);
//		//System.out.println(pruef.checkSetzen(pos, Spieler1, Spieler2));
		
//		System.out.println(pruef.checkZug(new Bewegung(pos, pos1), Spieler1, Spieler2));
//		System.out.println(pruef.checkInMuehle(1, Spieler1.Steine));
		
		Spielzug spielzug1 = new Spielzug(Spieler1.Steine[0]);
		Spielzug spielzug2 = new Spielzug(Spieler1.Steine[1]);
		Spielzug spielzug3 = new Spielzug(Spieler1.Steine[2]);
		Spielzug spielzug4 = new Spielzug(Spieler2.Steine[0]);
		Spielzug spielzug5 = new Spielzug(Spieler2.Steine[1]);
		Spielzug spielzug6 = new Spielzug(Spieler2.Steine[2]);
		
		
		List<ISpielstein> p_SpielFeld = new ArrayList<ISpielstein>();
		Spielstein s = Spieler1.Steine[0];
		
		p_SpielFeld.add(spielzug1.getNeuenSpielstein());
		p_SpielFeld.add(spielzug2.getNeuenSpielstein());
		p_SpielFeld.add(spielzug3.getNeuenSpielstein());
		p_SpielFeld.add(spielzug4.getNeuenSpielstein());
		p_SpielFeld.add(spielzug5.getNeuenSpielstein());
		p_SpielFeld.add(spielzug6.getNeuenSpielstein());
		
		
		Strategie strategy = new Strategie();
		strategy.startePartie(ESpielsteinFarbe.WEISS);
		List<Bewegung> moves = strategy.getMöglicheBewegungen(p_SpielFeld, Spieler1.Steine[0]);
		
		for( Bewegung b : moves)
		{
			System.out.println("b.toString()");
		}
		
	}
}
