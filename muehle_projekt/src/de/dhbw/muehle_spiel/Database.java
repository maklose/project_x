package de.dhbw.muehle_spiel;


import java.sql.*;
import java.io.*;


public class Database {
	public Database(){
		
	
	}
	
	public static void main(String[]args){

		    
	}
//Löschen der Datenbank	
public void deletetb(){
	String url="jdbc:sqlite:database.db";
	String delete=("DROP TABLE position");
	try {
		Connection c = null;		    
	    c = DriverManager.getConnection(url);
	    Statement statement=c.createStatement();
		statement.executeQuery(delete);
	} catch (SQLException e) {		
		e.printStackTrace();
	}
	
	
}

public void testdelete(){
	String driver="org.sqlite.JDBC";
	String url="jdbc:sqlite:database.db";
	String delete=("DROP TABLE position");
	try {
		Class.forName(driver);
		Connection c = null;		    
	    c = DriverManager.getConnection(url);
	    Statement statement=c.createStatement();
		statement.executeQuery(delete);
	} catch (SQLException e) {		
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	}
}
//Übergabe der Position an DB
public void valuetrans(){
	String url="jdbc:sqlite:database.db";
	String update=("INSERT INTO position (Spielstein,E1,X1,Y1,E2,X2,Y2) VALUES('Weiß',5,5,5,6,6,6)");
    try {
    	Connection c = null;		    
	    c = DriverManager.getConnection(url);
    	Statement statement=c.createStatement();
		statement.executeUpdate(update);
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

//Ausgabe der DB
public void showdb(){
	String url="jdbc:sqlite:database.db";
    try {
    	Connection c = null;		    
	    c = DriverManager.getConnection(url);
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
//Erzeugen der DB
public void connectdb(){
	String driver="org.sqlite.JDBC";
	String url="jdbc:sqlite:database.db";
	 Connection c = null;		    
	    	
		    //Treiber laden
		    try {
		    Class.forName(driver);
		      
		    c = DriverManager.getConnection(url);
		    Statement statement= null;
		    statement = c.createStatement();
//		    String create="CREATE TABLE position(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
//		    			+ "Spielstein varChar(30),"
//		    			+ "E1 INT, X1 INT, Y1 INT, "
//		    			+ "E2 INT, X2 INT, Y2 INT) ";
//		    statement.executeQuery(create);
		    		    		  		    
		    } 
		    catch ( Exception e ) {
		    	System.err.println(e.getMessage());
		        System.exit(0);
		    }
		    
		    
		    
	}
}


