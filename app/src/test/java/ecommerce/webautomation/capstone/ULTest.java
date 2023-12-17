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
        String productName = ConfigReader.getProductName();

        homePage.goToHomePage();
        pdp.selectProduct();
        String expectedTitle = productName + " – ul-web-playground";
        waits.waitForTitleToBeChanged("Alfa – ul-web-playground");
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Title does not match");

        String nameOfProduct = pdp.getNameOfProduct();
        Assert.assertEquals(nameOfProduct,"Alfa");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
