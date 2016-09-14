package kelley.josh.View;

import kelley.josh.Controller.Controller;

/**
 * Created by joshuakelley on 9/13/16.
 */
public class View extends Controller{
    protected static boolean exit=false;
    protected static void welcome(){
        System.out.println("*******************************     Welcome to First Bank of Josh     *******************************");
    }
    protected static void start(){
        System.out.println();
        System.out.println("1: Sign Up\n2: Sign In\n3: Exit");
        switch (getInt()){
            case 1: signUp();
            case 2: signIn();
            case 3: exit();
            default:System.out.println("Option not Available");
        }
    }
    protected static void exit(){
        exit=true;
        System.out.println("Thank you for choosing First Bank of Josh.\n\nGoodbye");
    }
}
