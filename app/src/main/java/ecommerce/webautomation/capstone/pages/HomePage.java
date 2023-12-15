package ecommerce.webautomation.capstone.pages;

import ecommerce.webautomation.capstone.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import ecommerce.webautomation.capstone.shared.Actions;

public class HomePage {
    private WebDriver driver = null;
    Actions actions = null;
    private HomePage(WebDriver driver){
        this.driver = driver;
        this.actions = Actions.getActionsObject();
    }
    public void goToHomePage(){
        actions.navigateTo(ConfigReader.getBaseURL());

    }
}
