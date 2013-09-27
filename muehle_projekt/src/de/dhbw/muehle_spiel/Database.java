package de.dhbw.muehle_spiel;


import java.sql.*;
import java.io.*;


public class Database {
	public static void main(String[]args){
		String driver="com.mysql.jdbc.Driver";
		String url= "jdbc:mysql://localhost/Namen";
		String user = "root";
		String password="database";
		
		try{
			Class.forName(driver);
			
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.exit(0);
		}
	
		Connection conn = null;
		Statement hans= null;
		try{
			conn=DriverManager.getConnection("jdbc:mysql://localhost/Namen?user=root&password=database");
			hans=conn.createStatement();
			String query="SELECT * FROM Biersorten";
			ResultSet result= hans.executeQuery(query);
			if(result!=null){
				ResultSetMetaData rsmd=result.getMetaData();
				for(int i=1; i<=rsmd.getColumnCount();i++){
					System.out.print(rsmd.getColumnName(i));
					System.out.print((char)9);
					
				}
				System.out.println();
				
				result.beforeFirst();
				while(result.next()){
					for(int column=1; column<=rsmd.getColumnCount();column++){
						System.out.print(result.getObject(column));
						System.out.print((char)9);
						System.out.println();
					}
				}	result.close();
			}
		
		}
		catch(SQLException)
	
	}
	
	
}

