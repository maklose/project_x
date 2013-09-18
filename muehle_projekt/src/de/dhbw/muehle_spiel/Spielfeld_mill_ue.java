package de.dhbw.muehle_spiel;

import java.awt.*;

import javax.swing.*;



public class Spielfeld_mill_ue {

	public Spielfeld_mill_ue() {
		
			}
	
	

	public static void main(String[] args) {
		
		final JFrame frame = new JFrame ("Spielfeld");
		frame.setLocation(100, 100);
		frame.setSize(800, 600);
		frame.setVisible(true);
		
		
		
		GridLayout gl = new GridLayout (); 
		gl.setColumns(7);
		gl.setRows(7);
		frame.getContentPane().setLayout( gl );
		
	
		
		int rows=gl.getRows();
		int colums=gl.getColumns();
		
		for (int row=1;row<=rows;row++){
			for (int colum=1;colum<=colums;colum++){
				final JButton btn = new JButton("Feld"+row+"-"+colum);
				frame.add(btn);
			}
		}
		
		
	}
	

}
