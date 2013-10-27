package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import de.dhbw.muehle_spiel.Database;

public class Highscore1 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Database db=new Database();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Highscore1 frame = new Highscore1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Highscore1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Dispose on close?
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		String[][]highscore=db.speichern_h();
		String[]SpaltenName={"Anzahl Züge","Spieler"};
		
		table = new JTable();
		table.setModel(new DefaultTableModel(highscore, SpaltenName));
		
		
		scrollPane.setViewportView(table);
	}

}
