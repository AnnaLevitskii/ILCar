package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrTests extends TestBase{
    @Test
    public void registrationPositiveTest(){
        app.getHelperUser().openRegForm();
        app.getHelperUser().fillRegForm(app.getHelperUser().randomName(),app.getHelperUser().randomLName(), app.getHelperUser().randomEmail(), app.getHelperUser().randomPassword());
        app.getHelperUser().regSubmit();
        Assert.assertTrue(app.getHelperUser().isRegistred());


    }
}
