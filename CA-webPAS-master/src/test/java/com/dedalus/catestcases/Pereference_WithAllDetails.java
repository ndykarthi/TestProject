package com.dedalus.catestcases;

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

import com.dedalus.capages.MenuPage;
import com.dedalus.capages.PatientBannerPage;
import com.dedalus.capages.SummaryPage;
import com.dedalus.restasssured.RESTAssuredBase;

import io.restassured.response.Response;


public class Pereference_WithAllDetails extends RESTAssuredBase {

	@Parameters({"PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
		
  @BeforeTest
  public void setData(String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation) throws IOException, InterruptedException, AWTException {
    testCaseName = "Patient preference with all details validations "+PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
    testDescription = "This test is to verify the Patient preference with all details";
    nodes = PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
    category = "Regression";
    authors = "Kavitha";
  }
		
  @Parameters({"SOR","PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
  @Test
  public void patPrefWithData(String SOR,String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation, ITestContext context) throws InterruptedException, IOException, ClassNotFoundException, SQLException, ParseException, AWTException {

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
		String SORPatientDOB = getExposedValue(varResponse,"CP","TD_dtmDOB","TD_dtmDOB");
		String SORHRN = getExposedValue(varResponse,"CP","LV_HRN","LV_HRN");
		String SORConfigURL = getExposedValue(varResponse,"LCP","GV_ATCA_ConfigURL","GV_ATCA_ConfigURL");
		String SORPatAddLine1 = getExposedValue(varResponse,"CP","LV_AddressLine1","LV_AddressLine1");
		String SORPatAddLine2 = getExposedValue(varResponse,"CP","LV_AddressLine2","LV_AddressLine2"); 	
		String SORPatAddLine3 = getExposedValue(varResponse,"CP","LV_AddressLine3","LV_AddressLine3");
		String SORPatAddLine4 = getExposedValue(varResponse,"CP","LV_AddressLine4","LV_AddressLine4");
		String SORPostCode = getExposedValue(varResponse,"CP","TD_strPostCode","TD_strPostCode");
		SORPatAddLine3 = convertCamelCase(SORPatAddLine3);
		String SORPatAdd = SORPatAddLine1+", "+SORPatAddLine2+" "+SORPatAddLine3+" "+SORPatAddLine4+" "+SORPostCode;
	    String SORPatHomeContact = getExposedValue(varResponse,"CP","LV_HomePhoneNumber","LV_HomePhoneNumber");  
	    String SORPatWorkContact = getExposedValue(varResponse,"CP","LV_WorkPhoneNumber","LV_WorkPhoneNumber");
	    String SORPatMobileContact = getExposedValue(varResponse,"CP","LV_MobilePhoneNumber","LV_MobilePhoneNumber");
	    String SORPatEmailContact = getExposedValue(varResponse,"CP","LV_Emailid","LV_Emailid");
	    String SORPatAdd2 = SORPatAddLine3+" "+SORPatAddLine4+" "+SORPostCode;   
	    
    reportStep("PASID : "+SORHRN, "INFO");
    
    startCAUKApp(SOR,PlatformName,DeviceName,PlatformVersion,DeviceOrientation,SORConfigURL);
    Thread.sleep(8000);
    MenuPage mnuPg = new MenuPage(driver, test);
    
    PatientBannerPage patBnrPg = new PatientBannerPage(driver, test);
    SummaryPage summPg = new SummaryPage(driver, test);
    jsclick(mnuPg.clickMenu, "Menu");
    Thread.sleep(3000);
    click(mnuPg.menu_Search,"Search");
	Thread.sleep(8000);
	System.out.println("Patient ID: "+SORHRN);
	SearchPatient(SORHRN);
	VerifyPatientDetails_SearchListWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle,SORPatientDOB,SORHRN, SORPatAdd);
	VerifyPatientDetails_SearchListMoreWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle, SORPatientGender,SORPatientDOB,SORHRN);
	String PatientFN = SORPatientSurname.toUpperCase()+", "+SORPatientForename;
	SelectPatient(PatientFN);
	Thread.sleep(5000);
	
	String patientBannerName = getText(summPg.getPatientNameInBanner, "Patient Name in Banner Summary Page");
	String HRNBanner = getText(summPg.getHRNInBanner, "HRN Number in Banner Summary Page");
	String DOBBanner = getText(summPg.getDoBInBanner, "DOB in Banner Summary Page");
	
	AssertStringEquals((SORPatientSurname.toUpperCase()+", "+SORPatientForename+" ("+SORPatientTitle+")"), patientBannerName, "Patient Name in Patient banner");
	AssertStringEquals(SORHRN, HRNBanner, "HRN Number in Patient banner");
	AssertStringEquals(SORPatientDOB, DOBBanner, "DOB in Patient banner");
	
	if(DeviceOrientation.equals("LANDSCAPE")) {
	String AlertCntInPatBnr = getText(patBnrPg.AlertCnt,"Alert Count in Patient Banner");
	String AllergyCntInPatBnr = getText(patBnrPg.AllergyCount,"Allergy Count in Patient Banner");		
	AssertStringEquals(AlertCntInPatBnr, "0", "Alert Count in Patient Banner");
	AssertStringEquals(AllergyCntInPatBnr, "0", "Allergy Count in Patient Banner");
	}
	
	jsclick(patBnrPg.MoreButton, "More button");
	Thread.sleep(5000);
	
	// Address
	String PatAdd1 = getText(patBnrPg.Address1, "Patient Address Line 1");
	String PatAdd3 = getText(patBnrPg.Address2, "Patient Address Line 2");
	//AssertStringEquals(SORPatAddLine1.toUpperCase()+","+SORPatAddLine2.toUpperCase(), PatAdd1.toUpperCase(), "Patient Address Line 1");
	AssertStringEquals(SORPatAddLine1+", "+SORPatAddLine2, PatAdd1, "Patient Address Line 1");
	AssertStringContains(SORPatAdd2, PatAdd3, "Patient Address Line 2");
	
	// Contact
	String HomeContMore = getText(patBnrPg.ContactHome, "Home Contact in More Patient List");
	String MobileContMore = getText(patBnrPg.ContactMobile, "Mobile Contact in More Patient List");
	String WorkContMore = getText(patBnrPg.ContactWork, "Work Contact in More Patient List");
	String EmailContMore = getText(patBnrPg.EmailMore, "EMail Contact in More Patient List");
	
	AssertStringEquals(HomeContMore, SORPatHomeContact, "Home Contact in More List");
	AssertStringEquals(MobileContMore, SORPatMobileContact, "Mobile Contact in More List");
	AssertStringEquals(WorkContMore, SORPatWorkContact, "Work Contact in More List");
	AssertStringEquals(EmailContMore, "mailto:"+SORPatEmailContact, "EMail Contact in More List");
	
	//Information
	String GenderMore = getText(patBnrPg.GenderMore, "Gender in More Patient List");
	if((SORPatientGender.equals("Transgender")) || (SORPatientGender.equals("Intersex"))) {
		SORPatientGender = "Other";
	}
	AssertStringEquals(GenderMore, SORPatientGender, "Gender in More List");		
			
	//Allergy
	String AllergyCountMore = getText(patBnrPg.AllergyCntMore, "Allergy Count in More List");
	String AllergyNameMore = getText(patBnrPg.AllergyName1More, "Allergy Name in More List");
	AssertStringEquals(AllergyCountMore, "0", "Allergy Count in More List");
	AssertStringEquals(AllergyNameMore, "Unknown", "Allergy Name in More List");
	
	// Alert
	String AlertCountMore = getText(patBnrPg.AlertCntMore, "Alert Count in More List");
	String AlertNameMore = getText(patBnrPg.AlertName1More, "Alert Name in More List");
	AssertStringEquals(AlertCountMore, "0", "Alert Count in More List");
	AssertStringEquals(AlertNameMore, "Unknown", "Alert Name in More List");
	
	reportStep("Script completed", "INFO");
    driver.quit();
	
  }
  
}