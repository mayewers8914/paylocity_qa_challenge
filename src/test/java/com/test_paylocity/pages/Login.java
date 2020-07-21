package com.test_paylocity.pages;

import com.test_paylocity.basepage.PaylocityBase;
import com.test_paylocity.utilities.ConfigurationReader;
import com.test_paylocity.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login  {
    public Login(){

        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy (xpath = "//input[@id='Username']")
    public WebElement username;

    @FindBy (xpath = "//input[@id='Password']")
    public WebElement password;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement submit;

    @FindBy (xpath = "//a[@class='navbar-brand']")
    public WebElement dashboard;

    public void login(String username, String password) {
        this.username.sendKeys(username);

        this.password.sendKeys(password);

        submit.click();
    }
}
