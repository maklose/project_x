package de.dhbw.muehle_spiel;


public class testderdb {
	public static void main(String[]args){
		Database db= new Database();
			
//			db.erzeugetb("highscore");
			//db.zugspeichern();
			//db.zeige_p();
//			db.l�schetb("highscore");
			//db.anzahlzuegespeichern();
//			db.testmethode_h();
			String [][] ergebnis=db.zeige_h();
			for(int i=0;i<10;i++)
			{	if(ergebnis[i][0]!=null && ergebnis[i][1]!=null){
					System.out.print("Z�ge: ["+ergebnis[i][0]+"] ");
					System.out.print("Spielername: ["+ergebnis[i][1]+"] ");
					System.out.println();
			}
				
			}
	
		
	}

}
