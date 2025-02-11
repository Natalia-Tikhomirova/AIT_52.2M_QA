package Homework_project;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase1 {

    @Test

    public void CreateAccountPositiveTest() {

        // Click on 'Login' By.linkText("Log in")
        app.getUserHelper().click(By.linkText("Log in"));

        // Click on 'Register' button
        app.getUserHelper().click(By.cssSelector("input.button-1.register-button"));

        // Select gender (Male)
        app.getUserHelper().click(By.id("gender-male"));

        // Enter First Name
        app.driver.findElement(By.id("FirstName")).sendKeys("Natalia");

        // Enter Last Name
        app.driver.findElement(By.id("LastName")).sendKeys("Tikhomirova");

        // Enter 'email'
        app.driver.findElement(By.id("Email")).click();
        app.driver.findElement(By.id("Email")).clear();
        app.driver.findElement(By.id("Email")).sendKeys("tnata12345" + System.currentTimeMillis() + "@gmail.com");

        // Enter 'Password'
        app.driver.findElement(By.cssSelector("#Password")).click();
        app.driver.findElement(By.cssSelector("#Password")).clear();
        app.driver.findElement(By.cssSelector("#Password")).sendKeys("Test@123");

        // Enter 'Confirm Password'
        app.driver.findElement(By.cssSelector("#ConfirmPassword")).click();
        app.driver.findElement(By.cssSelector("#ConfirmPassword")).clear();
        app.driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("Test@123");

        // Click on 'Register' button
        app.driver.findElement(By.cssSelector("#register-button")).click();

        // Click on 'Continue' button
        app.driver.findElement(By.cssSelector("input.button-1.register-continue-button")).click();

        app.getUserHelper().checkLogin();

        // Click 'Log out' link
        // driver.findElement(By.linkText("Log out")).click();
    }




}
