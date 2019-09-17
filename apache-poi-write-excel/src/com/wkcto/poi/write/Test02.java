package com.wkcto.poi.write;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
/**
 * 支持2010版本excel的。
 */
public class Test02{
 
    public static void main( String[] args ){
 
        //新建excel报表
        XSSFWorkbook excel = new XSSFWorkbook();
        //添加一个sheet，名字叫"我的POI之旅"
        XSSFSheet sheet = excel.createSheet("学生表");
        //往excel表格创建一行，excel的行号是从0开始的
        XSSFRow titleRow = sheet.createRow(0);
        //第一行创建第一个单元格
        XSSFCell cell0 = titleRow.createCell(0);
        //设置第一个单元格的值
        cell0.setCellValue("name");
        FileOutputStream fout = null;
        try{
            //用流将其写到D盘
            fout = new FileOutputStream("D:/students.xlsx");
            excel.write(fout);
            fout.close();
        }catch (Exception e){
            e.printStackTrace();
        }
 
    }
}