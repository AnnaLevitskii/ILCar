package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ListenerWD extends AbstractWebDriverEventListener {
    Logger logger = LoggerFactory.getLogger(ListenerWD.class);

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        super.beforeNavigateTo(url, driver);
        logger.info("NavigateTo --- > "+ url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        super.afterNavigateTo(url, driver);
        logger.info("NavigateTo --- > "+ url);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        super.onException(throwable, driver);
        logger.info("!Exception: "+ throwable.getMessage());
        //logger.info("---> throwable.fillInStackTrace() --> "+throwable.fillInStackTrace().toString());
        HelperBase helperBase = new HelperBase(driver);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd__HH:mm");
        Date date = new Date(System.currentTimeMillis());
        String dateStr = simpleDateFormat.format(date);
        String link = "src/test/screenshots/screen-"+dateStr+".png";
        helperBase.getScreenshot(link);
        logger.info("Link to screenshot: " + link);
    }
    public ListenerWD() {
        super();
        this.logger = logger;
    }
}
