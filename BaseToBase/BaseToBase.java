import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Math.pow;

public class BaseToBase {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		String[] extra = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
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
		// Move the number into a string array
		if (baseOld > extra.length + 10) {
			numStrArray = num.split(",");
		} else {
			numStrArray = num.split("");
		}
		// Convert letters into numbers
		for (int i = 0; i < numStrArray.length; i++) {
			for (int j = 0; j < extra.length; j++) {
				if (numStrArray[i].equalsIgnoreCase(extra[j])) {
					numStrArray[i] = Integer.toString(j + 10);
				}
			}
		}
		// Move the number into an int array
		int[] numArray = new int[numStrArray.length];
		for (int i = 0; i < numStrArray.length; i++) {
			numArray[i] = Integer.parseInt(numStrArray[i]);
		}
		// Convert to base 10
		for (int i = 0; i < numArray.length; i++) {
			numBase10 += numArray[numArray.length - i - 1] * Math.pow(baseOld, i);
		}
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