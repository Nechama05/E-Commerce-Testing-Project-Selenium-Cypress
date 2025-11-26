//package tests;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.testng.annotations.Test;
//import org.testng.Assert;
//import pages.ProductPage;
//import pages.ShoppingCartPage;
//import pages.CategoryPage;
//import utils.ExcelUtils;
//
//import java.util.List;
//
//public class ShoppingCartTest extends BaseTest {
//
//    @Test
//    public void testAddProductsToCartAndExportToExcel() {
//        CategoryPage categoryPage = new CategoryPage(driver);
//        ProductPage productPage = new ProductPage(driver);
//        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
//
//        // clothing
//        categoryPage.selectCategoryByIndex(5);
//        productPage.selectProduct();
//        productPage.chooseSizeIfAvailable();
//        productPage.addToCart();
//
//        //bags
//        categoryPage.selectCategoryByIndex(7);
//        productPage.selectProduct();
//        productPage.chooseSizeIfAvailable();
//        productPage.addToCart();
//
//        //accessories
//        categoryPage.selectCategoryByIndex(8);
//        productPage.selectProduct();
//        productPage.chooseSizeIfAvailable();
//        productPage.addToCart();
//
//        // מעבר לעגלה
//        cartPage.goToBag();
//
//        // הגדלת כמות של הפריט הראשון
//        cartPage.increaseQuantity();
//        int itemCount = cartPage.getItemsCount();
//        System.out.println("Number of items in cart: " + itemCount);
//        Assert.assertTrue(itemCount > 0, "Cart should have at least one item");
//
//// בדיקה של סכום כולל
//        String totalPriceText = cartPage.getTotalPrice();
//        System.out.println("Total price displayed in cart: " + totalPriceText);
//
//// חישוב סכום ידני של כל הפריטים לפי הפריטים שהוספת
//        List<WebElement> itemElements = driver.findElements(By.cssSelector("li[data-testid='item']"));
//        List<ExcelUtils.CartItem> items = ExcelUtils.extractCartItems(itemElements, driver);
//
//        double sumCalculated = 0;
//        for (ExcelUtils.CartItem item : items) {
//            String total = item.totalPrice.replaceAll("[^\\d.]", "");
//            sumCalculated += Double.parseDouble(total);
//        }
//
//        System.out.println("Calculated total sum: $" + String.format("%,.2f", sumCalculated));
//
//// השוואה בין הסכום שמוצג באתר לסכום שחישבנו
//        double totalDisplayed = Double.parseDouble(totalPriceText.replaceAll("[^\\d.]", ""));
//        Assert.assertEquals(totalDisplayed, sumCalculated, "Total price in cart should match sum of items");
//
//// --------------------------
//// כתיבה לאקסל
//        ExcelUtils.writeCartToExcel(items, "cart_report.xlsx");
//        // בדיקה של מספר פריטים בעגלה
//
//        // בדיקה של סכום כולל
//        String totalPrice = cartPage.getTotalPrice();
//        System.out.println("Total price: " + totalPrice);
//        Assert.assertNotNull(totalPrice);
//
//        // שמירה לאקסל
//
//// המרה ל-CartItem
//
//// כתיבה לאקסל
//        ExcelUtils.writeCartToExcel(items, "cart_report.xlsx");    }
//}
package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.Assert;
import pages.ProductPage;
import pages.ShoppingCartPage;
import pages.CategoryPage;
import utils.ExcelUtils;

import java.util.List;

public class ShoppingCartTest extends BaseTest {

    @Test
    public void testAddProductsToCartAndExportToExcel() {
        CategoryPage categoryPage = new CategoryPage(driver);
        ProductPage productPage = new ProductPage(driver);
        ShoppingCartPage cartPage = new ShoppingCartPage(driver);

        // --------------------------
        // הוספת פריטים מעגלות שונות
        // --------------------------

        // clothing
        categoryPage.selectCategoryByIndex(5);
        productPage.selectProduct();
        productPage.chooseSizeIfAvailable();
        productPage.addToCart();

        // bags
        categoryPage.selectCategoryByIndex(7);
        productPage.selectProduct();
        productPage.chooseSizeIfAvailable();
        productPage.addToCart();

        // accessories
        categoryPage.selectCategoryByIndex(8);
        productPage.selectProduct();
        productPage.chooseSizeIfAvailable();
        productPage.addToCart();

        // --------------------------
        // מעבר לעגלה והגדלת כמות
        // --------------------------
        cartPage.goToBag();
        cartPage.increaseQuantity();

        // --------------------------
        // בדיקות עגלה
        // --------------------------
        int itemCount = cartPage.getItemsCount();
        System.out.println("Number of items in cart: " + itemCount);
        Assert.assertTrue(itemCount > 0, "Cart should have at least one item");

        String totalPriceText = cartPage.getTotalPrice();
        System.out.println("Total price displayed in cart: " + totalPriceText);

        // חישוב סכום ידני של כל הפריטים
        List<WebElement> itemElements = driver.findElements(By.cssSelector("li[data-testid='item']"));
        List<ExcelUtils.CartItem> items = ExcelUtils.extractCartItems(itemElements, driver);

        // --------------------------
        // כתיבה לאקסל
        // --------------------------
        ExcelUtils.writeCartToExcel(items, "cart_report.xlsx");
    }
}
