package ecommerce.webautomation.capstone.pages;

import ecommerce.webautomation.capstone.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import ecommerce.webautomation.capstone.shared.Action;

public class HomePage {
    private static HomePage homePage = null;
    private Action action = null;

    private HomePage(WebDriver driver) {
        this.action = Action.getActionsObject(driver);
    }

    public static HomePage getInstance(WebDriver driver) {
        if (homePage == null) {
            HomePage.homePage = new HomePage(driver);
        }
        return HomePage.homePage;
    }

    public void goToHomePage() {
        action.navigateTo(ConfigReader.getBaseURL());
        action.Maximize();
    }
}
