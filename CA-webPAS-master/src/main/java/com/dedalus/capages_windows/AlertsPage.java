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

import io.appium.java_client.MobileElement;

public class AlertsPage extends CAProjectMethods {
	//testing
	public AlertsPage(RemoteWebDriver driver, ExtentTest test) {
		this.test = test;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//dxc-alert-list-item")
	public List<WebElement> getAlertCount;
	
	@FindBy(xpath = "//ion-toggle")
	public WebElement toggle;
	
	@FindBy(xpath = "//ion-icon[@class='tab-button-icon md hydrated']/following::span[text()='Alerts']")
	public WebElement AlertsTab;

	@FindBy(xpath = "//dxc-alert-list-item[1]//ion-item//h2//span")
	public WebElement AlertName1;

	@FindBy(xpath = "(//ion-item//ion-label/h2/ion-badge)[1]")
	public WebElement AlertStatus1;

	@FindBy(xpath = "(//span[contains(text(),'Type')])[1]/following-sibling::span")
	public WebElement AlertType1;

	@FindBy(xpath = "(//span[contains(text(),'Is significant')])[1]/following-sibling::span")
	public WebElement IsSignificant1;

	@FindBy(xpath = "(//span[contains(text(),'Severity')])[1]/following-sibling::span")
	public WebElement AlertSeverity1;

	@FindBy(xpath = "(//span[contains(text(),'Information source:')]/following-sibling::span)[1]")
	public WebElement AlertInfoSource1;

	@FindBy(xpath = "(//span[contains(text(),'Onset')])[1]/following-sibling::span")
	public WebElement AlertOnset1;
	
	@FindBy(xpath = "(//ion-icon[contains(@name,'chevron-forward')])[2]")
	public WebElement ExpandAlert1;
	
	@FindBy(xpath = "(//span[contains(text(),'Scope:')])[1]/following-sibling::span")
	public WebElement AlertScope1;

	@FindBy(xpath = "(//span[contains(text(),'Recorded on:')])[1]/following-sibling::span")
	public WebElement AlertRecordedDateTime1;

	@FindBy(xpath = "(//span[contains(text(),'Recorded by:')])/following-sibling::span")
	public WebElement AlertRecordedBy1;
	
	@FindBy(xpath = "(//span[contains(text(),'Description')])/following-sibling::span")
	public WebElement AlertDesc1;
	
	@FindBy(xpath = "(//span[contains(text(),'Expected conclusion')])/following-sibling::span")
	public WebElement AlertExpConcl;
	
	@FindBy(xpath = "(//span[contains(text(),'Modified on')])/following-sibling::span")
	public WebElement AlertModDtm;
	
	@FindBy(xpath = "(//span[contains(text(),'Review date')])/following-sibling::span")
	public WebElement AlertRevDtm;
	
	public int getAlertActiveCount() throws InterruptedException {
		int AlertCnt = 0;
		try {
			List<WebElement> alertCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Active')]");
			AlertCnt = alertCnt.size();
			takeSnap();
			reportStep("Active Alert Count: "+AlertCnt, "PASS");
		}catch (InvalidElementStateException e) {
		      reportStep("The element is not available", "FAIL");
		    } catch (WebDriverException e) {
		      reportStep("The element is not available", "FAIL");
		    }
		return AlertCnt;	
	}
	
	public int getAlertINActiveCount() throws InterruptedException {
		int totalCnt = 0;
		try {
			List<WebElement> alertCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Inactive')]");
			List<WebElement> alertCnt1 = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Entered-in-error')]");
			int AlertCnt = alertCnt.size();
			int AlertCnt1 = alertCnt1.size();
			totalCnt = AlertCnt + AlertCnt1;
			takeSnap();
			reportStep("InActive Alert Count: "+totalCnt, "PASS");
		}catch (InvalidElementStateException e) {
		      reportStep("The element is not available", "FAIL");
		    } catch (WebDriverException e) {
		      reportStep("The element is not available", "FAIL");
		    }	
		return totalCnt;
	}
	
	// Inactive    
	@FindBy(xpath = "//h2/ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//div[contains(@class,'expand')]")
	public WebElement expandClosedRec;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/parent::h2/following-sibling::div//span[contains(text(),'Type')]/following-sibling::span")
	public WebElement ClosedAlertType;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/parent::h2/following-sibling::div//span[contains(text(),'Is significant')]/following-sibling::span")
	public WebElement ClosedAlertIsSign;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/parent::h2/following-sibling::div//span[contains(text(),'Severity')]/following-sibling::span")		
	public WebElement ClosedAlertSev;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Information source')]/following-sibling::span")
	public WebElement ClosedAlertInfoSrc;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/parent::h2/following-sibling::div//span[contains(text(),'Onset')]/following-sibling::span")
	public WebElement ClosedAlertOnset;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Scope')]/following-sibling::span")
	public WebElement ClosedAlertScope;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Description')]/following-sibling::span")
	public WebElement ClosedAlertDesc;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Recorded on')]/following-sibling::span")
	public WebElement ClosedAlertRecDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Recorded by')]/following-sibling::span")
	public WebElement ClosedAlertRecBy;
	
	@FindBy(xpath = "//ion-card//h2//ion-badge[contains(text(),'Inactive')]/preceding-sibling::span")
	public WebElement ClosedAlertName;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Expected conclusion')]/following-sibling::span")
	public WebElement ClosedAlertExpConclDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Modified on')]/following-sibling::span")
	public WebElement ClosedAlertModDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Closing Reason')]/following-sibling::span")
	public WebElement ClosedAlertCloseRsn;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Status comments')]/following-sibling::span")
	public WebElement ClosedAlertComments;
	
//	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Review date')]/following-sibling::span")
//	public WebElement ClosedAlertRevDate;
	
	
	
	// Strikeout - Entered-in-error
	@FindBy(xpath = "//h2/ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-item//div[contains(@class,'expand')]")
	public WebElement expandStrikeOutRec; 
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Type')]/following-sibling::span")
	public WebElement StrikedOutAlertType;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Is significant')]/following-sibling::span")
	public WebElement StrikedOutAlertIsSign;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Severity')]/following-sibling::span")
	public WebElement StrikedOutAlertSev;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Information source')]/following-sibling::span")
	public WebElement StrikedOutAlertInfoSrc;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Onset')]/following-sibling::span")
	public WebElement StrikedOutAlertOnset;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Scope')]/following-sibling::span")
	public WebElement StrikedOutAlertScope;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Description')]/following-sibling::span")
	public WebElement StrikedOutAlertDesc;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Recorded on')]/following-sibling::span")
	public WebElement StrikedOutAlertRecDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Recorded by')]/following-sibling::span")
	public WebElement StrikedOutAlertRecBy;
	
	@FindBy(xpath = "//ion-card//h2//ion-badge[contains(text(),'Entered-in-error')]/preceding-sibling::span")
	public WebElement StrikedOutAlertName;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Expected conclusion date')]/following-sibling::span")
	public WebElement StrikedOutAlertExpConclDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Review date')]/following-sibling::span")
	public WebElement StrikedOutAlertRevDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Modified on')]/following-sibling::span")
	public WebElement StrikedOutAlertModDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Deletion Reason')]/following-sibling::span")
	public WebElement StrikedOutAlertDeletionRsn;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Status comments')]/following-sibling::span")
	public WebElement StrikedOutAlertComments;
	
	

	  @FindBy(xpath = "//p[@class='title' and contains(text(),'Alerts')]")
		public WebElement summaryAlertstext;
	
		@FindBy(xpath ="//ion-label[contains(text(),'Active')]/parent::ion-segment-button")
		public WebElement activeFauxtab;
	 
		@FindBy(xpath ="//ion-label[contains(text(),'All')]/parent::ion-segment-button")
		public WebElement allFauxtab;
	 
		@FindBy(xpath ="//ion-label[contains(text(),'Inactive')]/parent::ion-segment-button")
		public WebElement inactiveFauxtab;

		
		
		// Web PAS
		@FindBy(xpath = "(//span[contains(text(),'Recorded by')])/following-sibling::span")
		public WebElement AlertRecordedBy;
		
		@FindBy(xpath = "(//span[contains(text(),'Recorded on')])/following-sibling::span")
		public WebElement AlertRecordedOn;
		
		@FindBy(xpath = "//span[contains(text(),'Initiating hospital')]/following-sibling::span")
		public WebElement AlertInitHospital;
		
		@FindBy(xpath = "//span[contains(text(),'Requested by HCP')]/following-sibling::span")
		public WebElement AlertRequestBy;
		
		@FindBy(xpath = "(//span[contains(text(),'Recorded on')])/following-sibling::span")
		public WebElement AlertEndDate;
		
		@FindBy(xpath = "//span[contains(text(),'Site')]/following-sibling::span")
		public WebElement AlertSite;
		
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Recorded on')]/following-sibling::span")
		public WebElement ClosedAlertRecOn;
		
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Recorded by')]/following-sibling::span")
		public WebElement ClosedAlertRecordedBy;
		
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Site')]/following-sibling::span")
		public WebElement ClosedAlertSite;
		
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Initiating hospital')]/following-sibling::span")
		public WebElement ClosedAlertInitHospital;
		
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Requested by HCP')]/following-sibling::span")
		public WebElement ClosedAlertRequestBy;
		
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'End date')]/following-sibling::span")
		public WebElement ClosedAlertEndDate;
		
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Review date')]/following-sibling::span")
		public WebElement ClosedAlertRevDate;
		
		@FindBy(xpath = "//p[@class='title spacing']")
		public WebElement SummaryAlertName;

		@FindBy(xpath = "//span[@class='details'][1]/div")
		public WebElement SummaryAlertType;

		@FindBy(xpath = "//span[@class='details'][2]/div")
		public WebElement SummaryAlertOnsetDate;
		
		
}



