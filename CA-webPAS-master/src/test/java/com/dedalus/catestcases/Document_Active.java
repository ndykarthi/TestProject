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

import com.dedalus.capages.DocumentsPage;
import com.dedalus.capages.MenuPage;
import com.dedalus.capages.PatientBannerPage;
import com.dedalus.capages.SummaryPage;
import com.dedalus.restasssured.RESTAssuredBase;

import io.restassured.response.Response;


public class Document_Active extends RESTAssuredBase{
	
	@Parameters({"PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
	@BeforeTest
	public void setData(String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation) throws IOException, InterruptedException, AWTException {
		testCaseName = "Documents"+PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
		testDescription = "This test is to verify the Active Document with IP and Emergency Encounter for a patient";
		nodes = PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
	    category = "Regression";
	    authors = "Kavitha";
	}

	@Parameters({"SOR","PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
	@Test
	public void ViewObs(String SOR,String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation, ITestContext context) throws InterruptedException, FileNotFoundException, IOException, AWTException, ClassNotFoundException, SQLException, ParseException
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
			
			String SORIPDocTypeFinal = getExposedValue(varResponse, "CP", "LV_IP_DocTypeFinal", "LV_IP_DocTypeFinal");
		//	String SORIPDocTemplateFinal = getExposedValue(varResponse, "CP", "LV_IP_DocTemplateFinal", "LV_IP_DocTemplateFinal");
			String SORIPDocSubjectFinal = getExposedValue(varResponse, "CP", "LV_IP_DocSubjectFinal", "LV_IP_DocSubjectFinal");
			String SORIPDocDetailFinal = getExposedValue(varResponse, "CP", "LV_DOcdeatilFinal", "LV_DOcdeatilFinal");
			
			String SORIPDocTypeDraft = getExposedValue(varResponse, "CP", "LV_IP_DocTypeDraft", "LV_IP_DocTypeDraft");
		//	String SORIPDocTemplateDraft = getExposedValue(varResponse, "CP", "LV_IP_TemplateDraft", "LV_IP_TemplateDraft");
			String SORIPDocSubjectDraft = getExposedValue(varResponse, "CP", "LV_IP_SubjectDraft", "LV_IP_SubjectDraft");
			String SORIPDocDetailDraft = getExposedValue(varResponse, "CP", "LV_DOCdetailDraft", "LV_DOCdetailDraft");
			
			String SORIPDocTypeSuperseded = getExposedValue(varResponse, "CP", "LV_IP_DocTypesuperseded", "LV_IP_DocTypesuperseded");
		//	String SORIPDocTemplateSuperseded = getExposedValue(varResponse, "CP", "LV_IP_TemplateSuperseded", "LV_IP_TemplateSuperseded");
			String SORIPDocSubjectSuperseded = getExposedValue(varResponse, "CP", "LV_IP_SubjectSuperseded", "LV_IP_SubjectSuperseded");
			String SORIPDocDetailSuperseded = getExposedValue(varResponse, "CP", "LV_DOcdeatilSuperseded", "LV_DOcdeatilSuperseded");
			
			String SORAuthor = getExposedValue(varResponse,"LCP","LV_CPFullName","LV_CPFullName");
			
			reportStep("PASID : "+SORHRN, "INFO");    
		 startCAUKApp(SOR,PlatformName,DeviceName,PlatformVersion,DeviceOrientation,SORConfigURL);
	     Thread.sleep(8000);
	     MenuPage mnuPg = new MenuPage(driver, test);
		 SummaryPage summPg = new SummaryPage(driver, test);
		 DocumentsPage docpg = new DocumentsPage(driver, test);
		 PatientBannerPage patBnrPg = new PatientBannerPage(driver, test);
		 click(mnuPg.clickMenu, "Menu");
		 click(mnuPg.menu_Search,"Search");
		 Thread.sleep(3000);
		 System.out.println("Patient ID: "+SORHRN);
		 SearchPatient(SORHRN);
		 VerifyPatientDetails_SearchListWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle,SORPatientDOB,SORHRN, SORPatAdd);
		 VerifyPatientDetails_SearchListMoreWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle, SORPatientGender,SORPatientDOB,SORHRN);
		 String PatientFN = SORPatientSurname.toUpperCase()+", "+SORPatientForename;
		 SelectPatient(PatientFN);
		 Thread.sleep(5000);
		 
		 if(DeviceOrientation == "LANDSCAPE") {
				click(patBnrPg.IndentDecrease, "Indent Decrease");
				String AlertCount = getText(patBnrPg.AlertCnt, "Alert Count in banner");
				AssertStringEquals(AlertCount, "2", "Alert Count in banner");
		}
	 
		 String patientBannerName = getText(summPg.getPatientNameInBanner, "Patient Name in Banner Summary Page");
		 String NHSBanner = getText(summPg.getHRNInBanner, "HRN Number in Banner Summary Page");
		 String DOBBanner = getText(summPg.getDoBInBanner, "DOB in Banner Summary Page");
			
		 AssertStringContains(patientBannerName, PatientFN, "Patient Name in Patient banner");
		 AssertStringEquals(SORHRN, NHSBanner, "NHS Number in Patient banner");
	     AssertStringEquals(SORPatientDOB, DOBBanner, "DOB in Patient banner");
			
		javascriptScroll(summPg.DocumentSummaryWebPAS, "Document Tile");
		
		String DocIcon = getIconName(summPg.DocumentIconWebPAS, "Doccument Icon in Summary Page");
		AssertStringEquals(DocIcon, "document", "Document icon is present for Documents");
		
		 String DocDispPerd = getText(summPg.DocumentDisplayPeriodWebPAS, "Document Display period");
		 AssertStringContains(DocDispPerd, "LAST 2 WEEKS", "Last 2 Weeks display is present for Documents");
	    
		 String SummDocCnt = getText(summPg.DocumentCntWebPAS, "Document Notes Count");
		 AssertStringEquals(SummDocCnt, "3", "Clinical Document Count in Summary Page");
		 
		// Clicking on Document tile from Summary page
		click(summPg.DocumentCntWebPAS, "Summary Document Count");
		Thread.sleep(3000);
		VerifySelectedTab("Clinical Documents",DeviceName);
		jsclick(summPg.tab_Summary, "Summary Tab");
		Thread.sleep(3000);
			
		// Clicking on Document tab EPR
		click(summPg.tab_WebPASDocuments,"Document EPR Tab");
		VerifySelectedTab("Clinical Documents",DeviceName);
		
		// All tab - all encounter
		click(docpg.allFauxtab,"All");
		Thread.sleep(2000);
		
		int allActiveCount = docpg.getDocumentActiveCount();
		int allINActiveCount = docpg.getDocumentINActiveCount();
		int allCount = allActiveCount+allINActiveCount;
		String allActiveTabCnt1=String.valueOf(allCount);
		AssertStringEquals(allActiveTabCnt1, "4", "Count of documents in All tab for all encounter");
		
		jsclick(docpg.toggle, "Encounter toggle");
		
		// All tab - IP encounter
		int allActiveIPCount = docpg.getDocumentActiveCount();
		int allINActiveIPCount = docpg.getDocumentINActiveCount();
		int allIPCount = allActiveIPCount+allINActiveIPCount;
		String allActiveTabIPCnt1=String.valueOf(allIPCount);
		AssertStringEquals(allActiveTabIPCnt1, "4", "Count of documents in All tab for IP encounter");
		
		jsclick(docpg.toggle, "Encounter toggle");
		
		// Active tab - all encounter
		click(docpg.activeFauxtab,"Active tab");
		Thread.sleep(2000);
				
		int ActiveCount = docpg.getDocumentActiveCount();
		String ActiveTabCnt1=String.valueOf(ActiveCount);
		AssertStringEquals(ActiveTabCnt1, "3", "Count of documents in Active tab for all encounter");
				
		jsclick(docpg.toggle, "Encounter toggle");
		
		// Active tab - IP encounter		
		int ActiveIPCount = docpg.getDocumentActiveCount();
		String ActiveTabIPCnt1=String.valueOf(ActiveIPCount);
		AssertStringEquals(ActiveTabIPCnt1, "3", "Count of documents in Active tab for IP encounter");
			
		// Validation - Final Document
		String FinalType = getText(docpg.FinalDocType,"Final Document Type"); 
		String FinalStatus = getText(docpg.FinalDocStatus,"Final Document Status"); 
		String FinalCreatedOn = getText(docpg.FinalDocCreatedOn,"Final Document CreatedOn"); 
		FinalCreatedOn = FinalCreatedOn.replaceAll(" ", " at ");
		FinalCreatedOn = FinalCreatedOn.replaceAll("-", " ");
		String FinalAuthor = getText(docpg.FinalDocAuthor,"Final Document Author"); 
		String FinalName = getText(docpg.FinalDocName,"Final Document Name"); 
		
		AssertStringEquals(SORIPDocTypeFinal,FinalType,"Final Document Type");
		AssertStringEquals("Current",FinalStatus,"Final Document Status");
		AssertStringContains(SORIPDocDetailFinal,FinalCreatedOn,"Final Document CreatedOn");
		AssertStringContains(FinalAuthor,SORAuthor,"Final Document Author");
		AssertStringContains(SORIPDocSubjectFinal+" SRID",FinalName,"Final Document Name");
		
		// Validation - Draft Document
		String DraftType = getText(docpg.DraftDocType,"Draft Document Type"); 
		String DraftStatus = getText(docpg.DraftDocStatus,"Draft Document Status"); 
		String DraftCreatedOn = getText(docpg.DraftDocCreatedOn,"Draft Document CreatedOn"); 
		DraftCreatedOn = DraftCreatedOn.replaceAll(" ", " at ");
		DraftCreatedOn = DraftCreatedOn.replaceAll("-", " ");
		String DraftAuthor = getText(docpg.DraftDocAuthor,"Draft Document Author"); 
		String DraftName = getText(docpg.DraftDocName,"Draft Document Name"); 
		
		AssertStringEquals(SORIPDocTypeDraft,DraftType,"Draft Document Type");
		AssertStringEquals("Current",DraftStatus,"Draft Document Status");
		AssertStringContains(SORIPDocDetailDraft,DraftCreatedOn,"Draft Document CreatedOn");
		AssertStringContains(DraftAuthor,SORAuthor,"Draft Document Author");
		AssertStringEquals(SORIPDocSubjectDraft+" ^ (Current Draft)",DraftName,"Draft Document Name");
		
		// Validation - Superseded Document
		String SupersededType = getText(docpg.SupDocTypeActive,"Superseded Document Type"); 
		String SupersededStatus = getText(docpg.SupDocStatusActive,"Superseded Document Status"); 
		String SupersededCreatedOn = getText(docpg.SupDocCreatedOnActive,"Superseded Document CreatedOn"); 
		SupersededCreatedOn = SupersededCreatedOn.replaceAll(" ", " at ");
		SupersededCreatedOn = SupersededCreatedOn.replaceAll("-", " ");
		String SupersededAuthor = getText(docpg.SupDocAuthorActive,"Superseded Document Author"); 
		String SupersededName = getText(docpg.SupDocNameActive,"Superseded Document Name"); 
				
		AssertStringEquals(SORIPDocTypeSuperseded,SupersededType,"Superseded Document Type");
		AssertStringEquals("Current",SupersededStatus,"Superseded Document Status");
		AssertStringContains(SORIPDocDetailSuperseded,SupersededCreatedOn,"Superseded Document CreatedOn");
		AssertStringContains(SupersededAuthor,SORAuthor,"Superseded Document Author");
		AssertStringEquals(SORIPDocSubjectSuperseded+" SRID",SupersededName,"Superseded Document Name");

		
		reportStep("Script completed", "INFO");	
		driver.quit();
		
		}
	
}