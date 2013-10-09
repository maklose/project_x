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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import de.dhbw.muehle_api.*;
import de.dhbw.muehle_util.plugin.MyServiceLoader;

public class Spielfeld2 extends JFrame implements ActionListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public Spielfeld2() 
	{
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
	
		JButton btnNewButton_1 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Drei));
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setActionCommand("btnNewButton_1");
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		JButton btnNewButton_4 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Zwei, EPositionIndex.Drei));
		btnNewButton_4.addActionListener(this);
		btnNewButton_4.setActionCommand("btnNewButton_4");
		panel.add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_6 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Drei));
		btnNewButton_6.addActionListener(this);
		btnNewButton_6.setActionCommand("btnNewButton_6");
		panel.add(btnNewButton_6);
		
		JLabel label_1 = new JLabel("");
		panel.add(label_1);
		
		JButton btnNewButton_9 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Drei));
		btnNewButton_9.addActionListener(this);
		btnNewButton_9.setActionCommand("btnNewButton_9");
		panel.add(btnNewButton_9);
		
		JLabel label_2 = new JLabel("");
		panel.add(label_2);
		
		JButton btnNewButton_10 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Zwei, EPositionIndex.Drei));
		btnNewButton_10.addActionListener(this);
		btnNewButton_10.setActionCommand("btnNewButton_10");
		panel.add(btnNewButton_10);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton_13 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Drei));
		btnNewButton_13.addActionListener(this);
		btnNewButton_13.setActionCommand("btnNewButton_13");
		panel.add(btnNewButton_13);
		
		JLabel label_3 = new JLabel("");
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("");
		panel.add(label_5);
		
		JButton btnNewButton_15 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Drei));
		btnNewButton_15.addActionListener(this);
		btnNewButton_15.setActionCommand("btnNewButton_15");
		panel.add(btnNewButton_15);
		
		JButton btnNewButton_18 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Zwei, EPositionIndex.Drei));
		btnNewButton_18.addActionListener(this);
		btnNewButton_18.setActionCommand("btnNewButton_18");
		panel.add(btnNewButton_18);
		
		JButton btnNewButton_19 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Drei));
		btnNewButton_19.addActionListener(this);
		btnNewButton_19.setActionCommand("btnNewButton_19");
		panel.add(btnNewButton_19);
		
		JLabel label_6 = new JLabel("");
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("");
		panel.add(label_7);
		
		JButton btnNewButton_22 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Zwei));
		btnNewButton_22.addActionListener(this);
		btnNewButton_22.setActionCommand("btnNewButton_22");
		panel.add(btnNewButton_22);
		
		JButton btnNewButton_23 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Zwei));
		btnNewButton_23.addActionListener(this);
		btnNewButton_23.setActionCommand("btnNewButton_23");
		panel.add(btnNewButton_23);
		
		JButton btnNewButton_24 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Zwei));
		btnNewButton_24.addActionListener(this);
		btnNewButton_24.setActionCommand("btnNewButton_24");
		panel.add(btnNewButton_24);
		
		JLabel label_8 = new JLabel("");
		panel.add(label_8);
		
		JButton btnNewButton_26 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Zwei));
		btnNewButton_26.addActionListener(this);
		btnNewButton_26.setActionCommand("btnNewButton_26");
		panel.add(btnNewButton_26);
		
		JButton btnNewButton_27 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Zwei));
		btnNewButton_27.addActionListener(this);
		btnNewButton_27.setActionCommand("btnNewButton_27");
		panel.add(btnNewButton_27);
		
		JButton btnNewButton_28 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Zwei));
		btnNewButton_28.addActionListener(this);
		btnNewButton_28.setActionCommand("btnNewButton_28");
		panel.add(btnNewButton_28);
		
		JLabel label_9 = new JLabel("");
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("");
		panel.add(label_10);
		
		JButton btnNewButton_31 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Eins));
		btnNewButton_31.addActionListener(this);
		btnNewButton_31.setActionCommand("btnNewButton_31");
		panel.add(btnNewButton_31);
		
		JButton btnNewButton_32 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Zwei, EPositionIndex.Eins));
		btnNewButton_32.addActionListener(this);
		btnNewButton_32.setActionCommand("btnNewButton_32");
		panel.add(btnNewButton_32);
		
		JButton btnNewButton_33 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Eins));
		btnNewButton_33.addActionListener(this);
		btnNewButton_33.setActionCommand("btnNewButton_33");
		panel.add(btnNewButton_33);
		
		JLabel label_11 = new JLabel("");
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("");
		panel.add(label_12);
		
		JLabel label_13 = new JLabel("");
		panel.add(label_13);
		
		JButton btnNewButton_37 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Eins));
		btnNewButton_37.addActionListener(this);
		btnNewButton_37.setActionCommand("btnNewButton_37");
		panel.add(btnNewButton_37);
		
		JLabel label_14 = new JLabel("");
		panel.add(label_14);
		
		JButton btnNewButton_39 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Zwei, EPositionIndex.Eins));
		btnNewButton_39.addActionListener(this);
		btnNewButton_39.setActionCommand("btnNewButton_39");
		panel.add(btnNewButton_39);
		
		JLabel label_15 = new JLabel("");
		panel.add(label_15);
		
		JButton btnNewButton_41 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Eins));
		btnNewButton_41.addActionListener(this);
		btnNewButton_41.setActionCommand("btnNewButton_41");
		panel.add(btnNewButton_41);
		
		JLabel label_16 = new JLabel("");
		panel.add(label_16);
		
		JButton btnNewButton_43 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Eins));
		btnNewButton_43.addActionListener(this);
		btnNewButton_43.setActionCommand("btnNewButton_43");
		panel.add(btnNewButton_43);
		
		JLabel label_17 = new JLabel("");
		panel.add(label_17);
		
		JLabel label_18 = new JLabel("");
		panel.add(label_18);
		
		JButton btnNewButton_46 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Zwei, EPositionIndex.Eins));
		btnNewButton_46.addActionListener(this);
		btnNewButton_46.setActionCommand("btnNewButton_46");
		panel.add(btnNewButton_46);
		
		JLabel label_19 = new JLabel("");
		panel.add(label_19);
		
		JLabel label_20 = new JLabel("");
		panel.add(label_20);
		
		JButton btnNewButton_49 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Eins));
		btnNewButton_49.addActionListener(this);
		btnNewButton_49.setActionCommand("btnNewButton_49");
		panel.add(btnNewButton_49);
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String a = "1";
		int b = 0;
		b = b + (int)a;
		String name = e.getActionCommand();
		try {
			Object o = Class.forName(name);
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Position lPosiiton = o.getPosition();
		System.out.println(name);
		
	}
		
	}


