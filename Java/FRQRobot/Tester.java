public class Tester {

	public static void main(String[] args) {
		int[] hall = {1, 1, 2, 2};
		Robot robot = new Robot(hall, 1, true);
		System.out.println("moves: " + robot.clearHall());
	}
}