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

public class DialogOptionen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogOptionen dialog = new DialogOptionen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogOptionen() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(5, 0, 0, 0));
		{
			JLabel lblNewLabel = new JLabel("Bitte w\u00E4hlen Sie eine Option aus!");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
			contentPanel.add(lblNewLabel);
		}
		{
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Mensch gegen Mensch");
			buttonGroup.add(rdbtnNewRadioButton);
			contentPanel.add(rdbtnNewRadioButton);
		}
		{
			JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Mensch gegen Computer");
			buttonGroup.add(rdbtnNewRadioButton_1);
			contentPanel.add(rdbtnNewRadioButton_1);
		}
		{
			JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Computer gegen Computer");
			buttonGroup.add(rdbtnNewRadioButton_2);
			contentPanel.add(rdbtnNewRadioButton_2);
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

	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
