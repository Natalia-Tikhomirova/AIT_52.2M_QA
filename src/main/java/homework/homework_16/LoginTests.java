package homework.homework_16;

import org.testng.annotations.Test;

public class LoginTests extends TestBase1 {

    @Test(invocationCount = 1)
    public void loginExistedUserPositiveTest() {
        clickOnLoginLink();
        typeEmail("tnata12345@gmail.com");
        typePassword("Test@123");
        clickOnLoginButton();
        checkLogin();
    }

    @Test()
    public void loginWOEmaiTest() {
        clickOnLoginLink();
        fillInLoginForm(new User()
                //.setEmail("tnata12345@gmail.com")
                .setPassword("Test@123"));
        clickOnLoginButton();


    }

}
