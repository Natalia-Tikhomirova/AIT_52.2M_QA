package Homework_project.core;

import Homework_project.fw.CartHelper;
import Homework_project.fw.HomePageHelper;
import Homework_project.fw.UserHelper;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class ApplicationManager {
    public WebDriver driver;
    private final String browser;
    UserHelper userHelper;
    HomePageHelper homePageHelper;
    CartHelper cartHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equalsIgnoreCase("chrome")) {
//          ChromeOptions options = new ChromeOptions();
//          options.addArguments("headless");
//          options.addArguments("window-size=1920x1080");
//          driver = new ChromeDriver(options);
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println(browser);
        userHelper = new UserHelper(driver);
        homePageHelper = new HomePageHelper(driver);
        cartHelper = new CartHelper(driver);

    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public HomePageHelper getHomePageHelper() {
        return homePageHelper;
    }

    public CartHelper getCartHelper() {
        return cartHelper;
    }


    public void stop() {
        driver.quit();
    }

}
