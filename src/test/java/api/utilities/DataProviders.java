package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        //String path = System.getProperty("user.dir")+"testData//userTestData.xlsx";

        String path = "C:\\Users\\a853684\\IdeaProjects\\PetStoreAPIAutomation\\testData\\userTestData.xlsx";

        ExcelUtility excelUtility = new ExcelUtility(path);

        int rowNum = excelUtility.getRowCount("Sheet1");
        int colNum = excelUtility.getCellCount("Sheet1",1);

        String ApiData[][] = new String[rowNum][colNum];

        for(int i = 1;i<=rowNum;i++)
        {
            for(int j=0;j<colNum;j++)
            {
                ApiData[i-1][j] =excelUtility.getCellData("Sheet1",i,j);
            }
        }

        return ApiData;
    }

    @DataProvider(name="userNames")
    public String[] getUserNames() throws IOException {
//        String path = System.getProperty("user.dir")+"testData//userTestData.xlsx";

        String path = "C:\\Users\\a853684\\IdeaProjects\\PetStoreAPIAutomation\\testData\\userTestData.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path);

        int rowNum = excelUtility.getRowCount("Sheet1");

        String ApiData[] = new String[rowNum];

        for(int i=1;i<=rowNum;i++)
        {
            ApiData[i-1] = excelUtility.getCellData("Sheet1",i,1);
        }

        return ApiData;
    }
}
