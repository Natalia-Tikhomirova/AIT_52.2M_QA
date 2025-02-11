package Homework_project.fw;

import Homework_project.core.BaseHelper;
import org.openqa.selenium.*;

public class CartHelper extends BaseHelper {

    public static final String SECOND_ITEM_LOCATOR = "(//h2[@class='product-title']//a)[2]";
    public static final String ADD_TO_CART_BUTTON = "(//input[@value='Add to cart'])[2]";
    public static final String SHOPPING_CART_LOCATOR = "//span[normalize-space(text())='Shopping cart']";
    public static final String PRODUCT_NAME_CLASS = "product-name";

    public CartHelper(WebDriver driver) {
        super(driver);
    }

    public void goToCart() {

        // Закрываем уведомление, если оно есть
        try {
            driver.findElement(By.className("close")).click();  // Если есть кнопка закрытия
        } catch (NoSuchElementException ignored) {
            // Если кнопки закрытия нет, игнорируем
        }
        // Прокручиваем страницу до кнопки корзины
        WebElement cartButton = driver.findElement(By.xpath(SHOPPING_CART_LOCATOR));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartButton);

        // Кликаем по кнопке корзины
        cartButton.click();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Ошибка ожидания");
            Thread.currentThread().interrupt();
        }
    }
}
