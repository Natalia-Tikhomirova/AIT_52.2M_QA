package homework.homework_15;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase1 {

    @BeforeMethod
    public void precondition() {
        login("tnata12345@gmail.com", "Test@123");
    }

    @Test
    public void addContactPositiveTest() {

    }
}
