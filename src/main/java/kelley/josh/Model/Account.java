package kelley.josh.Model;

import java.util.ArrayList;

public class Account{
    public enum AccountType{
        CHECKING, SAVINGS, INVESTMENT, CREDIT;
    }
    public enum AccountStatus{
        OPEN, CLOSED, FROZEN;
    }
    public enum OverdraftProtection{
        ENABLED, DISABLED, AUTOMATIC
    }

    AccountType accountType;
    public OverdraftProtection overdraftProtection;
    public AccountStatus accountStatus=AccountStatus.OPEN;
    public Account transferAccount = null;
    public int accountNumber;
    public Double accountBalance;
    public Double interestRate = new Double(0);
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();

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
        switch (this.overdraftProtection){
            case DISABLED:success=(accountBalance>=0)?true:false;break;
            case ENABLED:success=((moneyAfter)>=0)?true:false;break;
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

    public boolean transferTo(Account receivingAccount, Double money){
        if(accountBalance>=money){
            withdraw(money);
            receivingAccount.deposit(money);
            return true;
        }else return false;

    }

    public double viewBalance() {
        System.out.println(accountBalance);
        return accountBalance;
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
