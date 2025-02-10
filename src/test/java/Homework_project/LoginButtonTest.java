package Homework_project;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginButtonTest extends TestBase1 {

    @Test
    public void isLoginButtonPresentTest() {
        Assert.assertTrue(app.getUserHelper().isLoginButtonPresent(), "Login button is found?: " + app.getUserHelper().isLoginButtonPresent());
    }
}