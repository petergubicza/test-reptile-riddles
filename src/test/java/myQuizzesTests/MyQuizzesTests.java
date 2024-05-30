package myQuizzesTests;

import base.BaseTests;
import com.codecool.reptile.pages.MyQuizzesPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyQuizzesTests extends BaseTests {

    @Test
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
    public void addMoreAnswerTest() {
        MyQuizzesPage myQuizzesPage = mainPage.navigateToMyQuizzes();
        myQuizzesPage.clickEditButton();
        myQuizzesPage.clickAddQuestionButton();
        int originalNumber = myQuizzesPage.getNumberOfAnswers();

        myQuizzesPage.clickAddAnswerButton();
        int actual = myQuizzesPage.getNumberOfAnswers();

        assertEquals(originalNumber + 1, actual);
    }
}
