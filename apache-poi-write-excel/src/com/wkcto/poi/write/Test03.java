package com.wkcto.poi.write;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 支持2010版本excel的。
 */
public class Test03 {

	public static void main( String[] args ){
    	// 创建一个工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 通过工作簿创建一个表格
        XSSFSheet sheet = workbook.createSheet("学生表");
        // 通过表格创建第一行(标题行)
        XSSFRow titleRow = sheet.createRow(0);
        // 创建第1行的第1个单元格
        XSSFCell cell11 = titleRow.createCell(0);
        cell11.setCellValue("no");
        // 创建第1行的第2个单元格
        XSSFCell cell12 = titleRow.createCell(1);
        cell12.setCellValue("name");
        // 创建第1行的第3个单元格
        XSSFCell cell13 = titleRow.createCell(2);
        cell13.setCellValue("email");
        
        // 创建第2行
        XSSFRow dataRow1 = sheet.createRow(1);
        // 创建第2行的第1个单元格
        XSSFCell cell21 = dataRow1.createCell(0);
        cell21.setCellValue("s1");
        // 创建第2行的第2个单元格
        XSSFCell cell22 = dataRow1.createCell(1);
        cell22.setCellValue("zhangsan");
        // 创建第2行的第3个单元格
        XSSFCell cell23 = dataRow1.createCell(2);
        cell23.setCellValue("zhangsan@123.com");
        
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream("D:/students.xlsx");
            workbook.write(fout);
            fout.close();
        }catch (Exception e){
            e.printStackTrace();
        }
 
    }
}