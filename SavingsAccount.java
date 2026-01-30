public class SavingsAccount extends Account {

    public SavingsAccount(String accountNumber, String owner, double balance) {
        super(accountNumber, owner, balance);
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }
}
