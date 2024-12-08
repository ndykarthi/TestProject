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
import com.dedalus.capages.AlertsPage;
import com.dedalus.capages.MenuPage;
import com.dedalus.capages.PatientBannerPage;
import com.dedalus.capages.SummaryPage;
import com.dedalus.restasssured.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Alert_Active extends RESTAssuredBase {

	JsonPath jsonPath;
  @Parameters({"PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
		
  @BeforeTest
  public void setData(String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation) throws IOException, InterruptedException, AWTException {
    testCaseName = "Patient Alerts validations"+PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
    testDescription = "This test is to verify the Active Alerts for a patient";
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
		String SORRecordedBy = getExposedValue(varResponse,"LCP","LV_CPFullName","LV_CPFullName");
	    String SORCPSurName = getExposedValue(varResponse, "LCP", "LV_CPSurName", "LV_CPSurName");
	    String SORCPForeName = 	getExposedValue(varResponse, "LCP", "LV_CPForeName", "LV_CPForeName");	
	    String SORCPTitle = getExposedValue(varResponse, "LCP", "LV_CPTitle", "LV_CPTitle");
	 
		String SORActiveAlertType = getExposedValue(varResponse,"CP","LV_IP_AlertType_RA","LV_IP_AlertType_RA");
		String SORActiveAlertName = getExposedValue(varResponse,"CP","LV_IP_AlertName_RA","LV_IP_AlertName_RA");
		String SORActiveAlertSeverity = getExposedValue(varResponse,"CP","LV_IP_Severity_RA","LV_IP_Severity_RA");
		String SORActiveAlertSeverityLevel = getExposedValue(varResponse,"CP","LV_IP_SeverityLevel_RA","LV_IP_SeverityLevel_RA");
		String SORActiveAlertOnsetDate = getExposedValue(varResponse,"CP","LV_IP_dtmOnsetDateRA","LV_IP_dtmOnsetDateRA");
		String SORActiveAlertDesc = getExposedValue(varResponse,"CP","LV_IP_AlertDescrptnRA_Modify","LV_IP_AlertDescrptnRA_Modify");
		String SORActiveAlertRecDtm = getExposedValue(varResponse,"CP","LV_IP_dtmOnsetDateRA","LV_IP_dtmOnsetDateRA");
		String SORActiveAlertRevDate = getExposedValue(varResponse,"CP","LV_IP_dtmAlertReviewDateRA","LV_IP_dtmAlertReviewDateRA");
		String SORActiveAlertSite = getExposedValue(varResponse,"CP","LV_IP_Site_RA","LV_IP_Site_RA");
		String SORActiveAlertStatus = "Active";
		String SORActiveAlertRecBy = SORRecordedBy;
		//SORActiveAlertSeverity = SORActiveAlertSeverityLevel+" - "+SORActiveAlertSeverity.toLowerCase();
		SORActiveAlertSeverity = SORActiveAlertSeverityLevel+" - severe";
		SORPatAddLine3 = convertCamelCase(SORPatAddLine3);
		String SORPatAdd = SORPatAddLine1+", "+SORPatAddLine2+" "+SORPatAddLine3+" "+SORPatAddLine4+" "+SORPostCode;
	    String SORHospitalName = getExposedValue(varResponse, "LCP", "GV_HospitalName", "GV_HospitalName");
	    String SORHospitalID = getExposedValue(varResponse, "LCP", "GV_HospitalID", "GV_HospitalID");
	    String SORInitiatingHospital = SORHospitalName;
	    
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
		SelectPatient(PatientFN);
		Thread.sleep(5000);
		
		
		String patientBannerName = getText(summPg.getPatientNameInBanner, "Patient Name in Banner Summary Page");
		String HRNBanner = getText(summPg.getHRNInBanner, "NHS Number in Banner Summary Page");
		String DOBBanner = getText(summPg.getDoBInBanner, "DOB in Banner Summary Page");
		AssertStringEquals((SORPatientSurname.toUpperCase()+", "+SORPatientForename+" ("+SORPatientTitle+")"), patientBannerName, "Patient Name in Patient banner");
		AssertStringEquals(SORHRN, HRNBanner, "HRN Number in Patient banner");
		AssertStringEquals(SORPatientDOB, DOBBanner, "DOB in Patient banner");

		if(DeviceOrientation.equals("LANDSCAPE")) {
		String AlertIconBanner = getIconName(summPg.getBannerAlertIcon, "Alert Icon in Banner");
		String AlertCntBanner = getText(summPg.getBannerAlertCount, "Alert Count in Banner");	
		AssertStringEquals("alert-circle", AlertIconBanner, "Alert Icon in Banner");
		AssertStringEquals("1", AlertCntBanner, "Alert Count in Banner");
		}
		

		
		jsclick(patBnrPg.MoreButton, "More button");
		Thread.sleep(5000);
		String AlertCountMore = getText(patBnrPg.AlertCntMore, "Alert Count in More List");
		AssertStringEquals(AlertCountMore, "1", "Alert Count in More List");
			
		String AlertName1More = getText(patBnrPg.AlertName1More, "1st Alert Name in More list");
		
		if(AlertName1More.equals(SORActiveAlertName)) {
			reportStep("Alert Name in Patient Banner More List - "+SORActiveAlertName, "PASS");
		}else {
			reportStep("Alert Name in Patient Banner More List is not matcing", "FAIL");
		}
		
		// Clicking on Alert link from More patient details
		click(patBnrPg.AlertLinkMore, "Alert link in More List");
		Thread.sleep(5000);
		VerifySelectedTab("Alerts",DeviceName);	
		jsclick(summPg.tab_Summary, "Summary Tab");
		Thread.sleep(3000);
		
		// Clicking on Alert tile from Summary page
		String SummaryAlertName = getText(alertPg.SummaryAlertName, "Summary Alert Name");
		String SummaryAlertType = getText(alertPg.SummaryAlertType, "Summary Alert Type");
		String SummaryAlertOnsetDate = getText(alertPg.SummaryAlertOnsetDate, "Summary Alert OnsetDate");
		
		AssertStringEquals(SummaryAlertName, SORActiveAlertName, "Summary Alert Name");
		AssertStringContains(SummaryAlertType, SORActiveAlertType, "Summary Alert Type");
		AssertStringContains(SummaryAlertType, SORActiveAlertSeverity.toLowerCase(), "Summary Alert Severity");
		AssertStringContains(SummaryAlertOnsetDate, SORActiveAlertRecDtm, "Summary Alert Onset Date");
		
		javascriptScroll(summPg.SummaryAlertsCount, "Summary Alert Count");
		click(summPg.SummaryAlertsCount, "Summary Alert Count");
		Thread.sleep(3000);
		VerifySelectedTab("Alerts",DeviceName);
		jsclick(summPg.tab_Summary, "Summary Tab");
		Thread.sleep(3000);
		
	 	String summaryAlertCount = getText(summPg.SummaryAlertsCount, "Summary Alerts Count");
		String summaryAlertIcon = getIcon(summPg.AlertIcon, "Summary Alert Icon");
		System.out.println("Alert Icon: "+summaryAlertIcon);
		AssertStringEquals(summaryAlertCount, "1", "Summary Alert Count");
		AssertStringEquals((summaryAlertIcon.trim()), "alert-circle", "Summary Alert Icon");

	  // Alerts
		// All tab - all encounter
		 javascriptScroll(summPg.tab_Summary, "Summary Tab");
	    click(summPg.tab_Alerts, "Alerts Tab");
	    Thread.sleep(5000);

	  click(alertPg.allFauxtab,"All");
	  Thread.sleep(2000);
	 
	  int allActiveCount = alertPg.getAlertActiveCount();
	  int allINActiveCount = alertPg.getAlertINActiveCount();
	  int allCount = allActiveCount+allINActiveCount;
	  String ActiveTabCnt1=String.valueOf(allCount);
	  AssertStringEquals(ActiveTabCnt1, "1", "Count of alerts in All tab");
		
	  //Active Tab
	  click(alertPg.activeFauxtab,"Active tab");
		
	  Thread.sleep(2000);
	  int ActiveCount = alertPg.getAlertActiveCount();
	  String ActiveCount1=String.valueOf(ActiveCount);
	  AssertStringEquals(ActiveCount1, "1", "Count of alerts in Active tab");
			
	  String AlertName = getText(alertPg.AlertName1, "AlertName");
	  String AlertStatus = getText(alertPg.AlertStatus1, "AlertStatus");
	  String AlertType = getText(alertPg.AlertType1, "AlertType");
	  String AlertSeverity = getText(alertPg.AlertSeverity1, "AlertSeverity");
	  String AlertOnset = getText(alertPg.AlertOnset1, "AlertOnset");
	  jsclick(alertPg.ExpandAlert1,"Expand Alert record");
	  Thread.sleep(3000);
	  String AlertRecordedOn = getText(alertPg.AlertRecordedOn, "Alert RecordedDateTime");
	  String AlertRecordedBy = getText(alertPg.AlertRecordedBy, "Alert RecordedBy");
	  String AlertDesc = getText(alertPg.AlertDesc1, "Alert Description");
	  String AlertRevDate = getText(alertPg.AlertRevDtm, "Alert Review Date Time");
	  String AlertSite = getText(alertPg.AlertSite, "Alert Site");
	  String AlertReqBy = getText(alertPg.AlertRequestBy, "Alert Requested By");
	  String AlertInitHosp = getText(alertPg.AlertInitHospital, "Alert Initiating Hospital");
	  
	  // Validation
	  AssertStringEquals(SORActiveAlertName, AlertName, "Alert Name");
	  AssertStringEquals(SORActiveAlertType, AlertType, "Alert Type");
	  AssertStringEquals(SORActiveAlertStatus, AlertStatus, "Alert Status");
	  AssertStringEquals(SORActiveAlertSeverity, AlertSeverity, "Alert Severity");
	  AssertStringEquals(SORActiveAlertOnsetDate, AlertOnset, "Alert Onset");
	  AssertStringEquals(SORActiveAlertDesc, AlertDesc, "Alert Description");
	  AssertStringEquals(SORActiveAlertRecBy+" .", AlertRecordedBy, "Alert Recorded By");
//	  AssertStringEquals(SORActiveAlertRecBy, AlertRecordedBy, "Alert Recorded By");
	  AssertStringEquals(SORActiveAlertRecDtm, AlertRecordedOn, "Alert Recorded On");
	  AssertStringContains(AlertRevDate, SORActiveAlertRevDate, "Alert Review Date");
	  AssertStringEquals(SORActiveAlertSite, AlertSite, "Alert Site");
	  AssertStringEquals(SORCPTitle+" "+SORCPForeName+" "+SORCPSurName, AlertReqBy, "Alert Requested By HCP");
	  AssertStringEquals(SORInitiatingHospital, AlertInitHosp, "Alert Initiating Hospital");
	   	
	  reportStep("Script completed", "INFO");
		
	  driver.quit();
	  }  
  
	}