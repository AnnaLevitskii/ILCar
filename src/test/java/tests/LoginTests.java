package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @Test
    public void logInPositiveTest(){
        app.getHelperUser().openLoginForm();
        System.out.println(app.getHelperUser().getTempEmail()+ "    "+  app.getHelperUser().getTempPassword());
        app.getHelperUser().fillLoginForm(app.getHelperUser().getTempEmail(), app.getHelperUser().getTempPassword());
        app.getHelperUser().loginSubmit();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }
}
