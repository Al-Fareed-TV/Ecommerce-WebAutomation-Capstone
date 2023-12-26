package ecommerce.webautomation.capstone;

import ecommerce.webautomation.capstone.pages.CartPage;
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
        boolean isProductAvailable = pdp.verifyProductAvailabilityAndAddToCart();
        if (isProductAvailable) {
            boolean itemAddedMessageDisplayed = pdp.isItemAddedMessageDisplayed();
            Assert.assertTrue(itemAddedMessageDisplayed, "Item added message is not displayed");
            boolean itemAddedToCart = pdp.verifyItemAddedToCart();
            Assert.assertTrue(itemAddedToCart, "Item count does not match");
        }

    }

    @Test
    public void validateCartContents() {
        testPDP();
        CartPage cartPage = CartPage.cartPageInstance(driver);
        String nameOfItemInCart = cartPage.navigateToCartPage().nameOfItemInCart();
        Assert.assertEquals(nameOfItemInCart, "Alfa","Name of the product doesn't match!");

        int numberOfItemsInCart = cartPage.numberOfItemsInCart();
        Assert.assertEquals(numberOfItemsInCart, 1,"Number of items in  cart is not matching");

        String sizeOfTheItem = cartPage.sizeOfTheItem();
        Assert.assertEquals(sizeOfTheItem, "58 cm","Size of the item doesn't match!");

        String totalAmount = cartPage.getTotalAmount();
        Assert.assertEquals(totalAmount, "Rs. 312.55", "Price doesn't match!");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
