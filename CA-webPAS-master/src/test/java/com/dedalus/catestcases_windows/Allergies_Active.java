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

import com.dedalus.capages_windows.*;
import com.dedalus.restasssured_windows.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Allergies_Active extends  RESTAssuredBase{

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
		String SORPatientGender = getExposedValue(varResponse,"LCP","TD_strGender","TD_strGender");
		String SORPatAddLine1 = getExposedValue(varResponse,"LCP","LV_AddressLine1","LV_AddressLine1");
		String SORPatAddLine2 = getExposedValue(varResponse,"LCP","LV_AddressLine2","LV_AddressLine2"); 
		String SORPatAddLine3 = getExposedValue(varResponse,"LCP","LV_AddressLine3","LV_AddressLine3");
		String SORPatAddLine4 = getExposedValue(varResponse,"LCP","LV_AddressLine4","LV_AddressLine4");
		String SORAllergiesRecordedBy = getExposedValue(varResponse,"CP","LV_strRecordedBy","LV_strRecordedBy");
		
		String SORActiveAllergen1 = getExposedValue(varResponse,"CP","LV_ActiveAllergen1","LV_ActiveAllergen1");
		String SORRxnCode_Active1 = getExposedValue(varResponse,"CP","LV_ReactionCode_Active1","LV_ReactionCode_Active1");
		String SORSeverityLvl_Active1 = getExposedValue(varResponse,"CP","LV_SeverityLvl_Active1","LV_SeverityLvl_Active1");
		
		String SORActiveAllergen2 = getExposedValue(varResponse,"CP","LV_ActiveAllergen2","LV_ActiveAllergen2");
		String SORAllergySite = getExposedValue(varResponse,"CP","LV_strSite","LV_strSite");
		String SORRxnCmnts_Active2 = getExposedValue(varResponse,"CP","LV_ReactionCmnts_Active2","LV_ReactionCmnts_Active2");
		String SORRxnCode_Active2 = getExposedValue(varResponse,"CP","LV_ReactionCode_Active2","LV_ReactionCode_Active2");
		String SORSeverityLvl_Active2 = getExposedValue(varResponse,"CP","LV_SeverityLvl_Active2","LV_SeverityLvl_Active2");
		
		
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
		
		String Summary_ActiveAllergy2Name = getText(allergy.getsummaryActiveAllergy2AllergenName, "Summary_ActiveAllergy2Name");
		String Summary_ActiveAllergy2Category = getText(allergy.getsummaryActiveAllergy2Category, "Summary_ActiveAllergy2Category");
		String Summary_ActiveAllergy2ReacSev = getText(allergy.getsummaryActiveAllergy2ReacSev, "Summary_ActiveAllergy2ReactionNSeverity");
		String Summary_ActiveAllergy2Onset = getText(allergy.getsummaryActiveAllergy2Onset,  "Summary_ActiveAllergy2OnsetDate").replace("Onset date: ", "");
		AssertStringEquals(Summary_ActiveAllergy2Name ,SORActiveAllergen2, "Active Allergen2");
		AssertStringEquals(Summary_ActiveAllergy2Onset,SORCurrentDate, "Active Allergy2 Onset Date");
		AssertStringEquals(Summary_ActiveAllergy2ReacSev.toUpperCase(),(SORRxnCode_Active2+" ("+SORSeverityLvl_Active2+" - Mild)").toUpperCase(), "Active Allergy2 Reaction severity");
		AssertStringEquals(Summary_ActiveAllergy2Category ,"Other","Active Allergy2 Category");

		String Summary_ActiveAllergy1Name = getText(allergy.getsummaryActiveAllergy1AllergenName, "Summary_ActiveAllergy1Name");
		String Summary_ActiveAllergy1Category = getText(allergy.getsummaryActiveAllergy1Category, "Summary_ActiveAllergy1Category");
		String Summary_ActiveAllergy1ReacSev = getText(allergy.getsummaryActiveAllergy1ReacSev, "Summary_ActiveAllergy1ReactionNSeverity");
		String Summary_ActiveAllergy1Onset = getText(allergy.getsummaryActiveAllergy1Onset, "Summary_ActiveAllergy1OnsetDate").replace("Onset date: ", "");
		AssertStringEquals(Summary_ActiveAllergy1Name ,SORActiveAllergen1, "Active Allergen1");
		AssertStringEquals(Summary_ActiveAllergy1Onset,SORCurrentDate, "Active Allergy1 Onset Date");
		AssertStringEquals(Summary_ActiveAllergy1ReacSev.toUpperCase(),(SORRxnCode_Active1+" ("+SORSeverityLvl_Active1+" - Mild)").toUpperCase(), "Active Allergy1 Reaction severity");
		AssertStringEquals(Summary_ActiveAllergy1Category ,"Other","Active Allergy1 Category");


		jsclick(patBnrPg.MoreButton, "More button");
		Thread.sleep(5000);
		String ActAllergyCountMore = getText(patBnrPg.AllergyCntMore, "Active allergy Count in More List");
		if(ActAllergyCountMore.equals(Summary_ActiveAllergiesCount)) {
			reportStep("Count of "+Summary_ActiveAllergiesCount+" Allergies is displayed in Patient Banner More List", "PASS");
		}else {
			reportStep("Count of "+Summary_ActiveAllergiesCount+" Allergies is not displayed in Patient Banner More List", "FAIL");
			System.out.println("Count of "+Summary_ActiveAllergiesCount+" Allergies is not displayed in Patient Banner More List");
		}
		String AllergyName1More = getText(patBnrPg.AllergyName1More, "Active Allergy 2 Name in More list");
		String AllergyName2More = getText(patBnrPg.AllergyName2More, "Active Allergy 1 Name in More list");

		// Clicking on Allergy link from More patient details
		click(patBnrPg.AllergyLinkMore, "Allergy link in More List");
		Thread.sleep(5000);
		if((DeviceName.contains("iPad"))|| (DeviceName.contains("Tab"))||(DeviceName.contains("Windows")))
		{
			VerifySelectedTab("Allergies / Intolerances",DeviceName);
		}
		else
		{
			VerifySelectedTab("Allergy/Intol",DeviceName);
		}
		
		click(allergy.clickAllTab,"All tab");
		int allActiveCount = allergy.getAllergiesActiveCount();
		int allINActiveCount = allergy.getAllergyINActiveCount();
		int allCount = allActiveCount+allINActiveCount;
		//All allergies count validation
		if(allCount == 2) {
			reportStep("All Active records are displayed for all Encounters", "PASS", false);
			System.out.println("All Active records are displayed for all Encounters");
		}
		else {
			reportStep("All Active  records are not displayed for all Encounters", "FAIL", false);
			System.out.println("All Active records are not displayed for all Encounters");
		}
		
		click(allergy.clickActiveTab,"Active tab");
		Thread.sleep(3000);
		
		String ActiveAllergen2Name = getText(allergy.getModerateActiveAllergyName, "ActiveAllergen2Name");
		AssertStringEquals(ActiveAllergen2Name.toLowerCase(), SORActiveAllergen2.toLowerCase(), "TD_ActiveAllergen2Name");
		AssertStringEquals(ActiveAllergen2Name.toLowerCase(), AllergyName2More.toLowerCase(), "AllergyName2More");
		
		String ActiveAllergy2OnsetDate = getText(allergy.getModerateActiveAllergyOnsetDate, "ActiveAllergy2OnsetDate");
		AssertStringEquals(ActiveAllergy2OnsetDate, SORCurrentDate, "TD_ActiveAllergy2OnsetDate");
				
		String ActiveAllergy2ReactionSeverity = getText(allergy.getModerateActiveAllergyReactionSeverity, "ActiveAllergy2ReactionSeverity");
		AssertStringEquals(ActiveAllergy2ReactionSeverity.toLowerCase(), Summary_ActiveAllergy2ReacSev.toLowerCase(), "ActiveAllergy2ReactionSeverity");
				
		String ActiveAllergen1Name = getText(allergy.getMildActiveAllergyName, "ActiveAllergen1Name");
		AssertStringEquals(ActiveAllergen1Name.toLowerCase(), SORActiveAllergen1.toLowerCase(), "TD_ActiveAllergen1Name");
		AssertStringEquals(ActiveAllergen1Name.toLowerCase(), AllergyName1More.toLowerCase(), "AllergyName1More");
		
		String ActiveAllergy1OnsetDate = getText(allergy.getMildActiveAllergyOnsetDate, "ActiveAllergy1OnsetDate");
		AssertStringContains(ActiveAllergy1OnsetDate, SORCurrentDate, "TD_ActiveAllergy1OnsetDate");
		
		String ActiveAllergy1ReactionSeverity = getText(allergy.getMildActiveAllergyReactionSeverity, "ActiveAllergy1ReactionSeverity");
		AssertStringEquals(ActiveAllergy1ReactionSeverity.toLowerCase(), Summary_ActiveAllergy1ReacSev.toLowerCase(), "ActiveAllergy1ReactionSeverity");

		jsclick(allergy.WPexpandActiveAllergy2, "Expanding Active allergy2");
		Thread.sleep(3000);

		String ActiveAllergy2Category = getText(allergy.getModerateActiveAllergyCategory, "Active allergy type 2");
		AssertStringEquals("other", ActiveAllergy2Category.toLowerCase(),"Active allergy type 2");
		AssertStringEquals("other", Summary_ActiveAllergy2Category.toLowerCase(),"Summary_ActiveAllergy2Category");

		

		String ActiveAllergy2ReviewDate = getText(allergy.getModerateActiveAllergyReviewDate, "ActiveAllergy2ReviewDate");
		AssertStringEquals(ActiveAllergy2ReviewDate, SORCurrentDate, "ActiveAllergy2ReviewDate");
		
		String ActiveAllergy2Site = getText(allergy.getModerateActiveAllergySite, "ActiveAllergy2Site");
		AssertStringEquals(ActiveAllergy2Site, SORAllergySite, "ActiveAllergy2Site");
		
		String ActiveAllergy2RecordedDate = getText(allergy.WPgetModerateActiveAllergyRecordedDate, "Active allergy Recorded date 2");
		AssertStringEquals(SORCurrentDate, ActiveAllergy2RecordedDate, "Active allergy Recorded date 2");

		String ActiveAllergy2RecordedBy = getText(allergy.WPgetModerateActiveAllergyRecordedBy, "Active allergy Recorded by 2");
		AssertStringContains(ActiveAllergy2RecordedBy.toLowerCase(), SORAllergiesRecordedBy.toLowerCase(), "Active allergy Recorded by 2");
	
		String ActiveAllergy2Comments = getText(allergy.getModerateActiveAllergyComments, "Active allergy Comments 2");
		AssertStringEquals(ActiveAllergy2Comments.toLowerCase(), SORRxnCmnts_Active2.toLowerCase(), "Active allergy Comments 2");

		jsclick(allergy.WPexpandActiveAllergy2, "Collapsing Active allergy 2");
		Thread.sleep(3000);
		
		jsclick(allergy.WPexpandActiveAllergy1, "Expanding Active allergy1");
		Thread.sleep(3000);


		String ActiveAllergy1ReviewDate = getText(allergy.getMildActiveAllergyReviewDate, "ActiveAllergy1ReviewDate");
		AssertStringContains(ActiveAllergy1ReviewDate, SORCurrentDate, "ActiveAllergy1ReviewDate");

		jsclick(allergy.WPexpandActiveAllergy1, "Collapsing Active allergy 1");
		Thread.sleep(3000);
		reportStep("Script completed", "INFO");
		driver.quit();
		
	}
		

}
