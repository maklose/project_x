package de.dhbw.muehle_spiel;

public class testderdb {
	public static void main(String[]args){
		Database db= new Database();
		//db.testdelete();
		db.connectdb();
		db.valuetrans();
		db.showdb();
		
	}

}