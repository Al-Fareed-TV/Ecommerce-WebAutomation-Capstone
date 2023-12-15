package ecommerce.webautomation.capstone.shared;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class FindElements {
    private static FindElements elements = null;
    WebDriver driver = null;
    public FindElements(WebDriver driver) {
        this.driver = driver;
    }
    public static FindElements getInstance(WebDriver driver){
        if(FindElements.elements == null){
            FindElements.elements = new FindElements(driver);
        }
        return FindElements.elements;
    }
    public WebElement ByCSS(String cssLocator){
        return driver.findElement(By.cssSelector(cssLocator));
    }
    public WebElement ByID(String elementId){
        return driver.findElement(By.id(elementId));
    }
    public WebElement ByClass(String className){
        return driver.findElement(By.className(className));
    }
    public WebElement ByXPath(String xpath){
        return driver.findElement(By.xpath(xpath));
    }
    public WebElement ByPartialLinkText(String text){
        return driver.findElement(By.partialLinkText(text));
    }
}