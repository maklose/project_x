package de.dhbw.muehle_spiel;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private final String database= "MyDB";
	
	//Verbindung zur Datenbank
	Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
	Connection c = DriverManager.getConnection("jdbc:derby:"+database+"create=true");
	Statement s = (Statement) c.createStatement();
	s.executeUpdate("CREATE TABLE spielzug " + "(spielstein varchar(20), e1 int, x1 int, y1 int, e2 int, x2 int, y2 int)");
	s.executeUpdate("INSERT INTO spielzug (spielstein, e1, x1, y1, e2, x2, y2)" + "VALUES ('weiﬂ1', 1,2,3,4,5,6)");
}

