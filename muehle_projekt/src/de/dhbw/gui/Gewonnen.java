package de.dhbw.gui;
import de.dhbw.gui.Spielfeld;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;



public class Gewonnen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gewonnen frame = new Gewonnen();
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
	public Gewonnen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel(){  
            public void paintComponent(Graphics g) {  
                Image hintergrund = Toolkit.getDefaultToolkit().getImage(  
                          Empfangsgui2.class.getResource("/de/dhbw/images/Sicherheitsabfrage_GUI.PNG"));  //richtiges bild einfügen
            g.drawImage(hintergrund, 0, 0, this.getWidth(), this.getHeight(), this);  
            }  
		};  
		contentPane.add(panel, BorderLayout.NORTH);;
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{50, 100, 144, 100, 50, 0};
		gbl_panel.rowHeights = new int[]{180, 40, 30, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);;
		
		JButton btnBeenden = new JButton("beenden"){
			public void paintComponent(Graphics g){
				g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Beenden.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
			};
		};
		btnBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final BestaetigungBeenden frageBeenden = new BestaetigungBeenden(100, 100); //Werte ändern
				frageBeenden.setListener( new BestaetigungBeenden.BestatigungsListener() {
					
					@Override
					public void onOK() {
						
						Gewonnen.this.dispose();
						//Spielfeld.dispose();
						frageBeenden.dispose();
					}
					
					@Override
					public void onCancel() {	
					}
				});
				frageBeenden.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnBeenden = new GridBagConstraints();
		gbc_btnBeenden.fill = GridBagConstraints.BOTH;
		gbc_btnBeenden.insets = new Insets(0, 0, 5, 5);
		gbc_btnBeenden.gridx = 1;
		gbc_btnBeenden.gridy = 1;
		panel.add(btnBeenden, gbc_btnBeenden);
		
		JButton btnSpielverlauf = new JButton("spielverlauf"){
			public void paintComponent(Graphics g){
				g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Spielverlauf.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);//neuer Button "Spielstand anzeigen" einfügen
			};
		};
		GridBagConstraints gbc_btnSpielverlauf = new GridBagConstraints();
		gbc_btnSpielverlauf.insets = new Insets(0, 0, 5, 5);
		gbc_btnSpielverlauf.fill = GridBagConstraints.BOTH;
		gbc_btnSpielverlauf.gridx = 3;
		gbc_btnSpielverlauf.gridy = 1;
		panel.add(btnSpielverlauf, gbc_btnSpielverlauf);
	}
}
