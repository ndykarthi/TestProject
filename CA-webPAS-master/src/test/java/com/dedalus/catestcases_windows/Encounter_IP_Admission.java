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
import com.dedalus.capages_windows.MyWorklistPage;
import com.dedalus.capages_windows.SummaryPage;
import com.dedalus.restasssured_windows.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Encounter_IP_Admission extends RESTAssuredBase{

	JsonPath jsonPath;

	@Parameters({"SOR"})
	@BeforeTest
	public void setData(String SOR) throws IOException, InterruptedException, AWTException {
		testCaseName = "IP Admission Encounter - "+SOR+" - Windows "; 
		testDescription = "The following test case verify Encounter - Inpatient Admission and My worklist overview";
		nodes = SOR;
		category = "Regression";
		authors = "Karthi";
	}



	@Parameters({"SOR"})

	@Test
	public void create(String SOR, ITestContext context)
			throws InterruptedException, IOException, ClassNotFoundException, SQLException, ParseException, AWTException {
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
		String SORPatAddLine1 = getExposedValue(varResponse, "LCP","LV_AddressLine1","LV_AddressLine1");
		String SORPatAddLine2 = getExposedValue(varResponse, "LCP","LV_AddressLine2","LV_AddressLine2"); 
		String SORPatAddLine3 = getExposedValue(varResponse, "LCP","LV_AddressLine3","LV_AddressLine3");
		String SORPatAddLine4 = getExposedValue(varResponse, "LCP","LV_AddressLine4","LV_AddressLine4");
		String SORCareProvider = getExposedValue(varResponse, "LCP","LV_CPFullName","LV_strRecordedBy");
		String SORCareProviderSN = getExposedValue(varResponse, "LCP","LV_CPSurName","LV_CPSurname");
		String SORCareProviderFN = getExposedValue(varResponse, "LCP","LV_CPForeName","LV_CPForename");
		String SORIPSpecialty = getExposedValue(varResponse, "LCP","LV_CPSplty","LV_CPSplty");
		String SORCPTitle = getExposedValue(varResponse, "LCP","LV_CPTitle","LV_CPTitle");
		String SORIPSite = getExposedValue(varResponse, "LCP", "GV_HospitalName", "GV_HospitalName");
		
		String SORIPWard =  getExposedValue(varResponse, "CP", "GV_IP_Ward", "SORIPWard");
		String SORIPBed =  getExposedValue(varResponse, "CP", "IPBed", "SORIPBed");
		SORIPBed = SORIPBed.substring(1);
		String SORIPEncID = getExposedValue(varResponse, "CP", "LV_IP_strEncID", "SORIPEncID ");
		String SORIPAdmittedDate = getExposedValue(varResponse, "CP", "AdmittedDTTM", "SORIPAdmittedDate ");
		//Login to CAUK
        startApp(SOR,SORConfigURL);
        Thread.sleep(8000);
        MenuPage mnuPg = new MenuPage(driver, test);
        EncountersPage enc = new EncountersPage(driver, test);
        MyWorklistPage worklistPg = new MyWorklistPage(driver, test);

        String SORPatFullName = SORPatientSurname.toUpperCase() +", " + SORPatientForename;
        click(mnuPg.clickMenu, "Menu");
        Thread.sleep(3000);
        click(mnuPg.menu_Search,"Search");
        Thread.sleep(3000);

        SearchNSelectPatient(SORHRN);
        Thread.sleep(3000);
        
        click(mnuPg.selectEnc,"Encounter");
        Thread.sleep(3000); 
        
        SORPatFullName = SORPatientSurname.toUpperCase() +", " + SORPatientForename+ " ("+SORPatientTitle+")";
        
        if(SORPatientGender.equals("Transgender"))
        {
        	SORPatientGender = "Other";
        }

        String IPEncID = getText(enc.ipEncID, "Inpatient encounter ID");
        AssertStringEquals(IPEncID, SORIPEncID, "Inpatient encounter ID");
        
        String IPEncBadge = getText(enc.IPEncBadge, "Inpatient encounter Status");
        AssertStringEquals(IPEncBadge, "Arrived", "Inpatient encounter Status");
        
        String IPEncPlannedDate = getText(enc.ipEncAdmittedDate, "Inpatient encounter Admitted date");
        AssertStringEquals(IPEncPlannedDate, SORIPAdmittedDate, "Inpatient encounter Admitted date");
        
        String IPEncCareProvider = getText(enc.ipEncCareProvider, "Inpatient encounter Care provider");
        AssertStringContains(IPEncCareProvider, SORCPTitle+" "+SORCareProviderFN + " "+SORCareProviderSN, "Inpatient encounter Care provider");
                
        explicitWait(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORHRN+"\"]//h2"));
        jsclick(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORHRN+"\"]//h2/ancestor::ion-row/following-sibling::ion-row//ion-icon[@name='star-outline']"), "FavIcon");
        Thread.sleep(3000);
        explicitWait(enc.clickMyWorklistFavIcon);
        jsclick(enc.clickMyWorklistFavIcon, "My worklist overview");
        Thread.sleep(3000);
        jsclick(worklistPg.MyWorkListAdmittedTab, "My worklist overview IP admitted tab");
        Thread.sleep(3000);
        String MyWL_IP_EncounterCount = getText(worklistPg.MyWorkListEncCount, "My worklist IP Enc count");
        if( MyWL_IP_EncounterCount.equals("1")) {
        	reportStep("No. of IP encounters equals 1", "PASS", false);
        	System.out.println("No. of IP encounters equals 1");
        }
        else {
        	reportStep("No. of IP encounters not equals 1", "FAIL", false);
        	System.out.println("No. of IP encounters not equals 1");
        }

        String MyWL_IP_PatName = getText(worklistPg.MyWorkListPatName, "My worklist IP Enc Pat Name");
        AssertStringEquals(MyWL_IP_PatName.toUpperCase(), SORPatFullName.toUpperCase(), "MyWL_IP_PatName");
        
        String MyWL_IP_PatHRN = getText(worklistPg.MyWorkListPatHRN, "My worklist IP Enc Pat HRN Number");
        AssertStringContains(MyWL_IP_PatHRN, SORHRN, "MyWL_IP_PatHRN");
        
        String MyWL_IP_PatDOB = getText(worklistPg.MyWorkListPatDOBWP, "My worklist IP Enc Pat DOB");
        AssertStringContains(MyWL_IP_PatDOB, SORPatientDOB, "MyWL_IP_PatDOB");
        
        String MyWL_IP_Site = getText(worklistPg.MyWorkListEncSite, "My worklist IP Enc Site");
        AssertStringContains(MyWL_IP_Site, SORIPSite, "MyWL_IP_Site");
        
        String MyWL_IP_Ward = getText(worklistPg.MyWorkListEncWard, "My worklist IP Enc Ward");
        AssertStringContains(MyWL_IP_Ward, SORIPWard, "MyWL_IP_Ward");
  
        String MyWL_IP_Bed = getText(worklistPg.MyWorkListEncBed, "My worklist IP Enc Bed");
        MyWL_IP_Bed = MyWL_IP_Bed.substring(3);
        AssertStringEquals(MyWL_IP_Bed, SORIPBed, "MyWL_IP_Bed");
        			
        jsclick(worklistPg.MyWorkListMorePatDetailsIcon, "My worklist overview More patient details");
        Thread.sleep(3000);
        
        String MyWL_IP_Patdet_PatGender = getText(worklistPg.MyWorkListMorePatGender, "My worklist IP Enc More details Pat Gender");
        AssertStringEquals(MyWL_IP_Patdet_PatGender, SORPatientGender, "MyWL_IP_Patdet_PatGender");
        
        String MyWL_IP_Patdet_EncLocation = getText(worklistPg.MyWorkListMoreEncLocation, "My worklist IP Enc More details  Location");
        AssertStringEquals(MyWL_IP_Patdet_EncLocation, MyWL_IP_Ward, "MyWL_IP_Patdet_EncLocation");
        
        String MyWL_IP_Patdet_PatHRN = getText(worklistPg.MyWorkListMorePatHRN, "My worklist IP Enc More details  HRN");
        AssertStringEquals(MyWL_IP_Patdet_PatHRN, SORHRN, "MyWL_IP_Patdet_PatHRN");
        
        String MyWL_IP_Patdet_CPName = getText(worklistPg.MyWorkListMoreEncCareprovider, "My worklist IP Enc More details  CP Name");
        AssertStringContains(MyWL_IP_Patdet_CPName, IPEncCareProvider, "MyWL_IP_Patdet_CPName");
        
        String MyWL_IP_Patdet_PatDOB = getText(worklistPg.MyWorkListMorePatDOBWP, "My worklist IP Enc More details  Pat DOB");
        AssertStringContains(MyWL_IP_Patdet_PatDOB, SORPatientDOB, "MyWL_IP_Patdet_PatDOB");
        
        String MyWL_IP_Patdet_EncID =  getText(worklistPg.MyWorkListMoreEncID, "My worklist IP Enc More details Enc ID");
        AssertStringContains(MyWL_IP_Patdet_EncID, IPEncID, "MyWL_IP_Patdet_EncID");
        
        String MyWL_IP_Patdet_Ward =  getText(worklistPg.MyWorkListMoreEncWard, "My worklist IP Enc More details Enc Ward");
        AssertStringContains(MyWL_IP_Patdet_Ward, MyWL_IP_Ward, "MyWL_IP_Patdet_Ward");
        
        String MyWL_IP_Patdet_PlannedDate =  getText(worklistPg.MyWorkListMoreEncAdmittedDate, "My worklist IP Enc More details Enc start date");
        AssertStringEquals(MyWL_IP_Patdet_PlannedDate, SORIPAdmittedDate, "MyWL_IP_Patdet_PlannedDate");

        jsclick(worklistPg.MyWorkListClosePatDetailsIcon, "My worklist overview close More patient details");
        Thread.sleep(3000);
        
		reportStep("Script completed", "INFO");
		driver.quit();
		
		
	}
}
