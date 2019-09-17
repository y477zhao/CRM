package com.wkcto.crm.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter<T> {

	/**
	 * 根据提供的数据返回一个工作簿对象。
	 * @param dataList
	 * @param sheetName
	 * @param clazz
	 * @return
	 */
	public XSSFWorkbook getWorkbook(List<T> dataList, String sheetName, Class clazz) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetName);
		XSSFRow titleRow = sheet.createRow(0);
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			XSSFCell cell = titleRow.createCell(i);
			cell.setCellValue(fieldName);
		}
		for (int i = 0; i < dataList.size(); i++) {
			T obj = dataList.get(i);
			XSSFRow dataRow = sheet.createRow(i + 1);
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				XSSFCell cell = dataRow.createCell(j);
				String fieldName = field.getName();
				String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Method method = clazz.getDeclaredMethod(methodName);
					Object value = method.invoke(obj);
					cell.setCellValue(value == null ? "" : value.toString());
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return workbook;
	}
}
