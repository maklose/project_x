package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WillkommensGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() 
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
				        	WillkommensGUI frame = new WillkommensGUI();
							frame.setVisible(true);
				        	sleep(3 * 1000);
				        	
				        	Empfangsgui2 neu = new Empfangsgui2();
				        	
				        	sleep(300);
				        	neu.setVisible(true);
				        	frame.dispose();
				        }
				        catch ( InterruptedException e ) { }
				      } 
				};
				
				
				
				
				/*
				
				try {
					WillkommensGUI frame = new WillkommensGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}*/
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WillkommensGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 456);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel(){  
            public void paintComponent(Graphics g) {  
                Image hintergrund = Toolkit.getDefaultToolkit().getImage(  
                          Empfangsgui2.class.getResource("/de/dhbw/images/Willkommen_GUI.PNG"));  
            g.drawImage(hintergrund, 0, 0, this.getWidth(), this.getHeight(), this);  
       }}  ;
		contentPane.add(panel, BorderLayout.CENTER);
	}

}
