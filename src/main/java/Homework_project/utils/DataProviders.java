package Homework_project.utils;

import Homework_project.model.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider
    public static Object[][] loginDataProvider() {
        return new Object[][]{
                {"tnata12345@gmail.com","Test@123"},
                {"tnata123456@gmail.com","Test@123"},
                {"tnata@gmail.com","Test@123"}
        };
    }

    @DataProvider
    public Object[][] registrationDataProvider() {
        return new Object[][]{
                {"Male", "Ivan", "Ivanov", "ivan@test.com", "Test@123","Test@123"},
                {"Female", "Anna", "Petrova", "anna@test.com", "Test@123","Test@123"},
                {"Male", "Maxim", "Ivanov", "maxim@test.com", "Test@123","Test@123"}
        };
    }

    @DataProvider
    public Iterator<Object[]> registrationDataProviderFromCsv() throws IOException {
        // Создаем список для хранения данных для тестов
        List<Object[]> list = new ArrayList<>();

        // Открываем CSV файл для чтения
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts1.csv"));
        // Читаем первую строку из файла
        String line = reader.readLine();
        // Обрабатываем каждую строку файла до конца
        while (line != null) {
            // Разделяем строку на элементы по запятой
            String[] split = line.split(",");
            // Создаем объект Contact и устанавливаем его поля из прочитанных данных
            list.add(new Object[]{new Contact()
                    .setGender(split[0])
                    .setFirstName(split[1])
                    .setLastName(split[2])
                    .setEmail(split[3])
                    .setPassword(split[4])
                    .setConfirmPassword(split[5])
            });
            // Читаем следующую строку из файла
            line = reader.readLine();
        }
        // Закрываем файл после чтения всех данных
        reader.close();
        // Возвращаем итератор для списка объектов
        return list.iterator();
    }
}
