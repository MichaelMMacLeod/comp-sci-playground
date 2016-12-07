import java.util.Scanner;

public class lab08i {

	/*/
	 * Michael MacLeod
	/*/

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int ans;

		ans = scan.nextInt();
		if (isPrime(ans)) {
			System.out.println(ans + " is prime.");
		} else {
			System.out.println(ans + " is not prime.");
		}
	}

	private static boolean isPrime(int num) {
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}