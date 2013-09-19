package de.dhbw.muehle_spiel;

import java.sql.Connection;

public class Database {
	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	//Verbindung zur Datenbank
	Connection connection;
	connection=DriverManager.getConnection("jdbc:derby:MyDB");
}

