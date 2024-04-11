import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Firefox {
    @Test
    public void test(){
        System.setProperty("webdriver.gecko.driver", "/Users/anna/Tools/geckodriver");
        WebDriver driver = new FirefoxDriver();

        //WebDriver deiver = new FirefoxDriver(options);
        driver.navigate().to("https://github.com/mozilla/geckodriver/releases");
    }

}
