package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FormPage;

public class FormTest extends BaseTest {

    @Test(priority = 1)
    public void testIfErrorAppearsWhenSkippingRequiredField() {
        FormPage formPage = new FormPage(driver);
        formPage.open();
        formPage.findZipCode();
        Assert.assertTrue(formPage.isRequiredFieldErrorDisplayed(),
                "Expected required-field error but none appeared.");
    }

    @Test(priority = 2)
    public void testIfErrorAppearsWhenAddressIsInvalid() {
        FormPage formPage = new FormPage(driver);
        formPage.open();

        formPage.address("Fake address!!!");
        formPage.city("Nowhere");
        formPage.state("5");
        formPage.zipCode("123456");
        formPage.findZipCode();

        Assert.assertTrue(formPage.isInvalidAddressErrorDisplayed(),
                "Expected invalid-address error but didn't get one.");
    }

    @Test(priority = 3)
    public void testValidAddressDoesNotShowError() {
        FormPage formPage = new FormPage(driver);
        formPage.open();

        formPage.address("1600 Pennsylvania Ave");
        formPage.city("Washington");
        formPage.state("10");
        formPage.zipCode("20500");
        formPage.findZipCode();

        Assert.assertFalse(formPage.isRequiredFieldErrorDisplayed(),
                "Required-field error appeared even though address is valid.");
        Assert.assertFalse(formPage.isInvalidAddressErrorDisplayed(),
                "Invalid-address error appeared even though address is valid.");
    }
}
