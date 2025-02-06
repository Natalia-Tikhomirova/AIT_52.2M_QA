package homework.homework_15;

import org.testng.annotations.Test;

public class LoginTests extends TestBase1 {

    @Test
    public void loginExistedUserPositiveTest() {
        clickOnLoginLink();
        typeEmail("tnata12345@gmail.com");
        typePassword("Test@123");
        clickOnLoginButton();
        checkLogin();
    }
}
