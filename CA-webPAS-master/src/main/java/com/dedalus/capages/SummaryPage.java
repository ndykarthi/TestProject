package com.dedalus.capages;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.CAProjectMethods;
import com.dedalus.genericappmethods.GenericAppMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SummaryPage extends CAProjectMethods {
	
	public SummaryPage(AppiumDriver<MobileElement> driver,ExtentTest test) {
		this.test = test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(xpath = "//DXC-PATIENT-BANNER//ion-col[contains(@class,'pat-name')]")
	public MobileElement getPatientNameInBanner;
	
	@FindBy(xpath ="//DXC-PATIENT-BANNER//span[contains(text(),'NHS')]/following-sibling::span")
	public MobileElement getNHSNoInBanner;
	
	@FindBy(xpath ="//DXC-PATIENT-BANNER//span[contains(text(),'Born')]/following-sibling::span")
	public MobileElement getDOBInBanner;
	
	
	//Summary
//	@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Summary')]")
//	public MobileElement tab_Summary;
	
	@FindBy(xpath = "//div/ion-icon[@name='home']")
	public MobileElement tab_Summary;
	
	// Alert
	@FindBy(xpath = "//p[contains(text(),'Alerts')]/parent::div//ion-icon")
	public MobileElement AlertIcon;
	
	@FindBy(xpath = "//p[contains(text(),'Alerts')]/parent::div/following-sibling::div/p")
	public MobileElement SummaryAlertsCount;
	
	@FindBy(xpath = "(//p[contains(text(),'Alerts')]/parent::div/parent::div/following-sibling::div//p[contains(@class,'title')])[1]")
	public MobileElement getSumAlertName1;

	@FindBy(xpath = "(//p[contains(text(),'Alerts')]/parent::div/parent::div/following-sibling::div//span/div)[1]")
	public MobileElement getSumAlertType1;
	
	@FindBy(xpath = "(//p[contains(text(),'Alerts')]/parent::div/parent::div/following-sibling::div//span/div)[2]")
	public MobileElement getSumAlertOnsetDate1;
	
	@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Alerts')]")
	public MobileElement tab_Alerts;
	
	// Procedures
	@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Procedure')]")
	public MobileElement tab_Procedures;
	
	@FindBy(xpath = "//p[contains(text(),'Procedures')]/parent::div//ion-icon")
	public MobileElement ProcedureIcon;
	
	@FindBy(xpath = "//p[contains(text(),'Procedures')]/ancestor::ion-card-content//p[@class='details']")
	public MobileElement PlanProcCntSummary;
	
	@FindBy(xpath = "//p[contains(text(),'Procedures')]/parent::div/following-sibling::div/p")
	public MobileElement SummaryProcCount;
	
	//Advance Directive
	@FindBy(xpath = "//p[contains(text(),'Advance Directive')]/parent::div//ion-icon")
	public MobileElement AdvDirIcon;
	
	@FindBy(xpath = "//p[contains(text(),'Advance Dir')]/parent::div/following-sibling::div/p")
	public MobileElement SummaryAdvDirCount;
	
	// Documents
	@FindBy(xpath = "(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[1]")
	public MobileElement DocumentSummary;
	
	@FindBy(xpath = "//p[contains(text(),'Documents')]/parent::div/following-sibling::div/p")
	public MobileElement SummaryDocumentCount;
	
	@FindBy(xpath = "//p[contains(text(),'Documents')]/parent::div//ion-icon")
	public MobileElement DocumentIcon;

	@FindBy(xpath = "//p[contains(text(),'Documents')]/parent::div//span[@class='alignis']")
	public MobileElement DocumentDisplayPeriod;
	
	@FindBy(xpath = "//*[contains(text(),'Forms')]/parent::div//p[contains(@class,'count')]")
	public MobileElement DocumentFormsCnt;
	
	@FindBy(xpath = "//*[contains(text(),'Notes')]/parent::div//p[contains(@class,'count')]")
	public MobileElement DocumentNotesCnt;
		
	@FindBy(xpath = "//p[contains(text(),'Documents')]")
	public MobileElement DocumentTile;
	
	// Allergy
	@FindBy(xpath = "//p[contains(text(),'Allerg')]/parent::div/following-sibling::div/p")
	public MobileElement SummaryAllergyCount;
	
	@FindBy(xpath = "//p[@class='title spacing' and contains(text(),'Allerg')]")
	public MobileElement SummaryAllergyName;

	// Encounters
	@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Encounters')]")
	public MobileElement tab_Encounters;
	
	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']")
	public MobileElement summaryAllergytext;
	
	@FindBy(xpath ="//p[contains(@class,'title') and contains(text(),'Allergies')]/ancestor::ion-card//p[contains(@class,'details')]")
	public MobileElement allergyEnhancementText;
	
	
	//Forms
	
		@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[1]")
		public MobileElement SummaryForm1Name;
		
		@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[1]/following-sibling::span//div[contains(text(),'Created On')]")
		public MobileElement SummaryForm1CreatedOn;

		@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[1]/following-sibling::span//div[contains(text(),'Modified On')]")
		public MobileElement SummaryForm1ModifiedOn;
		
		
		@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[2]")
		public MobileElement SummaryForm2Name;
		
		@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[2]/following-sibling::span//div[contains(text(),'Created On')]")
		public MobileElement SummaryForm2CreatedOn;
		
		@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[2]/following-sibling::span//div[contains(text(),'Modified On')]")
		public MobileElement SummaryForm2ModifiedOn;
		
		
		@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[3]")
		public MobileElement SummaryForm3Name;
		
		@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[3]/following-sibling::span//div[contains(text(),'Created On')]")
		public MobileElement SummaryForm3CreatedOn;
		
		@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[3]/following-sibling::span//div[contains(text(),'Modified On')]")
		public MobileElement SummaryForm3ModifiedOn;	
		
		@FindBy(xpath ="//DXC-PATIENT-BANNER//ion-col[contains(@class,'pat-name')]")
		public MobileElement getPatientFullnameBanner;

		@FindBy(xpath ="//DXC-PATIENT-BANNER//span[contains(text(),'NHS')]/following-sibling::span")
		public MobileElement getPatientNHSNumberBanner;

		@FindBy(xpath ="//DXC-PATIENT-BANNER//span[contains(text(),'Born')]/following-sibling::span")
		public MobileElement getPatientDOBBanner;
		
		@FindBy(xpath ="//p[contains(text(),'Results')]/ancestor::div[@class='header']//span[contains(text(),'Last')]")
		public MobileElement SummaryResultsDateFilter;
		

	
	// **************************************************** WEB PAS ************************************************
	
		@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'HRN')]//following::span[1]")
		public MobileElement getHRNInBanner;
			
		@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'DoB')]//following::span[1]")
		public MobileElement getDoBInBanner;
			
		@FindBy(xpath ="//ion-icon[@name='flower']/ancestor::ion-card-content//p[@class='title spacing']")
		public MobileElement getSummAllergyName;
			
		@FindBy(xpath = "//p[contains(text(),'Clinical Documents')]/parent::div/following-sibling::div//p[contains(@class,'count')]")
		public MobileElement DocumentCntWebPAS;
		
		@FindBy(xpath = "//p[contains(text(),'Clinical Documents')]/parent::div//span[@class='alignis']")
		public MobileElement DocumentDisplayPeriodWebPAS;
		
		@FindBy(xpath = "//p[contains(text(),'Clinical Documents')]/parent::div//ion-icon")
		public MobileElement DocumentIconWebPAS;
		
		@FindBy(xpath = "(//p[contains(text(),'Clinical Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[1]")
		public MobileElement DocumentSummaryWebPAS;

		@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Clinical Documents')]")
		public MobileElement tab_WebPASDocuments;
	
		@FindBy(xpath = "(//p[contains(text(),'Clinical Documents')]/ancestor::div[@class='header']/following-sibling::div//p)")
		public MobileElement AccessRescDocument;
		
		@FindBy(xpath = "(//dxc-patient-banner//ion-icon)[2]")
		public MobileElement getBannerAlertIcon;
		
		@FindBy(xpath = "//dxc-patient-banner//ion-icon[contains(@name,'alert')]/following-sibling::span")
		public MobileElement getBannerAlertCount;
		
		@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'Gender')]//following::span[1]")
		public MobileElement getGenderInBanner;
		
		@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'Died')]//following::span[1]")
		public MobileElement getDiedDateInBanner;
		
		@FindBy(xpath ="//dxc-patient-banner//ion-row[contains(@class,'deceased')]")
		public MobileElement getBannerColor;
		
		@FindBy(xpath ="//p[contains(text(),'Results')]/parent::div//ion-icon")
		public MobileElement ResultIconWebPAS;
		
		@FindBy(xpath = "//p[contains(text(),'Results')]/parent::div//span[@class='alignis']")
		public MobileElement ResultDisplayPeriodWebPAS;
		
		@FindBy(xpath = "//p[contains(text(),'Results')]/parent::div/following-sibling::div//p[contains(@class,'count')]")
		public MobileElement ResultCntWebPAS;
		
		@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Results')]")
		public MobileElement tab_WebPASResults;
		
		@FindBy(xpath = "//div[contains(text(),'Read')]/ancestor::div[@class='left']/following-sibling::div[contains(@class,'icon')]//ion-icon")
		public MobileElement Results_ReadIcon;
		
		@FindBy(xpath = "//div[contains(text(),'Read')]/ancestor::div[@class='left']/following-sibling::div[contains(@class,'count')]")
		public MobileElement Results_ReadCount;
		
		@FindBy(xpath = "//div[contains(text(),'Unread')]/ancestor::div[@class='left']/following-sibling::div[contains(@class,'icon')]//ion-icon")
		public MobileElement Results_UnreadIcon;
		
		@FindBy(xpath = "//div[contains(text(),'Unread')]/ancestor::div[@class='left']/following-sibling::div[contains(@class,'count')]")
		public MobileElement Results_UnreadCount;
		
		@FindBy(xpath = "//div[contains(text(),'Pending')]/ancestor::div[@class='left']/following-sibling::div[contains(@class,'count')]")
		public MobileElement Results_PendingOrdCount;
		
		@FindBy(xpath ="//p[contains(text(),'Results')]/ancestor::div[@class='header']//p[contains(@class,'count')]")
		public MobileElement SummaryResultsTotalCount;
}
