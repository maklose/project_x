package de.dhbw.muehle_api.strategy;

import de.dhbw.muehle_api.EPositionIndex;
import de.dhbw.muehle_api.Position;
import de.dhbw.muehle_spiel.Bewegung;
import de.dhbw.muehle_spiel.Spieler;

public class AuflistungZuege {                  

	
public void PossibleMoves(Spieler SpielerAktiv){
		
		
		
		EPositionIndex ebene = null;
		EPositionIndex x = null;
		EPositionIndex y = null;
		
		
		
		for (int i = 0; i <9 ; i++){
			
			for(int a = 1 ; a <= 3; a++)
				{
					if(a == 1){
						ebene = ebene.Eins;
						}
					if(a == 2){
						ebene = ebene.Zwei;
						}
					if(a == 3){
						ebene = ebene.Drei;
					}
					
					for(int b = 1; b <= 3; b++)
					{
							if(b == 1){
								x = x.Eins;
								}
							if(b == 2){
								x = x.Zwei;
								}
							if(b == 3){
								x = x.Drei;
								
							for(int c = 1; c <= 3; c++)
								{
									if(c == 1){
										y = y.Eins;
										}
									if(c == 2){
										y = y.Zwei;
										}
									if(c == 3){
										y = y.Drei;
									
									//Hier muss der Code rein
									}
									
								}
							}
						}
					}
			 	
				}
			
		
			

	}
	

	}
	

