public class BankAccount implements Comparable<BankAccount> {
    private String name;
    private double balance;

    public BankAccount(double initialBalance, String accountName) {
        balance = initialBalance;
        name = accountName;
    }

    public int compareTo(BankAccount other) {
    	return (int) (getBalance() - other.getBalance());
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            balance -= amount;
        }
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}