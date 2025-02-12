package Homework_project.core;

import Homework_project.fw.UserHelper;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BaseHelper {

    protected WebDriver driver;
    Logger logger = LoggerFactory.getLogger(BaseHelper.class);
    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoginButtonPresent() {
        return isElementPresent(By.xpath("//a[@class='ico-login']"));
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public void click(By locator) {
        //logger.info("Clicked by locator: " + locator);
        driver.findElement(locator).click();
    }

    public void clickOnLoginLink() {
        click(By.linkText("Log in"));
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }


    public String takeScreenshot() {
        // Check for alert before taking the screenshot
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Timeout for alert detection
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();  // or alert.dismiss(); based on your use case
            System.out.println("Alert was present and accepted.");
        } catch (NoAlertPresentException e) {
            // No alert, proceed with screenshot capture
        }

        // Capture screenshot
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("src/test_screenshots/screen-" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Screenshot saved to: [" + screenshot.getAbsolutePath() + "]");
        return screenshot.getAbsolutePath();
    }
}
