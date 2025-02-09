package homework.homework_16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddItemToCartTests extends TestBase1 {

    @BeforeMethod
    public void precondition() {
        login(VALID_USER_EMAIL, VALID_USER_PASSWORD);
    }

    @Test
    public void AddItemToCartPositiveTests() {
        int cartBefore = getCartCount();
        System.out.println("Количество товаров ДО теста: " + cartBefore);

        // Найти 2-й элемент (его название) и сохранить его текст
        WebElement secondItem = driver.findElement(By.xpath(SECOND_ITEM_LOCATOR));
        String expectedItemName = secondItem.getText();
        System.out.println("Добавляем товар: " + expectedItemName);

        // Клик на второй товар
        secondItem.click();

        // Ожидание загрузки страницы товара
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(ADD_TO_CART_BUTTON)));

        // Клик по кнопке "Add to cart"
        System.out.println("Кликаем по кнопке 'Add to cart'");
        driver.findElement(By.id(ADD_TO_CART_BUTTON)).click();
        System.out.println("Клик выполнен, ждем обновления корзины...");

        //  Ожидание, пока текст в корзине обновится
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> {
            String cartText = driver.findElement(By.cssSelector(CART_COUNT_LOCATOR)).getText().trim();
            System.out.println("Текущий текст корзины после добавления товара: " + cartText);
            return !cartText.isEmpty() && extractCartCount(cartText) > cartBefore;
        });

        //  Кликаем по кнопке "Shopping cart"
        System.out.println("Переходим в корзину");
        click(By.xpath("//a[@class='ico-cart']"));

        // Ожидаем, пока загрузится страница корзины
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CART_ITEMS_LOCATOR)));

        int cartAfter = getCartCount();
        System.out.println("Количество товаров ПОСЛЕ теста: " + cartAfter);

        Assert.assertTrue(cartAfter > cartBefore, "Товар не добавился в корзину!");

        // Проверка, что добавленный товар появился в корзине
        Assert.assertTrue(isItemAddedToCart(expectedItemName),
                "Ожидалось, что товар '" + expectedItemName + "' будет в корзине, но его нет!");
    }
}