package com.test_paylocity.pages;

import com.github.javafaker.Faker;
import com.test_paylocity.basepage.PaylocityBase;
import com.test_paylocity.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class BenefitDashboard extends PaylocityBase {
    public String idNum = null;
    public int idxOfNewEmployee = 0;
    public Faker faker = new Faker();

    public BenefitDashboard() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(tagName = "tr")
    public List<WebElement> tableName;

    @FindBy(id = "add")
    public WebElement addButton;

    @FindBy(xpath = "//input[@id='firstName']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@id='lastName']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@id='dependants']")
    public WebElement dependent;

    @FindBy(id = "addEmployee")
    public WebElement addEmployee;

    @FindBy(id = "updateEmployee")
    public WebElement update;

    public WebElement delete(int idxOfNewEmployee) {

        WebElement updateElement = Driver.getDriver().findElement(By.xpath("//tbody/tr[" + idxOfNewEmployee + "]/td/i[2]"));
        return updateElement;
    }

    public WebElement getRightRowOfNewEmployee(int idxOfNewEmployee) {

        WebElement updateElement = Driver.getDriver().findElement(By.xpath("//tbody/tr[" + idxOfNewEmployee + "]/td/i"));
        return updateElement;
    }

    public List<WebElement> salary() {

        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//tbody/tr/td[5]"));
        return elements;
    }

    public List<WebElement> allInfo() {

        List<WebElement> allInfo = Driver.getDriver().findElements((By.xpath("//tbody/tr")));
        return allInfo;

    }


    public String getNameForRow(int num) {
        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//tbody/tr[" + num + "]/td"));

        return elements.get(1).getText() + " " + elements.get(2).getText();
    }

    public void creatEmployee(String firstname, String lastname, String dependents) {

        firstName.sendKeys(firstname);
        lastName.sendKeys(lastname);
        dependent.clear();
        dependent.sendKeys(dependents);
        addEmployee.click();
    }

    public void setUpdateEmployee(String firstname, String lastname, String dependents) {

        firstName.sendKeys(firstname);
        lastName.sendKeys(lastname);
        dependent.clear();
        dependent.sendKeys(dependents);
        update.click();
    }

    public int saveInt(int num) {
        int nums = num;
        return nums;
    }

}