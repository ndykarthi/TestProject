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
import com.dedalus.capages.EncountersPage;
import com.dedalus.capages.MenuPage;
import com.dedalus.capages.MyWorklistPage;
import com.dedalus.capages.PatientBannerPage;
import com.dedalus.capages.SummaryPage;
import com.dedalus.restasssured.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Ward_Leave extends RESTAssuredBase {

	JsonPath jsonPath;
  @Parameters({"PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
		
  @BeforeTest
  public void setData(String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation) throws IOException, InterruptedException, AWTException {
    testCaseName = "Ward Leave validations"+PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
    testDescription = "This test is to verify Ward Leave for a patient";
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
		SORPatAddLine3 = convertCamelCase(SORPatAddLine3);
		String SORPatAdd = SORPatAddLine1+", "+SORPatAddLine2+" "+SORPatAddLine3+" "+SORPatAddLine4+" "+SORPostCode;
	    
		
		String SORPatFN = SORPatientSurname+", "+SORPatientForename;
		 
	String SORIPWard = getExposedValue(varResponse, "LCP", "GV_IP_Ward", "GV_IP_Ward");
	String SORLocation = SORIPWard;
	String SORIPSite = getExposedValue(varResponse, "LCP", "GV_HospitalName", "GV_HospitalName");
	String SORIPBed = getExposedValue(varResponse, "LCP", "LV_IPBed", "LV_IPBed");
	SORIPBed = SORIPBed.replace("B", "Bed ");
	String SORCPSurName = getExposedValue(varResponse, "LCP", "LV_CPSurName", "LV_CPSurName");
    String SORCPForeName = 	getExposedValue(varResponse, "LCP", "LV_CPForeName", "LV_CPForeName");	
    String SORCPTitle = getExposedValue(varResponse, "LCP", "LV_CPTitle", "LV_CPTitle");
	String SORCPSplty = getExposedValue(varResponse, "LCP", "LV_CPSplty", "LV_CPSplty");
	String SORVisitNo = getExposedValue(varResponse, "CP", "LV_IP_strEncID", "LV_IP_strEncID");
	String SORAdmittedDate = getExposedValue(varResponse, "CP", "LV_IP_dtmStartDate", "LV_IP_dtmStartDate");
	//String SORExpDiscDate = "09-Oct-2024 18:11";
	String SORCPName = SORCPSurName+" "+SORCPForeName;
	String SORCPFullName = SORCPTitle+" "+SORCPForeName+" "+SORCPSurName;
	    
    reportStep("PASID : "+SORHRN, "INFO");
            
		startCAUKApp(SOR,PlatformName,DeviceName,PlatformVersion,DeviceOrientation,SORConfigURL);
		Thread.sleep(8000);
		MenuPage mnuPg = new MenuPage(driver, test);
		MyWorklistPage wardLvePg = new MyWorklistPage(driver, test);
		EncountersPage enc = new EncountersPage(driver, test);
		
		 // My Wards
	    jsclick(mnuPg.clickMenu, "Menu");
	    Thread.sleep(3000);
	    click(mnuPg.clickMyWard,"My Wards");
	    click(mnuPg.AddWard, "Add Ward");
	    SearchNMarkFavourite("Ward",SORIPWard);
		click(mnuPg.clickMenu,"Menu");
		Thread.sleep(3000);
		click(mnuPg.clickMyWard,"My Wards");
		SelectFavourite("My Wards",SORIPWard);
		Thread.sleep(5000);
		jsclick(wardLvePg.WardLeaveTab, "Ward Leave Tab");
		Thread.sleep(3000);
		jsclick(wardLvePg.WardLeaveTab, "Ward Leave Tab");
		SearchNMarkFavourite_WardLeave("Name Search", SORHRN,SORPatientSurname);
		VerifyPatientDetails_WardListWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle,SORPatientDOB,SORHRN,SORIPSite,SORIPWard,SORIPBed);
		VerifyPatientDetails_WardListMoreWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle,SORPatientGender ,SORPatientDOB,SORHRN);
		VerifyCareProviderDetails_WardListMoreWebPAS(SORCPFullName,SORCPSplty,SORLocation,SORIPSite,SORIPWard,SORIPBed);
		VerifyEncounterDetails_WardListMoreWebPAS(SORVisitNo,SORAdmittedDate);
		//SearchNMarkFavourite("Name Search", SORPatientSurname);
		jsclick(mnuPg.clickMenu, "Menu");
	    Thread.sleep(3000);
	    click(mnuPg.clickMyWorklist, "My Worklist");
	    jsclick(wardLvePg.WorkListWardLeaveTab, "Ward Leave Tab");   
	    VerifyFavouritePatient(SORPatFN, "Ward Leave");
	    MarkUnFavourite_WardLeave(SORHRN);
	    
	    // Search
	    jsclick(mnuPg.clickMenu, "Menu");
	    Thread.sleep(3000);
	    click(mnuPg.menu_Search,"Search");
		Thread.sleep(8000);
		System.out.println("Patient ID: "+SORHRN);
		//SearchPatient(SORHRN);
		SearchNMarkFavourite_Search("Patient Identifier", SORHRN);
		VerifyPatientDetails_SearchListWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle,SORPatientDOB,SORHRN, SORPatAdd);
		VerifyPatientDetails_SearchListMoreWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle, SORPatientGender,SORPatientDOB,SORHRN);
		jsclick(mnuPg.clickMenu, "Menu");
	    Thread.sleep(3000);
	    click(mnuPg.clickMyWorklist, "My Worklist");
	    jsclick(wardLvePg.WorkListWardLeaveTab, "Ward Leave Tab");
	    
	    VerifyFavouritePatient(SORPatFN, "Ward Leave");
	    MarkUnFavourite_WardLeave(SORHRN);

	    // Care Provider
	    jsclick(mnuPg.clickMenu, "Menu");
	    Thread.sleep(3000);
	    click(mnuPg.clickMyCareProviders,"Click My Care Providers");
	    click(mnuPg.AddCareProvider,"Add Care Provider");
	    SearchNMarkFavourite("Care Provider",SORCPSurName);
		click(mnuPg.clickMenu,"Menu");
		Thread.sleep(3000);
		click(mnuPg.clickMyCareProviders,"My Care Providers");
	    SelectFavourite("My Care Providers", SORCPName);
	    Thread.sleep(5000);
		jsclick(wardLvePg.WardLeaveTab, "Ward Leave Tab");
		Thread.sleep(3000);
		SearchNMarkFavourite_WardLeave("Name Search", SORHRN,SORPatientSurname);
		VerifyPatientDetails_WardListWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle,SORPatientDOB,SORHRN,SORIPSite,SORIPWard,SORIPBed);
		VerifyPatientDetails_WardListMoreWebPAS(SORPatientSurname,SORPatientForename,SORPatientTitle,SORPatientGender ,SORPatientDOB,SORHRN);
		VerifyCareProviderDetails_WardListMoreWebPAS(SORCPFullName,SORCPSplty,SORLocation,SORIPSite,SORIPWard,SORIPBed);
		VerifyEncounterDetails_WardListMoreWebPAS(SORVisitNo,SORAdmittedDate);
		jsclick(mnuPg.clickMenu, "Menu");
	    Thread.sleep(3000);
	    click(mnuPg.clickMyWorklist, "My Worklist");
	    jsclick(wardLvePg.WorkListWardLeaveTab, "Ward Leave Tab");
	    Thread.sleep(3000);
	    VerifyFavouritePatient(SORPatFN, "Ward Leave");
	    
	    String PatientFN = SORPatientSurname.toUpperCase()+", "+SORPatientForename+" ("+SORPatientTitle+")";
	    SelectPatient(PatientFN);
		Thread.sleep(5000);
		click(mnuPg.selectEnc,"Encounter");
		Thread.sleep(3000); 
		 String IPEncID = getText(enc.ipEncID, "Inpatient encounter ID");
	     AssertStringEquals(IPEncID, SORVisitNo, "Inpatient encounter ID");
	     
	     String IPEncBadge = getText(enc.IPEncBadge, "Inpatient encounter Status");
	     AssertStringEquals(IPEncBadge, "Onleave", "Inpatient encounter Status");
	     
	     String IPEncPlannedDate = getText(enc.ipEncAdmittedDate, "Inpatient encounter Admitted date");
	     AssertStringEquals(IPEncPlannedDate, SORAdmittedDate, "Inpatient encounter Admitted date");
	     
	     String IPEncCareProvider = getText(enc.ipEncCareProvider, "Inpatient encounter Care provider");
	     AssertStringContains(IPEncCareProvider, SORCPFullName, "Inpatient encounter Care provider");        
	      
	  reportStep("Script completed", "INFO");
		
	  driver.quit();
	   
	  }
	}