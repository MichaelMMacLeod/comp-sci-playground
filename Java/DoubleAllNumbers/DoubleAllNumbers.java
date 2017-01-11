import java.util.Scanner;

public class DoubleAllNumbers {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String s = "";

		// this is ugly, i know, but i have to write a 2 page english essay right now. :("
		// it doesn't work if you don't put spaces before and after the numbers
		// ideally we would have some regex that matched anything right before or after a number, but i couldn't figure that out.
		while (!s.equalsIgnoreCase("exit")) {
			System.out.print("Type in a sentence and press ENTER. ");
			s = scan.nextLine();

			String[] split = s.split(" ");

			for (int i = 0; i < split.length; i++) {
				try {
					split[i] = Integer.toString(2 * Integer.parseInt(split[i]));
				} catch (Exception e) {}
			}

			String ans = "";
			for (String i : split) {
				ans += i + " ";
			}
			if (!s.equalsIgnoreCase("exit")) {
				System.out.println(ans);
			}
		}
		
	}
}