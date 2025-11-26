package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormPage extends BasePage {
    public FormPage(WebDriver driver) { super(driver); }

    protected String BaseUrl = "https://tools.usps.com/zip-code-lookup.htm?byaddress";

    // לשגיאת שדה חובה
    public boolean isRequiredFieldErrorDisplayed() {
        try {
            WebElement el = driver.findElement(By.cssSelector("div.server-error.help-block"));
            return el.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // לשגיאת כתובת/עיר לא קיימת
    public boolean isInvalidAddressErrorDisplayed() {
        try {
            WebElement el = driver.findElement(By.cssSelector("div.server-error.address-tCity.help-block"));
            return el.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void open() {
        driver.get(BaseUrl);
    }

    public void companyName(String companyName) {
        WebElement companyField = driver.findElement(By.cssSelector("#tCompany"));
        companyField.sendKeys(companyName);
    }

    public void address(String address) {
        WebElement addressField = driver.findElement(By.cssSelector("#tAddress"));
        addressField.sendKeys(address);
    }

    public void apt(String apt) {
        WebElement aptField = driver.findElement(By.cssSelector("#tApt"));
        aptField.sendKeys(apt);
    }

    public void city(String city) {
        WebElement cityField = driver.findElement(By.cssSelector("#tCity"));
        cityField.sendKeys(city);
    }

    public void state(String index) {
        WebElement stateDropDown = driver.findElement(By.cssSelector("#tState"));
        stateDropDown.click();
        WebElement stateField = driver.findElement(By.cssSelector("#tState > option:nth-child(" + index + ")"));
        stateField.click();
    }

    public void zipCode(String zipCode) {
        WebElement zipCodeField = driver.findElement(By.cssSelector("#tZip-byaddress"));
        zipCodeField.sendKeys(zipCode);
    }

    public void findZipCode() {
        WebElement findZipCode = driver.findElement(By.cssSelector("#zip-by-address"));
        findZipCode.click();
    }
}
