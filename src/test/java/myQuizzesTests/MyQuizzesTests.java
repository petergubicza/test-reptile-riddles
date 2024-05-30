package myQuizzesTests;

import base.BaseTests;
import com.codecool.reptile.pages.MyQuizzesPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyQuizzesTests extends BaseTests {

    @Test
    public void createQuizTest() {
        MyQuizzesPage myQuizzesPage = mainPage.navigateToMyQuizzes();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        myQuizzesPage.clickAddButton();
        myQuizzesPage.setQuizTitle();
        myQuizzesPage.clickSaveButton();
        myQuizzesPage.acceptAlert();
        driver.get("http://localhost:3000/quiz/my");

        var actual = myQuizzesPage.getQuizTitle();

        assertEquals(myQuizzesPage.getTitle(), actual);
    }
}
