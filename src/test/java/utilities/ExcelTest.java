package utilities;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelTest {

    public static void main(String[] args) throws IOException {

     //   String path = "src/test/resources/testData/TestData.xlsx";

    //    try {
    //        FileInputStream file = new FileInputStream(path);
    //        Workbook workbook = new XSSFWorkbook(file);
    //        Sheet sheet1 = workbook.getSheet("Sheet1");
    //        String firstName = sheet1.getRow(1).getCell(0).toString();
//
    //        System.out.println(sheet1.getLastRowNum()); // index of last row

    //        System.out.println(firstName);
//
     //       System.out.println(sheet1.createRow(3));
    //        System.out.println(sheet1);
    //        System.out.println(sheet1.getRow(1).getCell(2));
     //       System.out.println(sheet1.getRow(3).getCell(0));  // nullpointerexception

    //        sheet1.createRow(3).createCell(0).setCellValue("Shirin");
    //        System.out.println(sheet1.getRow(3).getCell(0));

    //        sheet1.getRow(3).createCell(1).setCellType(CellType.NUMERIC);
    //        sheet1.getRow(3).getCell(1).setCellValue(123);


    //        FileOutputStream output = new FileOutputStream(path);
    //        workbook.write(output);
//



     //   } catch (IOException e) {
    //        e.printStackTrace();
    //    }
        ExcelUtils.openExcelFile ("TestData","Sheet1");
        System.out.println(ExcelUtils.getCellValue(1,0));
        ExcelUtils.setValue(4,0,"David");
    }



}