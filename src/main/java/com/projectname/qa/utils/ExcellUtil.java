/**
 * 
 */
package com.projectname.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 */
public class ExcellUtil {
	
	private static final String TEST_DATA_SHEET_PATH = "./src/main/java/com/projectname/qa/testdata/testdata.xlsx";

	public static Object[][] getTestDataFromExcel(String sheetName) {
		File excelFile = new File(TEST_DATA_SHEET_PATH);
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fis = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				XSSFCell cell = sheet.getRow(i + 1).getCell(j);
				data[i][j] = cell.toString();
			}
		}
		return data;
	}
}