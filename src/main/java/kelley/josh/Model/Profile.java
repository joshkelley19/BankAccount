package kelley.josh.Model;

import java.util.ArrayList;
import java.util.Scanner;
import static kelley.josh.Controller.Controller.getInt;

public class Profile {
    public Scanner scan = new Scanner(System.in);
    public String firstName;
    public String lastName;
    public String userName;
    public String password;
    public Account currentAccount;
    ArrayList<Account> accounts=new ArrayList<Account>();

    public Profile(String first,String last,String user,String pass){
        this.firstName=first;this.lastName=last;this.userName=user;this.password=pass;
        createAccount();
        mainMenu();
    }

    public boolean mainMenu(){
        System.out.println(("Welcome "+firstName+"!\n\n"));
        System.out.print("Enter 1 to Choose an account; Enter 2 to Create New Account;Enter 3 to Transfer Funds; Enter 4 to Sign Out: ");
        switch (getInt()){
            case 1:printAccounts();
                currentAccount= selectAccount(getInt());
                while (currentAccount!=null){
                    manageAccount();
                }
                break;
            case 2:currentAccount=createAccount();break;
            case 3:transfer();break;
            case 4:return signOut();
            default:System.out.println("Option is not valid");return true;
        }
        return true;
    }

    protected boolean signOut(){
        return false;
    }

    public void printAccounts(){
        int index=1;
        for (Account a: accounts){
            System.out.println(index+" "+a.toString());
            index++;
        }
    }

    public Account selectAccount(int accountIndex){
        return accounts.get(accountIndex-1);
    }

    public void manageAccount(){
        System.out.println("What would you like to do?");
        printOptions();
        boolean access=checkAccessLevel(currentAccount.accountStatus);
        boolean open=isOpen(currentAccount.accountStatus);
        switch (getInt()){
            case 1:System.out.print("How much would you like to deposit? ");
                if (access) {
                    currentAccount.deposit(getMoney());
                }break;
            case 2:System.out.print("How much would you like to withdraw? ");Double dough=getMoney();
                if(access&&(currentAccount.overdraftProtection!= Account.OverdraftProtection.AUTOMATIC)){
                currentAccount.withdraw(dough);
            }else if((currentAccount.accountBalance-dough)<0){
                currentAccount.transferTo(currentAccount.transferAccount,dough);
            }break;
            case 3:if(open)currentAccount.viewBalance();break;
            case 4:transfer();break;
            case 5:if(open) {
                System.out.println("Are you sure you want to close your account?\nEnter 1 to Confirm; Enter Any Number to Cancel");
                accounts.remove(currentAccount.closeAccount(getInt()));
            }break;
            case 6:cancel();currentAccount=null;break;
            default:defaultOption();
        }
    }

    public void printOptions(){
        System.out.println("Enter 1 to Deposit; Enter 2 to Withdraw; Enter 3 to View Balance; Enter 4 to Transfer Money; Enter 5 to Close your Account; Enter 6 to Exit");
    }

    public Account createAccount(){
        System.out.println("What kind of account would you like?\nEnter 1 for Checking; Enter 2 for Savings; Enter 3 Investment");
        Account createdAccount;
        switch (getInt()){
            case 1:createdAccount=new CheckingAccount(setAccountNumber(),getMoney(),setOverdraftProtectionType());break;
            case 2:createdAccount= new SavingsAccount(setAccountNumber(),getMoney(),setOverdraftProtectionType());if (createdAccount.overdraftProtection== Account.OverdraftProtection.AUTOMATIC){
                setOverdraftProtectionAccount(createdAccount);
            }break;
            case 3:createdAccount= new InvestmentAccount(setAccountNumber(),getMoney());break;
            case 4:cancel();return null;
            default:defaultOption();return null;
        }
        createdAccount.transactions.add(new Transaction(createdAccount.accountType, Transaction.TransactionType.CLOSEACCOUNT,true,true));
        accounts.add(createdAccount);
        return createdAccount;
    }

    public void setOverdraftProtectionAccount(Account accountSettingOverdraft){
        if(!accounts.isEmpty()) {
            printAccounts();
            System.out.println("Which account would you like to automatically draw from?");
            accountSettingOverdraft.transferAccount = selectAccount(getInt());
        }else{
            System.out.println("No accounts to draw from. Overdraft Protection set to Disabled");
            accountSettingOverdraft.overdraftProtection= Account.OverdraftProtection.DISABLED;
        }
    }

    public void transfer() {
        System.out.println("Transfer to: Enter 1 for your accounts; Enter 2 for outside accounts: ");
        switch (getInt()){
            case 1:transferToPersonal();break;
            case 2:transferToOutside();break;
            case 3:cancel();break;
            default:defaultOption();
        }
    }

    public boolean transferToPersonal(){
        printAccounts();
        System.out.println("Which account would you like to transfer to?\nEnter Number: ");
        Account to = selectAccount(getInt());
        printAccounts();
        System.out.println("Which account would you like to transfer from?\nEnter Number: ");
        Account from = selectAccount(getInt());
        Double money=getMoney();
        return from.transferTo(to, money);
    }

    public void transferToOutside(){
        System.out.println("Enter User Name of Account you want to transfer to: ");
        String userTo =getUser();
        System.out.println("Enter Account Number of Account you want to transfer to: ");
        int accountTo=getInt();

        //search profile arraylist for userName, acct num
        //create random acct num, not same as other acct nums, acct nums<> saved in controller
    }
    //    public Account getOutsideTransferAccount(){
//        for (Profile profileReceivingTransfer: profiles){
//            if((!accountReceivingTransfer.equals(currentAccount))&&accountReceivingTransfer.accountBalance>=money){
//                return accountReceivingTransfer;
//            }
//        }
//        return null;
//    }
    public boolean completeTransfer(Account moneyFrom, Account moneyTo, Double money){

        if (moneyFrom.withdraw(money)&&moneyTo.deposit(money))return true;
        return false;
    }

    public int setAccountNumber(){
        int idNumber = (int)(Math.random()*10000000);
        return idNumber;
    }

    public Double getMoney(){
        System.out.println("How much money?");
        return scan.nextDouble();
    }

    public String getUser(){
        return scan.next();
    }

    public Account.OverdraftProtection setOverdraftProtectionType(){
        while (true) {
            System.out.println("Do you want Overdraft Protection?");
            System.out.println("Enter 1 for Yes; Enter 2 for No; Enter 3 if you would like Automatic Account Transfer");
            switch (getInt()) {
                case 1:
                    return Account.OverdraftProtection.ENABLED;
                case 2:
                    return Account.OverdraftProtection.DISABLED;
                case 3:
                    return Account.OverdraftProtection.AUTOMATIC;
                default:
                    defaultOption();
            }
            return null;
        }
    }

    public boolean checkAccessLevel(Account.AccountStatus status){
        switch (status) {
            case FROZEN:
                System.out.println("Account is frozen, No Access. You may only View Balance and Close Account");
                return false;
            case CLOSED:
                System.out.println("Account Closed, No Access.");
                return false;
            case OPEN:
                return true;
        }
        return true;
    }

    public boolean isOpen(Account.AccountStatus status){
        if(status==Account.AccountStatus.OPEN){
            return true;
        }else System.out.println("Account Closed, No Access.");return false;
    }

    public void cancel(){
        System.out.println("Cancelling operation");
    }

    public void defaultOption(){
        System.out.println("Option not available");
    }

}
