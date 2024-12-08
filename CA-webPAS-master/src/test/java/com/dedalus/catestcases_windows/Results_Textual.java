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

import com.dedalus.capages_windows.MenuPage;
import com.dedalus.capages_windows.ResultsPage;
import com.dedalus.capages_windows.SummaryPage;
import com.dedalus.restasssured_windows.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Results_Textual extends RESTAssuredBase{

	Response response;
	JsonPath jsonPath;

	@Parameters({"SOR"})
	@BeforeTest
	public void setData(String SOR) throws IOException, InterruptedException, AWTException {
		testCaseName = "Textual results - "+SOR+" - Windows "; 
		testDescription = "The following test case verify Textual results";
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
		String SORPatientTitle = getExposedValue(varResponse, "LCP","TD_strTitleAbbr","TD_strTitleAbbr");
		String SORHRN = getExposedValue(varResponse, "LCP","LV_HRN","LV_HRN");
		String SORPatientGender = getExposedValue(varResponse,"LCP","TD_strGender","TD_strGender");
		if((SORPatientGender.equals("Transgender")) || (SORPatientGender.equals("Intersex"))) {
			SORPatientGender = "Other";
		}
		String SORPatAddLine1 = getExposedValue(varResponse, "LCP","LV_AddressLine1","LV_AddressLine1");
		String SORPatAddLine2 = getExposedValue(varResponse, "LCP","LV_AddressLine2","LV_AddressLine2"); 
		String SORPatAddLine3 = getExposedValue(varResponse, "LCP","LV_AddressLine3","LV_AddressLine3");
		String SORPatAddLine4 = getExposedValue(varResponse, "LCP","LV_AddressLine4","LV_AddressLine4");
		String SORCareProviderTitle = getExposedValue(varResponse, "LCP","LV_CPTitle","LV_CPTitle");
		String SORCareProviderSN = getExposedValue(varResponse, "LCP","LV_CPSurName","LV_CPSurname");
		String SORCareProviderFN = getExposedValue(varResponse, "LCP","LV_CPForeName","LV_CPForename");
		String SORCareProvider = SORCareProviderTitle+" "+SORCareProviderFN +" " + SORCareProviderSN;
		String SORIPSpecialty = getExposedValue(varResponse, "LCP","LV_CPSplty","LV_CPSplty");

		String SORPatFullName = SORPatientSurname.toUpperCase() +", " + SORPatientForename;

		String SORResName = getExposedValue(varResponse, "CP","LV_strResultName","SORResName");
		String SORResLab = getExposedValue(varResponse, "CP","LV_strLab","SORResLab");
		String SORResStatus = getExposedValue(varResponse, "CP","LV_strResultStatus","SORResStatus");
		String SORResultPerformedDate =  getExposedValue(varResponse, "CP", "LV_strSamplePerformedDate", "LV_strSamplePerformedDate");
		SORResultPerformedDate = SORResultPerformedDate.replace(" at", "");
		String SORPackCell =  getExposedValue(varResponse, "CP", "LV_strPackCell", "SORPackCell");
		String SORWholeBlood =  getExposedValue(varResponse, "CP", "LV_strWholeBlood", "SORWholeBlood");		
		String SORFFP = getExposedValue(varResponse, "CP", "LV_strFFP", "SORFFP");
		String SORPlatelets = getExposedValue(varResponse, "CP", "LV_strPlatlets", "SORPlatlets");
		String SORResOthers = getExposedValue(varResponse, "CP", "LV_strOthers", "SORResOthers");
		String SORResComments =  getExposedValue(varResponse, "CP", "LV_strComments", "SORResComments");
		String SORABO = getExposedValue(varResponse, "CP", "LV_strABO", "SORABO");
		String SORHoldSerum = getExposedValue(varResponse, "CP", "LV_strSerum", "SORHoldSerum");
		String SORAntibodyScreening = getExposedValue(varResponse, "CP", "LV_strAntbodyScreening", "SORAntibodyScreening");
		String SORPrcsBy =  getExposedValue(varResponse, "CP", "LV_strPrcsdBY", "SORPrcsBy");
		String SORCrtdBy =  getExposedValue(varResponse, "CP", "LV_strCrtdBY", "SORCrtdBy");
		String SORDiagnosticGroup =  getExposedValue(varResponse, "CP", "LV_strDiagnosticGroup", "SORDiagnosticGroup");
	
//==============================================================================================================
		
		startApp(SOR,SORConfigURL);
		Thread.sleep(8000);
		MenuPage mnuPg = new MenuPage(driver, test);
		ResultsPage ResPg = new ResultsPage(driver, test);
		SummaryPage summPg = new SummaryPage(driver, test);
		click(mnuPg.clickMenu, "Menu");
		String SORReadby = getText(mnuPg.LoggedInUser, "Logged In User");
        click(mnuPg.menu_Search,"Search");
        Thread.sleep(3000);

        AdvSearchNSelectPatient(SORPatientSurname, SORPatientForename, SORPatientGender, SORPatientDOB, SORHRN);
        Thread.sleep(3000);

		javascriptScroll(summPg.SummaryResultsDateFilter,"Results Tile");
		verifyTextDisplay(summPg.SummaryResultsDateFilter);

		String Summary_DefaultResultFilter = getText(summPg.SummaryResultsDateFilter, "Summary_DefaultResultFilter");
		AssertStringEquals(Summary_DefaultResultFilter, "LAST 2 WEEKS", "Summary_DefaultResultFilter");
		
		String Summary_TotalResultsCount = getText(summPg.SummaryResultsTotalCount, "Summary_TotalResultsCount");
		AssertStringEquals(Summary_TotalResultsCount, "1", "Summary_TotalResultsCount");
		
		String Summary_ReadResultsCount = getText(summPg.Results_ReadCount, "Summary_ReadResultsCount");
		AssertStringEquals(Summary_ReadResultsCount, "0", "Summary_ReadResultsCount");
		
		String Summary_UnreadResultsCount = getText(summPg.Results_UnreadCount, "Summary_UnreadResultsCount");
		AssertStringEquals(Summary_UnreadResultsCount, "1", "Summary_UnreadResultsCount");
		
		String Summary_PendingOrdersCount = getText(summPg.Results_PendingOrdCount, "Summary_PendingOrdersCount");
		AssertStringEquals(Summary_PendingOrdersCount, "0", "Summary_PendingOrdersCount");
		
		click(summPg.tab_WebPASResults,"results tab");
		Thread.sleep(5000);

		int ListViewResultsCount = getResultsCount();
		if( ListViewResultsCount == 1) {
			reportStep("Count of "+ ListViewResultsCount+" Results is displayed.", "PASS");
			System.out.println("Count of "+ ListViewResultsCount+" Results is displayed.");
		}else {
			reportStep("Count of "+ ListViewResultsCount+" Results is not displayed.", "FAIL");
			System.out.println("Count of "+ ListViewResultsCount+" Results is not displayed.");
		}

		String ResultName = getText(ResPg.WPResultName, "ResultName");
		AssertStringEquals(ResultName, SORResName, "ResultName");
			
		String ResultStatus = getText(ResPg.WPResultStatus, "ResultStatus");
		AssertStringEquals(ResultStatus, SORResStatus, "ResultStatus");
		
		String ResultLab = getText(ResPg.WPResultLab, "ResultLab");
		AssertStringEquals(ResultLab, SORResLab, "ResultLab");
		
		String ResultDiagnosticGroup = getText(ResPg.WPResultDiagnosticGroup, "ResultDiagnosticGroup");
		AssertStringEquals(ResultDiagnosticGroup, SORDiagnosticGroup, "ResultDiagnosticGroup");
		
		String ResultPerformedDate = getText(ResPg.WPResultPerformedDate, "ResultPerformedDate");
		AssertStringEquals(ResultPerformedDate.replaceAll("-", " "), SORResultPerformedDate, "ResultPerformedDate");
		
		javascriptScroll(ResPg.WPExpandResultIcon, "ExpandResultIcon");
		jsclick(ResPg.WPExpandResultIcon, "ExpandResultIcon");
		Thread.sleep(15000);
		
		String SORListviewInfo1 = "Associated documents/reports can be viewed within the documents tab or refer to webPAS.";
		String ListViewInfo1 = getText(ResPg.ResultInfo1, "ListViewInfo1");
		AssertStringEquals(ListViewInfo1, SORListviewInfo1, "ListViewInfo1");
		
		String SORListviewInfo2 = "Select result detail and scroll vertically and/or horizontally to ensure you have read all the result";
		String ListViewInfo2 = getText(ResPg.ResultInfo2, "ListViewInfo2");
		AssertStringEquals(ListViewInfo2, SORListviewInfo2, "ListViewInfo2");
		
		String ResPackCell = getText(ResPg.ResPackCell, "ResPackCell");
		AssertStringEquals(ResPackCell, SORPackCell, "ResPackCell");

		String ResWholeBlood = getText(ResPg.ResWholeBlood, "ResWholeBlood");
		AssertStringEquals(ResWholeBlood, SORWholeBlood, "ResWholeBlood");
		
		String ResFFP = getText(ResPg.ResFFP, "ResFFP");
		AssertStringEquals(ResFFP, SORFFP, "ResFFP");
		
		String ResPlatelets = getText(ResPg.ResPlatelets, "ResPlatelets");
		AssertStringEquals(ResPlatelets, SORPlatelets, "ResPlatelets");
		
		String ResOthers = getText(ResPg.ResOthers, "ResOthers");
		AssertStringEquals(ResOthers, SORResOthers, "ResOthers");
		
		javascriptScroll(ResPg.ResComments, "ResComments");
		String ResComments = getText(ResPg.ResComments, "ResComments");
		AssertStringEquals(ResComments, SORResComments, "ResComments");
		
		String ResABO = getText(ResPg.ResABO, "ResABO");
		AssertStringEquals(ResABO, SORABO, "ResABO");
		
		String ResAntibodyScreening = getText(ResPg.ResAntibodyScreening, "ResAntibodyScreening");
		AssertStringEquals(ResAntibodyScreening, SORAntibodyScreening, "ResAntibodyScreening");
		
		String ResHoldSerum = getText(ResPg.ResHoldSerum, "ResHoldSerum");
		AssertStringEquals(ResHoldSerum, SORHoldSerum, "ResHoldSerum");
		
		String ResProcessedBy = getText(ResPg.ResProcessedBy, "ResProcessedBy");
		AssertStringEquals(ResProcessedBy, SORPrcsBy, "ResProcessedBy");
		
		String ResCheckedBy = getText(ResPg.ResCheckedBy, "ResCheckedBy");
		AssertStringEquals(ResCheckedBy, SORCrtdBy, "ResCheckedBy");
		
		javascriptScroll(ResPg.ResLabMore, "ResLabMore");
		String ResLabMore = getText(ResPg.ResLabMore, "ResLabMore");
		AssertStringEquals(ResLabMore, ResultLab, "ResLabMore");
		
		String ResDiagnosticGroupMore = getText(ResPg.ResDiagnosticGroupMore, "ResDiagnosticGroupMore");
		AssertStringEquals(ResDiagnosticGroupMore, SORDiagnosticGroup, "ResDiagnosticGroupMore");
		
		click(ResPg.tabularFauxtab,"results tabularFauxtab");
		Thread.sleep(15000);
			
		String SORTabviewInfo = "Please refer to the List view or System of Record for full details of the result and all comments.";
		String TabViewInfo = getText(ResPg.ResultInfo1, "TabViewInfo");
		AssertStringEquals(TabViewInfo, SORTabviewInfo, "TabViewInfo");
		
		String TabViewResultName = getText(ResPg.tabularViewResname, "ResultName");
		AssertStringEquals(TabViewResultName, SORResName, "ResultName");
		
		String TabViewResultPerformedDate = getText(ResPg.tabularViewResPerformedDate, "ResultPerformedDate");
		AssertStringEquals(TabViewResultPerformedDate.replaceAll("-", " "), SORResultPerformedDate, "ResultPerformedDate");
	
		jsclick(ResPg.packCellCommentIcon,"packCellCommentIcon");
		Thread.sleep(5000);	
		
		String DetResultName = getText(ResPg.popupResItemName, "DetResultName");
		AssertStringEquals(DetResultName, "PACK CELL", "DetResultName");
			
		jsclick(ResPg.ClosePopup,"Close popup");
		Thread.sleep(5000);	
		
		jsclick(ResPg.WholeBloodCommentIcon,"Whole Blood comment icon");
		Thread.sleep(5000);	
		
		DetResultName = getText(ResPg.popupResItemName, "DetResultName");
		AssertStringEquals(DetResultName, "WHOLE BLOOD", "DetResultName");
		
		String DetResultStatus = getText(ResPg.popupResItemStatus, "DetResultStatus");
		AssertStringEquals(DetResultStatus, SORResStatus, "DetResultStatus");
		
		String DetResultLab = getText(ResPg.popupResItemLab, "DetResultLab");
		AssertStringEquals(DetResultLab, SORResLab, "DetResultLab");
		
		String DetResultPerformedDate = getText(ResPg.popupResItemPerformedDate, "DetResultPerformedDate");
		AssertStringEquals(DetResultPerformedDate.replaceAll("-", " "), SORResultPerformedDate, "DetResultPerformedDate");
		
		String DetResPackCellDiagnosticGroup = getText(ResPg.popupResItemDiagnosticGroup, "DetResPackCellDiagnosticGroup");
		AssertStringEquals(DetResPackCellDiagnosticGroup, SORDiagnosticGroup, "DetResPackCellDiagnosticGroup");
		
		jsclick(ResPg.clickViewMoreDetails,"clickViewMoreDetails");
		Thread.sleep(5000);	
		
		VerifySelectedFauxTab("List");
				
		driver.quit();
		reportStep("Script completed", "INFO");	 
	}
}
