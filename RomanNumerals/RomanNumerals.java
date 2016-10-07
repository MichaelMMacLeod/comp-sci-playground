import java.util.Scanner;

public class RomanNumerals {

	// Sorting the arrays so that the two character numerals come first
	// public static final String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	public static final String[] numerals = {"CM", "CD", "XC", "XL", "IX", "IV", "M", "D", "C", "L", "X", "V", "I"};
	// public static final int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	public static final int[] numbers = {900, 400, 90, 40, 9, 4, 1000, 500, 100, 50, 10, 5, 1};

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println(convert(scan.nextLine()));
	}

	/** Converts a roman numeral to a base 10 number */
	public static int convert(String num) {

		int ans = 0;

		for (int i = 0; i < num.length(); i++) {
			for (int j = 0; j < numerals.length; j++) {
				try {
					if (num.substring(i, i + numerals[j].length()).equalsIgnoreCase(numerals[j])) {
						ans += numbers[j];
						if (numerals[j].length() == 2) {
							i++;
						}
						break;
					}
				} catch (Exception e) {}
				
			}
		}
		return ans;
	}
/*
get input in the form of a roman numeral
find the first character. Favor large characters over small ones
for example, if the input is CM, the character will be CM- not C and then M







*/
}