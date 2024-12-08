package com.dedalus.capages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.CAProjectMethods;
import com.dedalus.genericappmethods.GenericAppMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MyCareprovidersPage extends CAProjectMethods{
	
	public MyCareprovidersPage(AppiumDriver<MobileElement> driver, ExtentTest test) {
		this.test=test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(xpath ="//div[contains(text(),'My Care Providers')]/span")
	public MobileElement titleBarMyCPName;
	
	@FindBy(xpath = "//*[contains(text(),'Admitted')]/parent::ion-segment-button")
	public MobileElement MyCPAdmittedTab;
	
	@FindBy(xpath = "//*[contains(text(),'Planned')]/parent::ion-segment-button")
	public MobileElement MyCPPlannedTab;
	
	@FindBy(xpath = "//*[contains(text(),'Ward Leave')]/parent::ion-segment-button")
	public MobileElement MyCPWardLeaveTab;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-DIVIDER//ION-BADGE")
	public MobileElement MyCPEncCount;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-DIVIDER//ION-LABEL")
	public MobileElement MyCPEncLocation;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//H2")
	public MobileElement MyCPPatName;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'NHS')]/parent::span")
	public MobileElement MyCPPatNHS;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Born')]/parent::span")
	public MobileElement MyCPPatDOB;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//ion-icon[@name='star']")
	public MobileElement MyCPMarkUnfavouriteIcon;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//ion-icon[contains(@aria-label,'ellipsis vertical outline')]")
	public MobileElement MyCPMorePatDetailsIcon;
	
	@FindBy(xpath = "//dxc-more-list//ion-icon[contains(@name,'close')]")
	public MobileElement MyCPClosePatDetailsIcon;
	

	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Building')]/following-sibling::span")
	public MobileElement MyCPEncBuilding;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Bay')]/following-sibling::span")
	public MobileElement MyCPEncBay;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Building')]/following-sibling::span")
	public MobileElement MyCPMoreEncBuilding;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Bay')]/following-sibling::span")
	public MobileElement MyCPMoreEncBay;
	
	@FindBy(xpath = "//ion-icon[@name='pin']/parent::span/following-sibling::span")
	public MobileElement MyCPMoreEncLocation;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Planned')]/following-sibling::span")
	public MobileElement MyCPMoreEncPlannedDate;
	
	@FindBy(xpath = "//dxc-more-list//ion-row/ion-grid/ion-row//span[@class='titleBold']")
	public MobileElement MyCPMoreEncCareprovider;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Gender')]/following-sibling::span")
	public MobileElement MyCPMorePatGender;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'PAS') and contains(@class,'labelColor')]/following-sibling::span")
	public MobileElement MyCPMorePatPASID;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'NHS')]/following-sibling::span")
	public MobileElement MyCPMorePatNHS;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Born')]/following-sibling::span")
	public MobileElement MyCPMorePatDOB;
	
	@FindBy(xpath = "//*[contains(text(),'Inpatient')]/parent::ion-segment-button")
	public MobileElement MyCPInpatientTab;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'DoB')]/parent::span")
	public MobileElement MyCPPatDOBWP;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'HRN')]/parent::span")
	public MobileElement MyCPPatHRN;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Site')]/following-sibling::span")
	public MobileElement MyCPEncSite;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Room')]/following-sibling::span")
	public MobileElement MyCPEncRoom;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Ward')]/following-sibling::span")
	public MobileElement MyCPEncWard;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Bed')]/following-sibling::span")
	public MobileElement MyCPEncBed;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'HRN') and contains(@class,'labelColor')]/following-sibling::span")
	public MobileElement MyCPMorePatHRN;
	 
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'DoB')]/following-sibling::span")
	public MobileElement MyCPMorePatDOBWP;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Site')]/following-sibling::span")
	public MobileElement MyCPMoreEncSite;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Room')]/following-sibling::span")
	public MobileElement MyCPMoreEncRoom;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Ward')]/following-sibling::span")
	public MobileElement MyCPMoreEncWard;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Visit No')]/following-sibling::span")
	public MobileElement MyCPMoreEncID;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Admitted')]/following-sibling::span")
	public MobileElement MyCPMoreEncAdmittedDate;
}


