package tests;

import manager.DataProviderCar;
import models.Search;
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

    @Test(dataProvider = "newDateData_success", dataProviderClass = DataProviderCar.class)
    public void searchCar_Success(Search search){
        app.getHelperCar().searchCar("Tel Aviv, Iseael", "11/20/2024", "1/07/2025");
        app.getHelperCar().pause(1000);
        app.getHelperCar().getScreenshot("src/test/screenshots/searchCar_Success.png");
        app.getHelperCar().submit();

        logger.info("Assert that list of cars is appeared");
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }


}
