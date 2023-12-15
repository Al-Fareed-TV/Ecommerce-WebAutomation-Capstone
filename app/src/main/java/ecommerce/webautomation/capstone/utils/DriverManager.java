package ecommerce.webautomation.capstone.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    private static WebDriver driver = null;

    public DriverManager() {
    }

    private DriverManager(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "safari" -> {
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
            }
            default -> {
                System.out.println("Invalid browser..!Instantiating chrome driver.");
                new DriverManager("chrome");
            }
        }

    }

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            new DriverManager(browser);
        }
        return driver;
    }


}
