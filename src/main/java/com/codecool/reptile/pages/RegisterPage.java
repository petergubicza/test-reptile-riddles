package com.codecool.reptile.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
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
    @FindBy(xpath = "//button[text() = 'LOGIN']")
    private WebElement loginButton;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void register(String username,String email, String password){
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(username);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(singUpButton));
        singUpButton.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/login"));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }
}