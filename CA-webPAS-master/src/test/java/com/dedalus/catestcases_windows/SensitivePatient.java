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
import com.dedalus.capages_windows.AllergiesPage;
import com.dedalus.capages_windows.DocumentsPage;
import com.dedalus.capages_windows.EncountersPage;
import com.dedalus.capages_windows.MenuPage;
import com.dedalus.capages_windows.PatientBannerPage;
import com.dedalus.capages_windows.SummaryPage;
import com.dedalus.restasssured_windows.RESTAssuredBase;

import io.restassured.response.Response;


public class SensitivePatient extends RESTAssuredBase {

  @Parameters({"SOR"})
		
  @BeforeTest
  public void setData(String SOR) throws IOException, InterruptedException, AWTException {
    testCaseName = "Sensitive Patient record validations ";
    testDescription = "This test is to verify the Alerts,Allergy and Clinical documents for a Sensitive patient.";
    nodes = SOR;
    category = "Regression";
    authors = "Kavitha";
  }
		
  @Parameters({"SOR"})
  @Test
  public void alerts(String SOR, ITestContext context) throws InterruptedException, IOException, ClassNotFoundException, SQLException, ParseException, AWTException {

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
		
		Assert.assertEquals(virtuosoOutcome, "PASS","Virtuoso Journey Failed");
		
	  	String SORPatientSurname = getExposedValue(varResponse,"LCP","TD_strSurname","TD_strSurname");
		String SORPatientForename = getExposedValue(varResponse,"LCP","TD_strForename","TD_strForename");
		String SORPatientGender = getExposedValue(varResponse,"LCP","TD_strGender","TD_strGender");
		String SORPatientTitle = getExposedValue(varResponse,"LCP","TD_strTitleAbbr","TD_strTitleAbbr");
		String SORPatientDOB = getExposedValue(varResponse,"CP","TD_dtmDOB","TD_dtmDOB");
		String SORHRN = getExposedValue(varResponse,"CP","LV_HRN","LV_HRN");
		String SORConfigURL = getExposedValue(varResponse,"LCP","GV_ATCA_ConfigURL","GV_ATCA_ConfigURL");
		
		String SORAlertName = getExposedValue(varResponse,"CP","LV_IP_AlertName_RA","LV_IP_AlertName_RA");
		String SORAllergyName = getExposedValue(varResponse,"CP","LV_ActiveAllergen1","LV_ActiveAllergen1");
		   
    reportStep("PASID : "+SORHRN, "INFO");
    
    startApp(SOR,SORConfigURL);
    Thread.sleep(8000);
    MenuPage mnuPg = new MenuPage(driver, test);
    
    PatientBannerPage patBnrPg = new PatientBannerPage(driver, test);
    DocumentsPage docPg = new DocumentsPage(driver, test);
    SummaryPage summPg = new SummaryPage(driver, test);
    jsclick(mnuPg.clickMenu, "Menu");
    Thread.sleep(3000);
    click(mnuPg.menu_Search,"Search");
	Thread.sleep(8000);
	System.out.println("Patient ID: "+SORHRN);
	SearchPatient(SORHRN);
	String sensiInfo = getText(mnuPg.SensitiveInfo,"Sensitive Information");
	AssertStringContains(sensiInfo, "Access restricted", "Sensitive Information");
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
	
	String AlertCntInPatBnr = getText(patBnrPg.AlertCnt,"Alert Count in Patient Banner");
	String AllergyCntInPatBnr = getText(patBnrPg.AllergyCount,"Allergy Count in Patient Banner");		
	AssertStringEquals(AlertCntInPatBnr, "1", "Alert Count in Patient Banner");
	AssertStringEquals(AllergyCntInPatBnr, "1", "Allergy Count in Patient Banner");
	
	String AccResIcon = getIconName(patBnrPg.AccessResIcon, "Access Restricted Icon in Patient Banner"); 
	AssertStringEquals(AccResIcon, "warning", "Access Restricted Icon in Patient Banner");
	
	jsclick(patBnrPg.MoreButton, "More button");
	Thread.sleep(5000);
	String AlertCountMore = getText(patBnrPg.AlertCntMore, "Alert Count in More List");
	AssertStringEquals(AlertCountMore, "1", "Alert Count in More List");
		
	String AlertName1More = getText(patBnrPg.AlertName1More, "Alert Name in More list");
	AssertStringEquals(AlertName1More.toUpperCase(),SORAlertName.toUpperCase() , "Alert Name in Patient Banner More List");
	
	String ActAllergyCountMore = getText(patBnrPg.AllergyCntMore, "Active allergy Count in More List");
	AssertStringEquals(ActAllergyCountMore, "1", "Allergy Count in More List");
	
	String AllergyName1More = getText(patBnrPg.AllergyName1More, "Allergy Name in More list");
	AssertStringEquals(AllergyName1More,SORAllergyName , "Allergy Name in Patient Banner More List");
	
	String Address = getText(patBnrPg.AccessResAddress, "Address Details of Sensitive Patient");
	String Contact = getText(patBnrPg.AccessResContact, "Contact Details of Sensitive Patient");
	AssertStringEquals(Address,"Access restricted", "Allergy Name in Patient Banner More List");
	AssertStringEquals(Contact,"Access restricted", "Allergy Name in Patient Banner More List");

	click(patBnrPg.AlertLinkMore, "Alert link in More List");
	Thread.sleep(5000);
	VerifySelectedTab("Alerts","Windows");	
	jsclick(summPg.tab_Summary, "Summary Tab");
	Thread.sleep(3000);
	
	String summaryAlertCount = getText(summPg.SummaryAlertsCount, "Summary Alerts Count");
	AssertStringEquals(summaryAlertCount, "1", "Summary Alert Count");
	
	String summaryAllergyCount = getText(summPg.SummaryAllergyCount, "Summary Alerts Count");
	AssertStringEquals(summaryAllergyCount, "1", "Summary Alert Count");
	
	String SummDocCnt = getText(summPg.DocumentCntWebPAS, "Document Notes Count");
	AssertStringEquals(SummDocCnt, "0", "Clinical Document Count in Summary Page");
	 
	String SummAlertName = getText(summPg.getSumAlertName1, "Alert Name in Summary Page");
	AssertStringEquals(SummAlertName, SORAlertName, "Alert Name in Summary Page");
	
	String SummAllergyName = getText(summPg.getSummAllergyName, "Alert Name in Summary Page");
	AssertStringEquals(SummAllergyName, SORAllergyName, "Allergy Name in Summary Page");
  
	String SummDocName = getText(summPg.AccessRescDocument, "Document Name in Summary Page");
	AssertStringEquals(SummDocName,"Access restricted", "Document Name in Summary Page");
	
	
	// Clicking on Document tab EPR
	click(summPg.tab_WebPASDocuments,"Document EPR Tab");
	String DocData = getText(docPg.AccResDocData, "Document Data in Document Page");
	AssertStringContains(DocData,"The Patient is marked as 'Sensitive'", "Document data in Document Page");
  
	reportStep("Script completed", "INFO");
    driver.quit();
	
  }
  
}