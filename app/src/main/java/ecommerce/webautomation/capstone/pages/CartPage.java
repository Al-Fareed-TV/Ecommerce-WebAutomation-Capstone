package ecommerce.webautomation.capstone.pages;

import ecommerce.webautomation.capstone.shared.Action;
import ecommerce.webautomation.capstone.shared.FindElements;
import ecommerce.webautomation.capstone.shared.PageWaits;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class CartPage {
    private WebDriver driver = null;
    Action action = null;
    FindElements findElements = null;
    private static CartPage cartPage = null;
    private PageWaits pageWaits = null;

    private static final Logger LOGGER = Logger.getLogger(ProductDescriptionPage.class.getName());

    private CartPage(WebDriver driver) {
        this.driver = driver;
        this.pageWaits = PageWaits.getPageWaitsObject(driver);
        this.action = Action.getActionsObject(driver);
        this.findElements = FindElements.getInstance(driver);
    }

    public static CartPage cartPageInstance(WebDriver driver) {
        if (CartPage.cartPage == null) {
            CartPage.cartPage = new CartPage(driver);
        }
        return CartPage.cartPage;
    }

    public CartPage navigateToCartPage() {
        pageWaits.waitUntilElementFoundByCSS("#cart-icon-bubble").click();
        return new CartPage(driver);
    }

    public void navigateToCheckout(){
        pageWaits.waitUntilElementFoundByID("checkout").click();

    }

    public String nameOfItemInCart() {
        pageWaits.waitForTitleToBeChanged("Your Shopping Cart – ul-web-playground");
        return  pageWaits.waitUntilElementFoundByCSS("#CartItem-1 > td.cart-item__details > a").getText();

    }

    public String sizeOfTheItem() {
        return findElements.ByCSS("#CartItem-1 > td.cart-item__details > dl > div > dd").getText();
    }

    public int numberOfItemsInCart() {
        return Integer.parseInt(
                findElements.ByClass("quantity__input").getAttribute("value")
        );
    }

    public String getTotalAmount() {
        return findElements.ByCSS("#main-cart-footer > div > div > div > div.js-contents > div.totals > p").getText();
    }
}
