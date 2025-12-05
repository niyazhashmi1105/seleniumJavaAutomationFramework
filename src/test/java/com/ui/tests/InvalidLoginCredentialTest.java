package com.ui.tests;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;


@Listeners({com.ui.listeners.TestListener.class})
public class InvalidLoginCredentialTest extends TestBase{

    private static final String INVALID_EMAIL_ADDRESS = "niyaz@yopmail.com";
    private static final String INVALID_PASSWORD = "password";

    @Test(description = "Verifies with the invalid user is not able to login to the application",groups={"e2e","sanity","smoke"})
    public void loginTest() {
        assertEquals(homePage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS,INVALID_PASSWORD).getErrorMessage(),"Authentication failed.");
    }

}
