package Homework_project;

import Homework_project.data.ContactData;
import Homework_project.data.UserData;
import Homework_project.model.Contact;
import Homework_project.utils.DataProviders;
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
        app.driver.findElement(By.id("FirstName")).sendKeys(ContactData.FIRST_NAME);

        // Enter Last Name
        app.driver.findElement(By.id("LastName")).sendKeys(ContactData.LAST_NAME);

        // Enter 'email'
        app.driver.findElement(By.id("Email")).click();
        app.driver.findElement(By.id("Email")).clear();
        app.driver.findElement(By.id("Email")).sendKeys(UserData.RANDOM_EMAIL);

        // Enter 'Password'
        app.driver.findElement(By.cssSelector("#Password")).click();
        app.driver.findElement(By.cssSelector("#Password")).clear();
        app.driver.findElement(By.cssSelector("#Password")).sendKeys(ContactData.PASSWORD);

        // Enter 'Confirm Password'
        app.driver.findElement(By.cssSelector("#ConfirmPassword")).click();
        app.driver.findElement(By.cssSelector("#ConfirmPassword")).clear();
        app.driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys(ContactData.CONFIRM_PASSWORD);

        // Click on 'Register' button
        app.driver.findElement(By.cssSelector("#register-button")).click();

        // Click on 'Continue' button
        app.driver.findElement(By.cssSelector("input.button-1.register-continue-button")).click();

        app.getUserHelper().checkLogin();

        // Click 'Log out' link
        // driver.findElement(By.linkText("Log out")).click();
    }


    @Test
    public void CreateAccountNewPositiveTest() {
        createAccountPositiveTest("male", "Natalia", "Tikhomirova", "tnata12345" + System.currentTimeMillis() + "@gmail.com", "Test@123", "Test@123");
    }

    @Test(dataProvider = "registrationDataProvider", dataProviderClass = DataProviders.class)
    public void createAccountPositiveTest(String gender, String firstName, String lastName, String email, String password, String confirmPassword) {
        app.getUserHelper().click(By.linkText("Log in"));
        app.getUserHelper().click(By.cssSelector("input.button-1.register-button"));

        // Заполняем форму регистрации
        app.getContactHelper().registerNewUser(new Contact()
                .setGender(gender)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setConfirmPassword(confirmPassword)
        );

        // Нажимаем кнопку продолжения после регистрации
        app.getUserHelper().click(By.id("register-button"));
    }

    @Test(dataProvider = "registrationDataProviderFromCsv", dataProviderClass = DataProviders.class)
    public void createAccountDataProviderFromCSVPositiveTest(Contact contact){
        app.getUserHelper().click(By.linkText("Log in"));
        app.getUserHelper().click(By.cssSelector("input.button-1.register-button"));

        // Заполняем форму регистрации
        app.getContactHelper().registerNewUser(contact);

        // Нажимаем кнопку продолжения после регистрации
        app.getUserHelper().click(By.id("register-button"));

    }
}
