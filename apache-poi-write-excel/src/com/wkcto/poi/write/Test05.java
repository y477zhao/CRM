package com.wkcto.poi.write;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wkcto.poi.write.domain.Student;

/**
 * 支持2010版本excel的。
 */
public class Test05 {

	public static void main( String[] args ) throws Exception{
		// 准备数据
		Student s1 = new Student();
		s1.setNo("s1");
		s1.setName("zhangsan");
		s1.setEmail("zhangsan@126.com");
		
		Student s2 = new Student();
		s2.setNo("s2");
		s2.setName("lisi");
		s2.setEmail("lisi@126.com");
		
		Student s3 = new Student();
		s3.setNo("s3");
		s3.setName("wangwu");
		s3.setEmail("wangwu@126.com");
		
		List<Student> studentList = new ArrayList<>();
		studentList.add(s1);
		studentList.add(s2);
		studentList.add(s3);
		
    	// 创建一个工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 通过工作簿创建一个表格
        XSSFSheet sheet = workbook.createSheet("学生表");
        // 通过表格创建第一行(标题行)
        XSSFRow titleRow = sheet.createRow(0);
        /*
        // 创建第1行的第1个单元格
        XSSFCell cell11 = titleRow.createCell(0);
        cell11.setCellValue("no");
        // 创建第1行的第2个单元格
        XSSFCell cell12 = titleRow.createCell(1);
        cell12.setCellValue("name");
        // 创建第1行的第3个单元格
        XSSFCell cell13 = titleRow.createCell(2);
        cell13.setCellValue("email");
        */
        // 获取类
        Class studentClass = Student.class;
        // 获取该类中所有的属性
        Field[] fields = studentClass.getDeclaredFields();
        // 遍历属性
        for(int i = 0; i < fields.length; i++){
        	Field field = fields[i];
        	String fieldName = field.getName();
        	XSSFCell cell = titleRow.createCell(i);
        	cell.setCellValue(fieldName);
        }
        
        for(int i = 0; i < studentList.size(); i++){
        	Student student = studentList.get(i);
        	
        	XSSFRow dataRow = sheet.createRow(i + 1);
        	
        	/*
        	XSSFCell cell0 = dataRow.createCell(0);
        	cell0.setCellValue(student.getNo());
        	
        	XSSFCell cell1 = dataRow.createCell(1);
        	cell1.setCellValue(student.getName());
        	
        	XSSFCell cell2 = dataRow.createCell(2);
        	cell2.setCellValue(student.getEmail());
        	*/
        	
        	for(int j = 0; j < fields.length; j++){
        		Field field = fields[j];
        		XSSFCell cell = dataRow.createCell(j);
        		// String value = student.getNo();
        		String fieldName = field.getName();
        		String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        		Method method = studentClass.getDeclaredMethod(methodName);
        		Object value = method.invoke(student);
        		cell.setCellValue(value == null ? "" : value.toString());
        	}
        	
        }
        
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