import java.util.Scanner;

public class WeightOnOtherPlanets {

	public static void main(String[] args) {

		// Gravity numbers via http://www.universetoday.com/35565/gravity-on-other-planets/
		final String[] planets = {"Venus", "Mercury", "The Moon", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Cardoorium Prime"};
		final double[] planetWeights = {0.904, 0.38, 0.1654, 0.38, 2.528, 1.065, 0.886, 1.14, 3104558};
		Scanner scan = new Scanner(System.in);
		double weightOnNewPlanet = 0;
		int selectedPlanet = 0;

		System.out.print("What is your weight on the Earth? ");
		int earthWeight = scan.nextInt();
		scan.nextLine();
		System.out.println("    Planet              Gravity");
		for (int i = 0; i < planets.length; i++) {
			System.out.print("(" + (i + 1) + ") " + planets[i]);
			for (int j = 0; j < 20 - planets[i].length(); j++) {
				System.out.print(".");
			}
			System.out.println(planetWeights[i]);
		}
		System.out.print("Please select a planet (#" + 1 + "-" + planets.length + "): ");
		while (selectedPlanet < 1 || selectedPlanet > planets.length) {
			selectedPlanet = scan.nextInt();
			if (selectedPlanet < 1 || selectedPlanet > planets.length) {
				System.out.println("Selected planet # must be between " + 1 + " and " + planets.length);
			}
		}
		weightOnNewPlanet = earthWeight * planetWeights[selectedPlanet - 1];
		System.out.println("Your weight on " + planets[selectedPlanet - 1] + " would be " + weightOnNewPlanet);
	}
}