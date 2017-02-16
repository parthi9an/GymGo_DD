package dd_utils;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import dd_core.testCore;

public class testUtils extends testCore{
	
	
	// Check if test case is executable 
	public static boolean isExecutable(String testCaseId) throws IOException {
		excelRead=new ExcelReadWrite((System.getProperty("user.dir")+"\\src\\dd_properties\\GymGoTestData.xlsx"));
		XSSFSheet DataSheet = excelRead.Setsheet("GymGoTestData");
		for (int rowNum = 1; rowNum <= excelRead.getRowCount(DataSheet); rowNum++) {
			if (excelRead.readValue(DataSheet, rowNum, "TC_ID").equals(testCaseId)) {
				if (excelRead.readValue(DataSheet, rowNum, "RUN_MODE").equalsIgnoreCase("Y")) {
					return true;
				} // if
				else {
					return false;
				}
			} // if
		} // for
		return false;
	}// isExecutable

	
	// Check if test case is executable 
		public static int isExecutableReturnRowNum(String testCaseId) throws IOException  {
			excelRead=new ExcelReadWrite((System.getProperty("user.dir")+"\\src\\dd_properties\\GymGoTestData.xlsx"));
			XSSFSheet DataSheet = excelRead.Setsheet("GymGoTestData");
			for (int rowNum = 1; rowNum <= excelRead.getRowCount(DataSheet); rowNum++) {
				if (excelRead.readValue(DataSheet, rowNum, "TC_ID").equals(testCaseId)) {
					if (excelRead.readValue(DataSheet, rowNum, "RUN_MODE").equalsIgnoreCase("Y")) {
						return rowNum;
					} // if
					else {
						return 0;
					}
				} // if
			} // for
			return 0;
		
		}// isExecutable
		
		
	//Capture screenshot
	public static void captureScreenShot()
	{
	Calendar calender = new GregorianCalendar();
	int month=calender.get(Calendar.MONTH);
	
		
		
	}//captureScreenShot
		
} //testCode
