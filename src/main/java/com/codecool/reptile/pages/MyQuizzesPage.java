package com.codecool.reptile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class MyQuizzesPage {

    private static final String TITLE = "testquiz";
    private static final String BASE_URL = "http://localhost:3000/quiz/my";  // <3
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(xpath = "//button[text() = 'Delete']")
    private WebElement deleteButton;  // TODO: rename to firstDeleteButton?
    @FindBy(xpath = "//button[text() = 'Add Quiz']")
    private WebElement addQuizButton;
    @FindBy(id = "name")
    private WebElement quizTitleInput;
    @FindBy(xpath = "//button[text() = 'Save quiz']")
    private WebElement saveButton;

    public MyQuizzesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void setQuizTitle() {
        wait.until(ExpectedConditions.visibilityOf(quizTitleInput));
        quizTitleInput.sendKeys(TITLE);
    }

    public void createQuiz(String inputString) {
        navigateToMyQuizzes();
        clickAddButton();
        typeToInputField(quizTitleInput, inputString);
        clickSaveButton();
        acceptAlert();
    }
    public void deleteQuiz(){
        driver.get("http://localhost:3000/quiz/my");  // TODO: should reference constant
        wait.until(ExpectedConditions.visibilityOf(deleteButton));
        deleteButton.click();
        driver.switchTo().alert().accept();
    }

    public void deleteQuiz(String quizTitle){
        navigateToMyQuizzes();
        if(isQuizPresent(quizTitle)) {
            String myDeleteButton = "//span[contains(text(), '" + quizTitle + "')]/following-sibling::button[text() = 'Delete']";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(myDeleteButton))).click();
            acceptAlert();
        }
    }


    public boolean isQuizPresent(String quizTitle) {
        try {
            navigateToMyQuizzes();
            String xpathExpression = "//span[contains(text(), '" + quizTitle + "')]";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
            WebElement element = driver.findElement(By.xpath(xpathExpression));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Couldn't find the element: " + e.getMessage());
            return false;
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for element: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        return false;
    }

    private void navigateToMyQuizzes() {
        driver.get(BASE_URL);
    }

    public void acceptAlert() {  // TODO: remove duplications
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addQuizButton)).click();
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();;
    }

    private void typeToInputField(WebElement webElement, String inputString) {
        wait.until(ExpectedConditions.visibilityOf(webElement)).sendKeys(inputString);
    }
}
