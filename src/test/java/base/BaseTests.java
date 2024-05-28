package base;

import com.codecool.reptile.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTests {
    WebDriver driver;
    private By usernameField = By.id("user-name");
    //private By usernameField = By.xpath("//input[@type = 'text']");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[text() = 'LOGIN']");
    private String username = "test";
    private String password = "test";


    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "D:\\Codecool\\projects\\Advanced\\test-reptile-riddles\\src\\test\\resources/chromedriver.exe");
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:3000/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
