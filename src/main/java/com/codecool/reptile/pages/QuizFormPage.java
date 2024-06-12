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
import java.util.NoSuchElementException;

public class QuizFormPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public QuizFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//*[contains(@id, 'answer-')]")
    private WebElement answerField;
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
    @FindBy(id = "checkbox-1")
    private WebElement firstCheckbox;
    @FindBy(xpath = "//button[text() = 'Save quiz']")
    private WebElement saveButton;
    @FindBy(xpath = "//button[text() = 'Delete']")
    private WebElement deleteButton;
    @FindBy(id = "-1question")
    private WebElement questionTitleInput;
    @FindBy(id = "answer-1")
    private WebElement firstAnswerInput;
    @FindBy(id = "answer-2")
    private WebElement secondAnswerInput;

    private final By answerFieldLocator = By.xpath("//*[contains(@id, 'answer-')]");

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void clickQuestion() {
        wait.until(ExpectedConditions.elementToBeClickable(questionButton));
        questionButton.click();
        wait.until(ExpectedConditions.visibilityOf(saveQuestionButton));
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
        List<WebElement> answers = driver.findElements(answerFieldLocator).stream().toList();
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

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    public void addTwoAnswers(String firstAnswer, String secondAnswer) {
        wait.until(ExpectedConditions.visibilityOf(firstAnswerInput));
        firstAnswerInput.sendKeys(firstAnswer);
        secondAnswerInput.sendKeys(secondAnswer);
    }

    public void addQuestionWithAnswers(
            String question, String answer1, String answer2, int correctAnswer) {
        wait.until(ExpectedConditions.elementToBeClickable(addQuestionButton));
        addQuestionButton.click();

        wait.until(ExpectedConditions.visibilityOf(questionTitleInput));
        questionTitleInput.sendKeys(question);

        wait.until(ExpectedConditions.visibilityOf(firstAnswerInput));
        firstAnswerInput.sendKeys(answer1);
        secondAnswerInput.sendKeys(answer2);

        if (findNthCorrectCheckbox(correctAnswer) != null) {
            findNthCorrectCheckbox(correctAnswer).click();
        }

        wait.until(ExpectedConditions.elementToBeClickable(saveQuestionButton));
        saveQuestionButton.click();

        acceptAlert();
    }

    public List<String> getAnswers() {
        List<WebElement> answerFields = driver.findElements(By.xpath("//input[contains(@id, 'answer-')]"));

        return answerFields.stream().map(webElement -> webElement.getAttribute("value")).toList();
    }

    private WebElement findNthCorrectCheckbox(int n) {
        try {
            return driver.findElement(By.id("checkbox-" + n));
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
