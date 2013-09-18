package de.dhbw.muehle_spiel;


// Enumeration der drei Phasen des Spielsase 
public enum EPhase{
	
	//Erste Phase: Setzen der Steine
Setzen,

	//Zweite Phase: Alle Spielsteine sind gelegt worden
	//Steine k�nnen jetzt geschoben werden
Schieben,

	//Dritte Phase: Anzahl der Spielsteine <= 3
	//Steine k�nnen jetzt springen
Legen
	
}
