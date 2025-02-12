package Homework_project.fw;

import Homework_project.core.BaseHelper;
import Homework_project.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ContactHelper extends BaseHelper {
    public ContactHelper(WebDriver driver) {
        super(driver);
    }
    public void registerNewUser(Contact contact) {
        click(By.id("gender-" + contact.getGender().toLowerCase())); // Выбираем пол

        type(By.id("FirstName"), contact.getFirstName());
        type(By.id("LastName"), contact.getLastName());
        type(By.id("Email"), contact.getEmail());
        type(By.id("Password"), contact.getPassword());
        type(By.id("ConfirmPassword"), contact.getPassword()); // Подтверждаем пароль

        click(By.id("register-button")); // Кнопка "Register"
    }
}
