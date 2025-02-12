package Homework_project;

import Homework_project.model.User;
import Homework_project.utils.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends TestBase1 {


    @BeforeMethod
    public void preCondition() {
        if (app.getUserHelper().isSignOutButtonPresent()) {
            logger.info("User already logged in. Sign out...");
            app.getUserHelper().clickOnSignOutButton();
        } else {
            logger.info("LOGIN link is present. No need to Sign Out");
        }
    }

    @Test(invocationCount = 1)
    public void loginExistedUserPositiveTest() {
        app.getUserHelper().login("tnata12345@gmail.com", "Test@123");
        app.getUserHelper().checkLogin();
    }

    @Test()
    public void loginWOEmaiNegativeTest() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().fillInLoginForm(new User()
                //.setEmail("tnata12345@gmail.com")
                .setPassword("Test@123"));
        app.getUserHelper().clickOnLoginButton();
        System.out.println("Login was unsuccessful. Please correct the errors and try again. No customer account found.");
    }

    //  "tnata12345@gmail.com"
    //  "Test@123"


    @Test(dataProvider = "loginDataProvider",dataProviderClass = DataProviders.class)
    public void loginExistedUserDataProviderPositiveTest(String email,String password) {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail(email);
        app.getUserHelper().typePassword(password);
        app.getUserHelper().clickOnLoginButton();
        app.getUserHelper().checkLogin();
    }

    @AfterMethod
    public void postCondition(){
        // app.getUserHelper().clickOnSignOutButton();

    }
}
