package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicContentPage extends BasePage {

    public DynamicContentPage(WebDriver driver) {
        super(driver);
    }

    // פונקציה לביצוע חיפוש וגלילה תוך שימוש ב־wait מה־BasePage
    public void searchAndScroll(String keyword) {
        try {
            // מחכים שהשדה יהיה גלוי
            WebElement searchInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#search"))
            );
            searchInput.clear();
            searchInput.sendKeys(keyword);
            searchInput.submit();

            // גלילה כדי שהתיקים ייראו
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,300)");

            // מחכים שהעמוד יתעדכן (אפשר להחליף ל־Explicit Wait על אלמנט מסוים אם רוצים)
            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("Search failed: " + e.getMessage());
        }
    }
}
