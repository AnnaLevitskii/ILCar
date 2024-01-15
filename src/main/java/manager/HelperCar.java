package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase{
    By addNewCarTab = By.id("1");
    By inputLocation = By.xpath("//*[@id='pickUpPlace' or @id='city']") ;
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
            try{
                findAndType(inputLocation, location);
                clickWithWait(By.className("pac-item"), 10);
            }catch (Exception e){
                logger.info("Error in typeLocation");
            }
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


    public void searchCarCurrentMonth(String city, String dateFrom, String dateTo) {
        searchCar(city, dateFrom, dateTo);
    }
    public void picADay(String date){
        String[] data1 = date.split("/", 3);
        if(data1[1].charAt(0) == '0' ){
            data1[1] = String.valueOf(data1[1].charAt(1));
        }
        click(By.xpath("//*[text()=' "+data1[1]+" ']"));
    }

    public boolean isListOfCarsAppeared() {
        return isElPressent(By.tagName("car-details"));
    }

    public void searchCarCurrentYear(String city, String dateFrom, String dateTo) {
        searchCar(city, dateFrom,  dateTo);
    }
    public void picYear(String date){
        LocalDate now = LocalDate.now();
        int yearNow = now.getYear();
        String[] data1 = date.split("/", 3);
        int yearFromString = Integer.parseInt(data1[2]);
        if(yearFromString==yearNow || yearFromString==yearNow+1) {
            click(By.xpath("//button[@mat-button]"));
            click(By.xpath("//td/div[text()=' " + data1[2] + " ']"));
        }else {
            logger.info("Non valid data `year` "+ date);
        }
    }
    public void picMonth(String date){
        String[] data1 = date.split("/", 3);
        int month = Integer.parseInt(data1[0]);
        String x = null;
        switch (month) {
            case 1:
                x ="JAN";
                break;
            case 2:
                x="FEB";
                break;
            case 3:
                x="MAR";
                break;
            case 4:
                x="APR";
                break;
            case 5:
                x="MAY";
                break;
            case 6:
                x="JUN";
                break;
            case 7:
                x="JUL";
                break;
            case 8:
                x="AUG";
                break;
            case 9:
                x="SEP";
                break;
            case 10:
                x="OCT";
                break;
            case 11:
                x="NOV";
                break;
            case 12:
                x="DEC";
                break;
            default:
                logger.info("Non valid data `month`"+ date);
                break;
        }
        By monthName = By.xpath("//td/div[text()=' "+x+" ']");
        click(monthName);
    }

    public void searchCar(String city, String dateFrom, String dateTo) {
        LocalDate now = LocalDate.now();
        LocalDate dateToFromString = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/dd/yyyy"));
        if(dateToFromString.isBefore(now.plusYears(1))) {
            typeLocation(city);
            pause(100);
            WebElement inputDate = wd.findElement(By.xpath("//*[@id='dates' or @ng-reflect-name='dates']"));
            inputDate.click();
            inputDate.clear();
            pause(100);
            picYear(dateFrom);
            picMonth(dateFrom);
            picADay(dateFrom);
            picYear(dateTo);
            picMonth(dateTo);
            picADay(dateTo);
        }else {
            logger.info("Non valid data `dateTo`"+ dateTo);
        }
    }
}
