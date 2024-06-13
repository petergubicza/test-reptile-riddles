package myQuizEditorTests;

import com.codecool.reptile.pages.LoginPage;
import com.codecool.reptile.pages.MainPage;
import com.codecool.reptile.pages.MyQuizzesPage;
import com.codecool.reptile.pages.QuizFormPage;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyQuizEditorTest {  // TODO: test folder structure should exactly match main folder structure (LoginPageTest, MainPageTest, etc.)
    protected WebDriver driver = new ChromeDriver();
    private final MainPage mainPage = new MainPage(driver);
    private final QuizFormPage quizFormPage = new QuizFormPage(driver);
    private WebDriverWait wait;
    private MyQuizzesPage myQuizzesPage;
    private String quizUrl;
    private static final String VALID_USERNAME = System.getenv("USERNAME");
    private static final String VALID_PASSWORD = System.getenv("PASSWORD");
    private static final String LOGIN_URL = "http://localhost:3000/login";
    private static final String CORRECT_ANSWER = "correct answer";
    private static final String INCORRECT_ANSWER = "incorrect answer";

    @BeforeEach
    public void setUp() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(VALID_USERNAME, VALID_PASSWORD);

        myQuizzesPage = mainPage.navigateToMyQuizzes();

        myQuizzesPage.clickAddButton();
        myQuizzesPage.setQuizTitle();
        quizUrl = driver.getCurrentUrl();
        myQuizzesPage.clickSaveButton();
        myQuizzesPage.acceptAlert();
        driver.get(quizUrl);

    }

    @Test
    public void test_selectCorrectAnswer() {
        String testQuestionUrl = quizFormPage.selectCorrectAnswer(CORRECT_ANSWER, INCORRECT_ANSWER);
        driver.get(testQuestionUrl);
        quizFormPage.clickQuestion();
        assertTrue(quizFormPage.isCorrectAnswerCheckboxChecked());
    }

    @Test
    public void test_AddMoreAnswer() {
        quizFormPage.clickAddQuestionButton();
        int originalNumber = quizFormPage.getNumberOfAnswers();

        quizFormPage.clickAddAnswerButton();
        int actual = quizFormPage.getNumberOfAnswers();

        assertEquals(originalNumber + 1, actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data-valid-time-limit.csv", numLinesToSkip = 1)
    public void test_SetTimeWithValidTimeLimit(String sec) {  // TODO: parameter could be called expected
        quizFormPage.clickAddQuestionButton();
        quizFormPage.setTimeInput(sec);
        quizFormPage.clickQuestionSaveButton();
        myQuizzesPage.acceptAlert();

        wait.until(ExpectedConditions.urlToBe(quizUrl));  // TODO: waiting should happen inside a page object, after the navigation call

        quizFormPage.clickQuestion();
        String actual = quizFormPage.getTimeLimitValue();

        assertEquals(sec, actual);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data-invalid-time-limit.csv", numLinesToSkip = 1)
    public void test_SetTimeWithInvalidTimeLimit(String invalidType) {
        quizFormPage.clickAddQuestionButton();
        quizFormPage.setTimeInput(invalidType);
        quizFormPage.clickQuestionSaveButton();
        quizFormPage.acceptAlert();

        wait.until(ExpectedConditions.urlToBe(quizUrl));

        quizFormPage.clickQuestion();
        String actual = quizFormPage.getTimeLimitValue();

        assertEquals("30", actual); //TODO minusz értékre bugriportot írni
    }

    @AfterEach
    public void tearDown() {
        myQuizzesPage.deleteQuiz();
        driver.quit();
    }
}
