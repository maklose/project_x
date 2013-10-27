package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableModel;
import de.dhbw.muehle_spiel.Database;

public class Spielverlauf extends JFrame {
	private JTable table;
	int Partie;
	Database db=new Database();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Spielverlauf frame = new Spielverlauf();
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
	public Spielverlauf() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JScrollBar scrollbar = new JScrollBar ();
		scrollPane.add(scrollbar);
		Vector SpaltenName={"Spieler", "Von", "Nach", "Muehle", "Gelöschter Stein"};
		String [][]protokoll=db.speichern_p(Partie);
		table = new JTable();
		table.setModel(new DefaultTableModel(
		
		));
		scrollPane.setViewportView(table);
	}
	
}
