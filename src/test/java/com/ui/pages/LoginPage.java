package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class LoginPage extends BrowserUtility {


    private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
    private static final By PASSWORD_TEXTBOX_LOCATOR = By.id("passwd");
    private static final By SUBMIT_BUTTON_LOCATOR = By.id("SubmitLogin");
    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//div[contains(@class,'alert-danger')]/ol/li");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MyAccountPage doLoginWith(String emailAddress, String password){
        enterText(EMAIL_TEXT_BOX_LOCATOR,emailAddress);
        enterText(PASSWORD_TEXTBOX_LOCATOR,password);
        clickOn(SUBMIT_BUTTON_LOCATOR);
        return new MyAccountPage(getDriver());

    }

    public LoginPage doLoginWithInvalidCredentials(String emailAddress, String password){
        enterText(EMAIL_TEXT_BOX_LOCATOR,emailAddress);
        enterText(PASSWORD_TEXTBOX_LOCATOR,password);
        clickOn(SUBMIT_BUTTON_LOCATOR);
        return new LoginPage(getDriver());
    }

    public String getErrorMessage(){
        return getVisibleText(ERROR_MESSAGE_LOCATOR);
    }
}
