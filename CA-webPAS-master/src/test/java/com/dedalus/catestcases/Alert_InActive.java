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

import io.restassured.response.Response;


public class Alert_InActive extends RESTAssuredBase {

  @Parameters({"PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
		
  @BeforeTest
  public void setData(String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation) throws IOException, InterruptedException, AWTException {
    testCaseName = "Patient Alerts validations"+PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
    testDescription = "This test is to verify the InActive Alerts for a patient";
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
		
		String SORCloseAlertName = getExposedValue(varResponse,"CP","LV_IP_AlertName_RAClose","LV_IP_AlertName_RAClose");
		String SORCloseAlertType = getExposedValue(varResponse,"CP","LV_IP_AlertType_RAClose","LV_IP_AlertType_RAClose");
		String SORCloseAlertOnsetDate = getExposedValue(varResponse,"CP","LV_IP_OnsetDateRAClose","LV_IP_OnsetDateRAClose");
		String SORCloseAlertDesc = getExposedValue(varResponse,"CP","LV_IP_AlertDescrptnRAClose","LV_IP_AlertDescrptnRAClose");
		String SORCloseAlertSeverity = getExposedValue(varResponse,"CP","LV_IP_SeverityRAClose","LV_IP_SeverityRAClose");
		String SORCloseAlertSeverityLevel = getExposedValue(varResponse,"CP","LV_IP_SeverityLevel_RAClose","LV_IP_SeverityLevel_RAClose");
		String SORCloseAlertRecDate = getExposedValue(varResponse,"CP","LV_IP_OnsetDateRAClose","LV_IP_OnsetDateRAClose");
		String SORCloseAlertRevDate = getExposedValue(varResponse,"CP","LV_IP_dtmAlertReviewDateRAClose","LV_IP_dtmAlertReviewDateRAClose");
		String SORCloseAlertSite = getExposedValue(varResponse,"CP","LV_IP_Site_RAClose","LV_IP_Site_RAClose");
		String SORCloseAlertEndDate = getExposedValue(varResponse,"CP","LV_IP_dtmCloseAlertDate","LV_IP_dtmCloseAlertDate");
		String SORCloseAlertRecordedBy = SORRecordedBy;
		SORCloseAlertSeverity = SORCloseAlertSeverityLevel+" - "+SORCloseAlertSeverity;
		SORCloseAlertSeverity = SORCloseAlertSeverityLevel+" - moderate";
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
			click(patBnrPg.IndentDecrease, "Indent Decrease");	
		}
/*		
		jsclick(patBnrPg.MoreButton, "More button");
		Thread.sleep(5000);
		String AlertCountMore = getText(patBnrPg.AlertCntMore, "Alert Count in More List");
		AssertStringEquals(AlertCountMore, "0", "Alert Count in More List");
*/		
		// Clicking on Alert tile from Summary page
		click(summPg.SummaryAlertsCount, "Summary Alert Count");
		Thread.sleep(3000);
		VerifySelectedTab("Alerts",DeviceName);
		jsclick(summPg.tab_Summary, "Summary Tab");
		Thread.sleep(3000);
		
	 	String summaryAlertCount = getText(summPg.SummaryAlertsCount, "Summary Alerts Count");
		String summaryAlertIcon = getIcon(summPg.AlertIcon, "Summary Alert Icon");
		System.out.println("Alert Icon: "+summaryAlertIcon);
		AssertStringEquals(summaryAlertCount, "0", "Summary Alert Count");
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
		
	//INACTIVE tab
		click(alertPg.inactiveFauxtab,"Inactive tab");
		Thread.sleep(10000);
		int INActiveCount = alertPg.getAlertINActiveCount();
		String INActiveCount1=String.valueOf(INActiveCount);
		AssertStringEquals(INActiveCount1, "1", "Count of alerts in Inactive tab");
			
		String ClosedAlertName = getText(alertPg.ClosedAlertName, "Closed Alert Name");
	  //String ClosedAlertStatus = getText(alertPg.AlertStatus1, "Closed Alert Status");
	  String ClosedAlertType = getText(alertPg.ClosedAlertType, "Closed Alert Type");
	  String ClosedAlertSeverity = getText(alertPg.ClosedAlertSev, "Closed Alert Severity");
	  String ClosedAlertOnset = getText(alertPg.ClosedAlertOnset, "Closed Alert Onset");
	  jsclick(alertPg.expandClosedRec,"Expand Closed Alert record");
	  Thread.sleep(10000);
	  String ClosedAlertRecordedDateTime = getText(alertPg.ClosedAlertRecOn, "Closed Alert RecordedDateTime");
	  String ClosedAlertRecordedBy = getText(alertPg.ClosedAlertRecordedBy, "Closed Alert RecordedBy");
	  String ClosedAlertDesc = getText(alertPg.ClosedAlertDesc,"Closed Alert Description");
	  String ClosedAlertSite = getText(alertPg.ClosedAlertSite, "Alert Site");
	  String ClosedReqBy = getText(alertPg.ClosedAlertRequestBy, "Closed Alert Requested By");
	  String ClosedAlertIniHosp = getText(alertPg.ClosedAlertInitHospital, "Closed Alert Initiating Hospital");
	  String ClosedAlertRevDate = getText(alertPg.ClosedAlertRevDate, "Closed Alert Review Date");
	  String ClosedAlertEndDate = getText(alertPg.ClosedAlertEndDate, "Closed Alert Review Date");
	  
	  jsclick(alertPg.expandClosedRec,"Collapse Closed Alert record");
	 
	  // Validation
	  AssertStringEquals(SORCloseAlertName, ClosedAlertName, "Closed Alert Name");
	  AssertStringEquals(SORCloseAlertType, ClosedAlertType, "Closed Alert Type");
	  AssertStringEquals(SORCloseAlertRecordedBy+" .", ClosedAlertRecordedBy, "Closed Alert Recorded By");
//	  AssertStringEquals(SORCloseAlertRecordedBy, ClosedAlertRecordedBy, "Closed Alert Recorded By");
	  AssertStringEquals(SORCloseAlertSeverity, ClosedAlertSeverity, "Closed Alert Severity");
	  AssertStringEquals(SORCloseAlertOnsetDate, ClosedAlertOnset, "Closed Alert Onset");
	  AssertStringEquals(SORCloseAlertDesc, ClosedAlertDesc, "Closed Alert Description");  
	  AssertStringEquals(SORCloseAlertRecDate, ClosedAlertRecordedDateTime, "Closed Alert Recorded DateTime");
	  AssertStringEquals(SORCloseAlertSite, ClosedAlertSite, "Closed Alert Site");
	  AssertStringEquals(SORCloseAlertEndDate, ClosedAlertEndDate, "Closed Alert End Date");
	  AssertStringEquals(SORCPTitle+" "+SORCPForeName+" "+SORCPSurName, ClosedReqBy, "Closed Alert Requested By");
	  AssertStringContains(SORInitiatingHospital, ClosedAlertIniHosp, "Closed Alert Initiating Hospital");
	  AssertStringEquals(ClosedAlertRevDate, SORCloseAlertRevDate, "Closed Alert Review Date");
	 	
	  reportStep("Script completed", "INFO");
		
	     driver.quit();
	  }  
  
	}