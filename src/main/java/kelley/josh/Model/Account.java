package kelley.josh.Model;

import java.util.ArrayList;
import kelley.josh.Model.Profile;
/**
 * Created by joshuakelley on 9/13/16.
 */
public class Account{
    enum AccountType{
        CHECKING, SAVINGS, INVESTMENT, CREDIT;
    }
    enum AccountStatus{
        OPEN, CLOSED, FROZEN;
    }
    enum OverdraftProtection{
        ENABLED, DISABLED, AUTOMATIC
    }
    AccountType accountType;
    OverdraftProtection overdraftProtection;
    AccountStatus accountStatus=AccountStatus.OPEN;
    int accountNumber;
    Double accountBalance = new Double(0);
    Double interestRate = new Double(0);
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();

//    public Account(AccountType acctType,Integer acctNum, Double intRate, Double initialDeposit, OverdraftProtection odType){
//        this.accountType=acctType;this.accountNumber=acctNum;this.interestRate=intRate;this.accountBalance=initialDeposit;this.overdraftProtection=odType;
//    }
    public boolean deposit(Double money) {
        boolean success=((money+accountBalance)<Double.MAX_VALUE)?true:false;
        String confirm=(success)?"Success":"Failure";
        //type,success,amount,online
        transactions.add(new Transaction(accountType, Transaction.TransactionType.DEPOSIT, success,money,true));
        System.out.println("Your Transaction was a "+confirm);
        if(success) {
            accountBalance += money;
            return true;
        }else return false;
    }

    public boolean withdraw(Double money) {
        boolean success;
        String confirm;
        double moneyAfter=accountBalance-money;
        switch (overdraftProtection){
            case DISABLED:success=(accountBalance>0)?true:false;
            case ENABLED:success=((moneyAfter)>0)?true:false;
            default:success=false;
        }
        confirm=(success)?"Success":"Failure";
        //type,success,amount,online
        transactions.add(new Transaction(accountType, Transaction.TransactionType.WITHDRAW,success,money,true));
        System.out.println("Your Transaction was a "+confirm);
        if (success){
            accountBalance-=money;
            return true;
        }else return false;
    }

    public void viewBalance() {
        System.out.println(accountBalance);
    }

    public Account closeAccount(int answer) {
        if(answer==1) {
            accountStatus=AccountStatus.CLOSED;
            accountBalance=0.0;
            transactions.add(new Transaction(accountType, Transaction.TransactionType.CLOSEACCOUNT,true,true));
            return this;
        } else {return null;}
    }


}
