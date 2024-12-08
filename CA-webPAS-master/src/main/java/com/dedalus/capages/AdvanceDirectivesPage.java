package com.dedalus.capages;

import java.util.List;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.GenericAppMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AdvanceDirectivesPage extends GenericAppMethods {

	public AdvanceDirectivesPage(AppiumDriver<MobileElement> driver, ExtentTest test) {
		this.test = test; 
		this.driver = driver;
		//PageFactory.initElements(driver, this);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
//	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Advance Directives')]")
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'nonselected-tab')]//span[contains(text(),'Advance Dir')]")
	public MobileElement selectAdvDir;
	
	@FindBy(xpath = "//ion-toggle")
	public MobileElement toggle;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Summary')]")
	public MobileElement selectSummary;
	
	@FindBy(xpath ="//ion-label[contains(text(),'Active')]/parent::ion-segment-button")
	public MobileElement activeFauxtab;
 
	@FindBy(xpath ="//ion-label[contains(text(),'All')]/parent::ion-segment-button")
	public MobileElement allFauxtab;
 
	@FindBy(xpath ="//ion-label[contains(text(),'Inactive')]/parent::ion-segment-button")
	public MobileElement inactiveFauxtab;

	@FindBy(how = How.XPATH, using = ("(//ion-item//ion-label/h2)[1]"))
	public MobileElement clickrecord;
	
	@FindBy(how = How.XPATH, using = ("//ion-item//ion-label/h2/span"))
	public MobileElement advType;

	@FindBy(how = How.XPATH, using = ("//ion-item//ion-label/h2/ion-badge"))
	public MobileElement status;

	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Obtained Date')]/following-sibling::span"))
	public MobileElement obtainedDate;
	
	@FindBy(how = How.XPATH, using = ("//p[contains(text(),'Attachment')]/following-sibling::p"))
	public MobileElement attachName;
	
	@FindBy(how = How.XPATH, using = ("//p[contains(text(),'Attachment')]/following-sibling::p/ancestor::div[@class='item-inner']/button//ion-icon[@name='open']"))
	public MobileElement viewIcon;

	public String clickViewIcon() throws InterruptedException {
		//actionClick(viewIcon);
		jsclick(viewIcon,"View");
		Thread.sleep(2000);
		takeSnap();
		return null;
	}
	
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Scope')]/following-sibling::span"))
	public MobileElement scope;

	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Patient Statement')]/following-sibling::span"))
	public MobileElement patStmt;
	  
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Care Provider')]/following-sibling::span"))
	public MobileElement careProv;
	
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Modified date and time')]/following-sibling::span"))
	public MobileElement modDate;

	@FindBy(how = How.XPATH, using = ("//ion-label/p[contains(text(),'Comments')]/following-sibling::p"))
	public MobileElement comments;
		
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Status Reason')]/following-sibling::span"))
	public MobileElement statusRsn;

	@FindBy(how = How.XPATH, using = ("//p[contains(text(),'Advance Directives')]/parent::div/following-sibling::div/p"))
	public MobileElement summaryCount;
	
	@FindBy(how = How.XPATH, using = ("(//p[contains(text(),'Advance Directives')]/ancestor::div[@class='header']/following-sibling::div//p)[1]"))
	public MobileElement summaryName;
	
	@FindBy(how = How.XPATH, using = ("//p[contains(text(),'Advance Directives')]/parent::div//ion-icon"))
	public MobileElement summaryIcon;
	
	@FindBy(how = How.XPATH, using = ("//ion-grid//span[contains(@class,'mdi-account-voice')]/following-sibling::span"))
	public MobileElement bannerCount; //no ion-icon tag is available in banner.
	
	//@FindBy(how = How.XPATH, using = ("(//ion-col//ion-icon[@name='md-more'])[2]"))
	@FindBy(how = How.XPATH, using = ("//ion-col[contains(@class,'more')][2]"))
	public MobileElement morePatDetails;
	
 	@FindBy(how = How.XPATH, using = ("//ion-label[contains(text(),'Advance Directives')]/following-sibling::ion-note"))
	 //@FindBy(how = How.XPATH, using = ("(//div[@class='more-detail-banner-content']//div//ion-label[contains(text(),'Advance Directives')])/following-sibling::ion-note"))
	public MobileElement morePatDetailsCount;
	
	@FindBy(how = How.XPATH, using = ("((//div[@class='more-detail-banner-content']//div//ion-label[contains(text(),'Advance Directives')])/parent::ion-item/following-sibling::div//span)[1]"))
	public MobileElement morePatDetailsAdvDirName;
	
	@FindBy(how = How.XPATH, using = "//ion-icon[@name='menu']")
	public MobileElement clickMenu;
	
	
	// Strikeout
	
	@FindBy(xpath = "//h2/ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-item//div[contains(@class,'expand')]")
	public MobileElement expandStrikeOutRec;

	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Patient Statement')]/following-sibling::span")
	public MobileElement strikeOutADStmnt;

	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Scope')]/following-sibling::span")
	public MobileElement strikeOutADScope;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Care Provider')]/following-sibling::span")
	public MobileElement strikeOutADCP;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Status Reason')]/following-sibling::span")
	public MobileElement strikeOutADStatusRsn;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Modified date')]/following-sibling::span")
	public MobileElement strikeOutADModDate;

	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//p[contains(text(),'Comments') and contains(@class,'prop-name')]/following-sibling::p")
	public MobileElement strikeOutADComm;
	
	@FindBy(xpath = "//ion-card//h2//ion-badge[contains(text(),'Entered-in-error')]/preceding-sibling::span")
	public MobileElement strikeOutADName;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Obtained')]/following-sibling::span")
	public MobileElement strikeOutADObtDate;
		
	
	// Closed		
	@FindBy(xpath = "//h2/ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//div[contains(@class,'expand')]")
	public MobileElement expandClosedOutRec;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Patient Statement')]/following-sibling::span")
	public MobileElement ClosedADStmnt;

	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Scope')]/following-sibling::span")
	public MobileElement ClosedADScope;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Care Provider')]/following-sibling::span")
	public MobileElement ClosedADCP;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Status Reason')]/following-sibling::span")
	public MobileElement ClosedADStatusRsn;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Modified date')]/following-sibling::span")
	public MobileElement ClosedADModDate;

	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//p[contains(text(),'Comments') and contains(@class,'prop-name')]/following-sibling::p")
	public MobileElement ClosedADComm;
	
	@FindBy(xpath = "//ion-card//h2//ion-badge[contains(text(),'Inactive')]/preceding-sibling::span")
	public MobileElement ClosedADName;
		
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/parent::h2/following-sibling::div//span[contains(text(),'Obtained')]/following-sibling::span")
	public MobileElement ClosedADObtDate;
		
	
	
	public int getAdvDirActiveCount() throws InterruptedException {
		int totalCnt = 0;
		try {
			List<MobileElement> advDirCntActive = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Active')]");
			List<MobileElement> advDirCntDraft = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Draft')]");
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
			List<MobileElement> advDirCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Inactive')]");
			List<MobileElement> advDirCnt1 = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Entered-in-error')]");
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
