package com.dedalus.capages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.CAProjectMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AllergiesPage extends CAProjectMethods {
	JavascriptExecutor js = driver;

	public AllergiesPage(AppiumDriver<MobileElement> driver, ExtentTest test) {
		this.test = test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(xpath ="//DXC-PATIENT-BANNER//ion-col[contains(@class,'pat-name')]")
	public MobileElement getPatientFullnameBanner;

	@FindBy(xpath ="//DXC-PATIENT-BANNER//span[contains(text(),'NHS')]/following-sibling::span")
	public MobileElement getPatientNHSNumberBanner;

	@FindBy(xpath ="//DXC-PATIENT-BANNER//span[contains(text(),'Born')]/following-sibling::span")
	public MobileElement getPatientDOBBanner;
	

	@FindBy(xpath ="//div/ion-icon[@name='flower']")
	public MobileElement clickAllergiesTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Active')]/parent::ion-segment-button")
	public MobileElement clickActiveTab;

	@FindBy(xpath ="//ion-label[contains(text(),'All')]/parent::ion-segment-button")
	public MobileElement clickAllTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Inactive')]/parent::ion-segment-button")
	public MobileElement clickInActiveTab;

	@FindBy(xpath ="//*[contains(text(),'Last checked') or contains(text(),'Confirmed updates')]")
	public MobileElement getlastcheckedtext;
	
	@FindBy(xpath ="//*[contains(text(),'Remarks')]/following-sibling::span")
	public MobileElement getRemarks;
	
	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/parent::div/following-sibling::div/p")
	public List<MobileElement> getsummaryAllergyCount;
	
	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/div/p")
	public List<MobileElement> getsummaryLastChecked;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']")
	public MobileElement summaryAllergytext;
	
	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]//p")
	public MobileElement getsummaryModerateAllergenName;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[1]/div")
	public MobileElement getsummaryModerateAllergyCategory;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[2]/div")
	public MobileElement getsummaryModerateAllergyReacSev;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[3]/div")
	public MobileElement getsummaryModerateAllergyOnset;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]//p")
	public MobileElement getsummaryMildAllergenName;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]/div[@class='left']/child::div/span[1]/div")
	public MobileElement getsummaryMildAllergyCategory;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]/div[@class='left']/child::div/span[2]/div")
	public MobileElement getsummaryMildAllergyReacSev;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]/div[@class='left']/child::div/span[3]/div")
	public MobileElement getsummaryMildAllergyOnset;
	
	
	
	
	public int getAllergiesActiveCount() throws InterruptedException {
		List<MobileElement> allergyCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Active')]");
		int AllergyCnt = allergyCnt.size();
		takeSnap();
		return AllergyCnt;
	}

	public int getAllergyINActiveCount() throws InterruptedException {
		List<MobileElement> AllergyCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Inactive')]");
		List<MobileElement> AllergyCnt1 = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Entered-in-error')]");
		int allergyCnt = AllergyCnt.size();
		int allergyCnt1 = AllergyCnt1.size();
		int totalCnt = allergyCnt + allergyCnt1;
		takeSnap();
		return totalCnt;
	}


	//Active allergies - Validate by Index

	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-item//H2/span)[1]")
	public MobileElement getModerateActiveAllergyName;

	@FindBy(xpath ="(//dxc-allergy-list-item[1]//ion-badge[contains(text(),'Active')]/ancestor::ion-item//span[contains(text(),'Onset')]/following-sibling::span)[1]")
	public MobileElement getModerateActiveAllergyOnsetDate;

	@FindBy(xpath ="(//dxc-allergy-list-item[1]//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Review')]/following-sibling::span)[1]")
	public MobileElement getModerateActiveAllergyReviewDate;        

	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Site')]/following-sibling::span)[1]")
	public MobileElement getModerateActiveAllergySite;  
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Initiating hospital')]/following-sibling::span)[1]")
	public MobileElement getModerateActiveAllergyInitiatingHospital;  
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Initiating hospital')]/following-sibling::span)[1]")
	public MobileElement getModerateInactiveAllergyInitiatingHospital;  
	
	
	@FindBy(xpath ="(//dxc-allergy-list-item[1]//ion-badge[contains(text(),'Active')]/ancestor::ion-item//span[contains(text(),'Reaction')]/following-sibling::span)[1]")
	public MobileElement getModerateActiveAllergyReactionSeverity;

	@FindBy(xpath ="//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Moderate')]/ancestor::ion-card//ion-icon")
	public MobileElement expandModerateActiveAllergy;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Allergen')]/following-sibling::span)[1]")
	public MobileElement getModerateActiveAllergen;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Category')]/following-sibling::span)[1]")
	public MobileElement getModerateActiveAllergyCategory;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Information source')]/following-sibling::span)[1]")
	public MobileElement getModerateActiveAllergyInformationSource;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Recorded By')]/following-sibling::span)[1]")
	public MobileElement getModerateActiveAllergyRecordedBy;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Recorded Date')]/following-sibling::span)[1]")
	public MobileElement getModerateActiveAllergyRecordedDate;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Comment')]/following-sibling::span)[1]")
	public MobileElement getModerateActiveAllergyComments;
	

	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-item//H2/span)[2]")
	public MobileElement getMildActiveAllergyName;

	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-item//span[contains(text(),'Onset')]/following-sibling::span)[2]")
	public MobileElement getMildActiveAllergyOnsetDate;

	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Review')]/following-sibling::span)[3]")
	public MobileElement getMildActiveAllergyReviewDate;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-item//span[contains(text(),'Reaction')]/following-sibling::span)[2]")
	public MobileElement getMildActiveAllergyReactionSeverity;

	@FindBy(xpath ="//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Mild')]/ancestor::ion-card//ion-icon")
	public MobileElement expandMildActiveAllergy;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Allergen')]/following-sibling::span)[2]")
	public MobileElement getMildActiveAllergen;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Category')]/following-sibling::span)[2]")
	public MobileElement getMildActiveAllergyCategory;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Information source')]/following-sibling::span)[2]")
	public MobileElement getMildActiveAllergyInformationSource;
		
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Recorded Date')]/following-sibling::span)[2]")
	public MobileElement getMildActiveAllergyRecordedDate;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Recorded By')]/following-sibling::span)[2]")
	public MobileElement getMildActiveAllergyRecordedBy;
	
	@FindBy(xpath ="(//dxc-allergy-list-item//ion-badge[contains(text(),'Active')]/ancestor::ion-card//span[contains(text(),'Comment')]/following-sibling::span)[2]")
	public MobileElement getMildActiveAllergyComments;

	//Inactive allergies - Validate by Index

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//H2/span)[1]")
	public MobileElement getModerateInactiveAllergyName;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//span[contains(text(),'Onset')]/following-sibling::span)[1]")
	public MobileElement getModerateInactiveAllergyOnsetDate;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//span[contains(text(),'Reaction')]/following-sibling::span)[1]")
	public MobileElement getModerateInactiveAllergyReactionSeverity;

	@FindBy(xpath ="//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Moderate')]/ancestor::ion-card//ion-icon")
	public MobileElement expandModerateInactiveAllergy;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Allergen')]/following-sibling::span)[1]")
	public MobileElement getModerateInactiveAllergen;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Category')]/following-sibling::span)[1]")
	public MobileElement getModerateInactiveAllergyCategory;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Information source')]/following-sibling::span)[1]")
	public MobileElement getModerateInactiveAllergyInformationSource;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Recorded Date')]/following-sibling::span)[1]")
	public MobileElement getModerateInactiveAllergyRecordedDate;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Recorded By')]/following-sibling::span)[1]")
	public MobileElement getModerateInactiveAllergyRecordedBy;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Comment')]/following-sibling::span)[1]")
	public MobileElement getModerateInactiveAllergyComments;
	

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//H2/span)[2]")
	public MobileElement getMildInactiveAllergyName;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//span[contains(text(),'Onset')]/following-sibling::span)[2]")
	public MobileElement getMildInactiveAllergyOnsetDate;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//span[contains(text(),'Reaction')]/following-sibling::span)[2]")
	public MobileElement getMildInactiveAllergyReactionSeverity;

	@FindBy(xpath ="//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Mild')]/ancestor::ion-card//ion-icon")
	public MobileElement expandMildInactiveAllergy;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Allergen')]/following-sibling::span)[2]")
	public MobileElement getMildInactiveAllergen;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Category')]/following-sibling::span)[2]")
	public MobileElement getMildInactiveAllergyCategory;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Information source')]/following-sibling::span)[2]")
	public MobileElement getMildInactiveAllergyInformationSource;
		
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Recorded Date')]/following-sibling::span)[2]")
	public MobileElement getMildInactiveAllergyRecordedDate;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Recorded By')]/following-sibling::span)[2]")
	public MobileElement getMildInactiveAllergyRecordedBy;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Comment')]/following-sibling::span)[2]")
	public MobileElement getMildInactiveAllergyComments;
	
	//Struckout allergy

	@FindBy(xpath  = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-item//H2/span")
	public MobileElement getStruckoutAllergyName;

	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Onset')]/following-sibling::span")
	public MobileElement getStruckoutAllergyOnsetDate;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Reaction(Severity):')]/following-sibling::span)[2]")
	public MobileElement getStruckoutAllergyReactionSeverity;

	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-item//ion-icon")
	public MobileElement expandStruckoutAllergy;

	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Allergen')]/following-sibling::span")
	public MobileElement getStruckoutAllergen;
	
	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Category')]/following-sibling::span")
	public MobileElement getStruckoutAllergyCategory;
	
	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Information source')]/following-sibling::span")
	public MobileElement getStruckoutAllergyInformationSource;
	
	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Recorded Date')]/following-sibling::span")
	public MobileElement getStruckoutAllergyRecordedDate;
	
	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Recorded By')]/following-sibling::span")
	public MobileElement getStruckoutAllergyRecordedBy;
	
	@FindBy(xpath ="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card//span[contains(text(),'Comment')]/following-sibling::span")
	public MobileElement getStruckoutAllergyComments;
	
	//WEBPAS
	@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'HRN')]//following::span[1]")
	public MobileElement WPgetPatientHRNNumberBanner;

	@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'DoB')]//following::span[1]")
	public MobileElement WPgetPatientDOBBanner;
	
	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]//p")
	public MobileElement getsummaryActiveAllergy2AllergenName;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]/div[@class='left']/child::div/span[1]/div")
	public MobileElement getsummaryActiveAllergy2Category;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]/div[@class='left']/child::div/span[2]/div")
	public MobileElement getsummaryActiveAllergy2ReacSev;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]/div[@class='left']/child::div/span[3]/div")
	public MobileElement getsummaryActiveAllergy2Onset;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]//p")
	public MobileElement getsummaryActiveAllergy1AllergenName;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[1]/div")
	public MobileElement getsummaryActiveAllergy1Category;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[2]/div")
	public MobileElement getsummaryActiveAllergy1ReacSev;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[3]/div")
	public MobileElement getsummaryActiveAllergy1Onset;
		
	@FindBy(xpath ="(//ion-badge[contains(text(),'Active')]/ancestor::ion-card)[1]//ion-icon")
	public MobileElement WPexpandActiveAllergy2;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Active')]/ancestor::ion-card)[2]//ion-icon")
	public MobileElement WPexpandActiveAllergy1;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//H2/span")
	public MobileElement WPgetActiveAllergy3Name;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Allergen')]/following-sibling::span")
	public MobileElement WPgetModerateInactiveAllergen;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Category')]/following-sibling::span")
	public MobileElement WPgetModerateInactiveAllergyCategory;
		
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Recorded on')]/following-sibling::span")
	public MobileElement WPgetModerateInactiveAllergyRecordedDate;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Recorded by')]/following-sibling::span")
	public MobileElement WPgetModerateInactiveAllergyRecordedBy;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Site')]/following-sibling::span")
	public MobileElement WPgetModerateInactiveAllergySite;

	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Comment')]/following-sibling::span")
	public MobileElement WPgetModerateInactiveAllergyComments;
		
	@FindBy(xpath ="//dxc-allergy-list-item[2]//H2/span")
	public MobileElement WPgetActiveAllergy1Name;
	
	@FindBy(xpath ="//dxc-allergy-list-item[3]//H2/span")
	public MobileElement WPgetActiveAllergy2Name;
	

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card)[1]//ion-icon")
	public MobileElement WPexpandInactiveAllergy2;
	

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card)[2]//ion-icon")
	public MobileElement WPexpandInactiveAllergy3;
	
	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//span[contains(text(),'Onset')]/following-sibling::span)[3]")
	public MobileElement getInactiveAllergy2OnsetDate;

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//span[contains(text(),'Reaction')]/following-sibling::span)[3]")
	public MobileElement getInactiveAllergy2ReactionSeverity;
	
	@FindBy(xpath ="(//dxc-allergy-list-item)[1]//H2/span")
	public MobileElement WPgetInActiveAllergy3Name;
	
	@FindBy(xpath ="(//dxc-allergy-list-item)[2]//H2/span")
	public MobileElement WPgetInActiveAllergy1Name;
	
	@FindBy(xpath ="(//dxc-allergy-list-item)[3]//H2/span")
	public MobileElement WPgetInActiveAllergy2Name;
	
	@FindBy(xpath ="//span[contains(text(),'Codiene')]")
	public List<MobileElement> deletedAllergyName;
	
	@FindBy(xpath ="//span[contains(text(),'Adhesive Tape')]")
	public List<MobileElement> AvailableAllergyName;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Recorded on')]/following-sibling::span")
	public MobileElement WPgetModerateActiveAllergyRecordedDate;
	
	@FindBy(xpath ="//dxc-allergy-list-item[1]//span[contains(text(),'Recorded by')]/following-sibling::span")
	public MobileElement WPgetModerateActiveAllergyRecordedBy;
	
	// No known allergy
	@FindBy(xpath = "//h2/ion-badge[contains(text(),'Active')]/ancestor::ion-item//div[contains(@class,'expand')]")
	public MobileElement expandRec;
			
	@FindBy(xpath = "//span[contains(text(),'Recorded Date')]/following-sibling::span")
	public MobileElement NKARecDate;
		
	@FindBy(xpath = "//span[contains(text(),'Recorded By')]/following-sibling::span")
	public MobileElement NKARecBy;
		
	//@FindBy(xpath = "//span[contains(text(),'Information source')]/following-sibling::span")
	//public MobileElement NKAInfoSrc;
		
	@FindBy(xpath = "(//span[contains(text(),'Reaction')]/following-sibling::span)[1]")
	public MobileElement NKAReaction;
		
	@FindBy(xpath = "//span[contains(text(),'Category')]/following-sibling::span")
	public MobileElement NKACategory;
		
	@FindBy(xpath = "//span[contains(text(),'Allergen')]/following-sibling::span")
	public MobileElement NKAAllergen;
		
	@FindBy(xpath = "//span[contains(text(),'Remarks')]/following-sibling::span")
	public MobileElement NKAcomments;


	public int getAllAllergiesCount() throws InterruptedException {
		List<MobileElement> allergyCnt = driver.findElementsByXPath("//dxc-allergy-list-item");
		int AllergyCnt = allergyCnt.size();
		takeSnap();
		return AllergyCnt;
	}
	
	// Web PAS
	@FindBy(xpath = "//dxc-allergy-list-item//h2/span")
	public MobileElement NKAWebPASType;
			
	@FindBy(xpath = "//span[contains(text(),'Recorded by')]/following-sibling::span")
	public MobileElement NKAWebPASRecBy;
			
	@FindBy(xpath = "//span[contains(text(),'Recorded on')]/following-sibling::span")
	public MobileElement NKAWebPASRecOn;
			
	@FindBy(xpath = "//span[contains(text(),'Site')]/following-sibling::span")
	public MobileElement NKASite;
			
	@FindBy(xpath = "//span[contains(text(),'Initiating hosp')]/following-sibling::span")
	public MobileElement NKAInitiatingHosp;

	@FindBy(xpath = "//span[contains(text(),'Review')]/following-sibling::span[1]")
	public MobileElement NKAReviewDate;
			
	@FindBy(xpath = "(//span[contains(text(),'Onset')]/following-sibling::span)[1]")
	public MobileElement NKAWebPASOnset;
			
	@FindBy(xpath = "//span[contains(text(),'Comment')]/following-sibling::span")
	public MobileElement NKAWebPASComment;
	

	@FindBy(xpath ="//DXC-PATIENT-BANNER//ion-icon/parent::ion-col/span")
	public MobileElement getBannerActiveAllergyCount;


	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Review')]/following-sibling::span)[1]")
	public MobileElement getModerateInactiveAllergyReviewDate;
	

	@FindBy(xpath ="(//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card//span[contains(text(),'Review')]/following-sibling::span)[3]")
	public MobileElement getMildInactiveAllergyReviewDate;
	
}


