import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.File;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

// only works for integers
// ex: 2.9 -> 4.18 instead of 2.9 -> 5.8

// only works on .java files

// Probably only works on Lawrence's program; the blacklist may be incomplete
// on other programs

// Does not rename class name to end with x2

public class DoubleAllNumbers {
	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		Scanner scan = null;
		int size = 0;

		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error: File " + args[0] + " not found.");

			System.exit(0);
		}
		
		ArrayList<String> list = new ArrayList<String>();

		while (scan.hasNextLine()) {
			String s = scan.nextLine();
			
			Matcher m = Pattern.compile("\\D+|\\d+").matcher(s);
			while (m.find()) {
				list.add(m.group());
			}

			if (!s.contains("color") && !s.contains("row")) {
				for (int i = size; i < list.size(); i++) {
					try {
						list.set(i, Integer.toString(2 * Integer.parseInt(list.get(i))));
					} catch (Exception e) {}
				}
			}

			list.add("\n");

			size = list.size();
		}

		String fileName = args[0].substring(0, args[0].length() - 5); // sry

		FileWriter fw = new FileWriter(fileName + "x2.java");
		PrintWriter pw = new PrintWriter(fw);

		for (String i : list)
			pw.print(i);

		pw.close();
		fw.close();
	}
}