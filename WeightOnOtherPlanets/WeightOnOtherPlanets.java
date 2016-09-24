import java.util.Scanner;

public class WeightOnOtherPlanets {

	public static void main(String[] args) {

		// Planet objects weight attributes would be really helpful here
		// Gravity numbers via http://www.universetoday.com/35565/gravity-on-other-planets/
		final String[] planets = {"Mercury", "Venus", "The Moon", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Cardoorium Prime"};
		final double[] planetWeights = {0.38, 0.904, 0.1654, 0.38, 2.528, 1.065, 0.886, 1.14, 3104558};
		Scanner scan = new Scanner(System.in);
		double weightOnNewPlanet = 0;

		System.out.print("What is your weight on the Earth? ");
		int earthWeight = scan.nextInt();
		System.out.println("    Planet              Gravity");
		for (int i = 0; i < planets.length; i++) {
			System.out.print("(" + (i + 1) + ") " + planets[i]);
			for (int j = 0; j < 20 - planets[i].length(); j++) {
				System.out.print(".");
			}
			System.out.println(planetWeights[i]);
		}
		System.out.print("Please select a planet (#" + 1 + "-" + planets.length + "): ");
		int selectedPlanet = scan.nextInt();
		// rip
		// weightOnNewPlanet = earthWeight * planetWeights[selectedPlanet - 1];
		switch (selectedPlanet) {
			case 1:
				weightOnNewPlanet = earthWeight * planetWeights[0];
				break;
			case 2:
				weightOnNewPlanet = earthWeight * planetWeights[1];
				break;
			case 3:
				weightOnNewPlanet = earthWeight * planetWeights[2];
				break;
			case 4:
				weightOnNewPlanet = earthWeight * planetWeights[3];
				break;
			case 5:
				weightOnNewPlanet = earthWeight * planetWeights[4];
				break;
			case 6:
				weightOnNewPlanet = earthWeight * planetWeights[5];
				break;
			case 7:
				weightOnNewPlanet = earthWeight * planetWeights[6];
				break;
			case 8:
				weightOnNewPlanet = earthWeight * planetWeights[7];
				break;
			case 9:
				weightOnNewPlanet = earthWeight * planetWeights[8];
				break;
			default:
				System.out.println("Your weight in the void is nonexistent");
		}
		if (selectedPlanet >= 1 && selectedPlanet <= planets.length) {
			System.out.println("Your weight on " + planets[selectedPlanet - 1] + " would be " + weightOnNewPlanet);
		}
	}
}