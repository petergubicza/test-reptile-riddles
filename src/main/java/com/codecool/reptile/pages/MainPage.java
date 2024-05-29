package com.codecool.reptile.pages;

import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MyQuizzesPage navigateToMyQuizzes(){
        driver.get("http://localhost:3000/quiz/my");
        return new MyQuizzesPage(driver);
    }
}
