package com.utility;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.ui.pojo.User;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtility {

    public static Iterator<User> readCSVFile(String fileName) {

        File csvFile = new File(System.getProperty("user.dir") + File.separator + "testData" + File.separator + fileName);
        FileReader fileReader = null;
        CSVReader csvReader;
        List<User> userList = null;
        String [] line;
        User userData;

        try {
            fileReader = new FileReader(csvFile);
            csvReader = new CSVReader(fileReader);
            csvReader.readNext(); // Skipping Column Name

            userList = new ArrayList<>();
            while((line = csvReader.readNext())!= null){
                userData = new User(line[0],line[1]);
                userList.add(userData);
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        return userList.iterator();
    }
}
