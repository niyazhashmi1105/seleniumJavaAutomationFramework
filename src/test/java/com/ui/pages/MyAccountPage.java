package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public final class MyAccountPage extends BrowserUtility {

    private static final By USER_NAME_LOCATOR = By.xpath("//a[@title='View my customer account']/span");
    private static final By SEARCH_TXT_BOX_LOCATOR = By.id("search_query_top");

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName(){
        return getVisibleText(USER_NAME_LOCATOR);
    }

    public SearchResultPage searchProduct(String productName){
        enterText(SEARCH_TXT_BOX_LOCATOR,productName);
        enterSpecialKey(SEARCH_TXT_BOX_LOCATOR, Keys.ENTER);
        return new SearchResultPage(getDriver());
    }

}
