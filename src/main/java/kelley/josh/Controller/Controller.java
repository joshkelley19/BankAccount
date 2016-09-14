package kelley.josh.Controller;

import kelley.josh.Model.Profile;

import java.util.Scanner;

public class Controller {
    static Profile currentProfile;
    static Scanner scan = new Scanner(System.in);
    private static String firstName;
    private static String lastName;
    private static String user;
    private static String pass;
    static String[] info = {"First Name: ","Last Name: ","User Name: ","Password (between 6-16 characters): "};
    private static String actualInfo[]=new String[4];


    protected static void signUp(){
        getInfo();
        currentProfile=new Profile(firstName,lastName,user,pass);
    }
    protected static void signIn(){
        do {
        }while (currentProfile.mainMenu());
    }
//    protected static Account newAccount(){
//        Account createdAccount;
//        System.out.println("What type of Account would you like to open today?");
//        System.out.println("Enter 1 for Checking; Enter 2 for Savings; Enter 3 for for Investment");
//        switch (getInt()){
//            case 1: createdAccount=new CheckingAccount();
//            case 2: createdAccount=new SavingsAccount();
//            case 3: createdAccount=new InvestmentAccount();
//            default:
//                System.out.println("That is not a valid option.");
//        }
//        createdAccount=new Account(Integer acctNum, Double intRate, Double initialDeposit);
//        return createdAccount;
//    }
    protected static void getInfo(){
        for (int i=0;i<info.length;i++){
            System.out.print(info[i]);
            if (info[i].charAt(0)=='P'){
                pass=checkPassword(getWord());
            }else {
                actualInfo[i]=getWord();
            }
        }
        firstName=actualInfo[0];lastName=actualInfo[1];user=actualInfo[2];
    }
    protected static String getWord(){
        return scan.next();
    }
    public static int getInt(){
        return scan.nextInt();
    }
    protected static String checkPassword(String password){
        int length=password.length();
        String correct=password;
        while (length<6){
            System.out.println("Password too short. Try again");
            correct=getWord();
            length=correct.length();
        }
        while (length>16){
            System.out.println("Password too long. Try again");
            correct=getWord();
            length=correct.length();
        }
        return correct;
    }
}
