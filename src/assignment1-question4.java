import Lecture4_interfaces_abstract_classes.BankAccount;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void testDeposit() {
        System.out.println("\n=== Testing Deposit Transaction ===");
        BankAccount account = new BankAccount(1000);
        Calendar date = new GregorianCalendar();
        DepositTransaction deposit = new DepositTransaction(500, date);
        
        System.out.println("Initial balance: " + account.getBalance());
        deposit.printTransactionDetails();
        try {
            deposit.apply(account);
            System.out.println("Final balance: " + account.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void testWithdrawal() {
        System.out.println("\n=== Testing Withdrawal Transaction ===");
        BankAccount account = new BankAccount(1000);
        Calendar date = new GregorianCalendar();
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(800, date);
        
        System.out.println("Initial balance: " + account.getBalance());
        withdrawal.printTransactionDetails();
        try {
            withdrawal.apply(account);
            System.out.println("Withdrawal successful");
            
            // Test reversal
            System.out.println("\nTesting reversal...");
            boolean reversed = withdrawal.reverse();
            System.out.println("Reversal successful: " + reversed);
            System.out.println("Balance after reversal: " + account.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void testPartialWithdrawal() {
        System.out.println("\n=== Testing Partial Withdrawal ===");
        BankAccount account = new BankAccount(500);
        Calendar date = new GregorianCalendar();
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(800, date);
        
        System.out.println("Initial balance: " + account.getBalance());
        withdrawal.printTransactionDetails();
        withdrawal.apply(account, true);
    }
    
    public static void testPolymorphism() {
        System.out.println("\n=== Testing Polymorphism ===");
        BankAccount account = new BankAccount(1000);
        Calendar date = new GregorianCalendar();
        
        BaseTransaction[] transactions = {
            new BaseTransaction(100, date),
            new DepositTransaction(500, date),
            new WithdrawalTransaction(300, date)
        };
        
        for (BaseTransaction transaction : transactions) {
            System.out.println("\nProcessing transaction of type: " + transaction.getClass().getSimpleName());
            transaction.printTransactionDetails();
            try {
                transaction.apply(account);
                System.out.println("Current balance: " + account.getBalance());
            } catch (InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        testDeposit();
        testWithdrawal();
        testPartialWithdrawal();
        testPolymorphism();
    }
}
