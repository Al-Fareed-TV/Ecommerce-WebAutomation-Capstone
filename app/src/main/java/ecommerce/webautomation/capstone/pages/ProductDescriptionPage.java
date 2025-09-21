package ecommerce.webautomation.capstone.pages;

import ecommerce.webautomation.capstone.Exceptions.ProductUnavailableException;
import ecommerce.webautomation.capstone.shared.Action;
import ecommerce.webautomation.capstone.shared.FindElements;
import ecommerce.webautomation.capstone.shared.PageWaits;
import ecommerce.webautomation.capstone.utils.ConfigReader;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDescriptionPage {
    private WebDriver driver = null;
    Action action = null;
    FindElements findElements = null;
    private static ProductDescriptionPage pdp = null;
    private PageWaits pageWaits = null;

    private static final Logger LOGGER = Logger.getLogger(ProductDescriptionPage.class.getName());

    private ProductDescriptionPage(WebDriver driver) {
        this.driver = driver;
        this.pageWaits = PageWaits.getPageWaitsObject(driver);
        this.action = Action.getActionsObject(driver);
        this.findElements = FindElements.getInstance(driver);
    }

    public static ProductDescriptionPage getPDPInstance(WebDriver driver) {
        if (pdp == null) {
            ProductDescriptionPage.pdp = new ProductDescriptionPage(driver);
        }
        return ProductDescriptionPage.pdp;
    }

    public ProductDescriptionPage selectProductByName() {
        WebElement productElement = findElements.ByXPath("//a[contains(text(), '" + ConfigReader.getProductName() + "')]");
        action.scrollWindow(productElement);
        action.clickElement(productElement);
        return new ProductDescriptionPage(driver);
    }

    public boolean isProductDetailsPageLoaded() {
        pageWaits.waitForTitleToBeChanged("Alfa – ul-web-playground");
        return pageWaits.waitUntilElementFoundByXPath("//h1[contains(text(), '" + ConfigReader.getProductName() + "')]").isDisplayed();
    }

    public boolean verifyProductAvailabilityAndAddToCart() {
        WebElement buyNowButton = findElements.ByCSS("#product-form-template--15328405717213__main > div > button");
        try {
            if (!findElements.isElementDisplayed(buyNowButton)) {
                LOGGER.log(Level.INFO, "The Product is sold out..!");
                throw new ProductUnavailableException("Product not available!");
            } else {
                addItemToCart(buyNowButton);
                return true;
            }
        } catch (ProductUnavailableException e) {
            LOGGER.log(Level.SEVERE, "Error: " + e.getMessage(), e);
            return false;
        }
    }

    public void addItemToCart(WebElement element) {
        // add item to cart by clicking on Add Item to Cart button
        action.clickElement(element);
    }

    public boolean isItemAddedMessageDisplayed() {
        try {
            pageWaits.waitUntilElementFoundByCSS("#cart-notification");
            // checks for message Item added to your cart
            String query = "//h2[contains(text(),'Item added to your cart')]";
            return findElements.ByXPath(query).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            LOGGER.log(Level.WARNING, "Item not added", e);
            return false;
        }
    }

    public boolean verifyItemAddedToCart() {
        // checks for the item count
        try {
            String text = findElements.ByXPath("//a[@id='cart-notification-button']").getText();
            LOGGER.log(Level.INFO, text);
            return text.contains("(1)");
        } catch (NoSuchElementException | TimeoutException e) {
            LOGGER.log(Level.WARNING, "Error while verifying item added to cart", e);
            return false;
        }
    }
}
