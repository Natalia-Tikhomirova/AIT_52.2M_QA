package homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class By_xpath {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void xpathTest() {

        WebElement secondImageLink = driver.findElement(By.xpath("(//a[@class='nivo-imageLink'])[2]"));
        System.out.println("Second image link: " + secondImageLink.getAttribute("href"));

        WebElement shoppingCart = driver.findElement(By.xpath("//a[@class='ico-cart']"));
        System.out.println("Shopping Cart: " + shoppingCart.getText());

        WebElement loginButton = driver.findElement(By.xpath("//a[@class='ico-login']"));
        System.out.println("Login button: " + loginButton.getText());

        WebElement searchBox = driver.findElement(By.xpath("//input[@id='small-searchterms']"));
        System.out.println("Search box placeholder: " + searchBox.getAttribute("placeholder"));

        WebElement featuredProduct = driver.findElement(By.xpath("(//div[@class='item-box'])[1]"));
        System.out.println("First featured product: " + featuredProduct.getText());

        WebElement footerNewsletter = driver.findElement(By.xpath("//input[@id='newsletter-email']"));
        System.out.println("Newsletter input placeholder: " + footerNewsletter.getAttribute("placeholder"));

        WebElement addToCartButton = driver.findElement(By.xpath("(//input[@value='Add to cart'])[1]"));
        System.out.println("First 'Add to cart' button: " + addToCartButton.getAttribute("value"));

        WebElement categoryBooks = driver.findElement(By.xpath("//a[contains(text(),'Books')]"));
        System.out.println("Books category link: " + categoryBooks.getText());

        WebElement wishlistLink = driver.findElement(By.xpath("//a[@class='ico-wishlist']"));
        System.out.println("Wishlist link text: " + wishlistLink.getText());

    }

    @AfterMethod(enabled = true) // включение или отключения закрытия браузера после тестов
    public void tearDown() {
        driver.quit();
    }
}
