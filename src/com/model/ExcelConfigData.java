package com.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelConfigData 
{
	XSSFWorkbook wb;
	XSSFSheet sheet;
	
public void readExcel(String filename) throws IOException
{
	FileInputStream fis=new FileInputStream(filename);
	wb= new XSSFWorkbook(fis);
	
}

public int getRowCount(String sheetname)
{
	sheet=wb.getSheet(sheetname);
	int rows=sheet.getLastRowNum();
	System.out.println("Rows are:"+rows);
	int rowcount=rows+1;
	System.out.println("Total rows are:"+ rowcount);
	return rowcount;
}

public int getColumnCount(String sheetname)
{
	sheet=wb.getSheet(sheetname);
	int row=sheet.getLastRowNum();
	int columns=sheet.getRow(row).getLastCellNum();
	return columns;
}

public String getData(String sheetname,int rownum,int column)
{
	sheet=wb.getSheet(sheetname);
	String val=sheet.getRow(rownum).getCell(column).getStringCellValue();
	System.out.println("Data in cell is:"+val);
	return val;
	
}
	
}
