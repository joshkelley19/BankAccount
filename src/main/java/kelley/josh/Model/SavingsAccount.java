package kelley.josh.Model;

import java.util.ArrayList;

public class SavingsAccount extends Account{
    final AccountType accountType=AccountType.SAVINGS;
    AccountStatus accountStatus=AccountStatus.OPEN;
    Double interestRate=new Double(.1);

    public SavingsAccount(Integer acctNum, Double initialDeposit, OverdraftProtection odType){
        this.accountNumber=acctNum;this.accountBalance=initialDeposit;this.overdraftProtection=odType;
    }
    @Override
    public String toString(){
        return accountType+" Account - Account Number: "+accountNumber+" Balance: "+accountBalance;
    }

}
