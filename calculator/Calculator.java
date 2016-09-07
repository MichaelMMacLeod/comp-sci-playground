import java.util.Scanner;

public class Calculator {
	
	/*/
	 * Created by Michael MacLeod
	 * 9/7/2016, 2:30 PM
	/*/

	public static boolean on = true;
	public static final String STOP = "stop";
	public static final String HELP = "help";
	public static final String[] COMMANDS = {
		"help: display commands",
		"stop: turn off the calculator"
	};

	public static void main(String[] args) {

		String command;

		while (on) {
			System.out.print(" > ");
			command = getInput();
			interpret(command);
		}

	}

	private static String getInput() {

		Scanner scan = new Scanner(System.in);
		String input;

		input = scan.nextLine();
		if (input.equalsIgnoreCase(STOP)) {
			return STOP;
		}
		if (input.equalsIgnoreCase(HELP)) {
			return HELP;
		}
		return "noinput";
	}

	private static void interpret(String command) {
		
		if (command.equalsIgnoreCase(STOP)) {
			on = false;
		}
		if (command.equalsIgnoreCase(HELP)) {
			for (int i = 0; i < COMMANDS.length; i++) {
				System.out.println(COMMANDS[i]);
			}
		}
	}
}