package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class HelperCar extends HelperBase{
    By addNewCarTab = By.id("1");
    By inputLocation = By.id("pickUpPlace");
    //By inputLocation = By.cssSelector("input[placeholder='Enter your address']");
    By inputManufacture = By.id("make");
    By inputModel = By.id("model");
    By inputYear = By.id("year");
    By inputFuel = By.id("fuel");
    By inputSeats = By.id("seats");
    By inputClass = By.id("class");
    By inputSerialNumber = By.id("serialNumber");
    By inputPrice = By.id("price");
    By inputAbout = By.id("about");
    By inputPhotos = By.id("photos");

    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        click(addNewCarTab);
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        findAndType(inputManufacture, car.getManufacture());
        findAndType(inputModel, car.getModel());
        findAndType(inputYear, car.getYear());
        findAndType(inputYear, car.getYear());
        select(inputFuel, car.getFuel());
        findAndType(inputSeats, String.valueOf(car.getSeats()));
        findAndType(inputClass, car.getCarClass());
        findAndType(inputSerialNumber, car.getRegistrationNumber());
        findAndType(inputPrice, String.valueOf(car.getPrice()));
        findAndType(inputAbout, car.getAbout());

    }

    private void select(By locator, String options) {
        Select select = new Select(wd.findElement(locator));
        if(!options.isEmpty()){
            select.selectByValue(options);
        }else {
            JavascriptExecutor js = (JavascriptExecutor) wd;
            js.executeScript("document.getElementById('fuel').value=''");
        }
    }

    private void typeLocation(String location) {
        if (!location.isEmpty()) {
            findAndType(inputLocation, location);
            clickWithWait(By.className("pac-item"), 10);
        }
    }


    public void closeAddAnotherCarPopup() {
        if(isElPressent(By.xpath("//button[text()='Add another car']"))){
            click(By.xpath("//button[text()='Add another car']"));
            wd.navigate().refresh();
        }
    }

    public void attachPhoto(String link) {
        wd.findElement(inputPhotos).sendKeys(link);
    }
    public void attachPhotoWithRelativePath(String link) {
        File pic = new File(link);
        String abPath = pic.getAbsolutePath();
        wd.findElement(inputPhotos).sendKeys(abPath);
        System.out.println(" - >"+abPath);
    }


}
