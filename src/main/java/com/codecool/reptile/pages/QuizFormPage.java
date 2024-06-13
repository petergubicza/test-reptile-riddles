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

public class QuizFormPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public QuizFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//button[contains(text(), '1')]")
    private WebElement questionButton;
    @FindBy(xpath = "//button[text() = 'Add Question']")
    private WebElement addQuestionButton;
    @FindBy(xpath = "//input[contains(@id, 'time')]")
    private WebElement timeSetterInput;
    @FindBy(xpath = "//button[text() = 'Save']")
    private WebElement saveQuestionButton;
    @FindBy(xpath = "//button[contains(text(), 'Add option')]")
    private WebElement addAnswerButton;
    @FindBy(xpath = "//button[text() = 'Save quiz']")
    private WebElement saveButton;
    @FindBy(xpath = "//button[text() = 'Delete']")
    private WebElement deleteButton;
    @FindBy(id = "answer-1")
    private WebElement firstAnswerInput;
    @FindBy(id = "answer-2")
    private WebElement secondAnswerInput;
    @FindBy(xpath = "//input[@type='text' and @value='correct answer']/ancestor::div[contains(@class, 'col-span-11')]/following-sibling::div[contains(@class, 'col-span-1')]//input[@type='checkbox']")
    WebElement checkboxForCorrectAnswer;

    private final By answerFieldLocator = By.xpath("//*[contains(@id, 'answer-')]");

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void clickQuestion() {
        wait.until(ExpectedConditions.elementToBeClickable(questionButton));
        questionButton.click();
    }

    public void clickAddQuestion() {
        wait.until(ExpectedConditions.elementToBeClickable(addQuestionButton));
        addQuestionButton.click();
    }

    public void clickAddAnswerButton() {  // TODO: could some of these tiny methods be private?
        wait.until(ExpectedConditions.elementToBeClickable(addAnswerButton));
        addAnswerButton.click();
    }

    public void clickQuestionSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveQuestionButton));
        saveQuestionButton.click();
    }

    public int getNumberOfAnswers() {
        wait.until(ExpectedConditions.visibilityOf(deleteButton));
        List<WebElement> answers = driver.findElements(answerFieldLocator).stream().toList();
        return answers.size();
    }

    public void clickAddQuestionButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addQuestionButton));
        addQuestionButton.click();
    }

    public void setTimeInput(String sec) {
        wait.until(ExpectedConditions.visibilityOf(timeSetterInput));
        timeSetterInput.clear();
        timeSetterInput.sendKeys(sec);
    }

    public String getTimeLimitValue() {
        wait.until(ExpectedConditions.visibilityOf(timeSetterInput));
        return timeSetterInput.getAttribute("value");
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }
    public void clickForCorrectAnswerCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(checkboxForCorrectAnswer));
        checkboxForCorrectAnswer.click();
    }
    public boolean isCorrectAnswerCheckboxChecked() {
        wait.until(ExpectedConditions.visibilityOf(checkboxForCorrectAnswer));
        return checkboxForCorrectAnswer.isSelected();
    }
    public void addTwoAnswers(String firstAnswer, String secondAnswer){
        wait.until(ExpectedConditions.visibilityOf(firstAnswerInput));
        firstAnswerInput.sendKeys(firstAnswer);
        secondAnswerInput.sendKeys(secondAnswer);
    }

    public String selectCorrectAnswer(String correctAnswer, String incorrectAnswer) {
        clickAddQuestion();
        addTwoAnswers(correctAnswer, incorrectAnswer);
        clickForCorrectAnswerCheckbox();
        String testQuestionUrl = driver.getCurrentUrl();
        clickQuestionSaveButton();
        acceptAlert();
        clickSaveButton();
        acceptAlert();
        return testQuestionUrl;
    }
}
