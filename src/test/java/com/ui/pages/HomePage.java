package com.ui.pages;

import com.aventstack.extentreports.Status;
import com.constants.Browser;
import static com.constants.Env.*;

import com.constants.Env;
import com.utility.BrowserUtility;
import static com.utility.JSONUtility.readJSON;
import static com.utility.PropertiesUtil.readProperty;

import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class HomePage extends BrowserUtility {

    Logger logger = LoggerUtility.getLogger(this.getClass());
    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign')]");

    public HomePage(Browser browser,boolean isHeadless) {
        super(browser,isHeadless);
        goToWebsite(readProperty(QA,"url"));
        //goToWebsite(readJson(QA).getUrl());
        maximizeBrowser();
    }
    public HomePage(WebDriver driver) {
        super(driver);
        extentLog(Status.INFO,"Launching Browser on Environment and URL using json file" +readJSON(QA).getUrl());
        goToWebsite(readJSON(QA).getUrl());
    }

    public LoginPage goToLoginPage(){


        clickOn(SIGN_IN_LINK_LOCATOR);
        return new LoginPage(getDriver());
    }
}
