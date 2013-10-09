package de.dhbw.muehle_spiel;


import java.sql.*;
import java.io.*;


public class Database {
	public static void main(String[]args){
	String driver="org.sqlite.JDBC";
	String url="jdbc:sqlite:test.db";
		
		    //Treiber laden
		    try {
		      Class.forName(driver);
		      
		    } catch ( Exception e ) {
		    	System.err.println(e.getMessage());
		        System.exit(0);
		    }
		    System.out.println("Opened database successfully");
		    
		    Connection c = null;
		    Statement statement= null;
		    c = DriverManager.getConnection(url);
	}


public void createdb(){
	
}

public void valuetrans(Position von, Position nach, ){
	
}

}