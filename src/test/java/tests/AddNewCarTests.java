package tests;


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

    @Test
    public void addNewCarSuccess(){
        int i = new Random().nextInt(1000)+1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//mat-dialog-container//h1")), "Car added" );
    }
    @Test
    public void addNewCarNegative_regNumberIsAlreadyExisted(){

        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .registrationNumber("12345")
                .price(50.)
                .about("Nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(By.xpath("//mat-dialog-container//h1")), "Car adding failed" );
    }
    @Test
    public void addNewCarNegative_blankLocation(){
        int i = new Random().nextInt(1000)+1000;
        Car car = Car.builder()
                .location("")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void addNewCarNegative_blankManufacture(){
        int i = new Random().nextInt(1000)+1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void addNewCarNegative_blankModel(){
        int i = new Random().nextInt(1000)+1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void addNewCarNegative_blankYear(){
        int i = new Random().nextInt(1000)+1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void addNewCarNegative_blankFuel(){
        int i = new Random().nextInt(1000)+1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("1992")
                .fuel("")
                .seats(4)
                .carClass("C")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void addNewCarNegative_blankSeats(){
        int i = new Random().nextInt(1000)+1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("1992")
                .fuel("Petrol")
                .seats(null)
                .carClass("C")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void addNewCarNegative_blankClass(){
        int i = new Random().nextInt(1000)+1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("1992")
                .fuel("Petrol")
                .seats(4)
                .carClass("")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void addNewCarNegative_blankRegNumber(){
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("1992")
                .fuel("Petrol")
                .seats(4)
                .carClass("c")
                .registrationNumber("")
                .price(50.)
                .about("Nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void addNewCarNegative_blankPrice(){
        int i = new Random().nextInt(1000)+1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("1992")
                .fuel("Petrol")
                .seats(4)
                .carClass("c")
                .registrationNumber(String.valueOf(i))
                .price(null)
                .about("Nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @AfterMethod
    public void postCondidons(){
        app.getHelperCar().closeAddAnotherCarPopup();
        app.getHelperUser().closeOkPopup();
        app.getHelperCar().refreshPage();
    }
}
