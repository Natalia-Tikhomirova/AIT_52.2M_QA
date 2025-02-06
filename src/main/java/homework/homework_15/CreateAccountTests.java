package homework.homework_15;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase1 {

    @Test

    public void CreateAccountPositiveTest() {

        // Click on 'Login' By.linkText("Log in")
        driver.findElement(By.linkText("Log in")).click();

        // Click on 'Register' button
        driver.findElement(By.cssSelector("input.button-1.register-button")).click();

        // Select gender (Male)
        driver.findElement(By.id("gender-male")).click();

        // Enter First Name
        driver.findElement(By.id("FirstName")).sendKeys("Natalia");

        // Enter Last Name
        driver.findElement(By.id("LastName")).sendKeys("Tikhomirova");

        // Enter 'email' By.cssSelector(#Email)
        driver.findElement(By.cssSelector("#Email")).click();
        driver.findElement(By.cssSelector("#Email")).clear();
        driver.findElement(By.cssSelector("#Email")).sendKeys("tnata12345@gmail.com");

        // Enter 'Password' By.cssSelector("#Password")
        driver.findElement(By.cssSelector("#Password")).click();
        driver.findElement(By.cssSelector("#Password")).clear();
        driver.findElement(By.cssSelector("#Password")).sendKeys("Test@123");

        // Enter Password By.cssSelector("#ConfirmPassword")
        driver.findElement(By.cssSelector("#ConfirmPassword")).click();
        driver.findElement(By.cssSelector("#ConfirmPassword")).clear();
        driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("Test@123");

        // Click on 'Register' button By.cssSelector("#register-button")
        driver.findElement(By.cssSelector("#register-button")).click();

        // Click on 'Continue' button By.cssSelector("input.button-1.register-continue-button")
        driver.findElement(By.cssSelector("input.button-1.register-continue-button")).click();

        Assert.assertTrue(isElementPresent(By.linkText("Log out")));

        // Click 'Log out' link
        // driver.findElement(By.linkText("Log out")).click();
    }
}
