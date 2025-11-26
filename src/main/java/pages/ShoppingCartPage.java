package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage extends pages.BasePage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public void goToBag() {
        WebElement navigateToCart = driver.findElement(By.cssSelector("[data-testid='bag']"));
        click(navigateToCart);
    }

    public int getItemsCount() {
        List<WebElement> items = driver.findElements(By.cssSelector("li[data-testid='item']"));
        int totalCount = 0;

        for (WebElement item : items) {
            try {
                WebElement quantityElement = item.findElement(By.cssSelector("span[data-testid='item-quantity']"));
                String quantityText = quantityElement.getText().trim();

                // המרה למספר, מתעלמים אם לא מצליחים
                int quantity = Integer.parseInt(quantityText);
                totalCount += quantity;

            } catch (Exception e) {
                System.out.println("Warning: Could not read quantity for an item. Counting as 1.");
                totalCount += 1; // ברירת מחדל אם אין כמות
            }
        }

        return totalCount;
    }


    public String getTotalPrice() {
        WebElement total = driver.findElement(By.cssSelector("span[data-testid=\"lineItem-Subtotal-value\"]"));
        return getText(total);
    }

    public void increaseQuantity() {
        try {
            //TO DO: change to a proper wait!!!!!!!!!
            Thread.sleep(10000); // 3 שניות
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement quantityDropdown = driver.findElement(By.cssSelector("button[data-testid='item-quantity-actionButton']"));
        click(quantityDropdown);
        WebElement downDrop = driver.findElement(By.cssSelector("._f42980._e7472d"));
        click(downDrop);
        WebElement option = driver.findElement(By.cssSelector("div[data-testid=\"option-2\"]"));
        click(option);
    }
}
