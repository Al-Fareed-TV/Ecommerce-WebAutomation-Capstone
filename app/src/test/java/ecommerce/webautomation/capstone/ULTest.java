package ecommerce.webautomation.capstone;

import ecommerce.webautomation.capstone.pages.CartPage;
import ecommerce.webautomation.capstone.pages.HomePage;
import ecommerce.webautomation.capstone.pages.LoginPage;
import ecommerce.webautomation.capstone.pages.ProductDescriptionPage;
import ecommerce.webautomation.capstone.shared.PageWaits;
import ecommerce.webautomation.capstone.utils.ConfigReader;
import ecommerce.webautomation.capstone.utils.DriverCreator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class ULTest extends BaseTest {

    @Test
    public void testPDP() throws InterruptedException {
        HomePage homePage = HomePage.getInstance(driver);
        ProductDescriptionPage pdp = ProductDescriptionPage.getPDPInstance(driver);
        LoginPage loginPage = LoginPage.getInstance(driver);


        homePage.goToHomePage();
        loginPage.loginWithCookies();

        driver.findElement(By.cssSelector("#shopify-section-header > sticky-header > header > div > a.header__icon.header__icon--account.link.focus-inset.small-hide")).click();

        this.waits.waitForTitleToBeChanged("Account – ul-web-playground");

        sleep(2000);

        this.driver.navigate().back();


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
    @Test(dependsOnMethods = "testPDP")
    public void validateCartContents() throws InterruptedException {
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

    @Test
    public void testTakeScreenShot(){
        Assert.fail();
    }


}
