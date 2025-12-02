package com.utility;

import com.ui.pojo.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {

    public static Iterator<User> readExcelFile(String fileName, String sheetName) {

        File xlsxFile = new File(System.getProperty("user.dir") + File.separator + "testData" + File.separator + fileName);
        XSSFWorkbook xssfWorkbook = null;
        XSSFSheet xssfSheet;
        Iterator<Row> rowIterator;
        List<User> userList = null;
        Row row;
        Cell emailAddressCellValue;
        Cell passwordCellValue;
        User user;

        try {
            xssfWorkbook = new XSSFWorkbook(xlsxFile);
            xssfSheet = xssfWorkbook.getSheet(sheetName);
            rowIterator = xssfSheet.iterator();
            userList = new ArrayList<>();

            rowIterator.next(); // Skipping Column Name
            while(rowIterator.hasNext()) {
                row = rowIterator.next();
                emailAddressCellValue = row.getCell(0);
                passwordCellValue = row.getCell(1);
                user = new User(emailAddressCellValue.toString(), passwordCellValue.toString());
                userList.add(user);
                xssfWorkbook.close();
            }

        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        return userList.iterator();
    }
}
