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

import com.dedalus.capages_windows.*;
import com.dedalus.restasssured_windows.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Allergies extends  RESTAssuredBase{

	Response response;
	JsonPath jsonPath;

	@Parameters({"SOR"})
	@BeforeTest
	public void setData(String SOR) throws IOException, InterruptedException, AWTException {
		testCaseName = "Allergies - "+SOR+" - Windows "; 
		testDescription = "The following test case verify Active and Inactive allergies";
		nodes = SOR;
		category = "Regression";
		authors = "Karthi";
	}



	@Parameters({"SOR","DeviceName"})

	@Test
	public void create(String SOR,String DeviceName, ITestContext context)
			throws InterruptedException, IOException, ClassNotFoundException, SQLException, ParseException, AWTException {

//		========================================= VIRTUOSO API =========================================

		ISuite suite = context.getSuite();
		int intJobID = (int) suite.getAttribute("JobID");
		int suiteID = (int) suite.getAttribute("SuiteID");
		String virtuosoOutcome = (String) suite.getAttribute("VirtuosoOutcome");

		String accessToken = "Bearer 72db341d-cbdb-4d6d-b521-df59d5b2a571";
		HashMap<String, String> alertHeaderMap = getHeaderMap(accessToken);
		Response response = getWithHeader(alertHeaderMap, "https://api.virtuoso.qa/api/testsuites/execution?suiteId="+suiteID+"&jobId="+intJobID+"&includeJourneyDetails=true");
		response.prettyPrint();

		AssertStringEquals(virtuosoOutcome, "PASS","Virtuoso Journey Failed");
//		========================================= VIRTUOSO API =========================================
		
			String SORConfigURL = getExposedValue(response, "LCP", "GV_ATCA_ConfigURL", "SORConfigURL");
			String SORPatientSurname = getExposedValue(response,"LCP","TD_strSurname","TD_strSurname");
			String SORPatientForename = getExposedValue(response,"LCP","TD_strForename","TD_strForename");
			String SORPatientDOB = getExposedValue(response,"LCP","TD_dtmDOB","TD_dtmDOB");
			String SORHRN = getExposedValue(response,"LCP","LV_HRN","LV_HRN");
			String SORPatAddLine1 = getExposedValue(response,"LCP","LV_AddressLine1","LV_AddressLine1");
			String SORPatAddLine2 = getExposedValue(response,"LCP","LV_AddressLine2","LV_AddressLine2"); 
			String SORPatAddLine3 = getExposedValue(response,"LCP","LV_AddressLine3","LV_AddressLine3");
			String SORPatAddLine4 = getExposedValue(response,"LCP","LV_AddressLine4","LV_AddressLine4");
			String SORAllergiesRecordedBy = getExposedValue(response,"CP","LV_strRecordedBy","LV_strRecordedBy");
			
			String SORActiveAllergen1 = getExposedValue(response,"CP","LV_ActiveAllergen1","LV_ActiveAllergen1");
			String SORRxnCode_Active1 = getExposedValue(response,"CP","LV_ReactionCode_Active1","LV_ReactionCode_Active1");
			String SORSeverityLvl_Active1 = getExposedValue(response,"CP","LV_SeverityLvl_Active1","LV_SeverityLvl_Active1");
			
			String SORActiveAllergen2 = getExposedValue(response,"CP","LV_ActiveAllergen2","LV_ActiveAllergen2");
			String SORAllergySite = getExposedValue(response,"CP","LV_strSite","LV_strSite");
			String SORRxnCmnts_Active2 = getExposedValue(response,"CP","LV_ReactionCmnts_Active2","LV_ReactionCmnts_Active2");
			String SORRxnCode_Active2 = getExposedValue(response,"CP","LV_ReactionCode_Active2","LV_ReactionCode_Active2");
			String SORSeverityLvl_Active2 = getExposedValue(response,"CP","LV_SeverityLvl_Active2","LV_SeverityLvl_Active2");
			
			String SORInactiveAllergen1 = getExposedValue(response,"CP","LV_InactiveAllergen1","LV_InactiveAllergen1");
			String SORRxnCode_InActive1 = getExposedValue(response,"CP","LV_ReactionCode_InActive1","LV_ReactionCode_InActive1");
			String SORSeverityLvl_InActive1 = getExposedValue(response,"CP","LV_SeverityLvl_InActive1","LV_SeverityLvl_InActive1");
			
			String SORInactiveAllergen2 = getExposedValue(response,"CP","LV_InActiveAllergen2","LV_InactiveAllergen2");
			String SORRxnCode_InActive2 = getExposedValue(response,"CP","LV_ReactionCode_InActive2","LV_ReactionCode_InActive2");
			String SORSeverityLvl_InActive2 = getExposedValue(response,"CP","LV_SeverityLvl_InActive2","LV_SeverityLvl_InActive2");
			
			String SORInactiveAllergen3 = getExposedValue(response,"CP","LV_InActiveAllergen3","LV_InactiveAllergen3");
			String SORRxnCmnts_InActive3 = getExposedValue(response,"CP","LV_ReactionCmnts_InActive3","LV_ReactionCmnts_InActive3");
			String SORRxnCode_InActive3 = getExposedValue(response,"CP","LV_ReactionCode_InActive3","LV_ReactionCode_InActive3");
			String SORSeverityLvl_InActive3 = getExposedValue(response,"CP","LV_SeverityLvl_InActive3","LV_SeverityLvl_InActive3");
			
			String SORCurrentDate = getExposedValue(response,"CP","LV_Date","LV_Date");
			
			
			String SORHospitalName = getExposedValue(response, "LCP", "GV_HospitalName", "GV_HospitalName");
		    String SORHospitalID = getExposedValue(response, "LCP", "GV_HospitalID", "GV_HospitalID");
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
			
			String Summary_ActiveAllergiesCount = getText(allergy.getsummaryAllergyCount, "Summary_AllegiesCount");
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
			VerifySelectedTab("Allergies / Intolerances",DeviceName);

	
			jsclick(summPg.tab_Summary, "Summary Tab");
			Thread.sleep(3000);
			// Clicking on Alert tile from Summary page
			click(allergy.summaryAllergytext,"Summary Allergy Tile");
			Thread.sleep(3000);
			VerifySelectedTab("Allergies / Intolerances",DeviceName);
		
			jsclick(summPg.tab_Summary, "Summary Tab");
			Thread.sleep(3000);

			//Navigation via icon
			jsclick(allergy.clickAllergiesTab,"Allergies Tab");
			Thread.sleep(5000);

			click(allergy.clickAllTab,"All tab");
			int allActiveCount = allergy.getAllergiesActiveCount();
			int allINActiveCount = allergy.getAllergyINActiveCount();
			int allCount = allActiveCount+allINActiveCount;
			//All allergies count validation
			if(allCount == 5) {
				reportStep("All Active and Inactive records are displayed for all Encounters", "PASS", false);
				System.out.println("All Active and Inactive records are displayed for all Encounters");
			}
			else {
				reportStep("All Active and Inactive records are not displayed for all Encounters", "FAIL", false);
				System.out.println("All Active  and Inactive records are not displayed for all Encounters");
			}		
			
			
			click(allergy.clickActiveTab,"Active tab");
			int allActiveCountACTIVETAB = allergy.getAllergiesActiveCount();

			if(allActiveCountACTIVETAB == allActiveCount) {
				reportStep("All Active records are displayed for all Encounters", "PASS", false);
				System.out.println("All Active records are displayed for all Encounters");
			}
			else {
				reportStep("All Active records are not displayed for all Encounters", "FAIL", false);
				System.out.println("All Active records are not displayed for all Encounters");
			}

			if(Integer.parseInt(Summary_ActiveAllergiesCount) == allActiveCountACTIVETAB) {
				System.out.println("All Active records count ["+allActiveCountACTIVETAB+"] matches with summary page");
				reportStep("All Active records count ["+allActiveCountACTIVETAB+"] matches with summary page", "PASS", false);
			}
			else {
				reportStep("All Active records count ["+allActiveCountACTIVETAB+"] does not matches with summary page","FAIL", false);
				System.out.println("All Active records count ["+allActiveCountACTIVETAB+"] does not matches with summary page");
			}

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


			click(allergy.clickActiveTab,"Active tab");
			Thread.sleep(5000);
			
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
			Thread.sleep(5000);

			
			String ActiveAllergy2Category = getText(allergy.getModerateActiveAllergyCategory, "Active allergy type 2");
			AssertStringEquals("other", ActiveAllergy2Category.toLowerCase(),"Active allergy type 2");
			AssertStringEquals("other", Summary_ActiveAllergy2Category.toLowerCase(),"Summary_ActiveAllergy2Category");

			String ActiveAllergy2RecordedDate = getText(allergy.WPgetModerateActiveAllergyRecordedDate, "Active allergy Recorded date 2");
			AssertStringEquals(SORCurrentDate, ActiveAllergy2RecordedDate, "Active allergy Recorded date 2");

			String ActiveAllergy2ReviewDate = getText(allergy.getModerateActiveAllergyReviewDate, "ActiveAllergy2ReviewDate");
			AssertStringEquals(ActiveAllergy2ReviewDate, SORCurrentDate, "ActiveAllergy2ReviewDate");

			String ActiveAllergy2Site = getText(allergy.getModerateActiveAllergySite, "ActiveAllergy2Site");
			AssertStringEquals(ActiveAllergy2Site, SORAllergySite, "ActiveAllergy2Site");
			
			String ActiveAllergy2hospital = getText(allergy.getModerateActiveAllergyInitiatingHospital, "ActiveAllergy2hospital");
			AssertStringEquals(ActiveAllergy2hospital, SORInitiatingHospital, "ActiveAllergy2hospital");

			String ActiveAllergy2RecordedBy = getText(allergy.WPgetModerateActiveAllergyRecordedBy, "Active allergy Recorded by 2");
			AssertStringContains(ActiveAllergy2RecordedBy.toLowerCase(), SORAllergiesRecordedBy.toLowerCase(), "Active allergy Recorded by 2");
		
			String ActiveAllergy2Comments = getText(allergy.getModerateActiveAllergyComments, "Active allergy Comments 2");
			AssertStringEquals(ActiveAllergy2Comments.toLowerCase(), SORRxnCmnts_Active2.toLowerCase(), "Active allergy Comments 2");

			jsclick(allergy.WPexpandActiveAllergy2, "Collapsing Active allergy 2");
			Thread.sleep(5000);

			click(allergy.clickInActiveTab,"Inactive tab");
			Thread.sleep(5000);
			
			
			
			String InactiveAllergen3Name = getText(allergy.WPgetInActiveAllergy3Name, "InactiveAllergen3Name");
			AssertStringEquals(InactiveAllergen3Name.toLowerCase(), SORInactiveAllergen3.toLowerCase(), "TD_InactiveAllergen3Name");
			
			String InactiveAllergy3OnsetDate = getText(allergy.getModerateInactiveAllergyOnsetDate, "InactiveAllergy3OnsetDate");
			AssertStringEquals(InactiveAllergy3OnsetDate, SORCurrentDate, "TD_InactiveAllergy3OnsetDate");
					
			String InactiveAllergy3ReactionSeverity = getText(allergy.getModerateInactiveAllergyReactionSeverity, "InactiveAllergy3ReactionSeverity");
			AssertStringEquals(InactiveAllergy3ReactionSeverity.toLowerCase(), (SORRxnCode_InActive3+" ("+SORSeverityLvl_InActive3+" - Mild)").toLowerCase(), "InactiveAllergy3ReactionSeverity");
			
			jsclick(allergy.WPexpandInactiveAllergy2, "Expanding Inactive allergy3");
			Thread.sleep(5000);

			String InactiveAllergy3Category = getText(allergy.WPgetModerateInactiveAllergyCategory, "Inactive allergy type 3");
			AssertStringEquals("other", InactiveAllergy3Category.toLowerCase(),"Inactive allergy type 3");

			String InactiveAllergy3RecordedDate = getText(allergy.WPgetModerateInactiveAllergyRecordedDate, "Inactive allergy Recorded date 3");
			AssertStringEquals(SORCurrentDate, InactiveAllergy3RecordedDate, "Inactive allergy Recorded date 3");

			String InactiveAllergy3RecordedBy = getText(allergy.WPgetModerateInactiveAllergyRecordedBy, "Inactive allergy Recorded by 3");
			AssertStringContains(InactiveAllergy3RecordedBy.toLowerCase(), SORAllergiesRecordedBy.toLowerCase(), "Inactive allergy Recorded by 3");
		
			String InactiveAllergy3Comments = getText(allergy.WPgetModerateInactiveAllergyComments, "Inactive allergy Comments 3");
			AssertStringEquals(InactiveAllergy3Comments.toLowerCase(), SORRxnCmnts_InActive3.toLowerCase(), "Inactive allergy Comments 3");
			
			jsclick(allergy.WPexpandInactiveAllergy2, "Collapsing Inactive allergy 2");
			Thread.sleep(5000);
			
			
			
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