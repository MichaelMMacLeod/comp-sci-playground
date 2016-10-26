import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.print("Enter numerator: ");
		int numerator = scan.nextInt();
		System.out.print("Enter denominator: ");
		int denominator = scan.nextInt();
		Fraction fraction = new Fraction(numerator, denominator);

		fraction.printSmall();
		fraction.printLarge();
		System.out.println("Is it reducible? " + fraction.isReducible());
		if (fraction.isReducible()) {
			fraction.reduce();
			System.out.print("Reduced fraction: ");
			fraction.printSmall();
		}
	}
}