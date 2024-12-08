package com.dedalus.catestcases_windows;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dedalus.capages_windows.ProblemsPage;
import com.dedalus.capages_windows.PatientBannerPage;
import com.dedalus.capages_windows.SummaryPage;
import com.dedalus.capages_windows.EncountersPage;
import com.dedalus.capages_windows.MenuPage;
import com.dedalus.capages_windows.MyWorklistPage;
import com.dedalus.restasssured_windows.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Problems extends RESTAssuredBase{

	Response response;
	JsonPath jsonPath;

	@Parameters({"SOR"})
	@BeforeTest
	public void setData(String SOR) throws IOException, InterruptedException, AWTException {
		testCaseName = "Problems - "+SOR+" - Windows "; 
		testDescription = "The following test case verify Problems";
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

		String SOROnsetCurrentDate = getExposedValue(varResponse, "CP", "ActiveOnsetDate", "SOROnsetCurrentDate");
		SOROnsetCurrentDate = SOROnsetCurrentDate.replace(" ", "-");
		String SOROnsetPast2Date = getExposedValue(varResponse, "CP", "LV_strpastDate", "SOROnsetPast2Date");
		SOROnsetPast2Date = SOROnsetPast2Date.replace(" ", "-");
		String SOROnsetPast4Date = getExposedValue(varResponse, "CP", "LV_strpastDatefour", "SOROnsetPast4Date");
		SOROnsetPast4Date = SOROnsetPast4Date.replace(" ", "-");

		String SORActiveProblemType = getExposedValue(varResponse, "CP", "LV_ActiveProblemType", "ActiveProblemType");
		String SORActiveProblemDescription = getExposedValue(varResponse, "CP", "LV_ActiveProblemDescription", "ActiveProblemDescription");

		String SORInactiveProblemType = getExposedValue(varResponse, "CP", "LV_InactiveProblemType", "InactiveProblemType");
		String SORInactiveProblemDescription = getExposedValue(varResponse, "CP", "LV_InactiveProblemDescription", "InactiveProblemDescription");

		String SORCurrentProblemType = getExposedValue(varResponse, "CP", "LV_CurrentProblemType", "CurrentProblemType");
		String SORCurrentProblemDescription = getExposedValue(varResponse, "CP", "LV_CurrentProblemDescription", "CurrentProblemDescription");

		String SORResolvedProblemType = getExposedValue(varResponse, "CP", "LV_ResolvedProblemType", "ResolvedProblemType");
		String SORResolvedProblemDescription = getExposedValue(varResponse, "CP", "LV_ResolvedProblemDescription", "ResolvedProblemDescription");

		String SORHistoryActiveProblemType = getExposedValue(varResponse, "CP", "LV_HistoryActiveProblemType", "HistoryActiveProblemType");
		String SORHistoryActiveProblemDescription = getExposedValue(varResponse, "CP", "LV_HistoryActiveProblemDescription", "HistoryActiveProblemDescription");

		String SORHistoryInactiveProblemType = getExposedValue(varResponse, "CP", "LV_HistoryInactiveProblemType", "HistoryInactiveProblemType");
		String SORHistoryInactiveProblemDescription = getExposedValue(varResponse, "CP", "LV_HistoryInactiveProblemDescription", "HistoryInactiveProblemDescription");

		String SORHistoryCurrentProblemType = getExposedValue(varResponse, "CP", "LV_HistoryCurrentProblemType", "HistoryCurrentProblemType");
		String SORHistoryCurrentProblemDescription = getExposedValue(varResponse, "CP", "LV_HistoryCurrentProblemDescription", "HistoryCurrentProblemDescription");

		String SORHistoryResolvedProblemType = getExposedValue(varResponse, "CP", "LV_HistoryResolvedProblemType", "HistoryResolvedProblemType");
		String SORHistoryResolvedProblemDescription = getExposedValue(varResponse, "CP", "LV_HistoryResolvedProblemDescription", "HistoryResolvedProblemDescription");
		
		String SORCurrentDate = getExposedValue(varResponse,"CP","LV_Date","LV_Date");
		
		String SORIPWard = getExposedValue(varResponse, "LCP", "GV_IP_Ward", "GV_IP_Ward");
		String SORHospitalName = getExposedValue(varResponse, "LCP", "GV_HospitalName", "GV_HospitalName");
	    String SORHospitalID = getExposedValue(varResponse, "LCP", "GV_HospitalID", "GV_HospitalID");
	    String SORInitiatingHospital = SORHospitalName+"-"+SORHospitalID;
		
		
		startApp(SOR,SORConfigURL);
		Thread.sleep(8000);
		MenuPage mnuPg = new MenuPage(driver, test);
		EncountersPage enc = new EncountersPage(driver, test);
		ProblemsPage problems = new ProblemsPage(driver, test);
		MyWorklistPage worklistPg = new MyWorklistPage(driver, test);


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
		AssertStringContains( SORCurrentProblemDescription,summaryProblem1Name ,"summaryProblem1Name");

		String summaryProblem1Type = getText(problems.getsummaryActiveProblem1Type, "Summary view Active Problem 1 - Type");
		AssertStringEquals(summaryProblem1Type.replace("Type: ",""), SORCurrentProblemType, "summaryProblem1Type");

		String summaryProblem1DateObtained = getText(problems.getsummaryActiveProblem1OnsetDate, "Summary view Active Problem 1 - Date obtained");
		AssertStringContains(summaryProblem1DateObtained, SOROnsetCurrentDate, "summaryProblem1DateObtained");

		String summaryProblem2Name = getText(problems.getsummaryActiveProblem2, "Summary view Active Problem 2");
		AssertStringContains(SORActiveProblemDescription ,summaryProblem2Name ,"summaryProblem2Name");

		String summaryProblem2Type = getText(problems.getsummaryActiveProblem2Type, "Summary view Active Problem 2 - Type");
		AssertStringEquals(summaryProblem2Type.replace("Type: ",""), SORActiveProblemType, "summaryProblem2Type");

		String summaryProblem2DateObtained = getText(problems.getsummaryActiveProblem2OnsetDate, "Summary view Active Problem 2 - Date obtained");
		AssertStringContains(summaryProblem2DateObtained, SOROnsetCurrentDate, "summaryProblem2DateObtained");

		String summaryProblem3Name = getText(problems.getsummaryActiveProblem3, "Summary view Active Problem 3");
		AssertStringContains( SORHistoryActiveProblemDescription,summaryProblem3Name ,"summaryProblem3Name");

		String summaryProblem3Type = getText(problems.getsummaryActiveProblem3Type, "Summary view Active Problem 3 - Type");
		AssertStringEquals(summaryProblem3Type.replace("Type: ",""), SORHistoryActiveProblemType, "summaryProblem3Type");

		String summaryProblem3DateObtained = getText(problems.getsummaryActiveProblem3OnsetDate, "Summary view Active Problem 3 - Date obtained");
		AssertStringContains(summaryProblem3DateObtained, SOROnsetPast2Date, "summaryProblem3DateObtained");


		javascriptScroll(problems.clickProblemsTab, "Problems icon");
		jsclick(problems.clickProblemsTab, "Problems icon");
		Thread.sleep(15000);

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

		//Inactive TAB
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

		//All TAB
		click(problems.clickAllTab,"All TAB");
		Thread.sleep(2000);
		verifyTextDisplay(problems.clickAllTab);
		int ProblemsCount = problems.getProblemsCount();
		if (ProblemsCount == 8) {
			reportStep("All problems count is [" + ProblemsCount + "] matching with count 8", "PASS");
			System.out.println("All problems count is [" + ProblemsCount + "] matching with count 8");
		} else {
			reportStep("All problems count is " + ProblemsCount + " not matching with count 8", "FAIL");
			System.out.println("All problems count is [" + ProblemsCount + "] not matching with count 8");
		}


		//Problem details Validation
		click(problems.clickInactiveTab,"Inactive TAB");
		Thread.sleep(2000);
		verifyTextDisplay(problems.clickInactiveTab);

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
		AssertStringContains(SORHistoryInactiveProblemDescription.toLowerCase(),getHistoryInactiveProblemName.toLowerCase(), "getHistoryInactiveProblemName");

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
		AssertStringContains(SORResolvedProblemDescription.toLowerCase(),getResolvedProblemName.toLowerCase(), "getResolvedProblemName");

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
		AssertStringContains(SORHistoryResolvedProblemDescription.toLowerCase(),getHistoryResolvedProblemName.toLowerCase(), "getHistoryResolvedProblemName");

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

		click(problems.clickActiveTab,"Active TAB");
		Thread.sleep(2000);
		verifyTextDisplay(problems.clickActiveTab);

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
		AssertStringContains(SORHistoryActiveProblemDescription.toLowerCase(),getHistoryActiveProblemName.toLowerCase(), "getHistoryActiveProblemName");

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
		AssertStringContains( SORCurrentProblemDescription.toLowerCase(),getCurrentProblemName.toLowerCase(), "getCurrentProblemName");

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



		jsclick(problems.clickFilterIcon, "Filter Icon");
		Thread.sleep(5000);
		takeSnap();
		explicitWait(problems.clickTypeFilter);
		jsclick(problems.clickTypeFilter, "Problems type filter");
		Thread.sleep(5000);
		explicitWait(problems.clickMedicalFilter);
		click(problems.clickMedicalFilter, "Medical");
		Thread.sleep(3000);
		jsclick(problems.clickFilterOKbutton,"Ok button");
		Thread.sleep(3000);
		
		int MedicalFiltercount = problems.getProblemsCount();
		if (MedicalFiltercount == 2) {
			reportStep("Filter Count of problem of Type : Medical is [" + MedicalFiltercount + "] matching with expected count 2", "PASS");
			System.out.println("Filter Count of problem of Type : Medical is [" + MedicalFiltercount + "] matching with expected count 2");
		} else {
			reportStep("Filter Count of problem of Type : Medical is [" + MedicalFiltercount + " not matching with expected count 2", "FAIL");
			System.out.println("Filter Count of problem of Type : Medical is [" + MedicalFiltercount + "] not matching with Active problem count 2");
		}

		String getFilterValue = getText(problems.getFilterValue, "After filter value");
		if (getFilterValue.equals("Type (Medical)")) {
			reportStep("Filter value of problem of Type : Medical is [" + getFilterValue + "] matching with expected value Type (Medical)", "PASS");
			System.out.println("Filter value of problem of Type : Medical is [" + getFilterValue + "] matching with expected value Type (Medical)");
		} else {
			reportStep("Filter value of problem of Type : Medical is [" + getFilterValue + " not matching with expected value Type (Medical)", "FAIL");
			System.out.println("Filter value of problem of Type : Medical is [" + getFilterValue + "] not matching with expected problem value Type (Medical)");
		}

		jsclick(problems.clickFilterIcon, "Filter Icon");
		Thread.sleep(15000);
		takeSnap();
		explicitWait(problems.clickTypeFilter);
		jsclick(problems.clickTypeFilter, "Problems type filter");
		Thread.sleep(5000);
		explicitWait(problems.clickMedicalFilter);
		explicitWait(problems.clickSurgicalFilter);
		jsclick(problems.clickSurgicalFilter, "Filter Surgical");
		Thread.sleep(3000);
		jsclick(problems.clickFilterOKbutton,"Ok button");
		Thread.sleep(3000);
		
		int MedicalAndSurgicalFiltercount = problems.getProblemsCount();
		if (MedicalAndSurgicalFiltercount == 4) {
			reportStep("Filter Count of problem of Type : Cardio-thoracic and Psychiatric is [" + MedicalAndSurgicalFiltercount + "] matching with expected count 4", "PASS");
			System.out.println("Filter Count of problem of Type : Cardio-thoracic and Psychiatric is [" + MedicalAndSurgicalFiltercount + "] matching with expected count 4");
		} else {
			reportStep("Filter Count of problem of Type : Cardio-thoracic and Psychiatric is [" + MedicalAndSurgicalFiltercount + " not matching with expected count 4", "FAIL");
			System.out.println("Filter Count of problem of Type : Cardio-thoracic and Psychiatric is [" + MedicalAndSurgicalFiltercount + "] not matching with Active problem count 4");
		}

		getFilterValue = getText(problems.getFilterValue, "After Second filter value");
		if (getFilterValue.equals("Type (Medical, Surgical)")) {
			reportStep("Filter value of problem of Type : Cardio-thoracic and Psychiatric is [" + getFilterValue + "] matching with expected value Type (Medical, Surgical))", "PASS");
			System.out.println("Filter value of problem of Type : Cardio-thoracic and Psychiatric is [" + getFilterValue + "] matching with expected value Type (Medical, Surgical)");
		} else {
			reportStep("Filter value of problem of Type : Cardio-thoracic and Psychiatric is [" + getFilterValue + " not matching with expected value Type (Medical, Surgical)", "FAIL");
			System.out.println("Filter value of problem of Type : Cardio-thoracic and Psychiatric is [" + getFilterValue + "] not matching with expected problem value Type (Medical, Surgical)");
		}

		jsclick(problems.clickFilterIcon, "Filter Icon");
		Thread.sleep(15000);
		takeSnap();
		explicitWait(problems.clickTypeFilter);
		jsclick(problems.clickTypeFilter, "Problems type filter");
		Thread.sleep(5000);
		explicitWait(problems.clickMedicalFilter);
		jsclick(problems.clickMedicalFilter, "Remove Medical filter");
		Thread.sleep(3000);
		jsclick(problems.clickFilterOKbutton,"Ok button");
		Thread.sleep(3000);
		

		int PsychiatricFiltercount = problems.getProblemsCount();
		if (PsychiatricFiltercount == 2) {
			reportStep("Filter Count after removing filter of problem Type : Surgical is [" + PsychiatricFiltercount + "] matching with expected count 2", "PASS");
			System.out.println("Filter Count after removing filter of problem Type : Surgical is [" + PsychiatricFiltercount + "] matching with expected count 2");
		} else {
			reportStep("Filter Count after removing filter of problem Type : Surgical is [" + PsychiatricFiltercount + " not matching with expected count 2", "FAIL");
			System.out.println("Filter Count after removing filter of problem Type : Surgical is [" + PsychiatricFiltercount + "] not matching with Active problem count 2");
		}

		getFilterValue = getText(problems.getFilterValue, "After filter value");
		if (getFilterValue.equals("Type (Surgical)")) {
			reportStep("Filter value after removing filter of problem Type : Surgical is [" + getFilterValue + "] matching with expected value Type (Surgical)", "PASS");
			System.out.println("Filter value after removing filter of problem Type : Surgical is [" + getFilterValue + "] matching with expected value Type (Surgical)");
		} else {
			reportStep("Filter value after removing filter of problem Type : Surgical is [" + getFilterValue + " not matching with expected value Type (Surgical)", "FAIL");
			System.out.println("Filter value after removing filter of problem Type : Surgical is [" + getFilterValue + "] not matching with expected problem value Type (Surgical)");
		}	

		reportStep("Script completed", "INFO");
		driver.quit();

	}

}


