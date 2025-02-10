package homework.homework_16;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class AddToCartTest extends TestBase1 {


    @BeforeMethod
    public void precondition() {
        login("tnata12345@gmail.com", "Test@123");
    }

    @Test
    public void AddItemToCartPositiveTests() {
        // Создать переменную, которая будет хранить имя 2-го товара
        String secondItem = driver.findElement(By.xpath("(//h2[@class='product-title']//a)[2]")).getText();
        System.out.println(secondItem);
        // Выбрать 2-й товар на странице
        click(By.xpath("(//input[@value='Add to cart'])[2]"));
         // Перейти в корзину
        pause(1000);
        click(By.xpath("//span[normalize-space(text())='Shopping cart']"));
        // Обновить страницу - метод selenium
        driver.navigate().refresh();
        // Создать переменную, которая будет хранить имя товара в корзине
        String secondtItemInCard = driver.findElement(By.className("product-name")).getText();
        System.out.println(secondtItemInCard);
        // Сравнить две переменные
        Assert.assertEquals(secondItem, secondtItemInCard, "Товар не добавлен в корзину!");
    }

    public void pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (TimeoutException e) {
            System.out.println("Времени не хватило");
            throw new TimeoutException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
