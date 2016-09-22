import java.util.Scanner;

public class lab09d {

	public static void main(String args[]) {

		Scanner scan = new Scanner(System.in);
		int sum = 0;
		double num;

		num = scan.nextDouble();
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		System.out.println(sum);
	}
}