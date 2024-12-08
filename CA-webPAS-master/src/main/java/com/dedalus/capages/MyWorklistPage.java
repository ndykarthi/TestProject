package com.dedalus.capages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.GenericAppMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MyWorklistPage extends GenericAppMethods {
	
	public MyWorklistPage(AppiumDriver<MobileElement> driver,ExtentTest test) {
		this.test = test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//@FindBy(xpath = "//*[contains(text(),'Admitted')]/parent::ion-segment-button")
	@FindBy(xpath = "//ion-segment-button[1]")
	public MobileElement MyWorkListAdmittedTab;
	
	//@FindBy(xpath = "//*[contains(text(),'Planned')]/parent::ion-segment-button")
	@FindBy(xpath = "//ion-segment-button[3]")
	public MobileElement MyWorkListPlannedTab;
	
	//@FindBy(xpath = "//*[contains(text(),'Discharged')]/parent::ion-segment-button")
	@FindBy(xpath = "//ion-segment-button[2]")
	public MobileElement MyWorkListDischargedTab;
	
	@FindBy(xpath = "//*[contains(text(),'Emergency')]/parent::ion-segment-button")
	public MobileElement MyWorkListEmergencyTab;
	
	@FindBy(xpath = "//*[contains(text(),'Outpatient')]/parent::ion-segment-button")
	public MobileElement MyWorkListOutpatientTab;
	
	@FindBy(xpath = "//*[contains(text(),'Contacts')]/parent::ion-segment-button")
	public MobileElement MyWorkListContactsTab;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-DIVIDER//ION-BADGE")
	public MobileElement MyWorkListEncCount;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-DIVIDER//ION-LABEL")
	public MobileElement MyWorkListEncLocation;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//H2")
	public MobileElement MyWorkListPatName;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'NHS')]/parent::span")
	public MobileElement MyWorkListPatNHS;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Born')]/parent::span")
	public MobileElement MyWorkListPatDOB;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//ion-icon[@name='star']")
	public MobileElement MyWorkListMarkUnfavouriteIcon;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//ion-icon[contains(@name,'ellipsis-vertical-outline')]")
	public MobileElement MyWorkListMorePatDetailsIcon;
	
	@FindBy(xpath = "//dxc-more-list//ion-icon[contains(@name,'close')]")
	public MobileElement MyWorkListClosePatDetailsIcon;
	

	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Building')]/following-sibling::span")
	public MobileElement MyWorkListEncBuilding;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Bay')]/following-sibling::span")
	public MobileElement MyWorkListEncBay;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Building')]/following-sibling::span")
	public MobileElement MyWorkListMoreEncBuilding;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Bay')]/following-sibling::span")
	public MobileElement MyWorkListMoreEncBay;
	
	@FindBy(xpath = "//ion-icon[@name='pin']/parent::span/following-sibling::span")
	public MobileElement MyWorkListMoreEncLocation;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Planned')]/following-sibling::span")
	public MobileElement MyWorkListMoreEncPlannedDate;
	
	@FindBy(xpath = "//dxc-more-list//ion-row/ion-grid/ion-row//span[@class='titleBold']")
	public MobileElement MyWorkListMoreEncCareprovider;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Gender')]/following-sibling::span")
	public MobileElement MyWorkListMorePatGender;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'PAS') and contains(@class,'labelColor')]/following-sibling::span")
	public MobileElement MyWorkListMorePatPASID;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'NHS')]/following-sibling::span")
	public MobileElement MyWorkListMorePatNHS;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Born')]/following-sibling::span")
	public MobileElement MyWorkListMorePatDOB;
	
	@FindBy(xpath = "//*[contains(text(),'Inpatient')]/parent::ion-segment-button")
	public MobileElement MyWorkListInpatientTab;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//*[contains(text(),'DoB')]/parent::span")
	public MobileElement MyWorkListPatDOBWP;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//*[contains(text(),'HRN')]/parent::span")
	public MobileElement MyWorkListPatHRN;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//*[contains(text(),'Site')]/following-sibling::span")
	public MobileElement MyWorkListEncSite;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//*[contains(text(),'Room')]/following-sibling::span")
	public MobileElement MyWorkListEncRoom;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//*[contains(text(),'Ward')]/following-sibling::span")
	public MobileElement MyWorkListEncWard;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//*[contains(text(),'Bed')]/following-sibling::span")
	public MobileElement MyWorkListEncBed;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'HRN') and contains(@class,'labelColor')]/following-sibling::span")
	public MobileElement MyWorkListMorePatHRN;
	 
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'DoB')]/following-sibling::span")
	public MobileElement MyWorkListMorePatDOBWP;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Bed')]/following-sibling::span")
	public MobileElement MyWorkListMoreEncBed;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Site')]/following-sibling::span")
	public MobileElement MyWorkListMoreEncSite;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Room')]/following-sibling::span")
	public MobileElement MyWorkListMoreEncRoom;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Ward')]/following-sibling::span")
	public MobileElement MyWorkListMoreEncWard;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Visit No')]/following-sibling::span")
	public MobileElement MyWorkListMoreEncID;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Admitted')]/following-sibling::span")
	public MobileElement MyWorkListMoreEncAdmittedDate;
	
	@FindBy(xpath = "//*[contains(text(),'Ward Leave')]/parent::ion-segment-button")
	public MobileElement WardLeaveTab;
	@FindBy(xpath = "//span[contains(@class,'bed-icon')]/ancestor::ion-segment-button")
	public MobileElement WorkListWardLeaveTab;
}



