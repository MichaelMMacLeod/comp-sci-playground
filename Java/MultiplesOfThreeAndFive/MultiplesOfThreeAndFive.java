public class MultiplesOfThreeAndFive {

	/*/
	 * Michael MacLeod
	 * 9/8/2016
	/*/

	public static void main(String[] args) {

		int sum = 0;
		int[] difference = {3, 3, 2, 1, 3, 1, 2};
		int currentDiff = 1;

		for (int i = 3; i < 1000; i += difference[currentDiff]) {
			sum += i;
			currentDiff = currentDiff < difference.length - 1 ? currentDiff + 1 : 0;
		}
		System.out.println("sum: " + sum);

		/* We could've done it this way, but that's boring */
		// for (int i = 0; i < 1000; i++) {
		// 	if (i % 3 == 0 || i % 5 == 0) {
		// 		sum += i;
		// 	}
		// }
		// System.out.println("Sum: " + sum);
	}
}