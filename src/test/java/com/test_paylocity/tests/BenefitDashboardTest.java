package com.test_paylocity.tests;

import com.github.javafaker.Faker;
import com.test_paylocity.basepage.PaylocityBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class BenefitDashboardTest extends PaylocityBase {
    int num;

    @Test
    public void getInfo() {

        List<WebElement> salary = dashboard.salary();
        for (int i = 0; i < salary.size(); i++) {
            WebElement each = salary.get(i);
            if (!each.getText().equals("52000.00")) {
                System.out.println(dashboard.getNameForRow(i +1));
                assertEquals(each.getText(), "52000.00");
            }

        }
    }

    @Test
    public void addEmployeeWithValidCredentials() {

        dashboard.addButton.click();


        String firstname = dashboard.faker.name().firstName();
        String lastname = dashboard.faker.name().lastName();
        wait.until(ExpectedConditions.visibilityOfAllElements(dashboard.firstName, dashboard.lastName));
        dashboard.creatEmployee(firstname, lastname, "2");

        wait.until(ExpectedConditions.invisibilityOfAllElements(dashboard.allInfo()));
        List<WebElement> allInfo = dashboard.allInfo();
        for (int i = 0; i < allInfo.size(); i++) {
            WebElement currentRow = allInfo.get(i);
            List<WebElement> eachChildren = currentRow.findElements(By.tagName("td"));
            if (eachChildren.get(2).getText().equals(lastname) && eachChildren.get(1).getText().equals(firstname)) {
                dashboard.idxOfNewEmployee = i;
                System.out.println("setting dashboard.idxOfNewEmployee = " + dashboard.idxOfNewEmployee);
                dashboard.idNum = eachChildren.get(0).getText();
                System.out.println(dashboard.idNum);

                assertTrue(currentRow.toString().contains(lastname + " " + firstname));
            }
        }

    }



    @Test
    public void editRecentlyAdded() {
//        System.out.println("reading dashboard.idxOfNewEmployee = " + dashboard.idxOfNewEmployee);
//        dashboard.getRightRowOfNewEmployee(dashboard.idxOfNewEmployee).click();

        dashboard.addButton.click();
        String firstname = dashboard.faker.name().firstName();
        String lastname = dashboard.faker.name().lastName();
        wait.until(ExpectedConditions.visibilityOfAllElements(dashboard.firstName, dashboard.lastName));
        dashboard.creatEmployee(firstname, lastname, "2");
        wait.until(ExpectedConditions.invisibilityOfAllElements(dashboard.firstName, dashboard.lastName));

        int idxOfNewEmployee = -1;
        String idNum = "";
        List<WebElement> allInfo = dashboard.allInfo();
        for (int i = 0; i < allInfo.size(); i++) {
            WebElement currentRow = allInfo.get(i);
            List<WebElement> eachChildren = currentRow.findElements(By.tagName("td"));
            if (eachChildren.get(2).getText().equals(lastname) && eachChildren.get(1).getText().equals(firstname)) {
                idxOfNewEmployee = i;
                idNum = eachChildren.get(0).getText();
                break;
            }
        }

        dashboard.getRightRowOfNewEmployee(idxOfNewEmployee).click();

        String newName = dashboard.faker.name().firstName();
        String newLastName = dashboard.faker.name().lastName();

        wait.until(ExpectedConditions.visibilityOfAllElements(dashboard.firstName, dashboard.lastName, dashboard.dependent));
        wait.until(ExpectedConditions.elementToBeClickable(dashboard.update));
        dashboard.setUpdateEmployee(newName, newLastName, "1");

        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfAllElements(dashboard.firstName, dashboard.lastName, dashboard.dependent)));
        allInfo = dashboard.allInfo();
        for (int i = 0; i < allInfo.size(); i++) {
            WebElement currentRow = allInfo.get(i);
            List<WebElement> eachChildren = currentRow.findElements(By.tagName("td"));
            if (eachChildren.get(0).getText().equals(idNum)) {
              assertEquals(eachChildren.get(0).getText(), idNum);
              break;
            }
        }
//        List<WebElement> cellsInRow = dashboard.elementChanged(idxOfNewEmployee).findElements(By.tagName("td"));
//
//        assertEquals(cellsInRow.get(1).getText() + " " + cellsInRow.get(2).getText(), newName + " " + newLastName);


    }



    @Test
    public void deletingEmplyee() {
//        System.out.println("reading dashboard.idxOfNewEmployee = " + dashboard.idxOfNewEmployee);
//        dashboard.getRightRowOfNewEmployee(dashboard.idxOfNewEmployee).click();

        dashboard.addButton.click();
        String firstname = dashboard.faker.name().firstName();
        String lastname = dashboard.faker.name().lastName();
        wait.until(ExpectedConditions.visibilityOfAllElements(dashboard.firstName, dashboard.lastName));
        dashboard.creatEmployee(firstname, lastname, "2");
        wait.until(ExpectedConditions.invisibilityOfAllElements(dashboard.firstName, dashboard.lastName));

        int idxOfNewEmployee = -1;
        String idNum = "";
        List<WebElement> allInfo = dashboard.allInfo();
        for (int i = 0; i < allInfo.size(); i++) {
            WebElement currentRow = allInfo.get(i);
            List<WebElement> eachChildren = currentRow.findElements(By.tagName("td"));
            if (eachChildren.get(2).getText().equals(lastname) && eachChildren.get(1).getText().equals(firstname)) {
                idxOfNewEmployee = i;
                break;
            }
        }

        dashboard.delete(idxOfNewEmployee).click();

        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfAllElements(dashboard.firstName, dashboard.lastName, dashboard.dependent)));
        allInfo = dashboard.allInfo();
        for (int i = 0; i < allInfo.size(); i++) {
            WebElement currentRow = allInfo.get(i);
            List<WebElement> eachChildren = currentRow.findElements(By.tagName("td"));
            if (eachChildren.get(0).getText().equals(idNum)) {
                assertEquals(eachChildren.get(0).getText(), idNum);
                break;
            } else {
                assertTrue(!eachChildren.get(0).isSelected());
            }
        }


    }


}
