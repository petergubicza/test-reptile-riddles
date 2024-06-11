package com.codecool.reptile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyQuizzesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MyQuizzesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    private By editButton = By.xpath("//button[text() = 'Edit']");
    private By answerField = By.xpath("//*[contains(@id, 'answer-')]");
    @FindBy (xpath = "//button[contains(text(), '1')]")
    private WebElement questionButton;
    private By addQuestionButton = By.xpath("//button[text() = 'Add Question']");
    private By addButton = By.xpath("//button[text() = 'Add Quiz']");
    private By quizTitleInput = By.id("name");
    private By saveButton = By.xpath("//button[text() = 'Save quiz']");
    private static final String TITLE = "testquiz";
    private By quizTitle = By.xpath("//span[contains(text(), '" + TITLE + "')]");
    private By timeSetterInput = By.xpath("//input[contains(@id, 'time')]");
    private By saveQuestionButton = By.xpath("//button[text() = 'Save']");
    private By addAnswerButton = By.xpath("//button[contains(text(), 'Add option')]");
    private By firstCheckbox = By.xpath("(//input[@type='checkbox'])");

    @FindBy(xpath = "//button[text() = 'Delete']")
    private WebElement deleteButton;

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
    public void clickQuestion() {
        wait.until(ExpectedConditions.elementToBeClickable(questionButton));
        questionButton.click();
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
        wait.until(ExpectedConditions.visibilityOf(deleteButton));
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
    public void deleteQuiz(){
        wait.until(ExpectedConditions.visibilityOf(deleteButton));
        deleteButton.click();
        driver.switchTo().alert().accept();
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
    }
}
