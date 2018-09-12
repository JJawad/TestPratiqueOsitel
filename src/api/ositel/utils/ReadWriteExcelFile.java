package api.ositel.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.ositel.classe.LigneValue;
import api.ositel.classe.Result;

public class ReadWriteExcelFile {

	public static Result readXLSFile(File file) throws IOException
	{
		InputStream ExcelFileToRead = new FileInputStream(file);
		Result result = new Result(file.getName());
		Boolean isFirstRow = true;
		HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
		HSSFSheet sheet=wb.getSheetAt(0);
		HSSFRow row; 
		HSSFCell cell;

		Iterator rows = sheet.rowIterator();
		int nembreLigne = 0;
		List<LigneValue> listLigneValue = new ArrayList<LigneValue>();
		while (rows.hasNext())
		{
			
			String rowString = "[";
			row=(HSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			Boolean isFirstCell = true;
			List<String> listValeur =new ArrayList<String>();
			while (cells.hasNext())
			{
				cell=(HSSFCell) cells.next();
				if(isFirstCell) {
					isFirstCell = false;
				}else {
					rowString += ",";
				}
				
				if ("STRING".equals(cell.getCellType().name()))
				{
					rowString += "\""+cell.getStringCellValue()+"\"";
					listValeur.add(cell.getStringCellValue());
				}
				else if("NUMERIC".equals(cell.getCellType().name()))
				{
					rowString += "\""+cell.getNumericCellValue()+"\"";
					listValeur.add(""+cell.getNumericCellValue());
				}
				
			}
			
			rowString += "]";
			if(isFirstRow) {
				result.setHeaderColumn(rowString);
				isFirstRow = false;
			}else {
				LigneValue ligneValue = new LigneValue(); 
				ligneValue.setId(nembreLigne);
				ligneValue.setValeurs(listValeur);
				listLigneValue.add(ligneValue);
			}
			nembreLigne++;
			
		}
		result.setListlinesValue(listLigneValue);
		
		return result;
	}
	
	
	public static Result readXLSXFile(File file) throws IOException
	{
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook  wb = new XSSFWorkbook(fis);
		
		Result result = new Result(file.getName());
		Boolean isFirstRow = true;
		int nembreLigne = 0;
		List<LigneValue> listLigneValue = new ArrayList<LigneValue>();
		
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row; 
		XSSFCell cell;

		Iterator rows = sheet.rowIterator();

		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			Boolean isFirstCell = true;
			List<String> listValeur =new ArrayList<String>();
			String rowString = "[";
			
			while (cells.hasNext())
			{
				cell=(XSSFCell) cells.next();
				if(isFirstCell) {
					isFirstCell = false;
				}else {
					rowString += ",";
				}
		
				if ("STRING".equals(cell.getCellType().name()))
				{
					rowString += "\""+cell.getStringCellValue()+"\"";
					listValeur.add(cell.getStringCellValue());
				}
				else if("NUMERIC".equals(cell.getCellType().name()))
				{
					
					rowString += "\""+cell.getNumericCellValue()+"\"";
					listValeur.add(""+cell.getNumericCellValue());
				}
				
				
			}
			rowString += "]";
			if(isFirstRow) {
				result.setHeaderColumn(rowString);
				isFirstRow = false;
			}else {
				LigneValue ligneValue = new LigneValue(); 
				ligneValue.setId(nembreLigne);
				ligneValue.setValeurs(listValeur);
				listLigneValue.add(ligneValue);
			}
			nembreLigne++;
		}
		
		result.setListlinesValue(listLigneValue);
		
		return result;
	
	}
	
	
	public static File setValueInXLSXFile(File file,int line,int colonne,String value) throws IOException
	{
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook  wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row = sheet.getRow(line) ; 
		
		if(row == null) {
			row = sheet.createRow(line);
		}
		
		XSSFCell cell = row.getCell(colonne-1);
		if(cell == null) {
			cell = row.createCell(colonne-1);
		}
		
		cell.setCellValue(value);
		
		fis.close();
		FileOutputStream fileOut = new FileOutputStream(file);

		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		
		return file;
	
	}

}