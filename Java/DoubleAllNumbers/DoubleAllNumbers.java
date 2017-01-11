import java.util.Scanner;

public class DoubleAllNumbers {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println(doubleMe(scan.nextLine(), 0));

		// String s = "";

		// this is ugly, i know, but i have to write a 2 page english essay right now. :("
		// it doesn't work if you don't put spaces before and after the numbers
		// ideally we would have some regex that matched anything right before or after a number, but i couldn't figure that out.
		// while (!s.equalsIgnoreCase("exit")) {
		// 	System.out.print("Type in a sentence and press ENTER. ");
		// 	s = scan.nextLine();

		// 	String[] split = s.split(" ");

		// 	for (int i = 0; i < split.length; i++) {
		// 		try {
		// 			split[i] = Integer.toString(2 * Integer.parseInt(split[i]));
		// 		} catch (Exception e) {}
		// 	}

		// 	String ans = "";
		// 	for (String i : split) {
		// 		ans += i + " ";
		// 	}
		// 	if (!s.equalsIgnoreCase("exit")) {
		// 		System.out.println(ans);
		// 	}
		// }
	}

	static String doubleMe(String s, int n) {
		if (s.length() > 0 && Character.isDigit(s.charAt(0))) {
			return 
			Integer.toString(2 * Character.getNumericValue(s.charAt(0)) * (int) Math.pow(10, n)) 
			+ doubleMe(s.substring(1), n + 1);
		} else if (s.length() > 0) {
			return s.charAt(0) + doubleMe(s.substring(1), 0);
		} else
			return "";
	}

	static int findEndNum(String s) {
		if (Character.isDigit(s.charAt(0)))
			return 1 + findEndNum(s.substring(1))
		else
			return 0;
		
	}
}