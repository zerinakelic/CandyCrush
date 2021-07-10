package logika;

import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import GUI.Prozor;

/**
 * @author zerina
 *
 */
public class CandyCrush {
	private int[][] matrica;
	private int[][] prijePoteza;
	private int n;
	private int broj_poteza = 20;
	private int broj_bodova = 0;
	private int prag = 500;
	Boolean clean = false;
	Boolean pogresan_potez = true;
	Boolean kraj = false;
	Boolean pobjeda = false;
	
	
	/**
	 * Konstruktor bez parametara
	 */
	public CandyCrush() {
		n=7;
		matrica = new int[n][n];
		prijePoteza = new int[n][n];
		generisiRandomMatricu();
	}

	/**
	 * Konstruktor sa parametrom
	 * @param n1
	 */
	public CandyCrush(int n1) {
		n = n1;
		matrica = new int[n][n];
		prijePoteza = new int[n][n];
		generisiRandomMatricu();
	}

	/**
	 * Generisi random matricu
	 */
	private void generisiRandomMatricu() {
		int rand = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				rand = random();
				matrica[i][j] = rand;
			}
		}
		while (clean == false)
			ocisti();
		ispisiMatricu();
	}

	/**
	 * Generisi random broj
	 * @return
	 */
	private int random() {
		return (0 + (int) (Math.random() * ((5 - 0) + 1)));
	}

	/**
	 * Get metoda, vrati trenutno stanje 
	 * @return
	 */
	public int[][] dajMatricu() {
		return matrica;
	}

	/**
	 * Get metoda, vrati matricu prije poteza
	 * @return
	 */
	public int[][] dajKopiju() {
		return prijePoteza;
	}

	/**
	 * Get metoda, vrati broj poteza
	 * @return
	 */
	public int dajBrojPoteza() {
		return broj_poteza;
	}

	/**
	 * Get metoda, vrati broj bodova
	 * @return
	 */
	public int dajBrojBodova() {
		return broj_bodova;
	}

	/**
	 * Get metoda, vrati prag
	 * @return
	 */
	public int dajPrag() {
		return prag;
	}

	/**
	 * @return
	 */
	public Boolean jeLiKraj() {
		return kraj;
	}

	/**
	 * @return
	 */
	public Boolean jeLiPobjeda() {
		return pobjeda;
	}

	/**
	 * Ocisti ako ima nekih kombinacija nakon generisanja pocetne matrice
	 * ili nakon poteza
	 */
	public void ocisti() {
		clean = true;
		for (int i = 0; i < n; i++) {
			int brojacDesno = 1;
			for (int j = 0; j < n; j++) { // gledamo donjeg i desnog susjeda kroz cijelu matricu
				int brojacDole = 1;
				if (i < n - 2 && matrica[i + 1][j] == matrica[i][j]) // donji
				{
					brojacDole++;
					while (i + brojacDole < n && matrica[i + brojacDole][j] == matrica[i][j]) {
						brojacDole++;
					}
					if (brojacDole >= 3) {
						clean = false;
						while (brojacDole > 0) {
							brojacDole--;
							matrica[i + brojacDole][j] = -1;
						}
					}
				}

				if (j < n - 2 && matrica[i][j + 1] == matrica[i][j]) // desni
				{
					brojacDesno++;
					while (j + brojacDesno < n && matrica[i][j + brojacDesno] == matrica[i][j]) {
						brojacDesno++;
					}
					if (brojacDesno >= 3) {
						clean = false;
						while (brojacDesno > 0) {
							brojacDesno--;
							matrica[i][j + brojacDesno] = -1;
						}
					} else
						brojacDesno = 1;
				}

			}
		}
		spusti();
	}

	/**
	 * Provjeri da li su susjedi
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	private Boolean susjed(int x1, int y1, int x2, int y2) {
		if ((x1 == x2 && y1 == y2 - 1) || (x1 == x2 && y1 == y2 + 1) || (y1 == y2 && x1 == x2 + 1)
				|| (y1 == y2 && x1 == x2 - 1))
			return true;
		else
			return false;
	}

	/**
	 * Glavni algoritam
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void igraj(int x1, int y1, int x2, int y2) {

		if (susjed(x1, y1, x2, y2) && broj_poteza > 0) {
			napraviKopiju();
			pogresan_potez = true;
			int brojac = 0;
			zamijeni(x1, y1, x2, y2);
			if (y1 == y2) {
				for (int j = 0; j < n - 2; j++) {
					if (matrica[x1][j] != -1 && matrica[x1][j] == matrica[x1][j + 1]
							&& matrica[x1][j] == matrica[x1][j + 2]) {
						pogresan_potez = false;
						brojac = 3;
						while (j + brojac < n && matrica[x1][j] == matrica[x1][j + brojac]) {
							brojac++;
						}
						if (brojac == 3)
							broj_bodova += 20;
						else
							broj_bodova += 50;
						while (brojac > 0) {
							brojac--;
							matrica[x1][j + brojac] = -1;

						}

					}

				}

				brojac = 0;
				for (int j = 0; j < n - 2; j++) {
					if (matrica[x2][j] != -1 && matrica[x2][j] == matrica[x2][j + 1]
							&& matrica[x2][j] == matrica[x2][j + 2]) {
						pogresan_potez = false;
						brojac = 3;
						while (j + brojac < n && matrica[x2][j] == matrica[x2][j + brojac]) {
							brojac++;
						}
						if (brojac == 3)
							broj_bodova += 20;
						else
							broj_bodova += 50;
						while (brojac > 0) {
							brojac--;
							matrica[x2][j + brojac] = -1;
						}
					}
				}
				brojac = 0;
				for (int i = 0; i < n - 2; i++) {
					if (matrica[i][y1] != -1 && matrica[i][y1] == matrica[i + 1][y1]
							&& matrica[i][y1] == matrica[i + 2][y1]) {
						pogresan_potez = false;
						brojac = 3;
						while (i + brojac < n && matrica[i][y1] == matrica[i + brojac][y1]) {
							brojac++;
						}
						if (brojac == 3)
							broj_bodova += 20;
						else
							broj_bodova += 50;
						while (brojac > 0) {
							brojac--;
							matrica[i + brojac][y1] = -1;

						}

					}

				}
			}

			else if (x1 == x2) { 
				brojac = 0;
				for (int i = 0; i < n - 2; i++) {
					if (matrica[i][y1] != -1 && matrica[i][y1] == matrica[i + 1][y1]
							&& matrica[i][y1] == matrica[i + 2][y1]) {
						pogresan_potez = false;
						brojac = 3;
						while (i + brojac < n && matrica[i][y1] == matrica[i + brojac][y1]) {
							brojac++;
						}
						if (brojac == 3)
							broj_bodova += 20;
						else
							broj_bodova += 50;
						while (brojac > 0) {
							brojac--;
							matrica[i + brojac][y1] = -1;
						}
					}
				}

				brojac = 0;
				for (int i = 0; i < n - 2; i++) {
					if (matrica[i][y2] != -1 && matrica[i][y2] == matrica[i + 1][y2]
							&& matrica[i][y2] == matrica[i + 2][y2]) {
						pogresan_potez = false;
						brojac = 3;
						while (i + brojac < n && matrica[i][y2] == matrica[i + brojac][y2]) {
							brojac++;
						}
						if (brojac == 3)
							broj_bodova += 20;
						else
							broj_bodova += 50;
						while (brojac > 0) {
							brojac--;
							matrica[i + brojac][y2] = -1;
						}
					}
				}
				brojac = 0;
				for (int j = 0; j < n - 2; j++) {
					if (matrica[x1][j] != -1 && matrica[x1][j] == matrica[x1][j + 1]
							&& matrica[x1][j] == matrica[x1][j + 2]) {
						pogresan_potez = false;
						brojac = 3;
						while (j + brojac < n && matrica[x1][j] == matrica[x1][j + brojac]) {
							brojac++;
						}
						if (brojac == 3)
							broj_bodova += 20;
						else
							broj_bodova += 50;
						while (brojac > 0) {
							brojac--;
							matrica[x1][j + brojac] = -1;
						}
					}
				}
			}

			if (pogresan_potez == false) {
				broj_poteza--;
				ocisti();
				while (clean == false)
					ocisti();
				ispisiMatricu();
			} else {
				zamijeni(x1, y1, x2, y2);
				System.out.println("Ne mogu se zamijeniti");
			}
		} else {
			System.out.println("Nisu susjedi");
		}
		if (broj_bodova >= prag) {
			pobjeda = true;
			kraj = true;
		}
		if (broj_poteza == 0) {
			kraj = true;
		}
	}

	/**
	 * Spusti odozgo nakon odigranog poteza
	 */
	private void spusti() {
		for (int i = n - 1; i > 0; i--) {
			for (int j = 0; j < n; j++) {
				if (matrica[i][j] == -1) {
					int k = i;
					while (k - 1 > 0 && matrica[k - 1][j] == -1) {
						k--;
					}
					matrica[i][j] = matrica[k - 1][j];
					matrica[k - 1][j] = -1;
				}
			}
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				if (matrica[i][j] == -1) {
					matrica[i][j] = random();
				}
			}
		}

	}

	/**
	 * Kopija matrice prije odigranog poteza
	 */
	private void napraviKopiju() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				prijePoteza[i][j] = matrica[i][j];
			}
		}
	}

	/**
	 * Zamijeni mjesta
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	private void zamijeni(int x1, int y1, int x2, int y2) {
		int temp = matrica[x1][y1];
		matrica[x1][y1] = matrica[x2][y2];
		matrica[x2][y2] = temp;

	}

	/**
	 * Ispisi matricu
	 */

	public void ispisiMatricu() {
		System.out.print("   ");
		for(int i=0;i<n;i++) 
			System.out.print(i+" ");
		System.out.println();
		System.out.print("   ");
		for(int i=0;i<n;i++) 
			System.out.print(". ");
		System.out.println();
		for (int i = 0; i < n; i++) {
			System.out.print(i+ "- ");
			for (int j = 0; j < n; j++) {
				System.out.print(matrica[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
