package com.salesforce.utilities.classes;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtility {
	
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	
	public static void setExcelFile(String path, String sheetName) throws IOException {
		try {
			// Open Excel
			FileInputStream ExcelFile = new FileInputStream(path);
			
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet= ExcelWBook.getSheet(sheetName);
		} catch (IOException e) {
			throw(e);
		}
	}
	
	public static String[][] getTestData(String tableName) throws IOException {
//		XSSFWorkbook wb = new XSSFWorkbook(strFile);
//		XSSFSheet ws = wb.getSheet(strSheet);
		String[][] testData = null;
		try {
			DataFormatter dFormatter = new DataFormatter();
			XSSFCell[] boundaryCells = findBoundaryCells(tableName);
			XSSFCell	startCell = boundaryCells[0];
			XSSFCell	endCell = boundaryCells[1];
			
			int startRow = startCell.getRowIndex() + 1;
			int endRow = endCell.getRowIndex() - 1;
			int startCol = startCell.getColumnIndex() + 1;
			int endCol	= endCell.getColumnIndex() - 1;
			
			testData = new String[endRow - startRow + 1][endCol - startCol + 1]; 
			
			for(int i=startRow; i<endRow; i++) {
				for (int j = startCol; j < endCol; j++) {
					Cell cell = ExcelWSheet.getRow(i).getCell(j);
//					String cellValue = dFormatter.formatCellValue(cell);
					testData[i-startRow][j - startCol] = dFormatter.formatCellValue(cell);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
//		wb.close();
		return testData;
	}
	
	public static XSSFCell[] findBoundaryCells(String tableName) {
		DataFormatter formatter = new DataFormatter();
		String cellKey = "beginTable";
		XSSFCell[] boundaryCells = new XSSFCell[2];
		
		for (Row row: ExcelWSheet) {
			for(Cell cell : row) {
				if(tableName.equals(formatter.formatCellValue(cell))) {
					if(cellKey.equalsIgnoreCase("beginTable")) {
						boundaryCells[0] = (XSSFCell) cell;
					} else {
						boundaryCells[1] = (XSSFCell) cell;
					}
				}
			}
		}
		return boundaryCells;
	}
}
