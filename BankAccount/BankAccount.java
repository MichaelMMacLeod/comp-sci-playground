public class BankAccount {
    private String name;
    private double balance;
    public BankAccount(double initialBalance, String accountName) {
        balance = initialBalance;
        name = accountName;
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
    // because encapsulation is good, right?
    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
}