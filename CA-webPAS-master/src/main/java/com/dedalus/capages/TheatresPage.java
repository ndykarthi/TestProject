package com.dedalus.capages;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.CAProjectMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TheatresPage extends CAProjectMethods{
	JavascriptExecutor js = driver;

	public TheatresPage(AppiumDriver<MobileElement> driver, ExtentTest test) {
		this.test = test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(xpath ="//ion-label[contains(text(),'Today')]/parent::ion-segment-button")
	public MobileElement clickTodayTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Planned')]/parent::ion-segment-button")
	public MobileElement clickPlannedTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Complete')]/parent::ion-segment-button")
	public MobileElement clickCompleteTab;

	@FindBy(xpath ="//input[contains(@placeholder,'Name Search')]")
	public MobileElement enterNamesearch;
	
	@FindBy(xpath ="//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]")
	public MobileElement PatDetMoreIcon;
	
	@FindBy(xpath ="//span[contains(text(),'Patient Details')]/ancestor::ion-row//*[contains(@class,'Title')]")
	public MobileElement PatDetMorePatName;
	
	@FindBy(xpath ="//span[contains(text(),'Patient Details')]/ancestor::ion-row//*[contains(text(),'DoB')]/following-sibling::span")
	public MobileElement PatDetMorePatDOB;
	
	@FindBy(xpath ="//span[contains(text(),'Patient Details')]/ancestor::ion-row//*[contains(text(),'Gender')]/following-sibling::span")
	public MobileElement PatDetMorePatGender;
	
	@FindBy(xpath ="//span[contains(text(),'Patient Details')]/ancestor::ion-row//*[contains(text(),'HRN')]/following-sibling::span")
	public MobileElement PatDetMorePatHRN;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Status')]/following-sibling::span")
	public MobileElement PatDetMoreBookingStatus;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Surgeon')]/following-sibling::span")
	public MobileElement PatDetMoreSurgeon;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Anaesthetist')]/following-sibling::span")
	public MobileElement PatDetMoreAneasthetist;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Unit')]/following-sibling::span")
	public MobileElement PatDetMoreOperationUnit;

	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Theatre')]/following-sibling::span")
	public MobileElement PatDetMoreOperationTheatre;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Date')]/following-sibling::span")
	public MobileElement PatDetMoreOperationDate;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Duration')]/following-sibling::span")
	public MobileElement PatDetMoreOperationDuration;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Anaesthetic type')]/following-sibling::span")
	public MobileElement PatDetMoreAnestheticType;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'MBS codes')]/following-sibling::span")
	public MobileElement PatDetMoreMBSCodes;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Reason')]/following-sibling::span")
	public MobileElement PatDetMoreReason;
		
	@FindBy(xpath ="//span[contains(text(),'Patient Details')]/parent::ion-col/following-sibling::ion-col/ion-icon[@name='close']")
	public MobileElement closeicon;
	
	@FindBy(xpath ="//ion-label[contains(text(),'Future')]/parent::ion-segment-button")
	public MobileElement encFutureTab;
	
	@FindBy(xpath ="//ion-label/h2/span[contains(text(),'NEXT 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card-header/following-sibling::ion-card-content//dxc-expandable-item//ion-label/h2/span")
	public MobileElement encFutureTabEncID;
	
	@FindBy(xpath ="//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card-header/following-sibling::ion-card-content//dxc-expandable-item//ion-label/h2/span")
	public MobileElement encPastTabEncID;

	

	

}
