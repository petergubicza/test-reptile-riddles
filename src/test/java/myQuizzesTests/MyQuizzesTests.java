package myQuizzesTests;

import base.BaseTests;
import com.codecool.reptile.pages.MyQuizzesPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
