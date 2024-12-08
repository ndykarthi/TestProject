package com.dedalus.capages_windows;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods_windows.CAProjectMethods;

import io.appium.java_client.MobileElement;


public class EncountersPage extends CAProjectMethods {
	
	public EncountersPage(RemoteWebDriver driver, ExtentTest test) {
		this.test = test;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//@FindBy(xpath = ("//dxc-encounters//ion-label[contains(text(),'Future')]"))
	//public WebElement futureFauxtab;
	@FindBy(xpath = ("(//dxc-encounters//ion-segment-button)[1]"))
	public WebElement futureFauxtab;
	
	@FindBy(how = How.XPATH, using = ("//dxc-encounters//ion-label[contains(text(),'Past')]"))
	public WebElement pastFauxtab;
	
	@FindBy(how = How.XPATH, using = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]"))
	public WebElement selectRec;

	@FindBy(how = How.XPATH, using = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card-header/following-sibling::ion-card-content//dxc-expandable-item//ion-label/h2/span"))
	public WebElement ipEncID;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label//ion-badge"))
	public WebElement IPEncBadge;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Admitted')]/following-sibling::span"))
	public WebElement ipEncAdmittedDate;
	
	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Discharge')]/following-sibling::span"))
	public WebElement ipEncDischargeDate;
	
	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Care provider')]/following-sibling::span"))
	public WebElement ipEncCareProvider;
	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Specialty')]/following-sibling::span"))
	public WebElement ipEncSpecialty;
	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-icon[contains(@name,'chevron-forward')]"))
	public WebElement expandIPEncDetails;
	
	@FindBy(xpath = ("//*[contains(text(),'Seen by')]/parent::ion-row/following-sibling::ion-row[1]/ion-col[1]"))
	public WebElement getIPEncSeen2SeenBy;
	
	@FindBy(xpath = ("//*[contains(text(),'Seen by type')]/parent::ion-row/following-sibling::ion-row[1]/ion-col[2]"))
	public WebElement getIPEncSeen2SeenType;
	
	@FindBy(xpath = ("//*[contains(text(),'Start date and time')]/parent::ion-row/following-sibling::ion-row[1]/ion-col[3]"))
	public WebElement getIPEncSeen2SeenStartDate;
	
	@FindBy(xpath = ("//*[contains(text(),'End date and time')]/parent::ion-row/following-sibling::ion-row[1]/ion-col[4]"))
	public WebElement getIPEncSeen2SeenEndDate;
	
	@FindBy(xpath = ("//*[contains(text(),'Comments')]/parent::ion-row/following-sibling::ion-row[1]/ion-col[5]"))
	public WebElement getIPEncSeen2Comments;
	
	@FindBy(xpath = ("//*[contains(text(),'Seen by')]/parent::ion-row/following-sibling::ion-row[2]/ion-col[1]"))
	public WebElement getIPEncSeen1SeenBy;
	
	@FindBy(xpath = ("//*[contains(text(),'Seen by type')]/parent::ion-row/following-sibling::ion-row[2]/ion-col[2]"))
	public WebElement getIPEncSeen1SeenType;
	
	@FindBy(xpath = ("//*[contains(text(),'Start date and time')]/parent::ion-row/following-sibling::ion-row[2]/ion-col[3]"))
	public WebElement getIPEncSeen1SeenStartDate;
	
	@FindBy(xpath = ("//*[contains(text(),'End date and time')]/parent::ion-row/following-sibling::ion-row[2]/ion-col[4]"))
	public WebElement getIPEncSeen1SeenEndDate;
	
	@FindBy(xpath = ("//*[contains(text(),'Comments')]/parent::ion-row/following-sibling::ion-row[2]/ion-col[5]"))
	public WebElement getIPEncSeen1Comments;
	
	
	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 EMERGENCY DEPARTMENT ENCOUNTERS')]"))
	public WebElement selectEDRec;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 EMERGENCY DEPARTMENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/h2/span"))
	public WebElement EDEncID;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 EMERGENCY DEPARTMENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label//ion-badge"))
	public WebElement EDEncBadge;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 EMERGENCY DEPARTMENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Attended')]/following-sibling::span"))
	public WebElement EDEncAttendedDate;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 EMERGENCY DEPARTMENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Care provider')]/following-sibling::span"))
	public WebElement EDEncEncCareProvider;
	
	
	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 OUTPATIENT ENCOUNTERS')]"))
	public WebElement selectOPRec;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 OUTPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/h2/span"))
	public WebElement OPEncID;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 OUTPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label//ion-badge"))
	public WebElement OPEncBadge;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 OUTPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Start')]/following-sibling::span"))
	public WebElement OPEncStartDate;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 OUTPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Care provider')]/following-sibling::span"))
	public WebElement OPEncEncCareProvider;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 OUTPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Specialty')]/following-sibling::span"))
	public WebElement OPEncSpecialty;
	
	
	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 SINGLE ENCOUNTERS')]"))
	public WebElement selectContactRec;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 SINGLE ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/h2/span"))
	public WebElement ContactEncID;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 SINGLE ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label//ion-badge"))
	public WebElement ContactEncBadge;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 SINGLE ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Start')]/following-sibling::span"))
	public WebElement ContactEncStartDate;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 SINGLE ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Care provider')]/following-sibling::span"))
	public WebElement ContactEncCareProvider;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 SINGLE ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Specialty')]/following-sibling::span"))
	public WebElement ContactEncSpecialty;

	
	@FindBy(xpath = ("//DXC-PATIENT-BANNER//ion-icon[contains(@name,'chevron-back-outline')]"))
	public WebElement backToPatientsearchIcon;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Encounter Id')]/following-sibling::ion-col"))
	public WebElement encListEDEncID;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Care provider')]/following-sibling::ion-col"))
	public WebElement encListEDEncCareProvider;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Specialty')]/following-sibling::ion-col"))
	public WebElement encListEDEncSpecialty;
	
	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Status')]/following-sibling::ion-col"))
	public WebElement encListEDEncStatus;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Start')]/following-sibling::ion-col"))
	public WebElement encListEDEncStartDate;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'End')]/following-sibling::ion-col"))
	public WebElement encListEDEncEndDate;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency')]/ancestor::div[contains(@class,'mainContinerSets')]//span[@id='starLayers']//ion-icon"))
	public WebElement encListEDFavIcon;


	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Encounter Id')]/following-sibling::ion-col"))
	public WebElement encListOPEncID;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Care provider')]/following-sibling::ion-col"))
	public WebElement encListOPEncCareProvider;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Specialty')]/following-sibling::ion-col"))
	public WebElement encListOPEncSpecialty;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Status')]/following-sibling::ion-col"))
	public WebElement encListOPEncStatus;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Start')]/following-sibling::ion-col"))
	public WebElement encListOPEncStartDate;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'End')]/following-sibling::ion-col"))
	public WebElement encListOPEncEndDate;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//span[@id='starLayers']//ion-icon"))
	public WebElement encListOPFavIcon;


	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Encounter Id')]/following-sibling::ion-col"))
	public WebElement encListContactEncID;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Care provider')]/following-sibling::ion-col"))
	public WebElement encListContactEncCareProvider;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Specialty')]/following-sibling::ion-col"))
	public WebElement encListContactEncSpecialty;
	
	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Status')]/following-sibling::ion-col"))
	public WebElement encListContactEncStatus;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Start')]/following-sibling::ion-col"))
	public WebElement encListContactEncStartDate;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'End')]/following-sibling::ion-col"))
	public WebElement encListContactEncEndDate;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//span[@id='starLayers']//ion-icon"))
	public WebElement encListContactFavIcon;

	
	
	@FindBy(xpath = ("//Dxc-header/ion-toolbar//ion-icon[@name='star']"))
	public WebElement clickMyWorklistFavIcon;

	
	//WebPas
	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Encounter Id')]/following-sibling::ion-col"))
	public WebElement encListIPEncID;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Care provider')]/following-sibling::ion-col"))
	public WebElement encListIPEncCareProvider;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Specialty')]/following-sibling::ion-col"))
	public WebElement encListIPEncSpecialty;
	
	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Status')]/following-sibling::ion-col"))
	public WebElement encListIPEncStatus;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Start')]/following-sibling::ion-col"))
	public WebElement encListIPEncStartDate;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'End')]/following-sibling::ion-col"))
	public WebElement encListIPEncEndDate;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//span[@id='starLayers']//ion-icon"))
	public WebElement encListIPFavIcon;

	@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency Department')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Care provider')]/following-sibling::ion-col"))
	public WebElement encListEDEncCareProviderWP;
	
	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 EMERGENCY DEPARTMENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Care provider')]/following-sibling::span"))
	public WebElement EDEncEncCareProviderWP;
	
	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/h2/span"))
	public WebElement ipPreAdmitEncID;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label//ion-badge"))
	public WebElement IPPreAdmitEncBadge;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Planned')]/following-sibling::span"))
	public WebElement ipPreAdmitEncPlannedDate;
	
	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Care provider')]/following-sibling::span"))
	public WebElement ipPreAdmitEncCareProvider;
	
	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Specialty')]/following-sibling::span"))
	public WebElement ipPreAdmitEncSpecialty;

	@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Actual LOS')]/following-sibling::span"))
	public WebElement ipEncActualLOS;
		
}
