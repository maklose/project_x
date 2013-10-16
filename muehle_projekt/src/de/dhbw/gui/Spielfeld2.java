package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import de.dhbw.gui.BestaetigungBeenden;

import net.miginfocom.swing.MigLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import de.dhbw.muehle_api.*;
import de.dhbw.muehle_spiel.Bewegung;
import de.dhbw.muehle_spiel.Pruefung;
import de.dhbw.muehle_spiel.Spieler;
import de.dhbw.muehle_spiel.Spielstein;
import de.dhbw.muehle_util.plugin.MyServiceLoader;

import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;

public class Spielfeld2 extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	//Die Buttons werden initialisiert
	TransparentButtonFeld btnNewButton_1, btnNewButton_4, btnNewButton_6, btnNewButton_9, 
							btnNewButton_10, btnNewButton_13, btnNewButton_15, btnNewButton_18,
							btnNewButton_19, btnNewButton_22, btnNewButton_23, btnNewButton_24,
							btnNewButton_26, btnNewButton_27, btnNewButton_28, btnNewButton_31,
							btnNewButton_32, btnNewButton_33, btnNewButton_37, btnNewButton_39,
							btnNewButton_41, btnNewButton_43, btnNewButton_46, btnNewButton_49;
	
	//Die Spieler werden initialisiert 
	Spieler Spieler1, Spieler2;
	
	JPanel panel;
	
	Spielstein[][][] SpielfeldArray;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmSpielBeenden;
	private JMenuItem mntmNeuesSpiel;
	private JMenuItem mntmAnleitung;
	private JPanel panel_1;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	
	
	int xPos, yPos;
	

	/**
	 * Hier wird das Spielfeld gestartet
	 */
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Spielfeld2 frame = new Spielfeld2();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Das JPanel wird erzeugt
	 * Die Buttons werden instanziiert und mit dem action listener verknüpft
	 */
	public Spielfeld2() 
	{
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 450);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Men\u00FC");
		menuBar.add(mnNewMenu);
		
		mntmSpielBeenden = new JMenuItem("Spiel beenden");
		mntmSpielBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BestaetigungBeenden frageBeenden = new BestaetigungBeenden();
				frageBeenden.setListener( new BestaetigungBeenden.BestatigungsListener() {
					
					@Override
					public void onOK() {
						
						Spielfeld2.this.dispose();
						//schließen
					}
					
					@Override
					public void onCancel() {
						//schließen?	
					}
				});
				frageBeenden.setVisible(true);
				
			}
		});
		mnNewMenu.add(mntmSpielBeenden);
		
		mntmNeuesSpiel = new JMenuItem("neues Spiel");
		mntmNeuesSpiel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame neuesSpiel = new Spielfeld2();
				neuesSpiel.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNeuesSpiel);
		
		mntmAnleitung = new JMenuItem("Anleitung");
		mntmAnleitung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog anleitung = new DialogAnleitung2();
				anleitung.show(true);
			}
		});
		mnNewMenu.add(mntmAnleitung);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/*
		 * Hier wird ein Array ertzuegt in dem alle Spielsteine auf dem Feld gespeichert werden
		 */
		SpielfeldArray = new Spielstein[3][3][3];
		for(int i = 0; i <= 2; i++)
        {
        	for(int j = 0; j <= 2; j++)
        	{
        		for(int k = 0; k <= 2; k++)
        		{
        			SpielfeldArray[i][j][k] = null;    			
        		}
        	}
        }
		
		//hier wird das haupt-Panel erzeugt inc. Hintergrundbild
		panel = new JPanel()
		{  
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) 
            {  
			
                Image spielfeld = Toolkit.getDefaultToolkit().getImage(  
                          Spielfeld2.class.getResource("/de/dhbw/images/Spielbrett_GUI.png"));  
                g.drawImage(spielfeld, 0, 0, this.getWidth(), this.getHeight(), this);  
                
                for(int i = 0; i <= 2; i++)
                {
                	for(int j = 0; j <= 2; j++)
                	{
                		for(int k = 0; k <= 2; k++)
                		{
                			if(SpielfeldArray[i][j][k] != null)
                			{
	                			Spielstein aktuellerStein = SpielfeldArray[i][j][k];
	                			if(aktuellerStein.FarbVergleich(ESpielsteinFarbe.WEISS))
	                			{
	                				/*
	                				 * hier läuft das ab wenn auf einem Spielfeld ein weißer stein steht 
	                				 */
	                				
	                				//das Bild für den weißen Stein wird geladen
	                				Image SteinWeiss = Toolkit.getDefaultToolkit().getImage(  
	                		                Spielfeld2.class.getResource("/de/dhbw/images/Spielstein hell.png"));
	                				
	                				//hier wird das Verhältnis festgelet, in dem die Steine zum Spielfeld stehen (größe)
	                				int breite = (int) ((int)spielfeld.getWidth(this)/10);
	                				int hoehe = (int) ((int)spielfeld.getHeight(this)/10);
	                				
	                				
	                				g.drawImage(SteinWeiss,  xPos , yPos , breite, hoehe, this);
	                				
	                				
	                			}
	                			else 
	                			{
	                				/*
	                				 * hier läuft das ab wenn auf einem Spielfeld ein weißer stein steht 
	                				 */
	                				Image SteinSchwarz = Toolkit.getDefaultToolkit().getImage(  
	                                        Spielfeld2.class.getResource("/de/dhbw/images/Spielstein dunkel.png"));
	                				
	                				//hier wird das Verhältnis festgelet, in dem die Steine zum Spielfeld stehen (größe)
	                				int breite = (int) ((int)spielfeld.getWidth(this)/10);
	                				int hoehe = (int) ((int)spielfeld.getHeight(this)/10);
	                				
	                				g.drawImage(SteinSchwarz,  xPos, yPos, breite, hoehe, this);
	                			}
	                			
                			}
                			else
                			{
                				
                			}
                			
                		}
                	}
                }
            }


			private void SteinZeichnen(ESpielsteinFarbe weiss) {
				// TODO Auto-generated method stub
				
			}  
		};  
		
		//Hintergrund
     	panel.setBackground(Color.LIGHT_GRAY);
		
		GridLayout gl = new GridLayout(7,7,15,15);

		//panel wird hinzugefügt
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(gl);
		
		//ab hier werden die ganzen knöpfe definiert und mit dem actionlistener verknüpft
		btnNewButton_1 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Drei));
		btnNewButton_1.addActionListener(this);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		btnNewButton_4 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Zwei, EPositionIndex.Drei));
		btnNewButton_4.addActionListener(this);
		panel.add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2);
		
		btnNewButton_6 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Drei));
		btnNewButton_6.addActionListener(this);
		panel.add(btnNewButton_6);
		
		JLabel label_1 = new JLabel("");
		panel.add(label_1);
		
		btnNewButton_9 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Drei));
		btnNewButton_9.addActionListener(this);
		panel.add(btnNewButton_9);
		
		JLabel label_2 = new JLabel("");
		panel.add(label_2);
		
		btnNewButton_10 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Zwei, EPositionIndex.Drei));
		btnNewButton_10.addActionListener(this);
		panel.add(btnNewButton_10);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3);
		
		btnNewButton_13 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Drei));
		btnNewButton_13.addActionListener(this);
		panel.add(btnNewButton_13);
		
		JLabel label_3 = new JLabel("");
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("");
		panel.add(label_5);
		
		btnNewButton_15 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Drei));
		btnNewButton_15.addActionListener(this);
		panel.add(btnNewButton_15);
		
		btnNewButton_18 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Zwei, EPositionIndex.Drei));
		btnNewButton_18.addActionListener(this);
		panel.add(btnNewButton_18);
		
		btnNewButton_19 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Drei));
		btnNewButton_19.addActionListener(this);
		panel.add(btnNewButton_19);
		
		JLabel label_6 = new JLabel("");
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("");
		panel.add(label_7);
		
		btnNewButton_22 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Zwei));
		btnNewButton_22.addActionListener(this);
		panel.add(btnNewButton_22);
		
		btnNewButton_23 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Zwei));
		btnNewButton_23.addActionListener(this);
		panel.add(btnNewButton_23);
		
		btnNewButton_24 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Zwei));
		btnNewButton_24.addActionListener(this);
		panel.add(btnNewButton_24);
		
		JLabel label_8 = new JLabel("");
		panel.add(label_8);
		
		btnNewButton_26 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Zwei));
		btnNewButton_26.addActionListener(this);
		panel.add(btnNewButton_26);
		
		btnNewButton_27 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Zwei));
		btnNewButton_27.addActionListener(this);
		panel.add(btnNewButton_27);
		
		btnNewButton_28 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Zwei));
		btnNewButton_28.addActionListener(this);
		panel.add(btnNewButton_28);
		
		JLabel label_9 = new JLabel("");
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("");
		panel.add(label_10);
		
		btnNewButton_31 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Eins));
		btnNewButton_31.addActionListener(this);
		panel.add(btnNewButton_31);
		
		btnNewButton_32 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Zwei, EPositionIndex.Eins));
		btnNewButton_32.addActionListener(this);
		panel.add(btnNewButton_32);
		
		btnNewButton_33 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Eins));
		btnNewButton_33.addActionListener(this);
		panel.add(btnNewButton_33);
		
		JLabel label_11 = new JLabel("");
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("");
		panel.add(label_12);
		
		JLabel label_13 = new JLabel("");
		panel.add(label_13);
		
		btnNewButton_37 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Eins));
		btnNewButton_37.addActionListener(this);
		panel.add(btnNewButton_37);
		
		JLabel label_14 = new JLabel("");
		panel.add(label_14);
		
		btnNewButton_39 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Zwei, EPositionIndex.Eins));
		btnNewButton_39.addActionListener(this);
		panel.add(btnNewButton_39);
		
		JLabel label_15 = new JLabel("");
		panel.add(label_15);
		
		btnNewButton_41 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Eins));
		btnNewButton_41.addActionListener(this);
		panel.add(btnNewButton_41);
		
		JLabel label_16 = new JLabel("");
		panel.add(label_16);
		
		btnNewButton_43 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Eins));
		btnNewButton_43.addActionListener(this);
		panel.add(btnNewButton_43);
		
		JLabel label_17 = new JLabel("");
		panel.add(label_17);
		
		JLabel label_18 = new JLabel("");
		panel.add(label_18);
		
		btnNewButton_46 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Zwei, EPositionIndex.Eins));
		btnNewButton_46.addActionListener(this);
		panel.add(btnNewButton_46);
		
		JLabel label_19 = new JLabel("");
		panel.add(label_19);
		
		JLabel label_20 = new JLabel("");
		panel.add(label_20);
		
		btnNewButton_49 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Eins));
		btnNewButton_49.addActionListener(this);
		panel.add(btnNewButton_49);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		
		lblNewLabel_4 = new JLabel("New label");
		panel_1.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("New label");
		panel_1.add(lblNewLabel_5);
		
		
		/*
		 * Ab hier werden die Spieler erzuegt
		 */
		Spieler1 = new Spieler(ESpielsteinFarbe.WEISS);
		Spieler2 = new Spieler(ESpielsteinFarbe.SCHWARZ);
		
		 
		
	}

	
	/*
	 * Hier überprüft der actionlistener welcher Knopf 
	 *gedrückt wurde und gibt diesen dann an die methode aktion weiter
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object obj = e.getSource();
		
		if(obj.equals(this.btnNewButton_1))
		{
			this.aktion(btnNewButton_1);
		}
		else if(obj.equals(this.btnNewButton_4))
		{
			this.aktion(btnNewButton_4);
		}
		else if(obj.equals(this.btnNewButton_6))
		{
			this.aktion(btnNewButton_6);
		}
		else if(obj.equals(this.btnNewButton_9))
		{
			this.aktion(btnNewButton_9);
		}
		else if(obj.equals(this.btnNewButton_10))
		{
			this.aktion(btnNewButton_10);
		}
		else if(obj.equals(this.btnNewButton_13))
		{
			this.aktion(btnNewButton_13);
		}
		else if(obj.equals(this.btnNewButton_15))
		{
			this.aktion(btnNewButton_15);
		}
		else if(obj.equals(this.btnNewButton_18))
		{
			this.aktion(btnNewButton_18);
		}
		else if(obj.equals(this.btnNewButton_19))
		{
			this.aktion(btnNewButton_19);
		}
		else if(obj.equals(this.btnNewButton_22))
		{
			this.aktion(btnNewButton_22);
		}
		else if(obj.equals(this.btnNewButton_23))
		{
			this.aktion(btnNewButton_23);
		}
		else if(obj.equals(this.btnNewButton_24))
		{
			this.aktion(btnNewButton_24);
		}
		else if(obj.equals(this.btnNewButton_26))
		{
			this.aktion(btnNewButton_26);
		}
		else if(obj.equals(this.btnNewButton_27))
		{
			this.aktion(btnNewButton_27);
		}
		else if(obj.equals(this.btnNewButton_28))
		{
			this.aktion(btnNewButton_28);
		}
		else if(obj.equals(this.btnNewButton_31))
		{
			this.aktion(btnNewButton_31);
		}
		else if(obj.equals(this.btnNewButton_32))
		{
			this.aktion(btnNewButton_32);
		}
		else if(obj.equals(this.btnNewButton_33))
		{
			this.aktion(btnNewButton_33);
		}
		else if(obj.equals(this.btnNewButton_37))
		{
			this.aktion(btnNewButton_37);
		}
		else if(obj.equals(this.btnNewButton_39))
		{
			this.aktion(btnNewButton_39);
		}
		else if(obj.equals(this.btnNewButton_41))
		{
			this.aktion(btnNewButton_41);
		}
		else if(obj.equals(this.btnNewButton_43))
		{
			this.aktion(btnNewButton_43);
		}
		else if(obj.equals(this.btnNewButton_46))
		{
			this.aktion(btnNewButton_46);
		}
		else if(obj.equals(this.btnNewButton_49))
		{
			this.aktion(btnNewButton_49);
		}
	}
	
	/*
	 * wenn ein Knopf gedrückt wurde wird diese aktion ausgeführt
	 */
	
	public void aktion(TransparentButtonFeld lButton)
	{
		//Position von dem Button ermitteln der gedrückt wurde
		Position PositionGeklickt = lButton.getPosition();
		Point pos = lButton.getLocation();
		
		//Differenz von Buttonmitte zu der Position wo der Knopf gezeichnet werden muss (dynamisch)
		int xPosDif = panel.getWidth() / 20;
		int yPosDif = panel.getHeight() / 20;
		
		//neue reale Position für den neuen Stein festlegen = Position des Buttons - Different 
		xPos = (int)pos.getX() - xPosDif;
		yPos = (int)pos.getY() - yPosDif;
		
		
		System.out.println(pos);
		System.out.println(panel.getWidth() / 20);
		
		//neue Bewegung erstellen
		Bewegung neueBewegung = new Bewegung(null, PositionGeklickt);
		
		
		this.SpielsteinSetzen(neueBewegung, xPos, yPos);
		
		this.repaint();
		
		
	}

	
	public void SpielsteinSetzen(Bewegung neueBewegung, int xPos, int yPos)
	{
		System.out.println(neueBewegung);
		Spieler1.setzeSpielstein(neueBewegung.getNach(), xPos, yPos);
		Spielstein neuerStein = Spieler1.getSpielstein(1);
		
		int e, x, y;
		e = posIndexUmrechnen(neueBewegung.getNach().getEbene());
		x = posIndexUmrechnen(neueBewegung.getNach().getX());
		y = posIndexUmrechnen(neueBewegung.getNach().getY());
		SpielfeldArray[e][x][y] = neuerStein;
	}
	
	public int posIndexUmrechnen(EPositionIndex lPosition)
	{
		if(lPosition.equals(EPositionIndex.Eins))
			return 0;
		else if(lPosition.equals(EPositionIndex.Zwei))
			return 1;
		else if(lPosition.equals(EPositionIndex.Drei))
			return 2;
		else 
			return 99;
	}

	

		
}


