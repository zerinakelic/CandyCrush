package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logika.CandyCrush;

/**
 * @author zerina
 *
 */
public class Prozor extends JFrame {
	private int n;
	private int brojac = 0;
	private JButton[][] dugmad;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel broj_poteza_num;
	private JLabel broj_poteza_txt;
	private JLabel broj_bodova_num;
	private JLabel broj_bodova_txt;
	private JLabel level_txt;
	private JLabel prazno_polje;
	private int xPrviKlik, yPrviKlik; // koordinate prvi klik
	private Boolean pobjeda;
	CandyCrush candyCrush;
	Kraj kraj;
	NextLevel next_level;

	/**
	 * Konstruktor bez parametara
	 */
	public Prozor() {
		n = 7;
		build();
	}

	/**
	 * Konstruktor sa parametrom
	 * @param n
	 */
	public Prozor(int n) {
		this.n = n;
		build();
	}

	/**
	 * Kreiraj prozor
	 */
	private void build() {
		kraj = new Kraj();
		next_level = new NextLevel();
		candyCrush = new CandyCrush(n);
		dugmad = new JButton[n][n];

		this.setTitle("igra");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		panel2 = new JPanel();
		c.gridx = 1;
		c.gridy = 0;
		this.add(panel2, c);

		panel1 = new JPanel();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		this.add(panel1, c);

		panel1.setLayout(new GridLayout(n, n));
		panel2.setLayout(new GridLayout(6, 1));

		broj_poteza_txt = new JLabel("Moves: ");
		broj_poteza_num = new JLabel();
		broj_poteza_num.setText(String.valueOf(candyCrush.dajBrojPoteza()));
		broj_bodova_txt = new JLabel("Points: ");
		broj_bodova_num = new JLabel(String.valueOf(0));
		prazno_polje = new JLabel();
		level_txt = new JLabel("Collect " + candyCrush.dajPrag() + " points!");

		level_txt.setFont(new Font("Serif", Font.PLAIN, 30));
		broj_poteza_txt.setFont(new Font("Serif", Font.PLAIN, 20));
		broj_poteza_num.setFont(new Font("Serif", Font.PLAIN, 30));
		broj_bodova_txt.setFont(new Font("Serif", Font.PLAIN, 20));
		broj_bodova_num.setFont(new Font("Serif", Font.PLAIN, 30));

		panel2.add(level_txt);
		panel2.add(prazno_polje);
		panel2.add(broj_poteza_txt);
		panel2.add(broj_poteza_num);
		panel2.add(broj_bodova_txt);
		panel2.add(broj_bodova_num);

		generisiPocetak();
		pack();
		this.setLocationRelativeTo(null);
	}

	/**
	 * Generisi pocetak 
	 */
	private void generisiPocetak() {

		Dimension dim = new Dimension(90, 90);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dugmad[i][j] = new JButton(postaviCandy(i, j));
				dugmad[i][j].setPreferredSize(dim);
				panel1.add(dugmad[i][j]);
			}
		}

		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (dugmad[i][j] == b) {
							brojac++;
							if (brojac % 2 == 1) { // prvi klik

								xPrviKlik = i; // sacuvamo koordinate prvog klika
								yPrviKlik = j;
							} // drugi klik
							else {

								candyCrush.igraj(xPrviKlik, yPrviKlik, i, j); // saljemo koordinate prvog i drugog klika
								broj_poteza_num.setText(String.valueOf(candyCrush.dajBrojPoteza()));
								broj_bodova_num.setText(String.valueOf(candyCrush.dajBrojBodova()));
								update();
								if (candyCrush.jeLiKraj()) {
									if (candyCrush.jeLiPobjeda())
										pobjeda = true;
									else
										pobjeda = false;

									if (pobjeda == false)
										kraj.prikaziKraj();
									else
										next_level.prikaziNextLevel();
									unistiProzor();
								}
							}
						}
					}
				}
			}
		};

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				dugmad[i][j].addActionListener(buttonListener);
		}
	}

	/**
	 * Nakon poteza update candy
	 */
	private void update() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (candyCrush.dajMatricu()[i][j] != candyCrush.dajKopiju()[i][j]) {
					dugmad[i][j].setIcon(postaviCandy(i, j));
				}
			}
		}

	}

	/**
	 * Postavi candy na osnovu matrice iz logike
	 * @param x
	 * @param y
	 * @return
	 */
	private Icon postaviCandy(int x, int y) {

		Icon ic = new ImageIcon("slike/choko.png");

		if (candyCrush.dajMatricu()[x][y] == 0)
			ic = new ImageIcon("slike/blue.png");
		else if (candyCrush.dajMatricu()[x][y] == 1)
			ic = new ImageIcon("slike/red.png");
		else if (candyCrush.dajMatricu()[x][y] == 2)
			ic = new ImageIcon("slike/green.png");
		else if (candyCrush.dajMatricu()[x][y] == 3)
			ic = new ImageIcon("slike/yellow.png");
		else if (candyCrush.dajMatricu()[x][y] == 4)
			ic = new ImageIcon("slike/purple.png");
		else if (candyCrush.dajMatricu()[x][y] == 5)
			ic = new ImageIcon("slike/orange.png");

		return ic;
	}

	/**
	 * Prikazi Prozor
	 */
	public void prikaziProzor() {
		this.setVisible(true);
	}

	/**
	 * Unisti prozor
	 */
	private void unistiProzor() {
		this.dispose();
	}

	/**
	 * Je li pobjeda
	 * @return
	 */
	public Boolean jeLiPobjeda() {
		return pobjeda;
	}

}
