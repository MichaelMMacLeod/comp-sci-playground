import java.io.*;
import java.util.Scanner;

public class Tester {

	/*/
	 * Created by Michael MacLeod
	 * 9/2/2016, 2:23 PM
	/*/

	public static void main(String args[]) {

		Scanner scan = new Scanner(System.in);
		int answer;

		System.out.print("Enter an Integer: ");
		answer = scan.nextInt();
		if (answer % 2 == 0) {
			System.out.println("The integer " + answer + " is even.");
		} else {
			System.out.println("The integer " + answer + " is odd.");
		}
	}
}