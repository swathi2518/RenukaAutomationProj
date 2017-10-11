package DataProvider;

/**
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;



/**
 * @author SMuppidi
 *
 */
public class ReadExcelData {
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	FileInputStream fis;
	File src;
//Constructor used to intiate the excel data while creating Readexceldata object
	
	public ReadExcelData(String Filename) {
		try {
			src = new File(Filename);
			fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			System.out.println("Excel is loaded");  
		} catch (FileNotFoundException e) {
			System.out.println("File not located");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Exception while loading excel sheet");
			System.out.println(e.getMessage());
		}
	}

	public String getStringData(String sheetName, int rowIndex, int columnIndex) {
		sheet1 = wb.getSheet(sheetName);
		XSSFCell value = (XSSFCell) sheet1.getRow(rowIndex)
				.getCell(columnIndex);

		DataFormatter formatter = new DataFormatter();
		// Cell cell = sheet1.getRow(rowIndex).getCell(1);
		String data = formatter.formatCellValue(value);
		return data;
	}

	public int getNumericalData(String sheetName, int rowIndex, int columnIndex) {
		// sheet1 = (int)wb.getSheet(sheetName);
		int data = (int) wb.getSheet(sheetName).getRow(rowIndex)
				.getCell(columnIndex).getNumericCellValue();
		return data;
	}

	public int getRowCount(String sheetName) {
		// System.out.println(sheetName);

		int row = wb.getSheet(sheetName).getLastRowNum();

		row = row + 1;
		return row;
	}

	public int getColCount(String sheetName) {
		int col = wb.getSheet(sheetName).getRow(1).getLastCellNum();
		return col;
	}

	public ArrayList<String> ReturnExcelRowandColDataAsArray(String SheetName) {

		ArrayList<String> AL = new ArrayList<String>();

		for (int row = 1; row < getRowCount(SheetName); row++) {

			for (int col = 0; col < getColCount(SheetName); col++) {

				AL.add(getStringData(SheetName, row, col));

				}

		}

		return AL;

	}

	public ArrayList<String> ReturnExcelRowAsArray(String SheetName, int rowno) {

		ArrayList<String> AL = new ArrayList<String>();

		for (int col = 0; col < getColCount(SheetName); col++) {

			AL.add(getStringData(SheetName, rowno, col));

			
		}

		return AL;

	}

	public ArrayList<String> ReturnExcelColAsArray(String SheetName,
			int columnno) {

		ArrayList<String> AL = new ArrayList<String>();

		for (int row = 1; row < getRowCount(SheetName); row++) {
			try {
				if (getStringData(SheetName, row, columnno) != null) {
					AL.add(getStringData(SheetName, row, columnno));
				}
			}

			catch (Exception e) {
				System.out.println("Null Data so skipping....");
			}

			// System.out.println(readexcel.getStringData("sheet1", row, col));
		}
		return AL;

	}
	
	
		
	public  Object[][] Getdata(String sheetName )
	{
				int Totalrows=getRowCount(sheetName);
				int Totalcols=getColCount(sheetName);
				
				System.out.println(Totalrows);
				System.out.println(Totalcols);
				
				Object[][] Locator = new Object[Totalrows-1][Totalcols];
				
					for(int i=1;i<Totalrows;i++)
					{
						
						for(int j=0;j<Totalcols;j++)
						{
							Locator[i-1][j]=getStringData(sheetName,i, j);
						}
						
					}
	
		
			return Locator;
}
}