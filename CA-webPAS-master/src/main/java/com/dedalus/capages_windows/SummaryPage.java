package com.dedalus.capages_windows;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods_windows.CAProjectMethods;

public class SummaryPage extends CAProjectMethods {
	
	public SummaryPage(RemoteWebDriver driver,ExtentTest test) {
		this.test = test;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//ion-col[contains(@class,'pat-name')]")
	public WebElement getPatientNameInBanner;
	
	@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'NHS')]//following::span[1]")
	public WebElement getNHSNoInBanner;
	
	@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'Born')]//following::span[1]")
	public WebElement getDOBInBanner;
	
	//Summary
//	@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Summary')]")
//	public WebElement tab_Summary;
	
	@FindBy(xpath = "//div/ion-icon[@name='home']")
	public WebElement tab_Summary;
	
	// Alert
	@FindBy(xpath = "//p[contains(text(),'Alerts')]/parent::div//ion-icon")
	public WebElement AlertIcon;
	
	@FindBy(xpath = "//p[contains(text(),'Alerts')]/parent::div/following-sibling::div/p")
	public WebElement SummaryAlertsCount;
	
	@FindBy(xpath = "(//p[contains(text(),'Alerts')]/parent::div/parent::div/following-sibling::div//p[contains(@class,'title')])[1]")
	public WebElement getSumAlertName1;

	@FindBy(xpath = "(//p[contains(text(),'Alerts')]/parent::div/parent::div/following-sibling::div//span/div)[1]")
	public WebElement getSumAlertType1;
	
	@FindBy(xpath = "(//p[contains(text(),'Alerts')]/parent::div/parent::div/following-sibling::div//span/div)[2]")
	public WebElement getSumAlertOnsetDate1;
	
	@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Alerts')]")
	public WebElement tab_Alerts;
	
	// Procedures
	@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Procedure')]")
	public WebElement tab_Procedures;
	
	@FindBy(xpath = "//p[contains(text(),'Procedures')]/parent::div//ion-icon")
	public WebElement ProcedureIcon;
	
	@FindBy(xpath = "//p[contains(text(),'Procedures')]/ancestor::ion-card-content//p[@class='details']")
	public WebElement PlanProcCntSummary;
	
	//Advance Directive
	@FindBy(xpath = "//p[contains(text(),'Advance Directive')]/parent::div//ion-icon")
	public WebElement AdvDirIcon;
	
	@FindBy(xpath = "//p[contains(text(),'Advance Dir')]/parent::div/following-sibling::div/p")
	public WebElement SummaryAdvDirCount;
	
	// Documents
	@FindBy(xpath = "(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[1]")
	public WebElement DocumentSummary;
	
	@FindBy(xpath = "//p[contains(text(),'Documents')]/parent::div/following-sibling::div/p")
	public WebElement SummaryDocumentCount;
	
	@FindBy(xpath = "//p[contains(text(),'Documents')]/parent::div//ion-icon")
	public WebElement DocumentIcon;

	@FindBy(xpath = "//p[contains(text(),'Documents')]/parent::div//span[@class='alignis']")
	public WebElement DocumentDisplayPeriod;
	
	@FindBy(xpath = "//*[contains(text(),'Forms')]/parent::div//p[contains(@class,'count')]")
	public WebElement DocumentFormsCnt;
	
	@FindBy(xpath = "//*[contains(text(),'Notes')]/parent::div//p[contains(@class,'count')]")
	public WebElement DocumentNotesCnt;
	
	@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Documents')]")
	public WebElement tab_Documents;
		
	// Encounters
	@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Encounters')]")
	public WebElement tab_Encounters;
		
	// Allergy
	@FindBy(xpath = "//p[@class='title spacing' and contains(text(),'Allerg')]")
	public WebElement SummaryAllergyName;
	
	@FindBy(xpath = "//p[contains(text(),'Allerg')]/parent::div/following-sibling::div/p")
	public WebElement SummaryAllergyCount;
	
	@FindBy(xpath ="//p[contains(@class,'title') and text()='Allergies / Intolerances']")
	public WebElement summaryAllergytext;
	
	@FindBy(xpath ="//p[contains(@class,'title') and contains(text(),'Allergies')]/ancestor::ion-card//p[contains(@class,'details')]")
	public WebElement allergyEnhancementText;
	
	@FindBy(xpath = "//p[contains(text(),'Documents')]")
	public WebElement DocumentTile;
	
	@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[1]")
	public WebElement SummaryForm1Name;
	
	@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[1]/following-sibling::span//div[contains(text(),'Created On')]")
	public WebElement SummaryForm1CreatedOn;

	@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[1]/following-sibling::span//div[contains(text(),'Modified On')]")
	public WebElement SummaryForm1ModifiedOn;
	
	
	@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[2]")
	public WebElement SummaryForm2Name;
	
	@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[2]/following-sibling::span//div[contains(text(),'Created On')]")
	public WebElement SummaryForm2CreatedOn;
	
	@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[2]/following-sibling::span//div[contains(text(),'Modified On')]")
	public WebElement SummaryForm2ModifiedOn;
		
	@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[3]")
	public WebElement SummaryForm3Name;
	
	@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[3]/following-sibling::span//div[contains(text(),'Created On')]")
	public WebElement SummaryForm3CreatedOn;
	
	@FindBy(xpath ="(//p[contains(text(),'Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[3]/following-sibling::span//div[contains(text(),'Modified On')]")
	public WebElement SummaryForm3ModifiedOn;

	// **************************************************** WEB PAS ************************************************
	
	@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'HRN')]//following::span[1]")
	public WebElement getHRNInBanner;
		
	@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'DoB')]//following::span[1]")
	public WebElement getDoBInBanner;
		
	@FindBy(xpath ="//p[@class='title' and contains(text(),'Allerg')]/ancestor::ion-card-content//p[@class='title spacing']")
	public WebElement getSummAllergyName;
		
	@FindBy(xpath = "//p[contains(text(),'Clinical Documents')]/parent::div/following-sibling::div//p[contains(@class,'count')]")
	public WebElement DocumentCntWebPAS;
	
	@FindBy(xpath = "//p[contains(text(),'Clinical Documents')]/parent::div//span[@class='alignis']")
	public WebElement DocumentDisplayPeriodWebPAS;
	
	@FindBy(xpath = "//p[contains(text(),'Clinical Documents')]/parent::div//ion-icon")
	public WebElement DocumentIconWebPAS;
	
	@FindBy(xpath = "(//p[contains(text(),'Clinical Documents')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[1]")
	public WebElement DocumentSummaryWebPAS;

	@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Clinical Documents')]")
	public WebElement tab_WebPASDocuments;
	
	@FindBy(xpath = "(//p[contains(text(),'Clinical Documents')]/ancestor::div[@class='header']/following-sibling::div//p)")
	public WebElement AccessRescDocument;
	
	@FindBy(xpath = "(//dxc-patient-banner//ion-icon)[2]")
	public WebElement getBannerAlertIcon;
	
	@FindBy(xpath = "//dxc-patient-banner//ion-icon[contains(@name,'alert')]/following-sibling::span")
	public WebElement getBannerAlertCount;
	
	@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'Gender')]//following::span[1]")
	public WebElement getGenderInBanner;
	
	@FindBy(xpath ="//span[@class='label-txt' and contains(text(),'Died')]//following::span[1]")
	public WebElement getDiedDateInBanner;
	
	@FindBy(xpath ="//dxc-patient-banner//ion-row[contains(@class,'deceased')]")
	public WebElement getBannerColor;

	@FindBy(xpath ="//p[contains(text(),'Results')]/parent::div//ion-icon")
	public WebElement ResultIconWebPAS;
	
	@FindBy(xpath = "//p[contains(text(),'Results')]/parent::div//span[@class='alignis']")
	public WebElement ResultDisplayPeriodWebPAS;
	
	@FindBy(xpath = "//p[contains(text(),'Results')]/parent::div/following-sibling::div//p[contains(@class,'count')]")
	public WebElement ResultCntWebPAS;
	
	@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Results')]")
	public WebElement tab_WebPASResults;
	
	@FindBy(xpath = "//div[@class='record']//div[contains(text(),'Read')]/ancestor::div[@class='left']/following-sibling::div[contains(@class,'icon')]//ion-icon")
	public WebElement Results_ReadIcon;
	
	@FindBy(xpath = "//div[@class='record']//div[contains(text(),'Read')]/ancestor::div[@class='left']/following-sibling::div[contains(@class,'count')]")
	public WebElement Results_ReadCount;
	
	@FindBy(xpath = "//div[@class='record']//div[contains(text(),'Unread')]/ancestor::div[@class='left']/following-sibling::div[contains(@class,'icon')]//ion-icon")
	public WebElement Results_UnreadIcon;
	
	@FindBy(xpath = "//div[@class='record']//div[contains(text(),'Unread')]/ancestor::div[@class='left']/following-sibling::div[contains(@class,'count')]")
	public WebElement Results_UnreadCount;
	
	@FindBy(xpath = "//div[@class='record']//div[contains(text(),'Pending')]/ancestor::div[@class='left']/following-sibling::div[contains(@class,'count')]")
	public WebElement Results_PendingOrdCount;

	@FindBy(xpath ="//p[contains(text(),'Results')]/ancestor::div[@class='header']//p[contains(@class,'count')]")
	public WebElement SummaryResultsTotalCount;
	
	@FindBy(xpath ="//p[contains(text(),'Results')]/ancestor::div[@class='header']//span[contains(text(),'Last')]")
	public WebElement SummaryResultsDateFilter;
	
}
