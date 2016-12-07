import java.util.Scanner;

public class lab08h {

	/*/
	 * Michael MacLeod
	/*/

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int num, sum = 1;

		num = scan.nextInt();
		System.out.print("The factorial of " + num + " is ");
		for (int i = num; i > 1; i--) {
			sum *= i;
			System.out.print(i + "x");
		}
		System.out.println("1 = " + sum + ".");
	}
}