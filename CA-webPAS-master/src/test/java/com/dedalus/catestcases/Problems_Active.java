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

import com.dedalus.capages.EncountersPage;
import com.dedalus.capages.MenuPage;
import com.dedalus.capages.ProblemsPage;
import com.dedalus.restasssured.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Problems_Active extends RESTAssuredBase{
	Response response;
	JsonPath jsonPath;

	@Parameters({"PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
	@BeforeTest
	public void setData(String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation) throws IOException, InterruptedException, AWTException {
		testCaseName = "Active problems "+PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation; 
		testDescription = "The following test case verify Active problems";
		nodes = PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
		category = "Regression";
		authors = "Karthi";
	}



	@Parameters({"SOR","PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})

	@Test
	public void testCaseStart(String SOR, ITestContext context, String PlatformName, String DeviceName, String PlatformVersion, String DeviceOrientation)
			throws InterruptedException, IOException, ClassNotFoundException, SQLException, ParseException, AWTException {

		//		========================================= VIRTUOSO API =========================================

		String accessToken = "Bearer 72db341d-cbdb-4d6d-b521-df59d5b2a571";
		ISuite suite = context.getSuite();
		int suiteID = (int) suite.getAttribute("SuiteID");
		int intJobID = (int) suite.getAttribute("JobID"); 
		String virtuosoOutcome = (String) suite.getAttribute("VirtuosoOutcome");

		HashMap<String, String> alertHeaderMap = getHeaderMap(accessToken);
		Response varResponse = getWithHeader(alertHeaderMap, "https://api.virtuoso.qa/api/testsuites/execution?suiteId="+suiteID+"&jobId="+intJobID+"&includeJourneyDetails=true");
		varResponse.prettyPrint();

		if(virtuosoOutcome.equals(("FAIL"))) 
		{
			reportStep("Virtuoso Journey Outcome: "+virtuosoOutcome, "FAIL",false);
		}
		Assert.assertEquals(virtuosoOutcome, "PASS","Virtuoso Journey Pass");
		//		========================================= VIRTUOSO API =========================================

		String SORConfigURL = getExposedValue(varResponse, "LCP", "GV_ATCA_ConfigURL", "SORConfigURL");
		String SORPatientSurname = getExposedValue(varResponse, "LCP","TD_strSurname","TD_strSurname");
		String SORPatientForename = getExposedValue(varResponse, "LCP","TD_strForename","TD_strForename");
		String SORPatientDOB = getExposedValue(varResponse, "LCP","TD_dtmDOB","TD_dtmDOB");
		String SORHRN = getExposedValue(varResponse, "LCP","LV_HRN","LV_HRN");
		String SORPatientGender = getExposedValue(varResponse,"LCP","TD_strGender","TD_strGender");
		String SORPatAddLine1 = getExposedValue(varResponse, "LCP","LV_AddressLine1","LV_AddressLine1");
		String SORPatAddLine2 = getExposedValue(varResponse, "LCP","LV_AddressLine2","LV_AddressLine2"); 
		String SORPatAddLine3 = getExposedValue(varResponse, "LCP","LV_AddressLine3","LV_AddressLine3");
		String SORPatAddLine4 = getExposedValue(varResponse, "LCP","LV_AddressLine4","LV_AddressLine4");
		String SORRecordedBy = getExposedValue(varResponse, "LCP","LV_CPFullName","LV_strRecordedBy");
		String SORENCID = getExposedValue(varResponse, "CP", "LV_IP_EncID", "SORENCID");

		String SOROnsetCurrentDate = getExposedValue(varResponse, "CP", "ActiveOnsetDate", "SOROnsetCurrentDate");
		SOROnsetCurrentDate = SOROnsetCurrentDate.replace(" ", "-");
		String SOROnsetPast2Date = getExposedValue(varResponse, "CP", "LV_strpastDate", "SOROnsetPast2Date");
		SOROnsetPast2Date = SOROnsetPast2Date.replace(" ", "-");
		String SOROnsetPast4Date = getExposedValue(varResponse, "CP", "LV_strpastDatefour", "SOROnsetPast4Date");
		SOROnsetPast4Date = SOROnsetPast4Date.replace(" ", "-");

		String SORActiveProblemType = getExposedValue(varResponse, "CP", "LV_ActiveProblemType", "ActiveProblemType");
		String SORActiveProblemDescription = getExposedValue(varResponse, "CP", "LV_ActiveProblemDescription", "ActiveProblemDescription");
		
		String SORCurrentProblemType = getExposedValue(varResponse, "CP", "LV_CurrentProblemType", "CurrentProblemType");
		String SORCurrentProblemDescription = getExposedValue(varResponse, "CP", "LV_CurrentProblemDescription", "CurrentProblemDescription");

		String SORHistoryActiveProblemType = getExposedValue(varResponse, "CP", "LV_HistoryActiveProblemType", "HistoryActiveProblemType");
		String SORHistoryActiveProblemDescription = getExposedValue(varResponse, "CP", "LV_HistoryActiveProblemDescription", "HistoryActiveProblemDescription");
		
		String SORHistoryCurrentProblemType = getExposedValue(varResponse, "CP", "LV_HistoryCurrentProblemType", "HistoryCurrentProblemType");
		String SORHistoryCurrentProblemDescription = getExposedValue(varResponse, "CP", "LV_HistoryCurrentProblemDescription", "HistoryCurrentProblemDescription");

		String SORCurrentDate = getExposedValue(varResponse,"CP","LV_Date","LV_Date");
		
		String SORIPWard = getExposedValue(varResponse, "LCP", "GV_IP_Ward", "GV_IP_Ward");
		String SORHospitalName = getExposedValue(varResponse, "LCP", "GV_HospitalName", "GV_HospitalName");
	    String SORHospitalID = getExposedValue(varResponse, "LCP", "GV_HospitalID", "GV_HospitalID");
	    String SORInitiatingHospital = SORHospitalName+"-"+SORHospitalID;
	    
			//Login to CAUK
			startCAUKApp(SOR,PlatformName,DeviceName,PlatformVersion,DeviceOrientation,SORConfigURL);
			Thread.sleep(8000);
			MenuPage mnuPg = new MenuPage(driver, test);
			EncountersPage enc = new EncountersPage(driver, test);
			ProblemsPage problems = new ProblemsPage(driver, test);

			click(mnuPg.clickMenuNew, "Menu");
			Thread.sleep(3000);
			click(mnuPg.menu_Search,"Search");
			Thread.sleep(3000);
			SearchNSelectPatient(SORHRN);
			Thread.sleep(10000);

			javascriptScroll(problems.summaryProblemTile, "Summary Problems tile");
			verifyTextDisplay(problems.summaryProblemTile);
			String summaryProblemsCount = getText(problems.getsummaryProblemsCount, "Summary view Active Problems Count");
			if (summaryProblemsCount.equals("4")) {
				reportStep("Summary problems count is [" + summaryProblemsCount + "] matching with count 4", "PASS");
				System.out.println("Summary problems count is [" + summaryProblemsCount + "] matching with count 4");
			} else {
				reportStep("Summary problems count is [" + summaryProblemsCount + "] not matching with count 4", "FAIL");
				System.out.println("Summary problems count is [" + summaryProblemsCount + "] not matching with count 4");
			}
			
			String summaryProblem1Name = getText(problems.getsummaryActiveProblem1, "Summary view Active Problem 1");
			AssertStringEquals( summaryProblem1Name, SORCurrentProblemDescription,"summaryProblem1Name");

			String summaryProblem1Type = getText(problems.getsummaryActiveProblem1Type, "Summary view Active Problem 1 - Type");
			AssertStringEquals(summaryProblem1Type.replace("Type: ",""), SORCurrentProblemType, "summaryProblem1Type");

			String summaryProblem1DateObtained = getText(problems.getsummaryActiveProblem1OnsetDate, "Summary view Active Problem 1 - Date obtained");
			AssertStringContains(summaryProblem1DateObtained, SOROnsetCurrentDate, "summaryProblem1DateObtained");

			String summaryProblem2Name = getText(problems.getsummaryActiveProblem2, "Summary view Active Problem 2");
			AssertStringEquals( summaryProblem2Name, SORActiveProblemDescription,"summaryProblem2Name");

			String summaryProblem2Type = getText(problems.getsummaryActiveProblem2Type, "Summary view Active Problem 2 - Type");
			AssertStringEquals(summaryProblem2Type.replace("Type: ",""), SORActiveProblemType, "summaryProblem2Type");

			String summaryProblem2DateObtained = getText(problems.getsummaryActiveProblem2OnsetDate, "Summary view Active Problem 2 - Date obtained");
			AssertStringContains(summaryProblem2DateObtained, SOROnsetCurrentDate, "summaryProblem2DateObtained");

			String summaryProblem3Name = getText(problems.getsummaryActiveProblem3, "Summary view Active Problem 3");
			AssertStringEquals( summaryProblem3Name, SORHistoryActiveProblemDescription,"summaryProblem3Name");

			String summaryProblem3Type = getText(problems.getsummaryActiveProblem3Type, "Summary view Active Problem 3 - Type");
			AssertStringEquals(summaryProblem3Type.replace("Type: ",""), SORHistoryActiveProblemType, "summaryProblem3Type");

			String summaryProblem3DateObtained = getText(problems.getsummaryActiveProblem3OnsetDate, "Summary view Active Problem 3 - Date obtained");
			AssertStringContains(summaryProblem3DateObtained, SOROnsetPast2Date, "summaryProblem3DateObtained");

			click(mnuPg.selectEnc,"Encounter");
			Thread.sleep(5000); 
			String CurrentEnc1ID = getText(enc.ipEncID, "CurrentEncounterID");
			//AssertStringContains(CurrentEnc1ID, SORENCID, "CurrentEnc1ID");
			javascriptScroll(problems.clickProblemsTab, "Problems icon");
			jsclick(problems.clickProblemsTab, "Problems icon");
			Thread.sleep(15000);
			//Item count validation 

			//Active TAB
			verifyTextDisplay(problems.clickActiveTab);
			Thread.sleep(2000);
			int SummaryProblemsCount = Integer.valueOf(summaryProblemsCount);
			int ActiveProblemsCount = problems.getProblemsCount();
			if (SummaryProblemsCount == ActiveProblemsCount) {
				reportStep("Summary Count of problem is [" + SummaryProblemsCount + "] matching with Active problem count " + ActiveProblemsCount + "", "PASS");
				System.out.println("Summary Count of problem is [" + SummaryProblemsCount + "] matching with Active problem count " + ActiveProblemsCount);
			} else {
				reportStep("Summary Count of problem is " + SummaryProblemsCount + " not matching with Active problem count " + ActiveProblemsCount + "", "FAIL");
				System.out.println("Summary Count of problem is [" + SummaryProblemsCount + "] not matching with Active problem count " + ActiveProblemsCount);
			}

			String getActiveProblemName = getText(problems.WPgetActiveProblemName , "getActiveProblemName");
			AssertStringContains(SORActiveProblemDescription.toLowerCase(),getActiveProblemName.toLowerCase(), "getActiveProblemName");
			
			String getActiveProblemType = getText(problems.WPgetActiveProblemType , "getActiveProblemType");
			AssertStringEquals(getActiveProblemType, SORActiveProblemType, "getActiveProblemType");
			
			String getActiveProblemOnsetDate = getText(problems.WPgetActiveProblemOnsetDate , "getActiveProblemOnsetDate");
			AssertStringEquals(getActiveProblemOnsetDate, SOROnsetCurrentDate, "getActiveProblemOnsetDate");
			
			jsclick(problems.WPexpandCollapseActiveProblem, "Expand Active problem");
			Thread.sleep(3000);
			String getActiveProblemRecordedBy = getText(problems.WPgetActiveProblemRecordedBy , "getActiveProblemRecordedBy");
			AssertStringEquals(getActiveProblemRecordedBy, SORRecordedBy, "getActiveProblemRecordedBy");
						
			jsclick(problems.WPexpandCollapseActiveProblem, "Collapse Active problem");
			Thread.sleep(3000);
			

			String getHistoryActiveProblemName = getText(problems.WPgetHistoryActiveProblemName , "getHistoryActiveProblemName");
			AssertStringContains(SORHistoryActiveProblemDescription.toLowerCase(),  getHistoryActiveProblemName.toLowerCase(), "getHistoryActiveProblemName");
			
			String getHistoryActiveProblemType = getText(problems.WPgetHistoryActiveProblemType , "getHistoryActiveProblemType");
			AssertStringEquals(getHistoryActiveProblemType, SORHistoryActiveProblemType, "getHistoryActiveProblemType");
			
			String getHistoryActiveProblemOnsetDate = getText(problems.WPgetHistoryActiveProblemOnsetDate , "getHistoryActiveProblemOnsetDate");
			AssertStringEquals(getHistoryActiveProblemOnsetDate, SOROnsetPast2Date, "getHistoryActiveProblemOnsetDate");
			
			jsclick(problems.WPexpandCollapseHistoryActiveProblem, "Expand History Active problem");
			Thread.sleep(3000);
			String getHistoryActiveProblemRecordedBy = getText(problems.WPgetHistoryActiveProblemRecordedBy , "getHistoryActiveProblemRecordedBy");
			AssertStringEquals(getHistoryActiveProblemRecordedBy, SORRecordedBy, "getHistoryActiveProblemRecordedBy");
			
			jsclick(problems.WPexpandCollapseHistoryActiveProblem, "Collapse History Active problem");
			Thread.sleep(3000);

			
			String getCurrentProblemName = getText(problems.WPgetCurrentProblemName , "getCurrentProblemName");
			AssertStringContains(SORCurrentProblemDescription.toLowerCase(), getCurrentProblemName.toLowerCase(), "getCurrentProblemName");
			
			String getCurrentProblemType = getText(problems.WPgetCurrentProblemType , "getCurrentProblemType");
			AssertStringEquals(getCurrentProblemType, SORCurrentProblemType, "getCurrentProblemType");
			
			String getCurrentProblemOnsetDate = getText(problems.WPgetCurrentProblemOnsetDate , "getCurrentProblemOnsetDate");
			AssertStringEquals(getCurrentProblemOnsetDate, SOROnsetCurrentDate, "getCurrentProblemOnsetDate");
			
			jsclick(problems.WPexpandCollapseCurrentProblem, "Expand Current problem");
			Thread.sleep(3000);
			String getCurrentProblemRecordedBy = getText(problems.WPgetCurrentProblemRecordedBy , "getCurrentProblemRecordedBy");
			AssertStringEquals(getCurrentProblemRecordedBy, SORRecordedBy, "getCurrentProblemRecordedBy");
			
			jsclick(problems.WPexpandCollapseCurrentProblem, "Collapse Current problem");
			Thread.sleep(3000);
			

			String getHistoryCurrentProblemName = getText(problems.WPgetHistoryCurrentProblemName , "getHistoryCurrentProblemName");
			AssertStringContains(SORHistoryCurrentProblemDescription.toLowerCase(),getHistoryCurrentProblemName.toLowerCase(), "getHistoryCurrentProblemName");
			
			String getHistoryCurrentProblemType = getText(problems.WPgetHistoryCurrentProblemType , "getHistoryCurrentProblemType");
			AssertStringEquals(getHistoryCurrentProblemType, SORHistoryCurrentProblemType, "getHistoryCurrentProblemType");
			
			String getHistoryCurrentProblemOnsetDate = getText(problems.WPgetHistoryCurrentProblemOnsetDate , "getHistoryCurrentProblemOnsetDate");
			AssertStringEquals(getHistoryCurrentProblemOnsetDate, SOROnsetPast4Date, "getHistoryCurrentProblemOnsetDate");
			
			jsclick(problems.WPexpandCollapseHistoryCurrentProblem, "Expand History Current problem");
			Thread.sleep(3000);
			String getHistoryCurrentProblemRecordedBy = getText(problems.WPgetHistoryCurrentProblemRecordedBy , "getHistoryCurrentProblemRecordedBy");
			AssertStringEquals(getHistoryCurrentProblemRecordedBy, SORRecordedBy, "getHistoryCurrentProblemRecordedBy");
			
			jsclick(problems.WPexpandCollapseHistoryCurrentProblem, "Collapse History Current problem");
			Thread.sleep(3000);
			


			reportStep("Script completed", "INFO");
			driver.quit();
			
	}


}
