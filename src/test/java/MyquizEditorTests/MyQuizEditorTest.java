package MyquizEditorTests;

import com.codecool.reptile.pages.MainPage;
import com.codecool.reptile.pages.MyQuizzesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyQuizEditorTest{
    protected WebDriver driver = new ChromeDriver();
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[text() = 'LOGIN']");
    private String username = "test";
    private String password = "test";
    protected MainPage mainPage;
    protected WebDriverWait wait;
    private MyQuizzesPage myQuizzesPage;
    private String quizUrl;

    @BeforeEach
    public void setUp(){
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
        myQuizzesPage.clickSaveButton();
        myQuizzesPage.acceptAlert();
        driver.get(quizUrl);

    }

    @Test
    public void selectCorrectAnswerTest() {
        myQuizzesPage.clickAddQuestion();
        myQuizzesPage.clickCheckbox();
        String testQuestionUrl = driver.getCurrentUrl();
        myQuizzesPage.clickQuestionSaveButton();
        myQuizzesPage.acceptAlert();
        myQuizzesPage.clickSaveButton();
        myQuizzesPage.acceptAlert();

        driver.get(testQuestionUrl);
        myQuizzesPage.clickQuestion();
        assertTrue(myQuizzesPage.isFirstCheckboxChecked());
    }
    @Test
    public void addMoreAnswerTest() {
        myQuizzesPage.clickAddQuestionButton();
        int originalNumber = myQuizzesPage.getNumberOfAnswers();

        myQuizzesPage.clickAddAnswerButton();
        int actual = myQuizzesPage.getNumberOfAnswers();

        assertEquals(originalNumber + 1, actual);
    }
    @Test
    public void setTimeTest() {
        myQuizzesPage.clickAddQuestionButton();
        myQuizzesPage.setTimeInput();
        myQuizzesPage.clickQuestionSaveButton();
        myQuizzesPage.acceptAlert();

        wait.until(ExpectedConditions.urlToBe(quizUrl));

        myQuizzesPage.clickQuestion();
        String actual = myQuizzesPage.getTimeLimitValue();
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
