package ecommerce.webautomation.capstone.pages;

import ecommerce.webautomation.capstone.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import ecommerce.webautomation.capstone.shared.Actions;

public class HomePage {
    private static HomePage homePage = null;
    private Actions actions = null;

    private HomePage(WebDriver driver) {
        this.actions = Actions.getActionsObject(driver);
    }

    public static HomePage getInstance(WebDriver driver) {
        if (homePage == null) {
            HomePage.homePage = new HomePage(driver);
        }
        return HomePage.homePage;
    }

    public void goToHomePage() {
        actions.navigateTo(ConfigReader.getBaseURL());
        actions.Maximize();
    }
}
