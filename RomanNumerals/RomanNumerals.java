import java.util.Scanner;

public class RomanNumerals {

	public static final String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	public static final int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println(convert(scan.nextLine()));
	}

	/** Converts a roman numeral to a base 10 number */
	public static int convert(String num) {

		int last = 0;
		int ans = 0;

		for (int i = num.length(); i >= 0; i--) {
			for (int j = 0; j < numerals.length; j++) {
				try {
					if (num.substring(num.length() - i, num.length() - i + numerals[j].length()).equalsIgnoreCase(numerals[j]) && numbers[j] >= last) 
					{
						ans += numbers[j];
						last = numbers[j];
						System.out.println(last);
						break;
					} else  if (num.substring(num.length() - i, num.length() - i + numerals[j].length()).equalsIgnoreCase(numerals[j]) && numbers[j] < last){
						ans -= numbers[j];
						last = numbers[j];
						System.out.println(last);
						break;
					}
				} catch (Exception e) { }
			}
		}
		return ans;
	}
}