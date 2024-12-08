package com.dedalus.catestcases_windows;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dedalus.capages_windows.AlertsPage;
import com.dedalus.capages_windows.MenuPage;
import com.dedalus.capages_windows.PatientBannerPage;
import com.dedalus.capages_windows.SummaryPage;
import com.dedalus.restasssured_windows.RESTAssuredBase;

import io.restassured.response.Response;


public class Deceased_SameDOB extends RESTAssuredBase {

  @Parameters({"SOR"})
		
  @BeforeTest
  public void setData(String SOR) throws IOException, InterruptedException, AWTException {
    testCaseName = "Deceased_SameDOB";
    testDescription = "Same day deceased patient validation";
    nodes = SOR;
    category = "Regression";
    authors = "Kavitha";
  }
		
  @Parameters({"SOR"})
  @Test
  public void deceased(String SOR, ITestContext context) throws InterruptedException, IOException, ClassNotFoundException, SQLException, ParseException, AWTException {

	    String accessToken = "Bearer 72db341d-cbdb-4d6d-b521-df59d5b2a571";
		
		ISuite suite = context.getSuite();
		int suiteID = (int) suite.getAttribute("SuiteID");
		int intJobID = (int) suite.getAttribute("JobID"); 
		String virtuosoOutcome = (String) suite.getAttribute("VirtuosoOutcome");
		
		HashMap<String, String> alertHeaderMap = getHeaderMap(accessToken);
		Response varResponse = getWithHeader(alertHeaderMap, "https://api.virtuoso.qa/api/testsuites/execution?suiteId="+suiteID+"&jobId="+intJobID+"&includeJourneyDetails=true");
		varResponse.prettyPrint();
		
		if(virtuosoOutcome.equals(("FAIL"))) {
			reportStep("Virtuoso Journey Outcome: "+virtuosoOutcome, "FAIL",false);
		}
		Assert.assertEquals(virtuosoOutcome, "PASS","Virtuoso Journey Passed");
		
		String SORPatientSurname = getExposedValue(varResponse,"LCP","TD_strSurname","TD_strSurname");
		String SORPatientForename = getExposedValue(varResponse,"LCP","TD_strForename","TD_strForename");
		String SORPatientGender = getExposedValue(varResponse,"LCP","TD_strGender","TD_strGender");
		String SORPatientTitle = getExposedValue(varResponse,"LCP","TD_strTitleAbbr","TD_strTitleAbbr");
		String SORPatientDOB = getExposedValue(varResponse,"LCP","TD_dtmDOB","TD_dtmDOB");
		String SORHRN = getExposedValue(varResponse,"LCP","LV_HRN","LV_HRN");
		String SORConfigURL = getExposedValue(varResponse,"LCP","GV_ATCA_ConfigURL","GV_ATCA_ConfigURL");
		String SORPatAddLine1 = getExposedValue(varResponse,"LCP","LV_AddressLine1","LV_AddressLine1");
		String SORPatAddLine2 = getExposedValue(varResponse,"LCP","LV_AddressLine2","LV_AddressLine2"); 	
		String SORPatAddLine3 = getExposedValue(varResponse,"LCP","LV_AddressLine3","LV_AddressLine3");
		String SORPatAddLine4 = getExposedValue(varResponse,"LCP","LV_AddressLine4","LV_AddressLine4");
		String SORPostCode = getExposedValue(varResponse,"LCP","TD_strPostCode","TD_strPostCode");
		SORPatAddLine3 = convertCamelCase(SORPatAddLine3);
		String SORPatAdd = SORPatAddLine1+", "+SORPatAddLine2+" "+SORPatAddLine3+" "+SORPatAddLine4+" "+SORPostCode;
		String SORPatDOD = SORPatientDOB;
		String SORPatAge = "<1 da";
		    
    reportStep("PASID : "+SORHRN, "INFO");
    
    startApp(SOR,SORConfigURL);
    Thread.sleep(8000);
    MenuPage mnuPg = new MenuPage(driver, test);
    SummaryPage summPg = new SummaryPage(driver, test);
    jsclick(mnuPg.clickMenu, "Menu");
    Thread.sleep(3000);
    click(mnuPg.menu_Search,"Search");
	Thread.sleep(8000);
	System.out.println("Patient ID: "+SORHRN);
	SearchPatient(SORHRN);
	VerifyPatientDetails_SearchListWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle,SORPatientDOB,SORHRN, SORPatAdd);
	VerifyPatientDetails_SearchListMoreDeceasedWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle, SORPatientGender,SORPatientDOB,SORHRN,SORPatDOD,SORPatAge);
	String PatientFN = SORPatientSurname.toUpperCase()+", "+SORPatientForename;
	SelectPatient(PatientFN);
	Thread.sleep(5000);
	
	
	String patientBannerName = getText(summPg.getPatientNameInBanner, "Patient Name in Banner Summary Page");
	String HRNBanner = getText(summPg.getHRNInBanner, "HRN Number in Banner Summary Page");
	String DOBBanner = getText(summPg.getDoBInBanner, "DOB in Banner Summary Page");
	String GenderBanner = getText(summPg.getGenderInBanner, "Gender in Banner Summary Page");
	String DiedDateBanner = getText(summPg.getDiedDateInBanner, "Died Date in Banner Summary Page");
	  
	// Color assertion - black
	String BannerColor = summPg.getBannerColor.getCssValue("background-color");
	System.out.println("Color: "+BannerColor);
	
	if((SORPatientGender.equals("Transgender")) || (SORPatientGender.equals("Intersex"))) {
		SORPatientGender = "Other";
	}
	
	AssertStringEquals((SORPatientSurname.toUpperCase()+", "+SORPatientForename+" ("+SORPatientTitle+")"), patientBannerName, "Patient Name in Banner");
	AssertStringEquals(SORHRN,HRNBanner ,"Patient HRN in Banner");
	AssertStringEquals(SORPatientDOB,DOBBanner ,"Patient DOB in Banner");
	AssertStringEquals(SORPatientGender,GenderBanner ,"Patient Gender in Banner");
	AssertStringEquals(SORPatientDOB+"("+SORPatAge+"y)",DiedDateBanner ,"Patient Died Date in Banner");
	AssertStringEquals("rgba(0, 0, 0, 1)",BannerColor ,"Black background Color in Banner");
	
  reportStep("Script completed", "INFO");
	
  driver.quit();
   
  }
}