package com.salesforce.testngtests;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {

	public static String[][] readData(String strFile) throws IOException {
//		String fileName = "./data/sfdata1.xlsx";
		XSSFWorkbook wb = new XSSFWorkbook(strFile);
		XSSFSheet ws = wb.getSheet("Sheet1");
		
		int lastRow = ws.getLastRowNum();
//		System.out.println("Last row number is: "+ lastRow);
//		int rowCount =ws.getPhysicalNumberOfRows();
//		System.out.println("Physical row count is: "+rowCount);
		int colCount = ws.getRow(0).getLastCellNum();
//		System.out.println("Column count is: "+ colCount);

		DataFormatter dFormatter = new DataFormatter();
		
		String[][] data = new String[lastRow][colCount]; 

		for(int i=1; i<=lastRow; i++) {
			for (int j = 0; j < colCount; j++) {
				Cell cell = ws.getRow(i).getCell(j);
				String cellValue = dFormatter.formatCellValue(cell);
//				System.out.println("R" + i + "C" + j + ": " + cellValue);
				data[i-1][j] = cellValue;
			}
		}
		
		wb.close();
		return data;
//		for(int row=0; row< data.length; row++) {
//			for(int col=0; col < data[row].length; col++) {
//				System.out.println("data["+row+"]["+col+"]= "+data[row][col]);
//			}			
//		}
	}

}
