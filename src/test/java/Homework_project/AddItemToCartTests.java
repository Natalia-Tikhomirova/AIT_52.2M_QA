package Homework_project;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Homework_project.fw.CartHelper.*;

public class AddItemToCartTests extends TestBase1 {

    @BeforeMethod
    public void precondition() {
        app.getUserHelper().login("tnata12345@gmail.com", "Test@123");
    }

    @Test
    public void AddItemToCartPositiveTests() {
        // Создать переменную, которая будет хранить имя 2-го товара
        String secondItem = app.getHomePageHelper().getText(By.xpath(SECOND_ITEM_LOCATOR));
        System.out.println("Добавляем товар: " + secondItem);
        // Выбрать 2-й товар
        app.getUserHelper().click(By.xpath(ADD_TO_CART_BUTTON));
        // Перейти в корзину
        app.getCartHelper().pause(1000);
        app.getCartHelper().goToCart();

        // Обновить страницу через метод в CartHelper
        app.getCartHelper().refreshPage();

        /// Создать переменную, которая будет хранить имя товара в корзине
        String secondItemInCart = app.getCartHelper().getText(By.className(PRODUCT_NAME_CLASS));
        System.out.println("Товар в корзине: " + secondItemInCart);
        // Сравнить две переменные
        Assert.assertEquals(secondItem, secondItemInCart, "Товар не добавлен в корзину!");
    }
}