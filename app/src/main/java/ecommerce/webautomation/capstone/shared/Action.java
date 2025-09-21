package ecommerce.webautomation.capstone.shared;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Action {
    private static Action action = null;
    private WebDriver driver = null;

    private Action(WebDriver driver) {
        this.driver = driver;
    }

    public static Action getActionsObject(WebDriver driver) {
        if (Action.action == null) {
            Action.action = new Action(driver);
        }
        return Action.action;
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String values) {
        element.clear();
        element.sendKeys(values);
    }
    public void enterKey(WebElement element){
        element.sendKeys(Keys.ENTER);
    }

    public void Maximize() {
        driver.manage().window().maximize();
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void scrollWindow(WebElement element) {
        Actions actions1 = new Actions(driver);
        actions1.scrollToElement(element);
    }

}