package base;

import com.codecool.reptile.pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTests {
    protected WebDriver driver = new ChromeDriver();
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[text() = 'LOGIN']");
    private String username = "test";
    private String password = "test";
    protected MainPage mainPage;
    protected WebDriverWait wait;


    @BeforeEach
    public void setUp(){
        String driverLocation = System.getenv("DRIVER_LOCATION");
        System.setProperty("webdriver.chrome.driver", driverLocation);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:3000/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
