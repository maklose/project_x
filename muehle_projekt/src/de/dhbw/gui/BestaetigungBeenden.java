package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.GridLayout;

import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BestaetigungBeenden extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BestaetigungBeenden dialog = new BestaetigungBeenden();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private BestatigungsListener listener = null;
	/**
	 * Create the dialog.
	 */
	public BestaetigungBeenden() {
		//Fenster
		setBounds(100, 100, 300, 230);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			//Textfeld
			JPanel panel = new JPanel() {  
                public void paintComponent(Graphics g) {  
                     Image hintergrund = Toolkit.getDefaultToolkit().getImage(  
                               Empfangsgui2.class.getResource("/de/dhbw/images/Sicherheitsabfrage_GUI.PNG"));  
                 g.drawImage(hintergrund, 0, 0, this.getWidth(), this.getHeight(), this);  
            }  
          };  
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				//Button "OK"
				JButton okButton = new JButton(){
				public void paintComponent(Graphics g){
					g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Ja.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
				};
			};
				

				okButton.setBounds(28, 122, 92, 34);
				panel.add(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if( listener != null )
						{
							listener.onOK();
						}
					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				//Button "Cancel"
				JButton cancelButton = new JButton(){
					public void paintComponent(Graphics g){
						g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Nein.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
					}
				};	
				cancelButton.setBounds(151, 122, 92, 34);
				panel.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
		}
	}
	
	//Listener
	public void setListener(BestatigungsListener listener)
	{
		this.listener = listener;
	}
	
	public interface BestatigungsListener
	{
		public void onOK();
		public void onCancel();
	}

}
