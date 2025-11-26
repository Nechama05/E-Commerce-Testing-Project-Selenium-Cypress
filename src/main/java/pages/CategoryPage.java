package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryPage extends BasePage {

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public void selectCategoryByIndex(int index) {
        String selector = String.format("ul[data-component='NavBarList'] > li:nth-child(%d)", index);
        WebElement category = driver.findElement(By.cssSelector(selector));
        click(category);
    }
}
