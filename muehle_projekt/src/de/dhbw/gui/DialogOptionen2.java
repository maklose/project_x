package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
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
		setBounds(100, 100, 450, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 225, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{20, 0, 0, 0, 0, 20, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblOption = new JLabel("  Bitte w\u00E4hlen Sie eine Option aus.");
				GridBagConstraints gbc_lblOption = new GridBagConstraints();
				gbc_lblOption.anchor = GridBagConstraints.WEST;
				gbc_lblOption.insets = new Insets(0, 0, 5, 5);
				gbc_lblOption.gridx = 1;
				gbc_lblOption.gridy = 1;
				panel.add(lblOption, gbc_lblOption);
			}
			{
				JRadioButton rdbtnMgM = new JRadioButton("Mensch gegen Mensch");
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
			{
				JRadioButton rdbtnCgC = new JRadioButton("Computer gegen Computer");
				rdbtnCgC.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txtSpieler1.setEnabled(false);
						txtSpieler2.setEnabled(false);
					}
				});
				buttonGroup.add(rdbtnCgC);
				GridBagConstraints gbc_rdbtnCgC = new GridBagConstraints();
				gbc_rdbtnCgC.anchor = GridBagConstraints.WEST;
				gbc_rdbtnCgC.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnCgC.gridx = 1;
				gbc_rdbtnCgC.gridy = 4;
				panel.add(rdbtnCgC, gbc_rdbtnCgC);
			}
			{
				JLabel lblNamen = new JLabel("  Geben Sie einen Namen ein.");
				GridBagConstraints gbc_lblNamen = new GridBagConstraints();
				gbc_lblNamen.anchor = GridBagConstraints.WEST;
				gbc_lblNamen.insets = new Insets(0, 0, 5, 5);
				gbc_lblNamen.gridx = 1;
				gbc_lblNamen.gridy = 6;
				panel.add(lblNamen, gbc_lblNamen);
			}
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
				gbc_txtSpieler1.gridy = 7;
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
				gbc_txtSpieler2.insets = new Insets(0, 0, 0, 5);
				gbc_txtSpieler2.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtSpieler2.gridx = 1;
				gbc_txtSpieler2.gridy = 8;
				panel.add(txtSpieler2, gbc_txtSpieler2);
				txtSpieler2.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//option = ...
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
