package de.dhbw.gui;


import de.dhbw.gui.DialogOptionen2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.Toolkit; 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Empfangsgui2 extends JFrame implements WindowListener {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Empfangsgui2 frame = new Empfangsgui2();
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
	public Empfangsgui2() {
		//Fenster
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 456);
		setResizable(false);
		//setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//Hintergrund des Empfangsfensters
		final JPanel panel = new JPanel() {  
			                public void paintComponent(Graphics g) {  
			                     Image hintergrund = Toolkit.getDefaultToolkit().getImage(  
			                               Empfangsgui2.class.getResource("/de/dhbw/images/Menue_GUI.PNG"));  
		                     g.drawImage(hintergrund, 0, 0, this.getWidth(), this.getHeight(), this);  
		                }  
			          };  
		contentPane.add(panel, BorderLayout.CENTER);;;
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 138, 130, 138, 0, 0};
		gbl_panel.rowHeights = new int[]{200, 50, 50, 50, 70, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);;
		
		//Button "Start"
		JButton btn_start = new JButton(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Spiel starten.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
				btn_start.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JDialog optionenAuswahl = new DialogOptionen2();
						optionenAuswahl.setVisible(true);
					}
				});
				btn_start.setForeground(Color.BLACK);
				btn_start.setBackground(new Color(245, 222, 179));
				GridBagConstraints gbc_btn_start = new GridBagConstraints();
				gbc_btn_start.fill = GridBagConstraints.BOTH;
				gbc_btn_start.insets = new Insets(0, 0, 5, 5);
				gbc_btn_start.gridx = 1;
				gbc_btn_start.gridy = 1;
				panel.add(btn_start, gbc_btn_start);;
		
		//Button "Beenden"
		JButton btn_exit = new JButton(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Beenden.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		btn_exit.setForeground(Color.BLACK);
		btn_exit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				final BestaetigungBeenden frageBeenden = new BestaetigungBeenden(320,200);
				panel.invalidate();
				frageBeenden.setListener( new BestaetigungBeenden.BestatigungsListener() {
					
					@Override
					public void onOK() {
						
						Empfangsgui2.this.dispose();
						frageBeenden.dispose();
					}
					
					@Override
					public void onCancel() {	
					}
				});
				frageBeenden.setVisible(true);
				
			}
		});
		
		// Button "Highscore"
		JButton btnHighscore = new JButton(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Highscore.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		;
		btnHighscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame highscore = new Highscore();
				highscore.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnHighscore = new GridBagConstraints();
		gbc_btnHighscore.fill = GridBagConstraints.BOTH;
		gbc_btnHighscore.insets = new Insets(0, 0, 5, 5);
		gbc_btnHighscore.gridx = 3;
		gbc_btnHighscore.gridy = 1;
		panel.add(btnHighscore, gbc_btnHighscore);
		btn_exit.setBackground(new Color(245, 222, 179));
		GridBagConstraints gbc_btn_exit = new GridBagConstraints();
		gbc_btn_exit.fill = GridBagConstraints.BOTH;
		gbc_btn_exit.insets = new Insets(0, 0, 5, 5);
		gbc_btn_exit.gridx = 1;
		gbc_btn_exit.gridy = 3;
		panel.add(btn_exit, gbc_btn_exit);
		
		//Button "Spielanleitung"
		JButton btn_anleitung = new JButton(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Spielanleitung.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		btn_anleitung.setForeground(Color.BLACK);
		btn_anleitung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame anleitung = new Anleitung();
				anleitung.setVisible(true);
			}
		});
		btn_anleitung.setBackground(new Color(245, 222, 179));
		GridBagConstraints gbc_btn_anleitung = new GridBagConstraints();
		gbc_btn_anleitung.insets = new Insets(0, 0, 5, 5);
		gbc_btn_anleitung.fill = GridBagConstraints.BOTH;
		gbc_btn_anleitung.gridx = 3;
		gbc_btn_anleitung.gridy = 3;
		panel.add(btn_anleitung, gbc_btn_anleitung);
	}
	

	//WindowListener

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent Event) {				
		final BestaetigungBeenden frageBeenden = new BestaetigungBeenden(320,200);
				frageBeenden.setListener( new BestaetigungBeenden.BestatigungsListener() {
					
					@Override
					public void onOK() {
						
						Empfangsgui2.this.dispose();
						frageBeenden.dispose();
					}
					
					@Override
					public void onCancel() {	
					}
				});
				frageBeenden.setVisible(true);
				
	};	

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}

