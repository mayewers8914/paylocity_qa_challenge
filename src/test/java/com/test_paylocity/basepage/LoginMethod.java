package com.test_paylocity.basepage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.test_paylocity.pages.BenefitDashboard;
import com.test_paylocity.pages.Login;
import com.test_paylocity.utilities.BroweserUtils;
import com.test_paylocity.utilities.ConfigurationReader;
import com.test_paylocity.utilities.Driver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginMethod {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ExtentReports reports;
    private ExtentHtmlReporter htmlReporter;
    protected static ExtentTest test;
    public Login loginPage;
    public BenefitDashboard dashboard;
    private static final Logger logger = LogManager.getLogger(PaylocityBase.class);


    @BeforeClass
    public void setUpBeforeClass() {

        reports = new ExtentReports();
        String path = System.getProperty("user.dir") + "/test-output/reportLogin.html";
        htmlReporter = new ExtentHtmlReporter(path);
        htmlReporter.config().setReportName("QA Challenge");

        reports.attachReporter(htmlReporter);
        reports.setSystemInfo("Environment", "QA");
        reports.setSystemInfo("Browser", ConfigurationReader.getProperty("browser"));
        test = reports.createTest("Test");

        logger.info("running the test and expect trace from logger");
    }

    @AfterClass
    public void tearDownAfterClass() {

        reports.flush();
        logger.info("all test completed");

    }



    @BeforeMethod(groups = {"getInfo", "addEmployee"})
    public void loginSetUp() {

        driver = Driver.getDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get(ConfigurationReader.getProperty("urlUI"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        loginPage = new Login();
        dashboard = new BenefitDashboard();

    }


    @AfterMethod
    public void tearDownMethod(ITestResult iTestResult) throws IOException {
        if(test!=null && iTestResult != null) {
            if (iTestResult.getStatus() == ITestResult.FAILURE) {
                test.fail(iTestResult.getName());
                String screenshot = BroweserUtils.getScreenshot(iTestResult.getName());
                test.addScreenCaptureFromPath(screenshot);
                logger.warn("Failed Test: " + iTestResult.getName());
            } else {
                test.pass(iTestResult.getName());
                logger.info("Test Passed: " + iTestResult.getName());
            }
        }

        Driver.closeDriver();

    }
}
