package com.wkcto.poi.write;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 
/**
 * POI导出excel
 * Created by Ay on 2016/6/14.
 */
public class Test01{
 
    public static void main( String[] args ){
 
        //新建excel报表
        HSSFWorkbook excel = new HSSFWorkbook();
        //添加一个sheet，名字叫"我的POI之旅"
        HSSFSheet hssfSheet = excel.createSheet("我的POI之旅");
        //往excel表格创建一行，excel的行号是从0开始的
        HSSFRow hssfRow = hssfSheet.createRow(0);
        //第一行创建第一个单元格
        HSSFCell hssfCell = hssfRow.createCell(0);
        //设置第一个单元格的值
        hssfCell.setCellValue("poi");
        FileOutputStream fout = null;
        try{
            //用流将其写到D盘
            fout = new FileOutputStream("D:/students.xls");
            excel.write(fout);
            fout.close();
        }catch (Exception e){
            e.printStackTrace();
        }
 
    }
}