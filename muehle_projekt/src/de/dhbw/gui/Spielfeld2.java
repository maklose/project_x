package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;


import net.miginfocom.swing.MigLayout;

import java.awt.GridLayout;

import javax.swing.JButton;

public class Spielfeld2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Spielfeld2 frame = new Spielfeld2();
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
	public Spielfeld2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel(){  
            public void paintComponent(Graphics g) {  
                Image spielfeld = Toolkit.getDefaultToolkit().getImage(  
                          Spielfeld2.class.getResource("/de/dhbw/images/muehlespielfeld2.png"));  
            g.drawImage(spielfeld, 0, 0, this.getWidth(), this.getHeight(), this);  
       }  
     };  ;
		
     	panel.setBackground(Color.LIGHT_GRAY);
		
		GridLayout gl = new GridLayout(7,7,15,15);

		
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(gl);
	
		
		JButton btnNewButton_1 = new TransparentButtonFeld("");
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		JButton btnNewButton_4 = new TransparentButtonFeld("");
		panel.add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_6 = new TransparentButtonFeld("");
		panel.add(btnNewButton_6);
		
		JLabel label_1 = new JLabel("");
		panel.add(label_1);
		
		JButton btnNewButton_9 = new TransparentButtonFeld("");
		panel.add(btnNewButton_9);
		
		JLabel label_2 = new JLabel("");
		panel.add(label_2);
		
		JButton btnNewButton_10 = new TransparentButtonFeld("");
		panel.add(btnNewButton_10);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton_13 = new TransparentButtonFeld("");
		panel.add(btnNewButton_13);
		
		JLabel label_3 = new JLabel("");
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("");
		panel.add(label_5);
		
		JButton btnNewButton_15 = new TransparentButtonFeld("");
		panel.add(btnNewButton_15);
		
		JButton btnNewButton_18 = new TransparentButtonFeld("");
		panel.add(btnNewButton_18);
		
		JButton btnNewButton_19 = new TransparentButtonFeld("");
		panel.add(btnNewButton_19);
		
		JLabel label_6 = new JLabel("");
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("");
		panel.add(label_7);
		
		JButton btnNewButton_22 = new TransparentButtonFeld("");
		panel.add(btnNewButton_22);
		
		JButton btnNewButton_23 = new TransparentButtonFeld("");
		panel.add(btnNewButton_23);
		
		JButton btnNewButton_24 = new TransparentButtonFeld("");
		panel.add(btnNewButton_24);
		
		JLabel label_8 = new JLabel("");
		panel.add(label_8);
		
		JButton btnNewButton_26 = new TransparentButtonFeld("");
		panel.add(btnNewButton_26);
		
		JButton btnNewButton_27 = new TransparentButtonFeld("");
		panel.add(btnNewButton_27);
		
		JButton btnNewButton_28 = new TransparentButtonFeld("");
		panel.add(btnNewButton_28);
		
		JLabel label_9 = new JLabel("");
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("");
		panel.add(label_10);
		
		JButton btnNewButton_31 = new TransparentButtonFeld("");
		panel.add(btnNewButton_31);
		
		JButton btnNewButton_32 = new TransparentButtonFeld("");
		panel.add(btnNewButton_32);
		
		JButton btnNewButton_33 = new TransparentButtonFeld("");
		panel.add(btnNewButton_33);
		
		JLabel label_11 = new JLabel("");
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("");
		panel.add(label_12);
		
		JLabel label_13 = new JLabel("");
		panel.add(label_13);
		
		JButton btnNewButton_37 = new TransparentButtonFeld("");
		panel.add(btnNewButton_37);
		
		JLabel label_14 = new JLabel("");
		panel.add(label_14);
		
		JButton btnNewButton_39 = new TransparentButtonFeld("");
		panel.add(btnNewButton_39);
		
		JLabel label_15 = new JLabel("");
		panel.add(label_15);
		
		JButton btnNewButton_41 = new TransparentButtonFeld("");
		panel.add(btnNewButton_41);
		
		JLabel label_16 = new JLabel("");
		panel.add(label_16);
		
		JButton btnNewButton_43 = new TransparentButtonFeld("");
		panel.add(btnNewButton_43);
		
		JLabel label_17 = new JLabel("");
		panel.add(label_17);
		
		JLabel label_18 = new JLabel("");
		panel.add(label_18);
		
		JButton btnNewButton_46 = new TransparentButtonFeld("");
		panel.add(btnNewButton_46);
		
		JLabel label_19 = new JLabel("");
		panel.add(label_19);
		
		JLabel label_20 = new JLabel("");
		panel.add(label_20);
		
		JButton btnNewButton_49 = new TransparentButtonFeld("");
		panel.add(btnNewButton_49);
	}
		
	}


