package kelley.josh.BankAccountTests;

import kelley.josh.Model.Profile;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by joshuakelley on 9/14/16.
 */
public class ProfileTests {
    Profile profile;
    @Before
    public void initialize(){
        profile = new Profile("Josh","Kelley","Josh","Kelley");
        profile.createAccount();
    }

    @Test
    public void
}
