package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheetOperations {

	public static Object[][] excelReadData(String inputFileName, String sheetName) throws IOException {

		File file = new File(inputFileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);

		Sheet sheet = wb.getSheet(sheetName);

		int totalRows = sheet.getLastRowNum();
		int totalCols = sheet.getRow(0).getLastCellNum();
		System.out.println("Total num of rows in sheet are" + totalRows);
		System.out.println("Total number of coloumn in sheet are" + totalCols);

		Object[][] obj = new Object[totalRows][totalCols];

		for (int rowIndex = 0; rowIndex < totalRows; rowIndex++) {
			for (int colIndex = 0; colIndex < totalCols; colIndex++) {
				Cell cell = sheet.getRow(rowIndex + 1).getCell(colIndex);
				if (cell.getCellType() == CellType.STRING)
					obj[rowIndex][colIndex] = cell.getStringCellValue();
				else if (cell.getCellType() == CellType.BOOLEAN)
					obj[rowIndex][colIndex] = cell.getBooleanCellValue();
				else if (cell.getCellType() == CellType.NUMERIC)
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
						obj[rowIndex][colIndex] = cell.getDateCellValue();
					} else
						obj[rowIndex][colIndex] = cell.getNumericCellValue();
			}
		}
		return obj;
	}
}
