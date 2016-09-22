import java.util.Scanner;

public class lab09e {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int input;

		input = scan.nextInt();
		System.out.print(input + " has divisors ");
		for (int i = 1; i <= input / 2; i++) {
			if (input % i == 0) System.out.print(i + " ");
		}
	}
}