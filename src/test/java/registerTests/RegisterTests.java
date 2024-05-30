package registerTests;

import com.codecool.reptile.pages.RegisterPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTests {
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
    private WebDriver driver = new ChromeDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    @Test
    public void registerTest(){
        driver.get("http://localhost:3000/register");
        RegisterPage registerPage = new RegisterPage(driver);
        String username = "apple7";
        String password = "apple7";
        String email = "apple7@email.com";

        registerPage.register(username,email,password);
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));

        var actual = driver.getCurrentUrl();

        assertEquals("http://localhost:3000/", actual);
    }
}
