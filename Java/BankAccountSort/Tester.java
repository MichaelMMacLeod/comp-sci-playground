import java.util.Scanner;
import java.util.Arrays;

import java.text.*;

public class Tester {
	public static void main(String[] args) {
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMinimumFractionDigits(2);
		formatter.setMaximumFractionDigits(2);

		String name;
		int j;

		BankAccount[] ba = new BankAccount[5];
		for (int i = 0; i < ba.length; i++) {
			Scanner scan = new Scanner(System.in);
			System.out.print("Please enter the name to whom the account belongs. ");
			name = scan.nextLine();

			System.out.print("Please enter the amount of the deposit. ");
			double amount = scan.nextDouble();
			System.out.println();

			ba[i] = new BankAccount(amount, name);
		}

		Arrays.sort(ba);

		for (BankAccount b : ba)
			System.out.println(b.getName() + " >>> " + b.getBalance());
	}
}