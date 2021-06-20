package com.salesforce.testngtests;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {

	public static String[][] readData(String strFile, String strSheet) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook(strFile);
		XSSFSheet ws = wb.getSheet(strSheet);
		
		int lastRow = ws.getLastRowNum();
		int colCount = ws.getRow(0).getLastCellNum();

		DataFormatter dFormatter = new DataFormatter();
		
		String[][] data = new String[lastRow][colCount]; 

		for(int i=1; i<=lastRow; i++) {
			for (int j = 0; j < colCount; j++) {
				Cell cell = ws.getRow(i).getCell(j);
				String cellValue = dFormatter.formatCellValue(cell);
				data[i-1][j] = cellValue;
			}
		}
		
		wb.close();
		return data;
	}

}
