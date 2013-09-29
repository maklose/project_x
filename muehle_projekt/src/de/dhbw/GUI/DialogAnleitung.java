package de.dhbw.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class DialogAnleitung extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogAnleitung dialog = new DialogAnleitung();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogAnleitung() {
		setBounds(100, 100, 750, 400);
		getContentPane().setLayout(new BorderLayout());
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JTextArea txtrDieSpielregeln = new JTextArea();
				String anleitung = ("Die Spielregeln:\n\n\n"
						+ "1. Jeder Spieler erh�lt je 9 Spielsteine, die er abwechselnd auf einen der Kreuzungspunkte auf dem Spielbrett setzen darf.\n\n"
						+ "2. Wenn ein Spieler 3 Steine in einer Reihe hat (d.h. er macht eine M�hle),\n darf er einen Stein des Gegners vom Brett nehmen; der Stein scheidet aus.\n\n"
						+ "3. Steine in einer M�hle d�rfen nicht entfernt werden.\n\n"
						+ "4. Wenn alle Steine gesetzt wurden, ziehen die Spieler abwechselnd mit einem Stein auf ein benachbartes  freies Feld.\n\n"
						+ "5. Ein Spieler, der keinen g�ltigen Zug mehr machen darf, verliert das Spiel.\n\n"
						+ "6. Wenn ein Spieler nur noch drei Steine besitzt, darf er springen, d.h. einen seiner Steine auf ein beliebiges  freies Feld setzen.\n\n"
						+ "7. Wenn der Gegner nur noch drei Steine besitzt, darf man ihm auch einen Stein aus einer M�hle entfernen.\n\n"
						+ "8. Wer weniger als 3 Steine besitzt, verliert das Spiel.");
				txtrDieSpielregeln.setText(anleitung);
				panel.add(txtrDieSpielregeln, BorderLayout.CENTER);
			}
		}
	}

}
