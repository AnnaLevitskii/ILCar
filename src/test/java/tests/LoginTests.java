package tests;

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
        System.out.println(app.getHelperUser().isLogged());
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void logInPositiveTest2(){
        //User user = new User("parker47@gmail.com", "Swon634!");
        User user1 = new User().setEmail("parker47@gmail.com").setPassword("Swon634!");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user1);
        app.getHelperUser().submit();
        logger.info("Assert check that popup 'Logged in' is present ");
        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//h1[text()='Logged in']")), "Logged in" );
    }
    @Test
    public void logInPositiveTest(){
        app.getHelperUser().openLoginForm();
        System.out.println(app.getHelperUser().getTempEmail()+ "    "+  app.getHelperUser().getTempPassword());
        app.getHelperUser().fillLoginForm(app.getHelperUser().getTempEmail(), app.getHelperUser().getTempPassword());
        app.getHelperUser().submit();
        logger.info("Assert check that popup 'Logged in' is present ");
        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//h1[text()='Logged in']")), "Logged in" );
    }
    @Test
    public void logInPositiveTest1(){
        app.getHelperUser().openLoginForm();
        System.out.println(app.getHelperUser().getTempEmail()+ "    "+  app.getHelperUser().getTempPassword());
        app.getHelperUser().fillLoginForm(app.getHelperUser().getTempEmail(), app.getHelperUser().getTempPassword());
        app.getHelperUser().submit();
        logger.info("Assert check that popup 'Logged in' is present ");
        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//h1[text()='Logged in']")), "Logged in" );
    }
    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("parker47gmail.com","Swon634!");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        logger.info("Assert check that 'Yalla' button is disabled ");
        Assert.assertTrue(app.getHelperUser().isButtonYallaDisabled());
    }
    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("parker47@gmail.com","Swon63");
        app.getHelperUser().submit();

        logger.info("Assert check that popup 'Login or Password incorrect' is present ");
        Assert.assertTrue(app.getHelperUser().getMessage(By.tagName("mat-dialog-container")).contains("Login or Password incorrect"));
    }
    @Test
    public void loginUnregistredUser(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("parker471234@gmail.com","Swon634!");
        app.getHelperUser().submit();

        logger.info("Assert check that popup 'Login or Password incorrect' is present ");
        Assert.assertTrue(app.getHelperUser().getMessage(By.tagName("mat-dialog-container")).contains("Login or Password incorrect"));
    }

    @AfterMethod
    public void postCondidons(){
        app.getHelperUser().closeOkPopup();
    }

}
