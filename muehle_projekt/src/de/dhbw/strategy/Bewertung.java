package de.dhbw.strategy;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.muehle_api.ISpielstein;
import de.dhbw.muehle_api.Position;
import de.dhbw.muehle_spiel.Bewegung;
import de.dhbw.muehle_spiel.Pruefung;
import de.dhbw.muehle_spiel.Spielstein;

public class Bewertung 
{
	//Array mit den aktuellen Steinen auf dem Feld
	List<ISpielstein> lSteine;
	Spielstein[] aSteine;
	int Score = 0;
	Position von, nach;
	Pruefung pruef = new Pruefung();
	ISpielstein bewegterStein;
	
	public Bewertung()
	{
	
	}
	
	public int bewerteZug(List<ISpielstein> p_SpielFeld, Bewegung bewegung, ISpielstein Stein)
	{
		von = bewegung.getVon();
		nach = bewegung.getNach();
		lSteine = p_SpielFeld;
		bewegterStein = Stein;
		aSteine = this.ListeUmrechnen(lSteine);
		
		this.checkInMuehle(bewegung);
		return Score;
	}

	private void checkInMuehle(Bewegung bewegung) 
	{
		//herausfinden was für ein Index der 

		if(pruef.checkInMuehle(IndexStein, lSteine))
		{
			
		}
		
	}
	
	public Spielstein[] ListeUmrechnen(List<ISpielstein> p_SpielFeld)
	{
		Spielstein[] aSteine = new Spielstein[p_SpielFeld.size()-1];
		for(int i = 0; i <= p_SpielFeld.size()-1; i++)
		{
			aSteine[i] = (Spielstein)p_SpielFeld.get(i);
		}
			
		return aSteine;
		
	}
	
	
	
}
