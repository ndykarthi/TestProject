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
import com.dedalus.capages_windows.DocumentsPage;
import com.dedalus.capages_windows.MenuPage;
import com.dedalus.capages_windows.SummaryPage;
import com.dedalus.restasssured_windows.RESTAssuredBase;
import io.restassured.response.Response;


public class Document_InActive extends RESTAssuredBase {

  @Parameters({"SOR"})
		
  @BeforeTest
  public void setData(String SOR) throws IOException, InterruptedException, AWTException {
    testCaseName = "Patient Inactive Document validations ";
    testDescription = "This test is to verify the InActive Document with IP and Emergency Encounter for a patient";
    nodes = SOR;
    category = "Regression";
    authors = "Kavitha";
  }
		
  @Parameters({"SOR"})
  @Test
  public void alerts(String SOR, ITestContext context) throws InterruptedException, IOException, ClassNotFoundException, SQLException, ParseException, AWTException {

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
		Assert.assertEquals(virtuosoOutcome, "PASS","Virtuoso Journey Pass");
		
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
			
		String SORIPDocTypeSuperseded = getExposedValue(varResponse, "CP", "LV_IP_DocTypesuperseded", "LV_IP_DocTypesuperseded");
	//	String SORIPDocTemplateSuperseded = getExposedValue(varResponse, "CP", "LV_IP_TemplateSuperseded", "LV_IP_TemplateSuperseded");
		String SORIPDocSubjectSuperseded = getExposedValue(varResponse, "CP", "LV_IP_SubjectSuperseded", "LV_IP_SubjectSuperseded");
		String SORIPDocDetailSuperseded = getExposedValue(varResponse, "CP", "LV_DOcdeatilSuperseded", "LV_DOcdeatilSuperseded");
		
		String SORIPDocTypeDelete = getExposedValue(varResponse, "CP", "LV_IP_DocTypeDelete", "LV_IP_DocTypeDelete");
	//	String SORIPDocTemplateDelete = getExposedValue(varResponse, "CP", "LV_IP_TemplateDelete", "LV_IP_TemplateDelete");
		String SORIPDocSubjectDelete = getExposedValue(varResponse, "CP", "LV_IP_SubjectDelete", "LV_IP_SubjectDraft");
		String SORIPDocDetailDelete = getExposedValue(varResponse, "CP", "LV_DOcdetail_Delete", "LV_DOcdetail_Delete");
	
		String SORAuthor = getExposedValue(varResponse,"LCP","LV_CPFullName","LV_CPFullName");
		String SORToAuthCode = getExposedValue(varResponse,"LCP","LV_CPCode","LV_CPCode");
				
		reportStep("PASID : "+SORHRN, "INFO");
    
    startApp(SOR,SORConfigURL);
    Thread.sleep(8000);
    MenuPage mnuPg = new MenuPage(driver, test);
	 SummaryPage summPg = new SummaryPage(driver, test);
	 DocumentsPage docpg = new DocumentsPage(driver, test);
	 
	 click(mnuPg.clickMenu, "Menu");
	 Thread.sleep(3000);
	 click(mnuPg.menu_Search,"Search");
	 Thread.sleep(3000);
	 System.out.println("Patient ID: "+SORHRN);
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
		
	javascriptScroll(summPg.DocumentSummaryWebPAS, "Document Tile");
	
	String DocIcon = getIconName(summPg.DocumentIconWebPAS, "Doccument Icon in Summary Page");
	AssertStringEquals(DocIcon, "document", "Document icon is present for Documents");
	
	 String DocDispPerd = getText(summPg.DocumentDisplayPeriodWebPAS, "Document Display period");
	 AssertStringContains(DocDispPerd, "LAST 2 WEEKS", "Last 2 Weeks display is present for Documents");
    
	 String SummDocCnt = getText(summPg.DocumentCntWebPAS, "Document Notes Count");
	 AssertStringEquals(SummDocCnt, "1", "Clinical Document Count in Summary Page");
	 
	// Clicking on Document tile from Summary page
	click(summPg.DocumentCntWebPAS, "Summary Document Count");
	Thread.sleep(3000);
	VerifySelectedTab("Clinical Documents","Windows");
	jsclick(summPg.tab_Summary, "Summary Tab");
	Thread.sleep(3000);
		
	// Clicking on Document tab EPR
	click(summPg.tab_WebPASDocuments,"Document EPR Tab");
	VerifySelectedTab("Clinical Documents","Windows");
	
	// All tab - all encounter
	click(docpg.allFauxtab,"All");
	Thread.sleep(2000);
	
	int allActiveCount = docpg.getDocumentActiveCount();
	int allINActiveCount = docpg.getDocumentINActiveCount();
	int allCount = allActiveCount+allINActiveCount;
	String allActiveTabCnt1=String.valueOf(allCount);
	AssertStringEquals(allActiveTabCnt1, "3", "Count of documents in All tab for all encounter");
	
	click(docpg.toggle, "Encounter toggle");
	
	// All tab - IP encounter
	int allActiveIPCount = docpg.getDocumentActiveCount();
	int allINActiveIPCount = docpg.getDocumentINActiveCount();
	int allIPCount = allActiveIPCount+allINActiveIPCount;
	String allActiveTabIPCnt1=String.valueOf(allIPCount);
	AssertStringEquals(allActiveTabIPCnt1, "3", "Count of documents in All tab for IP encounter");
	
	click(docpg.toggle, "Encounter toggle");
		
	// InActive tab - all encounter
	click(docpg.inactiveFauxtab,"InActive tab");
	Thread.sleep(2000);
				
	int InActiveCount = docpg.getDocumentINActiveCount(); 
	String InActiveTabCnt1=String.valueOf(InActiveCount);
	AssertStringEquals(InActiveTabCnt1, "2", "Count of documents in InActive tab for all encounter");
				
	click(docpg.toggle, "Encounter toggle");
		
	// InActive tab - IP encounter		
	int InActiveIPCount = docpg.getDocumentINActiveCount();
	String InActiveTabIPCnt1=String.valueOf(InActiveIPCount);
	AssertStringEquals(InActiveTabIPCnt1, "2", "Count of documents in InActive tab for IP encounter");
	
	// Validation - Superseded
	String InActiveSupersededType = getText(docpg.SupDocTypeInActive,"Superseded Document Type InActive Tab"); 
	String InActiveSupersededStauts = getText(docpg.SupDocStatusInActive,"Superseded Document Status InActive Tab"); 
	String InActiveSupersededCreatedOn = getText(docpg.SupDocCreatedOnInActive,"Superseded Document CreatedOn InActive Tab"); 
	InActiveSupersededCreatedOn = InActiveSupersededCreatedOn.replaceAll(" ", " at ");
	InActiveSupersededCreatedOn = InActiveSupersededCreatedOn.replaceAll("-", " ");
	String InActiveSupersededAuthor = getText(docpg.SupDocAuthorInActive,"Superseded Document Author InActive Tab"); 
	String InActiveSupersededName = getText(docpg.SupDocNameInActive,"Superseded Document Name InActive Tab"); 
	
	AssertStringEquals(SORIPDocTypeSuperseded,InActiveSupersededType,"Superseded Document Type");
	AssertStringEquals("Superseded",InActiveSupersededStauts,"Superseded Document Status");
	AssertStringContains(SORIPDocDetailSuperseded,InActiveSupersededCreatedOn,"Superseded Document CreatedOn");
	AssertStringContains(InActiveSupersededAuthor,SORAuthor,"Superseded Document Author");
	AssertStringEquals(SORIPDocSubjectSuperseded+" SRID^ (Draft)",InActiveSupersededName,"Superseded Document Name");
	
	// Validation - Entered in error
	String EIEType = getText(docpg.EnteredInErrorDocType,"EnteredInError Document Type"); 
	String EIEStatus = getText(docpg.EnteredInErrorDocStatus,"EnteredInError Document Status"); 
	String EIECreatedOn = getText(docpg.EnteredInErrorDocCreatedOn,"EnteredInError Document CreatedOn"); 
	EIECreatedOn = EIECreatedOn.replaceAll(" ", " at ");
	EIECreatedOn = EIECreatedOn.replaceAll("-", " ");
	String EIEAuthor = getText(docpg.EnteredInErrorDocAuthor,"EnteredInError Document Author"); 
	String EIEName = getText(docpg.EnteredInErrorDocName,"EnteredInError Document Name"); 
  
	AssertStringEquals(SORIPDocTypeDelete,EIEType,"Entered in error Document Type");
	AssertStringEquals("Entered-in-error",EIEStatus,"Entered in error Document Status");
	AssertStringContains(SORIPDocDetailDelete,EIECreatedOn,"Entered in error Document CreatedOn");
	AssertStringContains(EIEAuthor,SORAuthor,"Entered in error Document Author");
	AssertStringEquals(SORIPDocSubjectDelete+" (DELETED)",EIEName,"Entered in error Document Name");
	
	reportStep("Script completed", "INFO");
	
    driver.quit();
	
  }
  
}