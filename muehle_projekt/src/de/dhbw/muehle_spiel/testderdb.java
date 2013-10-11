package de.dhbw.muehle_spiel;


public class testderdb {
	public static void main(String[]args){
		Database db= new Database();
		try{
			
			//db.createtb();
			//db.valuetrans();
			//db.showdb();
			db.deletetb();
		}catch (NullPointerException e) {
			
			e.printStackTrace();
		}
		
	}

}
