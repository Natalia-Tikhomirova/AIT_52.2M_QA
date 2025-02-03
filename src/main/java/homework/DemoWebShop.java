package homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DemoWebShop {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void findElementBySelector() {

        WebElement error = driver.findElement(By.cssSelector("div[title^='Er']"));
        System.out.println("Элемент, начинающийся на 'Er': " + error.getAttribute("title"));

        WebElement dialog = driver.findElement(By.cssSelector("#dialog-notifications-success"));
        System.out.println("Текст диалогового окна уведомления об успешном выполнении: " + dialog.getText());

        WebElement triangle = driver.findElement(By.cssSelector(".top-menu-triangle"));
        System.out.println("Top Menu Triangle Class: " + triangle.getAttribute("class"));

        WebElement mobile = driver.findElement(By.cssSelector("[class='mob-top-menu']"));
        System.out.println("Mobile Top Menu Class: " + mobile.getAttribute("class"));

        WebElement searchBox = driver.findElement(By.cssSelector("input#small-searchterms"));
        System.out.println("Search Box Placeholder:   " + searchBox.getAttribute("placeholder"));

        WebElement searchButton = driver.findElement(By.cssSelector("input.button-1.search-box-button"));
        System.out.println("Search Button Value: " + searchButton.getAttribute("value"));

        WebElement searchButton1 = driver.findElement(By.cssSelector("input[type='submit'][value='Search']"));
        System.out.println("Search Button Value: " + searchButton1.getAttribute("value"));

        WebElement jewelry = driver.findElement(By.cssSelector("a[href='/jewelry']"));
        System.out.println("Аттрибут <href>: " + jewelry.getAttribute("href"));

        WebElement headerLinks = driver.findElement(By.cssSelector("div.header-links"));
        System.out.println("Header Links Text: " + headerLinks.getText());

        WebElement books = driver.findElement(By.cssSelector("ul.top-menu > li:nth-child(1)"));
        System.out.println("Books: " + books.getText());
    }

    @AfterMethod()
    public void tearDown() {
        driver.quit();
    }
}
