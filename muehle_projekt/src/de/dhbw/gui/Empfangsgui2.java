package de.dhbw.gui;


import de.dhbw.gui.TransparentButton2;
import de.dhbw.gui.DialogAnleitung2;
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
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;

public class Empfangsgui2 extends JFrame {

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel() {  
			                public void paintComponent(Graphics g) {  
			                     Image hintergrund = Toolkit.getDefaultToolkit().getImage(  
			                               Empfangsgui2.class.getResource("/de/dhbw/images/EmpfangsGUI.PNG"));  
		                     g.drawImage(hintergrund, 0, 0, this.getWidth(), this.getHeight(), this);  
		                }  
			          };  
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 4, 10, 10));
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		JLabel label_1 = new JLabel("") ;
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("");
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("");
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("");
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("");
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("");
		panel.add(label_8);
		
		TransparentButton2 btn_start = new TransparentButton2("START");
		btn_start.setForeground(Color.BLACK);
		btn_start.setBackground(new Color(245, 222, 179));
		panel.add(btn_start);
		
		JButton btn_exit = new TransparentButton2("EXIT");
		btn_exit.setForeground(Color.BLACK);
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btn_exit.setBackground(new Color(245, 222, 179));
		panel.add(btn_exit);
		
		JLabel label_9 = new JLabel("");
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("");
		panel.add(label_10);;;
		
		JButton btn_optionen = new TransparentButton2 ("OPTIONEN");
		btn_optionen.setForeground(Color.BLACK);
		btn_optionen.setBackground(new Color(245, 222, 179));
		btn_optionen.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				JDialog auswahl = new DialogOptionen2 ();
				auswahl.show(true);
			}
		});
		panel.add(btn_optionen);
		
		JButton btn_anleitung = new TransparentButton2("New button");
		btn_anleitung.setForeground(Color.BLACK);
		btn_anleitung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog anleitung = new DialogAnleitung2();
				anleitung.show(true);
			}
		});
		btn_anleitung.setText("ANLEITUNG");
		btn_anleitung.setBackground(new Color(245, 222, 179));
		panel.add(btn_anleitung);
		
		JLabel label_11 = new JLabel("");
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("");
		panel.add(label_12);
		
		JLabel label_13 = new JLabel("");
		panel.add(label_13);
		
		JLabel label_14 = new JLabel("");
		panel.add(label_14);
	}
	
	
}
