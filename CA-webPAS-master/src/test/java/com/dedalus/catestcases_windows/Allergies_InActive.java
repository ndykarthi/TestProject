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

import com.dedalus.capages_windows.AllergiesPage;
import com.dedalus.capages_windows.MenuPage;
import com.dedalus.capages_windows.PatientBannerPage;
import com.dedalus.capages_windows.SummaryPage;
import com.dedalus.restasssured_windows.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Allergies_InActive extends RESTAssuredBase{
	Response response;
	JsonPath jsonPath;

	@Parameters({"SOR"})
	@BeforeTest
	public void setData(String SOR) throws IOException, InterruptedException, AWTException {
		testCaseName = "Allergies - "+SOR+" - Windows "; 
		testDescription = "The following test case verify Active and Inactive(close and Struck out) allergies";
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
		String SORPatientSurname = getExposedValue(varResponse,"LCP","TD_strSurname","TD_strSurname");
		String SORPatientForename = getExposedValue(varResponse,"LCP","TD_strForename","TD_strForename");
		String SORPatientDOB = getExposedValue(varResponse,"LCP","TD_dtmDOB","TD_dtmDOB");
		String SORHRN = getExposedValue(varResponse,"LCP","LV_HRN","LV_HRN");
		String SORPatAddLine1 = getExposedValue(varResponse,"LCP","LV_AddressLine1","LV_AddressLine1");
		String SORPatAddLine2 = getExposedValue(varResponse,"LCP","LV_AddressLine2","LV_AddressLine2"); 
		String SORPatAddLine3 = getExposedValue(varResponse,"LCP","LV_AddressLine3","LV_AddressLine3");
		String SORPatAddLine4 = getExposedValue(varResponse,"LCP","LV_AddressLine4","LV_AddressLine4");
		String SORAllergiesRecordedBy = getExposedValue(varResponse,"CP","LV_strRecordedBy","LV_strRecordedBy");
		
		
		String SORAllergySite = getExposedValue(varResponse,"CP","LV_strSite","LV_strSite");
		
		String SORInactiveAllergen1 = getExposedValue(varResponse,"CP","LV_InactiveAllergen1","LV_InactiveAllergen1");
		String SORRxnCode_InActive1 = getExposedValue(varResponse,"CP","LV_ReactionCode_InActive1","LV_ReactionCode_InActive1");
		String SORSeverityLvl_InActive1 = getExposedValue(varResponse,"CP","LV_SeverityLvl_InActive1","LV_SeverityLvl_InActive1");
		
		String SORInactiveAllergen2 = getExposedValue(varResponse,"CP","LV_InActiveAllergen2","LV_InactiveAllergen2");
		String SORRxnCode_InActive2 = getExposedValue(varResponse,"CP","LV_ReactionCode_InActive2","LV_ReactionCode_InActive2");
		String SORSeverityLvl_InActive2 = getExposedValue(varResponse,"CP","LV_SeverityLvl_InActive2","LV_SeverityLvl_InActive2");
		
		String SORInactiveAllergen3 = getExposedValue(varResponse,"CP","LV_InActiveAllergen3","LV_InactiveAllergen3");
		String SORRxnCmnts_InActive3 = getExposedValue(varResponse,"CP","LV_ReactionCmnts_InActive3","LV_ReactionCmnts_InActive3");
		String SORRxnCode_InActive3 = getExposedValue(varResponse,"CP","LV_ReactionCode_InActive3","LV_ReactionCode_InActive3");
		String SORSeverityLvl_InActive3 = getExposedValue(varResponse,"CP","LV_SeverityLvl_InActive3","LV_SeverityLvl_InActive3");
		
		String SORCurrentDate = getExposedValue(varResponse,"CP","LV_Date","LV_Date");
		
		
		String SORHospitalName = getExposedValue(varResponse, "LCP", "GV_HospitalName", "GV_HospitalName");
	    String SORHospitalID = getExposedValue(varResponse, "LCP", "GV_HospitalID", "GV_HospitalID");
	    String SORInitiatingHospital = SORHospitalName;
 
		
		startApp(SOR,SORConfigURL);
		Thread.sleep(8000);
		MenuPage mnuPg = new MenuPage(driver, test);
		AllergiesPage allergy= new AllergiesPage(driver,test);
		SummaryPage summPg = new SummaryPage(driver, test);
		PatientBannerPage patBnrPg = new PatientBannerPage(driver, test);
		click(mnuPg.clickMenuNew, "Menu");
		Thread.sleep(3000);
		click(mnuPg.menu_Search,"Search");
		Thread.sleep(3000);

		SearchNSelectPatient(SORHRN);
		Thread.sleep(15000);


		//Patient banner
		String PatBanner_FullName = getText(allergy.getPatientFullnameBanner, "Patient full name from Patient banner");
		AssertStringContains(PatBanner_FullName, SORPatientSurname.toUpperCase()+", "+SORPatientForename, "Pat Name");
		String PatBanner_HRNNumber = getText(allergy.WPgetPatientHRNNumberBanner, "Patient HRN number from Patient banner");
		AssertStringEquals(PatBanner_HRNNumber, SORHRN, "HRN NUmber from banner");
		String PatBanner_Dob = getText(allergy.WPgetPatientDOBBanner, "Patient DOB from Patient banner");
		AssertStringContains(PatBanner_Dob, SORPatientDOB , "Pat DOB");
		String PatBanner_ActiveAllergiesCount = getText(allergy.getBannerActiveAllergyCount, "PatBanner_AllegiesCount");
		String Summary_ActiveAllergiesCount = getText(allergy.getsummaryAllergyCount, "Summary_AllegiesCount");
		AssertStringEquals(PatBanner_ActiveAllergiesCount ,Summary_ActiveAllergiesCount, "Active Allergy count");
		
		//Navigation via icon
		jsclick(allergy.clickAllergiesTab,"Allergies Tab");
		Thread.sleep(5000);

		//Inactive allergies count validation	
		click(allergy.clickInActiveTab,"Inactive tab");
		int allINActiveCountINACTIVETAB = allergy.getAllergyINActiveCount();

		if(allINActiveCountINACTIVETAB == 3) {
			reportStep("All Inactive records are displayed for all Encounters", "PASS", false);
			System.out.println("All Inactive records are displayed for all Encounters");
		}
		else {
			reportStep("All Inactive records are not displayed for all Encounters", "FAIL", false);
			System.out.println("All Inactive records are not displayed for all Encounters");
		}

		String InactiveAllergen3Name = getText(allergy.WPgetInActiveAllergy3Name, "InactiveAllergen3Name");
		AssertStringEquals(InactiveAllergen3Name.toLowerCase(), SORInactiveAllergen3.toLowerCase(), "TD_InactiveAllergen3Name");
		
		String InactiveAllergy3OnsetDate = getText(allergy.getModerateInactiveAllergyOnsetDate, "InactiveAllergy3OnsetDate");
		AssertStringEquals(InactiveAllergy3OnsetDate, SORCurrentDate, "TD_InactiveAllergy3OnsetDate");
				
		String InactiveAllergy3ReactionSeverity = getText(allergy.getModerateInactiveAllergyReactionSeverity, "InactiveAllergy3ReactionSeverity");
		AssertStringEquals(InactiveAllergy3ReactionSeverity.toLowerCase(), (SORRxnCode_InActive3+" ("+SORSeverityLvl_InActive3+" - Mild)").toLowerCase(), "InactiveAllergy3ReactionSeverity");
		
		jsclick(allergy.WPexpandInactiveAllergy2, "Expanding Inactive allergy3");
		Thread.sleep(3000);

		String InactiveAllergy3Category = getText(allergy.WPgetModerateInactiveAllergyCategory, "Inactive allergy type 3");
		AssertStringEquals("other", InactiveAllergy3Category.toLowerCase(),"Inactive allergy type 3");

		String InactiveAllergy3RecordedDate = getText(allergy.WPgetModerateInactiveAllergyRecordedDate, "Inactive allergy Recorded date 3");
		AssertStringEquals(SORCurrentDate, InactiveAllergy3RecordedDate, "Inactive allergy Recorded date 3");
		
		String InactiveAllergySIte = getText(allergy.WPgetModerateInactiveAllergySite, "Inactive allergy Site3");
		AssertStringEquals(SORAllergySite, InactiveAllergySIte, "Inactive allergy Site");		
		
		String InactiveAllergyhospital = getText(allergy.getModerateInactiveAllergyInitiatingHospital, "InactiveAllergyhospital");
		AssertStringEquals(InactiveAllergyhospital, SORInitiatingHospital, "InactiveAllergyhospital");

		String InactiveAllergy3RecordedBy = getText(allergy.WPgetModerateInactiveAllergyRecordedBy, "Inactive allergy Recorded by 3");
		AssertStringContains(InactiveAllergy3RecordedBy.toLowerCase(), SORAllergiesRecordedBy.toLowerCase(), "Inactive allergy Recorded by 3");
	
		String InactiveAllergy3Comments = getText(allergy.WPgetModerateInactiveAllergyComments, "Inactive allergy Comments 3");
		AssertStringEquals(InactiveAllergy3Comments.toLowerCase(), SORRxnCmnts_InActive3.toLowerCase(), "Inactive allergy Comments 3");
		
		jsclick(allergy.WPexpandInactiveAllergy2, "Collapsing Inactive allergy 2");
		Thread.sleep(3000);
		
		
		String InactiveAllergen1Name = getText(allergy.getMildInactiveAllergyName, "InactiveAllergen1Name");
		AssertStringEquals(InactiveAllergen1Name.toLowerCase(), SORInactiveAllergen1.toLowerCase(), "TD_InactiveAllergen1Name");
		
		String InactiveAllergy1OnsetDate = getText(allergy.getMildInactiveAllergyOnsetDate, "InactiveAllergy1OnsetDate");
		AssertStringContains(InactiveAllergy1OnsetDate, SORCurrentDate, "TD_InactiveAllergy1OnsetDate");
		
		String InactiveAllergy1ReactionSeverity = getText(allergy.getMildInactiveAllergyReactionSeverity, "InactiveAllergy1ReactionSeverity");
		AssertStringEquals(InactiveAllergy1ReactionSeverity.toLowerCase(), (SORRxnCode_InActive1+" ("+SORSeverityLvl_InActive1+" - Mild)").toLowerCase(), "InactiveAllergy1ReactionSeverity");
		
		
		String InactiveAllergen2Name = getText(allergy.WPgetInActiveAllergy2Name , "InactiveAllergen2Name");
		AssertStringEquals(InactiveAllergen2Name.toLowerCase(), SORInactiveAllergen2.toLowerCase(), "TD_InactiveAllergen2Name");
		
		String InactiveAllergy2OnsetDate = getText(allergy.getInactiveAllergy2OnsetDate , "InactiveAllergy2OnsetDate");
		AssertStringContains(InactiveAllergy2OnsetDate, SORCurrentDate, "TD_InactiveAllergy2OnsetDate");
		
		String InactiveAllergy2ReactionSeverity = getText(allergy.getInactiveAllergy2ReactionSeverity , "InactiveAllergy2ReactionSeverity");
		AssertStringEquals(InactiveAllergy2ReactionSeverity.toLowerCase(), (SORRxnCode_InActive2+" ("+SORSeverityLvl_InActive2+" - Mild)").toLowerCase(), "InactiveAllergy2ReactionSeverity");
		
		reportStep("Script completed", "INFO");
		driver.quit();
		
		
	}
}
