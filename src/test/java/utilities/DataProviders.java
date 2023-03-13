package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\OC1PTEST.xlsx";//taking Excel file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);
				
		String logindata[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data.... email and password
		
		for(int i=1;i<=totalrows;i++)  //1   //read the data from Excel storing in two dimensional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0 //NBP:-In excel Zeroth row=header and the data starts from 1, 2, 3...,column=0,1,2,3
				//[i-1]:- But whereas in Array it row starts from 0, 1, 2, 3...(no header in Array only data will be placed in an Array)and column 0, 1, 2, 3....
			}
		}
	return logindata;//returning two dimension array
				
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}
