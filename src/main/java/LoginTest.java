import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTest {

    @Test
    public void testLogin() throws InterruptedException {
        // Инициализация WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Thread.sleep(2000);
        driver.get("https://grow-bank.io");

        // Создаем явное ожидание для поиска элемента username
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        // Вводим логин и пароль
        usernameField.sendKeys("testuser");
        driver.findElement(By.name("password")).sendKeys("Test@1234");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Проверка на успешный вход
        assert driver.getCurrentUrl().contains("dashboard");

        driver.quit();
    }
}