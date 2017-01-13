import java.io.FileNotFoundException;
import java.io.File;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

// only works for integers
// ex: 2.9 -> 4.18 instead of 2.9 -> 5.8

public class DoubleAllNumbers {
	public static void main(String[] args) {
		File file = new File("in.txt");
		Scanner scan = null;


		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error: File 'in.txt' not found.");

			System.exit(0);
		}

		
		while (scan.hasNextLine()) {
			String s = scan.nextLine();

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

			for (String i : list)
				System.out.print(i);
			System.out.println();
		}
	}
}