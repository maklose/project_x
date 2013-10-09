package de.dhbw.muehle_spiel;


import java.sql.*;
import java.io.*;


public class Database {
	public Database(){
		
	
	}
	
	public static void main(String[]args){

		    
	}
//Löschen der Datenbank	
public void deletedb(Statement statement){
	String delete=("DROP DATABASE database");
	try {
		statement.executeQuery(delete);
	} catch (SQLException e) {		
		e.printStackTrace();
	}
	
	
}
//Übergabe der Position an DB
public void valuetrans(Statement statement){
	String update=("INSERT INTO position VALUES('Weiß',5,5,5,6,6,6)");
    try {
		statement.executeUpdate(update);
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
//Erzeugen der DB
public void createdb(){
	String driver="org.sqlite.JDBC";
	String url="jdbc:sqlite:database.db";
		
		    //Treiber laden
		    try {
		    Class.forName(driver);
		      
		 		    
		    Connection c = null;		    
		    c = DriverManager.getConnection(url);
		    Statement statement= null;
		    statement = c.createStatement();
		    
		    String create="CREATE TABLE position (ID INT AUTO_INCREMENT,"
		    			+ "Spielstein varChar(30),"
		    			+ "E1 INT, X1 INT, Y1 INT, "
		    			+ "E2 INT, X2 INT, Y2 INT) ";
		    statement.executeQuery(create);
		    		  		    
		    } 
		    catch ( Exception e ) {
		    	System.err.println(e.getMessage());
		        System.exit(0);
		    }
		    
		    
	}
}


