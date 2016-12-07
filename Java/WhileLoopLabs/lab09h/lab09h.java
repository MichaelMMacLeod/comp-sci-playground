import java.util.Scanner;

public class lab09h {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int num, sum = 0;

		num = scan.nextInt();
		for (int i = 1; i <= num / 2; i++) {
			if (num % i == 0) sum += i;
		}
		if (num == sum) {
			System.out.println(num + " is perfect.");
		} else {
			System.out.println(num + " is not perfect.");
		}
	}
}