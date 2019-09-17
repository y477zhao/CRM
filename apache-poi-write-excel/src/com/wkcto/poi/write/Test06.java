package com.wkcto.poi.write;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wkcto.poi.write.domain.Student;
import com.wkcto.poi.write.utils.ExcelWriter;

/**
 * 支持2010版本excel的。
 */
public class Test06 {

	public static void main( String[] args ) throws Exception{
		// 准备数据（从数据库当中查询获取的）
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
		
    	// 获取工作簿对象
		ExcelWriter<Student> ew = new ExcelWriter<>();
		XSSFWorkbook workbook = ew.getWorkbook(studentList, "学生表", Student.class);
        
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