package com.ui.listeners;

import com.aventstack.extentreports.Status;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReportUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.util.Arrays;

public class TestListener implements ITestListener {

    Logger logger = LoggerUtility.getLogger(this.getClass());

    public void onTestStart(ITestResult result) {

        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
        logger.info(Arrays.toString(result.getMethod().getGroups()));
        ExtentReportUtility.createExtentTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("{} PASSED", result.getMethod().getMethodName());
        ExtentReportUtility.getTest().log(Status.PASS, result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        logger.error("{} FAILED", result.getMethod().getMethodName());
        logger.error(result.getThrowable().getMessage());
        ExtentReportUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName());
        ExtentReportUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());
        Object testClass = result.getInstance();
        BrowserUtility browserUtility = ((TestBase)testClass).getInstance();
        logger.info("Capturing Screenshot for the failed tests");
        String screenShotPath = browserUtility.takeScreenshot(result.getMethod().getMethodName());
        logger.info("Attaching the Screenshot to the HTML File");
        ExtentReportUtility.getTest().addScreenCaptureFromPath(screenShotPath);
    }

    public void onTestSkipped(ITestResult result) {
        logger.warn("{} SKIPPED", result.getMethod().getMethodName());
        ExtentReportUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName());
    }

    public void onStart(ITestContext context) {
        logger.info("Test Suite Started");
        ExtentReportUtility.setupSparkReporter("report.html");
    }

    public void onFinish(ITestContext context) {
        logger.info("Test Suite Completed");
        ExtentReportUtility.flushReport();
    }
}
