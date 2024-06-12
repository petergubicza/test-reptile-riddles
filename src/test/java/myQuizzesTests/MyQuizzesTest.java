package myQuizzesTests;

import com.codecool.reptile.pages.LoginPage;
import com.codecool.reptile.pages.MyQuizzesPage;
import com.codecool.reptile.pages.RegistrationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;
public class MyQuizzesTest {
    private static final String PROPER_USERNAME = System.getenv("PROPER_USERNAME");
    private static final String PROPER_PASSWORD = System.getenv("PROPER_PASSWORD");
    private static final String PROPER_EMAIL = System.getenv("PROPER_EMAIL");
    private WebDriver driver;
    private MyQuizzesPage myQuizzesPage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        myQuizzesPage = new MyQuizzesPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);

        registrationPage.register(PROPER_USERNAME, PROPER_PASSWORD, PROPER_EMAIL);
        loginPage.logIn(PROPER_USERNAME, PROPER_PASSWORD);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/create-quiz-test-data.csv", numLinesToSkip = 1)
    public void test_createQuiz(String quizTitleInput) {

        myQuizzesPage.createQuiz(quizTitleInput);

        boolean isQuizPresent = myQuizzesPage.isQuizPresent(quizTitleInput);
        assertTrue(isQuizPresent);

        myQuizzesPage.deleteQuiz(quizTitleInput);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
