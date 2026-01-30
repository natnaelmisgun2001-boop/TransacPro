import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== TransactPro ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Account Number: ");
                        String num = sc.nextLine();
                        System.out.print("Owner: ");
                        String owner = sc.nextLine();
                        System.out.print("Initial Balance: ");
                        double bal = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Type (SAVINGS/CHECKING): ");
                        String type = sc.nextLine().toUpperCase();

                        Account acc = type.equals("SAVINGS")
                                ? new SavingsAccount(num, owner, bal)
                                : new CheckingAccount(num, owner, bal);

                        bank.addAccount(acc);
                        System.out.println("✅ Account created.");
                    }

                    case 2 -> {
                        System.out.print("Account Number: ");
                        String num = sc.nextLine();
                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();
                        bank.getAccount(num).deposit(amt);
                        System.out.println("✅ Deposit successful.");
                    }

                    case 3 -> {
                        System.out.print("Account Number: ");
                        String num = sc.nextLine();
                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();
                        bank.getAccount(num).withdraw(amt);
                        System.out.println("✅ Withdrawal successful.");
                    }

                    case 4 -> {
                        System.out.print("From Account: ");
                        String from = sc.nextLine();
                        System.out.print("To Account: ");
                        String to = sc.nextLine();
                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();
                        bank.transfer(from, to, amt);
                        System.out.println("✅ Transfer completed.");
                    }

                    case 5 -> {
                        System.out.println("👋 Goodbye!");
                        return;
                    }

                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }
}
