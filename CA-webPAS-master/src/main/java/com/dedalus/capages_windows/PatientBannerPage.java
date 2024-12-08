package com.dedalus.capages_windows;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods_windows.CAProjectMethods;



public class PatientBannerPage extends CAProjectMethods {
	public PatientBannerPage(RemoteWebDriver driver, ExtentTest test) {
		this.test = test;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//ion-button//span[contains(@class,'indent-decrease')]")
	public WebElement IndentDecrease;
	
	@FindBy(xpath="//dxc-patient-banner//ion-icon[@name='alert-circle']/following-sibling::span")
	public WebElement AlertCnt;
	
//	@FindBy(xpath="//dxc-patient-banner//ion-icon[contains(@name,'ellipsis')]")
//	public WebElement MoreButton;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Alerts')]//following-sibling::ion-note[contains(@class,'count')]")
	public WebElement AlertCntMore;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Alerts')]/parent::ion-item/following-sibling::div/div[1]/span")
	public WebElement AlertName1More;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Alerts')]/parent::ion-item/following-sibling::div/div[2]/span")
	public WebElement AlertName2More;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Alerts')]/parent::ion-item/following-sibling::div//ion-button[contains(text(),'details')]")
	public WebElement AlertLinkMore;
	
	
	
	
	
    @FindBy(how=How.XPATH,using="//span[contains(text(),'NHS')]")
    public WebElement clickPatient;

  @FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Search')]")
  public WebElement Search;
 
  @FindBy(how = How.XPATH, using = "//ion-col[@class='ion-no-padding pat-name md hydrated']")
	public WebElement PatientName;

    @FindBy(how=How.XPATH,using="//span[text()='Preferred Name']//following::span[1]")
   	public WebElement PreferredName;

 // @FindBy(how = How.XPATH, using = "//ion-col[@class='ion-no-padding md hydrated']//span[text()='Born']//following::span[1]")
    @FindBy(how = How.XPATH, using = "//ion-col[contains(@class,'born')]//span[text()='Born']//following::span[1]")
   	public WebElement PatientDoB;

    @FindBy(how=How.XPATH,using="//span[@class='label-txt' and contains(text(),'NHS')]//following::span[1]")
	public WebElement PatNHS;

    //Patient Banner
    @FindBy(how=How.XPATH,using="//dxc-more-detail-banner//following::div[@class='detail-content']//div[@class='prop-value'][1]")
	public WebElement Address1;

    @FindBy(how=How.XPATH,using="//dxc-more-detail-banner//following::div[@class='detail-content']//div[@class='prop-value'][2]")
   	public WebElement Address2;

    @FindBy(how=How.XPATH,using="//dxc-more-detail-banner//following::div[@class='detail-content']//div[@class='prop-value'][3]")
   	public WebElement Address3;

    @FindBy(how=How.XPATH,using="//span[contains(text(),'Home')]//following-sibling::span")
   	public WebElement ContactHome;

    @FindBy(how=How.XPATH,using="//span[contains(text(),'Mobile')]//following-sibling::span")
   	public WebElement ContactMobile;

    @FindBy(how=How.XPATH,using="//span[contains(text(),'Work')]//following-sibling::span")
   	public WebElement ContactWork;

    @FindBy(how=How.XPATH,using="//span[contains(text(),'Email')]//following-sibling::span")
    public WebElement ContactEmail;

    @FindBy(how = How.XPATH, using ="//span[contains(text(),'Email')]//following-sibling::a")
    public WebElement EmailMore;

    @FindBy(how=How.XPATH,using="//span[contains(text(),'Gender')]//following-sibling::span")
   	public WebElement Gender;

    @FindBy(how=How.XPATH,using="//span[contains(text(),'PAS')]//following-sibling::span")
   	public WebElement PAS;

  @FindBy(how = How.XPATH, using = "(//span[contains(text(),'NHS')]//following-sibling::span)[2]")
   	public WebElement NHS;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Alerts')]/following::ion-note[@class='count ion-no-padding ion-no-margin md hydrated'])[1]")
   	public WebElement AlertsCount;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Allergies')]/following::ion-note[@class='count ion-no-padding ion-no-margin md hydrated'])[1]")
   	public WebElement AllergiesCount;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Advance Directives')]/following::ion-note[@class='count ion-no-padding ion-no-margin md hydrated'])[1]")
   	public WebElement AdvanceDirectivesCount;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Preferences')]/following::ion-note[@class='count ion-no-padding ion-no-margin md hydrated'])[1]")
   	public WebElement PreferencesCount;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Communication language')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][1]")
   	public WebElement CommunicationLanguage;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Communication language')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][2]")
   	public WebElement CommunicationLanguageDate;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Communication language')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][3]")
   	public WebElement CommunicationLanguageActionable;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Communication method')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][1]")
   	public WebElement CommunicationMethod;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Communication method')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][2]")
   	public WebElement CommunicationMethodDate;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Communication method')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][3]")
   	public WebElement CommunicationMethodActionable;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Transport needs')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][1]")
   	public WebElement TransportNeeds;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Transport needs')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][2]")
   	public WebElement TransportNeedsDate;

  @FindBy(how = How.XPATH, using = "//ion-col[contains(text(),'Transport needs')][1]//following::ion-col[@class='ion-text-capitalize prop-value md hydrated'][3]")
   	public WebElement TransportNeedsActionable;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Allergies')]/following::ion-button[@class='md button button-small button-solid ion-activatable ion-focusable hydrated'])[1]")
   	public WebElement ViewDetailsAllergies;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Alerts')]/following::ion-button[@class='md button button-small button-solid ion-activatable ion-focusable hydrated'])[1]")
   	public WebElement ViewDetailsAlerts;

  @FindBy(how = How.XPATH, using = "(//ion-label[@class='ion-no-padding ion-no-margin sc-ion-label-md-h sc-ion-label-md-s md hydrated' and contains(text(),'Advance Directives')]/following::ion-button[@class='md button button-small button-solid ion-activatable ion-focusable hydrated'])[1]")
   	public WebElement AdvanceDirectiveViewDetails;

  @FindBy(how = How.XPATH, using = "(//ion-icon[@name='ellipsis-vertical-outline'])[2]")
	public WebElement MoreButton;

	@FindBy(xpath = "//ion-label[contains(text(),'Allergies / Intolerances')]//following-sibling::ion-note[contains(@class,'count')]")
	public WebElement AllergyCntMore;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Allergies / Intolerances')]/parent::ion-item/following-sibling::div/div[1]/span")
	public WebElement AllergyName1More;

	@FindBy(xpath = "//ion-label[contains(text(),'Allergies / Intolerances')]/parent::ion-item/following-sibling::div/div[2]/span")
	public WebElement AllergyName2More;

	@FindBy(xpath = "//ion-label[contains(text(),'Allergies / Intolerances')]/parent::ion-item/following-sibling::div//ion-button[contains(text(),'details')]")
	public WebElement AllergyLinkMore;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Allerg')]/parent::ion-item/following-sibling::div/div[1]/span")
	public WebElement AllergyNameMore;
  
	@FindBy(how = How.XPATH, using = "//ION-ITEM[contains(@class,'banneritem')]//*[contains(text(),'Allergies')]/parent::ion-item/parent::div//p")
	public WebElement allergyEnhancementTextMore;
	
	@FindBy(how = How.XPATH, using ="//ion-row[contains(@class,'iconAlignment')]//ion-icon[@name='alert-circle']/following-sibling::span")
	public WebElement AllergyCount;
	
	@FindBy(how = How.XPATH, using ="//dxc-patient-banner//ion-col[3]//ion-icon")
	public WebElement AccessResIcon;
	
	@FindBy(how = How.XPATH, using ="//ion-label[text()='Contact']/parent::ion-item/following-sibling::div//span")
	public WebElement AccessResContact;
	
	@FindBy(how = How.XPATH, using ="//ion-label[text()='Address']/parent::ion-item/following-sibling::div//span")
	public WebElement AccessResAddress;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Adv')]//following-sibling::ion-note[contains(@class,'count')]")
	public WebElement AdvDirCntMore;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Adv')]/parent::ion-item/following-sibling::div/div[1]/span")
	public WebElement AdvDirName1More;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Adv')]/parent::ion-item/following-sibling::div/div[2]/span")
	public WebElement AdvDirName2More;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Adv')]/parent::ion-item/following-sibling::div//ion-button[contains(text(),'details')]")
	public WebElement AdvDirLinkMore;
	
	@FindBy(how = How.XPATH, using ="//span[contains(text(),'Gender')]//following-sibling::span")
	public WebElement GenderMore;
}