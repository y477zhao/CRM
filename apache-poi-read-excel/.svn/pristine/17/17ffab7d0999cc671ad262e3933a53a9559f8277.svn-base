package com.wkcto.poi.read3;

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

import com.wkcto.poi.domain.Activity;

public class ExcelPoi {
	public static void main(String[] args) {
		List<Activity> activityList = new ArrayList<>();
		Class activityClass = Activity.class;
		try {
			String fileToBeRead = "D:\\activity-1535942952861.xlsx";
			// 获取工作簿
			Workbook workbook = new XSSFWorkbook(new FileInputStream(fileToBeRead));
			// 获取第一个sheet
			Sheet sheet = workbook.getSheetAt(0);
			// 获取总列数
			int columns = sheet.getRow(0).getLastCellNum();
			// 获取标题行
			Row titleRow = sheet.getRow(0);
			// 获取第1行的每一个列，取出value，每一个value都是属性名。
			String[] propertyNames = new String[columns];
			for(int i = 0; i < columns; i++){
				Cell cell = titleRow.getCell(i);
				propertyNames[i] = getCellValue(cell);
			}
			// 获取表格的总行数
			int rows = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < rows; i++) {
				Activity activity = (Activity)activityClass.newInstance();
				Row dataRow = sheet.getRow(i);
				if (dataRow != null) {
					for (int j = 0; j < columns; j++) {
						Cell cell = dataRow.getCell(j);
						if (cell != null) {
							String cellValue = getCellValue(cell);
							// 将以上获取到的value赋值给对象的属性（setXxx()）
							String propertyName = propertyNames[j];
							String methodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
							Method method = activityClass.getDeclaredMethod(methodName, String.class);
							method.invoke(activity, cellValue);
						}
					}
				}
				activityList.add(activity);
			}
			System.out.println(activityList);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				// return
				// sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
			}
			return df.format(cell.getNumericCellValue());
		case Cell.CELL_TYPE_STRING:
			// System.out.println(cell.getStringCellValue());
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