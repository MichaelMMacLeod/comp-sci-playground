import java.util.Scanner;

public class lab08g {

	/*/
	 * Michael MacLeod
	/*/

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int start, stop;
		int even = 0;
		int odd = 0;
		int sum = 0;

		System.out.print("Start: ");
		start = scan.nextInt();
		System.out.print("Stop: ");
		stop = scan.nextInt();
		for (int i = start; i <= stop; i++) {
			sum += i;
			if (i % 2 == 0) {
				even++;
			} else {
				odd++;
			}
		}
		System.out.println("total " + sum);
		System.out.println("even count " + even);
		System.out.println("odd count " + odd);
	}
}