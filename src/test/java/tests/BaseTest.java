package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.time.Duration;


public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl = "https://www.farfetch.com/";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(baseUrl);

        try {
            WebElement closeBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("#summary-desktop-close"))
            );
            closeBtn.click();
        } catch (Exception e) {
            System.out.println("No popup to close.");
        }

        WebElement department = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("div[data-testid=\"editorial-post-list\"] article a")
                )
        );
        department.click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
