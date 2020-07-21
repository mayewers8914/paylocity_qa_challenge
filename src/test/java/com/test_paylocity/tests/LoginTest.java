package com.test_paylocity.tests;

import com.test_paylocity.basepage.LoginMethod;
import com.test_paylocity.basepage.PaylocityBase;
import com.test_paylocity.utilities.ConfigurationReader;
import org.testng.annotations.Test;
import static org.testng.Assert.*;


public class LoginTest extends LoginMethod {

    @Test
    public void dashboard() {
        loginPage.dashboard.click();

        String expectedResult = "Log In - Paylocity Benefits Dashboard";
        assertEquals(driver.getTitle(), expectedResult);
    }

    @Test
    public void loginNegativeData() {

      loginPage.login(ConfigurationReader.getProperty("invalidUsername"), ConfigurationReader.getProperty("invalidPassword"));

        String expectedResult = "Error - Paylocity Benefits Dashboard";
        assertEquals(driver.getTitle(), expectedResult);

    }

    @Test
    public void loginWithPositiveData() {

        loginPage.login(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));


        String expectedResult = "Employees - Paylocity Benefits Dashboard";
        assertEquals(driver.getTitle(), expectedResult);
    }



}
