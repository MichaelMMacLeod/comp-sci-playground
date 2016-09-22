import java.util.Scanner;

public class lab09f {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String text, remove;

		System.out.print("Text: ");
		text = scan.nextLine();
		System.out.print("Remove: ");
		remove = scan.nextLine();
		System.out.println(text.replaceAll(remove, ""));
	}
}