package kelley.josh.Model;

/**
 * Created by joshuakelley on 9/14/16.
 */
public class CheckingAccount extends Account{
    AccountType accountType=AccountType.CHECKING;
    final Double interestRate=null;

        public CheckingAccount(Integer acctNum, Double initialDeposit, OverdraftProtection odType){
        this.accountNumber=acctNum;this.accountBalance=initialDeposit;this.overdraftProtection=odType;
    }

    @Override
    public String toString(){
        return accountType+" Account - Account Number: "+accountNumber+" Balance: "+accountBalance;
    }
}
