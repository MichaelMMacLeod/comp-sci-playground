import java.util.Scanner;
public class Tester {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Account balance: ");
		int balance = scan.nextInt();/*ugh*/scan.nextLine();
		System.out.print("Account name: ");
		String name = scan.nextLine();
		BankAccount myAccount = new BankAccount(balance, name);
		myAccount.deposit(505.22);
		System.out.println(myAccount.getBalance());
		myAccount.withdraw(100);
		System.out.println("The " + myAccount.getName() + " account balance is, " + myAccount.getBalance());
	}
}