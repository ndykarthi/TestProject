package com.dedalus.capages_windows;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods_windows.GenericAppMethods;

import io.appium.java_client.MobileElement;

import org.openqa.selenium.interactions.Actions;


public class MenuPage extends GenericAppMethods{
	
	public MenuPage(RemoteWebDriver driver, ExtentTest test) {
		this.test = test;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.TAG_NAME, using = "ion-menu-button")
	public WebElement clickMenu;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Search')]")
	public WebElement menu_Search;
	
	@FindBy(how = How.XPATH, using = "//ion-item//ion-label[contains(text(),'Preferences')]")
	    public WebElement Preferences;
	 
		@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Show Avatars')]/parent::ion-item//ion-toggle[@aria-checked='false']")
		public WebElement AvtarToggleDisable;

	//@FindBy(how = How.XPATH, using = "//ion-menu-button[@class='md button ion-activatable ion-focusable hydrated']")
	@FindBy(xpath = "//ion-menu-button")	
	public WebElement clickMenuNew;
	
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Show Avatars')]/parent::ion-item//ion-toggle")
	public WebElement AvtarToggleEnable;

	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Wards')]")
	public WebElement clickMyWard;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Add Ward')]")
	public WebElement AddWard;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Ward']")
	public WebElement EnterWard;
	
/*	public ViewEncountersPage verifypatientAvtaar(String patientname) throws InterruptedException {
		WebElement PatientAvtar=driver.findElement(By.xpath("//h2[contains(text(),'"+patientname+"')]/ancestor::ion-col/preceding-sibling::ion-col//ion-avatar/div"));
		String patientAvtarvalue=PatientAvtar.getText();
		String result=getFirstLetters(patientname);
		System.out.println("result is displaying as" +result);
		StringBuilder input1 = new StringBuilder();
		 
        // append a string into StringBuilder input1
        input1.append(result);
 
        // reverse StringBuilder input1
       StringBuilder result1= input1.reverse();
		if(patientAvtarvalue.contentEquals(result1)){
			reportStep("Patient Avtar is displaying correct and value displaying as "+patientAvtarvalue, "PASS");
		}
		else{
			reportStep("Patient Avtar is displaying not correct and value displaying as "+patientAvtarvalue, "FAIL");
		}
		takeSnap();
		return new ViewEncountersPage(driver,test);
	}
*/
	 @FindBy(how = How.XPATH, using = "//*[contains(text(),'Documentation')]")
	    public WebElement Documentation_Preferences;
	 
	 @FindBy(how = How.XPATH, using = "//ion-label[text()='Cardiology ward01']/parent::ion-col/following-sibling::ion-col/ion-icon[@aria-label='star outline']")
		public WebElement Cardiologyward01_AWS;   
	    
	   
	    
	    @FindBy(how = How.XPATH, using = "//ion-button[contains(text(),' Deselect All ')]")
	    public WebElement DeselectALL_Enabled;
	    
	    @FindBy(how = How.XPATH, using = "//ion-button[contains(text(),' Deselect All ') and @aria-disabled='true']")
	    public WebElement DeselectALL_Disabled;
	    
	    @FindBy(how = How.XPATH, using = "//ion-button[contains(text(),' Select All ')]")
	    public WebElement SelectALL_Enabled;

	// ion-label[contains(text(),'My
	// Wards')]/ancestor::button/following-sibling::div//ion-label[contains(text(),'Cardio
	// Ward1')]

	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Cardio Ward1')]")
	public WebElement SelectWard_1058;

	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Cardio Ward')]")
	public WebElement SelectWard;

	@FindBy(how = How.XPATH, using = "//ion-icon[@name='star']")
	public WebElement favourites;

	public MenuPage selectFavourites() throws InterruptedException {
		actionClick(favourites);
		Thread.sleep(5000);
		takeSnap();
		return this;
	}

	/*@FindBy(how=How.XPATH,using="//input[@placeholder='Name Search']")
	public WebElement patName;

	public MenuPage searchNselectPatient(String data) throws InterruptedException {
	String field = "Patient Name";
	explicitWait(patName);
	type(patName, data, field);
	patName.sendKeys(Keys.TAB);
	Thread.sleep(2000);
	WebElement elePAt = driver.findElement(By.xpath("//div/h2[contains(text(),'"+data+"')]"));
	actionClick(elePAt);
	Thread.sleep(3000);
	takeSnap();
	return this;
	}      */

	@FindBy(how = How.XPATH, using = "//div[@class='scroll-zoom-wrapper']//span[contains(text(),'Observations')]")
	public WebElement selectObservation;

/*	public ViewObservationPage selectObservationTab() throws InterruptedException {
		actionClick(selectObservation);
		Thread.sleep(2000);
		takeSnap();
		return new ViewObservationPage(driver, test);
	}
*/
	@FindBy(how = How.XPATH, using = "//div[@class='scroll-zoom-wrapper']//span[contains(text(),'Advance Directives')]")
	public WebElement selectAdvDir;
/*
	public ViewAdvanceDirectivesPage selectAdvDirectiveTab() throws InterruptedException {
		actionClick(selectAdvDir);
		Thread.sleep(5000);
		takeSnap();
		return new ViewAdvanceDirectivesPage(driver,test);
	}
*/
	@FindBy(how = How.XPATH, using = "//div[@class='tab-btn nonselected-tab']//span[contains(text(),'Results')]")
	public WebElement selectResult;

/*	public ViewResultsPage selectResultsTab() throws InterruptedException {
		actionClick(selectResult);
		Thread.sleep(5000);
		takeSnap();
		return new ViewResultsPage(driver,test);
	}
*/
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Encounters')]")
	public WebElement selectEnc;
/*
	public ViewEncountersPage selectEncountersTab() throws InterruptedException {
		actionClick(selectEnc);
		Thread.sleep(5000);
		takeSnap();
		return new ViewEncountersPage(driver,test);
	}
*/
	@FindBy(how = How.XPATH, using = "//ion-item//ion-label[text()='Cardio Ward']/following-sibling::ion-icon")
	public WebElement CardioWard_star_1042;

	@FindBy(how = How.XPATH, using = "//ion-label[text()='Cardio Ward1']/following-sibling::ion-icon[@aria-label='star outline']")
	public WebElement CardioWard1_star_1058;
	
	//@FindBy(how = How.XPATH, using = "//ion-label[text()='Cardiology ward01']/following-sibling::ion-icon[@aria-label='star outline']")
	@FindBy(how = How.XPATH, using = "//ion-label[text()='Cardiology ward01']/parent::ion-col/following-sibling::ion-col/ion-icon[@aria-label='star outline']")
	public WebElement CardioWard_AWS;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Name Search']")
	public WebElement NameSearch_PatientName;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Results')]")
	public WebElement clickResultsTab;

	//@FindBy(how = How.XPATH, using = "(//ion-button[@class='button-tab reloadbutton mode-change refresh-color md button button-clear ion-activatable ion-focusable hydrated'])[2]")
	@FindBy(how = How.XPATH, using = "//ion-button/ion-icon[@aria-label='funnel']")
	public WebElement Filtericon;

	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(), 'Date Range')]")
	public WebElement DateRange;

	@FindBy(how = How.XPATH, using = "//div[contains(text(), 'Selected Range')]")
	public WebElement SelectedRange;

	@FindBy(how = How.XPATH, using = "//span[contains(text(), 'OK')]")
	public WebElement Okbutton;

	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(), 'Date From')]")
	public WebElement DateFrom;

	@FindBy(how = How.XPATH, using = "(//div[@class='picker-opts'])[1]/button[contains(text(), '15')]")
	public WebElement SelectDay;
	@FindBy(how = How.XPATH, using = "(//div[@class='picker-opts'])[2]/button[contains(text(), 'Sep')]")
	public WebElement SelectMonth;
	@FindBy(how = How.XPATH, using = "(//div[@class='picker-opts'])[3]/button[contains(text(), '2020')]")
	public WebElement SelectYear;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(), 'Done')]")
	public WebElement Donebutton;
	
	@FindBy(how = How.XPATH, using = "//ion-item//ion-label[text()='Cardio Ward1']/following-sibling::ion-icon")
    public WebElement MarkFavWard;
   
    @FindBy(how = How.XPATH, using = "//ion-item//ion-label[contains(text(),'My Wards')]/parent::ion-item/following-sibling::div//ion-label[contains(text(),'Cardiology ward01')]")
    public WebElement SelectFavWard_AWS;

    @FindBy(how = How.XPATH, using = "//ion-item//ion-label[contains(text(),'My Wards')]/parent::ion-item/following-sibling::div//ion-label[contains(text(),'Cardio Ward1')]")
    public WebElement SelectFavWard_1058;
    
    @FindBy(how = How.XPATH, using = "//ion-label[contains(text(), 'Result status')]")
	public WebElement ResStatus;
    
    @FindBy(how = How.XPATH, using = "//button[@role='checkbox']//div[contains(text(),'Final')]")
	public WebElement Final;
    
    @FindBy(how = How.XPATH, using = "//button//span[contains(text(),'OK')]")
	public WebElement Status_OK;
    
    @FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Worklist Overview')]")
	public WebElement clickMyWorklistOverview;
    
    @FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Worklist')]")
	public WebElement clickMyWorklist;
    
    
    //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
   
    
    
    
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Specialties')]")
	public WebElement clickMySpecialties;
    
	@FindBy(how = How.XPATH, using = "//ion-button[contains(text(),'Add Specialty')]")
	public WebElement clickAddSpecialty;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder = 'Specialty']")
	public WebElement inputSpeacialty;
	
	@FindBy(how = How.XPATH, using = "//ion-list//*[text()='CARDIOLOGY']/ancestor::ion-row//ion-icon[@aria-label='star outline']")
	public WebElement specialtyListitemFavIcon;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'CARDIOLOGY')]/parent::ion-item")
	public WebElement specialtyListiteminMenu;
	
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Care Providers')]")
	public WebElement clickMyCareProviders;
 
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Add Care Provider')]")
	public WebElement AddCareProvider;
 
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Care Provider']")
	public WebElement EnterCareProvider;
	
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Theatres')]")
	public WebElement clickTheatres;
	
	@FindBy(how = How.XPATH, using = "//ion-row[contains(@id,'lineOfParagraphGroup')][2]//span")
	public WebElement SensitiveInfo;
	
    //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	
    @FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Preferences')]")
	public WebElement clickPreference;
    
    @FindBy(how = How.XPATH, using = "//*[@id='ion-r-1']")
	public WebElement PlannedAdmissionSlider;
    
    @FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Planned Admissions')]")
	public WebElement clickMyPlannedAdmissions;
    
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Tomorrow')]")
	public WebElement clickTomorrowMyPlannedAdmissions;
 
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'My Planned Admissions')]//span[contains(text(),'Tomorrow')]")
	public WebElement TomorrowMyPlannedAdmissionsTitle;
	
	@FindBy(how = How.XPATH, using = "//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]")
	public WebElement PatDetMoreIcon;
    
	@FindBy(how = How.XPATH, using = "//h4[@class='head-data']")
	public WebElement LoggedInUser;
}

