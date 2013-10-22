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
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnSpielstand = new JButton("spielstand"){
			public void paintComponent(Graphics g){
				g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Ja.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);//neuer Button "Spielstand anzeigen" einfügen
			};
		};;
		btnSpielstand.setBounds(288, 199, 89, 23);
		panel.add(btnSpielstand);
		
		JButton btnBeenden = new JButton("beenden"){
			public void paintComponent(Graphics g){
				g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Exit.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
			};
		};
		btnBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final BestaetigungBeenden frageBeenden = new BestaetigungBeenden();
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
		});;
		btnBeenden.setBounds(55, 199, 89, 23);
		panel.add(btnBeenden);
	}
}
