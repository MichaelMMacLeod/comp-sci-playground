import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class DoubleAllNumbers {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();

		ArrayList<String> sa = new ArrayList<String>();

		Matcher m = Pattern.compile("\\D+|\\d+").matcher(s);

		while (m.find()) {
			sa.add(m.group());
		}

		for (int i = 0; i < sa.size(); i++) {
			try {
				sa.set(i, Integer.toString(2 * Integer.parseInt(sa.get(i))));
			} catch (Exception e) {}
		}

		for (String i : sa) 
			System.out.print(i);
		System.out.println();
	}
}