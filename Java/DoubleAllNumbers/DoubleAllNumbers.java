import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

// only works for integers
// ex: 2.9 -> 4.18 instead of 2.9 -> 5.8

public class DoubleAllNumbers {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		String s = "";

		while (!s.equalsIgnoreCase("exit")) {
			System.out.print("Type in a sentence and press ENTER. ");
			s = scan.nextLine();

			ArrayList<String> list = new ArrayList<String>();

			Matcher m = Pattern.compile("\\D+|\\d+").matcher(s);

			while (m.find()) {
				list.add(m.group());
			}

			for (int i = 0; i < list.size(); i++) {
				try {
					list.set(i, Integer.toString(2 * Integer.parseInt(list.get(i))));
				} catch (Exception e) {}
			}

			if (!s.equalsIgnoreCase("exit")) {
				for (String i : list)
					System.out.print(i);
				System.out.println();
			}
		}
	}
}