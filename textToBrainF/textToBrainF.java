import java.util.Scanner;

public class textToBrainF {

	/**
	 * Creates a BrainF**k program that displays the given input
	 */
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String output = "+++++ +++++\n[\n";

		System.out.print("Enter a message: ");
		String input = scan.nextLine();
		int[] ascii = new int[input.length()];
		for (int i = 0; i < ascii.length; i++) {
			ascii[i] = (int) input.charAt(i);
		}
		for (int i = 0; i < ascii.length; i++) {
			output += "  > ";
			for (int j = 0; j < ascii[i] / 10; j++) {
				output += "+";
			}
			output += "\n";
		}
		for (int i = 0; i < ascii.length; i++) {
			output += "<";
		}
		output += " -\n]\n";
		for (int i = 0; i < ascii.length; i++) {
			output += "> ";
			for (int j = 0; j < ascii[i] % 10; j++) {
				output += "+";
			}
			output += " .\n";
		}
		String compressed = output.replaceAll("\\s", "");
		System.out.println("BrainF**k code which prints out \"" + input + "\":\n"  + output);
		System.out.println("Compressed code:\n" + compressed);
	}
}