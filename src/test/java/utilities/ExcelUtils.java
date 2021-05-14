package utilities;

// This class will have reusable methods to work with Excel Files

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {
    /*
    .openExcelFile (String filename, String sheetName); -> this method will
                   open excel file with specific file name and sheet name

    .getCellValue (int row, int cell) -> this method will return value on provided
                  row and cell index. -> Returns String

    .setValue (int row , int cell , String value); -> this method will set value
              on provided row and cell index. -> No return
     */

    private static Workbook workbook;
    private static Sheet sheet;
    private static String path;

    public  static void openExcelFile (String fileName, String sheetName){
        path = "src/test/resources/testData/"+fileName+".xlsx";
        try {
            FileInputStream file = new FileInputStream(path);
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);

        } catch (IOException e) {
            System.out.println("Excel file path is invalid");
        }

    }

    public static String getCellValue (int row, int cell){

        return sheet.getRow(row).getCell(cell).toString();

    }

    public static void setValue (int row, int cell, String value) throws IOException {

        int numberOfRow = sheet.getPhysicalNumberOfRows(); // will return number of rolls
        Row row1;
        if (row>=numberOfRow){
            row1 = sheet.createRow(row);
        }else{
            row1 = sheet.getRow(row);
        }

        Cell cell1;
        if (cell>=row1.getPhysicalNumberOfCells()){
            cell1 = row1.createCell(cell);
        }else{
            cell1 = row1.getCell(cell);
        }

        cell1.setCellValue(value);
        FileOutputStream output = null;

        try {
          output   = new FileOutputStream(path);
            workbook.write(output);
        } catch (IOException e) {
            System.out.println("Excel file path is invalid");
        } finally {
            output.close();
        }
    }
}
