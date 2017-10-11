package TestScenarios;

import DataProvider.ReadExcelData;

public class Justtotest {
	
	public static void main(String[] args)
	{
		
	
	
	ReadExcelData readexcel = new ReadExcelData("./TestData/Locators.xlsx");
	
	readexcel.Getdata("Elements");
	
	
	
	}

}
