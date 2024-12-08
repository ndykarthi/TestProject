package com.dedalus.capages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.CAProjectMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.interactions.Actions;

public class MenuPage extends CAProjectMethods{
	
	public MenuPage(AppiumDriver<MobileElement> driver, ExtentTest test) {
		this.test=test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//@FindBy(how = How.TAG_NAME, using = "ion-menu-button")
	@FindBy(how = How.XPATH, using = "//ion-menu-button")
	public MobileElement clickMenu;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Search')]")
	public MobileElement menu_Search;
	
	@FindBy(how = How.XPATH, using = "//ion-item//ion-label[contains(text(),'Preferences')]")
	    public MobileElement Preferences;
	 
		@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Show Avatars')]/parent::ion-item//ion-toggle[@aria-checked='false']")
		public MobileElement AvtarToggleDisable;

	//@FindBy(how = How.XPATH, using = "//ion-menu-button[@class='md button ion-activatable ion-focusable hydrated']")
	@FindBy(xpath = "//ion-menu-button")	
	public MobileElement clickMenuNew;
	
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Show Avatars')]/parent::ion-item//ion-toggle")
	public MobileElement AvtarToggleEnable;

	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Wards')]")
	public MobileElement clickMyWard;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Add Ward')]")
	public MobileElement AddWard;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Ward']")
	public MobileElement EnterWard;
	
/*	public ViewEncountersPage verifypatientAvtaar(String patientname) throws InterruptedException {
		MobileElement PatientAvtar=driver.findElement(By.xpath("//h2[contains(text(),'"+patientname+"')]/ancestor::ion-col/preceding-sibling::ion-col//ion-avatar/div"));
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
	    public MobileElement Documentation_Preferences;
	 
	 @FindBy(how = How.XPATH, using = "//ion-label[text()='Cardiology ward01']/parent::ion-col/following-sibling::ion-col/ion-icon[@aria-label='star outline']")
		public MobileElement Cardiologyward01_AWS;   
	    
	   
	    
	    @FindBy(how = How.XPATH, using = "//ion-button[contains(text(),' Deselect All ')]")
	    public MobileElement DeselectALL_Enabled;
	    
	    @FindBy(how = How.XPATH, using = "//ion-button[contains(text(),' Deselect All ') and @aria-disabled='true']")
	    public MobileElement DeselectALL_Disabled;
	    
	    @FindBy(how = How.XPATH, using = "//ion-button[contains(text(),' Select All ')]")
	    public MobileElement SelectALL_Enabled;

	// ion-label[contains(text(),'My
	// Wards')]/ancestor::button/following-sibling::div//ion-label[contains(text(),'Cardio
	// Ward1')]

	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Cardio Ward1')]")
	public MobileElement SelectWard_1058;

	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Cardio Ward')]")
	public MobileElement SelectWard;

	@FindBy(how = How.XPATH, using = "//ion-icon[@name='star']")
	public MobileElement favourites;

	public MenuPage selectFavourites() throws InterruptedException {
		actionClick(favourites);
		Thread.sleep(5000);
		takeSnap();
		return this;
	}

	/*@FindBy(how=How.XPATH,using="//input[@placeholder='Name Search']")
	public MobileElement patName;

	public MenuPage searchNselectPatient(String data) throws InterruptedException {
	String field = "Patient Name";
	explicitWait(patName);
	type(patName, data, field);
	patName.sendKeys(Keys.TAB);
	Thread.sleep(2000);
	MobileElement elePAt = driver.findElement(By.xpath("//div/h2[contains(text(),'"+data+"')]"));
	actionClick(elePAt);
	Thread.sleep(3000);
	takeSnap();
	return this;
	}      */

	@FindBy(how = How.XPATH, using = "//div[@class='scroll-zoom-wrapper']//span[contains(text(),'Observations')]")
	public MobileElement selectObservation;

/*	public ViewObservationPage selectObservationTab() throws InterruptedException {
		actionClick(selectObservation);
		Thread.sleep(2000);
		takeSnap();
		return new ViewObservationPage(driver, test);
	}
*/
	@FindBy(how = How.XPATH, using = "//div[@class='scroll-zoom-wrapper']//span[contains(text(),'Advance Directives')]")
	public MobileElement selectAdvDir;
/*
	public ViewAdvanceDirectivesPage selectAdvDirectiveTab() throws InterruptedException {
		actionClick(selectAdvDir);
		Thread.sleep(5000);
		takeSnap();
		return new ViewAdvanceDirectivesPage(driver,test);
	}
*/
	@FindBy(how = How.XPATH, using = "//div[@class='tab-btn nonselected-tab']//span[contains(text(),'Results')]")
	public MobileElement selectResult;

/*	public ViewResultsPage selectResultsTab() throws InterruptedException {
		actionClick(selectResult);
		Thread.sleep(5000);
		takeSnap();
		return new ViewResultsPage(driver,test);
	}
*/
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Encounters')]")
	public MobileElement selectEnc;
/*
	public ViewEncountersPage selectEncountersTab() throws InterruptedException {
		actionClick(selectEnc);
		Thread.sleep(5000);
		takeSnap();
		return new ViewEncountersPage(driver,test);
	}
*/
	@FindBy(how = How.XPATH, using = "//ion-item//ion-label[text()='Cardio Ward']/following-sibling::ion-icon")
	public MobileElement CardioWard_star_1042;

	@FindBy(how = How.XPATH, using = "//ion-label[text()='Cardio Ward1']/following-sibling::ion-icon[@aria-label='star outline']")
	public MobileElement CardioWard1_star_1058;
	
	//@FindBy(how = How.XPATH, using = "//ion-label[text()='Cardiology ward01']/following-sibling::ion-icon[@aria-label='star outline']")
	@FindBy(how = How.XPATH, using = "//ion-label[text()='Cardiology ward01']/parent::ion-col/following-sibling::ion-col/ion-icon[@aria-label='star outline']")
	public MobileElement CardioWard_AWS;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Name Search']")
	public MobileElement NameSearch_PatientName;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Results')]")
	public MobileElement clickResultsTab;

	//@FindBy(how = How.XPATH, using = "(//ion-button[@class='button-tab reloadbutton mode-change refresh-color md button button-clear ion-activatable ion-focusable hydrated'])[2]")
	@FindBy(how = How.XPATH, using = "//ion-button/ion-icon[@aria-label='funnel']")
	public MobileElement Filtericon;

	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(), 'Date Range')]")
	public MobileElement DateRange;

	@FindBy(how = How.XPATH, using = "//div[contains(text(), 'Selected Range')]")
	public MobileElement SelectedRange;

	@FindBy(how = How.XPATH, using = "//span[contains(text(), 'OK')]")
	public MobileElement Okbutton;

	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(), 'Date From')]")
	public MobileElement DateFrom;

	@FindBy(how = How.XPATH, using = "(//div[@class='picker-opts'])[1]/button[contains(text(), '15')]")
	public MobileElement SelectDay;
	@FindBy(how = How.XPATH, using = "(//div[@class='picker-opts'])[2]/button[contains(text(), 'Sep')]")
	public MobileElement SelectMonth;
	@FindBy(how = How.XPATH, using = "(//div[@class='picker-opts'])[3]/button[contains(text(), '2020')]")
	public MobileElement SelectYear;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(), 'Done')]")
	public MobileElement Donebutton;
	
	@FindBy(how = How.XPATH, using = "//ion-item//ion-label[text()='Cardio Ward1']/following-sibling::ion-icon")
    public MobileElement MarkFavWard;
   
    @FindBy(how = How.XPATH, using = "//ion-item//ion-label[contains(text(),'My Wards')]/parent::ion-item/following-sibling::div//ion-label[contains(text(),'Cardiology ward01')]")
    public MobileElement SelectFavWard_AWS;

    @FindBy(how = How.XPATH, using = "//ion-item//ion-label[contains(text(),'My Wards')]/parent::ion-item/following-sibling::div//ion-label[contains(text(),'Cardio Ward1')]")
    public MobileElement SelectFavWard_1058;
    
    @FindBy(how = How.XPATH, using = "//ion-label[contains(text(), 'Result status')]")
	public MobileElement ResStatus;
    
    @FindBy(how = How.XPATH, using = "//button[@role='checkbox']//div[contains(text(),'Final')]")
	public MobileElement Final;
    
    @FindBy(how = How.XPATH, using = "//button//span[contains(text(),'OK')]")
	public MobileElement Status_OK;
    
    @FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Worklist Overview')]")
	public MobileElement clickMyWorklistOverview;
    
    @FindBy(how = How.XPATH, using = "//ion-footer//ion-icon[@name='funnel']")
	public MobileElement footerFunnel;
	
	@FindBy(how = How.XPATH, using = "//ion-select[contains(@aria-label,'Medically Discharged')]")
	public MobileElement MedDischFilter;
	
	@FindBy(how = How.XPATH, using = "//button//div[contains(text(),'True')]")
	public MobileElement radioTrue;
	
	@FindBy(how = How.XPATH, using = "//button//div[contains(text(),'False')]")
	public MobileElement radioFalse;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'alert-button')]//span[contains(text(),'OK')]")
	public MobileElement OK;
	
	@FindBy(how = How.XPATH, using = "//ion-button[contains(text(),'Clear Filters')]")
	public MobileElement ClearFilters;
    
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Worklist')]")
	public MobileElement clickMyWorklist;
    
    //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
   
    
    
    
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Specialties')]")
	public MobileElement clickMySpecialties;
    
	@FindBy(how = How.XPATH, using = "//ion-button[contains(text(),'Add Specialty')]")
	public MobileElement clickAddSpecialty;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder = 'Specialty']")
	public MobileElement inputSpeacialty;
	
	@FindBy(how = How.XPATH, using = "//ion-list//*[text()='CARDIOLOGY']/ancestor::ion-row//ion-icon[@aria-label='star outline']")
	public MobileElement specialtyListitemFavIcon;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'CARDIOLOGY')]/parent::ion-item")
	public MobileElement specialtyListiteminMenu;
	
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Care Providers')]")
	public MobileElement clickMyCareProviders;
 
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Add Care Provider')]")
	public MobileElement AddCareProvider;
 
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Care Provider']")
	public MobileElement EnterCareProvider;
	
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Theatres')]")
	public MobileElement clickTheatres;
	
	@FindBy(how = How.XPATH, using = "//ion-row[contains(@id,'lineOfParagraphGroup')][2]//span")
	public MobileElement SensitiveInfo;
	
    //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	
    @FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Preferences')]")
	public MobileElement clickPreference;
    
    @FindBy(how = How.XPATH, using = "//*[@id='ion-r-1']")
	public MobileElement PlannedAdmissionSlider;
    
    @FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Planned Admissions')]")
	public MobileElement clickMyPlannedAdmissions;
    
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Tomorrow')]")
	public MobileElement clickTomorrowMyPlannedAdmissions;
 
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'My Planned Admissions')]//span[contains(text(),'Tomorrow')]")
	public MobileElement TomorrowMyPlannedAdmissionsTitle;
	
	@FindBy(how = How.XPATH, using = "//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]")
	public MobileElement PatDetMoreIcon;
    
	@FindBy(how = How.XPATH, using = "//h4[@class='head-data']")
	public MobileElement LoggedInUser;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'General')]/ancestor::ion-item/ion-icon")
	public MobileElement GeneralMenu;
 
}



