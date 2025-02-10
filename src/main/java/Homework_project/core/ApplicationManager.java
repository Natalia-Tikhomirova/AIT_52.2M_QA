package Homework_project.core;

import Homework_project.fw.HomePageHelper;
import Homework_project.fw.UserHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager {
    public WebDriver driver;

    UserHelper userHelper;
    HomePageHelper homePageHelper;

    public void init() {
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        userHelper = new UserHelper(driver);
        homePageHelper = new HomePageHelper(driver);
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public HomePageHelper getHomePageHelper() {
        return homePageHelper;
    }

    public void stop() {
        driver.quit();
    }

}
