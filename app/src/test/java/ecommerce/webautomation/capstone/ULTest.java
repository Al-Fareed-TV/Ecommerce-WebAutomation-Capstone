package ecommerce.webautomation.capstone;

import ecommerce.webautomation.capstone.pages.HomePage;
import ecommerce.webautomation.capstone.pages.ProductDescriptionPage;
import ecommerce.webautomation.capstone.shared.PageWaits;
import ecommerce.webautomation.capstone.utils.ConfigReader;
import ecommerce.webautomation.capstone.utils.DriverCreator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ULTest {
    WebDriver driver = null;
    PageWaits waits = null;
    @BeforeClass
    public void setup() {
        this.driver = DriverCreator.instantiateDriver(ConfigReader.getBrowser());
        this.waits = PageWaits.getPageWaitsObject(this.driver);
    }

    @Test
    public void testPDP() {
        HomePage homePage = HomePage.getInstance(driver);
        ProductDescriptionPage pdp = ProductDescriptionPage.getPDPInstance(driver);

        homePage.goToHomePage();
        boolean productDetailsPageLoaded =
                pdp.selectProductByName()
                .isProductDetailsPageLoaded();
        Assert.assertTrue(productDetailsPageLoaded);

        pdp.verifyProductAvailabilityAndAddToCart();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
