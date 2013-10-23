package de.dhbw.strategy;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.muehle_api.ISpielstein;
import de.dhbw.muehle_spiel.Bewegung;

public class Bewertung 
{
	//Array mit den aktuellen Steinen auf dem Feld
	List<ISpielstein> Steine;
	int Score = 0;
	
	public Bewertung()
	{
	
	}
	
	public int bewerteZug(List<ISpielstein> p_SpielFeld, Bewegung bewegung)
	{
		Steine = p_SpielFeld;
		this.checkInMuehle(bewegung);
		return Score;
	}

	private void checkInMuehle(Bewegung bewegung) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
