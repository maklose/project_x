package de.dhbw.muehle_spiel;


import java.sql.*;
import java.io.*;


public class Database {
	public static void main(String[]args){
	String driver="org.sqlite.JDBC";
	String url="jdbc:sqlite:database.db";
		
		    //Treiber laden
		    try {
		    Class.forName(driver);
		    		    
		 		    
		    Connection c = null;		    
		    c = DriverManager.getConnection(url);
		    Statement statement= null;
		    statement = c.createStatement();
		    String create="CREATE TABLE position (ID INT primary key AUTO_INCREMENT, "
		    			+ "Spielstein varChar(30),"
		    			+ "E1 INT, X1 INT, Y1 INT, "
		    			+ "E2 INT, X2 INT, Y2 INT) ";
		    statement.executeQuery(create);
		    String update=("INSERT INTO position VALUES('Weiﬂ',5,5,5,6,6,6)");
		    statement.executeUpdate(update);
		    String ausgabe=("SELECT * FROM position");
		    statement.executeQuery(ausgabe);
		    } 
		    catch ( Exception e ) {
		    	System.err.println(e.getMessage());
		        System.exit(0);
		    }
		    
		    
	}




}