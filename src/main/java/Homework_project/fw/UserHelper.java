package Homework_project.fw;

import Homework_project.core.BaseHelper;
import Homework_project.model.Contact;
import Homework_project.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


public class UserHelper extends BaseHelper {
    public UserHelper(WebDriver driver) {
        super(driver);
    }

    Logger logger = LoggerFactory.getLogger(UserHelper.class);

    public void typePassword(String password) {
        logger.info("Typing password: " + password);
        type(By.cssSelector("#Password"), password);
    }

    public void typeEmail(String email) {
        logger.info("Typing email: " + email);
        type(By.id("Email"), email);
    }

    public void clickOnLoginButton() {
        click(By.cssSelector("input.button-1.login-button"));
    }

    public void checkLogin() {
        Assert.assertTrue(isElementPresent(By.linkText("Log out")));
    }

    public void login(String email, String password) {
        clickOnLoginLink();
        typeEmail(email);
        typePassword(password);
        clickOnLoginButton();
    }

    public void fillInLoginForm(User user) {
        typeEmail(user.getEmail());
        typePassword(user.getPassword());
    }

    public void clickOnSignOutButton() {
        click((By.linkText("Log out")));
    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.linkText("Log out"));
    }

    public void registerNewUser(Contact contact) {
        if (contact.getGender().equalsIgnoreCase("Male")) {
            click(By.id("gender-male"));
        } else {
            click(By.id("gender-female"));
        }

        type(By.id("FirstName"), contact.getFirstName());
        type(By.id("LastName"), contact.getLastName());
        type(By.id("Email"), contact.getEmail());
        type(By.cssSelector("#Password"), contact.getPassword());
        type(By.cssSelector("#ConfirmPassword"), contact.getPassword());

        click(By.cssSelector("#register-button"));
    }

}
