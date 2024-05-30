package myQuizzesTests;

import base.BaseTests;
import com.codecool.reptile.pages.MyQuizzesPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
@TestMethodOrder(OrderAnnotation.class)
public class MyQuizzesTests extends BaseTests {

    @Test
    @Order(1)
    public void createQuizTest() {
        MyQuizzesPage myQuizzesPage = mainPage.navigateToMyQuizzes();
        myQuizzesPage.clickAddButton();
        myQuizzesPage.setQuizTitle();
        myQuizzesPage.clickSaveButton();
        myQuizzesPage.acceptAlert();
        driver.get("http://localhost:3000/quiz/my");

        var actual = myQuizzesPage.getQuizTitle();

        assertEquals(myQuizzesPage.getTitle(), actual);
    }

    @Test
    @Order(2)
    public void selectCorrectAnswerTest() {
        MyQuizzesPage myQuizzesPage = mainPage.navigateToMyQuizzes();
        myQuizzesPage.clickEditButton();
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
    @Order(3)
    public void addMoreAnswerTest() {
        MyQuizzesPage myQuizzesPage = mainPage.navigateToMyQuizzes();
        myQuizzesPage.clickEditButton();
        myQuizzesPage.clickAddQuestionButton();
        int originalNumber = myQuizzesPage.getNumberOfAnswers();

        myQuizzesPage.clickAddAnswerButton();
        int actual = myQuizzesPage.getNumberOfAnswers();

        assertEquals(originalNumber + 1, actual);
    }

    @Test
    @Order(4)
    public void setTimeTest() {
        MyQuizzesPage myQuizzesPage = mainPage.navigateToMyQuizzes();
        myQuizzesPage.clickEditButton();
        myQuizzesPage.clickQuestion();
        myQuizzesPage.setTimeInput();
        myQuizzesPage.clickQuestionSaveButton();
        myQuizzesPage.acceptAlert();

        myQuizzesPage.clickQuestion();
        String actual = myQuizzesPage.getTimeLimitValue();
        String expected = "100";

        assertEquals(expected, actual);
    }
}
