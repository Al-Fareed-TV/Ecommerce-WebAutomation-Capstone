package ecommerce.webautomation.capstone;



import ecommerce.webautomation.capstone.shared.PageWaits;
import ecommerce.webautomation.capstone.utils.ConfigReader;
import ecommerce.webautomation.capstone.utils.DriverCreator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import ecommerce.webautomation.capstone.utils.TestLogger;
import org.testng.annotations.Listeners;
import java.lang.reflect.Method;

import static java.lang.Thread.sleep;

@Listeners(TestListener.class)
public class BaseTest {

    protected WebDriver driver = null;
    protected PageWaits waits = null;

    @BeforeClass
    public void setUp(){
        driver = DriverCreator.instantiateDriver(ConfigReader.getBrowser());
        waits = PageWaits.getPageWaitsObject(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m){
        TestLogger.info("STARTING TEST: " + m.getName());
        TestLogger.info("THREAD ID: " + Thread.currentThread().getId());
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        sleep(2000);
        driver.close();
        driver.quit();
        TestLogger.shutdown();
    }

}
