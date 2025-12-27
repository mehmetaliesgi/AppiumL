package utils.helpers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    private Workbook workbook;
    private Sheet sheet;
    private String excelFilePath;

    public ExcelReader(String excelPath, String sheetName) {
        this.excelFilePath = excelPath;
        try {
            FileInputStream fis = new FileInputStream(excelFilePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet bulunamadı: " + sheetName);
            }

        } catch (IOException e) {
            throw new RuntimeException("Excel dosyası açılamadı: " + excelPath, e);
        }
    }

    public String getCellData(int rowNum, int colNum) {
        try {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                return "";
            }

            Cell cell = row.getCell(colNum);
            if (cell == null) {
                return "";
            }

            return getCellValueAsString(cell);

        } catch (Exception e) {
            throw new RuntimeException("Hücre verisi okunamadı: Row=" + rowNum + ", Col=" + colNum, e);
        }
    }

    public String getCellData(int rowNum, String columnName) {
        int colNum = getColumnNumber(columnName);
        return getCellData(rowNum, colNum);
    }


    public int getRowCount() {
        return sheet.getLastRowNum();
    }


    public int getColumnCount() {
        return sheet.getRow(0).getLastCellNum();
    }


    private int getColumnNumber(String columnName) {
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            if (getCellValueAsString(headerRow.getCell(i)).equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        throw new RuntimeException("Sütun bulunamadı: " + columnName);
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    public Object[] getRowData(int rowNum) {
        int colCount = getColumnCount();
        Object[] data = new Object[colCount];

        for (int i = 0; i < colCount; i++) {
            data[i] = getCellData(rowNum, i);
        }
        return data;
    }

    public Object[][] getAllData() {
        int rowCount = getRowCount(); // Header hariç
        int colCount = getColumnCount();

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) { // 0. satır header, 1'den başla
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }
        return data;
    }

    public void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}