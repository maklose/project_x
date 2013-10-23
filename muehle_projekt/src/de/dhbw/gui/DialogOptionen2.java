package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;

public class DialogOptionen2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtSpieler1;
	private JTextField txtSpieler2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogOptionen2 dialog = new DialogOptionen2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogOptionen2() {
		//Fenster
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			//Hintergrundbild
			JPanel panel = new JPanel(){  
                public void paintComponent(Graphics g) {  
                    Image hintergrund = Toolkit.getDefaultToolkit().getImage(  
                              Empfangsgui2.class.getResource("/de/dhbw/images/Optionen_GUI.PNG"));  
                g.drawImage(hintergrund, 0, 0, this.getWidth(), this.getHeight(), this);  
           }  
         }; 
         	//Layoutmanager
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{20, 225, 30, 80, 80, 20, 0};
			gbl_panel.rowHeights = new int[]{20, 20, 20, 20, 20, 0, 0, 0, 20, 0, 20, 20, 33, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			//Aufforderung zur Wahl einer Option
			{
				JLabel lblOption = new JLabel("  Bitte w\u00E4hlen Sie eine Option aus.");
				GridBagConstraints gbc_lblOption = new GridBagConstraints();
				gbc_lblOption.anchor = GridBagConstraints.WEST;
				gbc_lblOption.insets = new Insets(0, 0, 5, 5);
				gbc_lblOption.gridx = 1;
				gbc_lblOption.gridy = 1;
				panel.add(lblOption, gbc_lblOption);
			}
			//Radiobuttons und Gruppierung derselben
			{
				JRadioButton rdbtnMgM = new JRadioButton("Mensch gegen Mensch");
				rdbtnMgM.setOpaque(false);
				rdbtnMgM.setContentAreaFilled(false);
				rdbtnMgM.setBorderPainted(false);
				rdbtnMgM.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txtSpieler1.setEnabled(true);
						txtSpieler2.setEnabled(true);
					}
				});
				buttonGroup.add(rdbtnMgM);
				GridBagConstraints gbc_rdbtnMgM = new GridBagConstraints();
				gbc_rdbtnMgM.anchor = GridBagConstraints.WEST;
				gbc_rdbtnMgM.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnMgM.gridx = 1;
				gbc_rdbtnMgM.gridy = 2;
				panel.add(rdbtnMgM, gbc_rdbtnMgM);
			}
			{
				JRadioButton rdbtnMgC = new JRadioButton("Mensch gegen Computer");
				rdbtnMgC.setOpaque(false);
				rdbtnMgC.setContentAreaFilled(false);
				rdbtnMgC.setBorderPainted(false);
				rdbtnMgC.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txtSpieler1.setEnabled(true);
						txtSpieler2.setEnabled(false);
					}
				});
				buttonGroup.add(rdbtnMgC);
				GridBagConstraints gbc_rdbtnMgC = new GridBagConstraints();
				gbc_rdbtnMgC.anchor = GridBagConstraints.WEST;
				gbc_rdbtnMgC.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnMgC.gridx = 1;
				gbc_rdbtnMgC.gridy = 3;
				panel.add(rdbtnMgC, gbc_rdbtnMgC);
			}
			//Aufforderung zur Eingabe eines Spielernamens
			{	
				JLabel lblNamen = new JLabel("  Geben Sie einen Namen ein.");
				GridBagConstraints gbc_lblNamen = new GridBagConstraints();
				gbc_lblNamen.anchor = GridBagConstraints.WEST;
				gbc_lblNamen.insets = new Insets(0, 0, 5, 5);
				gbc_lblNamen.gridx = 1;
				gbc_lblNamen.gridy = 5;
				panel.add(lblNamen, gbc_lblNamen);
			}
			//Textfelder zur Eingabe des Spielernamens; zunächst deaktiviert
			{
				txtSpieler1 = new JTextField();
				txtSpieler1.setEnabled(false);
				txtSpieler1.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						txtSpieler1.setText("");
					}
				});
				txtSpieler1.setText("Name von Spieler 1");
				GridBagConstraints gbc_txtSpieler1 = new GridBagConstraints();
				gbc_txtSpieler1.insets = new Insets(0, 0, 5, 5);
				gbc_txtSpieler1.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtSpieler1.gridx = 1;
				gbc_txtSpieler1.gridy = 6;
				panel.add(txtSpieler1, gbc_txtSpieler1);
				txtSpieler1.setColumns(10);
			}
			{
				txtSpieler2 = new JTextField();
				txtSpieler2.setEnabled(false);
				txtSpieler2.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						txtSpieler2.setText("");
					}
				});
				txtSpieler2.setText("Name von Spieler 2");
				GridBagConstraints gbc_txtSpieler2 = new GridBagConstraints();
				gbc_txtSpieler2.insets = new Insets(0, 0, 5, 5);
				gbc_txtSpieler2.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtSpieler2.gridx = 1;
				gbc_txtSpieler2.gridy = 7;
				panel.add(txtSpieler2, gbc_txtSpieler2);
				txtSpieler2.setColumns(10);
			}
			{;
			}
			//Aufforderung zur Wahl des Schwierigkeitsgrades
			{
				JLabel lblSchwierigkeit = new JLabel("  W\u00E4hlen Sie einen Schwierigkeitsgrad.");
				GridBagConstraints gbc_lblSchwierigkeit = new GridBagConstraints();
				gbc_lblSchwierigkeit.anchor = GridBagConstraints.SOUTHWEST;
				gbc_lblSchwierigkeit.insets = new Insets(0, 0, 5, 5);
				gbc_lblSchwierigkeit.gridx = 1;
				gbc_lblSchwierigkeit.gridy = 9;
				panel.add(lblSchwierigkeit, gbc_lblSchwierigkeit);
			}
			//Drop-down-Liste zur Auswahl des Schwierigkeitsgrades; nicht editierbar zur Vermeidung von Falscheingaben
			{
				JComboBox cbSchwierigkeitsgrad = new JComboBox();
				cbSchwierigkeitsgrad.addItem("1 (leicht)");
				cbSchwierigkeitsgrad.addItem("2");
				cbSchwierigkeitsgrad.addItem("3");
				cbSchwierigkeitsgrad.addItem("4");
				cbSchwierigkeitsgrad.addItem("5");
				cbSchwierigkeitsgrad.addItem("6");
				cbSchwierigkeitsgrad.addItem("7");
				cbSchwierigkeitsgrad.addItem("8");
				cbSchwierigkeitsgrad.addItem("9");
				cbSchwierigkeitsgrad.addItem("10 (schwer)");
				GridBagConstraints gbc_cbSchwierigkeitsgrad = new GridBagConstraints();
				gbc_cbSchwierigkeitsgrad.insets = new Insets(0, 0, 5, 5);
				gbc_cbSchwierigkeitsgrad.fill = GridBagConstraints.HORIZONTAL;
				gbc_cbSchwierigkeitsgrad.gridx = 1;
				gbc_cbSchwierigkeitsgrad.gridy = 10;
				panel.add(cbSchwierigkeitsgrad, gbc_cbSchwierigkeitsgrad);
			}
			//Cancel-Button
			{
				JButton cancelButton = new JButton("Cancel"){
					public void paintComponent(Graphics g){
						g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Abbruch.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
					}
				};	
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.fill = GridBagConstraints.BOTH;
				gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
				gbc_cancelButton.gridx = 3;
				gbc_cancelButton.gridy = 12;
				panel.add(cancelButton, gbc_cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			//Spiel-starten-Button
			{
				JButton okButton = new JButton(""){
					public void paintComponent(Graphics g){
						g.drawImage(new ImageIcon(Empfangsgui2.class.getResource("/de/dhbw/images/Button Spiel starten.PNG")).getImage(), 0, 0, getWidth(), getHeight(), this);
					}};
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.fill = GridBagConstraints.BOTH;
				gbc_okButton.insets = new Insets(0, 0, 5, 5);
				gbc_okButton.gridx = 4;
				gbc_okButton.gridy = 12;
				panel.add(okButton, gbc_okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//JFrame neuesSpiel = new Spielfeld(name1, );
						//neuesSpiel.setVisible(true);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
