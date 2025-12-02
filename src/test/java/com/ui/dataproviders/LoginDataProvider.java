package com.ui.dataproviders;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginDataProvider {

    @DataProvider(name = "LoginTestDataProvider")
    public Iterator<Object[]> loginDataProvider(){
        Gson gson = new Gson();
        File testDataFile = new File(System.getProperty("user.dir")+"/testData/loginData.json");
        FileReader fileReader = null;
        TestData data;
        List<Object[]> dataToReturn;

        try {
            fileReader = new FileReader(testDataFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        data = gson.fromJson(fileReader, TestData.class);
        dataToReturn = new ArrayList<>();

        for(User user: data.getData()){
            dataToReturn.add(new Object[]{user});
        }

        return dataToReturn.iterator();
    }

    @DataProvider(name = "LoginTestCSVDataProvider")
    public Iterator<User> loginCSVDataProvider(){
        return CSVReaderUtility.readCSVFile("logindata.csv");
    }

    @DataProvider(name = "LoginTestExcelDataProvider")
    public Iterator<User> loginExcelDataProvider(){
        return ExcelReaderUtility.readExcelFile("LoginData.xlsx","LoginTestData");
    }

}
