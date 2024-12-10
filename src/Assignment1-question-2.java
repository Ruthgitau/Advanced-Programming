import Lecture4_interfaces_abstract_classes.BankAccount;
import java.util.Calendar;

public class DepositTransaction extends BaseTransaction { 
    public DepositTransaction(double amount, Calendar date) {
        super(amount, date);
    }
    
    @Override
    public void printTransactionDetails() {
        System.out.println("DEPOSIT TRANSACTION");
        super.printTransactionDetails();
    }
    
    @Override
    public void apply(BankAccount ba) throws InsufficientFundsException {
        double newBalance = ba.getBalance() + getAmount();
        ba.setBalance(newBalance);
        System.out.println("Deposit successful. New balance: " + newBalance);
    }
}
