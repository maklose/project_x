package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WillkommensGUI extends JFrame {

	private JPanel contentPane;
	static int xPosition = 93;					//Position des Ladebalkens
	static int xPosition1 = 53;
	static int xPosition2 = 13;
	static int xPosition3 = -27;
	static int xPosition4 = -67;

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
				        	sleep(4000);
				        	
				        	Empfangsgui2 neu = new Empfangsgui2();
				        	
				        	sleep(300);
				        	neu.setVisible(true);
				        	frame.dispose();
				        }
				        catch ( InterruptedException e ) { }
				      } 
				};
				
				
				
				
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WillkommensGUI() {
						
		
		super("Mühle - Das Brettspiel für Groß und Klein");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 456);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		

		
		final JPanel panel = new JPanel(){  
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) 
			{  
	        	super.paintComponent(g);
	        	final Image hintergrund = Toolkit.getDefaultToolkit().getImage(  
	                      Empfangsgui2.class.getResource("/de/dhbw/images/Willkommen_GUI.PNG"));  
	        	final Image ladebalken = Toolkit.getDefaultToolkit().getImage(  
	                    Empfangsgui2.class.getResource("/de/dhbw/images/Ladebalken.PNG"));
	        	
	        	 g.drawImage(hintergrund, 0, 0, this.getWidth(), this.getHeight(), this); 
	        	
	        	if(xPosition >= 580)
	                xPosition = -150;
	        	if(xPosition1 >= 580)
	                xPosition1 = -150;
	        	if(xPosition2 >= 580)
	                xPosition2 = -150;
	        	if(xPosition3 >= 580)
	                xPosition3 = -150;
	        	if(xPosition4 >= 580)
	                xPosition4 = -150;
	           
	        	if(xPosition >= 93)
	        		g.drawImage(ladebalken, xPosition , 417, 30 ,17 , this);	      
	        	if(xPosition1 >= 93)	        	
	        		g.drawImage(ladebalken, xPosition1, 417, 30 ,17 , this);	    
	        	if(xPosition2 >= 93)	    
	        		g.drawImage(ladebalken, xPosition2, 417, 30 ,17 , this);   
	        	if(xPosition3 >= 93)	        	
	        		g.drawImage(ladebalken, xPosition3, 417, 30 ,17, this);	        	
	        	if(xPosition4 >= 93)
	        		g.drawImage(ladebalken, xPosition4, 417, 30 ,17 , this);
	        	
			}};
       
       new Thread() 
		{
			{
				start(); 
			}
			public void run()
			{
				try 
				{ 
					 for(int i = 0; i < 10000; i++)
		            {
						WillkommensGUI.zaehle();
		            	panel.repaint();	
		            	sleep(3);
		            }

				}
				catch ( InterruptedException e ) 
				{ 
					e.printStackTrace();
				} 
			} 
		};  //Thread zum warten
       
		contentPane.add(panel, BorderLayout.CENTER);
	}

	protected static void zaehle() {
	
		xPosition += 1;
		xPosition1 += 1;
		xPosition2 += 1;
		xPosition3 += 1;
		xPosition4 += 1;
		
	}
	
	
}
