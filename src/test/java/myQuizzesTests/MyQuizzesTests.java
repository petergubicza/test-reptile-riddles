package myQuizzesTests;

import base.BaseTests;
import com.codecool.reptile.pages.MyQuizzesPage;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestMethodOrder(OrderAnnotation.class)
public class MyQuizzesTests extends BaseTests {

    @Test
    @Order(1)
    public void createQuizTest() {
        MyQuizzesPage myQuizzesPage = mainPage.navigateToMyQuizzes();
        myQuizzesPage.clickAddButton();
        myQuizzesPage.setQuizTitle();
        quizFormPage.clickSaveButton();
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
        quizFormPage.clickAddQuestion();
        quizFormPage.clickCheckbox();
        String testQuestionUrl = driver.getCurrentUrl();
        quizFormPage.clickQuestionSaveButton();
        quizFormPage.acceptAlert();
        quizFormPage.clickSaveButton();
        quizFormPage.acceptAlert();

        driver.get(testQuestionUrl);
        quizFormPage.clickQuestion();
        assertTrue(quizFormPage.isFirstCheckboxChecked());
    }


    @Test
    @Order(3)
    public void addMoreAnswerTest() {
        MyQuizzesPage myQuizzesPage = mainPage.navigateToMyQuizzes();
        myQuizzesPage.clickEditButton();
        quizFormPage.clickAddQuestionButton();
        int originalNumber = quizFormPage.getNumberOfAnswers();

        quizFormPage.clickAddAnswerButton();
        int actual = quizFormPage.getNumberOfAnswers();

        assertEquals(originalNumber + 1, actual);
    }

    @Test
    @Order(4)
    public void setTimeTest() {
        MyQuizzesPage myQuizzesPage = mainPage.navigateToMyQuizzes();
        myQuizzesPage.clickEditButton();
        quizFormPage.clickQuestion();
        quizFormPage.setTimeInput();
        quizFormPage.clickQuestionSaveButton();
        myQuizzesPage.acceptAlert();

        quizFormPage.clickQuestion();
        String actual = quizFormPage.getTimeLimitValue();
        String expected = "100";

        assertEquals(expected, actual);
    }
}
