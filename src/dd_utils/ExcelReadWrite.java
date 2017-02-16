package dd_utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWrite {

FileInputStream fis;	
XSSFWorkbook workbook;	
/// Constructor
	public ExcelReadWrite(String xlsPath) throws IOException
	{
		fis = new FileInputStream(xlsPath);
		workbook = new XSSFWorkbook(fis);
	}
	

	public XSSFSheet Setsheet(String sheetName)
	{
		XSSFSheet sheet = workbook.getSheet(sheetName);
		return sheet;
	}

	
	public int getRowCount(XSSFSheet sheet)
	{
		int lastRowNum  = sheet.getLastRowNum();
		return lastRowNum;
	}
	
	public int getColumnCount(XSSFSheet sheet,int rowIndex)
	{
		int lastColumn = sheet.getRow(rowIndex).getLastCellNum();
		
		//System.out.println("lastColumn in getColumnCount Method "+lastColumn);
		return lastColumn;
	}
	
	//New method just pass sheet name to get column count
	public int getColumnCount(XSSFSheet sheet)
	{
		int lastColumn = sheet.getRow(0).getLastCellNum();
		
		return lastColumn;
		
	}
	public String readValue(XSSFSheet sheet, int rowIndex, int cellNum)
	{
		XSSFCell cell = sheet.getRow(rowIndex).getCell(cellNum);

		String cellText = null;

		cellText = cell.getStringCellValue();
		return cellText;
	} //readValue
	
	public String readValue(XSSFSheet Sheet, int rowIndex, String colName)
	{
		int colIndex = 0; 
		for (int i =0; i<getColumnCount(Sheet, 0);i++)
		{
			if (Sheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
				colIndex=i;
		}
		return readValue(Sheet, rowIndex, colIndex);
	}
	
	public void writeCell(XSSFSheet Sheet, int rowIndex, int cellNum, String writeValue)
	{
		XSSFCell writeCell = Sheet.getRow(rowIndex).getCell(cellNum);
		if(writeCell == null)
		{
			writeCell=Sheet.getRow(rowIndex).createCell(cellNum);
		}
		writeCell.setCellValue(writeValue);
	}
	
	public void writeCell(XSSFSheet Sheet, int rowIndex, String colName, String writeValue)
	{
		int colIndex=0;
		for(int i=0;i<getColumnCount(Sheet, 0);i++)
		{
			if(Sheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
				colIndex=i;
		}
	writeCell(Sheet, rowIndex, colIndex, writeValue);
	}
	
	// save excel file
	public void saveExcel(String xlsPath) throws IOException
	{
		fis.close();
		FileOutputStream fos = new FileOutputStream(xlsPath);
		workbook.write(fos);
		fos.close();
	}
	
	
} // ExcelReadWrite
