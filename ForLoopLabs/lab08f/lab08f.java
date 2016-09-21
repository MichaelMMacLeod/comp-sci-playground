import java.util.Scanner;

public class lab08f {

	/*/
	 * Michael MacLeod
	/*/

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String ans;

		ans = scan.nextLine();
		System.out.println(ans.charAt(0));
		System.out.println(ans.charAt(ans.length() - 1));
		for (int i = 1; i <= ans.length(); i++) {
			System.out.print(ans.charAt(ans.length() - i));
		}
	}
}