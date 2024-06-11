package com.codecool.reptile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MyQuizzesPage {

    private static final String TITLE = "testquiz";
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By answerField = By.xpath("//*[contains(@id, 'answer-')]");
    @FindBy (xpath = "//button[contains(text(), '1')]")
    private WebElement questionButton;
    @FindBy (xpath = "//button[text() = 'Add Question']")
    private WebElement addQuestionButton;
    @FindBy (xpath = "//input[contains(@id, 'time')]")
    private WebElement timeSetterInput;
    @FindBy (xpath = "//button[text() = 'Save']")
    private WebElement saveQuestionButton;
    @FindBy (xpath = "//button[contains(text(), 'Add option')]")
    private WebElement addAnswerButton;
    @FindBy (xpath = "(//input[@type='checkbox'])")
    private WebElement firstCheckbox;
    @FindBy(xpath = "//button[text() = 'Delete']")
    private WebElement deleteButton;
    @FindBy(xpath = "//button[text() = 'Edit']")
    private WebElement editButton;
    @FindBy(xpath = "//button[text() = 'Add Quiz']")
    private WebElement addButton;
    @FindBy(id = "name")
    private WebElement quizTitleInput;
    @FindBy(xpath = "//span[contains(text(), '" + TITLE + "')]")
    private WebElement quizTitle;
    @FindBy(xpath = "//button[text() = 'Save quiz']")
    private WebElement saveButton;

    public MyQuizzesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
    }

    public void setQuizTitle() {
        wait.until(ExpectedConditions.visibilityOf(quizTitleInput));
        quizTitleInput.sendKeys(TITLE);
    }

    public String getQuizTitle() {
        wait.until(ExpectedConditions.visibilityOf(quizTitle));
        return quizTitle.getText();
    }

    public String getTitle() {
        return TITLE;
    }

    public void clickEditButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editButton));
        editButton.click();
    }
    public void clickQuestion() {
        wait.until(ExpectedConditions.elementToBeClickable(questionButton));
        questionButton.click();
    }

    public void clickAddQuestion() {
        wait.until(ExpectedConditions.elementToBeClickable(addQuestionButton));
        addQuestionButton.click();
    }

    public void clickCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(firstCheckbox));
        firstCheckbox.click();
    }

    public void clickAddAnswerButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addAnswerButton));
        addAnswerButton.click();
    }

    public void clickQuestionSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveQuestionButton));
        saveQuestionButton.click();
    }


    public boolean isFirstCheckboxChecked() {
        wait.until(ExpectedConditions.visibilityOf(firstCheckbox));
        return firstCheckbox.isSelected();
    }

    public int getNumberOfAnswers() {
        wait.until(ExpectedConditions.visibilityOf(deleteButton));
        List<WebElement> answers = driver.findElements(answerField).stream().toList();
        return answers.size();
    }

    public void clickAddQuestionButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addQuestionButton));
        addQuestionButton.click();
    }

    public void setTimeInput() {
        wait.until(ExpectedConditions.visibilityOf(timeSetterInput));
        timeSetterInput.clear();
        timeSetterInput.sendKeys("100");
    }

    public String getTimeLimitValue() {
        wait.until(ExpectedConditions.visibilityOf(timeSetterInput));
        return timeSetterInput.getAttribute("value");
    }
    public void deleteQuiz(){
        wait.until(ExpectedConditions.visibilityOf(deleteButton));
        deleteButton.click();
        driver.switchTo().alert().accept();
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }
}
