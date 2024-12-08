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

import com.dedalus.capages.EncountersPage;
import com.dedalus.capages.MenuPage;
import com.dedalus.capages.MyWorklistPage;
import com.dedalus.capages.SummaryPage;
import com.dedalus.capages.TheatresPage;
import com.dedalus.restasssured.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Theatres_IPEnc_TodayTab extends RESTAssuredBase{
	Response response,response1;
	JsonPath jsonPath,jsonPath1;
	@Parameters({"PlatformName","DeviceName","PlatformVersion"})
	@BeforeTest
	public void setData(String PlatformName, String DeviceName, String PlatformVersion) throws IOException, InterruptedException, AWTException {
		testCaseName = "Emergency Encounter ["+PlatformName+" "+DeviceName+" (Version : "+PlatformVersion+")]"; 
		testDescription = "The following test case verify Emergency Encounter and My worklist overview";
		nodes = "The following test case verify Emergency Encounter and My worklist overview" ;
		category = "Regression";
		authors = "Karthi";
	}



	@Parameters({"SOR","PlatformName","DeviceName","PlatformVersion","DeviceOrientation","DeviceType"})

	@Test
	public void ViewEncountersTest(String SOR, String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation, ITestContext context, String DeviceType)
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
		String SORPatAddLine1 = getExposedValue(varResponse, "LCP","LV_AddressLine1","LV_AddressLine1");
		String SORPatAddLine2 = getExposedValue(varResponse, "LCP","LV_AddressLine2","LV_AddressLine2"); 
		String SORPatAddLine3 = getExposedValue(varResponse, "LCP","LV_AddressLine3","LV_AddressLine3");
		String SORPatAddLine4 = getExposedValue(varResponse, "LCP","LV_AddressLine4","LV_AddressLine4");
		String SORCareProviderSN = getExposedValue(varResponse, "LCP","LV_CPSurName","CPSN");
		String SORCareProviderFN = getExposedValue(varResponse, "LCP","LV_CPForeName","CPFN");
		String SORCareProviderTitle = getExposedValue(varResponse, "LCP","LV_CPTitle","LV_CPTitle");
		String SORCareProvider = SORCareProviderTitle+" "+SORCareProviderFN +" " + SORCareProviderSN;
		String SORIPSpecialty = getExposedValue(varResponse, "LCP","LV_CPSplty","LV_CPSplty");
	

		String SORPatFullName = SORPatientSurname.toUpperCase() +", " + SORPatientForename;

		String SORSurgeon =  getExposedValue(varResponse, "CP", "LV_strSurgen_Theatre", "SORSurgeon");
		String SORSurgeonSurname = SORSurgeon.split(" ")[2];
		String SORSurgeonForename = SORSurgeon.split(" ")[1];
		String SORSurgeonFullname = SORSurgeonSurname +" "+SORSurgeonForename;
		String SORAnaesthetist =  getExposedValue(varResponse, "CP", "LV_strAnasthetist_Theatre", "SORAnaesthetist");
		String SORAnaesthetistSurname = SORAnaesthetist.split(" ")[2];
		String SORAnaesthetistForename = SORAnaesthetist.split(" ")[1];
		String SORAnaesthetistFullname = SORAnaesthetistSurname +" "+SORAnaesthetistForename;
		String SORExtraDoc =  getExposedValue(varResponse, "CP", "LV_strThtrExtraDoctor", "SORExtraDoc");
		String SORExtraDocSurname = SORExtraDoc.split(" ")[1];
		String SORExtraDocForename = SORExtraDoc.split(" ")[2];
		String SORExtraDocFullname = SORExtraDocSurname +" "+SORExtraDocForename;
		String SOROperatingUnit =  getExposedValue(varResponse, "CP", "LV_OperatingUnit", "SOROperatingUnit");
		String SOROperationTheatre =  getExposedValue(varResponse, "CP", "LV_strOperationTheatre", "SOROperationTheatre");
		if(SOROperationTheatre.equals(""))
		{
			SOROperationTheatre = "Not Allocated";
		}
		String SORTheatreBookingDate = getExposedValue(varResponse, "CP", "LV_dtmDateBooking", "SORTheatreBookingDate");
		String SORTheatreBookingDateTime = getExposedValue(varResponse, "CP", "LV_BookingDateTime", "SORTheatreBookingTime");
		String SORDuration = getExposedValue(varResponse, "CP", "LV_Duration", "SORDuration");
		String SORAnaestheticType =  getExposedValue(varResponse, "CP", "LV_AnaestheticType", "SORAnaestheticType");
		String SORMBSCodes = getExposedValue(varResponse, "CP", "MBSDETAILS", "MBSDETAILS");
		String SORReason =  getExposedValue(varResponse, "CP", "ThtrBookingReason", "ThtrBookingReason");
		String SORIPSite = getExposedValue(varResponse, "LCP", "GV_HospitalName", "GV_HospitalName");
		
		String SORIPWard =  getExposedValue(varResponse, "LCP", "GV_IP_Ward", "SORIPWard");
		String SORIPBed =  getExposedValue(varResponse, "CP", "IPBed", "SORIPBed");
		SORIPBed = SORIPBed.substring(1);
		String SORIPEncID = getExposedValue(varResponse, "CP", "LV_IP_EncID", "SORIPEncID ");
		String SORIPAdmittedDate = getExposedValue(varResponse, "CP", "AdmittedDTTM", "SORIPAdmittedDate ");

		
		//Login to CAUK
        startCAUKApp(SOR, PlatformName,DeviceName,PlatformVersion,DeviceOrientation,SORConfigURL);
        Thread.sleep(8000);
        MenuPage mnuPg = new MenuPage(driver, test);
		TheatresPage ThtrPg = new TheatresPage(driver, test);
		click(mnuPg.clickMenuNew, "Menu");
		click(mnuPg.clickMyCareProviders, "My Care Providers");
		click(mnuPg.AddCareProvider, "Add Care Provider");
		explicitWait(mnuPg.EnterCareProvider);
		SearchNMarkFavourite("Care Provider",SORSurgeonFullname);
		click(mnuPg.EnterCareProvider,"Enter CP NAme");
		mnuPg.EnterCareProvider.clear();
		SearchNMarkFavourite("Care Provider", SORAnaesthetistFullname);
		click(mnuPg.EnterCareProvider,"Enter CP NAme");
		mnuPg.EnterCareProvider.clear();
		SearchNMarkFavourite("Care Provider", SORExtraDocFullname);
		
		click(mnuPg.clickMenuNew, "Menu");
		click(mnuPg.clickTheatres, "Theatres");
		click(driver.findElementByXPath("//ion-label[contains(text(),'"+SORSurgeonFullname+"')]"), "Intended Care Provider");
		Thread.sleep(10000);

		explicitWait(ThtrPg.enterNamesearch);
		enterText(ThtrPg.enterNamesearch, SORPatFullName, "Name Search");
		Thread.sleep(15000);
		String  BookedPatientDoB= getText(driver.findElementByXPath("//*[contains(text(),'"+SORPatFullName+"')]/ancestor::ion-row//span[contains(text(),'DoB')]/parent::span"), "Theatres Booking Search - Patient DOB");
		AssertStringEquals(BookedPatientDoB, ("DoB "+SORPatientDOB), "Patient DOB in Search list");

		String BookedPatientHRN = getText(driver.findElementByXPath("//*[contains(text(),'"+SORPatFullName+"')]/ancestor::ion-row//span[contains(text(),'HRN')]/parent::span"), "Theatres Booking Search - Patient HRN");
		AssertStringEquals(BookedPatientHRN, ("HRN "+SORHRN), "Patient HRN in Search list");

		String BookedTheatreName = getText(driver.findElementByXPath("//*[contains(text(),'"+SORPatFullName+"')]/ancestor::ion-row//*[contains(text(),'HRN')]/parent::span/parent::ion-col/parent::ion-row/following-sibling::ion-row//span"), "Theatres Booking Search - Booked Theatre name");
		AssertStringEquals(BookedTheatreName.trim().toUpperCase(), SOROperationTheatre.trim().toUpperCase(), "Theatre Name in Search list");

		String BookingTime = getText(driver.findElementByXPath("//*[contains(text(),'"+SORPatFullName+"')]/ancestor::ion-item//*[contains(@class,'timeBatch')]"), "Theatres Booking Search - Booking Time");
		//AssertStringContains(SORTheatreBookingDateTime, BookingTime, "Theatre Booking Time in Search list");

		jsclick(ThtrPg.PatDetMoreIcon, "More Icon");
		Thread.sleep(3000);


		String PatDetMorePatName = getText(ThtrPg.PatDetMorePatName, "Patient details - Patient Name");
		AssertStringContains(PatDetMorePatName.toUpperCase(), SORPatFullName.toUpperCase(), "Patient details - Patient Name");

		String PatDetMorePatDOB = getText(ThtrPg.PatDetMorePatDOB, "Patient details - Patient DOB");
		AssertStringEquals(PatDetMorePatDOB, SORPatientDOB, "Patient details - Patient DOB");

		String PatDetMorePatGender = getText(ThtrPg.PatDetMorePatGender, "Patient details - Patient Gender");
		AssertStringEquals(PatDetMorePatGender, SORPatientGender, "Patient details - Patient Gender");

		String PatDetMorePatHRN = getText(ThtrPg.PatDetMorePatHRN, "Patient details - Patient HRN");
		AssertStringEquals(PatDetMorePatHRN, SORHRN, "Patient details - Patient HRN");

		String PatDetMoreSurgeon = getText(ThtrPg.PatDetMoreSurgeon, "Theatre details - Surgeon");
		AssertStringEquals(PatDetMoreSurgeon, SORSurgeon, "Theatre details - Surgeon");

		String PatDetMoreAneasthetist = getText(ThtrPg.PatDetMoreAneasthetist, "Theatre details - Anaesthetist");
		AssertStringEquals(PatDetMoreAneasthetist, SORAnaesthetist, "Theatre details - Anaesthetist");

		String PatDetMoreOperationUnit = getText(ThtrPg.PatDetMoreOperationUnit, "Theatre details - Operating Unit");
		AssertStringEquals(PatDetMoreOperationUnit.trim().toUpperCase(), SOROperatingUnit.trim().toUpperCase(), "Theatre details - Operating Unit");

		String PatDetMoreOperationTheatre = getText(ThtrPg.PatDetMoreOperationTheatre, "Theatre details - Theatre");
		AssertStringEquals(PatDetMoreOperationTheatre.trim().toUpperCase(), SOROperationTheatre.trim().toUpperCase(), "Theatre details - Theatre");

		String PatDetMoreOperationDate = getText(ThtrPg.PatDetMoreOperationDate, "Theatre details - Operation Date");
		AssertStringContains(PatDetMoreOperationDate, SORTheatreBookingDate, "Theatre details - Operation Date");

		String PatDetMoreOperationDuration = getText(ThtrPg.PatDetMoreOperationDuration, "Theatre details - Duration");
		AssertStringEquals(PatDetMoreOperationDuration, (SORDuration+" minute(s)"), "Theatre details - Duration");

		String PatDetMoreAnestheticType = getText(ThtrPg.PatDetMoreAnestheticType, "Theatre details - Anasethetic type");
		AssertStringEquals(PatDetMoreAnestheticType, SORAnaestheticType, "Theatre details -Anasethetic type");

		String PatDetMoreMBSCodes = getText(ThtrPg.PatDetMoreMBSCodes, "Theatre details - MBS Codes");
		AssertStringEquals(PatDetMoreMBSCodes, SORMBSCodes, "Theatre details - MBS Codes");

		String PatDetMoreReason = getText(ThtrPg.PatDetMoreReason, "Theatre details - Reason");
		AssertStringEquals(PatDetMoreReason, SORReason, "Theatre details - Reason");

		jsclick(ThtrPg.closeicon, "Close Pat details");

		click(mnuPg.clickMenuNew, "Menu");
		click(mnuPg.clickTheatres, "Theatres");
		click(driver.findElementByXPath("//ion-label[contains(text(),'"+SORAnaesthetistFullname+"')]"), "Intended Care Provider");
		Thread.sleep(10000);

		explicitWait(ThtrPg.enterNamesearch);
		enterText(ThtrPg.enterNamesearch, SORPatFullName, "Name Search");
		Thread.sleep(15000);
		String  CP2BookedPatientDoB= getText(driver.findElementByXPath("//*[contains(text(),'"+SORPatFullName+"')]/ancestor::ion-row//span[contains(text(),'DoB')]/parent::span"), "Theatres Booking Search - Patient DOB");
		AssertStringEquals(CP2BookedPatientDoB, ("DoB "+SORPatientDOB), "Patient DOB in Search list");

		String CP2BookedPatientHRN = getText(driver.findElementByXPath("//*[contains(text(),'"+SORPatFullName+"')]/ancestor::ion-row//span[contains(text(),'HRN')]/parent::span"), "Theatres Booking Search - Patient HRN");
		AssertStringEquals(CP2BookedPatientHRN, ("HRN "+SORHRN), "Patient HRN in Search list");

		String CP2BookedTheatreName = getText(driver.findElementByXPath("//*[contains(text(),'"+SORPatFullName+"')]/ancestor::ion-row//*[contains(text(),'HRN')]/parent::span/parent::ion-col/parent::ion-row/following-sibling::ion-row//span"), "Theatres Booking Search - Booked Theatre name");
		AssertStringEquals(CP2BookedTheatreName.trim().toUpperCase(), SOROperationTheatre.trim().toUpperCase(), "Theatre Name in Search list");

		String CP2BookingTime = getText(driver.findElementByXPath("//*[contains(text(),'"+SORPatFullName+"')]/ancestor::ion-item//*[contains(@class,'timeBatch')]"), "Theatres Booking Search - Booking Time");
		//AssertStringContains(SORTheatreBookingDateTime, CP2BookingTime, "Theatre Booking Time in Search list");

		jsclick(ThtrPg.PatDetMoreIcon, "More Icon");
		Thread.sleep(3000);


		String CP2PatDetMorePatName = getText(ThtrPg.PatDetMorePatName, "Patient details - Patient Name");
		AssertStringContains(CP2PatDetMorePatName.toUpperCase(), SORPatFullName.toUpperCase(), "Patient details - Patient Name");

		String CP2PatDetMorePatDOB = getText(ThtrPg.PatDetMorePatDOB, "Patient details - Patient DOB");
		AssertStringEquals(CP2PatDetMorePatDOB, SORPatientDOB, "Patient details - Patient DOB");

		String CP2PatDetMorePatGender = getText(ThtrPg.PatDetMorePatGender, "Patient details - Patient Gender");
		AssertStringEquals(CP2PatDetMorePatGender, SORPatientGender, "Patient details - Patient Gender");

		String CP2PatDetMorePatHRN = getText(ThtrPg.PatDetMorePatHRN, "Patient details - Patient HRN");
		AssertStringEquals(CP2PatDetMorePatHRN, SORHRN, "Patient details - Patient HRN");

		String CP2PatDetMoreSurgeon = getText(ThtrPg.PatDetMoreSurgeon, "Theatre details - Surgeon");
		AssertStringEquals(CP2PatDetMoreSurgeon, SORSurgeon, "Theatre details - Surgeon");

		String CP2PatDetMoreAneasthetist = getText(ThtrPg.PatDetMoreAneasthetist, "Theatre details - Anaesthetist");
		AssertStringEquals(CP2PatDetMoreAneasthetist, SORAnaesthetist, "Theatre details - Anaesthetist");

		String CP2PatDetMoreOperationUnit = getText(ThtrPg.PatDetMoreOperationUnit, "Theatre details - Operating Unit");
		AssertStringEquals(CP2PatDetMoreOperationUnit, SOROperatingUnit, "Theatre details - Operating Unit");

		String CP2PatDetMoreOperationTheatre = getText(ThtrPg.PatDetMoreOperationTheatre, "Theatre details - Theatre");
		AssertStringEquals(CP2PatDetMoreOperationTheatre.trim().toUpperCase(), SOROperationTheatre.trim().toUpperCase(), "Theatre details - Theatre");

		String CP2PatDetMoreOperationDate = getText(ThtrPg.PatDetMoreOperationDate, "Theatre details - Operation Date");
		AssertStringContains(CP2PatDetMoreOperationDate, SORTheatreBookingDate, "Theatre details - Operation Date");

		String CP2PatDetMoreOperationDuration = getText(ThtrPg.PatDetMoreOperationDuration, "Theatre details - Duration");
		AssertStringEquals(CP2PatDetMoreOperationDuration, (SORDuration+" minute(s)"), "Theatre details - Duration");

		String CP2PatDetMoreAnestheticType = getText(ThtrPg.PatDetMoreAnestheticType, "Theatre details - Anasethetic type");
		AssertStringEquals(CP2PatDetMoreAnestheticType, SORAnaestheticType, "Theatre details -Anasethetic type");

		String CP2PatDetMoreMBSCodes = getText(ThtrPg.PatDetMoreMBSCodes, "Theatre details - MBS Codes");
		AssertStringEquals(CP2PatDetMoreMBSCodes, SORMBSCodes, "Theatre details - MBS Codes");

		String CP2PatDetMoreReason = getText(ThtrPg.PatDetMoreReason, "Theatre details - Reason");
		AssertStringEquals(CP2PatDetMoreReason, SORReason, "Theatre details - Reason");

		jsclick(ThtrPg.closeicon, "Close Pat details");
		click(mnuPg.clickMenuNew, "Menu");
		click(mnuPg.clickTheatres, "Theatres");
		click(driver.findElementByXPath("//ion-label[contains(text(),'"+SORExtraDocFullname+"')]"), "Intended Care Provider");
		Thread.sleep(10000);

		explicitWait(ThtrPg.enterNamesearch);
		enterText(ThtrPg.enterNamesearch, SORPatFullName, "Name Search");
		Thread.sleep(15000);
		String  CP3BookedPatientDoB= getText(driver.findElementByXPath("//*[contains(text(),'"+SORPatFullName+"')]/ancestor::ion-row//span[contains(text(),'DoB')]/parent::span"), "Theatres Booking Search - Patient DOB");
		AssertStringEquals(CP3BookedPatientDoB, ("DoB "+SORPatientDOB), "Patient DOB in Search list");

		String CP3BookedPatientHRN = getText(driver.findElementByXPath("//*[contains(text(),'"+SORPatFullName+"')]/ancestor::ion-row//span[contains(text(),'HRN')]/parent::span"), "Theatres Booking Search - Patient HRN");
		AssertStringEquals(CP3BookedPatientHRN, ("HRN "+SORHRN), "Patient HRN in Search list");

		String CP3BookedTheatreName = getText(driver.findElementByXPath("//*[contains(text(),'"+SORPatFullName+"')]/ancestor::ion-row//*[contains(text(),'HRN')]/parent::span/parent::ion-col/parent::ion-row/following-sibling::ion-row//span"), "Theatres Booking Search - Booked Theatre name");
		AssertStringEquals(CP3BookedTheatreName.trim().toUpperCase(), SOROperationTheatre.trim().toUpperCase(), "Theatre Name in Search list");

		String CP3BookingTime = getText(driver.findElementByXPath("//*[contains(text(),'"+SORPatFullName+"')]/ancestor::ion-item//*[contains(@class,'timeBatch')]"), "Theatres Booking Search - Booking Time");
		//AssertStringContains(SORTheatreBookingDateTime, CP3BookingTime, "Theatre Booking Time in Search list");

		jsclick(ThtrPg.PatDetMoreIcon, "More Icon");
		Thread.sleep(3000);


		String CP3PatDetMorePatName = getText(ThtrPg.PatDetMorePatName, "Patient details - Patient Name");
		AssertStringContains(CP3PatDetMorePatName.toUpperCase(), SORPatFullName.toUpperCase(), "Patient details - Patient Name");

		String CP3PatDetMorePatDOB = getText(ThtrPg.PatDetMorePatDOB, "Patient details - Patient DOB");
		AssertStringEquals(CP3PatDetMorePatDOB, SORPatientDOB, "Patient details - Patient DOB");

		String CP3PatDetMorePatGender = getText(ThtrPg.PatDetMorePatGender, "Patient details - Patient Gender");
		AssertStringEquals(CP3PatDetMorePatGender, SORPatientGender, "Patient details - Patient Gender");

		String CP3PatDetMorePatHRN = getText(ThtrPg.PatDetMorePatHRN, "Patient details - Patient HRN");
		AssertStringEquals(CP3PatDetMorePatHRN, SORHRN, "Patient details - Patient HRN");

		String CP3PatDetMoreSurgeon = getText(ThtrPg.PatDetMoreSurgeon, "Theatre details - Surgeon");
		AssertStringEquals(CP3PatDetMoreSurgeon, SORSurgeon, "Theatre details - Surgeon");

		String CP3PatDetMoreAneasthetist = getText(ThtrPg.PatDetMoreAneasthetist, "Theatre details - Anaesthetist");
		AssertStringEquals(CP3PatDetMoreAneasthetist, SORAnaesthetist, "Theatre details - Anaesthetist");

		String CP3PatDetMoreOperationUnit = getText(ThtrPg.PatDetMoreOperationUnit, "Theatre details - Operating Unit");
		AssertStringEquals(CP3PatDetMoreOperationUnit, SOROperatingUnit, "Theatre details - Operating Unit");

		String CP3PatDetMoreOperationTheatre = getText(ThtrPg.PatDetMoreOperationTheatre, "Theatre details - Theatre");
		AssertStringEquals(CP3PatDetMoreOperationTheatre.trim().toUpperCase(), SOROperationTheatre.trim().toUpperCase(), "Theatre details - Theatre");

		String CP3PatDetMoreOperationDate = getText(ThtrPg.PatDetMoreOperationDate, "Theatre details - Operation Date");
		AssertStringContains(CP3PatDetMoreOperationDate, SORTheatreBookingDate, "Theatre details - Operation Date");

		String CP3PatDetMoreOperationDuration = getText(ThtrPg.PatDetMoreOperationDuration, "Theatre details - Duration");
		AssertStringEquals(CP3PatDetMoreOperationDuration, (SORDuration+" minute(s)"), "Theatre details - Duration");

		String CP3PatDetMoreAnestheticType = getText(ThtrPg.PatDetMoreAnestheticType, "Theatre details - Anasethetic type");
		AssertStringEquals(CP3PatDetMoreAnestheticType, SORAnaestheticType, "Theatre details -Anasethetic type");

		String CP3PatDetMoreMBSCodes = getText(ThtrPg.PatDetMoreMBSCodes, "Theatre details - MBS Codes");
		AssertStringEquals(CP3PatDetMoreMBSCodes, SORMBSCodes, "Theatre details - MBS Codes");

		String CP3PatDetMoreReason = getText(ThtrPg.PatDetMoreReason, "Theatre details - Reason");
		AssertStringEquals(CP3PatDetMoreReason, SORReason, "Theatre details - Reason");

		jsclick(ThtrPg.closeicon, "Close Pat details");
		click(mnuPg.clickMenu, "Menu");
		click(mnuPg.clickMyWard,"My Ward");
		click(mnuPg.AddWard,"Add Ward");
		SearchNMarkFavourite("Ward",SORIPWard);
		click(mnuPg.clickMenu,"Menu");
		click(mnuPg.clickMyWard,"My Wards");
		SelectFavourite("My Wards",SORIPWard);
		Thread.sleep(15000);
		SearchPatientByName(SORPatFullName);
		VerifyPatientDetails_WardSearchListWebPAS(SORPatFullName,SORPatientDOB,SORHRN,SORIPBed,SORIPWard, SORIPSite);
		VerifyPatientDetails_WardSearchListMoreWebPAS(SORPatientTitle,SORPatFullName, SORPatientDOB ,SORHRN, SORPatientGender, SORCareProvider ,SORIPSpecialty ,SORIPSite ,SORIPWard ,SORIPBed ,SORIPEncID, SORIPAdmittedDate,"Admit");
		
			reportStep("Script completed", "INFO");
			driver.quit();
			
			
		}
	}
