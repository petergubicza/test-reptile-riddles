package com.codecool.reptile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyQuizzesPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public MyQuizzesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By addButton = By.xpath("//button[text() = 'Add Quiz']");
    private By quizTitleInput = By.id("name");
    private By saveButton = By.xpath("//button[text() = 'Save quiz']");
    private static final String TITLE = "testquiz";
    private By quizTitle = By.xpath("//span[contains(text(), '" + TITLE + "')]");

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addButton));
        driver.findElement(addButton).click();
    }

    public void setQuizTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quizTitleInput));
        driver.findElement(quizTitleInput).sendKeys(TITLE);
    }

    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    public String getQuizTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quizTitle));
        return driver.findElement(quizTitle).getText();
    }

    public String getTitle() {
        return TITLE;
    }
}
