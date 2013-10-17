package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Anleitung extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Anleitung frame = new Anleitung();
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
	public Anleitung() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 528);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g) {  
                Image hintergrund = Toolkit.getDefaultToolkit().getImage(  
                          Empfangsgui2.class.getResource("/de/dhbw/images/Spielanleitung_GUI.PNG"));  
            g.drawImage(hintergrund, 0, 0, this.getWidth(), this.getHeight(), this);  
			}  
		};
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnOk = new JButton("OK"){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button OK.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnOk.setBounds(584, 394, 85, 40);
		panel.add(btnOk);
	}
}
