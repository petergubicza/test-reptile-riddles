package com.codecool.reptile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyQuizzesPage {

    private WebDriver driver;

    public MyQuizzesPage(WebDriver driver) {
        this.driver = driver;
    }

    private By addButton = By.xpath("//button[text() = 'Add Quiz']");
    private By quizTitleInput = By.id("name");
    private By saveButton = By.xpath("//button[text() = 'Save quiz']");
    private static final String TITLE = "testquiz";
    private By quizTitle = By.xpath("//span[contains(text(), '" + TITLE + "')]");

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }
    public void clickAddButton(){
        driver.findElement(addButton).click();
    }
    public void setQuizTitle(){
        driver.findElement(quizTitleInput).sendKeys(TITLE);
    }
    public void clickSaveButton(){
        driver.findElement(saveButton).click();
    }
    public String getQuizTitle(){
        return driver.findElement(quizTitle).getText();
    }
    public String getTitle(){
        return TITLE;
    }
}
