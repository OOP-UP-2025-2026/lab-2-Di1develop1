package ua.opnu;

public class BankAccount {
    String name;
    double balance;
    double transactionFee; // комісія списується з відправника при знятті/переказі

    public BankAccount(String name, double initialBalance) {
        this.name = (name == null || name.isBlank()) ? "Unnamed" : name;
        this.balance = Math.max(0.0, initialBalance);
        this.transactionFee = 0.0;
    }

    public BankAccount() {
        this("Unnamed", 0.0);
    }

    void deposit(double amount) {
        // amount > 0; інакше — нічого не робимо
        if (amount <= 0) return;
        balance += amount;
    }

    double getBalance() {
        return balance;
    }

    boolean withdraw(double amount) {
        if (amount <= 0) return false;
        double total = amount + fee();
        if (!canDebit(total)) return false;
        balance -= total;
        return true;
    }

    boolean transfer(BankAccount receiver, double amount) {
        if (receiver == null || amount <= 0) return false;
        double total = amount + fee();
        if (!canDebit(total)) return false;
        this.balance -= total;   // відправник платить комісію
        receiver.balance += amount;
        return true;
    }

    // Можна змінювати комісію, якщо треба
    public void setTransactionFee(double fee) {
        this.transactionFee = Math.max(0.0, fee);
    }

    private double fee() {
        return Math.max(0.0, transactionFee);
    }

    private boolean canDebit(double total) {
        return total <= balance;
    }
}
