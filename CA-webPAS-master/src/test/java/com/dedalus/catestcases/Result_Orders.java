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
import com.dedalus.capages.ResultsPage;
import com.dedalus.capages.SummaryPage;
import com.dedalus.restasssured.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Result_Orders extends RESTAssuredBase {

	Response response,response1;
	JsonPath jsonPath,jsonPath1;
	@Parameters({"PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
	@BeforeTest
	public void setData(String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation) throws IOException, InterruptedException, AWTException {
		 testCaseName = "Results - Orders Tab validations "+PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
		    testDescription = "This test is to verify Orders Tab in results";
		    nodes = PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
		    category = "Regression";
		    authors = "Kavitha";
	}



	@Parameters({"SOR","PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})

	@Test
	public void ViewResultTest(String SOR, String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation, ITestContext context)
			throws InterruptedException, IOException, ClassNotFoundException, SQLException, ParseException, AWTException {

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
		String SORPatientDOB = getExposedValue(varResponse,"LCP","TD_dtmDOB","TD_dtmDOB");
		String SORPatDOB = getExposedValue(varResponse, "CP","TD_dtmDOB","TD_dtmDOB");
		String SORHRN = getExposedValue(varResponse,"LCP","LV_HRN","LV_HRN");

		String SORConfigURL = getExposedValue(varResponse,"LCP","GV_ATCA_ConfigURL","GV_ATCA_ConfigURL");
		String SORPatAddLine1 = getExposedValue(varResponse,"LCP","LV_AddressLine1","LV_AddressLine1");
		String SORPatAddLine2 = getExposedValue(varResponse,"LCP","LV_AddressLine2","LV_AddressLine2"); 	
		String SORPatAddLine3 = getExposedValue(varResponse,"LCP","LV_AddressLine3","LV_AddressLine3");
		String SORPatAddLine4 = getExposedValue(varResponse,"LCP","LV_AddressLine4","LV_AddressLine4");
		String SORPostCode = getExposedValue(varResponse,"LCP","TD_strPostCode","TD_strPostCode");
		SORPatAddLine3 = convertCamelCase(SORPatAddLine3);
		String SORPatAdd = SORPatAddLine1+", "+SORPatAddLine2+" "+SORPatAddLine3+" "+SORPatAddLine4+" "+SORPostCode;
		
		String SOROrder1 = getExposedValue(varResponse, "CP", "LV_strResultName1", "LV_strResultName1");
		String SOROrder2 = getExposedValue(varResponse, "CP", "LV_strResultName2", "LV_strResultName2");
		String SOROrder3 = getExposedValue(varResponse, "CP", "LV_strResultName3", "LV_strResultName3");
		String SOROrder4 = getExposedValue(varResponse, "CP", "LV_strResultName4", "LV_strResultName4");
		String SOROrder5 = getExposedValue(varResponse, "CP", "LV_strResultName5", "LV_strResultName5");
		String SOROrder6 = getExposedValue(varResponse, "CP", "LV_strResultName6", "LV_strResultName6");
		String SOROrder7 = getExposedValue(varResponse, "CP", "LV_strResultName7", "LV_strResultName7");
		String SOROrder8 = getExposedValue(varResponse, "CP", "LV_strResultName8", "LV_strResultName8");
		String SOROrder9 = getExposedValue(varResponse, "CP", "LV_strResultName9", "LV_strResultName9");
		String SOROrder10 = getExposedValue(varResponse, "CP", "LV_strResultName10", "LV_strResultName10");
		String SOROrder11 = getExposedValue(varResponse, "CP", "LV_strResultName11", "LV_strResultName11");
		
		String SORReqDate = getExposedValue(varResponse, "CP", "LV_StartDateTime", "LV_StartDateTime");
		String SORLab = getExposedValue(varResponse, "CP", "LV_Lab", "LV_Lab");
		String SORDiagGp = "Diagnostic Procedure";
		String SORPriority = "Routine";
		String SORStatus = getExposedValue(varResponse, "CP", "LV_Status", "LV_Status");
		if(SORStatus.equals("Scheduled")) {
			SORStatus = "active";
		}
		String SORCopyTo = getExposedValue(varResponse, "CP", "LV_strCopyTo", "LV_strCopyTo");
		String SOROrdProvCP = getExposedValue(varResponse, "CP", "LV_strOrderingPrvdr", "LV_strOrderingPrvdr");
		String SORReqBy = getExposedValue(varResponse, "CP", "LV_strRequestedBY", "LV_strRequestedBY");; 
		String SORReqOn = getExposedValue(varResponse, "CP", "LV_ReqOnDateTime", "LV_ReqOnDateTime");
		String SORClinicalNotes = getExposedValue(varResponse, "CP", "LV_strClinicalNotes", "LV_strClinicalNotes");
		String SOROrdProvSN = SOROrdProvCP.split(" ")[1];
		String SOROrdProvFN = SOROrdProvCP.split(" ")[0];
		String SOROrdProv = SOROrdProvSN+" "+SOROrdProvFN;
		
		reportStep("PASID : "+SORHRN, "INFO");

		//Login to CAUK
		startCAUKApp(SOR, PlatformName,DeviceName,PlatformVersion,DeviceOrientation,SORConfigURL);
		
		Thread.sleep(8000);

		MenuPage mnuPg = new MenuPage(driver, test);
		SummaryPage summPg = new SummaryPage(driver, test);
		ResultsPage rsltPg = new ResultsPage(driver, test);

		click(mnuPg.clickMenu, "Menu");
		 click(mnuPg.menu_Search,"Search");
		 Thread.sleep(3000);
		 System.out.println("Patient ID: "+SORHRN);
//		 AdvanceSearchPatient(SORPatientSurname, SORPatientForename, SORPatientGender, "27/Nov/1994");
		 AdvanceSearchPatient(SORPatientSurname, SORPatientForename, SORPatientGender, SORPatDOB);
		 SearchPatient(SORHRN);
		 VerifyPatientDetails_SearchListWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle,SORPatientDOB,SORHRN, SORPatAdd);
		 VerifyPatientDetails_SearchListMoreWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle, SORPatientGender,SORPatientDOB,SORHRN);
		 String PatientFN = SORPatientSurname.toUpperCase()+", "+SORPatientForename;
		 SelectPatient(PatientFN);
		 Thread.sleep(5000);
	 
		 String patientBannerName = getText(summPg.getPatientNameInBanner, "Patient Name in Banner Summary Page");
		 String NHSBanner = getText(summPg.getHRNInBanner, "HRN Number in Banner Summary Page");
		 String DOBBanner = getText(summPg.getDoBInBanner, "DOB in Banner Summary Page");
			
		 AssertStringContains(patientBannerName, PatientFN, "Patient Name in Patient banner");
		 AssertStringEquals(SORHRN, NHSBanner, "NHS Number in Patient banner");
	     AssertStringEquals(SORPatientDOB, DOBBanner, "DOB in Patient banner");
		
		String ResIcon = getIconName(summPg.ResultIconWebPAS, "Result Icon in Summary Page");
		AssertStringEquals(ResIcon, "flask", "Flask icon is present for Result");
		
		 String ResDispPerd = getText(summPg.ResultDisplayPeriodWebPAS, "Result Display period");
		 AssertStringContains(ResDispPerd, "LAST 2 WEEKS", "Last 2 Weeks display is present for Result");
	    
		 String SummResCnt = getText(summPg.ResultCntWebPAS, "Results Count");
		 AssertStringEquals(SummResCnt, "0", "Result Count in Summary Page");
		 
		 String ReadCnt = getText(summPg.Results_ReadCount, "Read Results Count");		 
		 String UnreadCnt = getText(summPg.Results_UnreadCount, "Unead Results Count");
		 String PendingOrders = getText(summPg.Results_PendingOrdCount, "Pending Results Order Count");
		 
		 AssertStringEquals(ReadCnt, "0", "Read Results Count in Summary Page");
		 AssertStringEquals(UnreadCnt, "0", "Unread Results Count in Summary Page");	
		 AssertStringEquals(PendingOrders, "11", "Pending Orders Count in Summary Page");
		 
		// Clicking on Result tile from Summary page
		click(summPg.ResultCntWebPAS, "Summary Result Count");
		Thread.sleep(3000);
		VerifySelectedTab("Results",DeviceName);
		jsclick(summPg.tab_Summary, "Summary Tab");
		Thread.sleep(3000);
			
		// Clicking on Result tab EPR
		click(summPg.tab_WebPASResults,"Results EPR Tab");
		VerifySelectedTab("Results",DeviceName);
		
		// Orders Tab
		click(rsltPg.OrdersTab,"Orders");
		Thread.sleep(5000);
		VerifyOrder(SOROrder1,SORReqDate,SORLab,SORDiagGp,SORPriority,SORStatus,SORCopyTo,SOROrdProv,SORReqBy,SORReqOn,SORClinicalNotes);
		VerifyOrder(SOROrder2,SORReqDate,SORLab,SORDiagGp,SORPriority,SORStatus,SORCopyTo,SOROrdProv,SORReqBy,SORReqOn,SORClinicalNotes);
		VerifyOrder(SOROrder3,SORReqDate,SORLab,SORDiagGp,SORPriority,SORStatus,SORCopyTo,SOROrdProv,SORReqBy,SORReqOn,SORClinicalNotes);
		VerifyOrder(SOROrder4,SORReqDate,SORLab,SORDiagGp,SORPriority,SORStatus,SORCopyTo,SOROrdProv,SORReqBy,SORReqOn,SORClinicalNotes);
		VerifyOrder(SOROrder5,SORReqDate,SORLab,SORDiagGp,SORPriority,SORStatus,SORCopyTo,SOROrdProv,SORReqBy,SORReqOn,SORClinicalNotes);
		VerifyOrder(SOROrder6,SORReqDate,SORLab,SORDiagGp,SORPriority,SORStatus,SORCopyTo,SOROrdProv,SORReqBy,SORReqOn,SORClinicalNotes);
		VerifyOrder(SOROrder7,SORReqDate,SORLab,SORDiagGp,SORPriority,SORStatus,SORCopyTo,SOROrdProv,SORReqBy,SORReqOn,SORClinicalNotes);
		VerifyOrder(SOROrder8,SORReqDate,SORLab,SORDiagGp,SORPriority,SORStatus,SORCopyTo,SOROrdProv,SORReqBy,SORReqOn,SORClinicalNotes);
		VerifyOrder(SOROrder9,SORReqDate,SORLab,SORDiagGp,SORPriority,SORStatus,SORCopyTo,SOROrdProv,SORReqBy,SORReqOn,SORClinicalNotes);
		VerifyOrder(SOROrder10,SORReqDate,SORLab,SORDiagGp,SORPriority,SORStatus,SORCopyTo,SOROrdProv,SORReqBy,SORReqOn,SORClinicalNotes);
		VerifyOrder(SOROrder11,SORReqDate,SORLab,SORDiagGp,SORPriority,SORStatus,SORCopyTo,SOROrdProv,SORReqBy,SORReqOn,SORClinicalNotes);
		
		reportStep("Script completed", "INFO");	
		driver.quit();
	  }
	  
	  
	}