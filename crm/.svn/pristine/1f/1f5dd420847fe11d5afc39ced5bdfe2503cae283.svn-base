package com.wkcto.crm.utils;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel解析器/读取器
 * @author Administrator
 *
 * @param <T>
 */
public class ExcelReader<T> {

	/**
	 * 从excel文件中读取数据
	 * @param clazz
	 * @param excelPath
	 * @return 数据集合List
	 */
	public List<T> getDataListFromExcel(Class clazz, String excelPath) {
		List<T> dataList = new ArrayList<>();
		try {
			// 获取工作簿
			Workbook workbook = new XSSFWorkbook(new FileInputStream(excelPath));
			// 获取第一个sheet
			Sheet sheet = workbook.getSheetAt(0);
			// 获取标题行
			Row titleRow = sheet.getRow(0);
			// 获取总列数
			int columns = titleRow.getLastCellNum();
			// 获取第1行的每一个列，取出value，每一个value都是属性名。
			String[] propertyNames = new String[columns];
			for (int i = 0; i < columns; i++) {
				propertyNames[i] = getCellValue(titleRow.getCell(i));
			}
			// 获取表格的总行数
			int rows = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < rows; i++) {
				T obj = (T) clazz.newInstance();
				Row dataRow = sheet.getRow(i);
				if (dataRow != null) {
					for (int j = 0; j < columns; j++) {
						Cell cell = dataRow.getCell(j);
						if (cell != null) {
							String cellValue = getCellValue(cell);
							// 将以上获取到的value赋值给对象的属性（setXxx()）
							String propertyName = propertyNames[j];
							String methodName = "set" + propertyName.substring(0, 1).toUpperCase()
									+ propertyName.substring(1);
							Method method = clazz.getDeclaredMethod(methodName, String.class);
							method.invoke(obj, cellValue);
						}
					}
				}
				dataList.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	private static String getCellValue(Cell cell) {
		DecimalFormat df = new DecimalFormat("#");
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(cell.getDateCellValue()).toString();
			}
			return df.format(cell.getNumericCellValue());
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case Cell.CELL_TYPE_BLANK:
			return "";
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue() + "";
		case Cell.CELL_TYPE_ERROR:
			return cell.getErrorCellValue() + "";
		}
		return "";
	}
}
