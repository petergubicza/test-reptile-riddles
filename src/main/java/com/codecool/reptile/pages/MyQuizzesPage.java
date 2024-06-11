package com.codecool.reptile.pages;

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
    private static final String TITLE = "testquiz";
    @FindBy(xpath = "//button[text() = 'Edit']")
    private WebElement editButton;
    @FindBy(xpath = "//button[text() = 'Delete']")
    private WebElement deleteButton;
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
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public MyQuizzesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }
}
