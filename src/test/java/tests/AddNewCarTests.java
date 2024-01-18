package tests;


import manager.DataProviderCar;
import models.Car;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase{
    @BeforeClass
    public void preConditions(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("parker47@gmail.com").setPassword("Swon634!"));
        }
    }

    @Test(dataProvider = "newCarData_success", dataProviderClass = DataProviderCar.class)
    public void addNewCar_success(Car car){
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        logger.info("Assert check that popup 'Car added' is present ");
        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//mat-dialog-container//h1")), "Car added" );
    }
    @Test(dataProvider = "newCarData_success", dataProviderClass = DataProviderCar.class)
    public void addNewCar_successWithPhoto(Car car){
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
       // app.getHelperCar().attachPhoto("/Users/anna/Documents/GitHub/ILCar/02-bugatti-cd-nardo-testing.jpg");
        app.getHelperCar().attachPhotoWithRelativePath("02-bugatti-cd-nardo-testing.jpg");
        app.getHelperCar().submit();

        logger.info("Assert check that popup 'Car added' is present ");
        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//mat-dialog-container//h1")), "Car added" );

    }
    @Test(dataProvider = "carData_existit", dataProviderClass = DataProviderCar.class)
    public void addNewCar_negativeRegNumberIsAlreadyExisted(Car car){
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        logger.info("Assert check that popup 'Car adding failed' is present ");
        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//mat-dialog-container//h1")), "Car adding failed" );

    }

    @Test(dataProvider = "carData_negative", dataProviderClass = DataProviderCar.class)
    public void addNewCar_negative(Car car){
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        logger.info("Assert check that popup 'Car adding failed' is present ");
        Assert.assertTrue(app.getHelperUser().isButtonYallaDisabled());
    }

    @AfterMethod
    public void postCondidons(){
        app.getHelperCar().closeAddAnotherCarPopup();
        app.getHelperUser().closeOkPopup();
        app.getHelperCar().refreshPage();
    }
}
