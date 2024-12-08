package com.dedalus.capages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.CAProjectMethods;
import com.dedalus.genericappmethods.GenericAppMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ConsentsPage extends CAProjectMethods {
	JavascriptExecutor js = driver;

	public ConsentsPage(AppiumDriver<MobileElement> driver, ExtentTest test) {
		this.test = test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	//1. class equals --->> class contains title

	@FindBy(xpath  = "//div/ion-icon[@name='newspaper']/following::span[text()='Consents']")
	public MobileElement ClickconsentsTab;

	//Summary

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Consents']")
	public MobileElement summaryConsenttext;

	@FindBy(xpath ="//p[contains(@class,'title') and text()='Consents']/parent::div/following-sibling::div/p")
	public MobileElement getsummaryConsentsCount;

	@FindBy(xpath  = "//p[contains(@class,'title') and text()='Consents']/ancestor::div[@class='left']/parent::div/following-sibling::div[1]/child::div[1]//p")
	public MobileElement getsummaryconsent1Name;

	@FindBy(xpath  = "//p[contains(@class,'title') and text()='Consents']/ancestor::div[@class='left']/parent::div/following-sibling::div[1]/child::div[1]/child::div[1]//p/following-sibling::div[1]/span[1]/div")
	public MobileElement getsummaryconsent1Given;

	@FindBy(xpath  = "//p[contains(@class,'title') and text()='Consents']/ancestor::div[@class='left']/parent::div/following-sibling::div[1]/child::div[1]/child::div[1]//p/following-sibling::div[1]/span[2]/div")
	public MobileElement getsummaryconsent1DateObtained;
	
	@FindBy(xpath  = "//p[contains(@class,'title') and text()='Consents']/ancestor::div[@class='left']/parent::div/following-sibling::div[1]/child::div[2]//p")
	public MobileElement getsummaryconsent2Name;

	@FindBy(xpath  = "//p[contains(@class,'title') and text()='Consents']/ancestor::div[@class='left']/parent::div/following-sibling::div[1]/child::div[2]/child::div[1]//p/following-sibling::div[1]/span[1]/div")
	public MobileElement getsummaryconsent2Given;

	@FindBy(xpath  = "//p[contains(@class,'title') and text()='Consents']/ancestor::div[@class='left']/parent::div/following-sibling::div[1]/child::div[2]/child::div[1]//p/following-sibling::div[1]/span[2]/div")
	public MobileElement getsummaryconsent2DateObtained;
	
	
	@FindBy(xpath = "//ion-toggle/input")
	public MobileElement toggle;

	public int getAllConsentsCount(){ // Name modifcation
		List<MobileElement> allOptions = driver.findElements(By.xpath("//dxc-consent-list-item//ion-card/ion-card-header"));
		int valueresult1=allOptions.size();
		//String result=String.valueOf(valueresult1);
		return valueresult1;
	}


	@FindBy(xpath ="//ion-label[contains(text(),'Active')]/parent::ion-segment-button")
	public MobileElement clickActiveTab;

	@FindBy(xpath ="//ion-label[contains(text(),'All')]/parent::ion-segment-button")
	public MobileElement clickAllTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Inactive')]/parent::ion-segment-button")
	public MobileElement clickInactiveTab;

	@FindBy(xpath ="//ion-badge[text()='Draft']/ancestor::ion-item//ion-icon")
	public MobileElement expandDraftConsent;

	@FindBy(xpath ="//ion-badge[text()='Active']/ancestor::ion-item//ion-icon")
	public MobileElement expandActiveConsent;

	@FindBy(xpath ="//ion-badge[text()='Inactive']/ancestor::ion-item//ion-icon")
	public MobileElement expandInactiveConsent;

	@FindBy(xpath ="//ion-badge[text()='Entered-in-error']/ancestor::ion-item//ion-icon")
	public MobileElement expandStruckoutConsent;
	
	@FindBy(xpath ="//ion-icon[@name='chevron-down-outline']")
	public MobileElement collapseConsent;
	
	
	
	
	@FindBy(xpath ="//DXC-PATIENT-BANNER//ion-col[contains(@class,'pat-name')]")
	public MobileElement getPatientFullnameBanner;

	@FindBy(xpath ="//DXC-PATIENT-BANNER//span[contains(text(),'NHS')]/following-sibling::span")
	public MobileElement getPatientNHSNumberBanner;

	@FindBy(xpath ="//DXC-PATIENT-BANNER//span[contains(text(),'Born')]/following-sibling::span")
	public MobileElement getPatientDOBBanner;
	
	
	//Draft consent

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-item//H2/span")
	public MobileElement getDraftConsentName;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-item//span[contains(text(),'Consent given')]/following-sibling::span")
	public MobileElement getDraftConsentgiven;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-item//span[contains(text(),'Date obtained ')]/following-sibling::span")
	public MobileElement getDraftConsentDateObtained;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::dxc-expandable-item//ion-card-content//span[@class='heading']")
	public MobileElement getDraftConsentFormName;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-card//span[contains(text(),'Consent for')]/following-sibling::span")
	public MobileElement getDraftConsentFor;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-card//span[contains(text(),'Given by')]/following-sibling::span")
	public MobileElement getDraftConsentGivenBy;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-card//span[contains(text(),'Consent value')]/following-sibling::span")
	public MobileElement getDraftConsentValue;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-card//span[contains(text(),'Encounter')]/following-sibling::span")
	public MobileElement getDraftConsentEncID;

	@FindBy(xpath  = "//ion-badge[text()='Draft']/ancestor::ion-card//span[contains(text(),'Consenter name')]/following-sibling::span")
	public MobileElement getDraftConsenterName;
	
	
	@FindBy(xpath  = "//*[contains(text(),'Comments')]/following-sibling::p")
	public MobileElement getConsentComments;

	@FindBy(xpath  = "//*[contains(text(),'Draft')]/ancestor::dxc-consent-list-item//*[contains(text(),'View')]/ion-icon")
	public MobileElement clicktoViewDraftForm;
	
	@FindBy(xpath  = "//*[contains(text(),'Active')]/ancestor::dxc-consent-list-item//*[contains(text(),'View')]/ion-icon")
	public MobileElement clicktoViewActiveForm;
	
	@FindBy(xpath  = "//*[contains(text(),'Inactive')]/ancestor::dxc-consent-list-item//*[contains(text(),'View')]/ion-icon")
	public MobileElement clicktoViewInactiveForm;
	
	@FindBy(xpath  = "//*[contains(text(),'Entered-in-error')]/ancestor::dxc-consent-list-item//*[contains(text(),'View')]/ion-icon")
	public MobileElement clicktoViewStrikeoutForm;
	
	@FindBy(xpath  = "//ion-icon[@name='close-circle']/parent::ion-button")
	public MobileElement closeConsentForm;


	//Active consent

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-item//H2/span")
	public MobileElement getActiveConsentName;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-item//span[contains(text(),'Consent given')]/following-sibling::span")
	public MobileElement getActiveConsentgiven;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-item//span[contains(text(),'Date obtained ')]/following-sibling::span")
	public MobileElement getActiveConsentDateObtained;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::dxc-expandable-item//ion-card-content//span[@class='heading']")
	public MobileElement getActiveConsentFormName;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Consent for:')]/following-sibling::span")
	public MobileElement getActiveConsentFor;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Given by:')]/following-sibling::span")
	public MobileElement getActiveConsentGivenBy;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Consent value:')]/following-sibling::span")
	public MobileElement getActiveConsentValue;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Encounter ID')]/following-sibling::span")
	public MobileElement getActiveConsentEncID;

	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Consenter name:')]/following-sibling::span")
	public MobileElement getActiveConsenterName;
	


	//Inactive consent

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-item//H2/span")
	public MobileElement getInactiveConsentName;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-item//span[contains(text(),'Consent given')]/following-sibling::span")
	public MobileElement getInactiveConsentgiven;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-item//span[contains(text(),'Date obtained ')]/following-sibling::span")
	public MobileElement getInactiveConsentDateObtained;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::dxc-expandable-item//ion-card-content//span[@class='heading']")
	public MobileElement getInactiveConsentFormName;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Consent for:')]/following-sibling::span")
	public MobileElement getInactiveConsentFor;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Given by:')]/following-sibling::span")
	public MobileElement getInactiveConsentGivenBy;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Consent value:')]/following-sibling::span")
	public MobileElement getInactiveConsentValue;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Encounter ID')]/following-sibling::span")
	public MobileElement getInactiveConsentEncID;

	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Consenter name:')]/following-sibling::span")
	public MobileElement getInactiveConsenterName;
	

	//Struckout consent

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-item//H2/span")
	public MobileElement getStruckoutConsentName;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-item//span[contains(text(),'Consent given')]/following-sibling::span")
	public MobileElement getStruckoutConsentgiven;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-item//span[contains(text(),'Date obtained')]/following-sibling::span")
	public MobileElement getStruckoutConsentDateObtained;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::dxc-expandable-item//ion-card-content//span[@class='heading']")
	public MobileElement getStruckoutConsentFormName;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-card//span[contains(text(),'Consent for')]/following-sibling::span")
	public MobileElement getStruckoutConsentFor;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-card//span[contains(text(),'Given by')]/following-sibling::span")
	public MobileElement getStruckoutConsentGivenBy;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-card//span[contains(text(),'Consent value')]/following-sibling::span")
	public MobileElement getStruckoutConsentValue;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-card//span[contains(text(),'Encounter ID')]/following-sibling::span")
	public MobileElement getStruckoutConsentEncID;

	@FindBy(xpath  = "//ion-badge[text()='Entered-in-error']/ancestor::ion-card//span[contains(text(),'Consenter name')]/following-sibling::span")
	public MobileElement getStruckoutConsenterName;
	

	
	//PHQ 9 Depression Form
	
	@FindBy(xpath  = "//font[contains(text(),'1. Little interest or pleasure in doing things')]/parent::td/following-sibling::td/font")
	public MobileElement getLittleinterest;

	@FindBy(xpath  = "//font[contains(text(),'2. Feeling down')]/parent::td/following-sibling::td/font")
	public MobileElement getFeelingdown;

	@FindBy(xpath  = "//font[contains(text(),'3. Trouble falling')]/parent::td/following-sibling::td/font")
	public MobileElement getTroublefalling;

	@FindBy(xpath  = "//font[contains(text(),'4. Feeling tired or having little energy')]/parent::td/following-sibling::td/font")
	public MobileElement getFeelingtired;

	@FindBy(xpath  = "//font[contains(text(),'5. Poor appetite or overeating')]/parent::td/following-sibling::td/font")
	public MobileElement getPoorappetite;

	@FindBy(xpath  = "//font[contains(text(),'6. Feeling bad about yourself ? or that you are a failure or have let yourself or your family down')]/parent::td/following-sibling::td/font")
	public MobileElement getFeelingbad;

	@FindBy(xpath  = "//font[contains(text(),'7. Trouble concentrating on things, such as reading the newspaper or watching television')]/parent::td/following-sibling::td/font")
	public MobileElement getTroubleconcentrating;

	@FindBy(xpath  = "//font[contains(text(),'8. Moving or speaking so slowly that other people could have noticed? Or the opposite ? being so fidgety or restless that you have been moving around a lot more than usual')]/parent::td/following-sibling::td/font")
	public MobileElement getMovingorspeaking;

	@FindBy(xpath  = "//font[contains(text(),'9. Thoughts that you would be better off dead or of hurting yourself in some way')]/parent::td/following-sibling::td/font")
	public MobileElement getThoughtsthatyouwould;

	@FindBy(xpath  = "//font[contains(text(),'PHQ9 Score')]/parent::td/following-sibling::td/font")
	public MobileElement getPHQ9Score;

	@FindBy(xpath  = "//font[contains(text(),'How difficult have these problems made it for you to do your work, take care of things at home, or get along with other people?')]/parent::td/following-sibling::td/font")
	public MobileElement getHowdifficulthave;
	
	@FindBy(xpath  ="//ion-icon[contains(@class,'close')]")	
	public MobileElement clickClose;
	//--------------------------------------------------------------------------------------




}



/*
@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question' and contains(text(),'Problem')]/following-sibling::span")
public List<MobileElement> getFormProblem;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'History')]/following-sibling::span")
public List<MobileElement> getFormHistory;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'Plan')]/following-sibling::span")
public List<MobileElement> getFormPlan;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'To do')]/following-sibling::span")
public List<MobileElement> getFormToDo;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'VTE')]/following-sibling::span")
public List<MobileElement> getFormVTE;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'TTO')]/following-sibling::span")
public List<MobileElement> getFormTTO;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'Handover To')]/following-sibling::span")
public List<MobileElement> getFormHandoverTo;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'Handover Received From')]/following-sibling::span")
public List<MobileElement> getFormHandoverReceivedFrom;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'Comments')]/following-sibling::span")
public List<MobileElement> getFormComments;

@FindBy(xpath  = "//span[@class='ion-text-wrap ion-padding-start assessment-question'][contains(text(),'Comments')]/following-sibling::span")
public MobileElement getFormComments1; 



// @FindBy(xpath  = "//ion-button[@class='close-form-button md button button-clear ion-activatable ion-focusable hydrated']")



@FindBy(how=How.XPATH,using="//div[@class='alert-radio-label'][contains(text(),'Consents')]")
public MobileElement ClickConsentsRadioButton;

public ConsentsPage clickConsentsRadioButton() throws InterruptedException {
	Thread.sleep(5000);
	javascriptClick(ClickConsentsRadioButton);
	MobileElement ele=driver.findElement(By.xpath("//span[contains(text(),'OK')]"));
	javascriptClick(ele);
	takeSnap();
	return this;
}*/






















