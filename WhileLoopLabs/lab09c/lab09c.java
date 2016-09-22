import java.util.Scanner;

public class lab09c {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int rev = 0, num;

		num = scan.nextInt();
		while (num > 0) {
			rev = rev * 10 + num % 10;
			num /= 10;
		}
		System.out.println(rev);
	}
}