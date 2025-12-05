package com.ui.tests;

import com.ui.pages.MyAccountPage;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.ui.listeners.TestListener.class})
public class SearchProductTest extends TestBase{

    private MyAccountPage myAccountPage;

    @BeforeMethod(description = "Verify user log into the application")
    public void setUp(){
        myAccountPage = homePage.goToLoginPage().doLoginWith("niyaz.hashmi@rediffmail.com","P@ssw0rd");
    }

    @Test(description = "Verify Logged in user able to search a product and verify search result product title",groups ={"smoke","sanity","e2e"})
    public void verifySearchProductTest(){
        String data = myAccountPage.searchProduct("Printed Summer Dress").getSearchResultProductTitle();
        System.out.println(data);

    }
}
