package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.DynamicContentPage;
import utils.ScreenShotsUtils;

import java.time.Duration;

public class DynamicContentTest extends BaseTest {

    @Test
    public void searchAndTakeScreenshots() {
        CategoryPage categoryPage = new CategoryPage(driver);
        DynamicContentPage dynamicContentPage = new DynamicContentPage(driver);

        // ניווט לקטגוריית תיקים
        categoryPage.selectCategoryByIndex(7);

        // גלילה קצת למטה כדי שהתיקים ייראו
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");

        // צילום מסך לפני החיפוש
        ScreenShotsUtils.takeScreenshot(driver, "before_search");

        // בצע חיפוש באמצעות הפונקציה ב-DynamicContentPage
        dynamicContentPage.searchAndScroll("gucci");

        // צילום מסך אחרי החיפוש
        ScreenShotsUtils.takeScreenshot(driver, "after_search");
    }
}
