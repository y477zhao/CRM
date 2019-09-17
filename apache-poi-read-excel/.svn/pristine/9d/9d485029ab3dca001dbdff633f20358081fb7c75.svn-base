package com.wkcto.poi.read2;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelPoi {
	public static void main(String[] args) {
		try {
			String fileToBeRead = "D:\\activity-1535942952861.xlsx";
			// 获取工作簿
			Workbook workbook = new XSSFWorkbook(new FileInputStream(fileToBeRead));
			// 获取第一个sheet
			Sheet sheet = workbook.getSheetAt(0);
			// 获取表格的总行数
			int rows = sheet.getPhysicalNumberOfRows();
			// 获取总列数
			int columns = sheet.getRow(0).getLastCellNum();
			for (int i = 1; i < rows; i++) {
				Row dataRow = sheet.getRow(i);
				if (dataRow != null) {
					for (int j = 0; j < columns; j++) {
						Cell cell = dataRow.getCell(j);
						if (cell != null) {
							String cellValue = getCellValue(cell);
							System.out.print(cellValue + " ");
						}
					}
					System.out.println();
				}
			}
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