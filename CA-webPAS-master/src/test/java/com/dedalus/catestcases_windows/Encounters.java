package com.dedalus.catestcases_windows;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dedalus.capages_windows.MenuPage;
import com.dedalus.capages_windows.MyWorklistPage;
import com.dedalus.capages_windows.SummaryPage;
import com.dedalus.capages_windows.EncountersPage;
import com.dedalus.restasssured_windows.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Encounters extends  RESTAssuredBase {
	
	Response response;
	JsonPath jsonPath;

	@Parameters({"SOR"})
	@BeforeTest
	public void setData(String SOR) throws IOException, InterruptedException, AWTException {
		testCaseName = "Encounters - "+SOR+" - Windows "; 
		testDescription = "The following test case verify Encounters and My worklist overview";
		nodes = SOR;
		category = "Regression";
		authors = "Karthi";
	}



	@Parameters({"SOR"})

	@Test
	public void create(String SOR, ITestContext context)
			throws InterruptedException, IOException, ClassNotFoundException, SQLException, ParseException, AWTException {

		ISuite suite = context.getSuite();
		int intJobID = (int) suite.getAttribute("JobID");
		int suiteID = (int) suite.getAttribute("SuiteID");


		String accessToken = "Bearer 72db341d-cbdb-4d6d-b521-df59d5b2a571";
		HashMap<String, String> alertHeaderMap = getHeaderMap(accessToken);		  
		response = getWithHeader(alertHeaderMap, "https://api.virtuoso.qa/api/testsuites/execution?suiteId="+suiteID+"&jobId="+intJobID+"&includeJourneyDetails=true");


		String SORPatAdd, SORRecordedBy = "";
		String SORConfigURL =  getExposedValue(response, "CP", "GV_ATCA_ConfigURL", "SORConfigURL");
		String SORUserSurname =  getExposedValue(response, "LCP", "LV_strUserSurname", "SORUserSurname");
		String SORUserForename =  getExposedValue(response, "LCP", "LV_strUserForename", "SORUserForename");
		String SORUserTitle =  getExposedValue(response, "LCP", "LV_strUserTitle", "SORUserTitle");
		String SORUserSplty =  getExposedValue(response, "LCP", "LV_strUserSpecialty", "SORUserSplty");
		if(SORUserTitle.equals("Not Known")) {
			SORRecordedBy = SORUserForename+" "+SORUserSurname;
		}else {
			SORRecordedBy = SORUserTitle+" "+SORUserForename+" "+SORUserSurname;
		}

		String SORPatientSurname =  getExposedValue(response, "LCP", "TD_strSurname", "SORPatientSurname");
		String SORPatientForename =  getExposedValue(response, "LCP", "TD_strForename", "SORPatientForename");
		String SORPASID =  getExposedValue(response, "CP", "LV_strPasID", "SORPASID");
		String SORPatientDOB =  getExposedValue(response, "CP", "TD_dtmDOB", "SORPatientDOB");
		String SORPatientGender = getExposedValue(response, "LCP", "TD_strGender", "SORPatientGender");
		String SORNHSNumber =  getExposedValue(response, "LCP", "LV_strNHSNumber", "SORNHSNumber");
		SORNHSNumber = SORNHSNumber.replaceAll("-", " ");
		String SORPremisesName =  getExposedValue(response, "LCP", "LV_strPremisesName", "SORPremisesName");
		String SORHouseNo = getExposedValue(response, "LCP", "LV_strHseNoStName", "SORHouseNo");
		String SORPatCity =  getExposedValue(response, "LCP", "LV_strCity", "SORPatCity");
		String SORPostCode =  getExposedValue(response, "LCP", "TD_strPostCode", "SORPostCose");
		String SORPatCountry =  getExposedValue(response, "LCP", "LV_strCountry", "SORPatCountry");
		if(SORPremisesName.equals("")) {
			SORPatAdd = SORHouseNo+", "+SORPatCity+" "+SORPostCode+" "+SORPatCountry;
		}else {
			SORPatAdd = SORPremisesName+", "+SORHouseNo+", "+SORPatCity+" "+SORPostCode+" "+SORPatCountry;
		}
		String SORPatientTitle = "";
		if(SOR.equals("WEBPAS"))
		{
			SORPatientSurname = getExposedValue(response,"LCP","TD_strSurname","TD_strSurname");
			SORPatientForename = getExposedValue(response,"LCP","TD_strForename","TD_strForename");
			SORPatientGender = getExposedValue(response,"LCP","TD_strGender","TD_strGender");
			SORPatientTitle = getExposedValue(response,"LCP","TD_strPatientTitle","TD_strPatientTitle");
			SORPatientDOB = getExposedValue(response,"LCP","TD_dtmDOB","TD_dtmDOB");
			String SORMartialStatus = getExposedValue(response,"LCP","LV_strMaritalStatus","LV_strMaritalStatus");
			String SORResident = getExposedValue(response,"LCP","LV_strResident","LV_strResident");
			String SORReligion = getExposedValue(response,"LCP","LV_strReligion","LV_strReligion");
			String SORIndiStatus = getExposedValue(response,"LCP","LV_strIndigenousStatus","LV_strIndigenousStatus");
			String SORCOB = getExposedValue(response,"LCP","LV_strCountryOfBirth","LV_strCountryOfBirth");
			String SORHRN = getExposedValue(response,"LCP","LV_HRN","LV_HRN");
			SORPASID = SORHRN;
		}



		String SORIPWard =  getExposedValue(response, "CP", "GV_IP_ATCAWard", "SORIPWard");
		String SORIPFloor =  getExposedValue(response, "CP", "GV_IP_ATCAFloor", "SORIPFloor");
		String SORIPBuilding = getExposedValue(response, "CP", "GV_IP_ATCABuilding", "SORIPBuilding");

		String SORIPEncID = getExposedValue(response, "CP", "LV_IP_strEncID", "SORIPEncID ");
		String SORIPAdmittedDate = getExposedValue(response, "CP", "LV_IP_dtmStartDate", "SORIPAdmittedDate ");
		if(SOR.equals("WEBPAS"))
		{
			SORIPWard = getExposedValue(response, "CP", "GV_IP_Ward", "SORIPWard");
		}
		String SORIPDischargeDate = getExposedValue(response, "CP", "LV_IP_dtmActualDiscrgDT", "SORIPDischargeDate ");

		String SORIPSeen1type = getExposedValue(response, "CP", "LV_IP_1st_SeenType", "SORIPSeen1type ");
		String SORIPSeen1StartDate = getExposedValue(response, "CP", "LV_IP_1st_dtmSeenStartDT", "SORIPSeen1StartDate ");
		String SORIPSeen1EndDate = getExposedValue(response, "CP", "LV_IP_1st_dtmSeenEndDT", "SORIPSeen1EndDate ");
		String SORIPSeen1Comments = getExposedValue(response, "CP", "LV_IP_1st_SeenComments", "SORIPSeen1Comments ");

		String SORUser2Surname =  getExposedValue(response, "CP", "LV_strUser2Surname", "SORUser2Surname");
		String SORUser2Forename =  getExposedValue(response, "CP", "LV_strUser2ForeName", "SORUser2Forename");

		String SORIPSeen2type = getExposedValue(response, "CP", "LV_IP_2nd_SeenType", "SORIPSeen2type ");
		String SORIPSeen2StartDate = getExposedValue(response, "CP", "LV_IP_2nd_dtmSeenStartDT", "SORIPSeen2StartDate ");
		String SORIPSeen2EndDate = getExposedValue(response, "CP", "LV_IP_2nd_dtmSeenEndDT", "SORIPSeen2EndDate ");
		String SORIPSeen2Comments = getExposedValue(response, "CP", "LV_IP_2nd_SeenComments", "SORIPSeen2Comments ");

		String SOREDEncID = getExposedValue(response, "CP", "LV_ED_strEncID", "SOREDEncID ");
		String SOREDAttendedDate = getExposedValue(response, "CP", "LV_ED_dtmStartDate", "SOREDAttendedDate");
		String SORContactEncID = getExposedValue(response, "CP", "LV_Contact_strEncID", "SORContactEncID");
		String SORContactStartDate = getExposedValue(response, "CP", "LV_Contact_dtmStartDate", "SORContactStartDate");
		String SOROPEncID = getExposedValue(response, "CP", "LV_OP_strEncID", "SOROPEncID");
		String SOROPStartDate = getExposedValue(response, "CP", "LV_OP_dtmStartDate", "SOROPStartDate");
		String SOROPCareProvider = getExposedValue(response, "CP", "LV_OP_strCareProvider", "SOROPCareProvider");
		String SOROPSpecialty = getExposedValue(response, "CP", "LV_OP_strSpecialty", "SOROPSpecialty");

//================================================TESTCASE START============================================================//
		
		startApp(SOR,SORConfigURL);
		Thread.sleep(8000);
		MenuPage mnuPg = new MenuPage(driver, test);
		EncountersPage enc = new EncountersPage(driver, test);
		SummaryPage summPg = new SummaryPage(driver, test);
		MyWorklistPage worklistPg = new MyWorklistPage(driver, test);

		String SORPatFullName = SORPatientSurname.toUpperCase() +", " + SORPatientForename;
		click(mnuPg.clickMenu, "Menu");
		click(mnuPg.menu_Search,"Search");
		Thread.sleep(3000);

		SearchNSelectPatient(SORPASID);
		Thread.sleep(3000);

		click(mnuPg.selectEnc,"Encounter");
		Thread.sleep(3000); 
		
		if(SOR.equals("WEBPAS"))
		{
			String SORCareProvider = "Snell , James (Dr)";
			String SORIPSpecialty = "Cardiac Services";
			String SOREDSite = "Boonah Hospital";
			String SOREDRoom = "Waiting Room";
			String SORIPSite = "St Andrew's Hospital Toowoomba";
			SORPatFullName = SORPatientSurname.toUpperCase() +", " + SORPatientForename+ " ("+SORPatientTitle+")";
			if(SORPatientGender.equals("Transgender"))
			{
				SORPatientGender = "Other";
			}
			
			click(enc.selectEDRec, "Expand Emergency Encounter record");
			Thread.sleep(3000);
			String EDEncID = getText(enc.EDEncID, "Emergency encounter ID");
			AssertStringEquals(EDEncID, SOREDEncID, "Emergency encounter ID");
			
			String EDEncBadge = getText(enc.EDEncBadge, "Emergency encounter Status");
			AssertStringEquals(EDEncBadge, "Arrived", "Emergency encounter Status");
					
			String EDEncAttndedDate = getText(enc.EDEncAttendedDate, "Emergency encounter Attended date");
			AssertStringEquals(EDEncAttndedDate, SOREDAttendedDate, "Emergency encounter Attended date");
			
			String EDEncCareProvider = getText(enc.EDEncEncCareProviderWP, "Emergency encounter Care provider");
			AssertStringContains(EDEncCareProvider, "No participant", "Emergency encounter Care provider");
			
			click(enc.futureFauxtab, "Future faux tab");
			Thread.sleep(5000);
			
			String IPEncID = getText(enc.ipPreAdmitEncID, "Inpatient encounter ID");
			AssertStringEquals(IPEncID, SORIPEncID, "Inpatient encounter ID");
			
			String IPEncBadge = getText(enc.IPPreAdmitEncBadge, "Inpatient encounter Status");
			AssertStringEquals(IPEncBadge, "Planned", "Inpatient encounter Status");
			
			String IPEncPlannedDate = getText(enc.ipPreAdmitEncPlannedDate, "Inpatient encounter Admitted date");
			AssertStringEquals(IPEncPlannedDate, SORIPAdmittedDate, "Inpatient encounter Planned date");
			
			String IPEncCareProvider = getText(enc.ipPreAdmitEncCareProvider, "Inpatient encounter Care provider");		
			AssertStringContains(IPEncCareProvider, "Dr James Snell", "Inpatient encounter Care provider");
			
			String IPEncSpecialty = getText(enc.ipPreAdmitEncSpecialty, "Inpatient encounter Specialty");		
			AssertStringContains(SORIPSpecialty, IPEncSpecialty, "Inpatient encounter Specialty");

			explicitWait(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORPASID+"\"]//h2"));
			jsclick(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORPASID+"\"]//h2/ancestor::ion-row/following-sibling::ion-row//ion-icon[@aria-label='star outline']"), "FavIcon");
			Thread.sleep(30000);
			takeSnap();
			explicitWait(driver.findElementByXPath("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Select Encounter for')]"));
			
			String encListEDEncCareProvider = getText(enc.encListEDEncCareProviderWP, "EncounterList Emergency encounter Care provider");
			AssertStringEquals(encListEDEncCareProvider, "No participant", "encListEDEncCareProvider");
			
			String encListencListEDEncID = getText(enc.encListEDEncID, "EncounterList Emergency encounter ID");
			AssertStringEquals(encListencListEDEncID, EDEncID, "encListencListEDEncID");
			
			String encListEDEncStatus = getText(enc.encListEDEncStatus, "EncounterList Emergency encounter Status");
			AssertStringEquals(encListEDEncStatus, EDEncBadge, "encListEDEncStatus");
			
			String encListEDEncStartDate = getText(enc.encListEDEncStartDate, "EncounterList Emergency encounter Start date");
			AssertStringEquals(encListEDEncStatus, EDEncBadge, "encListEDEncStatus");
			
			String encListEDEncSpecialty = getText(enc.encListEDEncSpecialty, "EncounterList Emergency encounter Specialty");
			AssertStringEquals(encListEDEncSpecialty, "", "encListEDEncSpecialty"); // Need to check in WebPAS for CP and Specialty
			
			
			String encListencListIPEncID = getText(enc.encListIPEncID, "EncounterList Inpatient encounter ID");
			AssertStringEquals(encListencListIPEncID, IPEncID, "encListencListIPEncID");
			
			String encListIPEncStatus = getText(enc.encListIPEncStatus, "EncounterList Inpatient encounter Status");
			AssertStringEquals(encListIPEncStatus, IPEncBadge, "encListIPEncStatus");
			
			String encListIPEncPlannedDate = getText(enc.encListIPEncStartDate, "EncounterList Inpatient encounter Planned date");
			AssertStringContains(IPEncPlannedDate, encListIPEncPlannedDate, "EncounterList Inpatient encounter Planned date");
			
			String encListIPEncSpecialty = getText(enc.encListIPEncSpecialty, "EncounterList Inpatient encounter Specialty");
			AssertStringContains(encListIPEncSpecialty, IPEncSpecialty, "Inpatient encounter Specialty");
			
			String encListIPEncCareProvider = getText(enc.encListIPEncCareProvider, "EncounterList Inpatient encounter Care provider");
			AssertStringContains("Snell , James (Dr)", encListIPEncCareProvider, "Inpatient encounter Care provider");
			
			jsclick(enc.encListEDFavIcon, "ED Enc Favourite icon");
			Thread.sleep(3000);
			jsclick(driver.findElementByXPath("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Select Encounter for')]/following-sibling::ion-col/ion-icon[@aria-label='close']"),"Close Enc list icon");

			Thread.sleep(3000);
			explicitWait(enc.clickMyWorklistFavIcon);
			jsclick(enc.clickMyWorklistFavIcon, "My worklist overview");
			Thread.sleep(3000);
			
			jsclick(worklistPg.MyWorkListInpatientTab, "Switch from My worklist overview IP tab");
			Thread.sleep(1000);
			jsclick(worklistPg.MyWorkListEmergencyTab, "My worklist overview ED tab");
			Thread.sleep(3000);
			String MyWL_ED_EncounterCount = getText(worklistPg.MyWorkListEncCount, "My worklist ED Enc count");
			if( MyWL_ED_EncounterCount.equals("1")) {
				reportStep("No. of ED encounters equals 1", "PASS", false);
				System.out.println("No. of ED encounters equals 1");
			}
			else {
				reportStep("No. of ED encounters not equals 1", "FAIL", false);
				System.out.println("No. of ED encounters not equals 1");
			}
			
			String MyWL_ED_PatName = getText(worklistPg.MyWorkListPatName, "My worklist ED Enc Pat Name");
			AssertStringEquals(MyWL_ED_PatName.toUpperCase(), SORPatFullName.toUpperCase(), "MyWL_ED_PatName");
			
			String MyWL_ED_PatHRNNumber = getText(worklistPg.MyWorkListPatHRN, "My worklist ED Enc Pat HRN Number");
			AssertStringContains(MyWL_ED_PatHRNNumber, SORPASID, "MyWL_ED_PatHRNNumber");
			
			String MyWL_ED_PatDOB = getText(worklistPg.MyWorkListPatDOBWP, "My worklist ED Enc Pat DOB");
			AssertStringContains(MyWL_ED_PatDOB, SORPatientDOB, "MyWL_ED_PatDOB");
			
			String MyWL_ED_Site = getText(worklistPg.MyWorkListEncSite, "My worklist OP Enc Site");
			AssertStringContains(MyWL_ED_Site, SOREDSite, "MyWL_ED_Site");
			
			String MyWL_ED_Room = getText(worklistPg.MyWorkListEncRoom, "My worklist OP Enc Room");
			AssertStringContains(MyWL_ED_Room, SOREDRoom, "MyWL_ED_Room");
			
			jsclick(worklistPg.MyWorkListMorePatDetailsIcon, "My worklist overview More patient details");
			Thread.sleep(3000);
			
			String MyWL_ED_Patdet_PatGender = getText(worklistPg.MyWorkListMorePatGender, "My worklist ED Enc More details Pat Gender");
			AssertStringEquals(MyWL_ED_Patdet_PatGender, SORPatientGender, "MyWL_ED_Patdet_PatGender");
			
			String MyWL_ED_Patdet_EncLocation = getText(worklistPg.MyWorkListMoreEncLocation, "My worklist ED Enc More details  Location");
			AssertStringEquals(MyWL_ED_Patdet_EncLocation, (MyWL_ED_Room +", "+MyWL_ED_Site), "MyWL_ED_Patdet_EncLocation");
			
			String MyWL_ED_Patdet_PatHRN = getText(worklistPg.MyWorkListMorePatHRN, "My worklist ED Enc More details HRN");
			AssertStringEquals(MyWL_ED_Patdet_PatHRN, SORPASID, "MyWL_ED_Patdet_PatHRN");

//			String MyWL_ED_Patdet_CPName = getText(worklistPg.MyWorkListMoreEncCareprovider, "My worklist ED Enc More details  CP Name");
//			AssertStringContains(MyWL_ED_Patdet_CPName, "No participant", "MyWL_ED_Patdet_CPName");
					
			String MyWL_ED_Patdet_PatDOB = getText(worklistPg.MyWorkListMorePatDOBWP, "My worklist ED Enc More details  Pat DOB");
			AssertStringContains(MyWL_ED_Patdet_PatDOB, SORPatientDOB, "MyWL_ED_Patdet_PatDOB");
						
			String MyWL_ED_Patdet_EncID =  getText(worklistPg.MyWorkListMoreEncID, "My worklist ED Enc More details Enc ID");
			AssertStringContains(MyWL_ED_Patdet_EncID, EDEncID, "MyWL_ED_Patdet_EncID");
			
			String MyWL_ED_Patdet_EncRoom =  getText(worklistPg.MyWorkListMoreEncRoom, "My worklist ED Enc More details Enc Room");
			AssertStringEquals(MyWL_ED_Patdet_EncRoom, MyWL_ED_Room , "MyWL_ED_Patdet_EncRoom");
			
			String MyWL_ED_Patdet_AttendedDate =  getText(worklistPg.MyWorkListMoreEncPlannedDate, "My worklist ED Enc More details Enc start date");
			AssertStringEquals(MyWL_ED_Patdet_AttendedDate, SOREDAttendedDate, "MyWL_ED_Patdet_AttendedDate");
			
			jsclick(worklistPg.MyWorkListClosePatDetailsIcon, "My worklist overview close More patient details");
			Thread.sleep(3000);
			
			jsclick(worklistPg.MyWorkListMarkUnfavouriteIcon, "My worklist overview unfavourite");
			Thread.sleep(3000);
			
			jsclick(worklistPg.MyWorkListEmergencyTab, "Switch from My worklist overview ED tab");
			Thread.sleep(1000);
			jsclick(worklistPg.MyWorkListInpatientTab, "My worklist overview ED tab");
			Thread.sleep(1000);
			jsclick(worklistPg.MyWorkListInpatientTab, "Switch from My worklist overview IP tab");
			Thread.sleep(1000);
			jsclick(worklistPg.MyWorkListEmergencyTab, "My worklist overview ED tab");
			Thread.sleep(1000);
			
			//Verify Not exist
		
			click(mnuPg.clickMenu, "Menu");
			click(mnuPg.menu_Search,"Search");
			Thread.sleep(3000);

			SearchNSelectPatient(SORPASID);
			Thread.sleep(3000);
			explicitWait(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORPASID+"\"]//h2"));
			jsclick(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORPASID+"\"]//h2/ancestor::ion-row/following-sibling::ion-row//ion-icon[@aria-label='star outline']"), "FavIcon");
			Thread.sleep(10000);
			takeSnap();
			explicitWait(driver.findElementByXPath("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Select Encounter for')]"));

			jsclick(enc.encListIPFavIcon, "IP Enc Favourite icon");
			Thread.sleep(3000);
			jsclick(driver.findElementByXPath("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Select Encounter for')]/following-sibling::ion-col/ion-icon[@aria-label='close']"),"Close Enc list icon");

			Thread.sleep(3000);
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
			AssertStringContains(MyWL_IP_PatHRN, SORPASID, "MyWL_IP_PatHRN");

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
			AssertStringEquals(MyWL_IP_Patdet_PatHRN, SORPASID, "MyWL_IP_Patdet_PatHRN");

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
			
			//Verify NOt Exist
						


			

		}
/*		else if(SOR.equals("LORENZO"))
		{

			String SOROPCareProviderSurname = SOROPCareProvider.split(" ")[0];
			String SOROPCareProviderForename = SOROPCareProvider.split(" ")[1];
			
			//Inpatient Encounter
			String IPEncID = getText(enc.ipEncID, "Inpatient encounter ID");
			String IPEncBadge = getText(enc.IPEncBadge, "Inpatient encounter Status");
			String IPEncAdmittedDate = getText(enc.ipEncAdmittedDate, "Inpatient encounter Admitted date");
			String IPEncDischargeDate = getText(enc.ipEncDischargeDate, "Inpatient encounter Discharge date");
			String IPEncCareProvider = getText(enc.ipEncCareProvider, "Inpatient encounter Care provider");

			AssertStringEquals(IPEncID, SORIPEncID, "Inpatient encounter ID");
			AssertStringEquals(IPEncBadge, "Finished", "Inpatient encounter Status");
			AssertStringEquals(IPEncAdmittedDate, SORIPAdmittedDate, "Inpatient encounter Admitted date");
			AssertStringEquals(IPEncDischargeDate, SORIPDischargeDate, "Inpatient encounter Discharge date");
			AssertStringContains(IPEncCareProvider, SORUserForename, "Inpatient encounter Care provider");

			jsclick(enc.expandIPEncDetails,"Expand IP enc Encounter");
			Thread.sleep(3000);

			String IPEncSeen1SeenBy = getText(enc.getIPEncSeen1SeenBy, "Seen 1 Seen By");
			String IPEncSeen1Type = getText(enc.getIPEncSeen1SeenType, "Seen 1 Type");
			String IPEncSeen1StartDate = getText(enc.getIPEncSeen1SeenStartDate, "Seen 1 Start date");
			String IPEncSeen1EndDate = getText(enc.getIPEncSeen1SeenEndDate, "Seen 1 End date");
			String IPEncSeen1Comments = getText(enc.getIPEncSeen1Comments, "Seen 1 Comments");

			String IPEncSeen2SeenBy = getText(enc.getIPEncSeen2SeenBy, "Seen 2 Seen By");
			String IPEncSeen2Type = getText(enc.getIPEncSeen2SeenType, "Seen 2 Type");
			String IPEncSeen2StartDate = getText(enc.getIPEncSeen2SeenStartDate, "Seen 2 Start date");
			String IPEncSeen2EndDate = getText(enc.getIPEncSeen2SeenEndDate, "Seen 2 End date");
			String IPEncSeen2Comments = getText(enc.getIPEncSeen2Comments, "Seen 2 Comments");

			AssertStringContains(IPEncSeen1SeenBy, SORUserForename, "Seen 1 Seen By");
			AssertStringEquals(SORIPSeen1type, IPEncSeen1Type, "Seen 1 Type");
			AssertStringEquals(SORIPSeen1StartDate, IPEncSeen1StartDate, "Seen 1 Start date");
			AssertStringEquals(SORIPSeen1EndDate, IPEncSeen1EndDate, "Seen 1 End date");
			AssertStringEquals(SORIPSeen1Comments, IPEncSeen1Comments, "Seen 1 Comments");

			AssertStringContains(IPEncSeen2SeenBy, SORUser2Forename, "Seen 2 Seen By");
			AssertStringEquals(SORIPSeen2type, IPEncSeen2Type, "Seen 2 Type");
			AssertStringEquals(SORIPSeen2StartDate, IPEncSeen2StartDate, "Seen 2 Start date");
			AssertStringEquals(SORIPSeen2EndDate, IPEncSeen2EndDate, "Seen 2 End date");
			AssertStringEquals(SORIPSeen2Comments, IPEncSeen2Comments, "Seen 2 Comments");		

			//			Emergency Encounter	
			click(enc.selectEDRec, "Expand Emergency Encounter record");
			Thread.sleep(3000);
			String EDEncID = getText(enc.EDEncID, "Emergency encounter ID");
			String EDEncBadge = getText(enc.EDEncBadge, "Emergency encounter Status");
			String EDEncAttndedDate = getText(enc.EDEncAttendedDate, "Emergency encounter Attended date");
			String EDEncCareProvider = getText(enc.EDEncEncCareProvider, "Emergency encounter Care provider");

			AssertStringEquals(EDEncID, SOREDEncID, "Emergency encounter ID");
			AssertStringEquals(EDEncBadge, "Arrived", "Emergency encounter Status");
			AssertStringEquals(EDEncAttndedDate, SOREDAttendedDate, "Emergency encounter Attended date");
			AssertStringContains(EDEncCareProvider, SORUserForename, "Emergency encounter Care provider");

			//			Outpatient Encounter
			click(enc.futureFauxtab, "Future faux tab");
			Thread.sleep(3000);
			click(enc.selectOPRec, "Expand Outpatient Encounter record");
			Thread.sleep(3000);
			String OPEncID = getText(enc.OPEncID, "Outpatient encounter ID");
			String OPEncBadge = getText(enc.OPEncBadge, "Outpatient encounter Status");
			String OPEncStartDate = getText(enc.OPEncStartDate, "Outpatient encounter Start date");
			String OPEncCareProvider = getText(enc.OPEncEncCareProvider, "Outpatient encounter Care provider");
			String OPEncSpecialty = getText(enc.OPEncSpecialty, "Outpatient encounter Specialty");

			AssertStringEquals(OPEncID, SOROPEncID, "OP encounter ID");
			AssertStringEquals(OPEncBadge, "Planned", "OP encounter Status");
			AssertStringEquals(OPEncStartDate, SOROPStartDate, "OP encounter Attended date");
			AssertStringContains(OPEncCareProvider, SOROPCareProviderForename, "OP encounter Care provider");
			AssertStringEquals(OPEncSpecialty.toLowerCase(), SOROPSpecialty.toLowerCase(), "OP encounter Specialty");


			//			Contact Encounter
			click(enc.selectContactRec, "Expand Contact Encounter record");
			Thread.sleep(3000);
			String ContactEncID = getText(enc.ContactEncID, "Contact encounter ID");
			String ContactEncBadge = getText(enc.ContactEncBadge, "Contact encounter Status");
			String ContactEncStartDate = getText(enc.ContactEncStartDate, "Contact encounter Start date");
			String ContactEncCareProvider = getText(enc.ContactEncCareProvider, "Contact encounter Care provider");
			String ContactEncSpecialty = getText(enc.ContactEncSpecialty, "Contact encounter Specialty");

			AssertStringEquals(ContactEncID, SORContactEncID, "Contact encounter ID");
			AssertStringEquals(ContactEncBadge, "Planned", "Contact encounter Status");
			AssertStringEquals(ContactEncStartDate, SORContactStartDate, "Contact encounter Attended date");
			AssertStringContains(ContactEncCareProvider, SORUserForename, "Contact encounter Care provider");
			AssertStringEquals(ContactEncSpecialty.toLowerCase(), SORUserSplty.toLowerCase(), "OP encounter Specialty");


			explicitWait(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORPASID+"\"]//h2"));
			jsclick(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORPASID+"\"]//h2/ancestor::ion-row/following-sibling::ion-row//ion-icon[@aria-label='star outline']"), "FavIcon");
			Thread.sleep(30000);
			takeSnap();
			explicitWait(driver.findElementByXPath("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Select Encounter for')]"));

			String encListencListEDEncID = getText(enc.encListEDEncID, "EncounterList Emergency encounter ID");
			String encListEDEncStatus = getText(enc.encListEDEncStatus, "EncounterList Emergency encounter Status");
			String encListEDEncStartDate = getText(enc.encListEDEncStartDate, "EncounterList Emergency encounter Start date");
			String encListEDEncEndDate = getText(enc.encListEDEncEndDate, "EncounterList Emergency encounter End date");
			String encListEDEncSpecialty = getText(enc.encListEDEncSpecialty, "EncounterList Emergency encounter Specialty");
			String encListEDEncCareProvider = getText(enc.encListEDEncCareProvider, "EncounterList Emergency encounter Care provider");

			String encListOPEncID = getText(enc.encListOPEncID, "EncounterList Outpatient encounter ID");
			String encListOPEncStatus = getText(enc.encListOPEncStatus, "EncounterList Outpatient encounter Status");
			String encListOPEncStartDate = getText(enc.encListOPEncStartDate, "EncounterList Outpatient encounter Start date");
			String encListOPEncEndDate = getText(enc.encListOPEncEndDate, "EncounterList Outpatient encounter End date");
			String encListOPEncCareProvider = getText(enc.encListOPEncCareProvider, "EncounterList Outpatient encounter Care provider");
			String encListOPEncSpecialty = getText(enc.encListOPEncSpecialty, "EncounterList Outpatient encounter Specialty");

			String encListContactEncID = getText(enc.encListContactEncID, "EncounterList Contact encounter ID");
			String encListContactEncStatus = getText(enc.encListContactEncStatus, "EncounterList Contact encounter Status");
			String encListContactEncStartDate = getText(enc.encListContactEncStartDate, "EncounterList Contact encounter Start date");
			String encListContactEncEndDate = getText(enc.encListContactEncEndDate, "EncounterList Contact encounter End date");
			String encListContactEncCareProvider = getText(enc.encListContactEncCareProvider, "EncounterList Contact encounter Care provider");
			String encListContactEncSpecialty = getText(enc.encListContactEncSpecialty, "EncounterList Contact encounter Specialty");


			AssertStringEquals(encListencListEDEncID , SOREDEncID, "Emergency encounter ID");
			AssertStringEquals(encListEDEncStatus, EDEncBadge, "Emergency encounter Status");
			AssertStringContains( SOREDAttendedDate, EDEncAttndedDate,"Emergency encounter Attended date");
			AssertStringEquals(encListEDEncEndDate , "--", "Emergency encounter EndDate");
			AssertStringEquals(encListencListEDEncID , "","Emergency encounter Specialty");
			AssertStringContains(encListEDEncCareProvider, SORUserForename, "Emergency encounter Care provider");
			 
			AssertStringEquals(OPEncID, encListOPEncID, "OP encounter ID");
			AssertStringEquals(OPEncBadge, encListOPEncStatus, "OP encounter Status");
			AssertStringContains(OPEncStartDate, encListOPEncStartDate, "OP encounter Attended date");
			AssertStringEquals(encListOPEncEndDate , "--", "OP encounter EndDate");
			AssertStringContains(encListOPEncCareProvider, SOROPCareProviderForename, "OP encounter Care provider");
			AssertStringEquals(encListOPEncSpecialty, "", "OP encounter Specialty");
			 
			AssertStringEquals(ContactEncID, encListContactEncID, "Contact encounter ID");
			AssertStringEquals(ContactEncBadge, encListContactEncStatus, "Contact encounter Status");
			AssertStringContains(ContactEncStartDate, encListContactEncStartDate, "Contact encounter Attended date");
			AssertStringEquals(encListContactEncEndDate , "--", "Contact encounter EndDate");
			AssertStringContains(encListContactEncCareProvider, SORUserForename, "Contact encounter Care provider");
			AssertStringEquals(encListContactEncSpecialty, "", "Contact encounter Specialty");
			
			//Favourite Outpatient Enc
			jsclick(enc.encListOPFavIcon, "Outpatient Enc Favourite icon");
			Thread.sleep(3000);
			jsclick(driver.findElementByXPath("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Select Encounter for')]/following-sibling::ion-col/ion-icon[@aria-label='close']"),"Close Enc list icon");

			Thread.sleep(3000);
			explicitWait(enc.clickMyWorklistFavIcon);
			jsclick(enc.clickMyWorklistFavIcon, "My worklist overview");
			Thread.sleep(3000);

			jsclick(worklistPg.MyWorkListInpatientTab, "Switch from My worklist overview IP tab");
			Thread.sleep(1000);
			explicitWait(worklistPg.MyWorkListOutpatientTab);
			jsclick(worklistPg.MyWorkListOutpatientTab, "My worklist overview Outpatient tab");
			Thread.sleep(3000);
			String MyWL_OP_EncounterCount = getText(worklistPg.MyWorkListEncCount, "My worklist OP Enc count");
			String MyWL_OP_PatName = getText(worklistPg.MyWorkListPatName, "My worklist OP Enc Pat Name");
			String MyWL_OP_PatNHSNumber = getText(worklistPg.MyWorkListPatNHS, "My worklist OP Enc Pat NHS Number");
			String MyWL_OP_PatDOB = getText(worklistPg.MyWorkListPatDOB, "My worklist OP Enc Pat DOB");
			String MyWL_OP_Location = getText(worklistPg.MyWorkListOPEncLocation, "My worklist OP Enc Location");

			jsclick(worklistPg.MyWorkListMorePatDetailsIcon, "My worklist overview More patient details");
			Thread.sleep(3000);
			String MyWL_OP_Patdet_PatGender = getText(worklistPg.MyWorkListMorePatGender, "My worklist OP Enc More details Pat Gender");
			String MyWL_OP_Patdet_PatPasID = getText(worklistPg.MyWorkListMorePatPASID, "My worklist OP Enc More details  PASID");
			String MyWL_OP_Patdet_CPName = getText(worklistPg.MyWorkListMoreEncCareprovider, "My worklist OP Enc More details  CP Name");
			String MyWL_OP_Patdet_PatNHSNumber = getText(worklistPg.MyWorkListMorePatNHS, "My worklist OP Enc More details  Pat NHS Number");
			String MyWL_OP_Patdet_PatDOB = getText(worklistPg.MyWorkListMorePatDOB, "My worklist OP Enc More details  Pat DOB");
			String MyWL_OP_Patdet_Session = getText(worklistPg.MyWorkListMoreOPEncSession, "My worklist OP Enc More details Session Name");
			String MyWL_OP_Patdet_Location = getText(worklistPg.MyWorkListMoreOPEncLocation, "My worklist OP Enc More details Session Location");
			
			jsclick(worklistPg.MyWorkListClosePatDetailsIcon, "My worklist overview close More patient details");
			Thread.sleep(3000);

			jsclick(worklistPg.MyWorkListMarkUnfavouriteIcon, "My worklist overview unfavourite");
			Thread.sleep(3000);
			
			jsclick(worklistPg.MyWorkListOutpatientTab, "My worklist overview Outpatient tab");
			Thread.sleep(3000);
			jsclick(worklistPg.MyWorkListInpatientTab, "My worklist overview admitted tab");
			Thread.sleep(3000);
			jsclick(worklistPg.MyWorkListInpatientTab, "My worklist overview admitted tab");
			Thread.sleep(3000);
			jsclick(worklistPg.MyWorkListOutpatientTab, "My worklist overview Outpatient tab");
			Thread.sleep(3000);
			// verify not exixts 
			
			click(mnuPg.clickMenu, "Menu");
			click(mnuPg.menu_Search,"Search");
			Thread.sleep(3000);

			SearchNSelectPatient(SORPASID);
			Thread.sleep(3000);

			explicitWait(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORPASID+"\"]//h2"));
			jsclick(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORPASID+"\"]//h2/ancestor::ion-row/following-sibling::ion-row//ion-icon[@aria-label='star outline']"), "FavIcon");
			Thread.sleep(10000);
			takeSnap();
			explicitWait(driver.findElementByXPath("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Select Encounter for')]"));


			jsclick(enc.encListContactFavIcon, "Contact Enc Favourite icon");
			Thread.sleep(3000);
			jsclick(driver.findElementByXPath("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Select Encounter for')]/following-sibling::ion-col/ion-icon[@aria-label='close']"),"Close Enc list icon");

			Thread.sleep(3000);
			explicitWait(enc.clickMyWorklistFavIcon);
			jsclick(enc.clickMyWorklistFavIcon, "My worklist overview");
			Thread.sleep(3000);

			jsclick(worklistPg.MyWorkListOutpatientTab, "My worklist overview Outpatient tab");
			Thread.sleep(1000);
			explicitWait(worklistPg.MyWorkListContactsTab);
			jsclick(worklistPg.MyWorkListContactsTab, "My worklist overview Contacts tab");
			Thread.sleep(3000);
			String MyWL_Contacts_EncounterCount = getText(worklistPg.MyWorkListEncCount, "My worklist Contacts Enc count");
			String MyWL_Contacts_PatName = getText(worklistPg.MyWorkListPatName, "My worklist Contacts Enc Pat Name");
			String MyWL_Contacts_PatNHSNumber = getText(worklistPg.MyWorkListPatNHS, "My worklist Contacts Enc Pat NHS Number");
			String MyWL_Contacts_PatDOB = getText(worklistPg.MyWorkListPatDOB, "My worklist Contacts Enc Pat DOB");

			jsclick(worklistPg.MyWorkListMorePatDetailsIcon, "My worklist overview More patient details");
			Thread.sleep(3000);
			String MyWL_Contacts_Patdet_PatGender = getText(worklistPg.MyWorkListMorePatGender, "My worklist Contacts Enc More details Pat Gender");
			String MyWL_Contacts_Patdet_EncLocation = getText(worklistPg.MyWorkListMoreEncLocation, "My worklist Contacts Enc More details  Location");
			String MyWL_Contacts_Patdet_PatPasID = getText(worklistPg.MyWorkListMorePatPASID, "My worklist Contacts Enc More details  PASID");
			String MyWL_Contacts_Patdet_CPName = getText(worklistPg.MyWorkListMoreEncCareprovider, "My worklist Contacts Enc More details  CP Name");
			String MyWL_Contacts_Patdet_PatNHSNumber = getText(worklistPg.MyWorkListMorePatNHS, "My worklist Contacts Enc More details  Pat NHS Number");
			String MyWL_Contacts_Patdet_PatDOB = getText(worklistPg.MyWorkListMorePatDOB, "My worklist Contacts Enc More details  Pat DOB");
			String MyWL_Contacts_Patdet_StartDate = getText(worklistPg.MyWorkListMoreEncPlannedDate, "My worklist Contacts Enc More details StartDate");

			jsclick(worklistPg.MyWorkListClosePatDetailsIcon, "My worklist overview close More patient details");
			Thread.sleep(3000);

			jsclick(worklistPg.MyWorkListMarkUnfavouriteIcon, "My worklist overview unfavourite");
			Thread.sleep(3000);
			jsclick(worklistPg.MyWorkListContactsTab, "My worklist overview Contacts tab");
			Thread.sleep(3000);
			jsclick(worklistPg.MyWorkListInpatientTab, "My worklist overview admitted tab");
			Thread.sleep(3000);
			jsclick(worklistPg.MyWorkListInpatientTab, "My worklist overview admitted tab");
			Thread.sleep(3000);
			jsclick(worklistPg.MyWorkListContactsTab, "My worklist overview Contacts tab");
			//Verify not exits 
			
			click(mnuPg.clickMenu, "Menu");
			click(mnuPg.menu_Search,"Search");
			Thread.sleep(3000);

			SearchNSelectPatient(SORPASID);
			Thread.sleep(3000);

			explicitWait(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORPASID+"\"]//h2"));
			jsclick(driver.findElementByXPath("//div[@id=\"dxc-list-item-"+SORPASID+"\"]//h2/ancestor::ion-row/following-sibling::ion-row//ion-icon[@aria-label='star outline']"), "FavIcon");
			Thread.sleep(10000);
			takeSnap();
			explicitWait(driver.findElementByXPath("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Select Encounter for')]"));


			jsclick(enc.encListEDFavIcon, "ED Enc Favourite icon");
			Thread.sleep(3000);
			jsclick(driver.findElementByXPath("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Select Encounter for')]/following-sibling::ion-col/ion-icon[@aria-label='close']"),"Close Enc list icon");

			Thread.sleep(3000);
			explicitWait(enc.clickMyWorklistFavIcon);
			jsclick(enc.clickMyWorklistFavIcon, "My worklist overview");
			Thread.sleep(3000);

			jsclick(worklistPg.MyWorkListContactsTab, "My worklist overview Contacts tab");
			Thread.sleep(1000);
			explicitWait(worklistPg.MyWorkListEmergencyTab);
			jsclick(worklistPg.MyWorkListEmergencyTab, "My worklist overview ED tab");
			Thread.sleep(3000);
			String MyWL_ED_EncounterCount = getText(worklistPg.MyWorkListEncCount, "My worklist ED Enc count");
			String MyWL_ED_PatName = getText(worklistPg.MyWorkListPatName, "My worklist ED Enc Pat Name");
			String MyWL_ED_PatNHSNumber = getText(worklistPg.MyWorkListPatNHS, "My worklist ED Enc Pat NHS Number");
			String MyWL_ED_PatDOB = getText(worklistPg.MyWorkListPatDOB, "My worklist ED Enc Pat DOB");
			String MyWL_ED_Building = getText(worklistPg.MyWorkListEncBuilding, "My worklist ED Enc Building");
			String MyWL_ED_Bay = getText(worklistPg.MyWorkListEncBay, "My worklist ED Enc Bay");

			jsclick(worklistPg.MyWorkListMorePatDetailsIcon, "My worklist overview More patient details");
			Thread.sleep(3000);
			String MyWL_ED_Patdet_PatGender = getText(worklistPg.MyWorkListMorePatGender, "My worklist ED Enc More details Pat Gender");
			String MyWL_ED_Patdet_EncLocation = getText(worklistPg.MyWorkListMoreEncLocation, "My worklist ED Enc More details  Location");
			String MyWL_ED_Patdet_PatPasID = getText(worklistPg.MyWorkListMorePatPASID, "My worklist ED Enc More details  PASID");
			String MyWL_ED_Patdet_CPName = getText(worklistPg.MyWorkListMoreEncCareprovider, "My worklist ED Enc More details  CP Name");
			String MyWL_ED_Patdet_PatNHSNumber = getText(worklistPg.MyWorkListMorePatNHS, "My worklist ED Enc More details  Pat NHS Number");
			String MyWL_ED_Patdet_PatDOB = getText(worklistPg.MyWorkListMorePatDOB, "My worklist ED Enc More details  Pat DOB");
			String MyWL_ED_Patdet_AttendedDate =  getText(worklistPg.MyWorkListMoreEncPlannedDate, "My worklist ED Enc More details Enc start date");

			jsclick(worklistPg.MyWorkListClosePatDetailsIcon, "My worklist overview close More patient details");
			Thread.sleep(3000);

			jsclick(worklistPg.MyWorkListMarkUnfavouriteIcon, "My worklist overview unfavourite");
			Thread.sleep(3000);
			jsclick(worklistPg.MyWorkListEmergencyTab, "My worklist overview Emergency tab");
			Thread.sleep(3000);
			jsclick(worklistPg.MyWorkListInpatientTab, "My worklist overview admitted tab");
			Thread.sleep(3000);
			jsclick(worklistPg.MyWorkListInpatientTab, "My worklist overview admitted tab");
			Thread.sleep(3000);
			jsclick(worklistPg.MyWorkListEmergencyTab, "My worklist overview Emergency tab");
			takeSnap();

			AssertStringEquals(MyWL_OP_PatName, SORPatFullName, "MyWL_OP_PatName");
			AssertStringContains(MyWL_OP_PatDOB, SORPatientDOB, "MyWL_OP_PatDOB");
			AssertStringEquals(MyWL_OP_Patdet_PatGender, SORPatientGender, "MyWL_OP_Patdet_PatGender");
			AssertStringEquals(MyWL_OP_Patdet_PatPasID, SORPASID, "MyWL_OP_Patdet_PatPasID");
			AssertStringContains(MyWL_OP_Patdet_CPName, SOROPCareProviderForename, "MyWL_OP_Patdet_CPName");
			AssertStringContains(MyWL_OP_PatNHSNumber, MyWL_OP_Patdet_PatNHSNumber, "MyWL_OP_Patdet_PatNHSNumber");
			AssertStringContains(MyWL_OP_Patdet_PatDOB, SORPatientDOB, "MyWL_OP_Patdet_PatDOB");


			AssertStringEquals(MyWL_Contacts_PatName, SORPatFullName, "MyWL_Contacts_PatName");
			AssertStringContains(MyWL_Contacts_PatDOB, SORPatientDOB, "MyWL_Contacts_PatDOB");
			AssertStringEquals(MyWL_Contacts_Patdet_PatGender, SORPatientGender, "MyWL_Contacts_Patdet_PatGender");
			AssertStringEquals(MyWL_Contacts_Patdet_EncLocation, "No Ward", "MyWL_Contacts_Patdet_EncLocation");
			AssertStringEquals(MyWL_Contacts_Patdet_PatPasID , SORPASID, "MyWL_Contacts_Patdet_PatPasID ");
			AssertStringContains(MyWL_Contacts_Patdet_CPName, SORUserForename, "MyWL_Contacts_Patdet_CPName");
			AssertStringContains(MyWL_Contacts_PatNHSNumber, MyWL_Contacts_Patdet_PatNHSNumber, "MyWL_Contacts_Patdet_PatNHSNumber");
			AssertStringContains(MyWL_Contacts_Patdet_PatDOB, SORPatientDOB, "MyWL_Contacts_Patdet_PatDOB");
			AssertStringEquals(MyWL_Contacts_Patdet_StartDate, SORContactStartDate, "MyWL_Contacts_Patdet_StartDate");

			AssertStringEquals(MyWL_ED_PatName, SORPatFullName, "MyWL_ED_PatName");
			AssertStringContains(MyWL_ED_PatDOB, SORPatientDOB, "MyWL_ED_PatDOB");
			AssertStringEquals(MyWL_ED_Patdet_PatGender, SORPatientGender, "MyWL_ED_Patdet_PatGender");
			AssertStringEquals(MyWL_ED_Patdet_EncLocation, (MyWL_ED_Bay +", "+MyWL_ED_Building), "MyWL_ED_Patdet_EncLocation");
			AssertStringEquals(MyWL_ED_Patdet_PatPasID, SORPASID, "MyWL_ED_Patdet_PatPasID");
			AssertStringContains(MyWL_ED_Patdet_CPName, SORUserForename, "MyWL_ED_Patdet_CPName");
			AssertStringContains(MyWL_ED_PatNHSNumber, MyWL_ED_Patdet_PatNHSNumber, "MyWL_ED_Patdet_PatNHSNumber");
			AssertStringContains(MyWL_ED_Patdet_PatDOB, SORPatientDOB, "MyWL_ED_Patdet_PatDOB");
			AssertStringEquals(MyWL_ED_Patdet_AttendedDate, SOREDAttendedDate, "MyWL_ED_Patdet_AttendedDate");

		}
		*/

//=======================================================TESTCASE END============================================================//
		driver.quit();
		reportStep("Script completed", "INFO");	 

	}
}
