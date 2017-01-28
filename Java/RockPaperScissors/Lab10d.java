// © A+ Computer Science  -  www.apluscompsci.com

import java.util.Scanner;

public class Lab10d {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		String player = "";

		while (true) {
			while (true) {
				System.out.print(
					"Type (Rock/Paper/Scissors) and press enter: ");

				player = scan.nextLine();

				if (player.equalsIgnoreCase("Rock")
					|| player.equalsIgnoreCase("Paper")
					|| player.equalsIgnoreCase("Scissors")) {

					break; // sorry
				}
			}

			RockPaperScissors game = new RockPaperScissors(player);

			System.out.println(game);
		}
	}
}



