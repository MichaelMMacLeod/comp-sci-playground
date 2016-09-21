import java.util.Scanner;
import static java.lang.Math.pow;

public class lab08j {

	/*/
	 * Michael MacLeod
	/*/

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String num, rev = "";
		int ans = 0;

		// Get input
		num = scan.nextLine();
		// Reverse the input
		for (int i = 0; i < num.length(); i++) {
			rev = num.charAt(i) + rev;
		}
		// Calculate base 10
		for (int i = 0; i < rev.length(); i++) {
			if (rev.charAt(i) == '1') {
				ans += Math.pow(2, i);
			}
		}
		// Print output
		System.out.println(num + " == " + ans);
	}
}