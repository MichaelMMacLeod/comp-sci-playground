import java.util.Scanner;

public class RomanNumerals {

	// Sorted so that the two character numerals come first
	public static final String[] orderedNumerals = {"CM", "CD", "XC", "XL", "IX", "IV", "M", "D", "C", "L", "X", "V", "I"};
	public static final int[] orderedNumbers = {900, 400, 90, 40, 9, 4, 1000, 500, 100, 50, 10, 5, 1};
	// Sorted from largest to smallest value
	public static final String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	public static final int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		String input = scan.nextLine();
		if (isInteger(input)) {
			System.out.println(input + " is " + tenToNumeral(Integer.parseInt(input)));
		} else {
			System.out.println(input + " is " + numeralToTen(input));
		}
	}

	/** because this is apparently not built in */
	public static boolean isInteger(String num) {
		try {
			Integer.parseInt(num);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/** Converts a base 10 number to a roman numeral */
	public static String tenToNumeral(int num) {

		String ans = "";
		
		while (num > 0) {
			for (int i = 0; i < numbers.length; i++) {
				if (numbers[i] <= num) {
					ans += numerals[i];
					num -= numbers[i];
					break;
				}
			}

		}
		return ans;
	}

	/** Converts a roman numeral to a base 10 number */
	public static int numeralToTen(String num) {

		int ans = 0;

		for (int i = 0; i < num.length(); i++) {
			for (int j = 0; j < orderedNumerals.length; j++) {
				try {
					if (num.substring(i, i + orderedNumerals[j].length()).equalsIgnoreCase(orderedNumerals[j])) {
						ans += orderedNumbers[j];
						if (orderedNumerals[j].length() == 2) {
							i++;
						}
						break;
					}
				} catch (Exception e) {}
				
			}
		}
		return ans;
	}
}