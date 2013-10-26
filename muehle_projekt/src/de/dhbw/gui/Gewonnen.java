package de.dhbw.gui;
import de.dhbw.gui.Spielfeld;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;

import com.jgoodies.forms.layout.CellConstraints.Alignment;



public class Gewonnen extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String nameSpieler1;
	private String nameSpieler2; 
	private int gMode; 
	private int gTheme;
	private int gSchwierigkeit;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gewonnen frame = new Gewonnen("test", "test", "test", 1, 2, 2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gewonnen(String name, String Spieler1, String Spieler2, int lMode, int Schwierigkeit, int lTheme) {
		nameSpieler1 = Spieler1;
		nameSpieler2 = Spieler2; 
		gMode = lMode; 
		gSchwierigkeit = Schwierigkeit;
		gTheme = lTheme;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel(){  
            public void paintComponent(Graphics g) {  
            	Image hintergrund1 = Toolkit.getDefaultToolkit().getImage(  
                        Empfangsgui2.class.getResource("/de/dhbw/images/Optionen_GUI.png"));
            	g.drawImage(hintergrund1, 0, 0, 700, 500, this); 
                Image hintergrund = Toolkit.getDefaultToolkit().getImage(  
                          Empfangsgui2.class.getResource("/de/dhbw/images/test.png"));  //richtiges bild einfügen
                g.drawImage(hintergrund, 20, 10, 350, 350, this);  
            }  
		};  
		
		contentPane.add(panel, BorderLayout.CENTER);
//        pack();
	
        
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{50, 100, 140, 99, 140, 100, 50, 0};
		gbl_panel.rowHeights = new int[]{326, 180, 40, 30, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);;
		
		JButton btnBeenden = new JButton("beenden"){
			public void paintComponent(Graphics g){
				g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Beenden.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
			};
		};
		btnBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final BestaetigungBeenden frageBeenden = new BestaetigungBeenden(300, 200); //Werte ändern
				frageBeenden.setListener( new BestaetigungBeenden.BestatigungsListener() {
					
					@Override
					public void onOK() {
						
						Gewonnen.this.dispose();
						dispose();
						frageBeenden.dispose();
						
					}
					
					@Override
					public void onCancel() {	
					}
				});
				frageBeenden.setVisible(true);
			}
		});
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.insets = new Insets(30, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		lblNewLabel.setFont(new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 30));
		lblNewLabel.setText(name);
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnNeuesSpiel = new JButton("Neues Spiel"){
			public void paintComponent(Graphics g){
				g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Spiel starten.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);//neuer Button "Spielstand anzeigen" einfügen
			};
		};
		btnNeuesSpiel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JDialog optionenAuswahl = new DialogOptionen2(nameSpieler1, nameSpieler2, gMode, gSchwierigkeit, gTheme);
				optionenAuswahl.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNeuesSpiel = new GridBagConstraints();
		gbc_btnNeuesSpiel.fill = GridBagConstraints.BOTH;
		gbc_btnNeuesSpiel.insets = new Insets(0,0, 5, 5);
		gbc_btnNeuesSpiel.gridx = 1;
		gbc_btnNeuesSpiel.gridy = 2;
		panel.add(btnNeuesSpiel, gbc_btnNeuesSpiel);
		
		JButton btnSpielverlauf = new JButton("spielverlauf"){
			public void paintComponent(Graphics g){
				g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Spielverlauf.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);//neuer Button "Spielstand anzeigen" einfügen
			};
		};
		GridBagConstraints gbc_btnSpielverlauf = new GridBagConstraints();
		gbc_btnSpielverlauf.insets = new Insets(0, 0, 5, 5);
		gbc_btnSpielverlauf.fill = GridBagConstraints.BOTH;
		gbc_btnSpielverlauf.gridx = 3;
		gbc_btnSpielverlauf.gridy = 2;
		panel.add(btnSpielverlauf, gbc_btnSpielverlauf);
		GridBagConstraints gbc_btnBeenden = new GridBagConstraints();
		gbc_btnBeenden.fill = GridBagConstraints.BOTH;
		gbc_btnBeenden.insets = new Insets(0, 0, 5, 5);
		gbc_btnBeenden.gridx = 5;
		gbc_btnBeenden.gridy = 2;
		panel.add(btnBeenden, gbc_btnBeenden);
	}

	public String getNameSpieler1() {
		return nameSpieler1;
	}

	public String getNameSpieler2() {
		return nameSpieler2;
	}

	public int getgMode() {
		return gMode;
	}

	public int getgTheme() {
		return gTheme;
	}

	private void setDefaultCloseOperation() {
		// TODO Auto-generated method stub
		
	}
}
