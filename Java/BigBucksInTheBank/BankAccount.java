public class BankAccount {
	public String name;
	public double balance;

	public BankAccount(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}

	public void deposit(double dp) {
		balance = balance + dp;
	}

	public void withdraw(double wd) {
		balance = balance - wd;
	}
}