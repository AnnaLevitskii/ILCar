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

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preConditions(){

        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test(dataProvider = "userData_UserExists", dataProviderClass = DataProviderUser.class)
    public void logIn_positive(User user){
        //User user = new User("parker47@gmail.com", "Swon634!");
        //User user1 = new User().setEmail("parker47@gmail.com").setPassword("Swon634!");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        logger.info("Assert check that popup 'Logged in' is present ");
        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//h1[text()='Logged in']")), "Logged in" );
    }
    @Test
    public void logIn_successWithGeneratedData(){
        app.getHelperUser().openLoginForm();
        System.out.println(app.getHelperUser().getTempEmail()+ "    "+  app.getHelperUser().getTempPassword());
        app.getHelperUser().fillLoginForm(app.getHelperUser().getTempEmail(), app.getHelperUser().getTempPassword());
        app.getHelperUser().submit();
        logger.info("Assert check that popup 'Logged in' is present ");
        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//h1[text()='Logged in']")), "Logged in" );
    }

    @Test(dataProvider = "userData_negativePasswordEmail", dataProviderClass = DataProviderUser.class)
    public void login_negativeButtonYallaDisabled(User user){ // Test fails: invalid password, blank password, blank email
        app.getHelperUser().pause(1000);
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user.getEmail(),user.getPassword());
        app.getHelperUser().pause(1000);
        app.getHelperUser().submit();

        logger.info("Assert check that 'Yalla' button is disabled ");
        Assert.assertTrue(app.getHelperUser().isButtonYallaDisabled());
    }
    @Test
    public void login_negativeWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("parker47@gmail.com","Swon63");
        app.getHelperUser().submit();

        logger.info("Assert check that popup 'Login or Password incorrect' is present ");
        Assert.assertTrue(app.getHelperUser().getMessage(By.tagName("mat-dialog-container")).contains("Login or Password incorrect"));
    }
    @Test(dataProvider = "userData_successReg", dataProviderClass = DataProviderUser.class)
    public void login_negativeUnregistredUser(User user){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submit();

        logger.info("Assert check that popup 'Login or Password incorrect' is present ");
        Assert.assertTrue(app.getHelperUser().getMessage(By.tagName("mat-dialog-container")).contains("Login or Password incorrect"));
    }

    @AfterMethod
    public void postCondidons(){
        app.getHelperUser().closeOkPopup();
    }

}
