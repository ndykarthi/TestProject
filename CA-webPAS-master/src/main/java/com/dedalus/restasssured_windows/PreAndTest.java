package com.dedalus.restasssured_windows;


import java.io.File;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.dedalus.genericappmethods_windows.CAProjectMethods;


public class PreAndTest extends CAProjectMethods{
	
	public String dataFileName, dataFileType;	
	
	
	@Parameters({"testSuiteName"})
	@BeforeSuite
	public void beforeSuite(String testSuiteName) {
		startResult(testSuiteName);
	
	}
	
	
	@BeforeClass
	public void beforeClass() {
		startTestModule(testCaseName, testDescription);		
	}
	
	@BeforeMethod
	public void beforeMethod() {
		//for reports		
		svcTest = startTestCase(nodes);
		svcTest.assignAuthor(authors);
		svcTest.assignCategory(category);
		
		//RestAssured.baseURI = "https://dev22827.service-now.com/api/now/pa";
	//	RestAssured.baseURI = "https://lor1058.lgn.mpe.ncrs.nhs.uk/LMHIAservice/api";

	}
	
		@AfterMethod
	public void afterMethod() {
	}

	@AfterSuite
	public void afterSuite() {
		endResult();
	}

//	@DataProvider(name="fetchData")
/*	public  Object[][] getData(){
		if(dataFileType.equalsIgnoreCase("Excel"))
			//return DataInputProvider.getSheet(dataFileName);
			//return com.dedalus.utilities.DataInputProvider.getSheet(dataFileName);
		else {
			Object[][] data = new Object[1][1];
			data[0][0] = new File("./data/"+dataFileName+"."+dataFileType);
			System.out.println(data[0][0]);
			return data;
		}
			
	}*/
/*
	@Override
	public long takeSnap() {
		return 0;
	}
	//public long takeSnap() { return 0; 	}	
*/
	
	
}
