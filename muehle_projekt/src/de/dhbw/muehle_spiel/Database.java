package de.dhbw.muehle_spiel;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;


public class Database {
	String database= "MyDB";
	
	Connection c;
	
	public void start() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		if (new File(database).exists()) {
			c = DriverManager.getConnection("jdbc:derby:" + database);
		} else {
			c = DriverManager.getConnection("jdbc:derby:" + database + ";create=true");
			filldb(c);
		}
	}
	
	public static void filldb(Connection c) throws SQLException{
		Statement s = (Statement) c.createStatement();
		s.executeUpdate("CREATE TABLE spielzug " + "(spielstein varchar(20), e1 int, x1 int, y1 int, e2 int, x2 int, y2 int)");
		s.executeUpdate("INSERT INTO spielzug"+ "VALUES ('weiﬂ1', 1,2,3,4,5,6)");
	}


}

