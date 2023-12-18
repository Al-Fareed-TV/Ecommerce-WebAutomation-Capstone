package ecommerce.webautomation.capstone.pages;

import ecommerce.webautomation.capstone.shared.Actions;
import ecommerce.webautomation.capstone.shared.FindElements;
import ecommerce.webautomation.capstone.shared.PageWaits;
import ecommerce.webautomation.capstone.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ProductDescriptionPage {
    private WebDriver driver = null;
    Actions actions = null;
    FindElements findElements = null;
    private static ProductDescriptionPage pdp = null;
    private PageWaits waits = null;

    private ProductDescriptionPage(WebDriver driver) {
        this.driver = driver;
        this.waits = PageWaits.getPageWaitsObject(driver);
        this.actions = Actions.getActionsObject(driver);
        this.findElements = FindElements.getInstance(driver);
    }

    public static ProductDescriptionPage getPDPInstance(WebDriver driver) {
        if (pdp == null) {
            ProductDescriptionPage.pdp = new ProductDescriptionPage(driver);
        }
        return ProductDescriptionPage.pdp;
    }

    public ProductDescriptionPage selectProductByName() {
//        By productName = By.xpath("//a[contains(text(), '" + ConfigReader.getProductName() + "')]");
        WebElement productElement = findElements.ByXPath("//a[contains(text(), '" + ConfigReader.getProductName() + "')]");
        actions.scrollWindow(productElement);
        actions.clickElement(productElement);
        return new ProductDescriptionPage(driver);
    }
    public boolean isProductDetailsPageLoaded(){
        waits.waitForTitleToBeChanged("Alfa – ul-web-playground");
        return waits.waitUntilElementFoundByXPath("//h1[contains(text(), '" + ConfigReader.getProductName() + "')]").isDisplayed();
    }
}
