package tests;

import org.openqa.selenium.WebDriver;
import pages.CategoryPage;
import pages.DynamicContentPage;
import utils.ScreenShotsUtils;
import org.testng.annotations.Test;
import org.testng.Assert;

public class DynamicContentTest extends BaseTest {

    @Test
    public void filterChangesContentAndTakesScreenshots() throws InterruptedException {
        CategoryPage categoryPage = new CategoryPage(driver);
        DynamicContentPage dynamicContentPage = new DynamicContentPage(driver);

        categoryPage.selectCategoryByIndex(7);

        //screenshot before:
        utils.ScreenShotsUtils.takeScreenshot(driver,"before_filter");
        //filter:
        dynamicContentPage.clickFilter();
        //TO DO: replace with proper wait.
        Thread.sleep(2000);
        //screenshot after:
        utils.ScreenShotsUtils.takeScreenshot(driver,"after_filter");

    }
}
