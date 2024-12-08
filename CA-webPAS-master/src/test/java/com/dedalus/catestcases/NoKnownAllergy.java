package com.dedalus.catestcases;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dedalus.capages.AlertsPage;
import com.dedalus.capages.AllergiesPage;
import com.dedalus.capages.MenuPage;
import com.dedalus.capages.PatientBannerPage;
import com.dedalus.capages.SummaryPage;
import com.dedalus.restasssured.RESTAssuredBase;

import io.restassured.response.Response;


public class NoKnownAllergy extends RESTAssuredBase {

  @Parameters({"PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
		
  @BeforeTest
  public void setData(String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation) throws IOException, InterruptedException, AWTException {
    testCaseName = "Patient Alerts validations"+PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
    testDescription = "This test is to verify the No Known Allergy for a patient";
    nodes = PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
    category = "Regression";
    authors = "Kavitha";
  }
		
  @Parameters({"SOR","PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
  @Test
  public void alerts(String SOR, String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation, ITestContext context) throws InterruptedException, IOException, ClassNotFoundException, SQLException, ParseException, AWTException {

	    String accessToken = "Bearer 72db341d-cbdb-4d6d-b521-df59d5b2a571";
		
		ISuite suite = context.getSuite();
		int suiteID = (int) suite.getAttribute("SuiteID");
		int intJobID = (int) suite.getAttribute("JobID"); 
		
		HashMap<String, String> alertHeaderMap = getHeaderMap(accessToken);
		Response varResponse = getWithHeader(alertHeaderMap, "https://api.virtuoso.qa/api/testsuites/execution?suiteId="+suiteID+"&jobId="+intJobID+"&includeJourneyDetails=true");
		varResponse.prettyPrint();
			
		String SORPatientSurname = getExposedValue(varResponse,"LCP","TD_strSurname","TD_strSurname");
		String SORPatientForename = getExposedValue(varResponse,"LCP","TD_strForename","TD_strForename");
		String SORPatientGender = getExposedValue(varResponse,"LCP","TD_strGender","TD_strGender");
		String SORPatientTitle = getExposedValue(varResponse,"LCP","TD_strPatientTitle","TD_strPatientTitle");
		String SORPatientDOB = getExposedValue(varResponse,"LCP","TD_dtmDOB","TD_dtmDOB");
		String SORMartialStatus = getExposedValue(varResponse,"LCP","LV_strMaritalStatus","LV_strMaritalStatus");
		String SORResident = getExposedValue(varResponse,"LCP","LV_strResident","LV_strResident");
		String SORReligion = getExposedValue(varResponse,"LCP","LV_strReligion","LV_strReligion");
		String SORIndiStatus = getExposedValue(varResponse,"LCP","LV_strIndigenousStatus","LV_strIndigenousStatus");
		String SORCOB = getExposedValue(varResponse,"LCP","LV_strCountryOfBirth","LV_strCountryOfBirth");
		String SORHRN = getExposedValue(varResponse,"LCP","LV_HRN","LV_HRN");
		String SORConfigURL = getExposedValue(varResponse,"LCP","GV_ATCA_ConfigURL","GV_ATCA_ConfigURL");
		String SORRecordedBy = "James Snell2 .";
		SORPatientTitle = SORPatientTitle.substring(0, 1).toUpperCase()+SORPatientTitle.substring(1).toLowerCase();
		String SORPatAdd = "78 Red St, Cross Road Bundaberg QLD 4670";
		
		// No Known Allergies
		String SORNKAType = getExposedValue(varResponse,"CP","LV_NK_AllergyType","LV_NK_AllergyType");	
	    String SORNKAReaction = getExposedValue(varResponse,"CP","LV_NK_SevReaction","LV_NK_SevReaction");
	    String SORNKASeverity = getExposedValue(varResponse,"CP","LV_NK_Severity","LV_NK_Severity");
	    String SORNKAAllergen = getExposedValue(varResponse,"CP","LV_NK_Allergen","LV_NK_Allergen");
	 //   String SORNKARecBy = SORUserForename+" "+SORUserSurname;
	    String SORNKASite = getExposedValue(varResponse, "CP", "LV_NK_Site", "LV_NK_Site");
	    String SORNKARecDate = getExposedValue(varResponse,"CP","LV_NK_dtmRecordedDT","LV_NK_dtmRecordedDT");
	    String SORNKARevDate = getExposedValue(varResponse, "CP", "LV_NK_dtmReviewDate", "LV_NK_dtmReviewDate");
	    String SORNKAComments = getExposedValue(varResponse, "CP", "LV_NK_AllergyComments", "LV_NK_AllergyComments");
	   
	    reportStep("PASID : "+SORHRN, "INFO");
	    
		startCAUKApp(SOR,PlatformName,DeviceName,PlatformVersion,DeviceOrientation,SORConfigURL);
		Thread.sleep(8000);
		MenuPage mnuPg = new MenuPage(driver, test);
		AlertsPage alertPg = new AlertsPage(driver, test);
		SummaryPage summPg = new SummaryPage(driver, test);
		PatientBannerPage patBnrPg = new PatientBannerPage(driver, test);
		click(mnuPg.clickMenu, "Menu");
		Thread.sleep(3000);
		click(mnuPg.menu_Search,"Search");
		Thread.sleep(3000);
		System.out.println("Patient ID: "+SORHRN);
		SearchPatient(SORHRN);
		VerifyPatientDetails_SearchListWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle,SORPatientDOB,SORHRN, SORPatAdd);
		VerifyPatientDetails_SearchListMoreWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle, SORPatientGender,SORPatientDOB,SORHRN);
		String PatientFN = SORPatientSurname.toUpperCase()+", "+SORPatientForename+" ("+SORPatientTitle+")";
		//String PatientFN = SORPatientSurname.toUpperCase()+", "+SORPatientForename;
		SelectPatient(SORHRN);
		Thread.sleep(5000);
		
		if(DeviceOrientation == "LANDSCAPE") {
			click(patBnrPg.IndentDecrease, "Indent Decrease");
			String AlertCount = getText(patBnrPg.AlertCnt, "Alert Count in banner");
			AssertStringEquals(AlertCount, "2", "Alert Count in banner");
		}
		
		String patientBannerName = getText(summPg.getPatientNameInBanner, "Patient Name in Banner Summary Page");
		String HRNBanner = getText(summPg.getHRNInBanner, "NHS Number in Banner Summary Page");
		String DOBBanner = getText(summPg.getDoBInBanner, "DOB in Banner Summary Page");
		
		AssertStringEquals((SORPatientSurname.toUpperCase()+", "+SORPatientForename+" ("+SORPatientTitle+")"), patientBannerName, "Patient Name in Patient banner");
		AssertStringEquals(SORHRN, HRNBanner, "HRN Number in Patient banner");
		AssertStringEquals(SORPatientDOB, DOBBanner, "DOB in Patient banner");
		
	  // Allergy Enhancement - No known allergies
	  AllergiesPage allergyPg = new AllergiesPage(driver, test);
	  reportStep("Allergy Enhancement - No known allergies", "INFO");
	  jsclick(summPg.tab_Summary, "Summary Tab");
	 // SelectPatient(SORHRN);
		Thread.sleep(3000);
		
		javascriptScroll(summPg.SummaryAllergyName, "Summary Allergy Name");
		String summaryAllergyCount = getText(summPg.SummaryAllergyCount, "Summary Allergy Count");
		AssertStringEquals(summaryAllergyCount, "1", "Summary Allergy Count");

		String summaryAllergyName = getText(summPg.getSummAllergyName, "Summary Allergy Name");
		AssertStringEquals(summaryAllergyName, "No Known Allergies", "Summary Allergy Name");
		
		jsclick(patBnrPg.MoreButton, "More button");
		Thread.sleep(5000);
		String AllergyCountMore = getText(patBnrPg.AllergyCntMore, "Allergy Count in More List");
		AssertStringEquals(AllergyCountMore, "1", "Allergy Count in More List");
			
		String AllergyNameMore = getText(patBnrPg.AllergyNameMore, "Allergy Name in More list");
		AssertStringEquals(AllergyNameMore, "No Known Allergies", "Allergy Name in More List");
		
		// Clicking on Alert link from More patient details
		click(patBnrPg.AllergyLinkMore, "Allergy link in More List");
		Thread.sleep(5000);
		
		 click(allergyPg.clickAllTab ,"All");
		 Thread.sleep(2000);
		   
		 int allAllergyCount = allergyPg.getAllAllergiesCount();
		 
		 String allAllergyCount1=String.valueOf(allAllergyCount);
		 AssertStringEquals(allAllergyCount1, "1", "Count of allergy in All tab");
			
		 click(allergyPg.clickActiveTab,"Active");
		 Thread.sleep(2000);
		   
		 int activeAllergyCount = allergyPg.getAllAllergiesCount();
		 
		 String activeAllergyCount1=String.valueOf(activeAllergyCount);
		 AssertStringEquals(activeAllergyCount1, "1", "Count of allergy in Active tab");
		
		jsclick(allergyPg.expandRec, "Expand Record");
		String NKAType = getText(allergyPg.NKAWebPASType, "No known Allergy Type");
		String NKARxn = getText(allergyPg.NKAReaction, "No known Allergy Reaction");
		String NKAAllergen = getText(allergyPg.NKAAllergen, "No known Allergy Allergen");
		String NKACategory = getText(allergyPg.NKACategory, "No known Allergy Category");
		String NKARecBy = getText(allergyPg.NKAWebPASRecBy, "No known Allergy Recorded By");
		String NKARecDate = getText(allergyPg.NKAWebPASRecOn, "No known Allergy Recorded Date");
		String NKASite = getText(allergyPg.NKASite, "No known Allergy Site");
		String NKAInitiatingHosp = getText(allergyPg.NKAInitiatingHosp, "No known Allergy Initiating hospital");
		String NKAReviewDate = getText(allergyPg.NKAReviewDate, "No known Allergy Review Date");
		String NKAWebPASOnset = getText(allergyPg.NKAWebPASOnset, "No known Allergy Onset Date");
		String NKAWebPASComment = getText(allergyPg.NKAWebPASComment, "No known Allergy Comment");
		
		AssertStringEquals(NKAType,SORNKAType,"No known Allergy Type");
		AssertStringEquals(NKAWebPASOnset,SORNKARecDate,"No known Allergy Onset Date");
		AssertStringEquals(NKARxn,SORNKAReaction+" ("+SORNKASeverity+")","No known Allergy Reaction(Severity)");
		AssertStringEquals(NKAAllergen,SORNKAType,"No known Allergy Allergen");
		AssertStringEquals(NKACategory,"Other","No known Allergy Category");
		AssertStringEquals(NKASite.toUpperCase(),SORNKASite.toUpperCase(),"No known Allergy Site");
		AssertStringEquals(NKAInitiatingHosp,"Hospital-H1","No known Allergy Initiating hospital");
		AssertStringContains(NKAReviewDate,SORNKARevDate,"No known Allergy Review Date");
		AssertStringEquals(NKARecBy,SORRecordedBy,"No known Allergy Recorded By");
		AssertStringEquals(NKARecDate,SORNKARecDate,"No known Allergy Recorded Date");
		AssertStringEquals(NKAWebPASComment,SORNKAComments,"No known Allergy Comment");
		
		reportStep("Script completed", "INFO");
		
	     driver.quit();
	  }  
  
	}