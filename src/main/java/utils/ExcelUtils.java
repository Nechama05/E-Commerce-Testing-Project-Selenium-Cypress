package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static class CartItem {
        public String name;
        public int quantity;
        public String pricePerItem;
        public String totalPrice;
        public String status;

        public CartItem(String name, int quantity, String pricePerItem, String totalPrice, String status) {
            this.name = name;
            this.quantity = quantity;
            this.pricePerItem = pricePerItem;
            this.totalPrice = totalPrice;
            this.status = status;
        }
        @Override
        public String toString() {
            return name + " | Qty: " + quantity + " | Price: " + pricePerItem + " | Total: " + totalPrice + " | Status: " + status;
        }
    }
    public static List<CartItem> extractCartItems(List<WebElement> itemElements, WebDriver driver) {
        List<CartItem> items = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int i = 0; i < itemElements.size(); i++) {
            WebElement item = itemElements.get(i);
            try {
                System.out.println("\n--- Processing item " + (i + 1) + " ---");
                System.out.println("Item outerHTML: " + item.getAttribute("outerHTML"));

                // לחכות לכל אלמנט בנפרד
                WebElement nameElement = wait.until(ExpectedConditions.visibilityOf(
                        item.findElement(By.cssSelector("[data-testid='item-description']"))
                ));
                String name = nameElement.getText().trim();
                System.out.println("Name: " + name);

                WebElement quantityElement = wait.until(ExpectedConditions.visibilityOf(
                        item.findElement(By.cssSelector("[data-testid='item-changeQuantity'] span"))
                ));
                String quantityText = quantityElement.getText().trim();
                System.out.println("Quantity text: " + quantityText);
                int quantity = Integer.parseInt(quantityText);

                WebElement priceElement = wait.until(ExpectedConditions.visibilityOf(
                        item.findElement(By.cssSelector("div[data-component='PriceDetailed'] span"))
                ));
                // מחיר סופי עבור כל הפריט (כמות X מחיר ליחידה)
                WebElement totalElement = wait.until(ExpectedConditions.visibilityOf(
                        item.findElement(By.cssSelector("p[data-component='Price']"))
                ));

                String totalText = totalElement.getText().replaceAll("[^\\d.]", "");
                double total = Double.parseDouble(totalText);


                items.add(new CartItem(
                        name,
                        quantity,
                        totalText,
                        "$" + String.format("%,.2f", total),
                        "PASS"
                ));

            } catch (Exception e) {
                System.out.println("Warning: Could not read item details. Skipping...");
                e.printStackTrace(); // מדפיס את השגיאה המדויקת
            }
        }

        return items;
    }

    // פונקציה שמקבלת את ה-WebElements מהעגלה וממירה ל-CartItem
//    public static List<CartItem> extractCartItems(List<WebElement> itemElements) {
//        List<CartItem> items = new ArrayList<>();
//
//        for (WebElement item : itemElements) {
//            try {
//
//                // שם
//                String name = item.findElement(By.cssSelector(
//                                "[data-testid='item-description']"))
//                        .getText().trim();
//
//                // כמות
//                String quantityText = item.findElement(
//                                By.cssSelector("[data-testid='item-changeQuantity'] span"))
//                        .getText().trim();
//
//                int quantity = Integer.parseInt(quantityText);
//
//                // מחיר
//                String pricePerItemText = item.findElement(
//                                By.cssSelector("div[data-component='PriceDetailed'] span"))
//                        .getText().trim();
//
//                double pricePerItem = Double.parseDouble(
//                        pricePerItemText.replace("$", "").replace(",", ""));
//
//                double total = pricePerItem * quantity;
//
//                items.add(new CartItem(
//                        name,
//                        quantity,
//                        pricePerItemText,
//                        "$" + String.format("%,.2f", total),
//                        "PASS"
//                ));
//
//            } catch (Exception e) {
//                System.out.println("Warning: Could not read item details. Skipping...");
//            }
//        }
//
//        return items;
//    }
//

    // פונקציה לכתיבת האקסל
    public static void writeCartToExcel(List<CartItem> items, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Shopping Cart");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Item Name");
        header.createCell(1).setCellValue("Quantity");
        header.createCell(2).setCellValue("Price per Item");
        header.createCell(3).setCellValue("Total Price");
        header.createCell(4).setCellValue("Status");

        int rowNum = 1;
        for (CartItem item : items) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.name);
            row.createCell(1).setCellValue(item.quantity);
            row.createCell(2).setCellValue(item.pricePerItem);
            row.createCell(3).setCellValue(item.totalPrice);
            row.createCell(4).setCellValue(item.status);
        }

        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Excel file written successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
