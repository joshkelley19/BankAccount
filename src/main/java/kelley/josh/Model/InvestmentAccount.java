package kelley.josh.Model;

/**
 * Created by joshuakelley on 9/14/16.
 */
public class InvestmentAccount extends Account{
    final AccountType accountType=AccountType.INVESTMENT;
    final OverdraftProtection overdraftProtection = OverdraftProtection.ENABLED;
    final Double interestRate=new Double(2.5);

    public InvestmentAccount(Integer acctNum, Double initialDeposit){
        this.accountNumber=acctNum;this.accountBalance=initialDeposit;
    }

    @Override
    public String toString(){
        return accountType+" Account - Account Number: "+accountNumber+" Balance: "+accountBalance;
    }
}
