package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        User user = new User("parker47@gmail.com", "Swon634!");
        User user1 = new User().setEmail("parker47@gmail.com").setPassword("Swon634!");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().loginSubmit();
        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//h1[text()='Logged in']")), "Logged in" );
    }
    @Test
    public void logInPositiveTest(){
        app.getHelperUser().openLoginForm();
        System.out.println(app.getHelperUser().getTempEmail()+ "    "+  app.getHelperUser().getTempPassword());
        app.getHelperUser().fillLoginForm(app.getHelperUser().getTempEmail(), app.getHelperUser().getTempPassword());
        app.getHelperUser().loginSubmit();
        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//h1[text()='Logged in']")), "Logged in" );
    }
    @Test
    public void logInPositiveTest1(){
        app.getHelperUser().openLoginForm();
        System.out.println(app.getHelperUser().getTempEmail()+ "    "+  app.getHelperUser().getTempPassword());
        app.getHelperUser().fillLoginForm(app.getHelperUser().getTempEmail(), app.getHelperUser().getTempPassword());
        app.getHelperUser().loginSubmit();
        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//h1[text()='Logged in']")), "Logged in" );
    }
    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("parker47gmail.com","Swon634!");
        app.getHelperUser().loginSubmit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("parker47@gmail.com","Swon63");
        app.getHelperUser().loginSubmit();

        Assert.assertTrue(app.getHelperUser().getMessage(By.tagName("mat-dialog-container")).contains("Login or Password incorrect"));
    }
    @Test
    public void loginUnregistredUser(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("parker471234@gmail.com","Swon634!");
        app.getHelperUser().loginSubmit();

        Assert.assertTrue(app.getHelperUser().getMessage(By.tagName("mat-dialog-container")).contains("Login or Password incorrect"));
    }

    @AfterMethod
    public void postCondidons(){
        app.getHelperUser().closeOkPopup();
    }
}
