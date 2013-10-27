package de.dhbw.muehle_spiel;


import java.sql.*;
import java.util.Vector;


public class Database {
	
	
	//Treiber laden
	String url="jdbc:sqlite:database.db";
	Connection c;
	String driver="org.sqlite.JDBC";
	Statement statement=null;
	
	public Database(){
		try {
			
			Class.forName(driver);
			c= DriverManager.getConnection(url);
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}

//Speichern der Anzahl der Spielzüge
public void anzahlzuegespeichern(Spieler spieler){
	
	int zuege=spieler.getAnzahlZuege();
	//String sql=("INSERT INTO highscores(Spieler, Züge) VALUES('"+Spielername+"','"+zuege+"')");
	try {
		statement=c.createStatement();
		//statement.executeUpdate(sql);
		  	
		
		
	}catch (SQLException e) {
		
		e.printStackTrace();
	}
	
}

//Befüllend er Tabelle highscore mit Beispielen
public void testmethode_h(){
	try {
    	statement=c.createStatement();	     
    	String create="INSERT INTO highscore (Spielername, Züge) VALUES('Peter',1)";
    	String create1="INSERT INTO highscore (Spielername, Züge) VALUES('Stefan',2)";
    	String create2="INSERT INTO highscore (Spielername, Züge) VALUES('Axel',3)";
    	String create3="INSERT INTO highscore (Spielername, Züge) VALUES('Lisa',3)";
    	String create4="INSERT INTO highscore (Spielername, Züge) VALUES('Jennifer',4)";
    	
	    statement.executeUpdate(create);
	    statement.executeUpdate(create1);
	    statement.executeUpdate(create2);
	    statement.executeUpdate(create3);
	    statement.executeUpdate(create4);
	    
	    System.out.println("Die Werte wurden übertragen");	    		  		    
	    } 
	catch ( Exception e ) {
	    	System.err.println(e.getMessage());
	        System.exit(0);
	}
}

//Speichern der ersten 10 Züge und Spielernamen in ein 2-dimensionales Array
public String[][] speichern_h(){
	String [][] highscore=new String[10][3];
	
		try{
			statement=c.createStatement();
			String ausgabe=("SELECT Züge, Spielername FROM highscore ORDER BY Züge ASC");
			ResultSet result=statement.executeQuery(ausgabe);
			
			for(int i=0; i<10; i++){
				
					if(result.next()==true){
					
					int züge_1=result.getInt("Züge");
					Integer züge_2=new Integer(züge_1);
					String zuege=züge_2.toString();
					
					String spielername=result.getString("Spielername");
					Integer p=new Integer(i+1);
					String f=p.toString();
					highscore[i][0]=f;
					highscore[i][1]=zuege;
					highscore[i][2]=spielername;
					}
				
								 
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	return highscore;
}

//Speichern der Tabelle protokoll in ein 2-dimensionales Array, abhängig von der übergebenen PartieID
public Vector speichern_p(int partie)
{	
	
	Vector protokoll = null;
	try{
		statement=c.createStatement();
		String ausgabe=("SELECT Spieler, E1,X1,Y1,E2,X2,Y2,muehle,GeloeschterStein FROM protokoll WHERE PartieID='"+partie+"'");
		ResultSet result=statement.executeQuery(ausgabe);
		int i=0;
		while(result.next()){						
							
			int zugID = result.getInt("ID");	    	  
	    	String Spieler=result.getString("Spieler");	    	  
	    	String E1=result.getString("E1");
	    	String X1=result.getString("X1");
	    	String Y1=result.getString("Y1");
	    	String E2=result.getString("E2");
	    	String X2=result.getString("X2");
	    	String Y2=result.getString("Y2");
	    	String vonKord=E1+","+X1+","+Y1;
	    	String nachKord=E2+","+X2+","+Y2;
	    	String muehle=result.getString("muehle");
	    	String GeloeschterStein=result.getString("GeloeschterStein");
	    	protokoll.add(i,Spieler);
	    	protokoll.add(i,vonKord);
	    	protokoll.add(i,nachKord);
	    	protokoll.add(i,muehle);
	    	protokoll.add(i,GeloeschterStein);
	    	i++;
	    	
								 
		}
		
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	return protokoll;
	
}



//Anzeigen der Tabelle protokoll
public void zeige_p()
{
	try {
    	statement=c.createStatement();
	    String ausgabe=("SELECT * FROM protokoll");
	    ResultSet result=statement.executeQuery(ausgabe);
	    while (result.next()) {
	    	  System.out.println();
	    	
	    	  
	    	  int zugID = result.getInt("ID");
	    	  
	    	  String Spieler=result.getString("Spieler");	    	  
	    	  String E1=result.getString("E1");
	    	  String X1=result.getString("X1");
	    	  String Y1=result.getString("Y1");
	    	  String E2=result.getString("E2");
	    	  String X2=result.getString("X2");
	    	  String Y2=result.getString("Y2");
	    	  String muehle=result.getString("muehle");
	    	  String GeloeschterStein=result.getString("GeloeschterStein");
	    	  
	    	  //Ausgabe
	    	  System.out.print("ID: ["+zugID+"]");
	    	  System.out.print(" Spieler: ["+Spieler+"]");	    	  
	    	  System.out.print(" E1: ["+E1+"]");
	    	  System.out.print(" X1: ["+X1+"]");
	    	  System.out.print(" Y1: ["+Y1+"]");
	    	  System.out.print(" E2: ["+E2+"]");
	    	  System.out.print(" X2: ["+X2+"]");
	    	  System.out.print(" Y2: ["+Y2+"]");
	    	  System.out.print(" Mühle: ["+muehle+"]");
	    	  System.out.print(" GelöschterStein: ["+GeloeschterStein+"]");  
	    	  
	    	  
	    		    	  
	    	}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}


//Übergabe der Protokolldaten an die Tabelle protokoll
public void zugspeichern(Bewegung bewegung,Spieler spieler,boolean muehle,Spielstein rspielstein){
	
		// Ablegen der Positionsindexe, Spielsteinfarbe in String-Variablen & 
		String vonEbene, vonX, vonY, nachEbene, nachX, nachY,farbe,lspielstein, lmuehle;
		if(rspielstein != null)
			lspielstein=rspielstein.toString();
		else
			lspielstein = "-";
		
		if(muehle)
			lmuehle = "richtig";
		else
			lmuehle = "falsch";
		
	    farbe=spieler.SpielsteinFarbeAsString();
	    if(bewegung.getVon() != null)
	    {
			vonEbene = bewegung.getVon().getEbene().toString();
			vonX = bewegung.getVon().getX().toString();
			vonY = bewegung.getVon().getY().toString();
	    }
	    else
	    {
	    	vonEbene = "-";
	    	vonX = "-";
	    	vonY = "-";
	    	
	    }
		nachEbene= bewegung.getNach().getEbene().toString();
		nachX = bewegung.getNach().getX().toString();
		nachY = bewegung.getNach().getY().toString();
		
	String update=("INSERT INTO protokoll (Spieler,E1,X1,Y1,E2,X2,Y2,muehle,GeloeschterStein)"
				+ " VALUES('"+farbe+"','"+vonEbene+"','"+vonX+"','"+vonY+"','"+nachEbene+"','"+nachX+"','"+nachY+"','"+lmuehle+"','"+lspielstein+"')");
						
	try {
		statement=c.createStatement();
    	statement.executeUpdate(update);
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

//Erzeugen einer Tabelle
public void erzeugetb(String tabelle){
		    if(tabelle=="protokoll")
		    {
		    	try {
				    statement=c.createStatement();	     
				    String create="CREATE TABLE protokoll(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				    			+ "PartieID INTEGER"
				    			+ "Spieler varChar(10),"
				    			+ "E1 varChar(10), X1 varChar(10), Y1 varChar(10), "
				    			+ "E2 varChar(10), X2 varChar(10), Y2 varChar(10), "
				    			+ "muehle varChar(10), GeloeschterStein varChar(80))";
				    statement.executeUpdate(create);
				    System.out.println("Tabelle protokoll wurde erzeugt");			    		  		    
			    } 
			    catch ( Exception e ) {
			    	System.err.println(e.getMessage());
			        System.exit(0);
			    }
		    	
		    }else if(tabelle=="highscore")
		    {
		    	try {
			    	statement=c.createStatement();	     
			    	String create="CREATE TABLE highscore (ID INTEGER PRIMARY KEY AUTOINCREMENT, Spielername varChar(20), Züge INT)";
		    	    statement.executeUpdate(create);
		    	    System.out.println("Tabelle highscore wurde erzeugt");		    		  		    
		    	    } 
		    	catch ( Exception e ) {
		    	    	System.err.println(e.getMessage());
		    	        System.exit(0);
		    	}
		    }
		    		    
		    
}

//Löschen einer Tabelle	
public void löschetb(String tabelle){
	
	String delete=("DROP TABLE '"+tabelle+"'");
	try {
		statement=c.createStatement();
		statement.executeUpdate(delete);
		System.out.println("Tabelle "+tabelle+" wurde gelöscht");	
	} catch (SQLException e ) {		
		e.printStackTrace();
	}
	
	
}
}


