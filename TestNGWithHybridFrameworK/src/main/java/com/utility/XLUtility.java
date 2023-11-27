package com.utility;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XLUtility {
	
	public DataFormatter df;
	public FileInputStream fis;
	public Workbook wb;
	String path = null;
	
	public XLUtility(String path)
	{
		this.path=path;
		
	}
	
	public int getRowCount(String sheetName) throws Exception
	{
		fis= new FileInputStream(path);
		wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		int rowCount=sh.getLastRowNum();
		wb.close();
		fis.close();
		return rowCount;
		
	}
	
	public int getCellCount(String sheetName, int rownum) throws Exception
	{
		fis= new FileInputStream(path);
		wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		int cellCount=sh.getRow(rownum).getLastCellNum();
		wb.close();
		fis.close();
		
		return cellCount;
		
		
	}
	
	public String getCellData(String sheetName, int rownum, int column) throws Exception
	{
		fis= new FileInputStream(path);
		wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		Row row=sh.getRow(rownum);
		Cell cell=row.getCell(column);
		
		df= new DataFormatter();
		
		String data=df.formatCellValue(cell);
		
		
		wb.close();
		fis.close();
		return data;
		
		
		
		}
}
