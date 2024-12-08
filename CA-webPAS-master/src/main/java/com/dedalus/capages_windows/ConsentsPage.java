package com.dedalus.capages_windows;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods_windows.CAProjectMethods;


	public class ConsentsPage extends CAProjectMethods {
		public ConsentsPage(RemoteWebDriver driver, ExtentTest test) {
			this.test = test;
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	//1. class equals --->> class contains title
		//test

	@FindBy(xpath  = "//div/ion-icon[@name='newspaper']/following::span[text()='Consents']")
	public WebElement ClickconsentsTab;

	//Summary

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Consents']")
	public WebElement summaryConsenttext;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Consents']/parent::div/following-sibling::div/p")
	public WebElement getsummaryConsentsCount;

	@FindBy(xpath  = "//p[contains(@class,'title') and text()='Consents']/ancestor::div[@class='left']/parent::div/following-sibling::div[1]/child::div[1]//p")
	public WebElement getsummaryconsent1Name;

	@FindBy(xpath  = "//p[contains(@class,'title') and text()='Consents']/ancestor::div[@class='left']/parent::div/following-sibling::div[1]/child::div[1]/child::div[1]//p/following-sibling::div[1]/span[1]/div")
	public WebElement getsummaryconsent1Given;

	@FindBy(xpath  = "//p[contains(@class,'title') and text()='Consents']/ancestor::div[@class='left']/parent::div/following-sibling::div[1]/child::div[1]/child::div[1]//p/following-sibling::div[1]/span[2]/div")
	public WebElement getsummaryconsent1DateObtained;
	
	@FindBy(xpath  = "//p[contains(@class,'title') and text()='Consents']/ancestor::div[@class='left']/parent::div/following-sibling::div[1]/child::div[2]//p")
	public WebElement getsummaryconsent2Name;

	@FindBy(xpath  = "//p[contains(@class,'title') and text()='Consents']/ancestor::div[@class='left']/parent::div/following-sibling::div[1]/child::div[2]/child::div[1]//p/following-sibling::div[1]/span[1]/div")
	public WebElement getsummaryconsent2Given;

	@FindBy(xpath  = "//p[contains(@class,'title') and text()='Consents']/ancestor::div[@class='left']/parent::div/following-sibling::div[1]/child::div[2]/child::div[1]//p/following-sibling::div[1]/span[2]/div")
	public WebElement getsummaryconsent2DateObtained;
	
	
	@FindBy(xpath = "//ion-toggle/input")
	public WebElement toggle;

	public int getAllConsentsCount(){ // Name modifcation
		List<WebElement> allOptions = driver.findElements(By.xpath("//dxc-consent-list-item//ion-card/ion-card-header"));
		int valueresult1=allOptions.size();
		//String result=String.valueOf(valueresult1);
		return valueresult1;
	}


	@FindBy(xpath ="//ion-label[contains(text(),'Active')]/parent::ion-segment-button")
	public WebElement clickActiveTab;

	@FindBy(xpath ="//ion-label[contains(text(),'All')]/parent::ion-segment-button")
	public WebElement clickAllTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Inactive')]/parent::ion-segment-button")
	public WebElement clickInactiveTab;

	@FindBy(xpath ="//ion-badge[text()='Draft']/ancestor::ion-item//ion-icon")
	public WebElement expandDraftConsent;

	@FindBy(xpath ="//ion-badge[text()='Active']/ancestor::ion-item//ion-icon")
	public WebElement expandActiveConsent;

	@FindBy(xpath ="//ion-badge[text()='Inactive']/ancestor::ion-item//ion-icon")
	public WebElement expandInactiveConsent;

	@FindBy(xpath ="//ion-badge[text()='Entered-in-error']/ancestor::ion-item//ion-icon")
	public WebElement expandStruckoutConsent;
	
	@FindBy(xpath ="//ion-icon[@name='chevron-down-outline']")
	public WebElement collapseConsent;
	
	
	
	
	@FindBy(xpath ="//DXC-PATIENT-BANNER//ion-col[contains(@class,'pat-name')]")
	public WebElement getPatientFullnameBanner;

	@FindBy(xpath ="//DXC-PATIENT-BANNER//span[contains(text(),'NHS')]/following-sibling::span")
	public WebElement getPatientNHSNumberBanner;

	@FindBy(xpath ="//DXC-PATIENT-BANNER//span[contains(text(),'Born')]/following-sibling::span")
	public WebElement getPatientDOBBanner;
	
	
	//Draft consent

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-item//H2/span")
	public WebElement getDraftConsentName;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-item//span[contains(text(),'Consent given')]/following-sibling::span")
	public WebElement getDraftConsentgiven;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-item//span[contains(text(),'Date obtained ')]/following-sibling::span")
	public WebElement getDraftConsentDateObtained;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::dxc-expandable-item//ion-card-content//span[@class='heading']")
	public WebElement getDraftConsentFormName;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-card//span[contains(text(),'Consent for')]/following-sibling::span")
	public WebElement getDraftConsentFor;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-card//span[contains(text(),'Given by')]/following-sibling::span")
	public WebElement getDraftConsentGivenBy;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-card//span[contains(text(),'Consent value')]/following-sibling::span")
	public WebElement getDraftConsentValue;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-card//span[contains(text(),'Encounter')]/following-sibling::span")
	public WebElement getDraftConsentEncID;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-card//span[contains(text(),'Consenter name')]/following-sibling::span")
	public WebElement getDraftConsenterName;
	
	
	@FindBy(xpath  = "//*[contains(text(),'Comments')]/following-sibling::p")
	public WebElement getConsentComments;

	@FindBy(xpath  = "//*[contains(text(),'Draft')]/ancestor::dxc-consent-list-item//*[contains(text(),'View')]/ion-icon")
	public WebElement clicktoViewDraftForm;
	
	@FindBy(xpath  = "//*[contains(text(),'Active')]/ancestor::dxc-consent-list-item//*[contains(text(),'View')]/ion-icon")
	public WebElement clicktoViewActiveForm;
	
	@FindBy(xpath  = "//*[contains(text(),'Inactive')]/ancestor::dxc-consent-list-item//*[contains(text(),'View')]/ion-icon")
	public WebElement clicktoViewInactiveForm;
	
	@FindBy(xpath  = "//*[contains(text(),'Entered-in-error')]/ancestor::dxc-consent-list-item//*[contains(text(),'View')]/ion-icon")
	public WebElement clicktoViewStrikeoutForm;
	
	@FindBy(xpath  = "//ion-icon[@name='close-circle']/parent::ion-button")
	public WebElement closeConsentForm;


	//Active consent

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-item//H2/span")
	public WebElement getActiveConsentName;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-item//span[contains(text(),'Consent given')]/following-sibling::span")
	public WebElement getActiveConsentgiven;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-item//span[contains(text(),'Date obtained ')]/following-sibling::span")
	public WebElement getActiveConsentDateObtained;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::dxc-expandable-item//ion-card-content//span[@class='heading']")
	public WebElement getActiveConsentFormName;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Consent for:')]/following-sibling::span")
	public WebElement getActiveConsentFor;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Given by:')]/following-sibling::span")
	public WebElement getActiveConsentGivenBy;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Consent value:')]/following-sibling::span")
	public WebElement getActiveConsentValue;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Encounter ID')]/following-sibling::span")
	public WebElement getActiveConsentEncID;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Consenter name:')]/following-sibling::span")
	public WebElement getActiveConsenterName;
	


	//Inactive consent

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-item//H2/span")
	public WebElement getInactiveConsentName;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-item//span[contains(text(),'Consent given')]/following-sibling::span")
	public WebElement getInactiveConsentgiven;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-item//span[contains(text(),'Date obtained ')]/following-sibling::span")
	public WebElement getInactiveConsentDateObtained;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::dxc-expandable-item//ion-card-content//span[@class='heading']")
	public WebElement getInactiveConsentFormName;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Consent for:')]/following-sibling::span")
	public WebElement getInactiveConsentFor;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Given by:')]/following-sibling::span")
	public WebElement getInactiveConsentGivenBy;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Consent value:')]/following-sibling::span")
	public WebElement getInactiveConsentValue;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Encounter ID')]/following-sibling::span")
	public WebElement getInactiveConsentEncID;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Consenter name:')]/following-sibling::span")
	public WebElement getInactiveConsenterName;
	

	//Struckout consent

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-item//H2/span")
	public WebElement getStruckoutConsentName;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-item//span[contains(text(),'Consent given')]/following-sibling::span")
	public WebElement getStruckoutConsentgiven;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-item//span[contains(text(),'Date obtained')]/following-sibling::span")
	public WebElement getStruckoutConsentDateObtained;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::dxc-expandable-item//ion-card-content//span[@class='heading']")
	public WebElement getStruckoutConsentFormName;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-card//span[contains(text(),'Consent for')]/following-sibling::span")
	public WebElement getStruckoutConsentFor;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-card//span[contains(text(),'Given by')]/following-sibling::span")
	public WebElement getStruckoutConsentGivenBy;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-card//span[contains(text(),'Consent value')]/following-sibling::span")
	public WebElement getStruckoutConsentValue;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-card//span[contains(text(),'Encounter ID')]/following-sibling::span")
	public WebElement getStruckoutConsentEncID;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-card//span[contains(text(),'Consenter name')]/following-sibling::span")
	public WebElement getStruckoutConsenterName;
	

	
	//PHQ 9 Depression Form
	
	@FindBy(xpath  = "//font[contains(text(),'1. Little interest or pleasure in doing things')]/parent::td/following-sibling::td/font")
	public WebElement getLittleinterest;

	@FindBy(xpath  = "//font[contains(text(),'2. Feeling down')]/parent::td/following-sibling::td/font")
	public WebElement getFeelingdown;

	@FindBy(xpath  = "//font[contains(text(),'3. Trouble falling')]/parent::td/following-sibling::td/font")
	public WebElement getTroublefalling;

	@FindBy(xpath  = "//font[contains(text(),'4. Feeling tired or having little energy')]/parent::td/following-sibling::td/font")
	public WebElement getFeelingtired;

	@FindBy(xpath  = "//font[contains(text(),'5. Poor appetite or overeating')]/parent::td/following-sibling::td/font")
	public WebElement getPoorappetite;

	@FindBy(xpath  = "//font[contains(text(),'6. Feeling bad about yourself ? or that you are a failure or have let yourself or your family down')]/parent::td/following-sibling::td/font")
	public WebElement getFeelingbad;

	@FindBy(xpath  = "//font[contains(text(),'7. Trouble concentrating on things, such as reading the newspaper or watching television')]/parent::td/following-sibling::td/font")
	public WebElement getTroubleconcentrating;

	@FindBy(xpath  = "//font[contains(text(),'8. Moving or speaking so slowly that other people could have noticed? Or the opposite ? being so fidgety or restless that you have been moving around a lot more than usual')]/parent::td/following-sibling::td/font")
	public WebElement getMovingorspeaking;

	@FindBy(xpath  = "//font[contains(text(),'9. Thoughts that you would be better off dead or of hurting yourself in some way')]/parent::td/following-sibling::td/font")
	public WebElement getThoughtsthatyouwould;

	@FindBy(xpath  = "//font[contains(text(),'PHQ9 Score')]/parent::td/following-sibling::td/font")
	public WebElement getPHQ9Score;

	@FindBy(xpath  = "//font[contains(text(),'How difficult have these problems made it for you to do your work, take care of things at home, or get along with other people?')]/parent::td/following-sibling::td/font")
	public WebElement getHowdifficulthave;
	
	@FindBy(xpath  ="//ion-icon[contains(@class,'close')]")	
	public WebElement clickClose;
	//--------------------------------------------------------------------------------------




}



/*
@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question' and contains(text(),'Problem')]/following-sibling::span")
public List<WebElement> getFormProblem;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'History')]/following-sibling::span")
public List<WebElement> getFormHistory;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'Plan')]/following-sibling::span")
public List<WebElement> getFormPlan;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'To do')]/following-sibling::span")
public List<WebElement> getFormToDo;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'VTE')]/following-sibling::span")
public List<WebElement> getFormVTE;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'TTO')]/following-sibling::span")
public List<WebElement> getFormTTO;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'Handover To')]/following-sibling::span")
public List<WebElement> getFormHandoverTo;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'Handover Received From')]/following-sibling::span")
public List<WebElement> getFormHandoverReceivedFrom;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'Comments')]/following-sibling::span")
public List<WebElement> getFormComments;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'Comments')]/following-sibling::span")
public WebElement getFormComments1; 



// @FindBy(xpath  = "//ion-button[@class='close-form-button md button button-clear ion-activatable ion-focusable hydrated']")



@FindBy(how=How.XPATH,using="//div[@class='alert-radio-label'][contains(text(),'Consents')]")
public WebElement ClickConsentsRadioButton;

public ConsentsPage clickConsentsRadioButton() throws InterruptedException {
	Thread.sleep(5000);
	javascriptClick(ClickConsentsRadioButton);
	WebElement ele=driver.findElement(By.xpath("//span[contains(text(),'OK')]"));
	javascriptClick(ele);
	takeSnap();
	return this;
}*/






















