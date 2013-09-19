package de.dhbw.muehle_spiel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	//Verbindung zur Datenbank
	Connection c;
	connection=DriverManager.getConnection("jdbc:derby:MyDB");
	
}

