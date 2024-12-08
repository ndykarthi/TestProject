package com.dedalus.capages_windows;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods_windows.CAProjectMethods;

import io.appium.java_client.MobileElement;


public class AllergiesPage extends CAProjectMethods {
	JavascriptExecutor js = driver;

	public AllergiesPage(RemoteWebDriver driver, ExtentTest test) {
			this.test = test;
			this.driver = driver;
			PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//div/ion-icon[@name='flower']")
	public WebElement clickAllergiesTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Active')]/parent::ion-segment-button")
	public WebElement clickActiveTab;

	@FindBy(xpath ="//ion-label[contains(text(),'All')]/parent::ion-segment-button")
	public WebElement clickAllTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Inactive')]/parent::ion-segment-button")
	public WebElement clickInActiveTab;

	@FindBy(xpath ="//DXC-PATIENT-BANNER//ion-col[contains(@class,'pat-name')]")
	public WebElement getPatientFullnameBanner;

	@FindBy(xpath ="//DXC-PATIENT-BANNER//span[contains(text(),'NHS')]/following-sibling::span")
	public WebElement getPatientNHSNumberBanner;

	@FindBy(xpath ="//DXC-PATIENT-BANNER//span[contains(text(),'Born')]/following-sibling::span")
	public WebElement getPatientDOBBanner;
	
	@FindBy(xpath ="//*[contains(text(),'Last checked') or contains(text(),'Confirmed updates')]")
	public WebElement getlastcheckedtext;
	
	@FindBy(xpath ="//*[contains(text(),'Remarks')]/following-sibling::span")
	public WebElement getRemarks;
	
	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/parent::div/following-sibling::div/p")
	public List<WebElement> getsummaryAllergyCount;
	
	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/div/p")
	public List<WebElement> getsummaryLastChecked;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']")
	public WebElement summaryAllergytext;
	
	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]//p")
	public WebElement getsummaryModerateAllergenName;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[1]/div")
	public WebElement getsummaryModerateAllergyCategory;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[2]/div")
	public WebElement getsummaryModerateAllergyReacSev;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[3]/div")
	public WebElement getsummaryModerateAllergyOnset;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]//p")
	public WebElement getsummaryMildAllergenName;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]/div[@class='left']/child::div/span[1]/div")
	public WebElement getsummaryMildAllergyCategory;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]/div[@class='left']/child::div/span[2]/div")
	public WebElement getsummaryMildAllergyReacSev;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]/div[@class='left']/child::div/span[3]/div")
	public WebElement getsummaryMildAllergyOnset;
	
	
	
	
	public int getAllergiesActiveCount() throws InterruptedException {
		List<WebElement> allergyCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Active')]");
		int AllergyCnt = allergyCnt.size();
		takeSnap();
		return AllergyCnt;
	}

	public int getAllergyINActiveCount() throws InterruptedException {
		List<WebElement> AllergyCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Inactive')]");
		List<WebElement> AllergyCnt1 = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Entered-in-error')]");
		int allergyCnt = AllergyCnt.size();
		int allergyCnt1 = AllergyCnt1.size();
		int totalCnt = allergyCnt + allergyCnt1;
		takeSnap();
		return totalCnt;
	}


	//Active allergies - Validate by Index

	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-item//H2/span)[1]")
	public WebElement getModerateActiveAllergyName;

	@FindBy(xpath ="(//dxc-allergy-list-item[1]//ion-badge[contains(text(),'Active')]/ancestor::ion-item//span[contains(text(),'Onset')]/following-sibling::span)[1]")
	public WebElement getModerateActiveAllergyOnsetDate;

	@FindBy(xpath ="(//dxc-allergy-list-item[1]//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Review')]/following-sibling::span)[1]")
	public WebElement getModerateActiveAllergyReviewDate;        

	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Site')]/following-sibling::span)[1]")
	public WebElement getModerateActiveAllergySite;  
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Initiating hospital')]/following-sibling::span)[1]")
	public WebElement getModerateActiveAllergyInitiatingHospital;  
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Initiating hospital')]/following-sibling::span)[1]")
	public WebElement getModerateInactiveAllergyInitiatingHospital;  
	
	@FindBy(xpath ="(//dxc-allergy-list-item[1]//ion-badge[contains(text(),'Active')]/ancestor::ion-item//span[contains(text(),'Reaction')]/following-sibling::span)[1]")
	public WebElement getModerateActiveAllergyReactionSeverity;

	@FindBy(xpath ="//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Moderate')]/ancestor::ion-card//ion-icon")
	public WebElement expandModerateActiveAllergy;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Allergen')]/following-sibling::span)[1]")
	public WebElement getModerateActiveAllergen;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Category')]/following-sibling::span)[1]")
	public WebElement getModerateActiveAllergyCategory;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Information source')]/following-sibling::span)[1]")
	public WebElement getModerateActiveAllergyInformationSource;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Recorded by')]/following-sibling::span)[1]")
	public WebElement getModerateActiveAllergyRecordedBy;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Recorded on')]/following-sibling::span)[1]")
	public WebElement getModerateActiveAllergyRecordedDate;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Comment')]/following-sibling::span)[1]")
	public WebElement getModerateActiveAllergyComments;
	

	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-item//H2/span)[2]")
	public WebElement getMildActiveAllergyName;

	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-item//span[contains(text(),'Onset')]/following-sibling::span)[2]")
	public WebElement getMildActiveAllergyOnsetDate;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Review')]/following-sibling::span)[3]")
	public WebElement getMildActiveAllergyReviewDate;

	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-item//span[contains(text(),'Reaction')]/following-sibling::span)[2]")
	public WebElement getMildActiveAllergyReactionSeverity;

	@FindBy(xpath ="//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Mild')]/ancestor::ion-card//ion-icon")
	public WebElement expandMildActiveAllergy;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Allergen')]/following-sibling::span)[2]")
	public WebElement getMildActiveAllergen;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Category')]/following-sibling::span)[2]")
	public WebElement getMildActiveAllergyCategory;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Information source')]/following-sibling::span)[2]")
	public WebElement getMildActiveAllergyInformationSource;
		
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Recorded on')]/following-sibling::span)[2]")
	public WebElement getMildActiveAllergyRecordedDate;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Recorded by')]/following-sibling::span)[2]")
	public WebElement getMildActiveAllergyRecordedBy;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Comment')]/following-sibling::span)[2]")
	public WebElement getMildActiveAllergyComments;


	@FindBy(xpath ="//DXC-PATIENT-BANNER//ion-icon/parent::ion-col/span")
	public WebElement getBannerActiveAllergyCount;
	
	
	//Inactive allergies - Validate by Index

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//H2/span)[1]")
	public WebElement getModerateInactiveAllergyName;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//span[contains(text(),'Onset')]/following-sibling::span)[1]")
	public WebElement getModerateInactiveAllergyOnsetDate;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Review')]/following-sibling::span)[1]")
	public WebElement getModerateInactiveAllergyReviewDate;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//span[contains(text(),'Reaction')]/following-sibling::span)[1]")
	public WebElement getModerateInactiveAllergyReactionSeverity;

	@FindBy(xpath ="//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Moderate')]/ancestor::ion-card//ion-icon")
	public WebElement expandModerateInactiveAllergy;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Allergen')]/following-sibling::span)[1]")
	public WebElement getModerateInactiveAllergen;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Category')]/following-sibling::span)[1]")
	public WebElement getModerateInactiveAllergyCategory;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Information source')]/following-sibling::span)[1]")
	public WebElement getModerateInactiveAllergyInformationSource;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Recorded on')]/following-sibling::span)[1]")
	public WebElement getModerateInactiveAllergyRecordedDate;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Recorded by')]/following-sibling::span)[1]")
	public WebElement getModerateInactiveAllergyRecordedBy;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Comment')]/following-sibling::span)[1]")
	public WebElement getModerateInactiveAllergyComments;
	

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//H2/span)[2]")
	public WebElement getMildInactiveAllergyName;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//span[contains(text(),'Onset')]/following-sibling::span)[2]")
	public WebElement getMildInactiveAllergyOnsetDate;	

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//span[contains(text(),'Review')]/following-sibling::span)[2]")
	public WebElement getMildInactiveAllergyReviewDate;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//span[contains(text(),'Reaction')]/following-sibling::span)[2]")
	public WebElement getMildInactiveAllergyReactionSeverity;

	@FindBy(xpath ="//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Mild')]/ancestor::ion-card//ion-icon")
	public WebElement expandMildInactiveAllergy;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Allergen')]/following-sibling::span)[2]")
	public WebElement getMildInactiveAllergen;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Category')]/following-sibling::span)[2]")
	public WebElement getMildInactiveAllergyCategory;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Information source')]/following-sibling::span)[2]")
	public WebElement getMildInactiveAllergyInformationSource;
		
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Recorded on')]/following-sibling::span)[2]")
	public WebElement getMildInactiveAllergyRecordedDate;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Recorded by')]/following-sibling::span)[2]")
	public WebElement getMildInactiveAllergyRecordedBy;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Comment')]/following-sibling::span)[2]")
	public WebElement getMildInactiveAllergyComments;
	
	//Struckout allergy

	@FindBy(xpath  = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-item//H2/span")
	public WebElement getStruckoutAllergyName;

	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Onset')]/following-sibling::span")
	public WebElement getStruckoutAllergyOnsetDate;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Reaction (Severity):')]/following-sibling::span)[2]")
	public WebElement getStruckoutAllergyReactionSeverity;

	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-item//ion-icon")
	public WebElement expandStruckoutAllergy;

	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Allergen')]/following-sibling::span")
	public WebElement getStruckoutAllergen;
	
	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Category')]/following-sibling::span")
	public WebElement getStruckoutAllergyCategory;
	
	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Information source')]/following-sibling::span")
	public WebElement getStruckoutAllergyInformationSource;
	
	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Recorded on')]/following-sibling::span")
	public WebElement getStruckoutAllergyRecordedDate;
	
	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Recorded by')]/following-sibling::span")
	public WebElement getStruckoutAllergyRecordedBy;
	
	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Comment')]/following-sibling::span")
	public WebElement getStruckoutAllergyComments;
	
	
	
	
	
	
	//WEBPAS
	@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'HRN')]//following::span[1]")
	public WebElement WPgetPatientHRNNumberBanner;

	@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'DoB')]//following::span[1]")
	public WebElement WPgetPatientDOBBanner;
	
	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]//p")
	public WebElement getsummaryActiveAllergy2AllergenName;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]/div[@class='left']/child::div/span[1]/div")
	public WebElement getsummaryActiveAllergy2Category;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]/div[@class='left']/child::div/span[2]/div")
	public WebElement getsummaryActiveAllergy2ReacSev;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]/div[@class='left']/child::div/span[3]/div")
	public WebElement getsummaryActiveAllergy2Onset;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]//p")
	public WebElement getsummaryActiveAllergy1AllergenName;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[1]/div")
	public WebElement getsummaryActiveAllergy1Category;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[2]/div")
	public WebElement getsummaryActiveAllergy1ReacSev;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[3]/div")
	public WebElement getsummaryActiveAllergy1Onset;
		
	@FindBy(xpath ="(//ion-badge[contains(text(),'Active')]/ancestor::ion-card)[1]//ion-icon")
	public WebElement WPexpandActiveAllergy2;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Active')]/ancestor::ion-card)[2]//ion-icon")
	public WebElement WPexpandActiveAllergy1;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//H2/span")
	public WebElement WPgetActiveAllergy3Name;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Allergen')]/following-sibling::span")
	public WebElement WPgetModerateInactiveAllergen;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Category')]/following-sibling::span")
	public WebElement WPgetModerateInactiveAllergyCategory;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Recorded on')]/following-sibling::span")
	public WebElement WPgetModerateInactiveAllergyRecordedDate;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Recorded by')]/following-sibling::span")
	public WebElement WPgetModerateInactiveAllergyRecordedBy;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Site')]/following-sibling::span")
	public WebElement WPgetModerateInactiveAllergySite;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Comment')]/following-sibling::span")
	public WebElement WPgetModerateInactiveAllergyComments;
		
	@FindBy(xpath ="//dxc-allergy-list-item[2]//H2/span")
	public WebElement WPgetActiveAllergy1Name;
	
	@FindBy(xpath ="//dxc-allergy-list-item[3]//H2/span")
	public WebElement WPgetActiveAllergy2Name;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Recorded on')]/following-sibling::span")
	public WebElement WPgetModerateActiveAllergyRecordedDate;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Recorded by')]/following-sibling::span")
	public WebElement WPgetModerateActiveAllergyRecordedBy;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card)[1]//ion-icon")
	public WebElement WPexpandInactiveAllergy2;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//span[contains(text(),'Onset')]/following-sibling::span)[3]")
	public WebElement getInactiveAllergy2OnsetDate;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//span[contains(text(),'Reaction')]/following-sibling::span)[3]")
	public WebElement getInactiveAllergy2ReactionSeverity;
	
	@FindBy(xpath ="(//dxc-allergy-list-item)[1]//H2/span")
	public WebElement WPgetInActiveAllergy3Name;
	
	@FindBy(xpath ="(//dxc-allergy-list-item)[2]//H2/span")
	public WebElement WPgetInActiveAllergy1Name;
	
	@FindBy(xpath ="(//dxc-allergy-list-item)[3]//H2/span")
	public WebElement WPgetInActiveAllergy2Name;
	
	@FindBy(xpath ="//span[contains(text(),'Codiene')]")
	public List<WebElement> deletedAllergyName;
	
	@FindBy(xpath ="//span[contains(text(),'Adhesive Tape')]")
	public List<WebElement> AvailableAllergyName;
	
	public int getAllAllergiesCount() throws InterruptedException {
		List<WebElement> allergyCnt = driver.findElementsByXPath("//dxc-allergy-list-item");
		int AllergyCnt = allergyCnt.size();
		takeSnap();
		return AllergyCnt;
	}

	// No known allergy
		@FindBy(xpath = "//h2/ion-badge[contains(text(),'Active')]/ancestor::ion-item//div[contains(@class,'expand')]")
		public WebElement expandRec;
			
		@FindBy(xpath = "//span[contains(text(),'Recorded on')]/following-sibling::span")
		public WebElement NKARecDate;
		
		@FindBy(xpath = "//span[contains(text(),'Recorded by')]/following-sibling::span")
		public WebElement NKARecBy;
		
		//@FindBy(xpath = "//span[contains(text(),'Information source')]/following-sibling::span")
		//public WebElement NKAInfoSrc;
		
		@FindBy(xpath = "(//span[contains(text(),'Reaction')]/following-sibling::span)[1]")
		public WebElement NKAReaction;
		
		@FindBy(xpath = "//span[contains(text(),'Category')]/following-sibling::span")
		public WebElement NKACategory;
		
		@FindBy(xpath = "//span[contains(text(),'Allergen')]/following-sibling::span")
		public WebElement NKAAllergen;
		
		@FindBy(xpath = "//span[contains(text(),'Remarks')]/following-sibling::span")
		public WebElement NKAcomments;
		
		// Web PAS
		@FindBy(xpath = "//dxc-allergy-list-item//h2/span")
		public WebElement NKAWebPASType;
		
		@FindBy(xpath = "//span[contains(text(),'Recorded by')]/following-sibling::span")
		public WebElement NKAWebPASRecBy;
		
		@FindBy(xpath = "//span[contains(text(),'Recorded on')]/following-sibling::span")
		public WebElement NKAWebPASRecOn;
		
		@FindBy(xpath = "//span[contains(text(),'Site')]/following-sibling::span")
		public WebElement NKASite;
		
		@FindBy(xpath = "//span[contains(text(),'Initiating hosp')]/following-sibling::span")
		public WebElement NKAInitiatingHosp;

		@FindBy(xpath = "//span[contains(text(),'Review')]/following-sibling::span[1]")
		public WebElement NKAReviewDate;
		
		@FindBy(xpath = "(//span[contains(text(),'Onset')]/following-sibling::span)[1]")
		public WebElement NKAWebPASOnset;
		
		@FindBy(xpath = "//span[contains(text(),'Comment')]/following-sibling::span")
		public WebElement NKAWebPASComment;

}
