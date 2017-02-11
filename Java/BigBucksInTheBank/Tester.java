import java.util.Scanner;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Tester {
	public static void main(String args[]) {
		NumberFormat formatter = NumberFormat.getNumberInstance();

		formatter.setMinimumFractionDigits(2);
		formatter.setMaximumFractionDigits(2);

		String name;

		ArrayList<BankAccount> aryList = new ArrayList<>();

		do {
			Scanner kbReader = new Scanner(System.in);

			System.out.print("Please enter the name to whom the account belongs. (\"Exit\" to abort) ");

			name = kbReader.nextLine();

			if(!name.equalsIgnoreCase("EXIT")) {
				System.out.print("Please enter the amount of the deposit. ");
				double amount = kbReader.nextDouble();
				System.out.println();
				aryList.add(new BankAccount(name, amount));
			}
		} while (!name.equalsIgnoreCase("EXIT"));

		// doesn't check if two accounts have the same balance
		BankAccount mostMoney = aryList.get(0);
		for (BankAccount b : aryList) {
			if (b.balance > mostMoney.balance) {
				mostMoney = b;
			}
		}
		System.out.println("The account with the largest balance belongs to " + mostMoney.name + "\nThe amount is " + mostMoney.balance);
	}
}