package ecommerce.webautomation.capstone.pages;

import ecommerce.webautomation.capstone.shared.Actions;
import ecommerce.webautomation.capstone.shared.FindElements;
import ecommerce.webautomation.capstone.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDescriptionPage {
    private WebDriver driver = null;
    Actions actions = null;
    FindElements findElements = null;

    public ProductDescriptionPage(WebDriver driver) {
        this.driver = driver;
        this.actions = Actions.getActionsObject();
        this.findElements = FindElements.getInstance(driver);
    }
    public void selectProduct(){
        WebElement productElement = findElements.ByPartialLinkText("Alfa");
        actions.scrollWindow(productElement);
        actions.clickElement(productElement);
    }
}
