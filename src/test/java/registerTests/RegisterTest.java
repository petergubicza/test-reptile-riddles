package registerTests;

import com.codecool.reptile.pages.LoginPage;
import com.codecool.reptile.pages.RegisterPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {
    private  WebDriver driver;
    private  RegisterPage registerPage;
    private LoginPage loginPage;
    private static final String PROPER_CREDENTIALS = "src/test/resources/data/register-proper-credentials-test-data.csv";

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        driver.get("http://localhost:3000/register"); // Ez mehetne környezeti változóba?
    }

    @ParameterizedTest
    @CsvFileSource(resources = PROPER_CREDENTIALS, numLinesToSkip = 1)
    public void test_RegistrationWithProperCredentials_pass(String username, String password, String email){
        registerPage.register(username, password, email);
        loginPage.logIn(username, password);
        assertEquals("http://localhost:3000/",driver.getCurrentUrl());
    }

    @AfterEach
    public void teardown() {
        // Kéne törölni a létrehozott accountokat, de nincs rá lehetőség
        driver.quit();
    }
}
