package Homework_project.fw;

import Homework_project.core.BaseHelper;
import Homework_project.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;



public class UserHelper extends BaseHelper {
    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void typePassword(String password) {
        type(By.cssSelector("#Password"), password);
    }

    public void typeEmail(String email) {
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
}
