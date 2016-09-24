import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Math.pow;

public class BaseToBase {

	// Holds symbols used in bases 11 through 36
	public static final String[] extra = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

	/** 
	 * Converts a string to an int array
	 * ex: "1234" => {1,2,3,4}
	 * Digits must be separated by commas in basses 27 or higher 
	 * ex: "1,2,A,B,37,38" => {1,2,A,B,37,38}
	 * @param number is a integer in base 2 or higher
	 * @param base   is the base that the number is in
	 */
	public static int[] toIntArray(String number, int base) {

		String[] strArray;

		if (base > extra.length + 10) {
			strArray = number.split(",");
		} else {
			strArray = number.split("");
		}
		for (int i = 0; i < strArray.length; i++) {
			for (int j = 0; j < extra.length; j++) {
				if (strArray[i].equalsIgnoreCase(extra[j])) {
					strArray[i] = Integer.toString(j + 10);
				}
			}
		}

		int[] intArray = new int[strArray.length];

		for (int i = 0; i < strArray.length; i++) {
			intArray[i] = Integer.parseInt(strArray[i]);
		}
		return intArray;
	}

	/**
	 * Returns the given number represented as an array in base 10
	 * ex: {1,0,1,0} base 2 => {1,0} base 10
	 * @param number is an integer represented as an array
	 *               ex: the number 1234 is {1,2,3,4}
	 * @param base   is the base of the given number
	 */
	public static int[] toBaseTen(int[] number, int base) {

		int base10 = 0;

		for (int i = 0; i < number.length; i++) {
			base10 += number[number.length - i - 1] * Math.pow(base, i);
		}
		return base10;

		int[] intArray = new int[base10.length()];

	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		
		String[] numStrArray;
		String num;
		int baseOld, baseNew, numBase10 = 0;

		// Get input
		System.out.print("Number: ");
		num = scan.nextLine();
		System.out.print("Original Base: ");
		baseOld = scan.nextInt();
		System.out.print("New Base: ");
		baseNew = scan.nextInt();

		// Move input into an array
		int[] numArray = toIntArray(num, baseOld);
		
		// Convert to base 10
		numBase10 = toBaseTen(numArray, baseOld);

		// Find the max power of the new base which is less than or equal to the number in base 10
		int power = 1;
		while (power * baseNew <= numBase10) {
			power *= baseNew;
		}
		// Calculate the digits of the number in the new base
		int remainder = numBase10;
		while (power >= 1) {
			ans.add(remainder / power);
			remainder %= power;
			power /= baseNew;
		}
		// Print out the number in the new base
		if (baseNew > extra.length) {
			System.out.println("Since base " + baseNew + " requires more symbols than the alphabet can provide, some digits will be represented in base 10 and separated by braces.");
		}
		System.out.print(num + " in base " + baseOld + " is ");
		for (int i = 0; i < ans.size(); i++) {
			if (ans.get(i) > 9 && ans.get(i) - 10 < extra.length) { 
				// Use special characters for bases larger than 10
				System.out.print(extra[ans.get(i) - 10]);
			} else if (ans.get(i) < 10) { 
				// Use normal characters for digits 1 through 9
				System.out.print(ans.get(i));
			} else { 
				// Since our array only lists A through Z, it's possible that 
				// we encounter digits which we can't represent. To fix this, 
				// we represent them as base 10 numbers separated by braces.
				System.out.print("{" + ans.get(i) + "}");
			}
		}
		System.out.println(" in base " + baseNew + ".");
		System.out.println("Used " + ans.size() + " digits.");
	}
}