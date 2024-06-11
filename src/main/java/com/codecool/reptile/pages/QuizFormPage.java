package com.codecool.reptile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class QuizFormPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public QuizFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By answerField = By.xpath("//*[contains(@id, 'answer-')]");
    private final By questionButton = By.xpath("//button[contains(text(), '1')]");
    private final By addQuestionButton = By.xpath("//button[text() = 'Add Question']");
    private final By timeSetterInput = By.xpath("//input[contains(@id, 'time')]");
    private final By saveQuestionButton = By.xpath("//button[text() = 'Save']");
    private final By addAnswerButton = By.xpath("//button[contains(text(), 'Add option')]");
    private final By firstCheckbox = By.id("checkbox-1");
    private final By saveButton = By.xpath("//button[text() = 'Save quiz']");
    private final By deleteButton = By.xpath("//button[text() = 'Delete']");

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
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

    public void setTimeInput() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(timeSetterInput));
        driver.findElement(timeSetterInput).clear();
        driver.findElement(timeSetterInput).sendKeys("100");
    }

    public String getTimeLimitValue() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(timeSetterInput));
        return driver.findElement(timeSetterInput).getAttribute("value");
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
    }
}
