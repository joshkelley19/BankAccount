package kelley.josh.BankAccountTests;

import kelley.josh.Model.CheckingAccount;
import kelley.josh.Model.SavingsAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static kelley.josh.Model.Account.OverdraftProtection.*;

/**
 * Created by joshuakelley on 9/14/16.
 */
public class AccountTests {
    CheckingAccount checking;
    SavingsAccount savings;
    double before,checkingAfter,savingsAfter,transferAmount;
    @Before
    public void initialize(){
        checking = new CheckingAccount(1, 100.0, ENABLED);
        savings = new SavingsAccount(2, 100.0, DISABLED);
        before=100.0;
        checkingAfter=50.0;
        savingsAfter=150.0;
        transferAmount=50.0;
    }
    @Test
    public void withdrawTest0(){
        Assert.assertTrue(checking.withdraw(transferAmount));
    }
    @Test
    public void withdrawTest1(){
        checking.withdraw(transferAmount);
        Assert.assertEquals("after withdraw",checkingAfter,checking.accountBalance,.01);
    }
    @Test
    public void depositTest0(){
        Assert.assertTrue(savings.deposit(transferAmount));
    }
    @Test
    public void depositTest1(){
        savings.deposit(transferAmount);
        Assert.assertEquals("after deposit",savingsAfter,savings.accountBalance,.01);
    }
    @Test
    public void transferTest0(){
        Assert.assertTrue(checking.transferTo(savings, transferAmount));
    }
    @Test
    public void transferTest1(){
        checking.transferTo(savings, transferAmount);
        Assert.assertEquals("checking after transfer",checkingAfter,checking.accountBalance,.01);
    }
    @Test
    public void transferTest2(){
        checking.transferTo(savings, transferAmount);
        Assert.assertEquals("savings after transfer",savingsAfter,savings.accountBalance,.01);
    }
}
