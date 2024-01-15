package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{
    @BeforeClass
    public void preConditions(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("parker47@gmail.com").setPassword("Swon634!"));
        }
    }
    @Test
    public void searchCarCurrentMonth_Success(){
        app.getHelperCar().searchCarCurrentMonth("Tel Aviv, Iseael", "1/16/2024", "1/18/2024");
        app.getHelperCar().submit();

        logger.info("Assert that list of cars is appeared");
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }
    @Test
    public void searchCarCurrentYear_Success(){
        app.getHelperCar().searchCarCurrentYear("Tel Aviv, Iseael", "1/20/2024", "5/24/2024");
        app.getHelperCar().submit();

        logger.info("Assert that list of cars is appeared");
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }
    @Test
    public void searchCar_Success(){
        app.getHelperCar().searchCar("Tel Aviv, Iseael", "11/20/2024", "1/07/2025");
        app.getHelperCar().submit();

        logger.info("Assert that list of cars is appeared");
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }


}
