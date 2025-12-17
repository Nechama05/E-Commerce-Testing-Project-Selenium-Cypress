package pages;

import org.openqa.selenium.*;

public class FormPage extends BasePage {

    public FormPage(WebDriver driver) {
        super(driver);
    }

    protected String BaseUrl = "https://tools.usps.com/zip-code-lookup.htm?byaddress";

    // פתיחת הדף
    public void open() {
        driver.get(BaseUrl);
    }

    // מילוי שדות עם שימוש בפונקציה type מהבייס
    public void companyName(String companyName) {
        WebElement el = driver.findElement(By.cssSelector("#tCompany"));
        type(el, companyName);
    }

    public void address(String address) {
        WebElement el = driver.findElement(By.cssSelector("#tAddress"));
        type(el, address);
    }

    public void apt(String apt) {
        WebElement el = driver.findElement(By.cssSelector("#tApt"));
        type(el, apt);
    }

    public void city(String city) {
        WebElement el = driver.findElement(By.cssSelector("#tCity"));
        type(el, city);
    }

    public void state(String index) {
        WebElement el = driver.findElement(By.cssSelector("#tState"));
        click(el);
        WebElement option = driver.findElement(By.cssSelector("#tState > option:nth-child(" + index + ")"));
        click(option);
    }

    public void zipCode(String zipCode) {
        WebElement el = driver.findElement(By.cssSelector("#tZip-byaddress"));
        type(el, zipCode);
    }

    public void findZipCode() {
        WebElement el = driver.findElement(By.cssSelector("#zip-by-address"));
        click(el);
    }

    // בדיקת שגיאות עם wait מהבייס
    public boolean isRequiredFieldErrorDisplayed() {
        try {
            WebElement el = wait.until(driver ->
                    driver.findElement(By.cssSelector(
                            "#zip-code-address-form > div > div > div > div:nth-child(6) > div.form-group.col-md-6.col-sm-6.col-xs-12.required-field.has-error.has-danger > div.help-block.with-errors"
                    ))
            );
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public String getZipCodeValue() {
        return driver.findElement(By.cssSelector("#tZip-byaddress")).getAttribute("value");
    }

    public boolean isInvalidAddressErrorDisplayed() {
        try {
            WebElement el = wait.until(driver ->
                    driver.findElement(By.cssSelector(
                            "#zip-code-address-form > div > div > div > div:nth-child(7) > div:nth-child(1) > div.server-error.address-tCity.help-block"
                    ))
            );
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getAddressValue() {
        return driver.findElement(By.cssSelector("#tAddress")).getAttribute("value");

    }
}
