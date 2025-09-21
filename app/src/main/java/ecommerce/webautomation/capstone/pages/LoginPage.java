package ecommerce.webautomation.capstone.pages;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoginPage {
    private static LoginPage loginPageInstance;
    private WebDriver driver = null;

    private LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public static synchronized LoginPage getInstance(WebDriver driver) {
        if (loginPageInstance == null) {
            loginPageInstance = new LoginPage(driver);
        }
        return loginPageInstance;
    }

    public void loginWithCookies() {
        Set<Cookie> cookies = getCookies();

        if (cookies != null) {
            cookies.forEach(driver.manage()::addCookie);
        }

        System.out.println("Got the cookies : "+cookies);

        driver.navigate().refresh();
        System.out.println("Added cookies successfully");
    }

    private Set<Cookie> getCookies() {
        Set<Cookie> cookies = new HashSet<>();

        try (InputStream inputStream = getClass().getResourceAsStream("/cookies.csv")) {
            if (inputStream != null) {
                try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
                    List<String[]> csvData = reader.readAll();

                    for (String[] cookieData : csvData) {
                        cookies.add(createCookie(cookieData));
                    }
                } catch (CsvException e) {
                    System.err.println("Error reading CSV: " + e.getMessage());
                }
            } else {
                System.err.println("CSV file not found");
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return cookies;
    }

    private Cookie createCookie(String[] cookieData) {
        if (cookieData.length >= 2) {
            return new Cookie.Builder(cookieData[0], cookieData[1])
                    .domain(cookieData.length >= 3 ? cookieData[2] : null)
                    .path(cookieData.length >= 4 ? cookieData[3] : null)
                    .build();
        } else {
            System.err.println("Invalid cookie data: " + String.join(",", cookieData));
            return null;
        }
    }

}
