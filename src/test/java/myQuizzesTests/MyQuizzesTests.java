package myQuizzesTests;

import base.BaseTests;
import com.codecool.reptile.pages.MyQuizzesPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyQuizzesTests extends BaseTests {

    @Test
    public void createQuizTest(){
        MyQuizzesPage myQuizzesPage = mainPage.navigateToMyQuizzes();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text() = 'Add Quiz']")));
        myQuizzesPage.clickAddButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        myQuizzesPage.setQuizTitle();
        myQuizzesPage.clickSaveButton();
        wait.until(ExpectedConditions.alertIsPresent());
        myQuizzesPage.acceptAlert();
        driver.get("http://localhost:3000/quiz/my");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'testquiz')]")));
        var actual = myQuizzesPage.getQuizTitle();

        assertEquals(myQuizzesPage.getTitle(), actual);
    }

    @Test
    public void testtest(){

    }
}
