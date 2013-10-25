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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class BestaetigungBeenden extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BestaetigungBeenden dialog = new BestaetigungBeenden(100,100);
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
	public BestaetigungBeenden(int xPos, int yPos) {
		//Fenster
		setBounds(xPos, yPos, 300, 230);
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
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{30, 106, 30, 106, 30, 0};
			gbl_panel.rowHeights = new int[]{150, 40, 30, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				//Button "OK"
				JButton okButton = new JButton(){
				public void paintComponent(Graphics g){
					g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Ja.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
				};
			};
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.fill = GridBagConstraints.BOTH;
				gbc_okButton.insets = new Insets(0, 0, 5, 5);
				gbc_okButton.gridx = 1;
				gbc_okButton.gridy = 1;
				panel.add(okButton, gbc_okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if( listener != null )
						{
							listener.onOK();
						}
					}
				});
				okButton.setActionCommand("OK");
			}
			{
				//Button "Cancel"
				JButton cancelButton = new JButton(){
					public void paintComponent(Graphics g){
						g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Nein.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
					}
				};
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
				gbc_cancelButton.fill = GridBagConstraints.BOTH;
				gbc_cancelButton.gridx = 3;
				gbc_cancelButton.gridy = 1;
				panel.add(cancelButton, gbc_cancelButton);
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
