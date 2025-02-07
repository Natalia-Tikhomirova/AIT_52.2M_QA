package homework.homework_16;

import org.testng.annotations.Test;

public class LoginTests extends TestBase1 {

    @Test(invocationCount = 1)
    public void loginExistedUserPositiveTest() {
        clickOnLoginLink();
        typeEmail(VALID_USER_EMAIL);
        typePassword(VALID_USER_PASSWORD);
        clickOnLoginButton();
        checkLogin();
    }
}
