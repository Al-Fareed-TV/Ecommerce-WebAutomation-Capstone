package ecommerce.webautomation.capstone;



import ecommerce.webautomation.capstone.pages.BasePage ;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import ecommerce.webautomation.capstone.utils.TestLogger;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;
import java.time.Duration;


@Listeners(TestListener.class)
public class BaseTest {

    WebDriver driver;
    @BeforeClass
    public void setUp(){
        driver = new BasePage().getDriver("chrome");
    }

    @BeforeMethod
    public void beforeMethod(Method m){
        TestLogger.info("STARTING TEST: " + m.getName());
        TestLogger.info("THREAD ID: " + Thread.currentThread().getId());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        TestLogger.shutdown();
    }

}
