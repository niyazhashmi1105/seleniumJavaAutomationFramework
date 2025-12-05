package com.utility;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public abstract class BrowserUtility {

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private WebDriverWait wait;
    private final Logger logger = LoggerUtility.getLogger(this.getClass());
    private static final long EXPLICIT_WAIT_TIME = 30L;

    public WebDriver getDriver(){
        return driver.get();
    }

    public BrowserUtility(WebDriver driver){
        super();
        this.driver.set(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIME));
    }

    public BrowserUtility(Browser browserName){
        ExtentReportUtility.getTest().log(Status.INFO,"Launching Browser for " + browserName);

        if(browserName == Browser.CHROME){
            driver.set(new ChromeDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(EXPLICIT_WAIT_TIME));
        }
        else if(browserName == Browser.EDGE){
            driver.set(new EdgeDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(EXPLICIT_WAIT_TIME));
        }
        else if(browserName == Browser.FIREFOX){
            driver.set(new FirefoxDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(EXPLICIT_WAIT_TIME));
        }
        else if(browserName == Browser.SAFARI){
            driver.set(new SafariDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(EXPLICIT_WAIT_TIME));
        }
        else{
            logger.error("Invalid Browser Name!! Please select among chrome, edge, firefox or safari only");
            extentLog(Status.FAIL,"Invalid Browser Name!! Please select among chrome, edge, firefox or safari only");
            System.err.println("Invalid Browser Name!! Please select among chrome, edge, firefox or safari only");
        }
    }

    public BrowserUtility(Browser browserName, boolean isHeadless){

        extentLog(Status.INFO,"Launching Browser for " + browserName.toString()+" in headless mode "+ isHeadless);

        if(browserName == Browser.CHROME){
            if(isHeadless) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(chromeOptions));
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(EXPLICIT_WAIT_TIME));
            }
            else{
                driver.set(new ChromeDriver());
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(EXPLICIT_WAIT_TIME));
            }
        }
        else if(browserName == Browser.EDGE){
            if(isHeadless){
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless=new");
                edgeOptions.addArguments("--disable-gpu");
                driver.set(new EdgeDriver(edgeOptions));
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(EXPLICIT_WAIT_TIME));
            }
            else {
                driver.set(new EdgeDriver());
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(EXPLICIT_WAIT_TIME));
            }

        }
        else if(browserName == Browser.FIREFOX){
            if(isHeadless){
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless=new");
                firefoxOptions.addArguments("--disable-gpu");
                driver.set(new FirefoxDriver(firefoxOptions));
            }
            else {
                driver.set(new FirefoxDriver());
            }
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(EXPLICIT_WAIT_TIME));
        }
        else if(browserName == Browser.SAFARI){
            driver.set(new SafariDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(EXPLICIT_WAIT_TIME));
        }
        else{
            logger.error("Invalid Browser Name!! Please select among options Chrome, Edge, Firefox or Safari only");
            extentLog(Status.FAIL,"Invalid Browser Name!! Please select among chrome, edge, firefox or safari only");
            System.err.println("Invalid Browser Name!! Please select among Chrome, Edge, Firefox or Safari only");
        }
    }

    public void goToWebsite(String url){

        extentLog(Status.INFO,"Visiting the website "+ url);
        driver.get().get(url);
    }

    public void maximizeBrowser(){

        extentLog(Status.INFO,"Maximizing the browser window");
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator){
        extentLog(Status.INFO,"Finding Element with the locator "+  locator);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void enterText(By locator, String textToEnter ){

        extentLog(Status.INFO,"Finding Element with the locator "+  locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        extentLog(Status.INFO,"Element Found and now going to enter text "+ textToEnter);
        element.sendKeys(textToEnter);
    }

    public void enterSpecialKey(By locator, Keys keyToEnter ){

        extentLog(Status.INFO,"Finding Element with the locator "+  locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        extentLog(Status.INFO,"Element Found and now going to enter Special Key "+ keyToEnter);
        element.sendKeys(keyToEnter);
    }

    public String getVisibleText(By locator){
        extentLog(Status.INFO,"Finding Element with the locator "+  locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        extentLog(Status.INFO,"Element Found and now going to to return visible text  "+ element.getText());
        return element.getText();
    }

    public String takeScreenshot(String name){

        TakesScreenshot screenshot = (TakesScreenshot)driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String timeStamp = simpleDateFormat.format(date);
        String path = ".//screenshots//"+name+"-"+timeStamp+".png";
        File screenshotFile = new File(path);
        try {
            FileUtils.copyFile(screenshotData, screenshotFile);
            extentLog(Status.INFO,"screenshots taken and copied to path "+path);
        } catch (IOException e) {
            extentLog(Status.FAIL,"Failed to capture screenshots");
            throw new RuntimeException(e);
        }
        return path;
    }

    public void extentLog(Status status, String message) {
        logger.info(message);

        ExtentTest test = ExtentReportUtility.getTest();
        if (test != null) {
            test.log(status, message);
        }
    }

    public void quit() {
        driver.get().quit();
    }

}
