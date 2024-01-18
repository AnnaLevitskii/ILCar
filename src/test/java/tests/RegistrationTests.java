package tests;

import manager.DataProviderUser;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preConditions(){
        System.out.println(app.getHelperUser().isLogged());
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test(dataProvider = "userData_successReg", dataProviderClass = DataProviderUser.class)
    public void RegistrationPositiveTest(User user){
//        Random random = new Random();
//        int i = random.nextInt(1000);
        //User user = new User().setEmail("parker"+i+"@gmail.com").setPassword("Swon634!").withFirstName("Lisa").withLastName("Brown");
        app.getHelperUser().openRegForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();

        Assert.assertFalse(app.getHelperUser().isButtonYallaDisabled());

        app.getHelperUser().submit();

        logger.info("Assert check that popup 'Registered' is present ");
        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//mat-dialog-container//h1")), "Registered" );
    }

    @Test(dataProvider = "userData_negativeReg", dataProviderClass = DataProviderUser.class)
    public void Registration_negativeButtonYallaDisabled(User user){
        if(user.getLastName()!=""){
            app.getHelperUser().openRegForm();
            app.getHelperUser().fillRegistrationForm(user);
            app.getHelperUser().checkPolicy();
        }else {
            logger.info("Blank last name (description = 'Bug report #', enabled = false)");
        }

        logger.info("Assert check that button Yalla is disabled ");
        Assert.assertTrue(app.getHelperUser().isButtonYallaDisabled());
    }

    @Test(dataProvider = "userData_UserExists", dataProviderClass = DataProviderUser.class)
    public void Registration_negativeUserWithEmailIsExisted(User user){
        //User user = new User().setEmail("parker47@gmail.com").setPassword("Swon634!").withFirstName("Lina").withLastName("Nowen");
        app.getHelperUser().openRegForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        logger.info("Assert check that popup 'User already exists' is present ");
        Assert.assertTrue(app.getHelperUser().getMessage(By.tagName("mat-dialog-container")).contains("User already exists"));
    }

    @AfterMethod
    public void postCondidons(){
        app.getHelperUser().closeOkPopup();
        app.getHelperUser().checkPolicy();
    }

}
