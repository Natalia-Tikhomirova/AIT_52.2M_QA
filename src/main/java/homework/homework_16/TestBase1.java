package homework.homework_16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

public class TestBase1 {
    WebDriver driver;

    public final String SECOND_ITEM_LOCATOR = "(//h2[@class='product-title']//a)[2]";
    public final String CART_ITEMS_LOCATOR = ".cart-item-row";
    public final String CART_COUNT_LOCATOR = ".mini-shopping-cart .count";
    public final String ADD_TO_CART_BUTTON = "add-to-cart-button-31";


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterMethod(enabled = false) // включение или отключения закрытия браузера после тестов
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
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
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
        type(By.id("Email"), email);
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


    protected void fillInLoginForm(User user) {
        typeEmail(user.getEmail());
        typePassword(user.getPassword());
    }
}
