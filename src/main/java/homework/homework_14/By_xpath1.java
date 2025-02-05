package homework.homework_14;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class By_xpath1  extends TestBaseXpath{

    @Test
    public void xpathTest() {

        Assert.assertTrue(isElementPresent("(//a[@class='nivo-imageLink'])[2]"), "Second image link is found?: " + isElementPresent("(//a[@class='nivo-imageLink'])[2]"));
        Assert.assertTrue(isElementPresent("//a[@class='ico-cart']"), "Shopping cart is found?: " + isElementPresent("//a[@class='ico-cart']"));
        Assert.assertTrue(isElementPresent("//a[@class='ico-login']"), "Login button is found?: " + isElementPresent("//a[@class='ico-login']"));
        Assert.assertTrue(isElementPresent("//input[@id='small-searchterms']"), "Search box is found?: " + isElementPresent("//input[@id='small-searchterms']"));
        Assert.assertTrue(isElementPresent("(//div[@class='item-box'])[1]"), "First featured product is found?: " + isElementPresent("(//div[@class='item-box'])[1]"));
        Assert.assertTrue(isElementPresent("//input[@id='newsletter-email']"), "Newsletter input is found?: " + isElementPresent("//input[@id='newsletter-email']"));
        Assert.assertTrue(isElementPresent("(//input[@value='Add to cart'])[1]"), "First 'Add to cart' button is found?: " + isElementPresent("(//input[@value='Add to cart'])[1]"));
        Assert.assertTrue(isElementPresent("//a[contains(text(),'Books')]"), "Books category link is found?: " + isElementPresent("//a[contains(text(),'Books')]"));
        Assert.assertTrue(isElementPresent("//a[@class='ico-wishlist']"), "Wishlist link is found?: " + isElementPresent("//a[@class='ico-wishlist']"));
    }

    private boolean isElementPresent(String xpath) {
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }
}