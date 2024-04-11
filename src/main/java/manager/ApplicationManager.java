package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    EventFiringWebDriver wd;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    HelperUser helperUser;
    public HelperCar helperCar;

    static String browser;

    public ApplicationManager(){
        browser = System.getProperty("browser", BrowserType.CHROME);
    }

    public void init(){
        //wd = new EventFiringWebDriver(new FirefoxDriver());
        //wd = new EventFiringWebDriver(new ChromeDriver());
        if(browser.equals( BrowserType.FIREFOX)) {
            wd = new EventFiringWebDriver(new FirefoxDriver());
        }else if(browser.equals( BrowserType.SAFARI)) {
            wd = new EventFiringWebDriver(new SafariDriver());
        }else {
            wd = new EventFiringWebDriver(new ChromeDriver());
        }
        logger.info("Browser "+(wd).getCapabilities().getBrowserName()+"");
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.navigate().to("https://ilcarro.web.app");
        logger.info("Link " + wd.getCurrentUrl() +"\n\n");

        helperUser = new HelperUser(wd);
        helperCar= new HelperCar(wd);
        wd.register(new ListenerWD());
    }
    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperCar getHelperCar() {
        return helperCar;
    }

    public void stop() {
       wd.quit();
    }
}
