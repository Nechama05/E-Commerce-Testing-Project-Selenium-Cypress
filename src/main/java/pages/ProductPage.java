package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectProduct() {
        String selector = String.format("#catalog-grid a:first-of-type\n");
        WebElement product = driver.findElement(By.cssSelector(selector));
        click(product);
    }

    public void chooseSizeIfAvailable() {
        try {

            WebElement sizeDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-component='SizeSelectorLabel']"))
            );
            sizeDropdown.click();
            WebElement firstSize = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("li[data-component='SizeSelectorOption']"))
            );
            firstSize.click();

        } catch (Exception e) {
            System.out.println("No size selection required.");
        }
    }

    public void addToCart() {
        try {
            // מחכים שהכפתור יהיה לחיץ
            WebElement addToBag = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid='addToBag']"))
            );
            addToBag.click();

            // מחכים שהכפתור יהפוך שוב ללחיץ (כשהפריט נוסף לעגלה)
            wait.until(ExpectedConditions.elementToBeClickable(addToBag));

        } catch (Exception e) {
            System.out.println("Add to Bag failed: " + e.getMessage());
        }
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

// מחכה שהכפתור יהיה קליקאבל
            WebElement addToBag = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[data-testid='addToBag']")
            ));
            addToBag.click();

// מחכה עד שהכפתור השתנה למצב בלתי קליקאבל או שווי (וי)
            wait.until(driver -> {
                WebElement button = driver.findElement(By.cssSelector("button[data-testid='addToBag']"));
                return !button.isEnabled(); // או בדיקה על class/attribute שמתעדכן
            });

        } catch (Exception e) {
            System.out.println("Failed to add item to cart: " + e.getMessage());
        }

    }



    public String getProductPrice() {
        WebElement priceElement = driver.findElement(
                By.cssSelector("[data-component='PriceLarge']")
        );
        return getText(priceElement);
    }

}
