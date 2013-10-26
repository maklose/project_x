package de.dhbw.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import de.dhbw.gui.BestaetigungBeenden;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import de.dhbw.muehle_api.*;
import de.dhbw.muehle_api.strategy.IBewegung;
import de.dhbw.muehle_api.strategy.ISpielzug;
import de.dhbw.muehle_api.strategy.StrategieException;
import de.dhbw.muehle_spiel.Bewegung;
import de.dhbw.muehle_spiel.EPhase;
import de.dhbw.muehle_spiel.Pruefung;
import de.dhbw.muehle_spiel.Spieler;
import de.dhbw.muehle_spiel.Spielstein;
import de.dhbw.strategy.Bewertung;
import de.dhbw.strategy.Strategie;

import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import net.miginfocom.swing.MigLayout;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Spielfeld extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static JPanel contentPane;
	
	//Die Buttons werden initialisiert
	static TransparentButtonFeld btnNewButton_1, btnNewButton_2, btnNewButton_3, btnNewButton_4, btnNewButton_5, btnNewButton_6, btnNewButton_7, btnNewButton_8,
									btnNewButton_9, btnNewButton_10, btnNewButton_11, btnNewButton_12, btnNewButton_13, btnNewButton_14,
									btnNewButton_15, btnNewButton_16, btnNewButton_17, btnNewButton_18, btnNewButton_19, btnNewButton_20,
									btnNewButton_21, btnNewButton_22, btnNewButton_23, btnNewButton_24;

	//Die Spieler werden initialisiert 
	Spieler Spieler1;
	Spieler Spieler2;

	
	static JPanel panel;
	
	//Array mit den Steinen die aktuell auf dem Spielfeld stehen an der Position im Array
	Spielstein[][][] SpielfeldArray = new Spielstein[3][3][3];
	
	//Array mit den Indizes der Steine die man aktuell auf dem Spielfeld hat
	int[] Indizes = new int[9];
	
	private JMenuBar menuBar;
	private JMenu mnNewMenu1, mnNewMenu2, mnNewMenu3, mnNewMenu11, mnNewMenu22;
	private JPanel panel_1;

	
	//xPos und yPos sind die reellen Positionen auf dem Feld, 
	//anzahlRunden zählt die Anzahl der Runden
	static int xPos;

	static int yPos;

	int anzahlRunden;
	int zaehler1 = 1;
	int zaehler2 = 0;
	
	static Font gFont = new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 30);

	
	private boolean hatMuehle = false;
	
	private Spieler aktuellerSpieler, passiverSpieler;
	
	private Bewegung neueBewegung;
	
	//Array mit den aktuellen Steinen auf dem Feld
	List<ISpielstein> gSteine = new ArrayList<ISpielstein>();
	
	//static Database db = new Database();
	
	private boolean SteinKannGeloeschtWerden = true;
	
	//Zaehler für die anzahl der Fenster schließen abfragen damit die nicht doppelt geöffnet werden
	private int anzahlFensterSchließen;
	

	
	//Mode des Spiels 1 = mensch gg mensch; 2 = mensch gg pc
	private int gMode = 1;
	
	private int gTheme = 1;
	
	private String nameSpieler1; //= "default Name Spieler 1";
	private String nameSpieler2; //= "default Name Spieler 2";
	private String textMuehle = " hat eine Mühle!";
	private String textNeuesSpiel = "Neues Spiel - Stefan beginnt!";
	private String textRunde2 = "Ab jetzt: Steine ziehen!";
	private static String textSpielName = "Mühle - Das Brettspiel für Groß und Klein";
	private String textGewonnen = " hat gewonnen!!!";
	private String textDarfSpringen = " darf jetzt springen!";
	
	private int meldungsZeit = 1;
	private int wichtigeMeldungsZeit = 2;
	
	//Alte Position wenn man zieht und die variable ob der aktuelle klick schon das neue setzen ist
	Position altePosition; 
	boolean hatAltePosition;
	
	//die linken oberen Positionen der drei Felder auf der rechten Seite
	int xPosOben = 12;				
	int yPosOben = 48;				
	int xPosMitte = 12;				
	int yPosMitte = 226;
	int xPosUnten = 12;				
	int yPosUnten = 404;
	
	//variablen die mitzählen wie viel Steine von dem jeweiligen Spieler wegen Mühlen vom Spielfeld entfernt wurden
	int entfernteSteineWeiss, entfernteSteineSchwarz = 0;
	
	//Wenn man auf einen Stein drückt um ihn zu verschieben, wird dieser Stein in dieser Variable gespeichert
	Spielstein ausgewaehlterStein;	
	
	//Variable die mit true anzeigt ob das Spiel beendet ist
	private boolean SpielBeendet = false;
	
	//neue Instanz der Prüfungsklasse
	Pruefung pruef = new Pruefung();
	
	//neue Instanz der Strategie, falls man gegen den PC spielen möchte
	Strategie strategie1, strategie2;
	
	//Liste mit allen Knöpfen auf dem Feld
	List<TransparentButtonFeld> gButtons = new ArrayList<TransparentButtonFeld>();
	
	//neue Instanz der Prüfung ZUM TESTEN!
	Bewertung bewertung = new Bewertung();
	
	//Schwierigkeitsstufe des Spiels
	private int gSchwierigkeit;
	
	//Größe des Meldungsfensters
	private static int x = 600;
	private static int y = 100;
	private JLabel lblNameSpieler;
	private JLabel lblAktuellerSpieler;
	private JLabel lblRunde;
	private JLabel lblNameSpieler_1;
	private JLabel label_14;
	
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
					final Spielfeld frame = new Spielfeld("Stefan", "Georg", 1, 5, 1);
					frame.addWindowListener(new WindowAdapter() {
											public void windowClosing(WindowEvent evt) {
											Spielfeld.exitForm(evt, frame, frame.getAnzahlFensterSchließen());}});
					frame.setVisible(true);
					 this.neueMeldung(frame.wichtigeMeldungsZeit, frame.textNeuesSpiel, frame);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			} 

			private void neueMeldung(final int sekunden, final String meldung, Spielfeld frame) 
			{
				//hier wird die Position festgelegt wo die meldung erscheinen soll
				Point pos = Spielfeld.panel.getLocationOnScreen();
				final int xPos = (int)(pos.getX() + 20);
				final int yPos = (int)pos.getY()+200;
				new Thread() 
				{
				      { 
				    	  start(); 
				      } 
				      public void run() 
				      {
				        try 
				        { 
				        	Anweisung1 probe = new Anweisung1(meldung, xPos, yPos, 600, 100, new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 30));
							probe.setAlwaysOnTop(true);
							probe.setVisible(true);
				        	sleep(sekunden * 1000);
				        	probe.setVisible(false);
				        }
				        catch ( InterruptedException e ) { e.printStackTrace(); }
				      } 
				};
				
			}
		});
	}

	static void exitForm(WindowEvent evt, final Spielfeld frame, int Anzahl) 
	{
		if(Anzahl < 1)
		{
			final BestaetigungBeenden frageBeenden = new BestaetigungBeenden(300,200);
			frageBeenden.setVisible(true);
			
			frageBeenden.setAlwaysOnTop(true);
			frageBeenden.setListener( new BestaetigungBeenden.BestatigungsListener() {
				
				@Override
				public void onOK() {
					
					frame.dispose();
					frageBeenden.dispose();
	//				db.löschetb("protokoll");
					
					
				}
				@Override
				public void onCancel() {
						
				}
				
			});
			frageBeenden.setVisible(true);
			frame.setAnzahlFensterSchließen(frame.getAnzahlFensterSchließen()+1);
		
	//		db.löschetb("protokoll");
		return;
		}
       
    }
	
	/**
	 * Das JPanel wird erzeugt
	 * Die Buttons werden instanziiert und mit dem action listener verknüpft
	 * @throws StrategieException 
	 */
	public Spielfeld(String Spieler1Name, String Spieler2Name, int lmode, int lSchwierigkeit, final int theme) throws StrategieException 
	{
		//Name der in dem Fenster angezeigt wird
		super(textSpielName);

		nameSpieler1 = Spieler1Name;
		nameSpieler2 = Spieler2Name;
		gMode = lmode;
		gSchwierigkeit = lSchwierigkeit;
		gTheme = theme;

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		Spieler1 = new Spieler(ESpielsteinFarbe.WEISS, nameSpieler1);
		switch(gMode)
		{
			case 1 : Spieler2 = new Spieler(ESpielsteinFarbe.SCHWARZ, nameSpieler2); break;
			case 2 : Spieler2 = new Spieler(ESpielsteinFarbe.SCHWARZ, nameSpieler2); strategie1 = new Strategie(); strategie1.startePartie(ESpielsteinFarbe.SCHWARZ); break;
			case 3 : Spieler1 = new Spieler(ESpielsteinFarbe.WEISS, nameSpieler2); Spieler2 = new Spieler(ESpielsteinFarbe.SCHWARZ, nameSpieler2); 
						strategie1 = new Strategie(); strategie2 = new Strategie(); 
						strategie1.startePartie(ESpielsteinFarbe.WEISS); strategie2.startePartie(ESpielsteinFarbe.SCHWARZ);break;
		}
		
		//Fenster fixieren
		setResizable(false);
		
		setBounds(20,20,890,700);		//20,20,900,700
		
		//das Bild für den weißen und schwarzen Stein wird geladen
		final Image SteinWeiss = Toolkit.getDefaultToolkit().getImage(  
                Spielfeld.class.getResource("/de/dhbw/images/Spielstein hell.png"));
		final Image SteinSchwarz = Toolkit.getDefaultToolkit().getImage(  
                Spielfeld.class.getResource("/de/dhbw/images/Spielstein dunkel.png"));
		
		final Image transparentSteinWeiss = Toolkit.getDefaultToolkit().getImage(  
                Spielfeld.class.getResource("/de/dhbw/images/transparent Spielstein hell.png"));
		final Image transparentSteinSchwarz = Toolkit.getDefaultToolkit().getImage(  
                Spielfeld.class.getResource("/de/dhbw/images/transparent Spielstein dunkel.png"));
		
		
		menuBar = new JMenuBar();
		
		menuBar.setPreferredSize(new Dimension(100, 40));
		setJMenuBar(menuBar);
		
		mnNewMenu1 = new JMenu("Neues Spiel");
		
		mnNewMenu1.setFont(new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 25));
		mnNewMenu1.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {
				dispose();
//				JFrame neuesSpiel = null;
				try 
				{
					final Spielfeld frame = new Spielfeld("Stefan", "Georg", 1, 5, 1);
					frame.addWindowListener(new WindowAdapter() {
											public void windowClosing(WindowEvent evt) {
											Spielfeld.exitForm(evt, frame, frame.getAnzahlFensterSchließen());}});
					frame.setVisible(true);
					Spielfeld.neueMeldung(frame.wichtigeMeldungsZeit, frame.textNeuesSpiel);
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
				
				
			}
			
			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	
		menuBar.add(mnNewMenu1);
		
		mnNewMenu11 = new JMenu("");
		mnNewMenu11.setPreferredSize(new Dimension(15, ImageObserver.HEIGHT));
		menuBar.add(mnNewMenu11);
		
		mnNewMenu2 = new JMenu("Spiel Beenden");
		mnNewMenu2.setFont(new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 25));
		mnNewMenu2.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				final BestaetigungBeenden frageBeenden = new BestaetigungBeenden(300,200);
				frageBeenden.setVisible(true);
				frageBeenden.setAlwaysOnTop(true);
				frageBeenden.setListener( new BestaetigungBeenden.BestatigungsListener() {
					
					@Override
					public void onOK() {
						
						Spielfeld.this.dispose();
						frageBeenden.dispose();
//						db.löschetb("protokoll");
						
					}
					@Override
					public void onCancel() {
							
					}
				});
				frageBeenden.setVisible(true);
			}
			
			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menuBar.add(mnNewMenu2);
		
		
		mnNewMenu22 = new JMenu("");
		mnNewMenu22.setPreferredSize(new Dimension(15, ImageObserver.HEIGHT));
		menuBar.add(mnNewMenu22);
		
		
		mnNewMenu3 = new JMenu("Hilfe");
		mnNewMenu3.setFont(new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 25));
		mnNewMenu3.addMenuListener(new MenuListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void menuSelected(MenuEvent e) {
					JDialog anleitung = new Anleitung();
					anleitung.show(true);
					anleitung.setAlwaysOnTop(true);
			}
			
			@Override
			public void menuDeselected(MenuEvent e) 
			{
				
				
			}
			
			@Override
			public void menuCanceled(MenuEvent e) 
			{
				
				
			}
		});
		menuBar.add(mnNewMenu3);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		
		
		//hier wird das haupt-Panel erzeugt inc. Hintergrundbild
		panel = new JPanel()
		{  
            /**
			 * 
			 */
			
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) 
            {
				Image spielfeld = null;
				
				switch(gTheme)
				{
					case 1: spielfeld = Toolkit.getDefaultToolkit().getImage(  
	                          Spielfeld.class.getResource("/de/dhbw/images/Spielbrett_GUIlinks.png")); break;
					case 2: spielfeld = Toolkit.getDefaultToolkit().getImage(  
	                          Spielfeld.class.getResource("/de/dhbw/images/Spielbrett_Frühling_GUIlinks.png")); break;
					case 3: spielfeld = Toolkit.getDefaultToolkit().getImage(  
	                          Spielfeld.class.getResource("/de/dhbw/images/Spielbrett_GUIlinks.png")); break;
					case 4: spielfeld = Toolkit.getDefaultToolkit().getImage(  
	                          Spielfeld.class.getResource("/de/dhbw/images/Spielbrett_GUIlinks.png")); break;
					case 5: spielfeld = Toolkit.getDefaultToolkit().getImage(  
	                          Spielfeld.class.getResource("/de/dhbw/images/Spielbrett_GUIlinks.png")); break;
				}
				
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
	                			
	                			int xPosi = aktuellerStein.getxPos();
                				int yPosi = aktuellerStein.getyPos();
                				
                				//hier wird das Verhältnis festgelet, in dem die Steine zum Spielfeld stehen (größe)
                				int breite = (int) ((int)spielfeld.getWidth(this)/6);
                				int hoehe = (int) ((int)spielfeld.getHeight(this)/6);
                				
                				
                				if(aktuellerStein != ausgewaehlterStein)
                				{	
		                			if(aktuellerStein.FarbVergleich(ESpielsteinFarbe.WEISS))
		                			{
		                				/*
		                				 * hier läuft das ab wenn auf einem Spielfeld ein weißer stein steht 
		                				 */
		                				
		                				if(!hatAltePosition && !hatMuehle)
		                					g.drawImage(SteinWeiss,  xPosi , yPosi , breite, hoehe, this);
		                				else
		                				{
		                					if(hatMuehle && aktuellerSpieler.equals(Spieler2) && 
		                							(!pruef.checkInMuehle(aktuellerStein.getIndex(), Spieler1.Steine) || alleGegnerSteineInMühle(Spieler1)))
		                						g.drawImage(SteinWeiss,  xPosi , yPosi , breite+6, hoehe+6, this);
		                					else
		                						g.drawImage(transparentSteinWeiss,  xPosi , yPosi , breite, hoehe, this);
		                				}
		                			}
		                			else 
		                			{
		                				/*
		                				 * hier läuft das ab wenn auf einem Spielfeld ein weißer stein steht 
		                				 */
		                				
		                				if(!hatAltePosition && !hatMuehle)
		                					g.drawImage(SteinSchwarz,  xPosi , yPosi , breite-4, hoehe-4, this);
		                				else
		                				{
		                					if(hatMuehle && aktuellerSpieler.equals(Spieler1) && 
		                							(!pruef.checkInMuehle(aktuellerStein.getIndex(), Spieler2.Steine) || alleGegnerSteineInMühle(Spieler2)))
		                						g.drawImage(SteinSchwarz,  xPosi , yPosi , breite+2, hoehe+2, this);
		                					else
		                						g.drawImage(transparentSteinSchwarz,  xPosi , yPosi , breite-4, hoehe-4, this);
		                				}
		                			}
                				}
	                			
                			}
                			else
                			{
                				
                			}
                			
                		}
                	}
                }
                //hier wird der ausgewählte Stein wieder ohne transparenz gezeichnet
                if(hatAltePosition)
                {
                	int e, x, y;
        			e = posIndexUmrechnen(altePosition.getEbene());
        			x = posIndexUmrechnen(altePosition.getX());
        			y = posIndexUmrechnen(altePosition.getY());
        			Spielstein aktuellerStein = SpielfeldArray[e][x][y];
        			
        			int xPosi = aktuellerStein.getxPos();
    				int yPosi = aktuellerStein.getyPos();
    				
    				//hier wird das Verhältnis festgelet, in dem die Steine zum Spielfeld stehen (größer)
    				int breite = (int) ((int)spielfeld.getWidth(this)/5);
    				int hoehe = (int) ((int)spielfeld.getHeight(this)/5);
    				
    				if(aktuellerStein.FarbVergleich(ESpielsteinFarbe.WEISS))
        				g.drawImage(SteinWeiss,  xPosi , yPosi , breite + 6, hoehe + 6, this);
    				else
    					g.drawImage(SteinSchwarz,  xPosi , yPosi , breite + 2, hoehe + 2, this);
        			
                	
                }
            }


		};
		
		//Layout des Panels
		panel.setLayout(new MigLayout("", "[5][66.15][25][66.15][25][60][2][60][2.00][60][25][66.15][25][66.15]", "[20][66.15px][35][66.15][37.48][60][5][60][2.2][60][37.48][66.15][40.79][66.15][25]"));
		
		
		//ab hier werden die ganzen knöpfe definiert und mit dem actionlistener verknüpft und in die Button Liste eingefügt
		btnNewButton_1 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Drei));
		btnNewButton_1.addActionListener(this);
		gButtons.add(btnNewButton_1);
		panel.add(btnNewButton_1, "cell 1 1,grow");
		
		btnNewButton_2 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Zwei, EPositionIndex.Drei));
		btnNewButton_2.addActionListener(this);
		gButtons.add(btnNewButton_2);
		panel.add(btnNewButton_2, "cell 7 1,grow");
		
		btnNewButton_3 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Drei));
		btnNewButton_3.addActionListener(this);
		gButtons.add(btnNewButton_3);
		panel.add(btnNewButton_3, "cell 13 1,grow");
		
		btnNewButton_4 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Drei));
		btnNewButton_4.addActionListener(this);
		gButtons.add(btnNewButton_4);
		panel.add(btnNewButton_4, "cell 3 3,grow");
		
		btnNewButton_5 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Zwei, EPositionIndex.Drei));
		btnNewButton_5.addActionListener(this);
		gButtons.add(btnNewButton_5);
		panel.add(btnNewButton_5, "cell 7 3,grow");
		
		btnNewButton_6 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Drei));
		btnNewButton_6.addActionListener(this);
		gButtons.add(btnNewButton_6);
		panel.add(btnNewButton_6, "cell 11 3,grow");
		
		btnNewButton_7 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Drei));
		btnNewButton_7.addActionListener(this);
		gButtons.add(btnNewButton_7);
		panel.add(btnNewButton_7, "cell 5 5,grow");

		btnNewButton_8 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Zwei, EPositionIndex.Drei));
		btnNewButton_8.addActionListener(this);
		gButtons.add(btnNewButton_8);
		panel.add(btnNewButton_8, "cell 7 5,grow");
		
		btnNewButton_9 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Drei));
		btnNewButton_9.addActionListener(this);
		gButtons.add(btnNewButton_9);
		panel.add(btnNewButton_9, "cell 9 5,grow");
		
		btnNewButton_10 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Zwei));
		btnNewButton_10.addActionListener(this);
		gButtons.add(btnNewButton_10);
		panel.add(btnNewButton_10, "cell 1 7,grow");
		
		btnNewButton_11 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Zwei));
		btnNewButton_11.addActionListener(this);
		gButtons.add(btnNewButton_11);
		panel.add(btnNewButton_11, "cell 3 7,grow");
		
		btnNewButton_12 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Zwei));
		btnNewButton_12.addActionListener(this);
		gButtons.add(btnNewButton_12);
		panel.add(btnNewButton_12, "cell 5 7,grow");
		
		btnNewButton_13 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Zwei));
		btnNewButton_13.addActionListener(this);
		gButtons.add(btnNewButton_13);
		panel.add(btnNewButton_13, "cell 9 7,grow");
		
		btnNewButton_14 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Zwei));
		btnNewButton_14.addActionListener(this);
		gButtons.add(btnNewButton_14);
		panel.add(btnNewButton_14, "cell 11 7,grow");
		
		btnNewButton_15 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Zwei));
		btnNewButton_15.addActionListener(this);
		gButtons.add(btnNewButton_15);
		panel.add(btnNewButton_15, "cell 13 7,grow");
			
		btnNewButton_16 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Eins));
		btnNewButton_16.addActionListener(this);
		gButtons.add(btnNewButton_16);
		panel.add(btnNewButton_16, "cell 5 9,grow");
		
		btnNewButton_17 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Zwei, EPositionIndex.Eins));
		btnNewButton_17.addActionListener(this);
		gButtons.add(btnNewButton_17);
		panel.add(btnNewButton_17, "cell 7 9,grow");
		
		btnNewButton_18 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Eins));
		btnNewButton_18.addActionListener(this);
		gButtons.add(btnNewButton_18);
		panel.add(btnNewButton_18, "cell 9 9,grow");
		
		btnNewButton_19 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Eins));
		btnNewButton_19.addActionListener(this);
		gButtons.add(btnNewButton_19);
		panel.add(btnNewButton_19, "cell 3 11,grow");
		
		btnNewButton_20 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Zwei, EPositionIndex.Eins));
		btnNewButton_20.addActionListener(this);
		gButtons.add(btnNewButton_20);
		panel.add(btnNewButton_20, "cell 7 11,grow");
		
		btnNewButton_21 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Eins));
		btnNewButton_21.addActionListener(this);
		gButtons.add(btnNewButton_21);
		panel.add(btnNewButton_21, "cell 11 11,grow");
		
		btnNewButton_22 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Eins));
		btnNewButton_22.addActionListener(this);
		gButtons.add(btnNewButton_22);
		panel.add(btnNewButton_22, "cell 1 13,grow");
		
		btnNewButton_23 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Zwei, EPositionIndex.Eins));
		btnNewButton_23.addActionListener(this);
		gButtons.add(btnNewButton_23);
		panel.add(btnNewButton_23, "cell 7 13,grow");
		
		btnNewButton_24 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Eins));
		btnNewButton_24.addActionListener(this);
		gButtons.add(btnNewButton_24);
		panel.add(btnNewButton_24, "cell 13 13,grow");

		//ab hier werden die Labels definiert die als Platzhalter zwischen den Buttons dienen
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel, "cell 3 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1, "cell 9 1,grow");
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2, "cell 11 1,grow");
	
		JLabel lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3, "cell 9 3,grow");
		
		JLabel label = new JLabel("");
		panel.add(label, "cell 5 1,grow");
		
		JLabel label_1 = new JLabel("");
		panel.add(label_1, "cell 1 3,grow");
		
		JLabel label_2 = new JLabel("");
		panel.add(label_2, "cell 5 3,grow");
		
		JLabel label_3 = new JLabel("");
		panel.add(label_3, "cell 13 3,grow");
		
		JLabel label_4 = new JLabel("");
		panel.add(label_4, "cell 1 5,grow");
		
		JLabel label_5 = new JLabel("");
		panel.add(label_5, "cell 3 5,grow");

		JLabel label_6 = new JLabel("");
		panel.add(label_6, "cell 11 5,grow");
		
		JLabel label_7 = new JLabel("");
		panel.add(label_7, "cell 13 5,grow");

		JLabel label_8 = new JLabel("");
		panel.add(label_8, "cell 7 7,grow");

		JLabel label_9 = new JLabel("");
		panel.add(label_9, "cell 1 9,grow");
		
		JLabel label_10 = new JLabel("");
		panel.add(label_10, "cell 3 9,grow");
		
		JLabel label_11 = new JLabel("");
		panel.add(label_11, "cell 11 9,grow");
		
		JLabel label_12 = new JLabel("");
		panel.add(label_12, "cell 13 9,grow");
		
		JLabel label_13 = new JLabel("");
		panel.add(label_13, "cell 1 11,grow");
		
		/*JLabel label_14 = new JLabel("");
		panel_1.add(label_14, "cell 1 0,grow");*/
		
		JLabel label_15 = new JLabel("");
		panel.add(label_15, "cell 9 11,grow");
		
		JLabel label_16 = new JLabel("");
		panel.add(label_16, "cell 13 11,grow");
		
		JLabel label_17 = new JLabel("");
		panel.add(label_17, "cell 3 13,grow");
		
		JLabel label_18 = new JLabel("");
		panel.add(label_18, "cell 5 13,grow");

		JLabel label_19 = new JLabel("");
		panel.add(label_19, "cell 9 13,grow");
		
		JLabel label_20 = new JLabel("");
		panel.add(label_20, "cell 11 13,grow");
		
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		
		
		//hier wird das Panel auf der rechten seite erzeugt
		panel_1 = new JPanel()
		{
			/**  
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			

			@Override
			public void paintComponent(Graphics p) 
			{				
				//font wird festgelegt
				p.setFont(new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 30));
				
				//Das Hintergrundbild wird geladen
				Image spielfeldRechts = null;
				
				switch(gTheme)
				{
					case 1: spielfeldRechts = Toolkit.getDefaultToolkit().getImage(  
	                          Spielfeld.class.getResource("/de/dhbw/images/Spielbrett_GUIrechts.png")); break;
					case 2: spielfeldRechts = Toolkit.getDefaultToolkit().getImage(  
	                          Spielfeld.class.getResource("/de/dhbw/images/Spielbrett_Frühling_GUIrechts.png")); break;
					case 3: spielfeldRechts = Toolkit.getDefaultToolkit().getImage(  
	                          Spielfeld.class.getResource("/de/dhbw/images/Spielbrett_GUIrechts.png")); break;
					case 4: spielfeldRechts = Toolkit.getDefaultToolkit().getImage(  
	                          Spielfeld.class.getResource("/de/dhbw/images/Spielbrett_GUIrechts.png")); break;
					case 5: spielfeldRechts = Toolkit.getDefaultToolkit().getImage(  
	                          Spielfeld.class.getResource("/de/dhbw/images/Spielbrett_GUIrechts.png")); break;
				} 
				  
				p.drawImage(spielfeldRechts, 0, 0, this.getWidth(), this.getHeight(), this);  
				
				//hier wird das Verhältnis festgelet, in dem die Steine zum Spielfeld stehen (größe)
				int breite = panel.getWidth()/8;
				int hoehe = panel.getWidth()/8;
				
				//Beschriftung der Felder
				
				p.drawString(nameSpieler1 + ": ", xPosOben+15, yPosOben+35);
				p.drawString(nameSpieler2 + ": ", xPosUnten+15, yPosUnten+42);
//				lblNameSpieler.setText("HALLOOOOOOOOOOOOO");
				
				//Zeichnet die noch vorhandenen Steine des Spielers
				for(int i = 0; i < (9-Spieler1.getAnzahlSteine()-entfernteSteineWeiss); i++)
				{
					p.drawImage(SteinWeiss,  xPosOben + (i*15) , yPosOben + 30 , breite, hoehe, this);
				}
				for(int i = 0; i < (9-Spieler2.getAnzahlSteine()-entfernteSteineSchwarz); i++)
				{
					p.drawImage(SteinSchwarz,  xPosUnten + (i*15) , yPosUnten + 37 , breite-3, hoehe-3, this);
				}
				
				//Zeichnet die gelöschten Steine vom Gegner
				for(int i = 0; i < entfernteSteineSchwarz; i++)
				{
					p.drawImage(SteinSchwarz,  xPosOben + (i*15) , yPosOben + 85 , breite-3, hoehe-3, this);
				}
				for(int i = 0; i < entfernteSteineWeiss; i++)
				{
					p.drawImage(SteinWeiss,  xPosUnten + (i*15) , yPosUnten + 92 , breite, hoehe, this);
				}
				
				//Zeichnet in das mittlere Feld einen Stein der aktuellen Farbe
				if(Spieler1.getAnzahlSteine()==0)
					p.drawImage(SteinWeiss,  xPosMitte + 40, yPosMitte + 10 , breite+50, hoehe+50, this);
				else
				{
					if(aktuellerSpieler == Spieler2)
						p.drawImage(SteinWeiss,  xPosMitte + 40, yPosMitte + 10 , breite+50, hoehe+50, this);
					else
						p.drawImage(SteinSchwarz,  xPosMitte + 40, yPosMitte + 10 , breite+43, hoehe+43, this);
				}
				//Rundenanzeige
				p.drawString("Runde " + (anzahlRunden+1), xPosMitte+15, yPosMitte+140);
			}
		};
		
		contentPane.setLayout(new MigLayout("", "[664.94px]0[300px]", "[677px]"));
		contentPane.add(panel, "cell 0 0,alignx left,aligny top");
		contentPane.add(panel_1, "cell 1 0,grow");
		panel_1.setLayout(new MigLayout("", "[][100px:100]", "[][20][][][][][][][][][][][][][][]"));
		
		
		
		lblNameSpieler = new JLabel("Name Spieler 1");
		panel_1.add(lblNameSpieler, "cell 1 1,grow");
		
		lblAktuellerSpieler = new JLabel("aktueller Spieler");
		panel_1.add(lblAktuellerSpieler, "cell 1 6, grow");
		
		lblRunde = new JLabel("Runde");
		panel_1.add(lblRunde, "cell 1 10, grow");
		
		lblNameSpieler_1 = new JLabel("Name Spieler 2");
		panel_1.add(lblNameSpieler_1, "cell 1 15, grow");
		
		
		/*if(gMode == 3)
		{
			new Thread() 
			{
				{
					start(); 
				}
				public void run()
				{
					try 
					{
						ISpielzug neuerZug1 = strategie1.bewegeStein(gSteine);
						IBewegung neueIBewegung1 = neuerZug1.bewegeSpielStein();
						Bewegung neueBewegung1 = new Bewegung(neueIBewegung1.altePosition(), neueIBewegung1.neuePosition());
						Spielfeld.bewegungInButtons(neueBewegung1);
						
						sleep(5000);
					} 
					catch (StrategieException | InterruptedException e) 
					{
						
						e.printStackTrace();
					}
				}
			};
		}*/
//		 db.erzeugetb("protokoll");

		
	}

	
	/*
	 * Hier überprüft der actionlistener welcher Knopf 
	 *gedrückt wurde und gibt diesen dann an die methode aktion weiter
	 */
	
	

	public int getAnzahlFensterSchließen() {
		return anzahlFensterSchließen;
	}

	public void setAnzahlFensterSchließen(int anzahlFensterSchließen) {
		this.anzahlFensterSchließen = anzahlFensterSchließen;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object obj = e.getSource();
		
		if(obj.equals(Spielfeld.btnNewButton_1))
		{
			this.aktion(btnNewButton_1);			
		}
		else if(obj.equals(Spielfeld.btnNewButton_2))
		{
			this.aktion(btnNewButton_2);
		}
		else if(obj.equals(Spielfeld.btnNewButton_3))
		{
			this.aktion(btnNewButton_3);
		}
		else if(obj.equals(Spielfeld.btnNewButton_4))
		{
			this.aktion(btnNewButton_4);
		}
		else if(obj.equals(Spielfeld.btnNewButton_5))
		{
			this.aktion(btnNewButton_5);
		}
		else if(obj.equals(Spielfeld.btnNewButton_6))
		{
			this.aktion(btnNewButton_6);
		}
		else if(obj.equals(Spielfeld.btnNewButton_7))
		{
			this.aktion(btnNewButton_7);
		}
		else if(obj.equals(Spielfeld.btnNewButton_8))
		{
			this.aktion(btnNewButton_8);
		}
		else if(obj.equals(Spielfeld.btnNewButton_9))
		{
			this.aktion(btnNewButton_9);
		}
		else if(obj.equals(Spielfeld.btnNewButton_10))
		{
			this.aktion(btnNewButton_10);
		}
		else if(obj.equals(Spielfeld.btnNewButton_11))
		{
			this.aktion(btnNewButton_11);
		}
		else if(obj.equals(Spielfeld.btnNewButton_12))
		{
			this.aktion(btnNewButton_12);
		}
		else if(obj.equals(Spielfeld.btnNewButton_13))
		{
			this.aktion(btnNewButton_13);
		}
		else if(obj.equals(Spielfeld.btnNewButton_14))
		{
			this.aktion(btnNewButton_14);
		}
		else if(obj.equals(Spielfeld.btnNewButton_15))
		{
			this.aktion(btnNewButton_15);
		}
		else if(obj.equals(Spielfeld.btnNewButton_16))
		{
			this.aktion(btnNewButton_16);
		}
		else if(obj.equals(Spielfeld.btnNewButton_17))
		{
			this.aktion(btnNewButton_17);
		}
		else if(obj.equals(Spielfeld.btnNewButton_18))
		{
			this.aktion(btnNewButton_18);
		}
		else if(obj.equals(Spielfeld.btnNewButton_19))
		{
			this.aktion(btnNewButton_19);
		}
		else if(obj.equals(Spielfeld.btnNewButton_20))
		{
			this.aktion(btnNewButton_20);
		}
		else if(obj.equals(Spielfeld.btnNewButton_21))
		{
			this.aktion(btnNewButton_21);
		}
		else if(obj.equals(Spielfeld.btnNewButton_22))
		{
			this.aktion(btnNewButton_22);
		}
		else if(obj.equals(Spielfeld.btnNewButton_23))
		{
			this.aktion(btnNewButton_23);
		}
		else if(obj.equals(Spielfeld.btnNewButton_24))
		{
			this.aktion(btnNewButton_24);
		}

		
		/*
		 * hier wird der neue Zug der Strategie abgefragt und this.aktion mit dem entsprechenden Button,
		 * also wo die Strategie hin will, ausgeführt
		 */
		if(gMode == 2)
		{
			try 
			{
				ISpielzug neuerZug = strategie1.bewegeStein(gSteine);
				IBewegung neueIBewegung = neuerZug.bewegeSpielStein();
				Bewegung neueBewegung = new Bewegung(neueIBewegung.altePosition(), neueIBewegung.neuePosition());
				Spielfeld.bewegungInButtons(neueBewegung);
			} 
			catch (StrategieException e1) 
			{
				e1.printStackTrace();
			}
		}
		
		if(gMode == 3)
		{
			new Thread() 
			{
				{
					start(); 
				}
				public void run()
				{
					try {
						ISpielzug neuerZug2 = strategie2.bewegeStein(gSteine);
						IBewegung neueIBewegung2 = neuerZug2.bewegeSpielStein();
						Bewegung neueBewegung2 = new Bewegung(neueIBewegung2.altePosition(), neueIBewegung2.neuePosition());
						Spielfeld.bewegungInButtons(neueBewegung2);
						
						sleep(5000);
						
						ISpielzug neuerZug1 = strategie1.bewegeStein(gSteine);
						IBewegung neueIBewegung1 = neuerZug1.bewegeSpielStein();
						Bewegung neueBewegung1 = new Bewegung(neueIBewegung1.altePosition(), neueIBewegung1.neuePosition());
						Spielfeld.bewegungInButtons(neueBewegung1);
						
						sleep(5000);
					} 
					catch (StrategieException | InterruptedException e) 
					{
						
						e.printStackTrace();
					}
				}
			};
		}
	}
		
	
	/*
	 * wenn ein Knopf gedrückt wurde wird diese aktion ausgeführt
	 */
	
	public void aktion(TransparentButtonFeld lButton)
	{
		Position PositionGeklickt = lButton.getPosition();
		while(!SpielBeendet)
		{

			int e, x, y;
			e = posIndexUmrechnen(PositionGeklickt.getEbene());
			x = posIndexUmrechnen(PositionGeklickt.getX());
			y = posIndexUmrechnen(PositionGeklickt.getY());
			Spielstein aktuellerStein = SpielfeldArray[e][x][y];
			
			//solange der aktuelle Spieler keine Mühle hat
			if(hatMuehle != true)
			{
				if(Spieler1.getAnzahlZuege() <= Spieler2.getAnzahlZuege())
				{
					aktuellerSpieler = Spieler1;
					passiverSpieler = Spieler2;
				}
				else
				{
					aktuellerSpieler = Spieler2;
					passiverSpieler = Spieler1;
				}

				//erste Phase wenn noch nicht alle Steine gesetzt wurden
				while(Spieler2.getPhase().equals(EPhase.Setzen)) //(anzahlRunden < 9)
				{
					//neue Bewegung erstellen
					neueBewegung = new Bewegung(null, PositionGeklickt);
					
					//verschiedene Prüfungen
					if(pruef.checkSetzen(PositionGeklickt, aktuellerSpieler, passiverSpieler) == true)
					{
						this.SpielsteinSetzen(neueBewegung, aktuellerSpieler, lButton);
						this.verschiedeneAusgaben();
					}
					else
					{
						System.out.print("checkSetzen ergab: ");
						System.out.println(pruef.checkSetzen(PositionGeklickt, aktuellerSpieler, passiverSpieler));	
						return;
					}
					
					//Steht der Stein in einer Mühle
					if(pruef.checkInMuehle(anzahlRunden, aktuellerSpieler.Steine))
					{
						
						System.out.println(aktuellerSpieler.getName() + textMuehle);
						System.out.println("Alle gegnerischen Steine stehen in einer Mühle: " + this.alleGegnerSteineInMühle(passiverSpieler));
						
						if((pruef.checkSpielBeendet(aktuellerSpieler, passiverSpieler) == true && passiverSpieler.getAnzahlZuege() == 9)
								|| (passiverSpieler.getAnzahlSteine() == 3 && passiverSpieler.getAnzahlZuege() == 9))
						{
							SpielBeendet = true;
							this.aktion(btnNewButton_1);
							return;
						}
						
						
						
						Spielfeld.neueMeldung(meldungsZeit, aktuellerSpieler.getName() + textMuehle);
						hatMuehle = true;
						panel.repaint();
						
						return;
					}
					else
					{
						panel_1.repaint();
//						db.zugspeichern(neueBewegung, aktuellerSpieler, false, aktuellerStein);
					}
					
					if(aktuellerSpieler == Spieler2)
						anzahlRunden++;
					
					//neu zeichnen
					panel.repaint();
					panel_1.repaint();
					if(Spieler2.getAnzahlZuege()==9)
					{
						new Thread() 
						{
							{
								start(); 
							}
							public void run()
							{
								try 
								{ 
									if(!pruef.checkSpielBeendet(passiverSpieler, aktuellerSpieler))
									{
										Spielfeld.neueMeldung(wichtigeMeldungsZeit, textRunde2);
										sleep(wichtigeMeldungsZeit * 1000);
										if(aktuellerSpieler.getAnzahlSteine() == 3)
										{
											Spielfeld.neueMeldung(wichtigeMeldungsZeit, aktuellerSpieler.getName() + textDarfSpringen);
											sleep(wichtigeMeldungsZeit * 1000);
										}
										if(passiverSpieler.getAnzahlSteine() == 3)
										{
											Spielfeld.neueMeldung(wichtigeMeldungsZeit, passiverSpieler.getName() + textDarfSpringen);
											sleep(wichtigeMeldungsZeit * 1000);
										}
									}
									else
									{
										SpielBeendet = true;
										return;
									}
								}
								catch ( InterruptedException e ) 
								{ 
									e.printStackTrace();
								} 
							} 
						};  //Thread zum warten
						
						
					}
					
					if(pruef.checkSpielBeendet(passiverSpieler, aktuellerSpieler) == true && passiverSpieler.getAnzahlZuege() == 9)
					{
						SpielBeendet = true;
						this.aktion(btnNewButton_1);
						return;
					}
					if(pruef.checkSpielBeendet(aktuellerSpieler, passiverSpieler) == true && passiverSpieler.getAnzahlZuege() == 9)
					{
						aktuellerSpieler = passiverSpieler;
						SpielBeendet = true;
						this.aktion(btnNewButton_1);
						return;
					}
					
					return;
				}				
		
				//Hier wird der Code ausgeführt, wenn die erste Phase abgeschlossen ist
				while(true)				//Spieler1.getPhase().equals(EPhase.Schieben)
				{
	
					//hier der Fall wenn auf einen bereits gelegten Stein gedrückt wird
					if(hatAltePosition == false)
					{
						if(aktuellerStein != null) //nur wenn auf Feld gedrückt wird auf dem auch ein Stein steht
						{
							if((aktuellerStein.FarbVergleich(ESpielsteinFarbe.WEISS) && aktuellerSpieler.getSpielerfarbe().equals(ESpielsteinFarbe.WEISS))
								|| (aktuellerStein.FarbVergleich(ESpielsteinFarbe.SCHWARZ) && aktuellerSpieler.getSpielerfarbe().equals(ESpielsteinFarbe.SCHWARZ)))
							{
								altePosition = PositionGeklickt;
								hatAltePosition = true;
								ausgewaehlterStein = aktuellerStein;
								panel.repaint();
								return;
							}
							else
							{
								System.out.println("Ein Spieler hat auf einen Stein gedrückt der nicht ihm gehört");
								return;
							}
						}
						else
						{
							System.out.println("Hier steht kein Stein");
							return;
						}
					}
					
					else 		//hier der Fall wenn die alte Position bereits abgespeichert wurde
					{
						//den ausgewaehlten Stein wieder zurücksetzen
						ausgewaehlterStein = null;
						
						//neue Bewegung erstellen
						neueBewegung = new Bewegung(altePosition, PositionGeklickt);
						System.out.println(neueBewegung);
						
						//verschiedene Prüfungen
						if(pruef.checkZug(neueBewegung, aktuellerSpieler, passiverSpieler) == true)
						{	
							this.SpielsteinBewegen(neueBewegung, aktuellerSpieler, lButton);
							this.verschiedeneAusgaben();
							this.createArrayEigenerIndeizes(aktuellerSpieler);
							if(pruef.checkSpielBeendet(aktuellerSpieler, passiverSpieler) == true)
							{
								SpielBeendet = true;
								this.aktion(btnNewButton_1);
								return;
							}
							if(pruef.checkSpielBeendet(passiverSpieler, aktuellerSpieler) == true)
							{
								SpielBeendet = true;
								this.aktion(btnNewButton_1);
								return;
							}
						}
						else
						{
							System.out.print("checkZug ergab: ");
							System.out.println(pruef.checkZug(neueBewegung, aktuellerSpieler, passiverSpieler));
							panel.repaint();
							hatAltePosition = false;
							return;
						}
						
						int e1, x1, y1;
						e1 = posIndexUmrechnen(PositionGeklickt.getEbene());
						x1 = posIndexUmrechnen(PositionGeklickt.getX());
						y1 = posIndexUmrechnen(PositionGeklickt.getY());
						Spielstein aktuellerStein1 = SpielfeldArray[e1][x1][y1];
						
						if(pruef.checkInMuehle(aktuellerStein1.getIndex() , aktuellerSpieler.Steine))
						{
							
							this.verschiedeneAusgaben();
							
							hatMuehle = true;
							hatAltePosition = false;
							panel.repaint();
							this.createArrayEigenerIndeizes(aktuellerSpieler);
							if(pruef.checkSpielBeendet(aktuellerSpieler, passiverSpieler) == true 
									|| passiverSpieler.getAnzahlSteine() == 3)
							{
								SpielBeendet = true;
								this.aktion(btnNewButton_1);
								return;
							}
							Spielfeld.neueMeldung(meldungsZeit, aktuellerSpieler.getName() + textMuehle);
							return;
						}
						else
							panel_1.repaint();
						
						if(aktuellerSpieler == Spieler2)
							anzahlRunden++;
						
						panel.repaint();
						
						hatAltePosition = false;
						return;		
					}
				}

			}
			else		//hier der code wenn ein Spieler eine Mühle hat
			{
				if(aktuellerStein != null)
				{
					//hat der Spieler auf einen anderen Stein gedrückt?
					if((aktuellerStein.FarbVergleich(ESpielsteinFarbe.SCHWARZ) && aktuellerSpieler.getSpielerfarbe().equals(ESpielsteinFarbe.WEISS))
							|| (aktuellerStein.FarbVergleich(ESpielsteinFarbe.WEISS) && aktuellerSpieler.getSpielerfarbe().equals(ESpielsteinFarbe.SCHWARZ)))
					{
						this.muehle(aktuellerStein, aktuellerSpieler, PositionGeklickt, neueBewegung);
						if(!SteinKannGeloeschtWerden)	 
						{
							System.out.println("Dieser Stein steht in einer Mühle, und kann daher nicht gelöscht werden!");
							SteinKannGeloeschtWerden = true;
							return;
						}							

						if(aktuellerSpieler == Spieler2)
						{
							anzahlRunden++;
							entfernteSteineWeiss++;
						}
						else
						{
							entfernteSteineSchwarz++;
//							
						}
						if(pruef.checkSpielBeendet(aktuellerSpieler, passiverSpieler) == true)
//							{
//								SpielBeendet = true;
//								this.aktion(btnNewButton_1);
//							}
						if(pruef.checkSpielBeendet(aktuellerSpieler, passiverSpieler) == true && passiverSpieler.getAnzahlZuege() == 9)
						{
							aktuellerSpieler = passiverSpieler;
							SpielBeendet = true;
							this.aktion(btnNewButton_1);
						}
						if(pruef.checkSpielBeendet(passiverSpieler, aktuellerSpieler) == true && passiverSpieler.getAnzahlZuege() >= 9)
						{
							SpielBeendet = true;
							this.aktion(btnNewButton_1);
						}
						panel_1.repaint();
						this.verschiedeneAusgaben();
						if(Spieler2.getAnzahlZuege()==9)
						{
							new Thread() 
							{
								{
									start(); 
								}
								public void run()
								{
									try 
									{ 
										if(!pruef.checkSpielBeendet(passiverSpieler, aktuellerSpieler))
										{
											Spielfeld.neueMeldung(wichtigeMeldungsZeit, textRunde2);
											sleep(wichtigeMeldungsZeit * 1000);
											if(aktuellerSpieler.getAnzahlSteine() == 3)
											{
												Spielfeld.neueMeldung(wichtigeMeldungsZeit, aktuellerSpieler.getName() + textDarfSpringen);
												sleep(wichtigeMeldungsZeit * 1000);
											}
											if(passiverSpieler.getAnzahlSteine() == 3)
											{
												Spielfeld.neueMeldung(wichtigeMeldungsZeit, passiverSpieler.getName() + textDarfSpringen);
												sleep(wichtigeMeldungsZeit * 1000);
											}
										}
										else
										{
											SpielBeendet = true;
											return;
										}
									}
									catch ( InterruptedException e ) 
									{ 
										e.printStackTrace();
									} 
								} 
							};  //Thread zum warten
						}
						
						//wenn duch das Mühle schlagen der passive Spieler nur noch 3 Steine hat wird die Meldung erzeugt, dass dieser jetzt springen darf
						if(passiverSpieler.getAnzahlSteine() == 3 && passiverSpieler.getAnzahlZuege() > 9)
							Spielfeld.neueMeldung(wichtigeMeldungsZeit, aktuellerSpieler.getName() + textDarfSpringen);
							
							
						return;
					}
					else //der Spieler hat auf einen seiner eigenen Steine gedrückt
					{
						System.out.println("Der Spieler hat auf einen eigenen Stein gedrückt obwohl er eine Mühle hat");
						return;
					}
				}
				else	//der Spieler hat auf ein leeres Feld gedrückt
				{
					return;
				}
			}	
		}
		new Thread() 
		{
			{
				start(); 
				
				
			}
			public void run()
			{
				try 
				{ 
					sleep(wichtigeMeldungsZeit * 1000);
					dispose();
					Gewonnen gewinnerBild = new Gewonnen(aktuellerSpieler.getName() + textGewonnen, Spieler1.getName(), Spieler2.getName(), gMode, gSchwierigkeit, gTheme);
					gewinnerBild.setVisible(true);
					System.out.println("Spieler beendet!");
				}	
				catch ( InterruptedException e ) 
				{ 
					e.printStackTrace();
				} 
			}
		};
	}
		
	

	public void muehle(Spielstein aktuellerStein, Spieler aktuellerSpieler, Position PositionGeklickt, Bewegung neueBewegung)
	{
		int e, x, y;
		e = posIndexUmrechnen(PositionGeklickt.getEbene());
		x = posIndexUmrechnen(PositionGeklickt.getX());
		y = posIndexUmrechnen(PositionGeklickt.getY());
		Spielstein zuLoeschenderStein = SpielfeldArray[e][x][y];
		
		
		// wenn der ausgewählte Stein nicht in einer Mühle steht oder alle Steine des Gegners in einer Mühle stehen, kann der Stein gelöscht werden
		if(!pruef.checkInMuehle(zuLoeschenderStein.getIndex() , passiverSpieler.Steine) || this.alleGegnerSteineInMühle(passiverSpieler) == true)
		{
			SpielfeldArray[e][x][y] = null;
			passiverSpieler.entferneSpielstein(zuLoeschenderStein.getIndex());
			hatAltePosition = false;
			gSteine.remove(zuLoeschenderStein);
		}

		else
		{
			SteinKannGeloeschtWerden = false;
			return;
		}
		
		
		panel.repaint();
		hatMuehle = false;
		
//		db.zugspeichern(neueBewegung, aktuellerSpieler, true, zuLoeschenderStein);
	}
	
	
	public void SpielsteinSetzen(Bewegung neueBewegung, Spieler lSpieler, TransparentButtonFeld lButton)
	{
		//Die Position an der der Stein gezeichnet werden soll wird ermittelt 
		Point pos = lButton.getLocation();
		xPos = (int)pos.getX()-5;
		yPos = (int)pos.getY()-3;
		
		//Die Bewegung wird an den Spieler weitergegeben 
		lSpieler.setzeSpielstein(neueBewegung.getNach(), xPos, yPos);
		
		//Das Array in dem die Spielsteine gepeichert sind wird gefüllt
		int e, x, y;
		e = posIndexUmrechnen(neueBewegung.getNach().getEbene());
		x = posIndexUmrechnen(neueBewegung.getNach().getX());
		y = posIndexUmrechnen(neueBewegung.getNach().getY());
		SpielfeldArray[e][x][y] = lSpieler.getSpielstein(anzahlRunden);
		
		//Stein wird der Liste mit den aktuellen Steinen auf dem Feld hinzugefügt
		gSteine.add(lSpieler.getSpielstein(anzahlRunden));
		
		//Ausgabe der Bewertung des Zuges ZUM TEST
		System.out.println("Dieser Zug wird so bewertet: " + bewertung.bewerteZug(gSteine, neueBewegung, aktuellerSpieler.getAnzahlZuege()));
	}
	
	
	public void SpielsteinBewegen(Bewegung neueBewegung, Spieler lSpieler, TransparentButtonFeld lButton)
	{
		//Die Position an der der Stein gezeichnet werden soll wrd ermittelt
		Point pos = lButton.getLocation();
		xPos = (int)pos.getX()-5;
		yPos = (int)pos.getY()-3;
		
		//Die alte Position des Steins wird aus dem Array gelöscht und in der Variable aktueller Stein zwischengespeichert
		int e, x, y;
		e = posIndexUmrechnen(altePosition.getEbene());
		x = posIndexUmrechnen(altePosition.getX());
		y = posIndexUmrechnen(altePosition.getY());
		Spielstein aktuellerStein = SpielfeldArray[e][x][y];
		
		//Die neue Position wird in das Array geschrieben 
		int a, b, c;
		a = posIndexUmrechnen(neueBewegung.getNach().getEbene());
		b = posIndexUmrechnen(neueBewegung.getNach().getX());
		c = posIndexUmrechnen(neueBewegung.getNach().getY());
		SpielfeldArray[a][b][c] = aktuellerStein;
		
		//Die Bewegung wird an den Spieler weitergegeben
		lSpieler.bewegeSpielstein(neueBewegung, aktuellerStein.getIndex(), xPos, yPos);	
		
		//Stein wird in der Liste mit den aktuellen Steinen auf dem Feld verändert
		gSteine.set(gSteine.indexOf(SpielfeldArray[e][x][y]), aktuellerStein);
		
		//Stein wird aus dem SpielfeldArray gelöscht
		SpielfeldArray[e][x][y] = null;
		
		//Ausgabe der Bewertung des Zuges ZUM TEST
		System.out.println("Dieser Zug wird so bewertet: " + bewertung.bewerteZug(gSteine, neueBewegung, aktuellerSpieler.getAnzahlZuege()));
	}
	
	//EPositionIndex auf dem Feld wird in int umgerechnet
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
	
	//Überprüft ob alle gegnerischen Steine in Mühle stehen
	//True wenn alle Steine in Mühle Stehen, false wenn nicht 
	public boolean alleGegnerSteineInMühle(Spieler passiverSpieler)
	{
		int counter = 0;
		
		/*
		 * das array mit allen Spielsteinen durchlaufen und prüfen ob der aktuelle Stein vom passiven Spieler ist, 
		 * wenn ja dann gucken ob dieser Stein in einer Mühle steht und wenn das auch true ist dann den counter hochsetzen
		 */
		for(int i = 0; i <= 2; i++)
        {
        	for(int j = 0; j <= 2; j++)
        	{
        		for(int k = 0; k <= 2; k++)
        		{
        			if(SpielfeldArray[i][j][k] != null)
        			{
            			Spielstein aktuellerStein = SpielfeldArray[i][j][k];
            			if(aktuellerStein.getFarbe() == passiverSpieler.getSpielerfarbe())
            			{
            				if(pruef.checkInMuehle(aktuellerStein.getIndex(), passiverSpieler.Steine))
            					counter++;
            			}
            				
        			}
        		}
        	}
        }
		
		//wenn der counter gleich der Anzahl Steine des passiven Spielers ist stehen alle Steine in einer Mühle
		if(passiverSpieler.getAnzahlSteine() == counter)
			return true;
		else
			return false; 
	}
	
	
	
	public static void neueMeldung(final int sekunden, final String meldung)
	{
		//hier wird die Position festgelegt wo die meldung erscheinen soll
		Point pos = panel.getLocationOnScreen();
		xPos = (int)pos.getX() + 20;
		yPos = (int)pos.getY() + 200;
		
		
		
	
		new Thread() 
		{
		      { 
		    	  start(); 
		      } 
		      public void run() 
		      {
		        try 
		        { 
		        	Anweisung1 probe = new Anweisung1(meldung, xPos, yPos, x, y, gFont);
					probe.setAlwaysOnTop(true);
					probe.setVisible(true);
		        	sleep(sekunden * 1000);
		        	probe.setVisible(false);
		        }
		        catch ( InterruptedException e ) { }
		      } 
		};
	}
	
	public void verschiedeneAusgaben()
	{
		//verschiedene Ausgaben
		System.out.print("Spieler1: " + Spieler1.getAnzahlZuege() + " Züge, " + Spieler1.getAnzahlSteine() + " Steine ||  ");
		System.out.println("Spieler2: " + Spieler2.getAnzahlZuege() + " Züge, " + Spieler2.getAnzahlSteine() + " Steine ||  Anzahl Runden: " + anzahlRunden); 
		System.out.println("Zug Beendet ----------------------------------------------------------------------------");
			
	}
	
	public void createArrayEigenerIndeizes(Spieler aktuellerSpieler)
	{
		int zaehler = 0;
		for(int i = 0; i <= 2; i++)
        {
        	for(int j = 0; j <= 2; j++)
        	{
        		for(int k = 0; k <= 2; k++)
        		{
        			if(SpielfeldArray[i][j][k] != null)
        			{
            			Spielstein aktuellerStein = SpielfeldArray[i][j][k];
            			if(aktuellerStein.getFarbe() == aktuellerSpieler.getSpielerfarbe())
            			{
            				Indizes[zaehler] = aktuellerStein.getIndex();
            				zaehler++;
            			}
            				
        			}
        		}
        	}
        }
	}
	
	//Methode die für die Bewegung die die Strategie zurückliefert die entsprechenenden Button-klicks simuliert
	public static void bewegungInButtons(Bewegung neueBewegung)
	{
		if(neueBewegung.getVon() == null)
		{
			
			if(btnNewButton_1.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_1.doClick(); 								//this.actionPerformed(new ActionEvent(btnNewButton_1, 1001, btnNewButton_1.)));
			if(btnNewButton_2.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_2.doClick(); 
			if(btnNewButton_3.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_3.doClick(); 
			if(btnNewButton_4.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_4.doClick(); 
			if(btnNewButton_5.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_5.doClick(); 
			if(btnNewButton_6.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_6.doClick(); 
			if(btnNewButton_7.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_7.doClick(); 
			if(btnNewButton_8.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_8.doClick(); 
			if(btnNewButton_9.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_9.doClick(); 
			if(btnNewButton_10.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_10.doClick(); 
			if(btnNewButton_11.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_11.doClick(); 
			if(btnNewButton_12.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_12.doClick(); 
			if(btnNewButton_13.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_13.doClick(); 
			if(btnNewButton_14.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_14.doClick(); 
			if(btnNewButton_15.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_15.doClick(); 
			if(btnNewButton_16.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_16.doClick(); 
			if(btnNewButton_17.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_17.doClick(); 
			if(btnNewButton_18.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_18.doClick(); 
			if(btnNewButton_19.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_19.doClick(); 
			if(btnNewButton_20.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_20.doClick(); 
			if(btnNewButton_21.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_21.doClick(); 
			if(btnNewButton_22.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_22.doClick(); 
			if(btnNewButton_23.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_23.doClick(); 
			if(btnNewButton_24.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_24.doClick(); 
			
		}
		else
		{
			//VON Bewegung in button klick umgewandelt
			if(btnNewButton_1.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_1.doClick(); 								//this.actionPerformed(new ActionEvent(btnNewButton_1, 1001, btnNewButton_1.)));
			if(btnNewButton_2.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_2.doClick(); 
			if(btnNewButton_3.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_3.doClick(); 
			if(btnNewButton_4.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_4.doClick(); 
			if(btnNewButton_5.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_5.doClick(); 
			if(btnNewButton_6.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_6.doClick(); 
			if(btnNewButton_7.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_7.doClick(); 
			if(btnNewButton_8.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_8.doClick(); 
			if(btnNewButton_9.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_9.doClick(); 
			if(btnNewButton_10.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_10.doClick(); 
			if(btnNewButton_11.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_11.doClick(); 
			if(btnNewButton_12.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_12.doClick(); 
			if(btnNewButton_13.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_13.doClick(); 
			if(btnNewButton_14.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_14.doClick(); 
			if(btnNewButton_15.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_15.doClick(); 
			if(btnNewButton_16.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_16.doClick(); 
			if(btnNewButton_17.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_17.doClick(); 
			if(btnNewButton_18.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_18.doClick(); 
			if(btnNewButton_19.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_19.doClick(); 
			if(btnNewButton_20.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_20.doClick(); 
			if(btnNewButton_21.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_21.doClick(); 
			if(btnNewButton_22.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_22.doClick(); 
			if(btnNewButton_23.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_23.doClick(); 
			if(btnNewButton_24.vergleichePosition(neueBewegung.getVon()) == true)
				btnNewButton_24.doClick(); 
			
			
			
			//nach button klick simuliert
			if(btnNewButton_1.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_1.doClick(); 								//this.actionPerformed(new ActionEvent(btnNewButton_1, 1001, btnNewButton_1.)));
			if(btnNewButton_2.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_2.doClick(); 
			if(btnNewButton_3.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_3.doClick(); 
			if(btnNewButton_4.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_4.doClick(); 
			if(btnNewButton_5.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_5.doClick(); 
			if(btnNewButton_6.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_6.doClick(); 
			if(btnNewButton_7.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_7.doClick(); 
			if(btnNewButton_8.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_8.doClick(); 
			if(btnNewButton_9.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_9.doClick(); 
			if(btnNewButton_10.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_10.doClick(); 
			if(btnNewButton_11.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_11.doClick(); 
			if(btnNewButton_12.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_12.doClick(); 
			if(btnNewButton_13.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_13.doClick(); 
			if(btnNewButton_14.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_14.doClick(); 
			if(btnNewButton_15.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_15.doClick(); 
			if(btnNewButton_16.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_16.doClick(); 
			if(btnNewButton_17.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_17.doClick(); 
			if(btnNewButton_18.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_18.doClick(); 
			if(btnNewButton_19.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_19.doClick(); 
			if(btnNewButton_20.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_20.doClick(); 
			if(btnNewButton_21.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_21.doClick(); 
			if(btnNewButton_22.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_22.doClick(); 
			if(btnNewButton_23.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_23.doClick(); 
			if(btnNewButton_24.vergleichePosition(neueBewegung.getNach()) == true)
				btnNewButton_24.doClick(); 
		}
	}
	

		
	
	
}



