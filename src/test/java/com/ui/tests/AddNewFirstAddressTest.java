package com.ui.tests;

import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPojo;
import com.utility.FakerAddressUtility;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewFirstAddressTest extends TestBase{

    private MyAccountPage myAccountPage;
    private AddressPojo address;

    @BeforeMethod(description = "Verify user log into the application")
    public void setUp(){
        myAccountPage = homePage.goToLoginPage().doLoginWith("niyaz.hashmi@rediffmail.com","P@ssw0rd");
        address = FakerAddressUtility.getFakerAddress();
    }

    @Test
    public void addNewFirstAddress(){
        String actualText = myAccountPage.goToAddressPage().saveAddress(address);
        Assert.assertEquals(actualText,address.getAddressTitle().toUpperCase());
    }
}
