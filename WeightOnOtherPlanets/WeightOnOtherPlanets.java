import java.util.Scanner;

public class WeightOnOtherPlanets {

	public static void main(String[] args) {

		// Planet objects weight attributes would be really helpful here
		// Gravity numbers via http://www.universetoday.com/35565/gravity-on-other-planets/
		final String[] planets = {"Venus", "The Moon", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Cardoorium Prime"};
		final double[] planetWeights = {0.904, 0.1654, 0.38, 2.528, 1.065, 0.886, 1.14, 3104558};
		Scanner scan = new Scanner(System.in);
		double weightOnNewPlanet = 0;

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
		char selectedPlanet = scan.nextLine().toLowerCase().charAt(0);
		// rip
		// weightOnNewPlanet = earthWeight * planetWeights[selectedPlanet - 1];
		switch (selectedPlanet) {
			case 'v':
				weightOnNewPlanet = earthWeight * planetWeights[0];
				System.out.println("Your weight on " + planets[0] + " would be " + weightOnNewPlanet);
				break;
			case 't':
				weightOnNewPlanet = earthWeight * planetWeights[1];
				System.out.println("Your weight on " + planets[1] + " would be " + weightOnNewPlanet);
				break;
			case 'm':
				weightOnNewPlanet = earthWeight * planetWeights[2];
				System.out.println("Your weight on " + planets[2] + " would be " + weightOnNewPlanet);
				break;
			case 'j':
				weightOnNewPlanet = earthWeight * planetWeights[3];
				System.out.println("Your weight on " + planets[3] + " would be " + weightOnNewPlanet);
				break;
			case 's':
				weightOnNewPlanet = earthWeight * planetWeights[4];
				System.out.println("Your weight on " + planets[4] + " would be " + weightOnNewPlanet);
				break;
			case 'u':
				weightOnNewPlanet = earthWeight * planetWeights[5];
				System.out.println("Your weight on " + planets[5] + " would be " + weightOnNewPlanet);
				break;
			case 'n':
				weightOnNewPlanet = earthWeight * planetWeights[6];
				System.out.println("Your weight on " + planets[6] + " would be " + weightOnNewPlanet);
				break;
			case 'c':
				weightOnNewPlanet = earthWeight * planetWeights[7];
				System.out.println("Your weight on " + planets[7] + " would be " + weightOnNewPlanet);
				break;
			default:
				System.out.println("Your weight in the void is nonexistent");
		}
	}
}