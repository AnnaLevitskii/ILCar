package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class HelperBase {
    WebDriver wd;



    public String getTempPassword() {
        String filePath = "credentials.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber == 2) {
                    return line;
                }
                lineNumber++;
            }
            return line;
        } catch (IOException e) {
            e.printStackTrace();
            return " ";
        }
    }

    public String getTempEmail() {
        String filePath = "credentials.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
            return " ";
        }
    }

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }
    public void findAndType(By locator, String text){
        WebElement el = wd.findElement(locator);
        el.click();
        el.clear();
        if(text!=null){
            el.sendKeys(text);
        }
    }
    public void click(By locator){
        WebElement el = wd.findElement(locator);
        el.click();
    }
    private WebElement findElementBase(By locator){
        return wd.findElement(locator);
    }
    private List<WebElement> findElementsBase(By locator){
        return wd.findElements(locator);
    }
    public boolean isElPressent(By locator){
        return !findElementsBase(locator).isEmpty();
    }

    public String randomName(){
        String[] names = {"Abby", "Mary", "Sam", "Jon", "Rod" };
        String name = names[(new Random().nextInt(names.length-1))];
        System.out.println(name);
        return name;
    }
    public String randomLName(){
        String[] lNames = {"Black", "Green", "Parker", "Swon", "Reply" };
        String lName = lNames[(new Random().nextInt(lNames.length-1))];
        System.out.println(lName);
        return lName;
    }

    public String randomEmail(){
        String email = randomLName().toLowerCase() + (new Random().nextInt(2000)) + "@gmail.com";
        return email;
    }
    public String randomPassword(){
        String password = randomLName() + (new Random().nextInt(2000)) + "!";
        return password;
    }
    public void navigateTo(String URL) {
        wd.navigate().to(URL);
    }



}
