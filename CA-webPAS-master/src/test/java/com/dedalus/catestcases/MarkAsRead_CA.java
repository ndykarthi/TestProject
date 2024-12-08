package com.dedalus.catestcases;

import java.awt.AWTException;
import java.io.FileNotFoundException;
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

import io.restassured.response.Response;


public class MarkAsRead_CA extends RESTAssuredBase{
	
	@Parameters({"PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
	@BeforeTest
	public void setData(String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation) throws IOException, InterruptedException, AWTException {
		testCaseName = "Results - Mark As Read from CA"+PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
		testDescription = "This test is to verify the Results of a patient and Read it";
		nodes = PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
	    category = "Regression";
	    authors = "Kavitha";
	}

	@Parameters({"SOR","PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
	@Test
	public void result(String SOR,String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation, ITestContext context) throws InterruptedException, FileNotFoundException, IOException, AWTException, ClassNotFoundException, SQLException, ParseException
	{
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
		String SORHRN = getExposedValue(varResponse,"LCP","LV_HRN","LV_HRN");
		String SORConfigURL = getExposedValue(varResponse,"LCP","GV_ATCA_ConfigURL","GV_ATCA_ConfigURL");
		String SORPatAddLine1 = getExposedValue(varResponse,"LCP","LV_AddressLine1","LV_AddressLine1");
		String SORPatAddLine2 = getExposedValue(varResponse,"LCP","LV_AddressLine2","LV_AddressLine2"); 	
		String SORPatAddLine3 = getExposedValue(varResponse,"LCP","LV_AddressLine3","LV_AddressLine3");
		String SORPatAddLine4 = getExposedValue(varResponse,"LCP","LV_AddressLine4","LV_AddressLine4");
		String SORPostCode = getExposedValue(varResponse,"LCP","TD_strPostCode","TD_strPostCode");
		SORPatAddLine3 = convertCamelCase(SORPatAddLine3);
		String SORPatAdd = SORPatAddLine1+", "+SORPatAddLine2+" "+SORPatAddLine3+" "+SORPatAddLine4+" "+SORPostCode;
		String SORPatDOB = getExposedValue(varResponse,"CP","TD_dtmDOB","TD_dtmDOB");
		String SOROResultName = getExposedValue(varResponse, "CP", "LV_strDmstcGrp", "LV_strDmstcGrp");
		String SORSamplePerfDate = getExposedValue(varResponse, "CP", "LV_StartDateTime", "LV_StartDateTime");
		String SORLab = getExposedValue(varResponse, "CP", "LV_strLAB", "LV_strLAB");
		String SORDiagGp = getExposedValue(varResponse, "CP", "LV_strDmstcGrp", "LV_strDmstcGrp");
		String SORStatus = getExposedValue(varResponse, "CP", "LV_strStatus", "LV_strStatus");
		String SORWBCValue = getExposedValue(varResponse, "CP", "LV_WBCValue", "LV_WBCValue");
		String SORWBCUnit = getExposedValue(varResponse, "CP", "LV_WBCUnit", "LV_WBCUnit");
		String SORWBCRange = getExposedValue(varResponse, "CP", "LV_WBCRange", "LV_WBCRange");	
		String SORRBCValue = getExposedValue(varResponse, "CP", "LV_RBCValue", "LV_RBCValue");
		String SORRBCUnit = getExposedValue(varResponse, "CP", "LV_RBCUnit", "LV_RBCUnit");
		String SORRBCRange = getExposedValue(varResponse, "CP", "LV_RBCRange", "LV_RBCRange");	
		String SORHGBValue = getExposedValue(varResponse, "CP", "LV_HGBValue", "LV_HGBValue");
		String SORHGBUnit = getExposedValue(varResponse, "CP", "LV_HGBUnit", "LV_HGBUnit");
		String SORHGBRange = getExposedValue(varResponse, "CP", "LV_HGBRange", "LV_HGBRange");	
		String SORMCVValue = getExposedValue(varResponse, "CP", "LV_MCVValue", "LV_MCVValue");
		String SORMCVUnit = getExposedValue(varResponse, "CP", "LV_MCVUnit", "LV_MCVUnit");
		String SORMCVRange = getExposedValue(varResponse, "CP", "LV_MCVRange", "LV_MCVRange");	
		String SORHct = getExposedValue(varResponse, "CP", "LV_Hct", "LV_Hct");
		String SORFilm = getExposedValue(varResponse, "CP", "LV_Film", "LV_Film");
		String SOROthersSpecify = getExposedValue(varResponse, "CP", "LV_OthersSpecify", "LV_OthersSpecify");
		String SORProcBy = getExposedValue(varResponse, "CP", "LV_ProcBy", "LV_ProcBy");
		String SORCheckedBy = getExposedValue(varResponse, "CP", "LV_CheckedBy", "LV_CheckedBy");
		
		reportStep("PASID : "+SORHRN, "INFO");

		 startCAUKApp(SOR,PlatformName,DeviceName,PlatformVersion,DeviceOrientation,SORConfigURL);
	     Thread.sleep(8000);
	     MenuPage mnuPg = new MenuPage(driver, test);
		 SummaryPage summPg = new SummaryPage(driver, test);
		 ResultsPage rsltPg = new ResultsPage(driver, test); 
		 
		 click(mnuPg.clickMenu, "Menu");
		 Thread.sleep(2000);
		 String SORReadby = getText(mnuPg.LoggedInUser, "Logged In User"); 
		 
		 click(mnuPg.menu_Search,"Search");
		 Thread.sleep(3000);
		 System.out.println("Patient ID: "+SORHRN);
		 AdvanceSearchPatient(SORPatientSurname, SORPatientForename, SORPatientGender, SORPatDOB);
		// SearchPatient(SORHRN);
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
		 AssertStringEquals(SummResCnt, "1", "Result Count in Summary Page");
		 
		 String ReadCnt = getText(summPg.Results_ReadCount, "Read Results Count");	 
		 String UnreadCnt = getText(summPg.Results_UnreadCount, "Unead Results Count");
		 String UnreadIcon = getIconName(summPg.Results_UnreadIcon, "Unread Results Icon");
		 String PendingOrders = getText(summPg.Results_PendingOrdCount, "Pending Results Order Count");
		 
		 AssertStringEquals(ReadCnt, "0", "Read Results Count in Summary Page");
		 AssertStringEquals(UnreadCnt, "1", "Unread Results Count in Summary Page");	
		 AssertStringEquals(UnreadIcon,"warning","Unread Results Icon in Summary Page"); 
		 AssertStringEquals(PendingOrders, "0", "Pending Orders Count in Summary Page");
		 		 
		// Clicking on Result tile from Summary page
		click(summPg.ResultCntWebPAS, "Summary Result Count");
		Thread.sleep(3000);
		VerifySelectedTab("Results",DeviceName);
		jsclick(summPg.tab_Summary, "Summary Tab");
		Thread.sleep(3000);
			
		// Clicking on Result tab EPR
		click(summPg.tab_WebPASResults,"Result EPR Tab");
		VerifySelectedTab("Results",DeviceName);
		
		// List Tab
		VerifyHaematologyResult(SOROResultName,SORStatus,SORLab,SORDiagGp,SORSamplePerfDate);
		VerifyAbnormalResultItemListView(SOROResultName,"WBC",SORWBCValue,SORWBCUnit,SORWBCRange,"down");
		VerifyNormalResultItemListView(SOROResultName,"RBC",SORRBCValue,SORRBCUnit,SORRBCRange);
		VerifyAbnormalResultItemListView(SOROResultName,"Hgb",SORHGBValue,SORHGBUnit,SORHGBRange,"up");		
		VerifyAbnormalResultItemListView(SOROResultName,"MCV",SORMCVValue,SORMCVUnit,SORMCVRange,"up");		
		
		VerifyTextValueListView(SOROResultName,"Hct",SORHct);
		VerifyTextValueListView(SOROResultName,"Others Specify",SOROthersSpecify);
		VerifyTextValueListView(SOROResultName,"Film",SORFilm);
		javascriptScroll(rsltPg.MarkAsRead, "Scroll to Mark As Read");
		VerifyTextValueListView(SOROResultName,"Processed By",SORProcBy);
		VerifyTextValueListView(SOROResultName,"Checked By",SORCheckedBy);
		
		click(rsltPg.MarkAsRead, "Mark As Read");
		Thread.sleep(3000);
		VerifyCheckMark(SOROResultName);
		
		jsclick(summPg.tab_Summary, "Summary Tab");
		Thread.sleep(3000);
		String NewReadCnt = getText(summPg.Results_ReadCount, "New Read Results Count");
		String NewUnreadCnt = getText(summPg.Results_UnreadCount, "New Unead Results Count");
		String NewReadIcon = getIconName(summPg.Results_ReadIcon, "New Read Results Icon");
		AssertStringEquals(NewReadCnt, "1", "New Read Results Count in Summary Page");
		AssertStringEquals(NewUnreadCnt, "0", "New Unread Results Count in Summary Page");	
		AssertStringEquals(NewReadIcon,"warning","New Read Results Icon in Summary Page");
		
		click(summPg.tab_WebPASResults,"Results EPR Tab");
		
		// Tabular Tab
		click(rsltPg.TabularTab,"Tabular Tab");
		Thread.sleep(3000);
		VerifyResultTabView(SOROResultName,SORSamplePerfDate);
		
		VerifyAbnormalResultItemTabView(SOROResultName,"WBC",SORWBCValue,SORWBCUnit,SORWBCRange,"down");
		VerifyNormalResultItemTabView(SOROResultName,"RBC",SORRBCValue,SORRBCUnit,SORRBCRange);
		VerifyAbnormalResultItemTabView(SOROResultName,"Hgb",SORHGBValue,SORHGBUnit,SORHGBRange,"up");
		VerifyAbnormalResultItemTabView(SOROResultName,"MCV",SORMCVValue,SORMCVUnit,SORMCVRange,"up");
		
		VerifyReadHaematologyTextTabView("Hct",SORLab,SORDiagGp,SORReadby,SORSamplePerfDate);
		VerifyReadHaematologyTextTabView("Others Specify",SORLab,SORDiagGp,SORReadby,SORSamplePerfDate);
		VerifyReadHaematologyTextTabView("Film",SORLab,SORDiagGp,SORReadby,SORSamplePerfDate);
		VerifyReadHaematologyTextTabView("Processed By",SORLab,SORDiagGp,SORReadby,SORSamplePerfDate);
		VerifyReadHaematologyTextTabView("Checked By",SORLab,SORDiagGp,SORReadby,SORSamplePerfDate);
		
		reportStep("Script completed", "INFO");	
		driver.quit();
	  }
	}