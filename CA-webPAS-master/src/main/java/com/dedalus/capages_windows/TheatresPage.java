package com.dedalus.capages_windows;

import java.util.List;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods_windows.CAProjectMethods;

public class TheatresPage extends CAProjectMethods {

	public TheatresPage(RemoteWebDriver driver, ExtentTest test) {
		this.test = test;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath ="//ion-label[contains(text(),'Today')]/parent::ion-segment-button")
	public WebElement clickTodayTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Planned')]/parent::ion-segment-button")
	public WebElement clickPlannedTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Complete')]/parent::ion-segment-button")
	public WebElement clickCompleteTab;

	@FindBy(xpath ="//input[contains(@placeholder,'Name Search')]")
	public WebElement enterNamesearch;
	
	@FindBy(xpath ="//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]")
	
	
	public WebElement PatDetMoreIcon;
	
	@FindBy(xpath ="//span[contains(text(),'Patient Details')]/ancestor::ion-row//*[contains(@class,'Title')]")
	public WebElement PatDetMorePatName;
	
	@FindBy(xpath ="//span[contains(text(),'Patient Details')]/ancestor::ion-row//*[contains(text(),'DoB')]/following-sibling::span")
	public WebElement PatDetMorePatDOB;
	
	@FindBy(xpath ="//span[contains(text(),'Patient Details')]/ancestor::ion-row//*[contains(text(),'Gender')]/following-sibling::span")
	public WebElement PatDetMorePatGender;
	
	@FindBy(xpath ="//span[contains(text(),'Patient Details')]/ancestor::ion-row//*[contains(text(),'HRN')]/following-sibling::span")
	public WebElement PatDetMorePatHRN;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Status')]/following-sibling::span")
	public WebElement PatDetMoreBookingStatus;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Surgeon')]/following-sibling::span")
	public WebElement PatDetMoreSurgeon;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Anaesthetist')]/following-sibling::span")
	public WebElement PatDetMoreAneasthetist;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Unit')]/following-sibling::span")
	public WebElement PatDetMoreOperationUnit;

	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Theatre')]/following-sibling::span")
	public WebElement PatDetMoreOperationTheatre;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Date')]/following-sibling::span")
	public WebElement PatDetMoreOperationDate;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Duration')]/following-sibling::span")
	public WebElement PatDetMoreOperationDuration;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Anaesthetic type')]/following-sibling::span")
	public WebElement PatDetMoreAnestheticType;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'MBS codes')]/following-sibling::span")
	public WebElement PatDetMoreMBSCodes;
	
	@FindBy(xpath ="//span[contains(text(),'Theatre Details')]/ancestor::ion-row//*[contains(text(),'Reason')]/following-sibling::span")
	public WebElement PatDetMoreReason;
		
	@FindBy(xpath ="//span[contains(text(),'Patient Details')]/parent::ion-col/following-sibling::ion-col/ion-icon[@name='close']")
	public WebElement closeicon;
	
	@FindBy(xpath ="//ion-label[contains(text(),'Future')]/parent::ion-segment-button")
	public WebElement encFutureTab;
	
	@FindBy(xpath ="//ion-label/h2/span[contains(text(),'NEXT 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card-header/following-sibling::ion-card-content//dxc-expandable-item//ion-label/h2/span")
	public WebElement encFutureTabEncID;
	
	@FindBy(xpath ="//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card-header/following-sibling::ion-card-content//dxc-expandable-item//ion-label/h2/span")
	public WebElement encPastTabEncID;

	

	
	
}
