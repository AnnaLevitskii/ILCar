package tests;


import manager.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);
    ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setApp(){
        app.init();
    }
    @BeforeMethod
    public void startLogger(Method m){
        String className = this.getClass().getName();
        String[] arrName = className.split("ts.", 2);;
        logger.info(arrName[1]+"___"+m.getName());
    }
    @AfterMethod
    public void calltestStatus(ITestResult result) throws IOException {
        testStatus(result);
    }
    public void testStatus(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.info("FAILURE \n");

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("SUCCESS \n");

        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.info("SKIP \n");

        } else {
            logger.info("No go \n");
        }
    }
    @AfterSuite
    public void tearDown(){
        app.stop();
    }
}
