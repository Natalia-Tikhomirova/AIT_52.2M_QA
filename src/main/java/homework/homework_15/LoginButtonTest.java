package homework.homework_15;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginButtonTest extends TestBase1 {

    @Test
    public void isLoginButtonPresentTest() {
        Assert.assertTrue(isLoginButtonPresent(), "Login button is found?: " + isLoginButtonPresent());
    }
}