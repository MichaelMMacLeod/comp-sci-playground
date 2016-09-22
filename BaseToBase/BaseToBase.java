import java.util.Scanner;
import java.util.ArrayList;	

public class BaseToBase {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String[] extra = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

		System.out.print("Number: ");
		String num = scan.nextLine();
		System.out.print("Original Base: ");
		int base1 = scan.nextInt();
		System.out.print("New Base: ");
		int base2 = scan.nextInt();
		String[] numStrArray = num.split("(\\{.*\\})|.");
		for (int i = 0; i < numStrArray.length; i++) {
			System.out.println(numStrArray[i]);
		}
	}
}