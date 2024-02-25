package org.qa.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.qa.base.TestBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Utility extends TestBase {
    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 10;
    public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\org\\qa\\testdata\\McPizzaData.xlsx";
    static Workbook book;
    static Sheet sheet;
    static JavascriptExecutor js;

    public static Object[][] getTestData(String sheetName) {
        log.info("Reaching '" + sheetName + "' sheet from file : " + TESTDATA_SHEET_PATH);

        FileInputStream file = null;
        try {
            file = new FileInputStream(TESTDATA_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
            }
        }
        return data;
    }

    public static void switchBackFromIFrame() {
        driver.switchTo().defaultContent();
        log.info("Switched back to default content.");
    }

    public static void switchToFrameByIdNameIndex(Object obj) throws Exception {
        if (obj.getClass().getName().contains("String")) {
            String nameId = (String) obj;
            log.info("Switching to frame with Id/name : " + nameId);

            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            String currentFrame = (String) jsExecutor.executeScript("return self.name");
            if (!currentFrame.equals(nameId)) {
                driver.switchTo().frame(nameId);
            }
        } else if (obj.getClass().getName().contains("Integer")) {
            int index = (Integer) obj;
            log.info("Switching to frame with index : " + index);
            driver.switchTo().frame(index);
        } else {
            log.error("Unsupported identified for switching to frame");
            throw new Exception("Unsupported identified for switching to frame");
        }
    }
}
