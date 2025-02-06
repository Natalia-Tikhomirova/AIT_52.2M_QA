package homework.homework_15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase1 {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterMethod(enabled = true) // включение или отключения закрытия браузера после тестов
    public void tearDown() {
        driver.quit();
    }


    public boolean isLoginButtonPresent() {
        return isElementPresent(By.xpath("//a[@class='ico-login']"));
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void clickOnLoginLink() {
        click(By.linkText("Log in"));
    }

    public void typePassword(String password) {
        type(By.cssSelector("#Password"), password);
    }

    public void typeEmail(String email) {
        type(By.cssSelector("#Email"), email);
    }

    public void clickOnLoginButton() {
        click(By.cssSelector("input.button-1.login-button"));
    }

    public void checkLogin() {
        Assert.assertTrue(isElementPresent(By.linkText("Log out")));
    }

    public void login(String email, String password) {
        clickOnLoginLink();
        typeEmail(email);
        typePassword(password);
        clickOnLoginButton();
    }
}
