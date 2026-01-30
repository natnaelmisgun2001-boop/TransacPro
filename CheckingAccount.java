public class CheckingAccount extends Account {

    public CheckingAccount(String accountNumber, String owner, double balance) {
        super(accountNumber, owner, balance);
    }

    @Override
    public String getAccountType() {
        return "CHECKING";
    }
}
