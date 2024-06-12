package com.codecool.reptile.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "user-name")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "email")
    private WebElement emailField;
    @FindBy(xpath = "//button[text() = 'SIGN UP']")
    private WebElement singUpButton;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void register(String username,String password, String email){
        driver.get("http://localhost:3000/register");
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(singUpButton)).click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/login"));
    }
}
