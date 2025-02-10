package Homework_project.core;

import Homework_project.fw.UserHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BaseHelper {
    public final String SECOND_ITEM_LOCATOR = "(//h2[@class='product-title']//a)[2]";
    public final String CART_ITEMS_LOCATOR = ".cart-item-row";
    public final String CART_COUNT_LOCATOR = ".mini-shopping-cart .count";
    public final String ADD_TO_CART_BUTTON = "add-to-cart-button-31";

    protected WebDriver driver;

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
        driver.findElement(locator).click();
    }

    public void clickOnLoginLink() {
        click(By.linkText("Log in"));
    }

    // Метод для получения количества товаров в корзине
    public int getCartCount() {
        WebElement cartElement = driver.findElement(By.cssSelector(CART_COUNT_LOCATOR));
        String cartText = cartElement.getText().trim();
        System.out.println("Текущий текст корзины: " + cartText);
        return extractCartCount(cartText);
    }

    public int extractCartCount(String text) {
        try {
            return Integer.parseInt(text.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Метод для проверки наличия товара в корзине по названию
    protected boolean isItemAddedToCart(String textToFind) {
        List<WebElement> cartItems = driver.findElements(By.cssSelector(CART_ITEMS_LOCATOR));
        for (WebElement item : cartItems) {
            if (item.getText().contains(textToFind)) {
                return true;
            }
        }
        return false;
    }

}
