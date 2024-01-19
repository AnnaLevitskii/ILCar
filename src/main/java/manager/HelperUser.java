package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

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
    public void fillLoginForm(User user){
        findAndType(inputEmail, user.getEmail());
        findAndType(inputPassword, user.getPassword());
    }
    public void fillRegistrationForm(User user){
        findAndType(inputName, user.getFirstName());
        findAndType(inputLName, user.getLastName());
        findAndType(inputEmail, user.getEmail());
        findAndType(inputPassword, user.getPassword());
    }


    public boolean isLogged() {
        return isElPressent(By.xpath("//a[text()=' Logout ']"));
    }
    public void logout() {
        click(By.xpath("//a[text()=' Logout ']"));
    }
    public void closeOkPopup() {
        if(isElPressent(By.tagName("mat-dialog-container"))&& isElPressent(By.xpath("//button[text()='Ok']"))){
            click(By.xpath("//button[text()='Ok']"));
        }
    }
    public void checkPolicy() {
        JavascriptExecutor js = (JavascriptExecutor) wd;
        try{
            if(isElPressent(checkboxReg)){
                if(!wd.findElement(checkboxReg).isSelected())
                    //click(checkboxReg);
                    js.executeScript("document.querySelector('#terms-of-use').click();");
            }
        }catch (Exception e){

        }
    }
    public void checkPolicyXY() {
        WebElement lable = wd.findElement(By.id("terms-of-use"));
        Rectangle rect = lable.getRect();
        int xOffSet = -rect.getWidth()/2;
        Actions actions = new Actions(wd);
        actions.moveToElement(lable, xOffSet, 0).click().release().perform();


        Dimension size = wd.manage().window().getSize();
        System.out.println(size);

    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submit();
        closeOkPopup();
    }
}
