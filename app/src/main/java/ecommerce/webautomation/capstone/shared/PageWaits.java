package ecommerce.webautomation.capstone.shared;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.List;

public class PageWaits {
    private static PageWaits pageWaits = null;
    private WebDriver driver = null;

    private PageWaits(WebDriver driver) {
        this.driver = driver;
    }
    public static PageWaits getPageWaitsObject(WebDriver driver){
        if(PageWaits.pageWaits == null){
            PageWaits.pageWaits = new PageWaits(driver);
        }
        return PageWaits.pageWaits;
    }

    private FluentWait<WebDriver> waitForElement(){
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
    }
    public WebElement waitUntilElementFoundByPartialLink(String partialLink){
        return waitForElement().until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(partialLink)));
    }
    public WebElement waitUntilElementFoundByID(String elementID){
        return waitForElement().until(ExpectedConditions.visibilityOfElementLocated(By.id(elementID)));
    }
    public WebElement waitUntilElementFoundByCSS(String cssLocator){
        return waitForElement().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssLocator)));
    }
    public List<WebElement> waitUntilListOfElementFoundByCss(String cssLocator){
        return waitForElement().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(cssLocator)));
    }
    public WebElement waitUntilElementFoundByXPath(String xpath){
        return waitForElement().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void waitForTitleToBeChanged(String titleName){
         waitForElement().until(ExpectedConditions.titleIs(titleName));
    }
}