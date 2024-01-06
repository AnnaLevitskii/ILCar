package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preConditions(){
        System.out.println(app.getHelperUser().isLogged());
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void RegistrationPositiveTest(){
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().setEmail("parker"+i+"@gmail.com").setPassword("Swon634!").withFirstName("Lisa").withLastName("Brown");
        app.getHelperUser().openRegForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();

        Assert.assertFalse(app.getHelperUser().isButtonYallaDisabled());

        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//mat-dialog-container//h1")), "Registered" );
    }
    @Test
    public void RegistrationNegativeTest_name(){
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().setEmail("parker"+i+"@gmail.com").setPassword("Swon634!").withFirstName("").withLastName("Brown");
        app.getHelperUser().openRegForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();

        Assert.assertTrue(app.getHelperUser().isButtonYallaDisabled());
    }
    @Test(description = "Bug report #", enabled = false)
    public void RegistrationNegativeTest_lastName(){
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().setEmail("parker"+i+"@gmail.com").setPassword("Swon634!").withFirstName("Lina").withLastName("");
        app.getHelperUser().openRegForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();

        Assert.assertTrue(app.getHelperUser().isButtonYallaDisabled());
    }
    @Test
    public void RegistrationNegativeTest_email(){
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().setEmail("parker"+i+"gmail.com").setPassword("Swon634!").withFirstName("Lina").withLastName("Brown");
        app.getHelperUser().openRegForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();

        Assert.assertTrue(app.getHelperUser().isButtonYallaDisabled());
    }
    @Test
    public void RegistrationNegativeTest_password(){
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().setEmail("parker"+i+"@gmail.com").setPassword("swon634").withFirstName("Lina").withLastName("");
        app.getHelperUser().openRegForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();

        Assert.assertTrue(app.getHelperUser().isButtonYallaDisabled());
    }
    @Test
    public void RegistrationNegativeTest_userWithEmailIsExisted(){
        User user = new User().setEmail("parker47@gmail.com").setPassword("Swon634!").withFirstName("Lina").withLastName("Nowen");
        app.getHelperUser().openRegForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().getMessage(By.tagName("mat-dialog-container")).contains("User already exists"));
    }

    @AfterMethod
    public void postCondidons(){
        app.getHelperUser().closeOkPopup();
        app.getHelperUser().checkPolicy();
    }


}
