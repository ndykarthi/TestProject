package com.dedalus.catestcases;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dedalus.capages.EncountersPage;
import com.dedalus.capages.MenuPage;
import com.dedalus.capages.MyCareprovidersPage;
import com.dedalus.capages.MyWorklistPage;
import com.dedalus.capages.SummaryPage;
import com.dedalus.restasssured.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Encounter_IP_Discharged extends RESTAssuredBase{
	Response response,response1;
	JsonPath jsonPath,jsonPath1;
	@Parameters({"PlatformName","DeviceName","PlatformVersion"})
	@BeforeTest
	public void setData(String PlatformName, String DeviceName, String PlatformVersion) throws IOException, InterruptedException, AWTException {
		testCaseName = "Encounter - Inpatient Admission ["+PlatformName+" "+DeviceName+" (Version : "+PlatformVersion+")]"; 
		testDescription = "The following test case verify Encounter - Discharged IP Admission";
		nodes = "The following test case verify Encounter - Discharged IP Admission" ;
		category = "Regression";
		authors = "Karthi";
	}



	@Parameters({"SOR","PlatformName","DeviceName","PlatformVersion","DeviceOrientation","DeviceType"})

	@Test
	public void DischargeIPAdmission(String SOR, String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation, ITestContext context, String DeviceType)
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
		String SORCareProviderTitle = getExposedValue(varResponse, "LCP","LV_CPTitle","LV_CPTitle");
		String SORCareProviderSN = getExposedValue(varResponse, "LCP","LV_CPSurName","LV_CPSurname");
		String SORCareProviderFN = getExposedValue(varResponse, "LCP","LV_CPForeName","LV_CPForename");
		String SORCPTFnSn = SORCareProviderTitle+" "+SORCareProviderFN +" " + SORCareProviderSN;
		String SORCareProvider = SORCareProviderSN +" " + SORCareProviderFN;
		String SORIPSpecialty = getExposedValue(varResponse, "LCP","LV_CPSplty","LV_CPSplty");
		String SORCPTitle = getExposedValue(varResponse, "LCP","LV_CPTitle","LV_CPTitle");
		String SORIPSite = getExposedValue(varResponse, "LCP", "GV_HospitalName", "GV_HospitalName");
		
		String SORIPWard =  getExposedValue(varResponse, "CP", "GV_IP_Ward", "SORIPWard");
		String SORIPBed =  getExposedValue(varResponse, "CP", "IPBed", "SORIPBed");

		String SORIPEncID = getExposedValue(varResponse, "CP", "LV_IP_strEncID", "SORIPEncID ");
		String SORIPAdmittedDate = getExposedValue(varResponse, "LCP", "LV_dtmAdmsnDate", "SORIPAdmittedDate ");
		String SORIPAdmittedtime = getExposedValue(varResponse, "LCP", "LV_AdmsnTime", "SORIPAdmittedTime");
		
		String AdmittedDTTM = SORIPAdmittedDate.replaceAll(" ", "-") + " " + SORIPAdmittedtime.substring(0,5);
		String SORIPDischargedDate = getExposedValue(varResponse, "CP", "LV_dtmDischargeDTTM", "SORIPDischargedDate ");
		//Login to CAUK
        startCAUKApp(SOR, PlatformName,DeviceName,PlatformVersion,DeviceOrientation,SORConfigURL);
        Thread.sleep(8000);
        MenuPage mnuPg = new MenuPage(driver, test);
        EncountersPage enc = new EncountersPage(driver, test);
        MyCareprovidersPage myCPPage = new MyCareprovidersPage(driver, test);
        MyWorklistPage worklistPg = new MyWorklistPage(driver, test);

        String SORPatFullName = SORPatientSurname.toUpperCase() +", " + SORPatientForename;
        click(mnuPg.clickMenu, "Menu");
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
        AssertStringEquals(IPEncBadge, "Finished", "Inpatient encounter Status");
        
        String IPEncCareProvider = getText(enc.ipEncCareProvider, "Inpatient encounter Care provider");
        AssertStringContains(IPEncCareProvider, SORCPTFnSn, "Inpatient encounter Care provider");
        
        String IPEncAdmittedDTTM =getText(enc.ipEncAdmittedDate, "Inpatient encounter Admitted Date");
        AssertStringEquals(IPEncAdmittedDTTM, AdmittedDTTM, "Inpatient encounter Admitted Date");
        
        String IPDischargedDTTM = getText(enc.ipEncDischargeDate, "Inpatient encounter Discharge Date");		
        AssertStringContains(IPDischargedDTTM, SORIPDischargedDate, "Inpatient encounter Discharge Date");
        
        String CalculatedLOS = CalculateTimeDifferenceinMinutes(IPEncAdmittedDTTM.trim(), IPDischargedDTTM.trim());
        String IPEncActualLOS = getText(enc.ipEncActualLOS, "Inpatient encounter Actual LOS");		
        AssertStringEquals(CalculatedLOS, IPEncActualLOS, "Inpatient encounter Actual LOS");
    
		reportStep("Script completed", "INFO");
		driver.quit();
		
	}
}
