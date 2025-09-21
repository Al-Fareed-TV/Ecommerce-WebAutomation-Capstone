package ecommerce.webautomation.capstone;

import ecommerce.webautomation.capstone.utils.ConfigReader;
import ecommerce.webautomation.capstone.utils.DriverCreator;
import ecommerce.webautomation.capstone.utils.TestLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {

    public void onTestStart(ITestResult result) {
        TestLogger.info("*****Test started :- " + result.getName() + "*****");
    }

    public void onTestSuccess(ITestResult result) {
        TestLogger.info("*****Test successful :- " + result.getName() + "*****");
    }

    public void onTestFailure(ITestResult result) {
        TestLogger.error("*****Test Failed :- " + result.getName() + "*****");
        WebDriver driver = DriverCreator.instantiateDriver(ConfigReader.getBrowser());
        try {
            captureScreenshotOnFail(driver, result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        if ((driver != null)) {
//            Allure.addAttachment(result.getName() + "web", "image/png", captureScreenshot(driver).toString());
//
//        }
        TestLogger.info("Screenshot taken");
    }

    public void onTestSkipped(ITestResult result) {
        TestLogger.error("*****Test Skipped :- " + result.getName() + "*****");
    }


    private void captureScreenshotOnFail(WebDriver driver,String methodName) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("/Users/testvagrant/Baganna/Ecommerce-WebAutomation-Capstone/app/src/test/screenshots/"+driver.getTitle()+".jpg"));
    }

    @Attachment(value = "Failure Screenshot2", type = "png")
    private byte[] captureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
