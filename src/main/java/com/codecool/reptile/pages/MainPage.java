package com.codecool.reptile.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    private WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public MyQuizzesPage navigateToMyQuizzes() {
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        driver.get("http://localhost:3000/quiz/my");
        return new MyQuizzesPage(driver);
    }
}
