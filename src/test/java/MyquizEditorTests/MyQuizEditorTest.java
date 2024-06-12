package MyquizEditorTests;

import com.codecool.reptile.pages.MainPage;
import com.codecool.reptile.pages.MyQuizzesPage;
import com.codecool.reptile.pages.QuizFormPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyQuizEditorTest {
    protected WebDriver driver = new ChromeDriver();
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[text() = 'LOGIN']");
    private String username = "test";
    private String password = "test";
    protected MainPage mainPage;
    protected WebDriverWait wait;
    private MyQuizzesPage myQuizzesPage;
    private QuizFormPage quizFormPage = new QuizFormPage(driver);
    private String quizUrl;

    @BeforeEach
    public void setUp() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:3000/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();

        mainPage = new MainPage(driver);

        myQuizzesPage = mainPage.navigateToMyQuizzes();
        myQuizzesPage.clickAddButton();
        myQuizzesPage.setQuizTitle();
        quizUrl = driver.getCurrentUrl();
        quizFormPage.clickSaveButton();
        myQuizzesPage.acceptAlert();
        driver.get(quizUrl);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/quiz-answers.csv", numLinesToSkip = 1)
    public void test_createQuestionWithAnswers_QuestionIsSaved(
            String question, String answer1, String answer2, int correctAnswer) {

        quizFormPage.addQuestionWithAnswers(question, answer1, answer2, correctAnswer);

        quizFormPage.clickQuestion();

        List<String> expected = new ArrayList<>(Arrays.asList(answer1, answer2));
        List<String> actual = quizFormPage.getAnswers();
        assertLinesMatch(expected, actual);
    }

    @Test
    public void selectCorrectAnswerTest() {
        quizFormPage.clickAddQuestion();
        quizFormPage.clickCheckbox();
        String testQuestionUrl = driver.getCurrentUrl();
        quizFormPage.clickQuestionSaveButton();
        myQuizzesPage.acceptAlert();
        quizFormPage.clickSaveButton();
        myQuizzesPage.acceptAlert();

        driver.get(testQuestionUrl);
        quizFormPage.clickQuestion();
        assertTrue(quizFormPage.isFirstCheckboxChecked());
    }

    @Test
    public void addMoreAnswerTest() {
        quizFormPage.clickAddQuestionButton();
        int originalNumber = quizFormPage.getNumberOfAnswers();

        quizFormPage.clickAddAnswerButton();
        int actual = quizFormPage.getNumberOfAnswers();

        assertEquals(originalNumber + 1, actual);
    }

    @Test
    public void setTimeTest() {
        quizFormPage.clickAddQuestionButton();
        quizFormPage.setTimeInput();
        quizFormPage.clickQuestionSaveButton();
        myQuizzesPage.acceptAlert();

        wait.until(ExpectedConditions.urlToBe(quizUrl));

        quizFormPage.clickQuestion();
        String actual = quizFormPage.getTimeLimitValue();
        String expected = "100";

        assertEquals(expected, actual);
    }

    @AfterEach
    public void tearDown() {
        driver.get("http://localhost:3000/quiz/my");
        myQuizzesPage.deleteQuiz();
        driver.quit();
    }
}
