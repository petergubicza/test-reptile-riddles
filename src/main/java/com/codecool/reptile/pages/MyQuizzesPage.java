package com.codecool.reptile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MyQuizzesPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By firstCheckbox = By.xpath("(//input[@type='checkbox'])");

    public MyQuizzesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By editButton = By.xpath("//button[text() = 'Edit']");
    private By deleteButton = By.xpath("//button[text() = 'Delete']");
    private By answerField = By.xpath("//*[contains(@id, 'answer-')]");
    private By questionButton = By.xpath("//button[contains(text(), '1')]");
    private By addQuestionButton = By.xpath("//button[text() = 'Add Question']");
    private By addButton = By.xpath("//button[text() = 'Add Quiz']");
    private By quizTitleInput = By.id("name");
    private By saveButton = By.xpath("//button[text() = 'Save quiz']");
    private By saveQuestionButton = By.xpath("//button[text() = 'Save']");
    private static final String TITLE = "testquiz";
    private By quizTitle = By.xpath("//span[contains(text(), '" + TITLE + "')]");
    private By addAnswerButton = By.xpath("//button[contains(text(), 'Add option')]");


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
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
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

    public void clickQuestion() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(questionButton));
        driver.findElement(questionButton).click();
    }

    public void clickAddQuestion() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addQuestionButton));
        driver.findElement(addQuestionButton).click();
    }

    public void clickCheckbox() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstCheckbox));
        driver.findElement(firstCheckbox).click();
    }

    public void clickAddAnswerButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addAnswerButton));
        driver.findElement(addAnswerButton).click();
    }

    public void clickQuestionSaveButton() {
        driver.findElement(saveQuestionButton).click();
    }


    public boolean isFirstCheckboxChecked() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstCheckbox));
        return driver.findElement(firstCheckbox).isSelected();
    }

    public int getNumberOfAnswers() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton));
        List<WebElement> answers = driver.findElements(answerField).stream().toList();
        return answers.size();
    }

    public void clickAddQuestionButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addQuestionButton));
        driver.findElement(addQuestionButton).click();
    }
}
