package com.utility;

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
import org.openqa.selenium.safari.SafariOptions;
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

    public WebDriver getDriver(){
        return driver.get();
    }

    public BrowserUtility(WebDriver driver){
        super();
        this.driver.set(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
    }

    public BrowserUtility(Browser browserName){
        logger.info("Launching Browser for " + browserName);

        if(browserName == Browser.CHROME){
            driver.set(new ChromeDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
        }
        else if(browserName == Browser.EDGE){
            driver.set(new EdgeDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
        }
        else if(browserName == Browser.FIREFOX){
            driver.set(new FirefoxDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
        }
        else if(browserName == Browser.SAFARI){
            driver.set(new SafariDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
        }
        else{
            logger.error("Invalid Browser Name!! Please select among chrome, edge, firefox or safari only");
            System.err.println("Invalid Browser Name!! Please select among chrome, edge, firefox or safari only");
        }
    }

    public BrowserUtility(Browser browserName, boolean isHeadless){
        logger.info("Launching Browser for " + browserName);

        if(browserName == Browser.CHROME){
            if(isHeadless) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(chromeOptions));
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
            }
            else{
                driver.set(new ChromeDriver());
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
            }
        }
        else if(browserName == Browser.EDGE){
            if(isHeadless){
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless=new");
                edgeOptions.addArguments("--disable-gpu");
                driver.set(new EdgeDriver(edgeOptions));
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
            }
            else {
                driver.set(new EdgeDriver());
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
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
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
        }
        else if(browserName == Browser.SAFARI){
            driver.set(new SafariDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
        }
        else{
            logger.error("Invalid Browser Name!! Please select among options Chrome, Edge, Firefox or Safari only");
            System.err.println("Invalid Browser Name!! Please select among Chrome, Edge, Firefox or Safari only");
        }
    }

    public void goToWebsite(String url){
        logger.info("Visiting the website {}", url);
        driver.get().get(url);
    }

    public void maximizeBrowser(){
        logger.info("Maximizing the browser window");
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator){
        logger.info("Finding Element with the locator {}",  locator);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void enterText(By locator, String textToEnter ){
        logger.info("Finding Element with the locator {}",  locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(textToEnter);
    }

    public String getVisibleText(By locator){
        logger.info("Finding Element with the locator {}",  locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    public String takeScreenshot(String name){

        TakesScreenshot screenshot = (TakesScreenshot)driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String timeStamp = simpleDateFormat.format(date);
        String path = System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+name+" - "+timeStamp+".png";
        File screenshotFile = new File(path);
        try {
            FileUtils.copyFile(screenshotData, screenshotFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "screenshots"+File.separator+name+" - "+timeStamp+".png";
    }

    public void quit() {
        driver.get().quit();
    }

}
