package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class HelperUser extends HelperBase{

    By registrTab = By.cssSelector("a[href^='/registration']");
    By inputName = By.id("name");
    By inputLName = By.id("lastName");
    By inputEmail = By.id("email");
    By inputPassword = By.id("password");
    By checkboxReg = By.cssSelector("div[class='checkbox-container']");
    By buttonReg = By.xpath("//button[text()='Y’alla!']");
    By loginTab = By.cssSelector("a[href^='/login']");

    By buttonLogin = By.xpath("//button[text()='Y’alla!']");



    public HelperUser(WebDriver wd) {
        super(wd);
    }
    public  void openRegForm(){
        click(registrTab);
    }
    public void fillRegForm(String name, String lName, String email, String password){
        findAndType(inputName, name);
        findAndType(inputLName, lName);
        findAndType(inputEmail, email);
        findAndType(inputPassword, password);
        System.out.println("      "+ email+ "      "+ password);
        click(checkboxReg);

        String fileName = "credentials.txt";
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(email);
            writer.newLine();
            writer.write(password);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }
    public void regSubmit(){
        click(buttonReg);
    }

    public boolean isRegistred() {
        return isElPressent(By.xpath("//h1[text()='Registered']"));
    }
    public  void openLoginForm(){
        click(loginTab);
    }
    public void fillLoginForm(String email, String password){
        findAndType(inputEmail, email);
        findAndType(inputPassword, password);
    }
    public void loginSubmit(){
        click(buttonLogin);
    }
    public boolean isLogged() {
        return isElPressent(By.xpath("//h1[text()='Logged in']"));
    }

}
