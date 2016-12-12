public class Tester {

	public static void main(String[] args) {
		int[] hall = {3, 4, 2, 2, 1, 5, 3, 4};
		TrumpBot trumpBot2000 = new TrumpBot(hall, 1, true);
		System.out.println("TrumpBot2000:");
		System.out.println("moves: " + trumpBot2000.clearHall());
	}
}