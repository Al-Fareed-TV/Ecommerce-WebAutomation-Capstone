package ecommerce.webautomation.capstone.pages;

import ecommerce.webautomation.capstone.utils.ConfigReader;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver = null;
    private HomePage(WebDriver driver){
        this.driver = driver;
    }
}
