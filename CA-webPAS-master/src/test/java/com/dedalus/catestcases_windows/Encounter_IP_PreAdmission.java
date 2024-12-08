package com.dedalus.catestcases_windows;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dedalus.capages_windows.EncountersPage;
import com.dedalus.capages_windows.MenuPage;
import com.dedalus.capages_windows.MyCareprovidersPage;
import com.dedalus.capages_windows.MyWorklistPage;
import com.dedalus.capages_windows.SummaryPage;
import com.dedalus.restasssured_windows.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Encounter_IP_PreAdmission extends RESTAssuredBase{

	JsonPath jsonPath;

	@Parameters({"SOR"})
	@BeforeTest
	public void setData(String SOR) throws IOException, InterruptedException, AWTException {
		testCaseName = "IP Pre-Admission Encounter - "+SOR+" - Windows "; 
		testDescription = "The following test case verify Encounter - Inpatient Pre-Admission and My worklist overview";
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
		String SORCPTFnSn = SORCareProviderTitle+" "+SORCareProviderFN +" " + SORCareProviderSN;
		String SORCareProvider = SORCareProviderSN +" " + SORCareProviderFN;
		String SORIPSpecialty = getExposedValue(varResponse, "LCP","LV_CPSplty","LV_CPSplty");
		String SORCPTitle = getExposedValue(varResponse, "LCP","LV_CPTitle","LV_CPTitle");
			String SORIPSite = getExposedValue(varResponse, "LCP", "GV_HospitalName", "GV_HospitalName");

		if (!SORPatientGender.equalsIgnoreCase("Male") && !SORPatientGender.equalsIgnoreCase("Female")) {
			SORPatientGender = "Other";
		}

		String SORIPWard =  getExposedValue(varResponse, "CP", "LV_strWardName", "SORIPWard");
		String SORIPBed =  getExposedValue(varResponse, "CP", "LV_strBedNumber", "SORIPBed");
		SORIPBed =SORIPBed.substring(1);
		String SORIPEncID = getExposedValue(varResponse, "CP", "LV_IP_strEncID", "SORIPEncID ");
		String SORIPAdmittedDate = getExposedValue(varResponse, "CP", "LV_IP_dtmStartDate", "SORIPAdmittedDate ");
		String SORIPAdmitDiagnosis = getExposedValue(varResponse, "CP", "LV_IP_Diagnosis", "SORIPAdmitDiagnosis ");

//		========================================= VIRTUOSO API =========================================
		
        startApp(SOR,SORConfigURL);
        Thread.sleep(8000);
        MenuPage mnuPg = new MenuPage(driver, test);
        EncountersPage enc = new EncountersPage(driver, test);
        MyWorklistPage worklistPg = new MyWorklistPage(driver, test);

        String SORPatFullName = SORPatientSurname.toUpperCase() +", " + SORPatientForename+ " ("+SORPatientTitle+")";
        click(mnuPg.clickMenu, "Menu");
        Thread.sleep(3000);
        click(mnuPg.menu_Search,"Search");
        Thread.sleep(3000);

        SearchNSelectPatient(SORHRN);
        Thread.sleep(3000);
        
        click(mnuPg.selectEnc,"Encounter");
        Thread.sleep(3000); 

        click(enc.futureFauxtab, "Future faux tab");
		Thread.sleep(3000);
		
		String IPEncID = getText(enc.ipPreAdmitEncID, "Inpatient encounter ID");
		AssertStringEquals(IPEncID, SORIPEncID, "Inpatient encounter ID");
		
		String IPEncBadge = getText(enc.IPPreAdmitEncBadge, "Inpatient encounter Status");
		AssertStringEquals(IPEncBadge, "Planned", "Inpatient encounter Status");
		
		String IPEncPlannedDate = getText(enc.ipPreAdmitEncPlannedDate, "Inpatient encounter Admitted date");
		AssertStringEquals(IPEncPlannedDate, SORIPAdmittedDate, "Inpatient encounter Planned date");
		
		String IPEncCareProvider = getText(enc.ipPreAdmitEncCareProvider, "Inpatient encounter Care provider");
		AssertStringContains(IPEncCareProvider, SORCPTFnSn, "Inpatient encounter Care provider");
		
		explicitWait(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORHRN+"\"]//h2"));
		jsclick(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORHRN+"\"]//h2/ancestor::ion-row/following-sibling::ion-row//ion-icon[@name='star-outline']"), "FavIcon");
		Thread.sleep(10000);

		explicitWait(enc.clickMyWorklistFavIcon);
		jsclick(enc.clickMyWorklistFavIcon, "My worklist overview");
		Thread.sleep(3000);
		
		jsclick(worklistPg.MyWorkListPlannedTab, "My worklist overview IP Planned tab");
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
		
		String MyWL_IP_Site= getText(worklistPg.MyWorkListEncSite, "My worklist OP Enc Site");
		AssertStringContains(MyWL_IP_Site, SORIPSite, "MyWL_IP_Site");
		
		String MyWL_IP_Ward = getText(worklistPg.MyWorkListEncWard, "My worklist OP Enc Ward");
		AssertStringContains(MyWL_IP_Ward, SORIPWard, "MyWL_IP_Ward");
		
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

		String MyWL_IP_Patdet_PlannedDate =  getText(worklistPg.MyWorkListMoreEncPlannedDate, "My worklist IP Enc More details Enc start date");
		AssertStringEquals(MyWL_IP_Patdet_PlannedDate, SORIPAdmittedDate, "MyWL_IP_Patdet_PlannedDate");
		
		jsclick(worklistPg.MyWorkListClosePatDetailsIcon, "My worklist overview close More patient details");
		Thread.sleep(3000);
		
		jsclick(worklistPg.MyWorkListMarkUnfavouriteIcon, "My worklist overview unfavourite");
		Thread.sleep(3000);
		jsclick(worklistPg.MyWorkListAdmittedTab, "My worklist overview IP Admitted tab");
		Thread.sleep(3000);
		jsclick(worklistPg.MyWorkListPlannedTab, "My worklist overview IP Planned tab");
		Thread.sleep(3000);
		
		click(mnuPg.clickMenuNew, "Menu");
		click(mnuPg.clickPreference, "preferences");
		Thread.sleep(10000);
		javascriptScroll(driver.findElementByXPath("//ion-label[text()='Planned admission days']"), SORIPAdmittedDate);
		Thread.sleep(10000);
		scrollMaxRange(mnuPg.PlannedAdmissionSlider);
		Thread.sleep(5000);
		click(mnuPg.clickMenuNew, "Menu");
		Thread.sleep(5000);
		click(mnuPg.clickMyCareProviders, "My Care Providers");
		click(mnuPg.AddCareProvider, "Add Care Provider");
		explicitWait(mnuPg.EnterCareProvider);
		SearchNMarkFavourite("Care Provider",SORCareProviderSN +" " + SORCareProviderFN);
		Thread.sleep(5000);	
		click(mnuPg.clickMenuNew, "Menu");
		Thread.sleep(5000);
		click(mnuPg.clickMyPlannedAdmissions, "My planned Admissions");
		click(mnuPg.clickTomorrowMyPlannedAdmissions, "Tomorrow");
		explicitWait(mnuPg.TomorrowMyPlannedAdmissionsTitle);
		Thread.sleep(10000);
		SearchPatientByName(SORPatFullName);
		VerifyPatientDetails_WardSearchListWebPAS(SORPatFullName,SORPatientDOB,SORHRN,SORIPBed,SORIPWard, SORIPSite);
		VerifyPatientDetails_WardSearchListMoreWebPAS(SORPatientTitle,SORPatFullName, SORPatientDOB ,SORHRN, SORPatientGender, SORCPTFnSn ,SORIPSpecialty ,SORIPSite ,SORIPWard ,SORIPBed ,SORIPEncID, SORIPAdmittedDate,"PreAdmit");
		jsclick(mnuPg.PatDetMoreIcon, "Patient Details More Icon");
		Thread.sleep(10000);
		String DiagnosisName =driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Diagnosis')]/following-sibling::span").getText();
		AssertStringEquals(DiagnosisName, SORIPAdmitDiagnosis, "SORIPAdmitDiagnosis");
		
		reportStep("Script completed", "INFO");
		driver.quit();
		
		
	}
}
