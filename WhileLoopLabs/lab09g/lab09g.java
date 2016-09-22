import java.util.Scanner;

public class lab09g {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String remove, oldStr, newStr;

		System.out.print("Text: ");
		newStr = scan.nextLine();
		System.out.print("Remove: ");
		remove = scan.nextLine();
		do {
			oldStr = newStr;
			newStr = newStr.replaceAll("." + remove, ""); // :)
		} while (!newStr.equals(oldStr));
		System.out.println(newStr);
	}
}