package ecommerce.webautomation.capstone.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class ConfigReader {
    private static final String PROPERTIES_FILE_PATH = "app/src/main/resources/.properties";
    private Properties properties;
    public ConfigReader() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream input = getClass().getResourceAsStream(PROPERTIES_FILE_PATH)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + PROPERTIES_FILE_PATH);
                return;
            }
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public String getBaseURL() {
        return properties.getProperty("baseURL");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
    public String getBrowser(){
        return  properties.getProperty("browser");
    }
}