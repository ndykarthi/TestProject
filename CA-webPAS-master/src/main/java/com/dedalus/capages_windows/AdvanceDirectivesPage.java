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

public class AdvanceDirectivesPage extends CAProjectMethods {

	public AdvanceDirectivesPage(RemoteWebDriver driver, ExtentTest test) {
		this.test = test;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'nonselected-tab')]//span[contains(text(),'Advance Dir')]")
	public WebElement selectAdvDir;
	
	@FindBy(xpath = "//ion-toggle")
	public WebElement toggle;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Summary')]")
	public WebElement selectSummary;
	
	@FindBy(xpath ="//ion-label[contains(text(),'Active')]/parent::ion-segment-button")
	public WebElement activeFauxtab;
 
	@FindBy(xpath ="//ion-label[contains(text(),'All')]/parent::ion-segment-button")
	public WebElement allFauxtab;
 
	@FindBy(xpath ="//ion-label[contains(text(),'Inactive')]/parent::ion-segment-button")
	public WebElement inactiveFauxtab;

	@FindBy(how = How.XPATH, using = ("(//ion-item//ion-label/h2)[1]"))
	public WebElement clickrecord;
	
	@FindBy(how = How.XPATH, using = ("//ion-item//ion-label/h2/span"))
	public WebElement advType;

	@FindBy(how = How.XPATH, using = ("//ion-item//ion-label/h2/ion-badge"))
	public WebElement status;

	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Obtained Date')]/following-sibling::span"))
	public WebElement obtainedDate;
	
	@FindBy(how = How.XPATH, using = ("//p[contains(text(),'Attachment')]/following-sibling::p"))
	public WebElement attachName;
	
	@FindBy(how = How.XPATH, using = ("//p[contains(text(),'Attachment')]/following-sibling::p/ancestor::div[@class='item-inner']/button//ion-icon[@name='open']"))
	public WebElement viewIcon;

	public String clickViewIcon() throws InterruptedException {
		//actionClick(viewIcon);
		jsclick(viewIcon,"View");
		Thread.sleep(2000);
		takeSnap();
		return null;
	}
	
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Scope')]/following-sibling::span"))
	public WebElement scope;

	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Patient Statement')]/following-sibling::span"))
	public WebElement patStmt;
	  
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Care Provider')]/following-sibling::span"))
	public WebElement careProv;
	
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Modified date and time')]/following-sibling::span"))
	public WebElement modDate;

	@FindBy(how = How.XPATH, using = ("//ion-label/p[contains(text(),'Comments')]/following-sibling::p"))
	public WebElement comments;
		
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Status Reason')]/following-sibling::span"))
	public WebElement statusRsn;

	@FindBy(how = How.XPATH, using = ("//p[contains(text(),'Advance Directives')]/parent::div/following-sibling::div/p"))
	public WebElement summaryCount;
	
	@FindBy(how = How.XPATH, using = ("(//p[contains(text(),'Advance Directives')]/ancestor::div[@class='header']/following-sibling::div//p)[1]"))
	public WebElement summaryName;
	
	@FindBy(how = How.XPATH, using = ("//p[contains(text(),'Advance Directives')]/parent::div//ion-icon"))
	public WebElement summaryIcon;
	
	@FindBy(how = How.XPATH, using = ("//ion-grid//span[contains(@class,'mdi-account-voice')]/following-sibling::span"))
	public WebElement bannerCount; //no ion-icon tag is available in banner.
	
	//@FindBy(how = How.XPATH, using = ("(//ion-col//ion-icon[@name='md-more'])[2]"))
	@FindBy(how = How.XPATH, using = ("//ion-col[contains(@class,'more')][2]"))
	public WebElement morePatDetails;
	
 	@FindBy(how = How.XPATH, using = ("//ion-label[contains(text(),'Advance Directives')]/following-sibling::ion-note"))
	 //@FindBy(how = How.XPATH, using = ("(//div[@class='more-detail-banner-content']//div//ion-label[contains(text(),'Advance Directives')])/following-sibling::ion-note"))
	public WebElement morePatDetailsCount;
	
	@FindBy(how = How.XPATH, using = ("((//div[@class='more-detail-banner-content']//div//ion-label[contains(text(),'Advance Directives')])/parent::ion-item/following-sibling::div//span)[1]"))
	public WebElement morePatDetailsAdvDirName;
	
	@FindBy(how = How.XPATH, using = "//ion-icon[@name='menu']")
	public WebElement clickMenu;
	
	
	// Strikeout
	
	@FindBy(xpath = "//h2/ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-item//div[contains(@class,'expand')]")
	public WebElement expandStrikeOutRec;

	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Patient Statement')]/following-sibling::span")
	public WebElement strikeOutADStmnt;

	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Scope')]/following-sibling::span")
	public WebElement strikeOutADScope;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Care Provider')]/following-sibling::span")
	public WebElement strikeOutADCP;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Status Reason')]/following-sibling::span")
	public WebElement strikeOutADStatusRsn;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Modified date')]/following-sibling::span")
	public WebElement strikeOutADModDate;

	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//p[contains(text(),'Comments') and contains(@class,'prop-name')]/following-sibling::p")
	public WebElement strikeOutADComm;
	
	@FindBy(xpath = "//ion-card//h2//ion-badge[contains(text(),'Entered-in-error')]/preceding-sibling::span")
	public WebElement strikeOutADName;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Obtained')]/following-sibling::span")
	public WebElement strikeOutADObtDate;
		
	
	// Closed		
	@FindBy(xpath = "//h2/ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//div[contains(@class,'expand')]")
	public WebElement expandClosedOutRec;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Patient Statement')]/following-sibling::span")
	public WebElement ClosedADStmnt;

	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Scope')]/following-sibling::span")
	public WebElement ClosedADScope;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Care Provider')]/following-sibling::span")
	public WebElement ClosedADCP;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Status Reason')]/following-sibling::span")
	public WebElement ClosedADStatusRsn;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Modified date')]/following-sibling::span")
	public WebElement ClosedADModDate;

	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//p[contains(text(),'Comments') and contains(@class,'prop-name')]/following-sibling::p")
	public WebElement ClosedADComm;
	
	@FindBy(xpath = "//ion-card//h2//ion-badge[contains(text(),'Inactive')]/preceding-sibling::span")
	public WebElement ClosedADName;
		
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/parent::h2/following-sibling::div//span[contains(text(),'Obtained')]/following-sibling::span")
	public WebElement ClosedADObtDate;
		
	
	
	public int getAdvDirActiveCount() throws InterruptedException {
		int totalCnt = 0;
		try {
			List<WebElement> advDirCntActive = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Active')]");
			List<WebElement> advDirCntDraft = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Draft')]");
			int AdvDirCnt = advDirCntActive.size();
			int AdvDirCnt1 = advDirCntDraft.size();	
			totalCnt = AdvDirCnt + AdvDirCnt1;
			takeSnap();
		}catch (InvalidElementStateException e) {
		   reportStep("The element is not available", "FAIL");
		} catch (WebDriverException e) {
		   reportStep("The element is not available", "FAIL");
		}	
		return totalCnt;
	}
	
	public int getAdvDirINActiveCount() throws InterruptedException {
		int totalCnt = 0;
		try {
			List<WebElement> advDirCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Inactive')]");
			List<WebElement> advDirCnt1 = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Entered-in-error')]");
			int Cnt = advDirCnt.size();
			int Cnt1 = advDirCnt1.size();
			totalCnt = Cnt + Cnt1;
			takeSnap();			
		}catch (InvalidElementStateException e) {
	      reportStep("The element is not available", "FAIL");
	    } catch (WebDriverException e) {
	      reportStep("The element is not available", "FAIL");
	    }	
		return totalCnt;
	}
}
