package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Meni extends JFrame {
	private JPanel panel;
	private JLabel slika;
	private JButton igraj;
	Prozor prozor;
	int n;

	/**
	 * Konstruktor
	 */
	public Meni() {
		build();
	}
	public Meni(int n) {
		this.n=n;
		build();
	}

	/**
	 * Kreiraj prozor
	 */
	private void build() {
		prozor = new Prozor(n);
		this.setTitle("igra");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout());

		panel = new JPanel();
		this.add(panel);

		slika = new JLabel();
		igraj = new JButton("Play");
		panel.setLayout(new GridLayout());
		slika.setLayout(new GridBagLayout());
		igraj.setPreferredSize(new Dimension(100, 50));

		slika.setIcon(new ImageIcon("slike/naslovna.png"));
		panel.add(slika);
		GridBagConstraints c = new GridBagConstraints();
		JLabel l = new JLabel();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		slika.add(l, c);
		c.weightx = 0;
		c.weighty = 0;

		c.gridx = 0;
		c.gridy = 1;
		slika.add(igraj, c);
		pack();
		this.setLocationRelativeTo(null);

		igraj.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				prozor.prikaziProzor();
				unistiProzor();
			}
		});
	}

	/**
	 * Prikazi meni
	 */
	public void prikaziMeni() {
		this.setVisible(true);
	}

	/**
	 * Unisti prozor
	 */
	private void unistiProzor() {
		this.dispose();
	}
}
