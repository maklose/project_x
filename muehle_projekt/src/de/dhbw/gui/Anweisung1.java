package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JLabel;

public class Anweisung1 extends JFrame  {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Anweisung1(String Hinweis, int xPos, int yPos, int x, int y, Font lFont) 
	{
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(xPos, yPos, x, y);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSpielerIst = new JLabel(Hinweis);
		lblSpielerIst.setFont(lFont);
		panel.add(lblSpielerIst);
		
		setOpacity((float) 0.7);
	}

	
}
