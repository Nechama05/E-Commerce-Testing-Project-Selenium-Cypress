
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.lang.constant.DynamicCallSiteDesc;
import java.time.Duration;

public class DynamicContentPage extends pages.BasePage {

    public DynamicContentPage(WebDriver driver) {super(driver);}

    public void clickFilter() {
//        WebElement filterButton = driver.findElement(By.cssSelector("[data-testid=\"filter-button\"]"));
//        filterButton.click();
//        WebElement filterCategory = driver.findElement(By.cssSelector("div[data-testid=\"quick-filters-scroller\"]>a"));
//        click(filterCategory);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // חכי עד שהכפתור עצמו יהיה קליקבילי
        By filterButtonBy = By.cssSelector("[data-testid=\"filter-button\"]");
        WebElement filterButton = wait.until(ExpectedConditions.elementToBeClickable(filterButtonBy));
        filterButton.click();

        // המתן שהקונטיינר של הפילטרים ייטען
        By quickFiltersContainer = By.cssSelector("div[data-selector='catalog-quick-filters']");
        wait.until(ExpectedConditions.presenceOfElementLocated(quickFiltersContainer));

        // ה־a הראשון בתוך הקונטיינר
        By firstFilterBy = By.cssSelector("div[data-selector='catalog-quick-filters'] a:first-of-type");

        WebElement firstFilter = wait.until(ExpectedConditions.elementToBeClickable(firstFilterBy));
        firstFilter.click();
    }

}
