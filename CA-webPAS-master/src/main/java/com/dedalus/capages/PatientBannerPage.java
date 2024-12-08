package com.dedalus.capages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.CAProjectMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class PatientBannerPage extends CAProjectMethods {
	public PatientBannerPage(AppiumDriver<MobileElement> driver, ExtentTest test) {
		this.test = test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath="//ion-button//span[contains(@class,'indent-decrease')]")
	public MobileElement IndentDecrease;
	
	@FindBy(xpath="//dxc-patient-banner//ion-icon[@name='alert-circle']/following-sibling::span")
	public MobileElement AlertCnt;
	
//	@FindBy(xpath="//dxc-patient-banner//ion-icon[contains(@name,'ellipsis')]")
//	public MobileElement MoreButton;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Alerts')]//following-sibling::ion-note[contains(@class,'count')]")
	public MobileElement AlertCntMore;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Alerts')]/parent::ion-item/following-sibling::div/div[1]/span")
	public MobileElement AlertName1More;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Alerts')]/parent::ion-item/following-sibling::div/div[2]/span")
	public MobileElement AlertName2More;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Alerts')]/parent::ion-item/following-sibling::div//ion-button[contains(text(),'details')]")
	public MobileElement AlertLinkMore;
	
    @FindBy(how=How.XPATH,using="//span[contains(text(),'NHS')]")
    public MobileElement clickPatient;

  @FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Search')]")
  public MobileElement Search;
 
    @FindBy(how = How.XPATH, using = "//ion-col[@class='ion-no-padding pat-name md hydrated']")
	public MobileElement PatientName;

    @FindBy(how=How.XPATH,using="//span[text()='Preferred Name']//following::span[1]")
   	public MobileElement PreferredName;

 // @FindBy(how = How.XPATH, using = "//ion-col[@class='ion-no-padding md hydrated']//span[text()='Born']//following::span[1]")
    @FindBy(how = How.XPATH, using = "//ion-col[contains(@class,'born')]//span[text()='Born']//following::span[1]")
   	public MobileElement PatientDoB;

    @FindBy(how=How.XPATH,using="//span[@class='label-txt' and contains(text(),'NHS')]//following::span[1]")
	public MobileElement PatNHS;

    @FindBy(how=How.XPATH,using="//dxc-more-detail-banner//following::div[@class='detail-content']//div[@class='prop-value'][1]")
	public MobileElement Address1;

    @FindBy(how=How.XPATH,using="//dxc-more-detail-banner//following::div[@class='detail-content']//div[@class='prop-value'][2]")
   	public MobileElement Address2;

    @FindBy(how=How.XPATH,using="//dxc-more-detail-banner//following::div[@class='detail-content']//div[@class='prop-value'][3]")
   	public MobileElement Address3;

    @FindBy(how=How.XPATH,using="//span[contains(text(),'Home')]//following-sibling::span")
   	public MobileElement ContactHome;

    @FindBy(how=How.XPATH,using="//span[contains(text(),'Mobile')]//following-sibling::span")
   	public MobileElement ContactMobile;

    @FindBy(how=How.XPATH,using="//span[contains(text(),'Work')]//following-sibling::span")
   	public MobileElement ContactWork;

 //   @FindBy(how=How.XPATH,using="//span[contains(text(),'Email')]//following-sibling::a")
 //  	public MobileElement ContactEmail;

    @FindBy(how=How.XPATH,using="//span[contains(text(),'Gender')]//following-sibling::span")
   	public MobileElement Gender;

    @FindBy(how=How.XPATH,using="//span[contains(text(),'PAS')]//following-sibling::span")
   	public MobileElement PAS;

  @FindBy(how = How.XPATH, using = "(//span[contains(text(),'NHS')]//following-sibling::span)[2]")
   	public MobileElement NHS;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Alerts')]/following::ion-note[@class='count ion-no-padding ion-no-margin md hydrated'])[1]")
   	public MobileElement AlertsCount;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Allergies')]/following::ion-note[@class='count ion-no-padding ion-no-margin md hydrated'])[1]")
   	public MobileElement AllergiesCount;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Advance Directives')]/following::ion-note[@class='count ion-no-padding ion-no-margin md hydrated'])[1]")
   	public MobileElement AdvanceDirectivesCount;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Preferences')]/following::ion-note[@class='count ion-no-padding ion-no-margin md hydrated'])[1]")
   	public MobileElement PreferencesCount;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Communication language')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][1]")
   	public MobileElement CommunicationLanguage;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Communication language')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][2]")
   	public MobileElement CommunicationLanguageDate;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Communication language')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][3]")
   	public MobileElement CommunicationLanguageActionable;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Communication method')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][1]")
   	public MobileElement CommunicationMethod;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Communication method')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][2]")
   	public MobileElement CommunicationMethodDate;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Communication method')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][3]")
   	public MobileElement CommunicationMethodActionable;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Transport needs')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][1]")
   	public MobileElement TransportNeeds;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Transport needs')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][2]")
   	public MobileElement TransportNeedsDate;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Transport needs')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][3]")
   	public MobileElement TransportNeedsActionable;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Allergies')]/following::ion-button[@class='md button button-small button-solid ion-activatable ion-focusable hydrated'])[1]")
   	public MobileElement ViewDetailsAllergies;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Alerts')]/following::ion-button[@class='md button button-small button-solid ion-activatable ion-focusable hydrated'])[1]")
   	public MobileElement ViewDetailsAlerts;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Advance Directives')]/following::ion-button[@class='md button button-small button-solid ion-activatable ion-focusable hydrated'])[1]")
   	public MobileElement AdvanceDirectiveViewDetails;
	
 // @FindBy(how = How.XPATH, using = "(//ion-icon[@aria-label='ellipsis vertical outline'])[2]")
  @FindBy(how = How.XPATH, using = "(//ion-icon[@name='ellipsis-vertical-outline'])[2]")
	public MobileElement MoreButton;

//	@FindBy(xpath = "//ion-label[contains(text(),'Allergies / Intolerances')]//following-sibling::ion-note[contains(@class,'count')]")
//	public MobileElement AllergyCntMore;
	
//	@FindBy(xpath = "//ion-label[contains(text(),'Allergies / Intolerances')]/parent::ion-item/following-sibling::div/div[1]/span")
//	public MobileElement AllergyName1More;

//	@FindBy(xpath = "//ion-label[contains(text(),'Allergies / Intolerances')]/parent::ion-item/following-sibling::div/div[2]/span")
//	public MobileElement AllergyName2More;
	
	@FindBy(xpath = "//dxc-more-detail-banner//ion-label[contains(text(),'Allergies / Intolerances')]//following-sibling::ion-note[contains(@class,'count')]")
	public MobileElement AllergyCntMore;
	
	@FindBy(xpath = "//dxc-more-detail-banner//ion-label[contains(text(),'Allergies / Intolerances')]/parent::ion-item/following-sibling::div/div[1]/span")
	public MobileElement AllergyName1More;
	
	@FindBy(xpath = "//dxc-more-detail-banner//ion-label[contains(text(),'Allergies / Intolerances')]/parent::ion-item/following-sibling::div/div[2]/span")
	public MobileElement AllergyName2More;

	@FindBy(xpath = "//ion-label[contains(text(),'Allergies / Intolerances')]/parent::ion-item/following-sibling::div//ion-button[contains(text(),'details')]")
	public MobileElement AllergyLinkMore;
  
	@FindBy(xpath = "//ion-label[contains(text(),'Allerg')]/parent::ion-item/following-sibling::div/div[1]/span")
	public MobileElement AllergyNameMore;
	
	@FindBy(how = How.XPATH, using = "//ION-ITEM[contains(@class,'banneritem')]//*[contains(text(),'Allergies')]/parent::ion-item/parent::div//p")
	public MobileElement allergyEnhancementTextMore;
	
	// Advance Directive
		@FindBy(xpath = "//ion-label[contains(text(),'Adv')]//following-sibling::ion-note[contains(@class,'count')]")
		public MobileElement AdvDirCntMore;
		
		@FindBy(xpath = "//ion-label[contains(text(),'Adv')]/parent::ion-item/following-sibling::div/div[1]/span")
		public MobileElement AdvDirName1More;
		
		@FindBy(xpath = "//ion-label[contains(text(),'Adv')]/parent::ion-item/following-sibling::div/div[2]/span")
		public MobileElement AdvDirName2More;
		
		@FindBy(xpath = "//ion-label[contains(text(),'Adv')]/parent::ion-item/following-sibling::div//ion-button[contains(text(),'details')]")
		public MobileElement AdvDirLinkMore;
		
		@FindBy(xpath="//dxc-patient-banner//span[contains(@class,'mdi-account-voice')]/following-sibling::span")
		public MobileElement AdvDirCnt;
		
		@FindBy(how = How.XPATH, using ="//ion-label[contains(text(),'Preferences')]/following-sibling::ion-note")
		public MobileElement PrefCount;
		
		@FindBy(how = How.XPATH, using ="//ion-row[contains(@class,'iconAlignment')]//ion-icon[@name='flower']/following-sibling::span")
		public MobileElement AlertCount;
		
		@FindBy(how = How.XPATH, using ="//ion-row[contains(@class,'iconAlignment')]//ion-icon[@name='alert-circle']/following-sibling::span")
		public MobileElement AllergyCount;
		
		@FindBy(how = How.XPATH, using ="//ion-icon[@name='warning']")
		public MobileElement AccessResIcon;
		
		@FindBy(how = How.XPATH, using ="//ion-label[text()='Contact']/parent::ion-item/following-sibling::div//span")
		public MobileElement AccessResContact;
		
		@FindBy(how = How.XPATH, using ="//ion-label[text()='Address']/parent::ion-item/following-sibling::div//span")
		public MobileElement AccessResAddress;
		
		@FindBy(how=How.XPATH,using="//span[contains(text(),'Email')]//following-sibling::span")
		public MobileElement ContactEmail;

		@FindBy(how = How.XPATH, using ="//span[contains(text(),'Email')]//following-sibling::a")
		public MobileElement EmailMore;
		
		@FindBy(how = How.XPATH, using ="//span[contains(text(),'Gender')]//following-sibling::span")
		public MobileElement GenderMore;
		
		
}