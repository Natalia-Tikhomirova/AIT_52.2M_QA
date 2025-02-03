import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class GoogleSearchTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Указываем путь к драйверу (если нужно)
        //WebDriverManager.chromedriver().setup(); // Автоматически скачает драйвер
        driver = new ChromeDriver();
    }

    @Test
    public void testGoogleSearch() {
        // Открываем Google
        driver.get("https://www.google.com");

        // Находим поле поиска и вводим запрос
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.submit();

        // Проверяем, что результаты загрузились
        boolean resultsLoaded = driver.findElements(By.cssSelector("h3")).size() > 0;
        assertTrue("Результаты поиска не найдены!", resultsLoaded);
    }

    @AfterMethod
    public void tearDown() {
        // Закрываем браузер
        if (driver != null) {
            driver.quit();
        }
    }
}
