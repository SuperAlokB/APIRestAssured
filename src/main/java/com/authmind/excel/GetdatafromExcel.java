package com.authmind.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;


public class GetdatafromExcel {

	private static String sheetName= "AddUsers";
	private static String excelFileName = "C:\\workspace\\Automation\\Cucumber-Selenium-Adaptor\\src\\test\\resources\\data\\AmTestData.xlsx";

	  public GetdatafromExcel(String workbookName,String sheetName){
		  
		 String localPath =System.getProperty("user.dir");
		 localPath = localPath + "\\src\\test\\resources\\data\\" + workbookName + ".xlsx";		  
		this.sheetName = sheetName ;
		this.excelFileName = localPath;
	 }
	 
	 private static Map<String, String> map = new HashMap<>();

	 public static String get(String testcasename,String columnname) throws IOException {
	 FileInputStream fis;
	 int rows;
	 int k =0;
	 try {
	 fis = new FileInputStream(excelFileName);
	 Workbook wb;
	 Sheet  ws = null;
	 String fileExtensionName = excelFileName.substring(excelFileName.indexOf("."));
	  if(fileExtensionName.equals(".xlsx")){

		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		}
//	  else if(fileExtensionName.equals(".xlsx")) {
//		  		  
//		  wb = new HSSFWorkbook(fis);
//		  ws = (HSSFSheet) wb.getSheet(s);
//	  }
//	 
	
	 rows = ws.getPhysicalNumberOfRows();
	 for(int i=0;i<rows;i++){
		 int cols = ws.getRow(0).getPhysicalNumberOfCells();
		 for (int j = 0; j < cols; j++) { 
			 if(ws.getRow(i).getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().replace(".0", "").
				 equalsIgnoreCase(columnname)){ 
				 k=j;
		 		}
			 map.put(ws.getRow(i).getCell(0,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().replace(".0",""), ws.getRow(i).getCell(k,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().replace(".0", "")); 
	 		}
		 
	 	} 
	 }catch (Exception e){
		 	e.printStackTrace(); 
	 	} 
	 
	 return map.get(testcasename);
	 
	 } 
		
}
