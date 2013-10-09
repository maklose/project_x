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
	
	TransparentButtonFeld btnNewButton_1;
	TransparentButtonFeld btnNewButton_4;
	TransparentButtonFeld btnNewButton_6;
	TransparentButtonFeld btnNewButton_9;
	TransparentButtonFeld btnNewButton_10;
	TransparentButtonFeld btnNewButton_13;
	TransparentButtonFeld btnNewButton_15;
	TransparentButtonFeld btnNewButton_18;
	TransparentButtonFeld btnNewButton_19;
	TransparentButtonFeld btnNewButton_22;
	TransparentButtonFeld btnNewButton_23;
	TransparentButtonFeld btnNewButton_24;
	TransparentButtonFeld btnNewButton_26;
	TransparentButtonFeld btnNewButton_27;
	TransparentButtonFeld btnNewButton_28;
	TransparentButtonFeld btnNewButton_31;
	TransparentButtonFeld btnNewButton_32;
	TransparentButtonFeld btnNewButton_33;
	TransparentButtonFeld btnNewButton_37;
	TransparentButtonFeld btnNewButton_39;
	TransparentButtonFeld btnNewButton_41;
	TransparentButtonFeld btnNewButton_43;
	TransparentButtonFeld btnNewButton_46;
	TransparentButtonFeld btnNewButton_49;
	

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
	
		btnNewButton_1 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Drei));
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setActionCommand("btnNewButton_1");
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		btnNewButton_4 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Zwei, EPositionIndex.Drei));
		btnNewButton_4.addActionListener(this);
		btnNewButton_4.setActionCommand("btnNewButton_4");
		panel.add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2);
		
		btnNewButton_6 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Drei));
		btnNewButton_6.addActionListener(this);
		btnNewButton_6.setActionCommand("btnNewButton_6");
		panel.add(btnNewButton_6);
		
		JLabel label_1 = new JLabel("");
		panel.add(label_1);
		
		btnNewButton_9 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Drei));
		btnNewButton_9.addActionListener(this);
		btnNewButton_9.setActionCommand("btnNewButton_9");
		panel.add(btnNewButton_9);
		
		JLabel label_2 = new JLabel("");
		panel.add(label_2);
		
		btnNewButton_10 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Zwei, EPositionIndex.Drei));
		btnNewButton_10.addActionListener(this);
		btnNewButton_10.setActionCommand("btnNewButton_10");
		panel.add(btnNewButton_10);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3);
		
		btnNewButton_13 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Drei));
		btnNewButton_13.addActionListener(this);
		btnNewButton_13.setActionCommand("btnNewButton_13");
		panel.add(btnNewButton_13);
		
		JLabel label_3 = new JLabel("");
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("");
		panel.add(label_5);
		
		btnNewButton_15 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Drei));
		btnNewButton_15.addActionListener(this);
		btnNewButton_15.setActionCommand("btnNewButton_15");
		panel.add(btnNewButton_15);
		
		btnNewButton_18 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Zwei, EPositionIndex.Drei));
		btnNewButton_18.addActionListener(this);
		btnNewButton_18.setActionCommand("btnNewButton_18");
		panel.add(btnNewButton_18);
		
		btnNewButton_19 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Drei));
		btnNewButton_19.addActionListener(this);
		btnNewButton_19.setActionCommand("btnNewButton_19");
		panel.add(btnNewButton_19);
		
		JLabel label_6 = new JLabel("");
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("");
		panel.add(label_7);
		
		btnNewButton_22 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Zwei));
		btnNewButton_22.addActionListener(this);
		btnNewButton_22.setActionCommand("btnNewButton_22");
		panel.add(btnNewButton_22);
		
		btnNewButton_23 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Zwei));
		btnNewButton_23.addActionListener(this);
		btnNewButton_23.setActionCommand("btnNewButton_23");
		panel.add(btnNewButton_23);
		
		btnNewButton_24 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Zwei));
		btnNewButton_24.addActionListener(this);
		btnNewButton_24.setActionCommand("btnNewButton_24");
		panel.add(btnNewButton_24);
		
		JLabel label_8 = new JLabel("");
		panel.add(label_8);
		
		btnNewButton_26 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Zwei));
		btnNewButton_26.addActionListener(this);
		btnNewButton_26.setActionCommand("btnNewButton_26");
		panel.add(btnNewButton_26);
		
		btnNewButton_27 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Zwei));
		btnNewButton_27.addActionListener(this);
		btnNewButton_27.setActionCommand("btnNewButton_27");
		panel.add(btnNewButton_27);
		
		btnNewButton_28 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Drei, EPositionIndex.Zwei));
		btnNewButton_28.addActionListener(this);
		btnNewButton_28.setActionCommand("btnNewButton_28");
		panel.add(btnNewButton_28);
		
		JLabel label_9 = new JLabel("");
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("");
		panel.add(label_10);
		
		btnNewButton_31 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Eins, EPositionIndex.Eins));
		btnNewButton_31.addActionListener(this);
		btnNewButton_31.setActionCommand("btnNewButton_31");
		panel.add(btnNewButton_31);
		
		btnNewButton_32 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Zwei, EPositionIndex.Eins));
		btnNewButton_32.addActionListener(this);
		btnNewButton_32.setActionCommand("btnNewButton_32");
		panel.add(btnNewButton_32);
		
		btnNewButton_33 = new TransparentButtonFeld("", new Position(EPositionIndex.Drei, EPositionIndex.Drei, EPositionIndex.Eins));
		btnNewButton_33.addActionListener(this);
		btnNewButton_33.setActionCommand("btnNewButton_33");
		panel.add(btnNewButton_33);
		
		JLabel label_11 = new JLabel("");
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("");
		panel.add(label_12);
		
		JLabel label_13 = new JLabel("");
		panel.add(label_13);
		
		btnNewButton_37 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Eins, EPositionIndex.Eins));
		btnNewButton_37.addActionListener(this);
		btnNewButton_37.setActionCommand("btnNewButton_37");
		panel.add(btnNewButton_37);
		
		JLabel label_14 = new JLabel("");
		panel.add(label_14);
		
		btnNewButton_39 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Zwei, EPositionIndex.Eins));
		btnNewButton_39.addActionListener(this);
		btnNewButton_39.setActionCommand("btnNewButton_39");
		panel.add(btnNewButton_39);
		
		JLabel label_15 = new JLabel("");
		panel.add(label_15);
		
		btnNewButton_41 = new TransparentButtonFeld("", new Position(EPositionIndex.Zwei, EPositionIndex.Drei, EPositionIndex.Eins));
		btnNewButton_41.addActionListener(this);
		btnNewButton_41.setActionCommand("btnNewButton_41");
		panel.add(btnNewButton_41);
		
		JLabel label_16 = new JLabel("");
		panel.add(label_16);
		
		btnNewButton_43 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Eins, EPositionIndex.Eins));
		btnNewButton_43.addActionListener(this);
		btnNewButton_43.setActionCommand("btnNewButton_43");
		panel.add(btnNewButton_43);
		
		JLabel label_17 = new JLabel("");
		panel.add(label_17);
		
		JLabel label_18 = new JLabel("");
		panel.add(label_18);
		
		btnNewButton_46 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Zwei, EPositionIndex.Eins));
		btnNewButton_46.addActionListener(this);
		btnNewButton_46.setActionCommand("btnNewButton_46");
		panel.add(btnNewButton_46);
		
		JLabel label_19 = new JLabel("");
		panel.add(label_19);
		
		JLabel label_20 = new JLabel("");
		panel.add(label_20);
		
		btnNewButton_49 = new TransparentButtonFeld("", new Position(EPositionIndex.Eins, EPositionIndex.Zwei, EPositionIndex.Eins));
		btnNewButton_49.addActionListener(this);
		btnNewButton_49.setActionCommand("btnNewButton_49");
		panel.add(btnNewButton_49);
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.equals(this.btnNewButton_1))
		{
			this.aktion(btnNewButton_1);
		}
		else if(e.equals(this.btnNewButton_4))
		{
			
		}
		else if(e.equals(this.btnNewButton_6))
		{
			
		}
		else if(e.equals(this.btnNewButton_9))
		{
			
		}
		else if(e.equals(this.btnNewButton_10))
		{
			
		}
		else if(e.equals(this.btnNewButton_13))
		{
			
		}
		else if(e.equals(this.btnNewButton_15))
		{
			
		}
		else if(e.equals(this.btnNewButton_18))
		{
			
		}
		else if(e.equals(this.btnNewButton_19))
		{
			
		}
		else if(e.equals(this.btnNewButton_22))
		{
			
		}
		else if(e.equals(this.btnNewButton_23))
		{
			
		}
		else if(e.equals(this.btnNewButton_24))
		{
			
		}
		else if(e.equals(this.btnNewButton_26))
		{
			
		}
		else if(e.equals(this.btnNewButton_27))
		{
			
		}
		else if(e.equals(this.btnNewButton_28))
		{
			
		}
		else if(e.equals(this.btnNewButton_31))
		{
			
		}
		else if(e.equals(this.btnNewButton_32))
		{
			
		}
		else if(e.equals(this.btnNewButton_33))
		{
			
		}
		else if(e.equals(this.btnNewButton_37))
		{
			
		}
		else if(e.equals(this.btnNewButton_39))
		{
			
		}
		else if(e.equals(this.btnNewButton_41))
		{
			
		}
		else if(e.equals(this.btnNewButton_43))
		{
			
		}
		else if(e.equals(this.btnNewButton_46))
		{
			
		}
		else if(e.equals(this.btnNewButton_49))
		{
			
		}
	}
	
	public void aktion(TransparentButtonFeld lButton)
	{
		System.out.println(lButton.getPosition().toString());
	}
		
}


