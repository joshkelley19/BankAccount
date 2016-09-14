package kelley.josh.Model;

import java.util.Date;


public class Transaction {
    public enum TransactionType {
        WITHDRAW,DEPOSIT,NEWACCOUNT,CLOSEACCOUNT;
    }
    Date currentDate;
    Double amount=0.0;
    boolean isOnline;
    boolean isSuccessful;
    Account.AccountType type;
    TransactionType transactionType;

    Transaction(Account.AccountType acctType,TransactionType transType,boolean success,Double amount,boolean online){
        this.type=acctType;
        this.transactionType=transType;
        this.isSuccessful=success;
        currentDate=new Date();
        this.amount=amount;
        this.isOnline=online;
    }
    Transaction(Account.AccountType acctType, TransactionType transType,boolean success,boolean online){
        this.type=acctType;
        this.transactionType=transType;
        this.isSuccessful=success;
        currentDate=new Date();
        this.isOnline=online;
    }
    @Override
    public String toString() {
        String online=(isOnline)?"Did":"Did Not";
        String successful=(isSuccessful)?"Approved":"Denied";
        return "Account Type: "+type+" "+successful+" "+"Transaction Type: "+transactionType+" Amount: "+amount+" Time: "+currentDate+" Transaction "+online+" Take Place Online";
    }
}
