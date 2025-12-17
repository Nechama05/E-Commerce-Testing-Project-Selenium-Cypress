package tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FormPage;

import java.time.Duration;

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
    @Test(priority = 4)
    public void testInvalidTypeOfText() {
        FormPage formPage = new FormPage(driver);
        formPage.open();

        // מנסה להכניס טקסט לשדה ZIP
        formPage.zipCode("Testing123");

        // בדיקה – הערך בשדה צריך להיות רק המספרים או ריק
        String zipValue = formPage.getZipCodeValue();
        Assert.assertFalse(zipValue.matches(".*[a-zA-Z]+.*"),
                "Zip code field should not accept letters, but it did: " + zipValue);
    }


    @Test(priority = 5)
    public void testLimitOfText() {
        FormPage formPage = new FormPage(driver);
        formPage.open();

        // מנסה להכניס טקסט ארוך מאוד
        String longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ".repeat(20);
        formPage.address(longText);

        // בודק שהטקסט בשדה כתובת מוגבל (למשל 100 תווים)
        String addressValue = formPage.getAddressValue();
        int maxLength = 38; // החליפי לפי הגבלה אמיתית
        Assert.assertTrue(addressValue.length() <= maxLength,
                "Address field exceeded maximum allowed length!");
    }

}
