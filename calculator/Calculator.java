import java.util.*;

public class Calculator {
	
	/*/
	 * Created by Michael MacLeod
	 * 9/7/2016, 2:30 PM
	/*/

	public static boolean on = true;
	public static final String STOP = "stop";
	public static final String HELP = "help";
	public static final String USER_INPUT_ARROW = "> ";
	public static final String PROGRAM_INPUT_ARROW = "-> ";
	public static final String[] COMMANDS = {
		"help: display commands",
		"stop: turn off the calculator"
	};

	public static void main(String[] args) {

		String command;

		displayHelp();
		while (on) {
			System.out.print(USER_INPUT_ARROW);
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
		return input;
	}

	private static void interpret(String command) {
		
		List<String> commandList = new LinkedList<String>(Arrays.asList(command.split("")));
		command = "";

		for (int i = 0; i < commandList.size(); i++) {
			if (" ".equals(commandList.get(i))) {
				commandList.remove(i);
				i--;
			}
		}
		for (int i = 0; i < commandList.size(); i++) {
			command += commandList.get(i);			
		}
		if (command.equalsIgnoreCase(STOP)) {
			on = false;
		}
		if (command.equalsIgnoreCase(HELP)) {
			displayHelp();
		}
	}

	private static void displayHelp() {

		for (int i = 0; i < COMMANDS.length; i++) {
			print(COMMANDS[i]);
		}
	}

	private static void print(String text) {

		System.out.print(PROGRAM_INPUT_ARROW);
		for (int i = 0; i < text.length(); i++) {
			System.out.print(text.charAt(i));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println("You can't take the sky from me, " + e);
			}
		}
		System.out.println();
	}
}