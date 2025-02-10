package Homework_project;

import Homework_project.model.User;
import org.testng.annotations.Test;

public class LoginTests extends TestBase1 {

    @Test(invocationCount = 1)
    public void loginExistedUserPositiveTest() {
        app.getUserHelper().login("tnata12345@gmail.com", "Test@123");
        app.getUserHelper().checkLogin();
    }

    @Test()
    public void loginWOEmaiTest() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().fillInLoginForm(new User()
                .setEmail("tnata12345@gmail.com")
                .setPassword("Test@123"));
        app.getUserHelper().clickOnLoginButton();
    }
}
