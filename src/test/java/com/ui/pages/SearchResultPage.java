package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends BrowserUtility {


    private static final By PRODUCT_LISTING_TITLE_LOCATOR = By.xpath("//span[@class='lighter']");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getSearchResultProductTitle(){
        return getVisibleText(PRODUCT_LISTING_TITLE_LOCATOR);
    }
}
