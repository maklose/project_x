package de.dhbw.muehle_spiel;


import java.sql.*;
import java.io.*;


public class Database {
	String url="jdbc:sqlite:database.db";
	Connection c;
	String driver="org.sqlite.JDBC";
	public Database(){
		try {
			Class.forName(driver);
			c= DriverManager.getConnection(url);
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[]args){

		    
	}
//Löschen der Datenbank	
public void deletetb(){
	
	String delete=("DROP TABLE position");
	try {
		Statement statement=c.createStatement();
		statement.executeUpdate(delete);
	} catch (SQLException e) {		
		e.printStackTrace();
	}
	
	
}


//Übergabe der Position an DB
public void valuetrans(){
	String update=("INSERT INTO position (Spielstein,E1,X1,Y1,E2,X2,Y2) VALUES('Weiß',5,5,5,6,6,6)");
    try {
    	Statement statement=c.createStatement();
		statement.executeUpdate(update);
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

//Ausgabe der DB
public void showdb(){
	
    try {
    	
	    Statement statement=c.createStatement();
	    String ausgabe=("SELECT * FROM position");
	    ResultSet result=statement.executeQuery(ausgabe);
	    while (result.next()) {
	    	  System.out.println();
	    	  int zugID = result.getInt("ID");
	    	  System.out.print("ID: "+zugID);
	    	  String Spielstein=result.getString("Spielstein");
	    	  System.out.print(" Spielstein: "+Spielstein);
	    	  int E1=result.getInt("E1");
	    	  int X1=result.getInt("X1");
	    	  int Y1=result.getInt("Y1");
	    	  int E2=result.getInt("E2");
	    	  int X2=result.getInt("X2");
	    	  int Y2=result.getInt("Y2");
	    	  System.out.print(" E1: "+E1);
	    	  System.out.print(" X1: "+X1);
	    	  System.out.print(" Y1: "+Y1);
	    	  System.out.print(" E2: "+E2);
	    	  System.out.print(" X2: "+X2);
	    	  System.out.print(" Y2: "+Y2);
	    	  
	    	  
	    	  
	    	}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
//Treiber laden

//Erzeugen der DB
public void createtb(){
		    
		    try {
		    
		     
		    Statement statement= null;
		    statement = c.createStatement();
		    String create="CREATE TABLE position(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
		    			+ "Spielstein varChar(30),"
		    			+ "E1 INT, X1 INT, Y1 INT, "
		    			+ "E2 INT, X2 INT, Y2 INT) ";
		    statement.executeUpdate(create);
		    		    		  		    
		    } 
		    catch ( Exception e ) {
		    	System.err.println(e.getMessage());
		        System.exit(0);
		    }
		    
		    
		    
	}
}


