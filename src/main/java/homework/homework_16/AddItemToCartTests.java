package homework.homework_16;

import org.openqa.selenium.By;
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

        // Найти 2-й элемент и кликнуть на него
        click(By.xpath(SECOND_ITEM_LOCATOR));

        // Нажать кнопку "Add to cart"
        click(By.xpath(ADD_TO_CART_BUTTON));

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.numberOfElementsToBe(By.id(CART_LOCATOR), cartBefore + 1));

        int cartAfter = getCartCount();
        System.out.println("Количество товаров ПОСЛЕ теста: " + cartAfter);
        Assert.assertTrue(isItemAddedToCart(CART_LOCATOR),"Товар не был добавлен в корзину!");
        Assert.assertEquals(cartAfter, cartBefore + 1, "Количество товаров в корзине не изменилось!");
    }

}
