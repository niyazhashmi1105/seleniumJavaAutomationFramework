package com.ui.tests;

import com.ui.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.constants.Size.L;

public class ProductCheckoutTest extends TestBase {

    private static final String SEARCH_TERM = "Printed Summer dress";

    private SearchResultPage searchResultPage;

    @BeforeMethod (description = "User Logs into the application and searches for a product")
    public void setup() {
        searchResultPage = homePage.goToLoginPage().doLoginWith("niyaz.hashmi@rediffmail.com", "P@ssw0rd")
                .searchProduct(SEARCH_TERM);
    }

    @Test (description = "Verify if the logged in User is able to buy a dress", groups = {"e2e", "smoke", "sanity"})
    public void checkoutTest() {
        String result = searchResultPage.clickOnTheProductAtIndex(2).changeSize(L).addProductToCart()
                .proceedToCheckout().goToConfirmAddressPage().goToShippmentPage().goToPaymentPage().makePaymentByWire();

        Assert.assertTrue(result.contains("complete"));
    }
}