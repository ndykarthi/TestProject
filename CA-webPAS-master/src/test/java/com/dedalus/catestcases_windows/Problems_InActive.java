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

import com.dedalus.capages_windows.EncountersPage;
import com.dedalus.capages_windows.MenuPage;
import com.dedalus.capages_windows.ProblemsPage;
import com.dedalus.restasssured_windows.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Problems_InActive extends RESTAssuredBase{
	
	JsonPath jsonPath;

	@Parameters({"SOR"})
	@BeforeTest
	public void setData(String SOR) throws IOException, InterruptedException, AWTException {
		testCaseName = "Inactive Problem - "+SOR+" - Windows "; 
		testDescription = "The following test case verify Inactive problem";
		nodes = SOR;
		category = "Regression";
		authors = "Karthi";
	}



	@Parameters({"SOR","DeviceName"})

	@Test
	public void create(String SOR,String DeviceName, ITestContext context)
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

		String SOROnsetCurrentDate = getExposedValue(varResponse, "CP", "LV_IP_ProblemResolved_Onsetdate", "SOROnsetCurrentDate");
		SOROnsetCurrentDate = SOROnsetCurrentDate.replace(" ", "-");
		String SOROnsetPast2Date = getExposedValue(varResponse, "CP", "LV_strpastDate", "SOROnsetPast2Date");
		SOROnsetPast2Date = SOROnsetPast2Date.replace(" ", "-");
		String SOROnsetPast4Date = getExposedValue(varResponse, "CP", "LV_strpastDatefour", "SOROnsetPast4Date");
		SOROnsetPast4Date = SOROnsetPast4Date.replace(" ", "-");
		String SORCurrentDate = getExposedValue(varResponse,"CP","LV_Date","LV_Date");
		
		String SORIPWard = getExposedValue(varResponse, "LCP", "GV_IP_Ward", "GV_IP_Ward");
		String SORHospitalName = getExposedValue(varResponse, "LCP", "GV_HospitalName", "GV_HospitalName");
	    String SORHospitalID = getExposedValue(varResponse, "LCP", "GV_HospitalID", "GV_HospitalID");
	    String SORInitiatingHospital = SORHospitalName+"-"+SORHospitalID;

		String SORInactiveProblemType = getExposedValue(varResponse, "CP", "LV_InactiveProblemType", "InactiveProblemType");
		String SORInactiveProblemDescription = getExposedValue(varResponse, "CP", "ProblemInactiveDescription", "InactiveProblemDescription");

		String SORResolvedProblemType = getExposedValue(varResponse, "CP", "LV_ResolvedProblemType", "ResolvedProblemType");
		String SORResolvedProblemDescription = getExposedValue(varResponse, "CP", "ProblemResolvedDescription", "ResolvedProblemDescription");

		String SORHistoryInactiveProblemType = getExposedValue(varResponse, "CP", "LV_HistoryInactiveProblemType", "HistoryInactiveProblemType");
		String SORHistoryInactiveProblemDescription = getExposedValue(varResponse, "CP", "ProblemHistoryInactiveDescription", "HistoryInactiveProblemDescription");
		
		String SORHistoryResolvedProblemType = getExposedValue(varResponse, "CP", "LV_HistoryResolvedProblemType", "HistoryResolvedProblemType");
		String SORHistoryResolvedProblemDescription = getExposedValue(varResponse, "CP", "ProblemHistoryResolvedDescription", "HistoryResolvedProblemDescription");

		startApp(SOR,SORConfigURL);
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

		if (summaryProblemsCount.equals("0")) {
			reportStep("Summary problems count is [" + summaryProblemsCount + "] matching with count 0", "PASS");
			System.out.println("Summary problems count is [" + summaryProblemsCount + "] matching with count 0");
		} else {
			reportStep("Summary problems count is [" + summaryProblemsCount + "] not matching with count 0", "FAIL");
			System.out.println("Summary problems count is [" + summaryProblemsCount + "] not matching with count 0");
		}
		
		javascriptScroll(problems.clickProblemsTab, "Problems icon");
		jsclick(problems.clickProblemsTab, "Problems icon");
		Thread.sleep(15000);
		//Problem details Validation
		click(problems.clickInactiveTab,"Inactive TAB");
		Thread.sleep(2000);
		verifyTextDisplay(problems.clickInactiveTab);

		int InactiveProblemsCount = problems.getProblemsCount();
		if (InactiveProblemsCount == 4) {
			reportStep("Inactive problems count is [" + InactiveProblemsCount + "] matching with count 4", "PASS");
			System.out.println("Inactive problems count is [" + InactiveProblemsCount + "] matching with count 4");
		} else {
			reportStep("Inactive problems count is [" + InactiveProblemsCount + "] not matching with count 4", "FAIL");
			System.out.println("Inactive problems count is [" + InactiveProblemsCount + "] not matching with count 4");
		}
		Thread.sleep(2000);

		String getInactiveProblemName = getText(problems.WPgetInactiveProblemName , "getInactiveProblemName");
		AssertStringContains(SORInactiveProblemDescription.toLowerCase(),getInactiveProblemName.toLowerCase(), "getInactiveProblemName");
		
		String getInactiveProblemType = getText(problems.WPgetInactiveProblemType , "getInactiveProblemType");
		AssertStringEquals(getInactiveProblemType, SORInactiveProblemType, "getInactiveProblemType");
		
		String getInactiveProblemOnsetDate = getText(problems.WPgetInactiveProblemOnsetDate , "getInactiveProblemOnsetDate");
		AssertStringEquals(getInactiveProblemOnsetDate, SOROnsetCurrentDate, "getInactiveProblemOnsetDate");
		
		jsclick(problems.WPexpandCollapseInactiveProblem, "Expand Inactive problem");
		Thread.sleep(3000);
		String getInactiveProblemRecordedBy = getText(problems.WPgetInactiveProblemRecordedBy , "getInactiveProblemRecordedBy");
		AssertStringEquals(getInactiveProblemRecordedBy, SORRecordedBy, "getInactiveProblemRecordedBy");
		
		jsclick(problems.WPexpandCollapseInactiveProblem, "Collapse Inactive problem");
		Thread.sleep(3000);
		
		String getHistoryInactiveProblemName = getText(problems.WPgetHistoryInactiveProblemName , "getHistoryInactiveProblemName");
		AssertStringContains( SORHistoryInactiveProblemDescription.toLowerCase(), getHistoryInactiveProblemName.toLowerCase(), "getHistoryInactiveProblemName");
		
		String getHistoryInactiveProblemType = getText(problems.WPgetHistoryInactiveProblemType , "getHistoryInactiveProblemType");
		AssertStringEquals(getHistoryInactiveProblemType, SORHistoryInactiveProblemType, "getHistoryInactiveProblemType");
		
		String getHistoryInactiveProblemOnsetDate = getText(problems.WPgetHistoryInactiveProblemOnsetDate , "getHistoryInactiveProblemOnsetDate");
		AssertStringEquals(getHistoryInactiveProblemOnsetDate, SOROnsetPast2Date, "getHistoryInactiveProblemOnsetDate");
		
		jsclick(problems.WPexpandCollapseHistoryInactiveProblem, "Expand History Inactive problem");
		Thread.sleep(3000);
		String getHistoryInactiveProblemRecordedBy = getText(problems.WPgetHistoryInactiveProblemRecordedBy , "getHistoryInactiveProblemRecordedBy");
		AssertStringEquals(getHistoryInactiveProblemRecordedBy, SORRecordedBy, "getHistoryInactiveProblemRecordedBy");
		
		jsclick(problems.WPexpandCollapseHistoryInactiveProblem, "Collapse History Inactive problem");
		Thread.sleep(3000);
		

		String getResolvedProblemName = getText(problems.WPgetResolvedProblemName , "getResolvedProblemName");
		AssertStringContains( SORResolvedProblemDescription.toLowerCase(), getResolvedProblemName.toLowerCase(), "getResolvedProblemName");
		
		String getResolvedProblemType = getText(problems.WPgetResolvedProblemType , "getResolvedProblemType");
		AssertStringEquals(getResolvedProblemType, SORResolvedProblemType, "getResolvedProblemType");
		
		String getResolvedProblemOnsetDate = getText(problems.WPgetResolvedProblemOnsetDate , "getResolvedProblemOnsetDate");
		AssertStringEquals(getResolvedProblemOnsetDate, SOROnsetCurrentDate, "getResolvedProblemOnsetDate");
		
		jsclick(problems.WPexpandCollapseResolvedProblem, "Expand Resolved problem");
		Thread.sleep(3000);
		String getResolvedProblemRecordedBy = getText(problems.WPgetResolvedProblemRecordedBy , "getResolvedProblemRecordedBy");
		AssertStringEquals(getResolvedProblemRecordedBy, SORRecordedBy, "getResolvedProblemRecordedBy");
		
		jsclick(problems.WPexpandCollapseResolvedProblem, "Collapse Resolved problem");
		Thread.sleep(3000);
		
		
		String getHistoryResolvedProblemName = getText(problems.WPgetHistoryResolvedProblemName , "getHistoryResolvedProblemName");
		AssertStringContains( SORHistoryResolvedProblemDescription.toLowerCase(), getHistoryResolvedProblemName.toLowerCase(), "getHistoryResolvedProblemName");
		
		String getHistoryResolvedProblemType = getText(problems.WPgetHistoryResolvedProblemType , "getHistoryResolvedProblemType");
		AssertStringEquals(getHistoryResolvedProblemType, SORHistoryResolvedProblemType, "getHistoryResolvedProblemType");
		
		String getHistoryResolvedProblemOnsetDate = getText(problems.WPgetHistoryResolvedProblemOnsetDate , "getHistoryResolvedProblemOnsetDate");
		AssertStringEquals(getHistoryResolvedProblemOnsetDate, SOROnsetPast4Date, "getHistoryResolvedProblemOnsetDate");
		
		jsclick(problems.WPexpandCollapseHistoryResolvedProblem, "Expand History Resolved problem");
		Thread.sleep(3000);
		String getHistoryResolvedProblemRecordedBy = getText(problems.WPgetHistoryResolvedProblemRecordedBy , "getHistoryResolvedProblemRecordedBy");
		AssertStringEquals(getHistoryResolvedProblemRecordedBy, SORRecordedBy, "getHistoryResolvedProblemRecordedBy");
		
		jsclick(problems.WPexpandCollapseHistoryResolvedProblem, "Collapse History Resolved problem");
		Thread.sleep(3000);

		reportStep("Script completed", "INFO");
		driver.quit();
	
	}
}
