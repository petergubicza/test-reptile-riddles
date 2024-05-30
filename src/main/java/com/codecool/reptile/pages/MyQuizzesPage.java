package com.codecool.reptile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyQuizzesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MyQuizzesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private static final String TITLE = "testquiz";
    private final By editButton = By.xpath("//button[text() = 'Edit']");
    private final By deleteButton = By.xpath("//button[text() = 'Delete']");
    private final By addButton = By.xpath("//button[text() = 'Add Quiz']");
    private final By quizTitleInput = By.id("name");
    private final By quizTitle = By.xpath("//span[contains(text(), '" + TITLE + "')]");
    private final By saveButton = By.xpath("//button[text() = 'Save quiz']");

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

    public String getQuizTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quizTitle));
        return driver.findElement(quizTitle).getText();
    }

    public String getTitle() {
        return TITLE;
    }

    public void clickEditButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(editButton));
        driver.findElement(editButton).click();
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
    }
}
