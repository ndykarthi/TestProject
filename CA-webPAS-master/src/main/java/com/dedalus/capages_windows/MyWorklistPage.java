package com.dedalus.capages_windows;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods_windows.CAProjectMethods;

public class MyWorklistPage extends CAProjectMethods{

	JavascriptExecutor js = driver;

	public MyWorklistPage(RemoteWebDriver driver, ExtentTest test) {
		this.test = test;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	//@FindBy(xpath = "//*[contains(text(),'Admitted')]/parent::ion-segment-button")
	@FindBy(xpath = "//ion-segment-button[1]")
	public WebElement MyWorkListAdmittedTab;
	
	//@FindBy(xpath = "//*[contains(text(),'Planned')]/parent::ion-segment-button")
	@FindBy(xpath = "//ion-segment-button[3]")
	public WebElement MyWorkListPlannedTab;
	
	//@FindBy(xpath = "//*[contains(text(),'Discharged')]/parent::ion-segment-button")
	@FindBy(xpath = "//ion-segment-button[2]")
	public WebElement MyWorkListDischargedTab;
	
	@FindBy(xpath = "//*[contains(text(),'Emergency')]/parent::ion-segment-button")
	public WebElement MyWorkListEmergencyTab;
	
	@FindBy(xpath = "//*[contains(text(),'Outpatient')]/parent::ion-segment-button")
	public WebElement MyWorkListOutpatientTab;
	
	@FindBy(xpath = "//*[contains(text(),'Contacts')]/parent::ion-segment-button")
	public WebElement MyWorkListContactsTab;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-DIVIDER//ION-BADGE")
	public WebElement MyWorkListEncCount;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-DIVIDER//ION-LABEL")
	public WebElement MyWorkListEncLocation;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//H2")
	public WebElement MyWorkListPatName;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'NHS')]/parent::span")
	public WebElement MyWorkListPatNHS;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Born')]/parent::span")
	public WebElement MyWorkListPatDOB;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//ion-icon[@name='star']")
	public WebElement MyWorkListMarkUnfavouriteIcon;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//ion-icon[contains(@name,'ellipsis-vertical-outline')]")
	public WebElement MyWorkListMorePatDetailsIcon;
	
	@FindBy(xpath = "//dxc-more-list//ion-icon[contains(@name,'close')]")
	public WebElement MyWorkListClosePatDetailsIcon;
	

	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Building')]/following-sibling::span")
	public WebElement MyWorkListEncBuilding;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Bay')]/following-sibling::span")
	public WebElement MyWorkListEncBay;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Building')]/following-sibling::span")
	public WebElement MyWorkListMoreEncBuilding;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Bay')]/following-sibling::span")
	public WebElement MyWorkListMoreEncBay;
	
	@FindBy(xpath = "//ion-icon[@name='pin']/parent::span/following-sibling::span")
	public WebElement MyWorkListMoreEncLocation;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Planned')]/following-sibling::span")
	public WebElement MyWorkListMoreEncPlannedDate;
	
	@FindBy(xpath = "//dxc-more-list//ion-row/ion-grid/ion-row//span[@class='titleBold']")
	public WebElement MyWorkListMoreEncCareprovider;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Gender')]/following-sibling::span")
	public WebElement MyWorkListMorePatGender;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'PAS') and contains(@class,'labelColor')]/following-sibling::span")
	public WebElement MyWorkListMorePatPASID;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'NHS')]/following-sibling::span")
	public WebElement MyWorkListMorePatNHS;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Born')]/following-sibling::span")
	public WebElement MyWorkListMorePatDOB;
	
	@FindBy(xpath = "//*[contains(text(),'Inpatient')]/parent::ion-segment-button")
	public WebElement MyWorkListInpatientTab;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//*[contains(text(),'DoB')]/parent::span")
	public WebElement MyWorkListPatDOBWP;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//*[contains(text(),'HRN')]/parent::span")
	public WebElement MyWorkListPatHRN;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//*[contains(text(),'Site')]/following-sibling::span")
	public WebElement MyWorkListEncSite;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//*[contains(text(),'Room')]/following-sibling::span")
	public WebElement MyWorkListEncRoom;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//*[contains(text(),'Ward')]/following-sibling::span")
	public WebElement MyWorkListEncWard;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//*[contains(text(),'Bed')]/following-sibling::span")
	public WebElement MyWorkListEncBed;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'HRN') and contains(@class,'labelColor')]/following-sibling::span")
	public WebElement MyWorkListMorePatHRN;
	 
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'DoB')]/following-sibling::span")
	public WebElement MyWorkListMorePatDOBWP;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Site')]/following-sibling::span")
	public WebElement MyWorkListMoreEncSite;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Bed')]/following-sibling::span")
	public WebElement MyWorkListMoreEncBed;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Room')]/following-sibling::span")
	public WebElement MyWorkListMoreEncRoom;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Ward')]/following-sibling::span")
	public WebElement MyWorkListMoreEncWard;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Visit No')]/following-sibling::span")
	public WebElement MyWorkListMoreEncID;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Admitted')]/following-sibling::span")
	public WebElement MyWorkListMoreEncAdmittedDate;
	
	@FindBy(xpath = "//*[contains(text(),'Ward Leave')]/parent::ion-segment-button")
	public WebElement WardLeaveTab;
}



