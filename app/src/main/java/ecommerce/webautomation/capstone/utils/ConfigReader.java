package ecommerce.webautomation.capstone.utils;


public class ConfigReader {
    private static final String baseURL = "https://web-playground.ultralesson.com/";
    private static final String browser = "chrome";
    private static final String productName = "Alfa";

    public static String getBaseURL() {
        return baseURL;
    }

    public static String getBrowser() {
        return browser;
    }
    public  static String getProductName(){
        return productName;
    }

}