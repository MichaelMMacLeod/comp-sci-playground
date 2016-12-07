import java.util.Scanner;

public class lab08b {

	/*/
	 * Michael MacLeod
	/*/

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int start, stop, increment;

		System.out.print("Start: ");
		start = scan.nextInt();
		System.out.print("Stop: ");
		stop = scan.nextInt();
		System.out.print("Increment: ");
		increment = scan.nextInt();
		for (int i = start; i < stop; i += increment) {
			System.out.print(i + " ");
		}
	}
}