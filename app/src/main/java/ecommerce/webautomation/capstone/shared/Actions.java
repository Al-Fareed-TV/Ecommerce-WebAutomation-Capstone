package ecommerce.webautomation.capstone.shared;
import ecommerce.webautomation.capstone.utils.ConfigReader;
import ecommerce.webautomation.capstone.utils.DriverCreator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Actions {
    private static Actions actions = null;
    WebDriver driver = null;

    private Actions() {
        this.driver = DriverCreator.instantiateDriver(ConfigReader.getBrowser());
    }

    public static Actions getActionsObject() {
        if (Actions.actions == null) {
            Actions.actions = new Actions();
        }
        return Actions.actions;
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

    public void Maximize(WebDriver driver) {
        driver.manage().window().fullscreen();
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void scrollWindow(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
    }

}