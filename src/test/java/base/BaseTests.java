package base;

import com.codecool.reptile.pages.MainPage;
import com.codecool.reptile.pages.QuizFormPage;
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
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.xpath("//button[text() = 'LOGIN']");
    private final String USERNAME = "test";
    private final String PASSWORD = "test";
    protected MainPage mainPage;
    protected WebDriverWait wait;
    protected QuizFormPage quizFormPage;


    @BeforeEach
    public void setUp() {
        String driverLocation = System.getenv("DRIVER_LOCATION");
        System.setProperty("webdriver.chrome.driver", driverLocation);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:3000/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).sendKeys(USERNAME);
        driver.findElement(passwordField).sendKeys(PASSWORD);
        driver.findElement(loginButton).click();

        mainPage = new MainPage(driver);
        quizFormPage = new QuizFormPage(driver);
    }

}
