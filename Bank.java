import java.io.*;
import java.util.*;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private final String FILE_NAME = "accounts.txt";

    public Bank() {
        loadAccounts();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
        saveAccounts();
    }

    public Account getAccount(String accNum) {
        Account acc = accounts.get(accNum);
        if (acc == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        return acc;
    }

    public void transfer(String from, String to, double amount) {
        Account sender = getAccount(from);
        Account receiver = getAccount(to);

        sender.withdraw(amount);
        receiver.deposit(amount);
        saveAccounts();
    }

    private void saveAccounts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Account acc : accounts.values()) {
                writer.println(acc.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts.");
        }
    }

    private void loadAccounts() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                String accNum = data[0];
                String owner = data[1];
                double balance = Double.parseDouble(data[2]);
                String type = data[3];

                Account acc = type.equals("SAVINGS")
                        ? new SavingsAccount(accNum, owner, balance)
                        : new CheckingAccount(accNum, owner, balance);

                accounts.put(accNum, acc);
            }
        } catch (Exception e) {
            System.out.println("Error loading accounts.");
        }
    }
}
