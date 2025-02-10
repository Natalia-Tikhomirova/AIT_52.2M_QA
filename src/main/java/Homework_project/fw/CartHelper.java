package Homework_project.fw;

import Homework_project.core.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class CartHelper extends BaseHelper {

    public static final String SECOND_ITEM_LOCATOR = "(//h2[@class='product-title']//a)[2]";
    public static final String ADD_TO_CART_BUTTON = "(//input[@value='Add to cart'])[2]";
    public static final String SHOPPING_CART_LOCATOR = "//span[normalize-space(text())='Shopping cart']";
    public static final String PRODUCT_NAME_CLASS = "product-name";

    public CartHelper(WebDriver driver) {
        super(driver);
    }

    public void goToCart() {
        click(By.xpath(SHOPPING_CART_LOCATOR));
    }

    public void refreshPage() {
        driver.navigate().refresh();
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
