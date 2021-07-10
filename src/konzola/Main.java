package konzola;

import java.util.Scanner;

import logika.CandyCrush;

/**
 * @author zerina
 *
 */
public class Main {
	public static void main(String[] args) {
		CandyCrush candy_crush = new CandyCrush(7);
		int broj_bodova= candy_crush.dajBrojBodova();
		int x1, x2, y1, y2;
		
		Scanner ulaz = new Scanner(System.in);
		while (candy_crush.dajBrojPoteza() > 0) {
			System.out.println("Unesi potez: ");
			System.out.print("x1: ");
			x1 = ulaz.nextInt();
			System.out.print("y1: ");
			y1 = ulaz.nextInt();
			System.out.print("x2: ");
			x2 = ulaz.nextInt();
			System.out.print("y2: ");
			y2 = ulaz.nextInt();
			candy_crush.igraj(x1, y1, x2, y2);
			if (candy_crush.jeLiKraj()) {
				if (candy_crush.jeLiPobjeda()) {
					System.out.println("YOU WIN!");
					break;
				}
				else 
					System.out.println("Out of moves.");
			}
		}
		ulaz.close();

	}

}
