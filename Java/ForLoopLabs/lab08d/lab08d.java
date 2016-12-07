import java.util.Scanner;

public class lab08d {

	/*/
	 * Michael MacLeod
	/*/

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String ans;

		ans = scan.nextLine();
		for (int i = 0; i < ans.length(); i++) {
			System.out.println(ans.substring(0, ans.length() - i));
		}
	}
}