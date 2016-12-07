import java.util.Scanner;

public class lab08e {

	/*/
	 * Michael MacLeod
	/*/

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int num, times;

		num = scan.nextInt();
		times = scan.nextInt();
		System.out.println("multiplication table for " + num);
		for (int i = 1; i <= times; i++) {
			System.out.println(i + " " + (num * i));
		}
	}
}