package com.dedalus.capages_windows;

import java.util.List;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods_windows.CAProjectMethods;

public class MyCareprovidersPage extends CAProjectMethods {
	//testing
	public MyCareprovidersPage(RemoteWebDriver driver, ExtentTest test) {
		this.test = test;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath ="//div[contains(text(),'My Care Providers')]/span")
	public WebElement titleBarMyCPName;
	
	@FindBy(xpath = "//*[contains(text(),'Admitted')]/parent::ion-segment-button")
	public WebElement MyCPAdmittedTab;
	
	@FindBy(xpath = "//*[contains(text(),'Planned')]/parent::ion-segment-button")
	public WebElement MyCPPlannedTab;
	
	@FindBy(xpath = "//*[contains(text(),'Ward Leave')]/parent::ion-segment-button")
	public WebElement MyCPWardLeaveTab;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-DIVIDER//ION-BADGE")
	public WebElement MyCPEncCount;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-DIVIDER//ION-LABEL")
	public WebElement MyCPEncLocation;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//H2")
	public WebElement MyCPPatName;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'NHS')]/parent::span")
	public WebElement MyCPPatNHS;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Born')]/parent::span")
	public WebElement MyCPPatDOB;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//ion-icon[@name='star']")
	public WebElement MyCPMarkUnfavouriteIcon;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//ion-icon[contains(@aria-label,'ellipsis vertical outline')]")
	public WebElement MyCPMorePatDetailsIcon;
	
	@FindBy(xpath = "//dxc-more-list//ion-icon[contains(@name,'close')]")
	public WebElement MyCPClosePatDetailsIcon;
	

	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Building')]/following-sibling::span")
	public WebElement MyCPEncBuilding;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Bay')]/following-sibling::span")
	public WebElement MyCPEncBay;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Building')]/following-sibling::span")
	public WebElement MyCPMoreEncBuilding;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Bay')]/following-sibling::span")
	public WebElement MyCPMoreEncBay;
	
	@FindBy(xpath = "//ion-icon[@name='pin']/parent::span/following-sibling::span")
	public WebElement MyCPMoreEncLocation;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Planned')]/following-sibling::span")
	public WebElement MyCPMoreEncPlannedDate;
	
	@FindBy(xpath = "//dxc-more-list//ion-row/ion-grid/ion-row//span[@class='titleBold']")
	public WebElement MyCPMoreEncCareprovider;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Gender')]/following-sibling::span")
	public WebElement MyCPMorePatGender;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'PAS') and contains(@class,'labelColor')]/following-sibling::span")
	public WebElement MyCPMorePatPASID;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'NHS')]/following-sibling::span")
	public WebElement MyCPMorePatNHS;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Born')]/following-sibling::span")
	public WebElement MyCPMorePatDOB;
	
	@FindBy(xpath = "//*[contains(text(),'Inpatient')]/parent::ion-segment-button")
	public WebElement MyCPInpatientTab;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'DoB')]/parent::span")
	public WebElement MyCPPatDOBWP;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'HRN')]/parent::span")
	public WebElement MyCPPatHRN;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Site')]/following-sibling::span")
	public WebElement MyCPEncSite;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Room')]/following-sibling::span")
	public WebElement MyCPEncRoom;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Ward')]/following-sibling::span")
	public WebElement MyCPEncWard;
	
	@FindBy(xpath = "//DXC-LIST-ITEMS//ION-ITEM-GROUP//ION-ITEM-SLIDING//p//*[contains(text(),'Bed')]/following-sibling::span")
	public WebElement MyCPEncBed;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'HRN') and contains(@class,'labelColor')]/following-sibling::span")
	public WebElement MyCPMorePatHRN;
	 
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'DoB')]/following-sibling::span")
	public WebElement MyCPMorePatDOBWP;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Site')]/following-sibling::span")
	public WebElement MyCPMoreEncSite;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Room')]/following-sibling::span")
	public WebElement MyCPMoreEncRoom;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Ward')]/following-sibling::span")
	public WebElement MyCPMoreEncWard;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Visit No')]/following-sibling::span")
	public WebElement MyCPMoreEncID;
	
	@FindBy(xpath = "//dxc-more-list//*[contains(text(),'Admitted')]/following-sibling::span")
	public WebElement MyCPMoreEncAdmittedDate;
}