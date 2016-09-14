package kelley.josh.Model;

/**
 * Created by joshuakelley on 9/14/16.
 */
public class InvestmentAccount extends Account{
    final AccountType accountType=AccountType.INVESTMENT;
    final Double interestRate=new Double(2.5);

    public InvestmentAccount(Integer acctNum, Double initialDeposit, OverdraftProtection odType){
        this.accountNumber=acctNum;this.accountBalance=initialDeposit;this.overdraftProtection=odType;
    }

    @Override
    public String toString(){
        return accountType+" Account - Account Number: "+accountNumber+" Balance: "+accountBalance;
    }
}
